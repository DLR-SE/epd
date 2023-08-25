package de.emir.tuml.runtime.epf.provider.impl;

import de.emir.tuml.runtime.epf.ClasspathEntry;
import de.emir.tuml.runtime.epf.PluginManager;
import de.emir.tuml.runtime.epf.descriptors.ClassPathDescriptor;
import de.emir.tuml.runtime.epf.provider.IClasspathEntryProvider;
import de.emir.tuml.ucore.runtime.resources.ClassPathProvider.SimpleClasspathInfo;

import java.io.File;
import java.net.URL;

/**
 * Resolves source directories, e.g. loaded as workspace expect the classes to be in the url[0]/../target/classes
 * directory (just checks for the existance of such an directory)
 * 
 * @author sschweigert
 *
 */
public class MavenSourceProvider implements IClasspathEntryProvider {

    @Override
    public ClasspathEntry provide(PluginManager pluginManager, ClassPathDescriptor descriptor) {
        if (descriptor.getURLs() == null || descriptor.getURLs().length != 1)
            return null;
        try {
            File file = new File(descriptor.getURLs()[0].toURI());
            if (file.exists() && file.getName().endsWith("pom.xml")) {
                File dir = new File(file.getParent() + "/target/classes");
                if (dir.exists() == false || dir.isDirectory() == false) {
                    return null;
                }
                URL[] sourceLoc = null;
                File sFile = new File(file.getParentFile() + "/src/main/java");
                if (sFile.exists())
                    sourceLoc = new URL[] { sFile.toURI().toURL() };
                URL[] javaDocLoc = null;

                SimpleClasspathInfo sci = new SimpleClasspathInfo(new URL[] { dir.toURI().toURL() }, sourceLoc,
                        javaDocLoc);
                return new ClasspathEntry<>(descriptor,
                        new EClassLoader(new URL[] { dir.toURI().toURL() }, descriptor, pluginManager), pluginManager,
                        sci);

            }
        } catch (Exception e) {
            // do not spam the user - may another provider is more lucky
        }
        return null;
    }

}
