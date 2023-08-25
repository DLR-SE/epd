package de.emir.tuml.runtime.epf.provider.impl;

import java.io.File;
import java.net.URL;

import de.emir.tuml.runtime.epf.PluginManager;
import de.emir.tuml.runtime.epf.descriptors.ClassPathDescriptor;
import de.emir.tuml.runtime.epf.provider.IClasspathDescriptorProvider;
import de.emir.tuml.runtime.epf.utils.MavenUtil;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * Estimates the maven parameters from the file / directory name
 * 
 * @warn this IClassDescriptorProvider is not able to detect dependencies
 * @arn use this IClassPathDescriptorProvider as last option since it most probably won't fail
 * @author sschweigert
 *
 */
public class GAVFileDescriptorProvider implements IClasspathDescriptorProvider {

    @Override
    public ClassPathDescriptor provide(URL url, MavenUtil util, PluginManager pmgr) {
        try {
            File f = new File(url.toURI());
            if (f != null && f.exists()) {
                File versionDir = f.getParentFile();
                if (versionDir != null) {
                    String version = versionDir.getName();
                    File artifactDir = versionDir.getParentFile();
                    if (artifactDir != null) {
                        String artifact = artifactDir.getName();
                        File gdir = artifactDir.getParentFile();
                        if (gdir != null) {
                            String str_g_abs = gdir.getAbsolutePath();
                            String localRepos = util.getLocalRepositoryPath().getAbsolutePath();
                            String gid = str_g_abs.substring(localRepos.length() + 1).replace(File.separator, ".");

                            String cid = gid + ":" + artifact;
                            String desc = "";

                            return new ClassPathDescriptor<String>(cid, desc, version, new URL[] { url });
                        }
                    }
                }
//                System.out.println();
            }
        } catch (Exception e) {
            ULog.error("Failed to estimate GAV parameters for : " + url);
        }
        return null;
    }

}
