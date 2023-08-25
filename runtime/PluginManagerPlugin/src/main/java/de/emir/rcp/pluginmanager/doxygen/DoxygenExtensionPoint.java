package de.emir.rcp.pluginmanager.doxygen;

import de.emir.tuml.ucore.runtime.extension.IExtensionPoint;

import java.util.ArrayList;
import java.util.List;

public class DoxygenExtensionPoint implements IExtensionPoint {
    private static List<AbstractDoxygenExtension> extensions = new ArrayList<>();

    public void registerDoxygenExtension(AbstractDoxygenExtension extension) {
        extensions.add(extension);
    }

    public List<AbstractDoxygenExtension> getExtensions() {
        if (extensions == null) {
            extensions = new ArrayList<>();
        }
        return extensions;
    }
}
