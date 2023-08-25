package de.emir.rcp.settings.ep;

import de.emir.rcp.settings.AbstractSettingsPage;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public interface ISettingsPageDescriptor extends ISettingsPageNodeDescriptor {

    ISettingsPageDescriptor label(String label);

    ISettingsPageDescriptor icon(String iconPath);

    /**
     * Specifies an icon for this SettingsPage
     * 
     * @param path
     * @param rmgr
     *            ResourceManager used to locate the icon
     * @return
     */
    ISettingsPageDescriptor icon(String path, ResourceManager rmgr);

    /**
     * Defines the size of the icon (if set)
     * 
     * @param size
     * @return
     */
    ISettingsPageDescriptor iconSize(int size);

    void pageClass(Class<? extends AbstractSettingsPage> pageClass);

}
