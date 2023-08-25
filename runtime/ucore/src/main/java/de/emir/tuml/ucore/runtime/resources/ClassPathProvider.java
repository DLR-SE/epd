package de.emir.tuml.ucore.runtime.resources;

import java.net.URL;

import com.google.common.reflect.ClassPath;

/**
 * The ClassPathProvider provides access to the current com.google.common.reflect.ClassPath without loading it in
 * advance
 * 
 * @ingroup UCoreExtensions
 * @ingroup Resources
 * 
 * @author sschweigert
 *
 */
public interface ClassPathProvider {

    public interface IClasspathInfo {
        URL[] getBinaryLocation();

        URL[] getSourceLocation();

        URL[] getJavadocLocation();
    }

    public static class SimpleClasspathInfo implements IClasspathInfo {
        public final URL[] binaryLocation;
        public final URL[] sourceLocation;
        public final URL[] javadocLocation;

        public SimpleClasspathInfo(URL[] bl, URL[] sl, URL[] jl) {
            binaryLocation = bl;
            sourceLocation = sl;
            javadocLocation = jl;
        }

        @Override
        public URL[] getBinaryLocation() {
            return binaryLocation;
        }

        @Override
        public URL[] getSourceLocation() {
            return sourceLocation;
        }

        @Override
        public URL[] getJavadocLocation() {
            return javadocLocation;
        }
    }

    /**
     * Provide and load the ClassPath
     * 
     * @return the loaded ClassPath
     */
    public ClassPath getClassPath();

    public ClassLoader getClassLoader();

    public IClasspathInfo getClasspathInfo();
}
