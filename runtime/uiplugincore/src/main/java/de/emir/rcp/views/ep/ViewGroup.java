package de.emir.rcp.views.ep;

import de.emir.rcp.views.AbstractView;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.runtime.plugin.manager.PluginRuntimeManager;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;
import org.slf4j.Logger;

import javax.swing.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ViewGroup implements IViewGroup {
    private static final Logger log = ULog.getLogger(ViewGroup.class);

    private String id;
    private String label;

    private int mIconSize = -1;
    private ImageIcon icon;
    private ResourceManager mResourceManager;

    private Map<String, ViewDescriptor> views;
    private Map<String, ViewGroup> subGroups;

    public ViewGroup(String id){
        this.id = id;
        views = new HashMap<>();
        subGroups = new HashMap<>();
    }

    @Override
    public IViewGroup group(String id) {
        return group(id, null);
    }

    @Override
    public IViewGroup group(String id, String label) {
        ViewGroup group = new ViewGroup(id);
        group = (ViewGroup) group.label(label);

        if (subGroups.containsKey(id)) {
            log.error("A view with id [" + id + "] already exists.");
            return null;
        }

        subGroups.put(id, group);
        return group;
    }

    @Override
    public IViewGroup label(String label) {
        this.label = label;
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.emir.rcp.views.ep.IView#icon(java.lang.String)
     */
    @Override
    public IViewGroup icon(String iconPath, ResourceManager rmgr) {

        mResourceManager = rmgr;

        long start = System.currentTimeMillis();

        if (iconPath != null && !iconPath.isEmpty()) {
            URL iconURL = mResourceManager != null ? mResourceManager.getResource(iconPath)
                    : ResourceManager.get(getClass()).getResource(iconPath);
            if (iconURL != null) {
                int iconSize = mIconSize < 0 ? IconManager.preferedSmallIconSize() : mIconSize;
                icon = IconManager.getIcon(iconURL, iconSize);
            }
        }

        long delta = System.currentTimeMillis() - start;

        if (delta > 50) {
            log.warn("Loading Icon [" + iconPath + "] for view [" + id
                    + "] takes a long time. If you want to use an image from another plugin, it is recommended to specify a certain ResourceManager.");
        }

        return this;
    }

    @Override
    public IViewDescriptor view(String id, IViewDescriptor view) {
        if (views.containsKey(id)){
            log.error("A view with id [" + id + "] already exists.");
            return null;
        }

        views.put(id, (ViewDescriptor) view);

        log.debug("View with id [" + id + "] added.");

        return view;
    }

    @Override
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

        ViewDescriptor view = new ViewDescriptor(id, viewClass, plugin);

        views.put(id, view);

        log.debug("View with id [" + id + "] added.");

        return view;
    }

    @Override
    public String toString() {
        if (label != null) {
            return label;
        }

        return id;
    }

    public Map<String, ViewDescriptor> getViews() {
        return views;
    }

    public Map<String, ViewGroup> getSubGroups() {
        return subGroups;
    }

    public String getLabel() {
        return label;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public String getId() {
        return id;
    }
}
