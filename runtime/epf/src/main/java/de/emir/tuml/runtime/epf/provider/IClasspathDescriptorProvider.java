package de.emir.tuml.runtime.epf.provider;

import java.net.URL;

import de.emir.tuml.runtime.epf.PluginManager;
import de.emir.tuml.runtime.epf.descriptors.ClassPathDescriptor;
import de.emir.tuml.runtime.epf.utils.MavenUtil;

public interface IClasspathDescriptorProvider {

    public ClassPathDescriptor<?> provide(URL url, MavenUtil util, PluginManager pmgr);

}
