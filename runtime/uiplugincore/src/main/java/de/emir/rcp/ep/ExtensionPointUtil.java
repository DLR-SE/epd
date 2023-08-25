package de.emir.rcp.ep;

import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.runtime.plugin.manager.PluginRuntimeManager;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;

public class ExtensionPointUtil {
    public static AbstractUIPlugin getCurrentlyLoadingPlugin() {

        PluginRuntimeManager prm = ServiceManager.get(PluginRuntimeManager.class);

        AbstractUIPlugin plugin = prm.getCurrentlyLoadingPlugin();

        if (plugin == null) {
            throw new IllegalStateException("Extensions may only be added in the start process of the application");
        }

        return plugin;

    }
}
