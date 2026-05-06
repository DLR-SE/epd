package de.emir.tuml.runtime.epf;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.ParserConfigurationException;

import com.github.zafarkhaja.semver.Version;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.FileOperations;
import de.emir.tuml.ucore.runtime.utils.XMLReader;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.tesla.aether.Repository;

/**
 * Utility class as an interface for product definition file (Product.xml). A product file is similar to maven project
 * pom files (pom.xml) and contains the plugin configuration of a product. It includes dependencies (e.g. maven),
 * workspaces (dependencies set as path to pom files for development) and repositories. This class is capable of
 * serializing and deserializing of product files.
 *
 * The localRepository is a path to a local maven repository (e.g. userHome/.m2/repository folder). It can contain
 * variables to simplify sharing of product xml files with relative paths. The following list shows variables for
 * this field and their replacement:
 * - ${PRODUCT_FOLDER} -> absolute path to the folder where the product xml file is located
 * - ${CWD_FOLDER} -> absolute path to the current working directory
 * - ${HOME_FOLDER} -> absolute path to user home directory
 * Note that these are not environmental variables!
 *
 * Workspaces contain paths to local maven pom.xml files. These will be read on startup to run local projects. In debug
 * mode it is also possible to load class files for debugging. Dependencies are defined as standard maven coordinates
 * (group, artifact, version). The plugin framework will use maven to resolve these. Remote repository are also defined
 * as standard maven repositories and resolved by maven itself.
 */
public class ProductFile {
    /** occurrences within the localRepos node will be replaced with the Product.xml folder path */
    public static final String VARIABLE_PRODUCT_DIRECTORY = "${PRODUCT_FOLDER}";
    /** occurrences within the localRepos node will be replaced with the current working directory */
    public static final String VARIABLE_CHANGE_WORKING_DIRECTORY = "${CWD_FOLDER}";
    /** occurrences within the localRepos node will be replaced with the user home folder */
    public static final String VARIABLE_HOME_DIRECTORY = "${HOME_FOLDER}";

    /** The constant dependency separator. */
    public static final String DEPENDENCY_SEPARATOR = ":";

    /** The default product definition file name. */
    public static final String PRODUCT_DEFINITION = "Product.xml";

    /** product name */
    private String 												mName;
    /** product description */
    private String 												mDescription;
    /** version of the product of the EPD itself */
    private String                                              mVersion;

    /**
     * Workspaces are directories, containing a pom file, usually a source directory, with compiled sources (e.g.
     * target directory) The PluginLoader will resolve all artefacts of the workspace and read the related target files
     * as plugins
     */
    private ArrayList<File> 									mWorkspaces = new ArrayList<>();
    private ArrayList<ObservableDependency> 					mDependencies = new ArrayList<>();
    private ArrayList<ObservableRepository> 					mRepositories = new ArrayList<>();
    private String 												mLocalRepository;
    private File                                                mProductFile;
    private String 												mIdentity;

    private PublishSubject<List<File>> 							workspacesSubject = PublishSubject.create();
    private PublishSubject<List<ObservableRepository>> 			repositoriesSubject = PublishSubject.create();
    private PublishSubject<List<ObservableDependency>> 			dependencySubject = PublishSubject.create();

    private PublishSubject<Optional<String>> 					localRepositorySubject = PublishSubject.create();

    /**
     * Reads and parses the given product file
     * @param productFile product xml file path
     * @throws SAXException exception occurring during file parsing
     * @throws IOException exception occurring while reading the file
     * @throws ParserConfigurationException exception occurring during file parsing
     */
    public ProductFile(final File productFile) throws SAXException, IOException, ParserConfigurationException {
        read(productFile);
    }

    /**
     * Reads and parses the given product file
     * @param productFile product xml file path
     * @throws SAXException exception occurring during file parsing
     * @throws IOException exception occurring while reading the file
     * @throws ParserConfigurationException exception occurring during file parsing
     */
    public void read(final File productFile) throws SAXException, IOException, ParserConfigurationException {
        mProductFile = productFile;
        XMLReader reader = new XMLReader();
        reader.parseFile(productFile);

        Element documentElement = reader.getDocument().getDocumentElement();
        mName = reader.getValue(reader.findNode(documentElement, "name"));
        mDescription = reader.getValue(reader.findNode(documentElement, "description"));
        mVersion = reader.getValue(reader.findNode(documentElement, "version"));
        
        readLocalRepository(reader);
        readIdentity(reader);
        readWorkspaces(reader);
        readDependencies(reader);
        readRepositories(reader);
    }

    /**
     * The local repository field in a product file can contain variables to simplify sharing of these files with
     * relative repository paths. This method resolves these variables and replaces them. It will return the path to
     * the local repository (as a file object) if the path to the local repository is not empty and the resolved file
     * exists. Otherwise, an empty optional is returned.
     * @param localRepository string content of the localRepository field in a product definition file
     * @param productFolder folder in which the product definition file lies
     * @return resolved local repository file or an empty optional if it cannot be resolved
     */
    private Optional<File> resolveLocalRepositoryPath(String localRepository, File productFolder) {
        if (localRepository != null && !localRepository.isEmpty()){
            if (productFolder.isFile()){
                productFolder = productFolder.getParentFile();
            }

            String home = System.getProperty("user.home");
            String cwd = Path.of("").toAbsolutePath().toString();

            // replace variables with the actual replacement
            localRepository = localRepository.replace(VARIABLE_PRODUCT_DIRECTORY, productFolder.getAbsolutePath());
            localRepository = localRepository.replace(VARIABLE_HOME_DIRECTORY, home);
            localRepository = localRepository.replace(VARIABLE_CHANGE_WORKING_DIRECTORY, cwd);

            File localRepositoryFolder = new File(localRepository);
            if (!localRepositoryFolder.exists() || localRepositoryFolder.isDirectory()) {
                localRepositoryFolder = localRepositoryFolder.isAbsolute() ? localRepositoryFolder : localRepositoryFolder.getAbsoluteFile();
                return Optional.of(localRepositoryFolder);
            }
        }

        return Optional.empty();
    }

    /**
     * Reads the localRepository field in a product definition xml file. On success, it will populate the
     * mLocalRepository member variable. This function is also able to process variables in the localRepository
     * (product folder, cwd folder, home folder). If the local repository folder cannot be found it will
     * default to the maven m2 folder within the users home directory.
     * @param reader product xml reader instance
     */
    private void readLocalRepository(final XMLReader reader){
        if (mLocalRepository == null || mLocalRepository.isEmpty()) {
            // find the repository setting
            String localRepos = reader.getValue(reader.findNode(reader.getRootNode(), "localRepos"));
            // default java M2 folder
            String defaultRepository = System.getProperty("user.home") + File.separator + ".m2" + File.separator + "repository";

            Optional<File> localRepositoryFolder = resolveLocalRepositoryPath(localRepos, mProductFile.getParentFile());
            // check if local repository exists
            if(!localRepositoryFolder.isPresent()) {
                ULog.warn(
                    "No local repository found, using standard directory instead: " + defaultRepository
                );
                mLocalRepository = defaultRepository;
                return;
            }

            File repositoryFolder = localRepositoryFolder.get();
            // create repository folder if it does not exit
            if (repositoryFolder.exists() == false) {
                // if the repository is within the current product folder we need to check if the product folder
                // exists first. If not, it makes no sense to create an empty folder here.
                if (repositoryFolder.getParentFile() != null && !repositoryFolder.getParentFile().exists()){
                    // create the folder if it does not exist
                    ULog.info("Create local repository: " + repositoryFolder.getAbsolutePath());
                    boolean success = repositoryFolder.mkdirs();
                    if (success == false){
                        ULog.error(
                                "Cannot create local repository: "
                                        + repositoryFolder.getAbsolutePath()
                                        + ". Defaulting to "
                                        + defaultRepository
                        );
                        localRepositoryFolder = Optional.of(
                                new File(defaultRepository)
                        );
                    }
                } else {
                    ULog.warn(
                            "Local repository points to an invalid directory use standard directory instead: "
                                    + defaultRepository
                    );
                    localRepositoryFolder = Optional.of(
                            new File(defaultRepository)
                    );
                }
            }
            // sanity check, should be impossible though
            assert localRepositoryFolder.isPresent();
            mLocalRepository = localRepositoryFolder.get().getAbsolutePath();
        }
    }

    /**
     * Read the identity of this product file (group, artifact, version).
     * @param reader product xml reader instance
     */
    private void readIdentity(final XMLReader reader) {
        ULog.debug("read identity...");
        Node doc = reader.getRootNode();
        String groupId = reader.getValue(reader.getChild(doc, "groupId", false));
        String version = reader.getValue(reader.getChild(doc, "version", false));
        String artifactId = reader.getValue(reader.getChild(doc, "artifactId", false));
        mIdentity = groupId + DEPENDENCY_SEPARATOR + artifactId + DEPENDENCY_SEPARATOR + version;
    }

    public String getIdentity() {
        return mIdentity;
    }

    /**
     * Reads in all external repositories, which are contained inside the Product.xml file. Process each repository
     * and checks for id, url and credentials. If credentials are not set they are looked up inside the global and
     * local repository settings.xml if existent.
     * @param reader The XML Reader, used to read the Product.xml file
     */
    private void readRepositories(XMLReader reader) {
        ULog.debug("Read repositories...");
        Node reps_node = reader.findNode(reader.getRootNode(), "repositories");
        if (reps_node != null) {

            for (Node rep : reader.getChildNodes(reps_node, "repository")) {
                String id = reader.getValue(reader.getChild(rep, "id"));
                String url = reader.getValue(reader.getChild(rep, "url"));
                String username = reader.getValue(reader.getChild(rep, "username"));
                String password = reader.getValue(reader.getChild(rep, "password"));

                if (url == null || url.isEmpty()) {
                    ULog.error("Failed to read repository: " + rep + " invalid url.");
                }

                if (username == null || username.isEmpty()) {
                    ULog.debug("No username found for repo inside Product.xml, analyzing settings.xml.");
                    username = checkRemoteRepositoryConfiguration(id, "name");
                }

                if (password == null || password.isEmpty()) {
                    ULog.debug("No username found for repo inside Product.xml, analyzing settings.xml.");
                    password = checkRemoteRepositoryConfiguration(id, "value");
                }

                ObservableRepository repos = new ObservableRepository(url);
                if (id != null && !id.isEmpty())
                    repos.setId(id);
                if (username != null && !username.isEmpty())
                    repos.setUsername(username);
                if (password != null && !password.isEmpty())
                    repos.setPassword(password);
                mRepositories.add(repos);
            }

        }
        ULog.debug("... repositories read");
    }

    /**
     * Analyzes a maven settings file for the given property name of the repository with the given id.
     * @param id The repository id
     * @param propertyName The property, which should be found for the repository
     * @param filePath The settings file path
     * @return Empty string if repository and property are not found.
     */
    private String analyzeSettingsFile(String id, String propertyName, String filePath) {
        File f = new File(filePath);
        if (f.exists()) {

            XMLReader settingsReader = new XMLReader();
            try {
                settingsReader.parseFile(f);
            } catch (IOException | ParserConfigurationException | SAXException e) {
                return "";
            }
            Node servers = settingsReader.findNode(settingsReader.getRootNode(), "servers");
            if (servers != null) {
                for (Node server : settingsReader.getChildNodes(servers, "server")) {
                    if (id.equals(settingsReader.getValue(settingsReader.getChild(server, "id")))) {
                        for (Node configuration : settingsReader.getChildNodes(server, "configuration")) {
                            for (Node httpHeaders : settingsReader.getChildNodes(configuration, "httpHeaders")) {
                                for (Node property : settingsReader.getChildNodes(httpHeaders, "property")) {
                                    return settingsReader.getValue(settingsReader.getChild(property, propertyName));
                                }
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    /**
     * Searches for the given property name for the given repository inside the users settings.xml file as well as
     * tries to found a settings.xml file inside the set local repository and the base directory of the Product.xml.
     * @param id The repository id
     * @param propertyName The property, which should be found for the repository
     * @return Empty string if repository and property are not found.
     */
    private String checkRemoteRepositoryConfiguration(String id, String propertyName) {
        // Check for local user settings.xml
        String defaultSettingsFile = System.getProperty("user.home") + File.separator + ".m2" + File.separator
                + "settings.xml";
        Path localRepositoryPath = Paths.get(mLocalRepository);
        Path parent;
        if (!localRepositoryPath.isAbsolute()) {
            Path relativeToSource = this.mProductFile.toPath().getParent().resolve(localRepositoryPath);
            parent = relativeToSource.getParent();
        } else {
            parent = localRepositoryPath.getParent();
        }
        // Check for settings.xml file inside the parent directory of the local repository path
        String localRepositorySettings = parent.toString() + File.separator + "settings.xml";
        // Check for settings.xml inside the parent directory of the Product.xml file
        String productFileDirectorySettings = this.mProductFile.toPath().getParent() + File.separator + "settings.xml";
        String value = analyzeSettingsFile(id, propertyName, localRepositorySettings);
        if (value.isEmpty()) {
            value = analyzeSettingsFile(id, propertyName, defaultSettingsFile);
        }
        if (value.isEmpty()) {
            value = analyzeSettingsFile(id, propertyName, productFileDirectorySettings);
        }
        return value;
    }

    /**
     * Reads all dependencies in a product definition file. Dependencies are defined like maven coordinates using
     * a group ID, artifact ID and a version. This populates the mDependencies variable. Currently, properties or
     * variables are not possible. Thus, dependencies needs to be fully defined.
     * @param reader product xml reader instance
     */
    private void readDependencies(XMLReader reader) {
        ULog.debug("read dependencies...");
        Node deps_node = reader.findNode(reader.getRootNode(), "dependencies");
        if (deps_node != null) {
            for (Node dep : reader.getChildNodes(deps_node, "dependency")) {
                String groupId = reader.getValue(reader.getChild(dep, "groupId"));
                String artifactId = reader.getValue(reader.getChild(dep, "artifactId"));
                String version = reader.getValue(reader.getChild(dep, "version"));
                String coordinate = groupId + DEPENDENCY_SEPARATOR + artifactId + DEPENDENCY_SEPARATOR + version;

                ObservableDependency dependency = new ObservableDependency();
                dependency.setGroupId(groupId);
                dependency.setArtifactId(artifactId);
                dependency.setVersion(version);

                ULog.trace("add dependency: " + coordinate);
                mDependencies.add(dependency);
            }
        }
        ULog.debug("... dependencies read");
    }

    /**
     * Reads workspace fields in a product definition file. Workspaces are absolute paths to maven poml files. This
     * populates the mWorkspaces member variable. Workspaces are only added if they exist. Non-existing workspaces
     * will be logged.
     * @param reader product xml reader instance
     */
    private void readWorkspaces(XMLReader reader) {
        ULog.debug("read workspaces...");
        Node workspaceNode = reader.findNode(reader.getRootNode(), "workspaces");
        if (workspaceNode != null) {
            for (Node workspaceDirectoryNode : reader.getAllNodes(workspaceNode, "workspace")) {
                String workspaceDirectory = reader.getValue(workspaceDirectoryNode);
                if (workspaceDirectory != null && workspaceDirectory.isEmpty() == false) {
                    File workspaceFile = new File(workspaceDirectory);
                    if (workspaceFile.exists()) {
                        ULog.debug("Add workspace directory: " + workspaceFile);
                        addWorkspace(workspaceFile);
                    } else {
                        ULog.warn("Workspace path " + workspaceFile + " was not found and cannot be used!");
                    }
                }
            }
        }
        ULog.debug("... workspaces read");
    }

    /**
     * Writes all information into the given product file (set via setFile).
     * @throws IOException exception thrown during io operations
     */
    public void write() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<project>\n\n\t<!-- This is NOT a MAVEN file it just uses a similar syntax -->\n\n\t");
        sb.append("<name>" + mName + "</name>\n\t");
        sb.append("<description>" + mDescription + "</description>\n\t");
        sb.append("<version>" + (mVersion != null ? mVersion : "1.0.0") + "</version>\n\t");
        sb.append("<localRepos>" + (mLocalRepository != null ? mLocalRepository : "") + "</localRepos>\n\n\t");

        sb.append("<workspaces>\n");
        for (File f : mWorkspaces) {
            sb.append("\t\t<workspace>" + f.getAbsolutePath() + "</workspace>\n");
        }
        sb.append("\t</workspaces>\n\n");

        sb.append("\t<dependencies>\n");
        for (ObservableDependency dep : getDependencies()) {
            sb.append("\t\t<dependency>\n");

            sb.append("\t\t\t<groupId>" + dep.getGroupId() + "</groupId>\n");
            sb.append("\t\t\t<artifactId>" + dep.getArtifactId() + "</artifactId>\n");
            sb.append("\t\t\t<version>" + dep.getVersion() + "</version>\n");
            sb.append("\t\t</dependency>\n");
        }
        sb.append("\t</dependencies>\n\n");

        sb.append("\t<repositories>\n");
        for (Repository rep : getRemoteRepositories()) {
            sb.append("\t\t<repository>\n");
            sb.append("\t\t\t<id>" + rep.getId() + "</id>\n");
            sb.append("\t\t\t<url>" + rep.getUrl() + "</url>\n");
            sb.append("\t\t\t<username>" + (rep.getUsername() != null ? rep.getUsername() : "") + "</username>\n");
            sb.append("\t\t\t<password>" + (rep.getPassword() != null ? rep.getPassword() : "") + "</password>\n");
            sb.append("\t\t</repository>\n");
        }
        sb.append("\t</repositories>\n\n</project>");

        if (mProductFile.exists() == true) {
            FileOperations.copy(mProductFile, new File(mProductFile.getAbsolutePath() + ".bak"), true);
        }
        FileWriter fw = new FileWriter(mProductFile);
        fw.write(sb.toString());
        fw.close();
    }

    public String getName() {
        return mName;
    }

    public void setName(final String nname) {
        mName = nname;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(final String desc) {
        mDescription = desc;
    }

    public Version getVersion() {
        Version version = null;

        try {
            version = Version.parse(mVersion);
        }catch (Exception e){
            ULog.error(e);
        }

        return version;
    }

    public void setVersion(String mVersion) {
        this.mVersion = mVersion;
    }

    public File getFile() {
        return mProductFile;
    }

    public void setFile(final File mFile) {
        this.mProductFile = mFile;
    }

    public String getLocalRepository() {
        return mLocalRepository;
    }

    public void setLocalRepository(final String path) {
        mLocalRepository = path;
        localRepositorySubject.onNext(Optional.ofNullable(path));
    }

    public Disposable subscribeLocalRepository(final Consumer<Optional<String>> c) {
        return localRepositorySubject.subscribe(c);
    }

    public List<File> getWorkspaces() {
        return Collections.unmodifiableList(mWorkspaces);
    }

    public Disposable subscribeWorkspaces(final Consumer<List<File>> c) {
        return workspacesSubject.subscribe(c);
    }

    /**
     * Adds a workspace to the workspaces list.
     * @param pomFile The pom containing the workspace to remove from the workspaces list
     */
    public void addWorkspace(File pomFile) {

        if (pomFile == null || !pomFile.exists()) {
            return;
        }

        // Check if exists
        for (File file : mWorkspaces) {
            try {
                if (Files.isSameFile(file.toPath(), pomFile.toPath())) {
                    return;
                }
            } catch (IOException e) {
                ULog.error(e);
            }
        }

        mWorkspaces.add(pomFile);
        workspacesSubject.onNext(mWorkspaces);
    }

    /**
     * Adds a workspace to the workspaces list at a specific index.
     * @param pomFile The pom containing the workspace to remove from the workspaces list
     * @param index index where the pom file should be added in the workspace list
     */
    public void addWorkspace(File pomFile, int index) {
        if (pomFile != null && pomFile.exists() && !mWorkspaces.contains(pomFile)) {
            mWorkspaces.add(index, pomFile);
            workspacesSubject.onNext(mWorkspaces);
        }
    }

    /**
     * Removes a workspace from workspaces list.
     * @param pomFile The pom containing the workspace to remove from the workspaces list
     * @return The index within the workspaces list before removing
     */
    public int removeWorkspace(final File pomFile) {
        File toRemove = null;

        int index = 0;

        for (File file : mWorkspaces) {

            try {
                if (Files.isSameFile(file.toPath(), pomFile.toPath())) {
                    toRemove = file;
                    break;
                }

            } catch (IOException e) {
                ULog.error(e);
            }
            index++;

        }

        if (toRemove != null) {
            mWorkspaces.remove(toRemove);
            workspacesSubject.onNext(mWorkspaces);
            return index;
        }

        return -1;
    }

    /**
     * Removes all workspaces from the workspace list.
     */
    public void clearWorkspaces() {
        mWorkspaces.clear();
        workspacesSubject.onNext(mWorkspaces);
    }

    public List<ObservableDependency> getDependencies() {
        return Collections.unmodifiableList(mDependencies);
    }

    public Disposable subscribeDependencies(Consumer<List<ObservableDependency>> c) {
        return dependencySubject.subscribe(c);
    }

    public void addDependency(final ObservableDependency dependency) {
        if (dependency != null && !mDependencies.contains(dependency)) {
            mDependencies.add(dependency);
            dependencySubject.onNext(mDependencies);
        }
    }

    public void addDependency(String coordinate) {
        ObservableDependency od = ObservableDependency.fromCoordinate(coordinate);
        addDependency(od);
    }

    public void addDependency(ObservableDependency dependency, int index) {
        mDependencies.add(index, dependency);
        dependencySubject.onNext(mDependencies);
    }

    public int removeDependency(ObservableDependency dependency) {
        int index = mDependencies.indexOf(dependency);

        mDependencies.remove(dependency);
        dependencySubject.onNext(mDependencies);

        return index;
    }

    public void clearDependencies() {
        mDependencies.clear();
    }

    public List<ObservableRepository> getRemoteRepositories() {
        return Collections.unmodifiableList(mRepositories);
    }

    public Disposable subscribeRepositories(Consumer<List<ObservableRepository>> c) {
        return repositoriesSubject.subscribe(c);
    }

    public void addRepository(ObservableRepository rep) {
        if (rep != null) {
            mRepositories.add(rep);
            repositoriesSubject.onNext(mRepositories);
        }
    }

    public void addRepository(ObservableRepository repository, int index) {
        mRepositories.add(index, repository);
        repositoriesSubject.onNext(mRepositories);
    }

    public int removeRepository(Repository r) {

        int index = mRepositories.indexOf(r);
        mRepositories.remove(r);
        repositoriesSubject.onNext(mRepositories);

        return index;
    }

    /**
     * Clears all repository configuration from the product file.
     */
    public void clearRepositories() {
        mRepositories.clear();
        repositoriesSubject.onNext(mRepositories);
    }

    /**
     * Clears authentication information from the repository section of the product file.
     */
    public void clearCredentials() {
        for(ObservableRepository repository : mRepositories) {
            repository.setUsername("");
            repository.setPassword("");
        }
        repositoriesSubject.onNext(mRepositories);
    }

}
