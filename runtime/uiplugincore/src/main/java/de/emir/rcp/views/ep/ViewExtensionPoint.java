package de.emir.rcp.views.ep;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import de.emir.rcp.views.AbstractView;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.runtime.plugin.manager.PluginRuntimeManager;
import de.emir.tuml.ucore.runtime.extension.IExtensionPoint;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class ViewExtensionPoint implements IExtensionPoint {

    private Logger log = ULog.getLogger(ViewExtensionPoint.class);

    private Map<String, ViewGroup> views = new HashMap<>();

    public IViewDescriptor view(String id, Class<? extends AbstractView> viewClass) {
        if (views.containsKey(id)) {
            log.error("A view with id [" + id + "] already exists.");
            return null;
        }

        PluginRuntimeManager prm = ServiceManager.get(PluginRuntimeManager.class);

        AbstractUIPlugin plugin = prm.getCurrentlyLoadingPlugin();

        if (plugin == null) {
            throw new IllegalStateException(
                    "Extensions may only be added in the start process of the application [" + id + "]");
        }

        ViewGroup group = null;
        if (views.containsKey(null)){

            group = views.get(null);

        }else {
            group = new ViewGroup(null);

            views.put(null, group);

            log.debug("View with id [" + id + "] added.");
        }

        ViewDescriptor view = new ViewDescriptor(id, viewClass, plugin);

        group.view(id, view);

        log.debug("View with id [" + id + "] added.");

        return view;
    }

    public IViewGroup group(String id){
       if (views.containsKey(id)){
           return views.get(id);
       }

       ViewGroup group = new ViewGroup(id);

       views.put(id, group);

       log.debug("View with id [" + id + "] added.");

       return group;
    }

    public Map<String, ViewGroup> getViewDescriptors() {
        return views;
    }

}
