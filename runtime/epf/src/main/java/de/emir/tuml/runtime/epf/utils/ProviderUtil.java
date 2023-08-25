package de.emir.tuml.runtime.epf.utils;

import java.util.Arrays;
import java.util.List;

import de.emir.tuml.runtime.epf.provider.IClasspathDescriptorProvider;
import de.emir.tuml.runtime.epf.provider.IClasspathEntryProvider;
import de.emir.tuml.runtime.epf.provider.impl.GAVFileDescriptorProvider;
import de.emir.tuml.runtime.epf.provider.impl.JarEntryProvider;
import de.emir.tuml.runtime.epf.provider.impl.JarFileDescriptorProvider;
import de.emir.tuml.runtime.epf.provider.impl.MavenSourceProvider;
import de.emir.tuml.runtime.epf.provider.impl.PomFileDescriptorProvider;

public class ProviderUtil {

    public static List<IClasspathDescriptorProvider> getDescriptorProvider() {
        return Arrays.<IClasspathDescriptorProvider>asList(new PomFileDescriptorProvider(),
                new JarFileDescriptorProvider(), new GAVFileDescriptorProvider() /*
                                                                                  * ensure to insert this as last option
                                                                                  */);
    }

    public static List<IClasspathEntryProvider> getEntryProvider() {
        return Arrays.<IClasspathEntryProvider>asList(new MavenSourceProvider(), new JarEntryProvider());
    }

}
