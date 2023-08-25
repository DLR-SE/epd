package de.emir.tuml.runtime.epf.provider.impl;

import de.emir.tuml.runtime.epf.PluginManager;
import de.emir.tuml.runtime.epf.descriptors.ClassPathDescriptor;
import de.emir.tuml.runtime.epf.utils.MavenUtil;
import org.apache.maven.model.Model;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Locates the pom.xml file within an jar file. if the pom file has been found, it acts similar to the
 * PomFileDescriptorProvider
 * 
 * @author sschweigert
 *
 */
public class JarFileDescriptorProvider extends PomFileDescriptorProvider {

    @Override
    public ClassPathDescriptor<String> provide(URL url, MavenUtil util, PluginManager pmgr) {
        if (url.toString().endsWith(".jar")) {
            File jarFile = null;
            JarFile jf = null;
            try {
                jarFile = new File(url.toURI());
                jf = new JarFile(jarFile);
            } catch (Exception e) {
            }
            if (jf == null)
                return null;
            JarEntry pomEntry = null;
            Enumeration<JarEntry> entries = jf.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                // TODO: Isn't it always META_INF/maven/pom.xml?
                if (entry.getName().endsWith("/pom.xml")) { // this could give problems, if more than one pom.xml is
                                                            // available, for example in resources
                    try {
                        InputStream pomInputStream = jf.getInputStream(entry);
                        Model model = util.resolveModel(pomInputStream);
                        // just a sanity check
                        String artifName = model.getArtifactId();
                        if (jarFile.getName().contains(artifName) == false) {
                            continue; // check if there are other pom files
                        }
                        model.setPomFile(jarFile);
                        return createDescriptor(pmgr, model, url, util);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        return null;
    }
}
