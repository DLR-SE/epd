package de.emir.runtime.plugin.manager;

import java.util.ArrayList;
import java.util.List;

import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.IService;

public class PluginRuntimeManager implements IService {

    private AbstractUIPlugin currentlyLoadingPlugin;

    private List<AbstractUIPlugin> loadedPlugins = new ArrayList<>();

    public void setCurrentlyLoadingPlugin(AbstractUIPlugin currentlyLoadingPlugin) {
        this.currentlyLoadingPlugin = currentlyLoadingPlugin;
    }

    public AbstractUIPlugin getCurrentlyLoadingPlugin() {
        return currentlyLoadingPlugin;
    }

    public List<AbstractUIPlugin> getLoadedPlugins() {
        return loadedPlugins;
    }

    public void addLoadedPlugin(AbstractUIPlugin plugin) {

        loadedPlugins.add(plugin);

    }

}
