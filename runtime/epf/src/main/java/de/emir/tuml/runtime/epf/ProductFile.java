package de.emir.tuml.runtime.epf;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.ParserConfigurationException;

import com.github.zafarkhaja.semver.Version;
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
 * Reads in a Product file.
 *
 * @author sschweigert
 */
public class ProductFile {
    /** The constant dependency separator. */
    public static final String DEPENDENCY_SEPARATOR = ":";

    /** The default porduct definition file name. */
    public static final String PRODUCT_DEFINITION = "Product.xml";

    private String 												mName;
    private String 												mDescription;
    private String                                              mVersion;

    /**
     * Workspaces are directories, containing an pom file, usually an source directory, with compiled sources (e.g.
     * target directory) The pluginloader will resolve all artefacts of the workspace and read the related target files
     * as plugins
     */
    private ArrayList<File> 									mWorkspaces = new ArrayList<>();
    private ArrayList<ObservableDependency> 					mDependencies = new ArrayList<>();
    private ArrayList<ObservableRepository> 					mRepositories = new ArrayList<>();
    private String 												mLocalRepository;
    private File 												mFile;
    private String 												mIdentity;

    private PublishSubject<List<File>> 							workspacesSubject = PublishSubject.create();
    private PublishSubject<List<ObservableRepository>> 			repositoriesSubject = PublishSubject.create();
    private PublishSubject<List<ObservableDependency>> 			dependencySubject = PublishSubject.create();

    private PublishSubject<Optional<String>> 					localRepositorySubject = PublishSubject.create();

    public ProductFile(final File file) throws SAXException, IOException, ParserConfigurationException {
        read(file);
    }

    /**
     * @param file
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public void read(final File file) throws SAXException, IOException, ParserConfigurationException {
        mFile = file;
        XMLReader reader = new XMLReader();
        reader.parseFile(file);

        mName = reader.getValue(reader.findNode(reader.getDocument().getDocumentElement(), "name"));
        mDescription = reader.getValue(reader.findNode(reader.getDocument().getDocumentElement(), "description"));
        mVersion = reader.getValue(reader.findNode(reader.getDocument().getDocumentElement(), "version"));
        String localRepos = reader.getValue(reader.findNode(reader.getRootNode(), "localRepos"));
        String defaultRepo = System.getProperty("user.home") + File.separator + ".m2" + File.separator + "repository";
        if (localRepos != null && localRepos.isEmpty() == false) {
            File f = new File(localRepos);
            if (f.getParentFile() == null) {
            	f = new File(mFile.getParentFile(), localRepos);
            }
            if (f.exists() == false) {
                if (f.getParentFile().exists()) {
                    ULog.trace("Create Local Repository: " + f.getAbsolutePath());
                    f.mkdirs();
                } else {
                    ULog.error("Local Repository points to an invalid directory use standard directory instead: "
                            + defaultRepo);
                    f = null;
                }
            }
            if (f != null)
                defaultRepo = localRepos;
        }
        mLocalRepository = defaultRepo;

        readIdentity(reader);
        readWorkspaces(reader);
        readDependencies(reader);
        readRepositories(reader);
    }

    public Disposable subscribeLocalRepository(final Consumer<Optional<String>> c) {
        return localRepositorySubject.subscribe(c);
    }

    /**
     * @param reader
     */
    private void readIdentity(final XMLReader reader) {
        ULog.debug("read identity...", 1);
        Node doc = reader.getRootNode();
        String groupId = reader.getValue(reader.getChild(doc, "groupId", false));
        String version = reader.getValue(reader.getChild(doc, "version", false));
        String artifactId = reader.getValue(reader.getChild(doc, "artifactId", false));
        mIdentity = groupId + DEPENDENCY_SEPARATOR + artifactId + DEPENDENCY_SEPARATOR + version;
    }

    private void readRepositories(XMLReader reader) {
        ULog.debug("read repositories...", 1);
        Node reps_node = reader.findNode(reader.getRootNode(), "repositories");
        if (reps_node != null) {

            for (Node rep : reader.getChildNodes(reps_node, "repository")) {
                String id = reader.getValue(reader.getChild(rep, "id"));
                String url = reader.getValue(reader.getChild(rep, "url"));
                String username = reader.getValue(reader.getChild(rep, "username"));
                String password = reader.getValue(reader.getChild(rep, "password"));

                if (url == null || url.isEmpty()) {
                    ULog.error("Failed to read repository: " + rep + " invalid url");
                }

                ObservableRepository repos = new ObservableRepository(url);
                if (id != null && id.isEmpty() == false)
                    repos.setId(id);
                if (username != null && username.isEmpty() == false)
                    repos.setUsername(username);
                if (password != null && password.isEmpty() == false)
                    repos.setPassword(password);
                mRepositories.add(repos);
            }

        }
        ULog.debug(-1, "... repositories read");
    }

    private void readDependencies(XMLReader reader) {
        ULog.debug("read dependencies...", 1);
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

                ULog.trace("add Dependency: " + coordinate);
                mDependencies.add(dependency);
            }
        }
        ULog.debug(-1, "... dependencies read");
    }

    private void readWorkspaces(XMLReader reader) {
        ULog.debug("read workspaces...", 1);
        Node ws_node = reader.findNode(reader.getRootNode(), "workspaces");
        if (ws_node != null) {
            for (Node wsdir : reader.getAllNodes(ws_node, "workspace")) {
                String dir = reader.getValue(wsdir);
                if (dir != null && dir.isEmpty() == false) {
                    File wsFile = new File(dir);
                    if (wsFile.exists()) {
                        ULog.debug("Add Workspace directory: " + wsFile);
                        addWorkspace(wsFile);
                    }
                }
            }
        }
        ULog.debug(-1, "... workspaces read");
    }

    public String getLocalRepository() {
        return mLocalRepository;
    }

    public List<ObservableRepository> getRemoteRepositories() {
        return Collections.unmodifiableList(mRepositories);
    }

    public List<File> getWorkspaces() {
        return Collections.unmodifiableList(mWorkspaces);
    }

    public List<ObservableDependency> getDependencies() {
        return Collections.unmodifiableList(mDependencies);
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public Version getVersion() {
        Version version = null;

        try {
            version = Version.valueOf(mVersion);
        }catch (Exception e){
            e.printStackTrace();
        }

        return version;
    }

    public File getFile() {
        return mFile;
    }

    /**
     * @param pomFile
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
                e.printStackTrace();
            }

        }

        mWorkspaces.add(pomFile);
        workspacesSubject.onNext(mWorkspaces);
    }

    public void clearWorkspaces() {
        mWorkspaces.clear();
        workspacesSubject.onNext(mWorkspaces);
    }

    public void addWorkspace(File pomFile, int index) {
        if (pomFile != null && pomFile.exists() && !mWorkspaces.contains(pomFile)) {
            mWorkspaces.add(index, pomFile);
            workspacesSubject.onNext(mWorkspaces);
        }
    }

    /**
     * Removes a workspace from workspaces list.
     *
     * @param pomFile
     *            The pom containing the workspace to remove from the workspaces list
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
                e.printStackTrace();
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

    public Disposable subscribeWorkspaces(final Consumer<List<File>> c) {
        return workspacesSubject.subscribe(c);
    }

    public void clearDependencies() {
        mDependencies.clear();
    }

    public void addDependency(final ObservableDependency dependency) {
        if (dependency != null && !mDependencies.contains(dependency)) {
            mDependencies.add(dependency);
            dependencySubject.onNext(mDependencies);
        }
    }

    public void addRepository(ObservableRepository rep) {
        if (rep != null) {
            mRepositories.add(rep);
            repositoriesSubject.onNext(mRepositories);
        }
    }

    public void clearRepositories() {
        mRepositories.clear();
        repositoriesSubject.onNext(mRepositories);
    }

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

        if (mFile.exists() == true) {
            FileOperations.copy(mFile, new File(mFile.getAbsolutePath() + ".bak"), true);
        }
        FileWriter fw = new FileWriter(mFile);
        fw.write(sb.toString());
        fw.close();
    }

    public void setName(final String nname) {
        mName = nname;
    }

    public void setDescription(final String desc) {
        mDescription = desc;
    }

    public void setVersion(String mVersion) {
        this.mVersion = mVersion;
    }

    public void setLocalRepository(final String path) {
        mLocalRepository = path;
        localRepositorySubject.onNext(Optional.ofNullable(path));
    }

    public String getIdentity() {
        return mIdentity;
    }

    public int removeRepository(Repository r) {

        int index = mRepositories.indexOf(r);
        mRepositories.remove(r);
        repositoriesSubject.onNext(mRepositories);

        return index;
    }

    public Disposable subscribeRepositories(Consumer<List<ObservableRepository>> c) {
        return repositoriesSubject.subscribe(c);
    }

    public Disposable subscribeDependencies(Consumer<List<ObservableDependency>> c) {
        return dependencySubject.subscribe(c);
    }

    public void addRepository(ObservableRepository repository, int index) {
        mRepositories.add(index, repository);
        repositoriesSubject.onNext(mRepositories);

    }

    public int removeDependency(ObservableDependency dependency) {

        int index = mDependencies.indexOf(dependency);

        mDependencies.remove(dependency);
        dependencySubject.onNext(mDependencies);

        return index;

    }

    public void addDependency(String coordinate) {

        ObservableDependency od = ObservableDependency.fromCoordinate(coordinate);
        addDependency(od);

    }

    public void addDependency(ObservableDependency dependency, int index) {
        mDependencies.add(index, dependency);
        dependencySubject.onNext(mDependencies);
    }

    public void setFile(final File mFile) {
        this.mFile = mFile;
    }

}