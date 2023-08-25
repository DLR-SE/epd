package de.emir.tuml.runtime.epf.provider.impl;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;

import de.emir.tuml.runtime.epf.ClasspathEntry;
import de.emir.tuml.runtime.epf.PluginManager;
import de.emir.tuml.runtime.epf.descriptors.ClassPathDescriptor;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class EClassLoader extends URLClassLoader {

    private final Logger log;
    private ClassPathDescriptor<?> mDescriptor;
    private PluginManager mPluginManager;
    private boolean mEnableTraceLog;

    private ArrayList<ClasspathEntry> mDependencies = null; // flat list of all dependencies - primary used for local
                                                            // search
    private RootApplicationClassLoader rootApplicationClassLoader;

    public EClassLoader(URL[] urls, ClassPathDescriptor<?> descriptor, PluginManager pluginManager) {
        super(urls, pluginManager.getRootClassLoader());

        mDescriptor = descriptor;
        mPluginManager = pluginManager;
        log = ULog.getLogger(descriptor.getName());
        mEnableTraceLog = log != null ? log.isTraceEnabled() : false;

        rootApplicationClassLoader = pluginManager.getRootApplicationClassLoader();

    }

    @Override
    public Class<?> loadClass(String className) throws ClassNotFoundException {

        Class<?> result = rootApplicationClassLoader.getLoadedClass(className);

        if (result == null) {
            try {
                result = rootApplicationClassLoader.loadClass(className);
            } catch (Exception e) {
                // Not found, that's okay
            }
        }

        if (result == null) {

            result = _loadClass(className);

            if (result != null) {
                rootApplicationClassLoader.rememberClass(className, result);
            }

        }
        return result;
    }

    public Class<?> _loadClass(String className) throws ClassNotFoundException {
//        if (className.contains("ObjectFactory"))
//            System.out.println();
        synchronized (getClassLoadingLock(className)) {
            if (mEnableTraceLog)
                log.trace("Received request to load class '{}'", className);

//            if (className.endsWith("XmlPullParser")) {
//                System.out.println();
//            }

            // if the class it's a part of the plugin engine use parent class loader
            if (className.startsWith("de.emir.tuml.") || className.startsWith("java.")) {
                try {
                    Class<?> pc = getClass().getClassLoader().loadClass(className);
                    if (pc != null)
                        return pc;
                } catch (ClassNotFoundException e) {
                    // try next step
                }
            }

            // second check whether it's already been loaded
            Class<?> clazz = findLoadedClass(className);
            if (clazz != null) {
                if (mEnableTraceLog)
                    log.trace("Found loaded class '{}'", className);
                return clazz;
            }

            // nope, try to load locally
            try {
                clazz = findClass(className);
                if (mEnableTraceLog)
                    log.trace("Found class '{}' in plugin classpath", className);
                return clazz;
            } catch (ClassNotFoundException e) {
                // try next step
            }

            // look in dependencies
            if (mEnableTraceLog)
                log.trace("Search in dependencies for class '{}'", className);
            Collection<ClasspathEntry> dependencies = getDependencies();
            for (ClasspathEntry dependency : dependencies) {
                ClassLoader classLoader = dependency.getClassLoader();
                try {
                    if (classLoader instanceof EClassLoader) {
                        // search only local, and do not check the dependencies, since other
                        // dependencies may have the same, this way we could save a lot of search time
                        Class<?> res = ((EClassLoader) classLoader).loadLocalClass(className);
                        if (res != null)
                            return res;
                    } else {
                        // if through some customisation another classloader has been loaded, fall back
                        // to normal search - potential slow!
                        Class<?> res = classLoader.loadClass(className);
                        if (res != null)
                            return res;
                    }
                } catch (ClassNotFoundException e) {
                    // try next dependency
                }
            }

            // we could not find it in the direct or indirect deps, so we search all - @warn
            // this may take some time
            for (ClasspathEntry dependency : mPluginManager.getEntries()) {
                ClassLoader classLoader = dependency.getClassLoader();
                if (classLoader == this)
                    continue;
                if (getDependencies().contains(dependency))
                    continue;
                try {
                    if (classLoader instanceof EClassLoader) {
                        // search only local, and do not check the dependencies, since other
                        // dependencies may have the same, this way we could save a lot of search time
                        Class<?> res = ((EClassLoader) classLoader).loadLocalClass(className);
                        if (res != null) {
//                            if (res.getName().endsWith("MXParser")) {
//                                Class<?>[] interfaces = res.getInterfaces();
//                                Class<?> superCl = res.getSuperclass();
//                                System.out.println();
//                            }
                            return res;
                        }

                    } else {
                        // if through some customisation another classloader has been loaded, fall back
                        // to normal search - potential slow!
                        Class<?> res = classLoader.loadClass(className);
                        if (res != null)
                            return res;
                    }
                } catch (ClassNotFoundException e) {
                    // try next dependency
                }
            }
            if (mEnableTraceLog)
                log.trace("Couldn't find class '{}' in plugin classpath. Delegating to parent", className);

            // use the standard ClassLoader (which follows normal parent delegation)
            return super.loadClass(className);
        }

    }

    public Enumeration<URL> _getResources(String name, Set<String> closedList) throws IOException{
    	String myCoord = mDescriptor.getCoordinate();
    	if (closedList.contains(myCoord)) return null;
    	closedList.add(myCoord);
    	
    	Enumeration<URL> parent = super.getResources(name);
        ArrayList<Enumeration<URL>> deps = new ArrayList<>();
        deps.add(parent);
        for (ClasspathEntry dep : getDependencies()) {
        	String dep_coord = dep.getCoordinate();
        	if (closedList.contains(dep_coord) == false) {
        		ClassLoader cl = dep.getClassLoader();
        		Enumeration<URL> depEnum = null; 
        		
        		if (cl instanceof EClassLoader) { //in this case we can use this method instead of the original
        			depEnum = ((EClassLoader)cl)._getResources(name, closedList);
        		}else
        			depEnum = cl.getResources(name);
        		
        		if (depEnum != null)
                    deps.add(depEnum);
        	}
        }
        Enumeration<URL>[] all = deps.toArray((Enumeration<URL>[]) new Enumeration<?>[deps.size()]);
        ArrayList<URL> result = new ArrayList<>();
        for (Enumeration<URL> enumItem : all) {
            while (enumItem.hasMoreElements())
                result.add(enumItem.nextElement());
        }
        return Collections.enumeration(result);
    }
    
    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
    	return _getResources(name, new HashSet<>());
//        // TODO
//        Enumeration<URL> parent = super.getResources(name);
//        ArrayList<Enumeration<URL>> deps = new ArrayList<>();
//        deps.add(parent);
//        for (ClasspathEntry dep : getDependencies()) {
//            Enumeration<URL> depEnum = dep.getClassLoader().getResources(name);
//            if (depEnum != null)
//                deps.add(depEnum);
//        }
//        Enumeration<URL>[] all = deps.toArray((Enumeration<URL>[]) new Enumeration<?>[deps.size()]);
//        ArrayList<URL> result = new ArrayList<>();
//        for (Enumeration<URL> enumItem : all) {
//            while (enumItem.hasMoreElements())
//                result.add(enumItem.nextElement());
//        }
//        return Collections.enumeration(result);
    }

    @Override
    public URL getResource(String name) {

        URL res = super.getResource(name);
        if (res == null) {
            Collection<ClasspathEntry> dependencies = getDependencies();

            for (ClasspathEntry dependency : dependencies) {
                ClassLoader classLoader = dependency.getClassLoader();

                try {
                    if (classLoader instanceof EClassLoader) {
                        res = ((EClassLoader) classLoader).getLocalResource(name);
                    } else
                        res = classLoader.getResource(name);
                    if (res != null)
                        return res;
                } catch (Exception e) {
                    // try next dependency
                }
            }

        }
        if (res == null) {
            log.debug("Search for Resource with name: " + name + " in whole classpath");
            for (ClasspathEntry<?> entry : mPluginManager.getEntries()) {
                if (entry.getClassLoader() == this)
                    continue;

                URL url = null;
                ClassLoader cl = entry.getClassLoader();
                if (cl instanceof EClassLoader)
                    url = ((EClassLoader) cl).getLocalResource(name);
                else
                    url = cl.getResource(name);
                if (url != null)
                    return url;
            }
        }
        if (res == null)
            return null;
        return res;
    }

    public URL getLocalResource(String name) {
        if (name.startsWith("http://")) {
            ULog.debug("Skip Resource: " + name);
            return null; // we do not download any resources
        }
        return super.getResource(name);
    }

    public Class<?> loadLocalClass(String className) {
        // second check whether it's already been loaded
        Class<?> clazz = findLoadedClass(className);
        if (clazz != null) {
            if (mEnableTraceLog)
                log.trace("Found loaded class '{}'", className);
            return clazz;
        }

        // nope, try to load locally
        try {
            clazz = findClass(className);
            if (mEnableTraceLog)
                log.trace("Found class '{}' in plugin classpath", className);
            return clazz;
        } catch (ClassNotFoundException e) {
            // try next step
        }
        return null;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    private Collection<ClasspathEntry> getDependencies() {

        if (mDependencies == null) {
            mDependencies = new ArrayList<>();
            mDependencies.addAll(mPluginManager.getEntry(mDescriptor.getCoordinate()).getAllDependencies());
            try {
                Collections.sort(mDependencies, new Comparator<ClasspathEntry>() {
                    @Override
                    public int compare(ClasspathEntry o1, ClasspathEntry o2) {
                        if (o1 == o2)
                            return 0;
                        if (o1.getAllDependencies().contains(o2))
                            return -1;

                        if (o2.getAllDependencies().contains(o1)) {
                            return 1;
                        }

                        return o1.getCoordinate().compareTo(o2.getCoordinate());
                    }
                });
            } catch (Exception e) {
            }
        }
        return mDependencies;
    }

}
