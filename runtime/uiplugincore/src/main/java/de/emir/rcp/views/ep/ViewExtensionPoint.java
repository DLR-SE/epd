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

/**
 * Extension point which manages registering of views. When the views are registered, they are available
 * in the Open View dialog and can be opened.
 */
public class ViewExtensionPoint implements IExtensionPoint {

    private Logger log = ULog.getLogger(ViewExtensionPoint.class);

    private Map<String, ViewGroup> views = new HashMap<>();

    /**
     * Creates a ViewDescriptor for a given view.
     * @param id ID of the view which should be added.
     * @param viewClass Class of the view which can be instantiated.
     * @return ViewDescriptor for the registered view.
    */
    public IViewDescriptor view(String id, Class<? extends AbstractView> viewClass) {
        if (views.containsKey(id)) {
            log.error("A view with id [{}] already exists.", id);
            return null;
        }

        PluginRuntimeManager prm = ServiceManager.get(PluginRuntimeManager.class);

        AbstractUIPlugin plugin = prm.getCurrentlyLoadingPlugin();

        if (plugin == null) {
            throw new IllegalStateException(
                    "Extensions may only be added in the start process of the application [" + id + "]");
        }

        ViewGroup group;
        if (views.containsKey(null)){
            group = views.get(null);
        } else {
            group = new ViewGroup(null);
            views.put(null, group);
            log.debug("Root view group added.", id);
        }

        IViewDescriptor view = new ViewDescriptor(id, viewClass, plugin);

        group.view(id, view);

        log.debug("View with id [{}] added.", id);

        return view;
    }

    /**
     * Creates a new ViewGroup or gets an already registered group.
     * @param id ID of the group.
     * @return Newly created ViewGroup with the given ID or already created instance.
     */
    public IViewGroup group(String id){
       if (views.containsKey(id)){
           return views.get(id);
       }

       ViewGroup group = new ViewGroup(id);

       views.put(id, group);

       log.debug("View group with id [{}] added.", id);

       return group;
    }

    /**
     * Gets all registered ViewGroups.
     * @return ViewGroups.
     */
    public Map<String, ViewGroup> getViewDescriptors() {
        return views;
    }

}
