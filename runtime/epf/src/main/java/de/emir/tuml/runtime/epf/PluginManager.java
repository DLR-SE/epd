package de.emir.tuml.runtime.epf;

import de.emir.tuml.runtime.epf.descriptors.ClassPathDescriptor;
import de.emir.tuml.runtime.epf.descriptors.PluginDescriptor;
import de.emir.tuml.runtime.epf.provider.IClasspathDescriptorProvider;
import de.emir.tuml.runtime.epf.provider.IClasspathEntryProvider;
import de.emir.tuml.runtime.epf.provider.impl.RootApplicationClassLoader;
import de.emir.tuml.runtime.epf.utils.MavenUtil;
import de.emir.tuml.runtime.epf.utils.ProviderUtil;
import de.emir.tuml.runtime.epf.utils.WorkspaceResolver;
import de.emir.tuml.ucore.runtime.UCoreExtensionManager;
import de.emir.tuml.ucore.runtime.UCorePlugin;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;
import de.emir.tuml.ucore.runtime.progress.NullProgressMonitor;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import org.apache.maven.model.Model;
import org.eclipse.aether.artifact.Artifact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class PluginManager {

    private static final Logger LOG = LoggerFactory.getLogger(PluginManager.class);

    private boolean mOfflineMode = true;

    private MavenUtil mMavenUtil = null;
    private List<IClasspathDescriptorProvider> mDescriptorProvider = null;
    private List<IClasspathEntryProvider> mEntryProvider = null;

    private Map<String, ClassPathDescriptor<?>> mDescriptors = new HashMap<>();
    private Map<String, CoordinateElement<?>> mUnresolvedCoordinates = new HashMap<>();
    private Map<String, ClasspathEntry<?>> mEntries = new HashMap<>();
    private Map<String, ClasspathEntry<?>> mPluginEntries = new HashMap<>();

    private Map<String, UCorePlugin> mPlugins = new HashMap<>();

    private IProgressMonitor monitor;

    private RootApplicationClassLoader rootApplicationClassLoader;

    private ProductFile mProductFile;

    private PublishSubject<ClasspathEntry<?>> mEntrySubject = PublishSubject.create();
    private PublishSubject<Model> mWorkspaceProjectSubject = PublishSubject.create();

    public PluginManager(IProgressMonitor monitor, ProductFile pf, boolean offline) {
        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }

        this.monitor = monitor;

        LOG.debug("initialize Classpath Descriptor Provider");
        mDescriptorProvider = ProviderUtil.getDescriptorProvider();
        LOG.debug("initialize Classpath Entry Provider");
        mEntryProvider = ProviderUtil.getEntryProvider();

        mOfflineMode = offline;
        mMavenUtil = new MavenUtil(pf.getLocalRepository(), pf.getRemoteRepositories(), offline);
        mProductFile = pf;

        UCoreExtensionManager.registerExtension(this); // allow global access to this manager
    }

    /**
     * Subscribe to classpath entries being loaded.
     * 
     * @param c
     * @return
     */
    public Disposable subscribeClasspathEntry(Consumer<ClasspathEntry<?>> c) {
        return mEntrySubject.subscribe(c);
    }

    /**
     * Subscribe to workspace projects being found
     * 
     * @param c
     * @return
     */
    public Disposable subscribeWorkspaceProjects(Consumer<Model> c) {
        return mWorkspaceProjectSubject.subscribe(c);
    }

    public ClasspathEntry<?> getEntry(String coordinate) {
        if (coordinate.contains("]")) {
            ULog.debug(coordinate);
        }
        if (mEntries.containsKey(coordinate)) {
            return mEntries.get(coordinate);
        } else {
            int i = coordinate.lastIndexOf(':');
            String start = null;
            if (i > 0) { // try with other version
                start = coordinate.substring(0, i);
                for (String c : mEntries.keySet()) {
//                    if (c.startsWith(start)) {
//                        ULog.error("Could not find exact Dependency: " + coordinate + " replace with: " + c);
//                        return mEntries.get(c);
//                    }
                    if (c.startsWith(start)) {
                        int j = c.lastIndexOf(":");
                        if (j > 0 && c.substring(0, j).equals(start)) {
                            ULog.warn("Could not find exact Dependency: " + coordinate + " replace with other version: " + c);
                            return mEntries.get(c);
                        }
                    }
                }
            }
            // if we reach this point, try just the artifact name
            if (start != null) {
                start = start.split(":")[1];
                for (String c : mEntries.keySet()) {
                    if (c.split(":")[1].equals(start)) {
                        ULog.warn("Could not find exact Dependency: " + coordinate + " replace with similar name: " + c);
                        return mEntries.get(c);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Returns an unmodifiable list of all loaded entries.
     * 
     * @note entries differ from descriptors, that they have been loaded and have a classloader associated with them
     * @return unmodifiable list of classpath entries
     */
    public Collection<ClasspathEntry<?>> getEntries() {
        return Collections.unmodifiableCollection(mEntries.values());
    }

    /** Returns all loaded classpath descriptors (unmodifiable). */
    public Collection<ClassPathDescriptor<?>> getDescriptors() {
        return Collections.unmodifiableCollection(mDescriptors.values());
    }

    /** Returns an (unmodifiable) (unsorted) list of unresolved dependencies. */
    public Collection<String> getUnresolvedDependencies() {
        return Collections.unmodifiableCollection(mUnresolvedCoordinates.keySet());
    }

    public void registerRootApplication(File pomFile, String identity, final ClassLoader _classLoader) {
        monitor.setProgress(5);
        monitor.setMessage("Loading Root Application");
        ULog.info("register root application...", 1);

        rootApplicationClassLoader = new RootApplicationClassLoader(_classLoader);

        ClassPathDescriptor<?> rootAppDecriptor = null;
        if (pomFile != null && pomFile.exists() && isPom(pomFile)) {
            try {
                rootAppDecriptor = loadDescriptor(pomFile.toURI().toURL());
            } catch (MalformedURLException e) {
                ULog.error(e);
            }
        } else {
            loadDescriptor(identity);
        }
        loadUnresolvedDescriptors();
        build();

        if (rootAppDecriptor == null) {
            return;
        }

        ClasspathEntry<?> rootAppEntry = getEntry(rootAppDecriptor.getCoordinate());
        rootAppEntry._setClassloader(rootApplicationClassLoader);

        // Set the main threads context class loader
        MultiContextClassLoader mccl = new MultiContextClassLoader(rootApplicationClassLoader, this);
        Thread.currentThread().setContextClassLoader(mccl);
        UCoreExtensionManager.registerExtension("MultiContextClassLoader", mccl);

        for (Object ce : rootAppEntry.getAllDependencies()) {
            // those entries have already been loaded from the applications
            // classloader, so we will use this one instead of our own
            ULog.debug("adjust classpath for: " + ((ClasspathEntry<?>) ce).getCoordinate());
            ((ClasspathEntry<?>) ce)._setClassloader(rootApplicationClassLoader);
        }

        ULog.info(-1, "... root application registered");
        monitor.setProgress(20);
    }

    /**
     * uses a configuration file to configure the maven resolver and load all the required plugins in order: -
     * workspaces - dependencies.
     * 
     * @note this method overwrites the maven settings if already been initialized
     */
    public void load() {
        ULog.info("Loading ProductFile: " + mProductFile.getName(), 1);
        ULog.debug("Configure Maven");

        ULog.debug("Loading workspaces...", 1);
        for (File file : mProductFile.getWorkspaces()) {

            if (file != null && file.exists()) {
                loadWorkspace(file);
            }
        }
        ULog.debug(-1, "...workspaces loaded");
        ULog.debug("Loading dependencies", 1);
        for (ObservableDependency dep : mProductFile.getDependencies()) {
            loadDescriptor(dep.getCoordinate());
        }
        ULog.debug(-1, "...dependencies loaded");
        ULog.debug("Loaded: " + mDescriptors.size() + " descriptors got: " + mUnresolvedCoordinates.size()
                + " unresolved dependencies");

        loadUnresolvedDescriptors();

        ULog.info(-1, "... PluginFile: " + mProductFile.getName() + " loaded");
        ULog.debug("Loaded: " + mDescriptors.size() + " descriptors got: " + mUnresolvedCoordinates.size()
                + " unresolved dependencies");
//        ULog.info("Loaded:", 1);
//
//        for (ClassPathDescriptor<?> cpd : mDescriptors.values()) {
//            ULog.info(cpd.getCoordinate());
//        }
//
//        ULog.setIntendation(0);
    }

    /**
     * Loads a maven coordinate with format: groupId:artifactId:version.
     * 
     * @note this method does not resolve dependencies, this has to be done manually using the @code
     *       resolveDependencies() method
     * @param coordinate
     * @return
     */
    public ClassPathDescriptor<?> loadDescriptor(String coordinate) {
        ULog.debug("loading Coordinate: " + coordinate);

        ClassPathDescriptor<?> desc = mDescriptors.get(coordinate);

        if (desc != null) {
            return desc;
        }

        Artifact artifact = mMavenUtil.resolveArtifact(coordinate);

        if (artifact == null || artifact.getFile() == null) {
            ULog.warn("Could not resolve Coordinate: " + coordinate);
            return null;
        }
        if (artifact.getFile().exists() == false) {
            ULog.warn("Artifact File: " + artifact + " does not exists");
            return null;
        }
        File file = artifact.getFile();
        if (isJar(file) == true) {
            // try to simply replace it with the pom file (as usual for maven
            // repositories)
            String str = file.getAbsolutePath();
            File f = new File(str.substring(0, str.length() - 3) + "pom");
            if (f.exists()) {
                file = f;
            }
        }
        try {
            return loadDescriptor(file.toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * loads a directory in "source"-mode search for a pom file in the given directory and loads all of its modules.
     * 
     * @note this method is meant to load source / working directories, e.g. eclipse / netbeans /... projects, where an
     *       IDE is used to compile the classes into the folder "target/classes"
     * @param file
     *            pom file or directory containing a pom file
     */
    public void loadWorkspace(final File file) {
        if (file == null || file.exists() == false) {
            ULog.warn("Can not load Workspace: " + file + " -- Invalid location");
            return;
        }
        // at this point, we do not really use the model but are only interested
        // in the file (model.getPomFile) thus the model will be read in again,
        // however we gain a small / clean interface by just using the load(URL
        // method)
        Collection<Model> models = new WorkspaceResolver(mMavenUtil).resolveWorkspaces(Arrays.asList(file));
        if (models == null || models.isEmpty()) {
            ULog.warn("Failed to load workspace: " + file + " is it empty?");
            return;
        }
        ULog.debug("Detected : " + models.size() + " projects in Workspace: " + file);
        for (Model m : models) {
            if (m == null) {
                continue;
            }

            if (m.getPomFile() == null || m.getPomFile().exists() == false) {
                ULog.warn("Failed to resolve file for Model: " + m);
                continue;
            }
            try {

                String coordinate = MavenUtil.getCoordinate(m);

                mWorkspaceProjectSubject.onNext(m);

                ClassPathDescriptor<?> desc = mDescriptors.get(coordinate);

                if (desc == null) {
                    loadDescriptor(m.getPomFile().toURI().toURL());
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
                ULog.error("Failed to load Entry: " + e.getMessage());
            }
        }
    }

    /**
     * Utility method for loading more than one url redirects each url to @code loadEntry(URL)
     * 
     * @param urls
     * @return
     */
    public List<ClassPathDescriptor<?>> loadDescriptor(URL... urls) {
        ArrayList<ClassPathDescriptor<?>> out = new ArrayList<>();
        for (URL url : urls) {
            ClassPathDescriptor<?> entry = loadDescriptor(url);
            if (entry != null) {
                out.add(entry);
            }
        }
        return out;
    }

    /**
     * Loads an class path descriptor using its URL. The URL has to point either to the jar file or to an pom file if
     * the url points to an jar file, the method tries to resolve the corresponding pom file by replacing ".jar" through
     * ".pom" (as usual in maven repos) if the pom file does not exists the method fails with a warning
     * 
     * @note this method does not resolve dependencies, this has to be done manually using the @code
     *       resolveDependencies() method
     * @param url
     *            or jar file to load
     * @return
     */
    public ClassPathDescriptor<?> loadDescriptor(URL url) {

        // use the first provider that provides a valid descriptor
        ClassPathDescriptor<?> desc = null;
        for (IClasspathDescriptorProvider provider : mDescriptorProvider) {
            try {
                desc = provider.provide(url, mMavenUtil, this);
                if (desc != null) {
                    break;
                }
            } catch (Exception e) {

                LOG.error("Failed to find ClassPathDescriptor for: " + url + " ERROR: " + e.getMessage());

            }
        }
        if (desc == null) {
            LOG.warn("Could not find a ClassPathDescriptor for URL: " + url);
            return null;
        }

        String coordinate = desc.getCoordinate();

        // check if we already have a descriptor with the same Coordinate, in
        // this case use the old one and ignore this one but not without a
        // warning
        ClassPathDescriptor<?> oldDesc = mDescriptors.get(coordinate);

        if (oldDesc != null) {
            ULog.warn("An Descriptor with Coordinate: " + coordinate
                    + " has already been loaded, skipping new descriptor");
            return oldDesc;
        }

        ULog.debug("Loaded Descriptor: " + coordinate);

        mDescriptors.put(coordinate, desc);

        // go through all dependencies and check if we do have the dependency
        // (using strict mode) if we got it, we can remove it from the
        // unresolved list
        // @note in a later step (before loading) this may be done again with
        // non-strict mode, e.g. ignoring the version
        StringBuilder depsMsg = new StringBuilder("Adding Dependencies of: " + coordinate + " = [");
        for (CoordinateElement<?> ce : desc.getDependencies()) {

            String depCoordinate = ce.getCoordinate();
            depsMsg.append(depCoordinate).append(", ");
            if (mUnresolvedCoordinates.containsKey(depCoordinate) == false) {

                mUnresolvedCoordinates.put(ce.getCoordinate(), ce);
            }
        }
        ULog.debug(depsMsg + "]");

        mUnresolvedCoordinates.remove(desc.getCoordinate());

        return desc;
    }

    /**
     * Tries to resolve all unresolved dependencies using maven aether Using the Maven Ather to resolve all remaining
     * dependencies by calling loadCoordinate(dep.getCoordinate())
     * 
     * @note this method also resolves dependencies of dependencies
     * @warn after running this method there may still some dependencies are unresolved, those could not be found locally
     *       or (if offline is disabled) in one of the remote repositories
     */
    public void loadUnresolvedDescriptors() {
        monitor.setProgress(25);
        monitor.setMessage("Resolving Dependencies");

        ULog.info("Resolving Dependencies...", 1);
        HashSet<String> closedList = new HashSet<>(); // list of coordinates we already tried to resolve; for the case
                                                      // we are not able to resolve one of the deps
        while (mUnresolvedCoordinates.isEmpty() == false) {
            String coord = mUnresolvedCoordinates.keySet().iterator().next();
            monitor.setMessage("loading Dependency: " + coord);

            // remove from list to not resolve it again
            CoordinateElement<?> entry = mUnresolvedCoordinates.remove(coord);
            if (closedList.contains(coord)) {
                // we already tried to resolve it (may did not work or
                // is a dependency of more than one plugin)
                continue;
            }

            closedList.add(coord);

            if (entry.getOption("classifier") != null) {

                coord = entry.getName() + ":jar:" + entry.getOption("classifier") + ":" + entry.getVersion();
                // coord += "-" + entry.getOption("classifier");
            }
            loadDescriptor(coord);// do the actual loading; this may add additional unresolved dependencies

            float s = mUnresolvedCoordinates.size();
            if (s > 100) {
                s = 100;
            }
            monitor.setProgress((int) (25 + s / 2.0f));
        }
        ULog.info(-1, "... dependencies resolved");
    }

    public Collection<ClasspathEntry<?>> build() {
        monitor.setProgress(75);
        monitor.setMessage("Building entries");
        ULog.info("build ClasspathEntries", 1);
        for (ClassPathDescriptor<?> descriptor : mDescriptors.values()) {
            loadEntry(descriptor);
        }
        monitor.setProgress(80);
        ULog.info(-1, "... ClasspathEntries build");
        return null;
    }

    /**
     * Builds an ClasspathEntry out of an ClasspathDescription this includes the creation of an ClassLoader
     * 
     * @note this method does not resolve the dependencies at this point, this is done by the ClasspathEntry on first
     *       request
     * @param descriptor
     */
    public void loadEntry(ClassPathDescriptor<?> descriptor) {
        if (descriptor == null) {
            return;
        }

        if (getEntry(descriptor.getCoordinate()) != null) {
            return; // has already been loaded, for example during initialisation
                    // of the root application
        }
        ULog.debug("Build ClasspathEntry for: " + descriptor);
        // try all entry provider if they are able to provide us a classpath
        // entry - first comes, first serves
        ClasspathEntry<?> entry = null;
        for (IClasspathEntryProvider provider : mEntryProvider) {
            try {
                entry = provider.provide(this, descriptor);
                if (entry != null) {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (entry == null) {
            ULog.warn("Failed to create ClassPathEntry for: " + descriptor);
            return;
        }

        ULog.debug("register Entry: " + entry.getCoordinate());
        monitor.setMessage(entry.getCoordinate());
        mEntries.put(entry.getCoordinate(), entry);
        mEntrySubject.onNext(entry);

        if (entry.getDescriptor() instanceof PluginDescriptor) {
            ULog.debug("register Plugin Entry: " + entry.getCoordinate());
            mPluginEntries.put(entry.getCoordinate(), entry);
        }
        // register the ClasspathEntry in the UCoreExtensionManager
        UCoreExtensionManager.registerExtension(entry);
    }

    public void startPlugins() {

        monitor.setProgress(80);
        monitor.setMessage("Starting Plugins");

        int n = mPluginEntries.size();
        float f = 20.0f / n;
        int idx = 0;
        ULog.info("Start all plugins...", 1);
        for (String coord : mPluginEntries.keySet()) {

            monitor.setProgress((int) (80 + idx++ * f));
            monitor.setMessage("Starting plugin: " + coord);
            startPlugin(coord);
        }

        ULog.info(-1, "... all plugins started");
        monitor.setProgress(100);
        monitor.setMessage("Done...");
    }

    private void startPlugin(String coord) {
        ClasspathEntry<?> entry = getEntry(coord);
        if (entry == null) {
            ULog.error("Could not find the Entry: " + coord);
            return;
        }
        if (entry.isStarted()) {
            ULog.trace("Plugin: " + coord + " already started");
            return; // nothing to do anymore
        }
        ULog.debug("start plugin: " + coord);
        if (entry.getPluginDependencies().isEmpty() == false) {
            ULog.debug("start plugin dependencies...", 1);
            // first start all of its dependencies (if they are plugins as well)
            for (Object dep_obj : entry.getPluginDependencies()) {
                ClasspathEntry<?> dep = (ClasspathEntry<?>) dep_obj; // don't know why
                // java does not
                // accept this
                // in the loop
                // variable
                if (!dep.isStarted()) {
                    startPlugin(dep.getCoordinate());
                }
            }
            ULog.debug(-1, "... plugin dependencies started");
        }

        try { // do the actual starting by calling the pluginClassName class as
              // UCorePlugin
            String pluginClassName = entry.getPluginClass();
            ULog.debug("Loading class: " + pluginClassName);
            Class<?> pluginClass = entry.getClassLoader().loadClass(pluginClassName);
            UCorePlugin plugin = (UCorePlugin) pluginClass.getDeclaredConstructor().newInstance();// no check, so we get an exception
                                                                         // and do the logging
            plugin.initializePlugin();
            entry._markStarted(true); // mark at the end, this way we may try
                                      // again, if it fails, but maybe it works
                                      // the next time (internal dep stuff?!)
                                      // - if this gets annoying move this line
                                      // to the start of this try-catch block

            mPlugins.put(coord, plugin);

        } catch (Exception | Error e) {
            e.printStackTrace();
            ULog.error("Failed to start Plugin : " + entry + " Error: " + e.getMessage());
        }
    }

    public ClassLoader getRootClassLoader() {
        return getClass().getClassLoader();
    }

    public boolean isOffline() {
        return mOfflineMode;
    }

    public void setOffline(boolean b) {
        if (mMavenUtil != null) {
            mMavenUtil.setOfflineMode(b);
        }
        mOfflineMode = b;
    }

    public Map<String, UCorePlugin> getPlugins() {
        return mPlugins;
    }

    public String getProgressMessage() {
        return monitor.getMessage();
    }

    public float getProgress() {
        return monitor.getProgress();
    }

    private boolean isPom(File file) {
        String name = file.getName();
        return name.endsWith("pom.xml");
    }

    private boolean isJar(File file) {
        String name = file.getName();
        return name.endsWith(".jar");
    }

    public RootApplicationClassLoader getRootApplicationClassLoader() {
        return rootApplicationClassLoader;
    }

    public MavenUtil getMavenUtil() {
        return mMavenUtil;
    }

    public CoordinateElement createCoordinate(String depId, String depV) {
        for (ClassPathDescriptor<?> desc : getDescriptors()) {
            if (desc.getName().equals(depId)) {
                ULog.debug("" + depId + ": " + depV + " ====?====" + desc.getCoordinate());
                if (depV != null && desc.getVersion() != null) {
                    if (depV.compareTo(desc.getVersion().toString()) <= 0) {
                        return desc;
                    }
                }
            }
        }
        return new CoordinateElement<>(depId, null, depV);
    }

	

}
