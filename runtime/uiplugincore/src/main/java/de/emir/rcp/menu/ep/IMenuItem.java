package de.emir.rcp.menu.ep;

import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public interface IMenuItem {

    IMenuItem label(String label);

    IMenuItem icon(String path);

    /**
     * Associate an ResourceManager with the icon. The ResourceManager is used to locate the icon path
     * 
     * @param path
     * @param rmgr
     *            ResourceManager to locate the icon
     * @return
     */
    IMenuItem icon(String path, ResourceManager rmgr);

    /**
     * (Optional) Assign the size (in pixel) for the icon
     * 
     * @param size
     * @return
     */
    IMenuItem iconSize(int size);

    IMenuItem after(String siblingID);

    IMenuItem before(String siblingID);

    /**
     * Define a tooltip for this menu item. If no tooltip is set the label of the command is used
     * 
     * @param tooltip
     * @return
     */
    IMenuItem tooltip(String tooltip);

}
