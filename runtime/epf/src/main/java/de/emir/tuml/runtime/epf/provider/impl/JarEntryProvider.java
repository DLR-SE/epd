package de.emir.tuml.runtime.epf.provider.impl;

import java.io.File;
import java.net.URL;

import de.emir.tuml.runtime.epf.ClasspathEntry;
import de.emir.tuml.runtime.epf.PluginManager;
import de.emir.tuml.runtime.epf.descriptors.ClassPathDescriptor;
import de.emir.tuml.runtime.epf.provider.IClasspathEntryProvider;
import de.emir.tuml.ucore.runtime.resources.ClassPathProvider.SimpleClasspathInfo;

/**
 * Resolves jar files, e.g. loaded from coordinates expect the jar file to be located right to the URL, with same name
 * but different ending (xml->jar | pom->jar)
 * 
 * @author sschweigert
 *
 */
public class JarEntryProvider implements IClasspathEntryProvider {

    @Override
    public ClasspathEntry<?> provide(PluginManager pluginManager, ClassPathDescriptor<?> descriptor) {
        if (descriptor.getURLs() == null || descriptor.getURLs().length != 1)
            return null;
        try {
            File file = new File(descriptor.getURLs()[0].toURI());
            if (file.exists()) {
                String name = file.getAbsolutePath();
                String baseName = name;
                int idx = name.lastIndexOf('.');
                if (idx > 0) {
                    baseName = name.substring(0, idx);
                    name = baseName + ".jar";
                }
                file = new File(name);
                if (file.exists() == false || file.isDirectory() == false) {

                    URL[] urls = new URL[] { file.toURI().toURL() };

                    EClassLoader classLoader = new EClassLoader(urls, descriptor, pluginManager);
                    URL[] sourceLoc = null;
                    File sFile = new File(baseName + "-sources.jar");
                    if (sFile.exists())
                        sourceLoc = new URL[] { sFile.toURI().toURL() };
                    URL[] javaDocLoc = null;
                    File jFile = new File(baseName + "-javadoc.jar");
                    if (jFile.exists())
                        javaDocLoc = new URL[] { jFile.toURI().toURL() };

                    SimpleClasspathInfo sci = new SimpleClasspathInfo(urls, sourceLoc, javaDocLoc);

                    return new ClasspathEntry<>(descriptor, classLoader, pluginManager, sci);
                }
                // check if the provider has options like a classifier and add
                // it
                String classifierOption = descriptor.getOption("classifier");
                if (classifierOption != null && classifierOption.isEmpty() == false) {
                    file = new File(name);
                }
                return null;
            }
        } catch (Exception e) {
            // do not spam the user - may another provider is more lucky
        }
        return null;
    }

}
