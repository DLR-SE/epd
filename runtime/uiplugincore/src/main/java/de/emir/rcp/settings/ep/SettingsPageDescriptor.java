package de.emir.rcp.settings.ep;

import java.net.URL;

import javax.swing.ImageIcon;

import de.emir.rcp.settings.AbstractSettingsPage;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public class SettingsPageDescriptor extends SettingsPageNodeDescriptor implements ISettingsPageDescriptor {

    private String id;
    private Class<? extends AbstractSettingsPage> pageClass;
    private String label;
    private String mIconPath;
    private int mIconSize = -1;
    private ResourceManager mResourceManager;
    private AbstractUIPlugin plugin;

    public SettingsPageDescriptor(String id, Class<? extends AbstractSettingsPage> pageClass) {
        this.id = id;
        this.pageClass = pageClass;
    }

    public String getId() {
        return id;
    }

    public Class<? extends AbstractSettingsPage> getPageClass() {
        return pageClass;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.settings.ep.ISettingsPage#label(java.lang.String)
     */
    @Override
    public ISettingsPageDescriptor label(String label) {
        this.label = label;
        return this;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public void pageClass(Class<? extends AbstractSettingsPage> pageClass) {
        this.pageClass = pageClass;
    }

    @Override
    public ISettingsPageDescriptor icon(String path) {
        return icon(path, ResourceManager.get(getPlugin()));
    }

    @Override
    public ISettingsPageDescriptor icon(String path, ResourceManager rmgr) {
        mResourceManager = rmgr;
        this.mIconPath = path;
        return this;
    }

    @Override
    public ISettingsPageDescriptor iconSize(int size) {
        mIconSize = size;
        return this;
    }

    public ImageIcon getIcon() {

        if (mIconPath != null && !mIconPath.isEmpty()) {
            URL iconURL = mResourceManager != null ? mResourceManager.getResource(mIconPath)
                    : ResourceManager.get(getPageClass()).getResource(mIconPath);
            if (iconURL != null) {
                int iconSize = mIconSize < 0 ? IconManager.preferedSmallIconSize() : mIconSize;
                return IconManager.getIcon(iconURL, iconSize);
            }
        }
        return null;
    }

    public void setPlugin(AbstractUIPlugin plugin) {
        this.plugin = plugin;

    }

    public AbstractUIPlugin getPlugin() {
        return plugin;
    }

}
