package de.emir.rcp.ids;

/**
 * Basic identifiers
 * 
 * @author fklein
 *
 */
public class Basic {

    public static final String CMD_EXIT = "de.emir.rcp.swingrcp.exitCommand";
    public static final String CMD_UNDO = "de.emir.rcp.swingrcp.UndoCommand";
    public static final String CMD_REDO = "de.emir.rcp.swingrcp.RedoCommand";
    public static final String CMD_SAVE = "de.emir.rcp.swingrcp.SaveCommand";
    public static final String CMD_SETTINGS = "de.emir.rcp.swingrcp.settingsCommand";
    public static final String CMD_OPEN_VIEW = "de.emir.rcp.swingrcp.openViewCommand";
    public static final String CMD_LOCK_UNLOCK_LAYOUT = "de.emir.rcp.swingrcp.lockUnlockLayoutCommand";

    public static final String CMD_LOAD_LAYOUT = "de.emir.rcp.swingrcp.loadLayoutCommand";
    public static final String CMD_SAVE_LAYOUT = "de.emir.rcp.swingrcp.saveLayoutCommand";
    public static final String CMD_IMPORT_LAYOUT = "de.emir.rcp.swingrcp.importLayoutCommand";
    public static final String CMD_EXPORT_LAYOUT = "de.emir.rcp.swingrcp.exportLayoutCommand";

    public static final String MENU_IDENTIFIER = "menu:";
    public static final String TOOLBAR_IDENTIFIER = "toolbar:";
    public static final String POPUP_IDENTIFIER = "popup:";
    public static final String MENU_MAIN_MENU = MENU_IDENTIFIER + "main";
    public static final String MENU_MAIN_TOOLBAR = TOOLBAR_IDENTIFIER + "main";
    public static final String PERSPECTIVE_MENU = MENU_IDENTIFIER + "perspective";

    public static final String SETTINGS_GENERAL_SETTINGS_PAGE = "de.emir.rcp.swingrcp.settings.general";

    // Basic Extension Points
    public static final String MENU_EXT_POINT = "MenuExtensionPoint";
    public static final String CMD_EXT_POINT = "CommandExtensionPoint";
    public static final String SETTINGS_PAGE_EXT_POINT = "SettingsPageExtensionPoint";
    public static final String KEY_BINDING_EXT_POINT = "KeyBindingExtensionPoint";
    public static final String VIEW_EXT_POINT = "ViewExtensionPoint";
    public static final String EDITOR_EXT_POINT = "EditorExtensionPoint";
    public static final String STATUS_BAR_EXT_POINT = "StatusBarExtensionPoint";

    public static final String KEY_BINDING_PROP_CTX = "keyBindingPropertyContext";
    public static final String KEY_BINDING_PROP = "keyBindings";

    public static final String DEV_PROP_CTX = "de.emir.rcp.dev.DevToolPropertiesContext";
    public static final String PROP_DEV_SHOW_MENU_CONTRIBUTIONS = "de.emir.rcp.dev.DevToolPropertiesContext";

    // Progress View
    public static final String PROGRESS_VIEW_ID = "uiplugincore.ProgressView";
    public static final String CMD_SHOW_PROGRESS_VIEW = "uiplugincore.cmd.ShowProgressView";

    // help
    public static final String CMD_OPEN_ABOUT = "de.emir.rcp.swingrcp.aboutCommand";

}
