package de.emir.tuml.runtime.epf.provider;

import de.emir.tuml.runtime.epf.ClasspathEntry;
import de.emir.tuml.runtime.epf.PluginManager;
import de.emir.tuml.runtime.epf.descriptors.ClassPathDescriptor;

public interface IClasspathEntryProvider {

    public ClasspathEntry<?> provide(PluginManager pluginManager, ClassPathDescriptor<?> descriptor);

}
