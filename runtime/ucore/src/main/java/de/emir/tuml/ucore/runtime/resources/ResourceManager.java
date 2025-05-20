
package de.emir.tuml.ucore.runtime.resources;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

import com.google.common.base.Predicate;
import com.google.common.io.Resources;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import com.google.common.reflect.ClassPath.ResourceInfo;

import de.emir.tuml.ucore.runtime.UCoreExtensionManager;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.FileOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class used to fetch resource URL's and image icons (more to come).
 * <p>
 * Examples:
 * 
 * <pre>
 * Resources res = Resources.get(RCP.class, "images/vesselIcons");
 * </pre>
 */
public final class ResourceManager {

    private static final Logger LOG = LogManager.getLogger(ResourceManager.class, null);

    private static Map<CacheKey, Object> cache = new ConcurrentHashMap<>();

    /**
     * Name of the current application. This name is (by default) used to determine the home path within the user
     * directory.
     */
    public static String applicationName = ".SwingRCP";

    Class<?> loaderClass;
    String folder;

    /**
     * Constructor
     * 
     * @param loaderClass
     *            class that defines the class-loader/jar-file to load from
     * @param folder
     *            the folder prefix
     */
    private ResourceManager(Class<?> loaderClass, String folder) {
        from(loaderClass).folder(folder);
    }

    /***********************************/
    /********* Factory methods *********/
    /***********************************/

    /**
     * Returns a new instance of the {@code Resources} entity with the given folder prefix.
     * <p>
     * The resources are loaded from the same class-path as the {@code Resources} class
     * 
     * @param folder
     *            the folder prefix
     * @return a new {@code Resources} entity
     */
    public static ResourceManager get(String folder) {
        return new ResourceManager(null, folder);
    }

    private static Path mHome = null;

    /**
     * Tries to parse the home path from the arguments list. Expects some kind of prefix (e.g. --home <path_to_home>)
     * 
     * @param args
     *            program arguments as in public static void main(String[] args)
     * @param prefix
     *            prefix after which the path shall be expected
     * @return true if the prefix could be found and the home directy could be set, false otherwise
     */
    public boolean parseHomePath(String[] args, String prefix) {
        if (args == null || args.length < 2 || prefix == null || prefix.isEmpty())
            return false; // invalid arguments
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals(prefix)) {
                File f = new File(args[i + 1]);
                return setHomePath(f);
            }
        }
        return false;
    }

    /**
     * Returns the Applications home path if the path has not been set (using setHomePath(..) or parseHomePath(...) the
     * methods tries to assign System.getProperty("user.home") + <member>applicationName<member> as home path. If that
     * also fails the local directory is used as home path
     * 
     * @return
     */
    public Path getHomePath() {
        if (mHome == null) {
            String p = System.getProperty("user.home") + File.separator + applicationName;
            File f = new File(p);
            if (!setHomePath(f)) {
                LOG.error("Failed to set home path, using local path");
                mHome = new File(applicationName).toPath();// as backup use the local version
            }
        }
        return mHome;
    }

    /**
     * Sets the home path for this application. If the path does not yet exists, the directory is created
     * 
     * @param f
     * @return
     */
    public boolean setHomePath(File f) {
        f = f.getAbsoluteFile();
        if (!f.exists())
            try {
                f.mkdirs();
            } catch (Exception e) {
            }
        if (f.exists())
            mHome = f.toPath();
        return mHome != null && mHome.toFile().exists();
    }

    public Path unpackFileToHome(String resourceName) throws IOException {
        Path dest = getHomePath().resolve(resourceName);
        if (!Files.exists(dest)) {
            URL url = resolveResource(resourceName);
            if (url == null)
                throw new IOException("Could not find Resource: " + resourceName);
            File parent = dest.getParent().toFile();
            if (!parent.exists())
                try {
                    parent.mkdirs();
                } catch (Exception e) {
                }
            Resources.copy(url, Files.newOutputStream(dest));
        }
        return dest;
    }

    public void unpackDirectoryToHome(String resourceName) throws IOException {
        Path dest = getHomePath().resolve(resourceName);
        if (!Files.exists(dest)) {
            URL url = resolveResource(resourceName);
            if (url == null)
                throw new IOException("Could not find Resource: " + resourceName);
            File parent = dest.toFile();
            if (!parent.exists())
                try {
                    parent.mkdirs();
                } catch (Exception e) {
                }
            try {
                FileOperations.copyDir(new File(url.toURI()), parent, false);
            } catch (URISyntaxException e) {
                ULog.error(e);
            }
            // Resources.copy(url, Files.newOutputStream(dest));
        }
    }

    /**
     * Same as resolveResource but returns a file if the resource could be found (and it is a file)
     * 
     * @param resourceName
     * @return resource as a file or null
     */
    public File resolveFile(String resourceName) {
        URL url = resolveResource(resourceName);
        if (url == null)
            return null;
        File f = null;
        try {
            f = new File(url.toURI());
        } catch (Exception e) {
            ULog.error("Failed to resolve file with Error: " + e.getMessage() + " for path: " + url);
            e.printStackTrace();
        }
        if (f != null && f.exists())
            return f;
        return null;
    }

    /**
     * Tries to resolve a resource name This method first tries to detect the resource inside the home directory (if
     * manipulated by the end-user) if that fails, tries to resolve the resource inside the classpath
     * 
     * @param resourceName
     * @return
     */
    public URL resolveResource(String resourceName) {
        // first try to locate the resource in home dir
        File f = new File(resourceName);
        if (f.exists())
            try {
                return f.toPath().toUri().toURL();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            }
        Path p = getHomePath().resolve(resourceName);
        if (Files.exists(p))
            try {
                return p.toFile().toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        URL url = loaderClass.getClassLoader().getResource(resourceName);
        if (url != null)
            return url;
        url = this.loaderClass.getResource(resourceName);
        if (url != null)
            return url;
        try {
            url = Resources.getResource(resourceName);
        } catch (Exception e) {
            // nothing to do here, we are allowed to return null
        }
        if (url != null)
            return url;
        // TODO: try some others
        return null;
    }

    /**
     * Returns a new instance of the {@code Resources} entity with the given folder prefix.
     * <p>
     * The resources are loaded from the class-path of the {@code loaderClass} class
     * 
     * @param loaderClass
     *            class that defines the class-loader/jar-file to load from
     * @param folder
     *            the folder prefix
     * @return a new {@code Resources} entity
     */
    public static ResourceManager get(Class<?> loaderClass, String folder) {
        return new ResourceManager(loaderClass, folder);
    }

    /**
     * Returns a new instance of the {@code Resources} entity.
     * <p>
     * The resources are loaded from the class-path of the {@code loaderClass} class
     * 
     * @param loaderClass
     *            class that defines the class-loader/jar-file to load from
     * @return a new {@code Resources} entity
     */
    public static ResourceManager get(Class<?> loaderClass) {
        return new ResourceManager(loaderClass, null);
    }

    /**
     * Returns a new instance of the {@code Resources} entity.
     * <p>
     * The resources are loaded from the class-path of the {@code loaderInstance} class
     * 
     * @param loaderInstance
     *            instance that defines the class-loader/jar-file to load from - may not be null
     * @return a new {@code Resources} entity
     */
    public static ResourceManager get(final Object loaderInstance) {
        if (loaderInstance == null)
            return null;
        return get(loaderInstance.getClass());
    }

    /**
     * Method chaining style of setting the loader-class
     * 
     * @param loaderClass
     *            the loader class
     */
    public ResourceManager from(Class<?> loaderClass) {
        this.loaderClass = loaderClass;
        if (this.loaderClass == null) {
            this.loaderClass = ResourceManager.class;
        }
        return this;
    }

    /**
     * Method chaining style of setting the loader-class
     * 
     * @param folder
     *            the loader class
     */
    public ResourceManager folder(String folder) {
        this.folder = folder == null ? "" : folder;
        if (!this.folder.isEmpty() && !this.folder.endsWith("/")) {
            this.folder = this.folder + "/";
        }
        return this;
    }

    /********************************************/
    /********* Resource loading methods *********/
    /********************************************/

    /**
     * Returns a full bath based on the current folder prefix and the given {@code path} parameter
     * 
     * @param path
     *            the resource path relative to the current folder
     * @return the full path
     */
    private String getFullPath(String path) {
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        return folder + path;
    }

    /**
     * Returns a URL for the resource given by the {@code path} parameter.
     * 
     * @param path
     *            the path to the resource
     * @return the resource
     */
    public URL getResource(String path) {
        Objects.requireNonNull(path);
        // String fp = getFullPath(path);
        URL url = resolveResource(path);// loaderClass != null ? loaderClass.getResource(fp) : null;
        if (url != null)
            return url;
        return null;// getClass().getClassLoader().getResource(fp);//if we could not find it from
                    // class, try out the classloader
    }

    /**
     * Returns a new {@linkplain ImageIcon} for the image given by the {@code path} parameter.
     * 
     * @param path
     *            the path to the resource
     * @return the {@linkplain ImageIcon} with the given path
     */
    public ImageIcon getImageIcon(String path) {
        Objects.requireNonNull(path);
        URL url = getResource(path);
        return (url == null) ? null : new ImageIcon(url);
    }

    /**
     * Returns a cached {@linkplain ImageIcon} for the image given by the {@code path} parameter.
     * 
     * @param path
     *            the path to the resource
     * @return the {@linkplain ImageIcon} with the given path
     */
    public ImageIcon getCachedImageIcon(String path) {
        Objects.requireNonNull(path);
        CacheKey key = new CacheKey(loaderClass, getFullPath(path));
        ImageIcon imageIcon = (ImageIcon) cache.get(key);
        if (cache.containsKey(key)) {
            return imageIcon;
        }
        imageIcon = new ImageIcon(getResource(path));
        cache.put(key, imageIcon);
        return imageIcon;
    }

    /**********************************/
    /********* Helper classes *********/
    /**********************************/

    /**
     * Defines a cache key used for caching resources by loaderClass, and a full resource path
     */
    private static class CacheKey implements Serializable {
        private static final long serialVersionUID = 1L;
        private Class<?> loaderClass;
        private String fullPath;

        /**
         * Constructor
         * 
         * @param loaderClass
         * @param fullPath
         */
        public CacheKey(Class<?> loaderClass, String fullPath) {
            Objects.requireNonNull(loaderClass);
            Objects.requireNonNull(fullPath);
            this.loaderClass = loaderClass;
            this.fullPath = fullPath;
        }

        @Override
        public int hashCode() {
            return Objects.hash(loaderClass, fullPath);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            CacheKey other = (CacheKey) obj;
            return loaderClass.equals(other.loaderClass) && fullPath.equals(other.fullPath);
        }

    }

    /**
     * resolves the named resource and tries to open an input stream
     * 
     * @param resourceName
     * @return
     * @throws IOException
     */
    public InputStream getInputStream(String resourceName) throws IOException {
        URL url = resolveResource(resourceName);
        if (url == null)
            return null;

        return url.openConnection().getInputStream();
    }

    /**
     * Collect Resources that match the filter predicate in the all available classpathes
     * 
     * @warn This method search all registered ClassPathProvider, this may take some time
     * @param filter
     * @return collection of Resource URLs, that did match the filter or an empty list
     */
    public static Collection<URL> collectResourcesInAllClasspaths(Predicate<URL> filter) {
        Collection<ClassPathProvider> classpathes = UCoreExtensionManager.getExtensions(ClassPathProvider.class);
        return collectResourcesInClassPaths(filter, classpathes);
    }

    /**
     * Collect Resources that match the filter predicate in the all available classpathes
     * 
     * @warn This method search all registered ClassPathProvider, this may take some time
     * @param regEx
     *            Regular Expression for the name of the URL that shall be accepted
     * @return collection of Resource URLs, that did match the filter or an empty list
     */
    public static Collection<URL> collectResourcesInAllClasspaths(String regEx) {
        Collection<ClassPathProvider> classpathes = UCoreExtensionManager.getExtensions(ClassPathProvider.class);
        return collectResourcesInClassPaths(regEx, classpathes);
    }

    /**
     * Collect Resources that match the filter predicate in all provided classpathes
     * 
     * @param regEx
     *            Regular Expression for the name of the URL that shall be accepted
     * @param classpathes
     *            classpathes to be searched in
     * @return collection of Resource URLs, that did match the filter or an empty list
     */
    public static Collection<URL> collectResourcesInClassPaths(String regEx,
            Collection<ClassPathProvider> classpathes) {
        final Pattern pattern = Pattern.compile(regEx);
        return collectResourcesInClassPaths(new Predicate<URL>() {
            @Override
            public boolean apply(URL input) {
                return pattern.matcher(input.toString()).matches();
            }
        }, classpathes);
    }

    /**
     * Collect Resources that match the filter predicate in all provided classpathes
     * 
     * @param filter
     *            filter to select accepted URLs
     * @param classpathes
     *            classpathes to be searched in
     * @return collection of Resource URLs, that did match the filter or an empty list
     */
    public static Collection<URL> collectResourcesInClassPaths(Predicate<URL> filter,
            Collection<ClassPathProvider> classpathes) {
        ArrayList<URL> out = new ArrayList<>();
        for (ClassPathProvider cpp : classpathes) {
            ClassPath cp = cpp.getClassPath();
            if (cp != null) {
                for (ResourceInfo ri : cp.getResources()) {
                    if (ri instanceof ClassInfo)
                        continue;
                    if (filter.apply(ri.url()))
                        out.add(ri.url());
                }
            }
        }

        return out;
    }

}
