package de.emir.rcp.menu.ep;

import de.emir.rcp.commands.ep.ICommandDescriptor;
import de.emir.rcp.menu.AbstractDynamicMenuProvider;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public interface IMenu {

    /**
     * Creates a new separator with a given identifier
     * 
     * @param id
     *            A unique identifier (Within all menu entries with the same parent element)
     * @return A representation of the new separator
     */
    ISeparator separator(String id);

    /**
     * Creates a new menu. Menus should never be leafs within a menu tree. Use menuItem() to create a menu leaf.
     * 
     * @param id
     *            A unique identifier (Within all menu entries with the same parent element)
     * @return A representation of the new menu
     */
    IMenu menu(String id);

    /**
     * Creates a new menu. Menus should never be leafs within a menu tree. Use menuItem() to create a menu leaf.
     * 
     * @param id
     *            A unique identifier (Within all menu entries with the same parent element)
     * @param label
     *            The menu label
     * @return A representation of the new menu
     */
    IMenu menu(String id, String label);

    IDynamicMenu dynamicMenu(String id, AbstractDynamicMenuProvider p);

    /**
     * Creates a new menu item. MenuItems are leafs within a menu tree, triggering commands
     * 
     * @param id
     *            A unique identifier (Within all menu entries with the same parent element)
     * @param cmd
     *            The command to be triggered
     * @return A representation of the new menu item
     */
    IMenuItem menuItem(String id, ICommandDescriptor cmd);

    /**
     * Creates a new menu item. MenuItems are leafs within a menu tree, triggering commands
     * 
     * @param id
     *            A unique identifier (Within all menu entries with the same parent element)
     * @param cmd
     *            The command to be triggered, identified by its id
     * @return A representation of the new menu item
     */
    IMenuItem menuItem(String id, String commandID);

    /**
     * Moves this menu entry below a specified entry
     * 
     * @param siblingID
     *            The identifier of another menu entry. Attention: The other menu entry must have the same parent
     *            element as this one.
     * @return This menu entry
     */
    IMenu after(String siblingID);

    /**
     * Moves this menu entry over a specified entry
     * 
     * @param siblingID
     *            The identifier of another menu entry. Attention: The other menu entry must have the same parent
     *            element as this one.
     * @return This menu entry
     */
    IMenu before(String siblingID);

    /**
     * Defines an icon for this menu entry
     * 
     * @param iconPath
     *            Relative path to the icon file (e.g. from resources folder).
     * @return
     */
    IMenu icon(String iconPath);

    /**
     * Specifies an icon for this New File Wizard.
     * 
     * @param path
     * @param rmgr
     *            ResourceManager used to locate the icon
     * @return
     */
    IMenu icon(String path, ResourceManager rmgr);

    /**
     * Defines the size of the icon (if set)
     * 
     * @param size
     * @return
     */
    IMenu iconSize(int size);

    /**
     * Creates a new radio group. A radio group contains several radio buttons representing a selectable option.
     * 
     * @param id
     *            A unique identifier
     * @param type
     *            Define a data type handled by these radio buttons. The specified commands handler class has to be of
     *            type AbstractRadioGroupCommand. If the user selects an option of the RadioGroup, its value is passed
     *            to the command. Selectable options can be defined within the RadioGroup (See IRadioGroup).
     * @param commandID
     *            The unique identifier of the command. Commands handler class has to extend type
     *            AbstractRadioGroupCommand
     * @return A representation of the new radio group
     */
    <T> IRadioGroup<T> radioGroup(String id, Class<? extends T> type, String commandID);

    /**
     * Creates a new radio group. A radio group contains several radio buttons representing a selectable option.
     * 
     * @param id
     *            A unique identifier
     * @param type
     *            Define a data type handled by these radio buttons. The specified commands handler class has to be of
     *            type AbstractRadioGroupCommand. If the user selects an option of the RadioGroup, its value is passed
     *            to the command. Selectable options can be defined within the RadioGroup (See IRadioGroup).
     * @param commandID
     *            The command. Commands handler class has to extend type AbstractRadioGroupCommand
     * @return A representation of the new radio group
     */
    <T> IRadioGroup<T> radioGroup(String id, Class<? extends T> type, ICommandDescriptor cmd);

    /**
     * Creates a new menu. Menus should never be leafs within a menu tree. Use menuItem() to create a menu leaf.
     * 
     * @param id
     *            A unique identifier (Within all menu entries with the same parent element)
     * @param label
     *            The menu label
     * @param tooltip
     *            The menu tooltip
     * @return A representation of the new menu
     */
    IMenu menu(String id, String label, String tooltip);

}
