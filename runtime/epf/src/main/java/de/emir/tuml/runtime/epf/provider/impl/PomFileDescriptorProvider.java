package de.emir.tuml.runtime.epf.provider.impl;

import de.emir.tuml.runtime.epf.CoordinateElement;
import de.emir.tuml.runtime.epf.PluginManager;
import de.emir.tuml.runtime.epf.descriptors.ClassPathDescriptor;
import de.emir.tuml.runtime.epf.descriptors.PluginDescriptor;
import de.emir.tuml.runtime.epf.provider.IClasspathDescriptorProvider;
import de.emir.tuml.runtime.epf.utils.MavenUtil;
import de.emir.tuml.ucore.runtime.logging.ULog;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.eclipse.aether.artifact.Artifact;

import java.net.URL;
import java.util.List;

/**
 * ClassPathDescriptor builded from a pom.xml
 * @author sschweigert
 *
 */
public class PomFileDescriptorProvider implements IClasspathDescriptorProvider {

    public static final String PLUGIN_CLASS_PROPERTY = "eMIR-Plugin-Class";

    @Override
    public ClassPathDescriptor<String> provide(URL url, MavenUtil util, PluginManager pmgr) {
        if (url.toString().endsWith(".jar"))
            return null; // do not throw an exception just say, we can not do this
        Model model = util.resolveModel(url);
        if (model == null)
            return null;

        return createDescriptor(pmgr, model, url, util);
    }

    public ClassPathDescriptor<String> createDescriptor(PluginManager pmgr, Model model, URL url, MavenUtil util) {
        String pluginClassName = model.getProperties().getProperty(PLUGIN_CLASS_PROPERTY);
        String cid = MavenUtil.getGroupId(model) + ":" + model.getArtifactId();
        String desc = model.getDescription();
        String v = MavenUtil.getVersion(model);
        URL[] urls = new URL[] { url };
        // check if it is a plugin or an classpath. It is a plulgin if it contains the
        // maven property PLUGIN_CLASS_PROPERTY
        ClassPathDescriptor<String> descriptor = null;
        if (pluginClassName == null || pluginClassName.isEmpty()) // its "just" an classpath entry
            descriptor = new ClassPathDescriptor<String>(cid, desc, v, urls);
        else
            descriptor = new PluginDescriptor<String>(cid, desc, v, pluginClassName, urls);

        // first check if we have to do a more detailed resolving process, that is the
        // case, if we find a non resolved (inherited) version or groupId within the
        // dependencies
        boolean resolve = false;
        for (Dependency dep : model.getDependencies()) {
            if (dep.getGroupId() == null || dep.getGroupId().contains("${") || dep.getVersion() == null
                    || dep.getVersion().contains("${")) {
                resolve = true;
                break;
            }
        }
        List<Artifact> resolvedArtifacts = null;
        if (resolve) {
            ULog.warn("Need to further resolve Descriptor: " + descriptor);
            Artifact resolveResult = util.resolveArtifact(descriptor.getCoordinate());
            resolvedArtifacts = util.resolveArtifacts(resolveResult);
        }

        // fill dependencies
        for (Dependency dep : model.getDependencies()) {
            if (MavenUtil.isRequired(dep)) {
                String dgid = dep.getGroupId();
                String dv = dep.getVersion();
                String daid = dep.getArtifactId();
                if (dgid == null || dgid.contains("${") || dv == null || dv.contains("${")) {
                    if (resolvedArtifacts != null) {
                        // find in list of resolved artifacts
                        for (Artifact a : resolvedArtifacts) {
                            if (a.getArtifactId().equals(daid)) {
                                dv = a.getVersion();
                                dgid = a.getGroupId();
                                break;
                            }
                        }
                    }
                }
                String depId = dgid + ":" + daid;
                String depV = dv;
                if (depV == null || depV.contains("${")) {
                    // try to find them in the resolved Artifacts
                    ULog.error("Could not find dependency: " + dep);
                    continue;
                }
                CoordinateElement<String> depCoord = pmgr.createCoordinate(depId, depV);
                // CoordinateElement<String> depCoord = new CoordinateElement<String>(depId, null, depV);
                if (dep.getClassifier() != null && dep.getClassifier().isEmpty() == false) {
                    depCoord.add("classifier", dep.getClassifier());
                }

                descriptor.addDependency(depCoord);
            }
        }
        return descriptor;
    }
}
