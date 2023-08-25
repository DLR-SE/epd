package de.emir.rcp.menu.ep;

import java.net.URL;

import javax.swing.ImageIcon;

import de.emir.rcp.commands.AbstractCheckableCommand;
import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.commands.ep.ICommandDescriptor;
import de.emir.rcp.manager.CommandManager;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public class MenuItem extends MenuEntry implements IMenuItem {

    private String id;

    private String label;
    private String tooltip;
    private String iconPath;

    private MenuItemType menuItemType = MenuItemType.PUSH;

    private ResourceManager mResourceManager;
    private int mIconSize = -1;

    private AbstractCommand mCommand;

    private String commandID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AbstractCommand getCommand() {

        if (mCommand == null) {
            CommandManager cm = ServiceManager.get(CommandManager.class);
            mCommand = cm.getCommand(commandID);
            setMenuItemType();
        }

        return mCommand;
    }

    public ImageIcon getIcon() {
        if (iconPath != null && !iconPath.isEmpty()) {
            URL iconURL = mResourceManager != null ? mResourceManager.getResource(iconPath)
                    : ResourceManager.get(getClass()).getResource(iconPath);
            if (iconURL != null) {
                int iconSize = mIconSize < 0 ? IconManager.preferedSmallIconSize() : mIconSize;
                return IconManager.getIcon(iconURL, iconSize);
            }
        }
        return null;
    }

    public MenuItem(String id, String commandID) {
        this.id = id;
        this.commandID = commandID;

    }

    public MenuItem(String id, ICommandDescriptor cmd) {
        this.id = id;
        this.commandID = cmd.getId();

    }

    private void setMenuItemType() {
        menuItemType = mCommand instanceof AbstractCheckableCommand ? MenuItemType.TOGGLE : MenuItemType.PUSH;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.menu.ep.IMenuItem#label(java.lang.String)
     */
    @Override
    public IMenuItem label(String label) {
        this.label = label;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.menu.ep.IMenuItem#tooltip(java.lang.String)
     */
    @Override
    public IMenuItem tooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.menu.ep.IMenuItem#icon(java.lang.String)
     */
    @Override
    public IMenuItem icon(String path) {

        ResourceManager rmgr = ResourceManager.get(getPlugin());

        return icon(path, rmgr);
    }

    @Override
    public IMenuItem icon(String path, ResourceManager rmgr) {
        this.iconPath = path;
        mResourceManager = rmgr;
        return this;
    }

    public String getLabel() {
        return label;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.menu.ep.IMenuItem#after(java.lang.String)
     */
    @Override
    public IMenuItem after(String siblingID) {
        after = siblingID;
        before = null;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.menu.ep.IMenuItem#before(java.lang.String)
     */
    @Override
    public IMenuItem before(String siblingID) {
        before = siblingID;
        after = null;
        return this;
    }

    public MenuItemType getMenuItemType() {

        if (mCommand == null) {
            getCommand();
            setMenuItemType();
        }

        return menuItemType;
    }

    @Override
    public IMenuItem iconSize(int size) {
        mIconSize = size;
        return this;
    }

    public String getTooltip() {
        return tooltip;
    }

}
