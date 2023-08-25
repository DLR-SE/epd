package de.emir.tuml.runtime.epf.provider.impl;

import java.util.HashMap;

public class RootApplicationClassLoader extends ClassLoader {

    private HashMap<String, Class<?>> mLoadedClass = new HashMap<>();
    private ClassLoader baseClassLoader;

    public RootApplicationClassLoader(ClassLoader baseClassLoader) {
        this.baseClassLoader = baseClassLoader;

    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {

        if (mLoadedClass.containsKey(className)) {
            Class<?> res = mLoadedClass.get(className);
            if (res == null)
                throw new ClassNotFoundException("Could not find: " + className + " in Applicationclassloader");
            return res;
        }
        Class<?> result = getLoadedClass(className);
        if (result == null) {
            try {
                result = baseClassLoader.loadClass(className);
                if (result != null)
                    rememberClass(className, result);
            } catch (ClassNotFoundException c) {
                rememberClass(className, null);
                throw c;
            }
        }
        return result;
    }

    public Class<?> getLoadedClass(String className) {
        return mLoadedClass.get(className);
    }

    public void rememberClass(String className, Class<?> result) {
        mLoadedClass.put(className, result);
    }
}
