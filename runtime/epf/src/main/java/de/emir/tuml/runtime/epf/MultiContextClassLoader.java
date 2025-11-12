package de.emir.tuml.runtime.epf;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import de.emir.tuml.runtime.epf.provider.impl.EClassLoader;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class MultiContextClassLoader extends ClassLoader {

    private ClassLoader wrappedClassLoader;
    private PluginManager pm;

    public MultiContextClassLoader(ClassLoader wrappedClassLoader, PluginManager pm) {
        this.wrappedClassLoader = wrappedClassLoader;
        this.pm = pm;
    }

    
    /**
     * Get the caller class.
     * @param level The level of the caller class.
     *              For example: If you are calling this class inside a method and you want to get the caller class of that method,
     *                           you would use level 2. If you want the caller of that class, you would use level 3.
     *
     *              Usually level 2 is the one you want.
     * @return The caller class.
     * @throws ClassNotFoundException We failed to find the caller class.
     */
    public static Class getCallerClass(int level) throws ClassNotFoundException {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        String rawFQN = stElements[level+1].toString().split("\\(")[0];
        String className = rawFQN.substring(rawFQN.lastIndexOf("/") + 1, rawFQN.lastIndexOf('.')).split("\\$")[0];
        return Class.forName(className);
    }
    @Override
    public URL getResource(String name) {
        // using reflections should be quite fast, but unfortunately oracle
        // removed (not only deprecated!) this method
        // return
        // sun.reflect.Reflection.getCallerClass(callStackDepth).getName();
        // StackTraceElement[] st = Thread.currentThread().getStackTrace();
        // if
        // (st[3].getClassName().equals("de.emir.tuml.ucore.runtime.logging.ULog")
        // && st.length > 3)
        // return st[4].getClassName(); // called by one of the methods, with
        // // intendation
        // return st[3].getClassName();
        // Attention: Class.forName("..."); maybe returns class from wrong
        // classloader
        if (name.startsWith("http://")) {
            try {
                ULog.debug("Skip Resource: " + name);
                return new URL(name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        URL url = wrappedClassLoader.getResource(name);
        if (url == null) {
        	try {
	            Class<?> clazz = getCallerClass(2);
	            ClassLoader loader = clazz.getClassLoader();
	
	            if (loader == null) {
	                clazz = getCallerClass(3);
	                loader = clazz.getClassLoader();
	            }
	
	            url = loader.getResource(name);
	            if (url != null) {
	                return url;
	            }
        	} catch (ClassNotFoundException e) {
                ULog.error(String.format("Could not find caller class for resource %s.", name));
            }catch (Exception e) {
        		e.printStackTrace();
        	}
        }

        ULog.debug("Search for Resource in whole classpath: " + name);
        for (ClasspathEntry<?> entry : pm.getEntries()) {
            if (entry.getClassLoader() == this) {
                continue;
            }
            if (entry.getClassLoader() instanceof MultiContextClassLoader) {
                url = ((MultiContextClassLoader) entry.getClassLoader()).wrappedClassLoader.getResource(name);
                if (url != null) {
                    return url;
                }
            } else {
                ClassLoader cl = entry.getClassLoader();
                if (cl instanceof EClassLoader) {
                    url = ((EClassLoader) cl).getLocalResource(name); // do not use getResource here, since they do also
                                                                      // go through all elements, if they could not find
                                                                      // it either in its own classpath or its
                                                                      // dependencies
                } else {
                    url = entry.getClassLoader().getResource(name);
                }
                if (url != null) {
                    return url;
                }
            }
        }

        return null;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            Class<?> cl = wrappedClassLoader.loadClass(name);
            if (cl != null) {
                return cl;
            }
        } catch (Exception e) {
        }
        ULog.debug("Search for Class in MultiContextLoader: " + name);
        for (ClasspathEntry<?> entry : pm.getEntries()) {
            if (entry.getClassLoader() == this) {
                continue;
            }
            if (entry.getClassLoader() instanceof MultiContextClassLoader) {
                continue; // we already searched this one (this)
            } else {
                try {
                    ClassLoader cl = entry.getClassLoader();
                    if (cl instanceof EClassLoader) {
                        Class<?> c = ((EClassLoader) cl).loadLocalClass(name);
                        if (c != null) {
                            return c; // do not use loadClass here, since they do also go through all elements, if they
                                      // could not find it either in its own classpath or its dependencies
                        }
                    } else {
                        Class<?> c = cl.loadClass(name);
                        if (c != null) {
                            return c;
                        }
                    }
                } catch (ClassNotFoundException e) {
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return wrappedClassLoader.toString();
    }

    @Override
    public void clearAssertionStatus() {
        wrappedClassLoader.clearAssertionStatus();
    }

    @Override
    public void setPackageAssertionStatus(String packageName, boolean enabled) {
        wrappedClassLoader.setPackageAssertionStatus(packageName, enabled);
    }

    @Override
    public void setClassAssertionStatus(String className, boolean enabled) {
        wrappedClassLoader.setClassAssertionStatus(className, enabled);
    }

    @Override
    public void setDefaultAssertionStatus(boolean enabled) {
        wrappedClassLoader.setDefaultAssertionStatus(enabled);
    }

    @Override
    public boolean equals(Object obj) {
        return wrappedClassLoader.equals(obj);
    }

    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        return wrappedClassLoader.getResources(name);
    }

    @Override
    public int hashCode() {
        return wrappedClassLoader.hashCode();
    }

}
