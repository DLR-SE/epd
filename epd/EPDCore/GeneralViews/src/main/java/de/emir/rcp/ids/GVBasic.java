package de.emir.rcp.ids;

public class GVBasic {

	// View Group

	public static final String GENERAL_VIEWS_GROUP_ID = "de.emir.rcp.general.GeneralViewsGroup";

	// Extension Points

	public static final String EXT_POINT_NEW_FILE_WIZ = "NewFileWizardExtensionPoint";
	public static final String EXT_POINT_PROPERTY_MANAGER = "PropertyManagerExtensionPoint";
	public static final String EXT_POINT_OPERATION_MANAGER = "OperationManagerExtensionPoint";
	
	
	// Commands --------------------------------------------------------------------------------------

	// Workspace View

	public static final String CMD_WORKSPACE_REFRESH = "de.emir.rcp.workspace.refresh";
	public static final String CMD_WORKSPACE_LINK_WITH_EDITOR = "de.emir.rcp.workspace.link";
	public static final String CMD_WORKSPACE_OPEN_FILE = "de.emir.rcp.workspace.openFile";
	public static final String CMD_WORKSPACE_OPEN_WITH = "de.emir.rcp.workspace.openFileWith";
	public static final String CMD_WORKSPACE_DELETE = "de.emir.rcp.workspace.delete";
	public static final String CMD_WORKSPACE_COPY = "de.emir.rcp.workspace.copy";
	public static final String CMD_WORKSPACE_PASTE = "de.emir.rcp.workspace.paste";
	public static final String CMD_SHOW_IN_SYSTEM_EXPLORER = "de.emir.rcp.workspace.showInSystemExplorer";

	// Console View ------------------------------------------------------------------------------------
	public static final String CMD_CONSOLE_LOCK_TAILING = "de.emir.rcp.console.locktailing";
	public static final String CMD_CONSOLE_CLEAR = "de.emir.rcp.console.clear";
	public static final String CMD_CONSOLE_COPY = "de.emir.rcp.console.copy";
	public static final String CMD_CONSOLE_LOG_LEVEL = "de.emir.rcp.console.loglevel";
	public static final String CMD_CONSOLE_CLASS_LOG_LEVEL = "de.emir.rcp.console.classloglevel";

	// New File Wizard ---------------------------------------------------------------------------------

	public static final String CMD_NEW_FILE_WIZ_OPEN = "de.emir.rcp.newFileWiz.open";
	public static final String CMD_NEW_FILE_WIZ_NEW_FILE = "de.emir.rcp.newFileWiz.newFile";
	public static final String CMD_NEW_FILE_WIZ_NEW_FOLDER = "de.emir.rcp.newFileWiz.newFolder";
	public static final String CMD_NEW_FILE_WIZ_NEW_UCORE_MDL_INSTANCE = "de.emir.rcp.newFileWiz.newUCoreModelInstance";

	// Menu Items ---------------------------------------------------------------------------------------



	public static final String MENU_SHOW_IN = "showInMenu";
	
	
	// New File

	public static final String MENU_NEW_FILE_MENU = "newFileMenu";
	public static final String MENU_NEW_FILE_MENU_FILE = "newFileMenuFile";
	public static final String MENU_NEW_FILE_MENU_FOLDER = "newFileMenuFolder";
	public static final String MENU_NEW_FILE_MENU_AFTER_BASICS_SEP = "newFileMenuAfterBasicsSeparator";
	public static final String MENU_NEW_FILE_MENU_UCORE_MDL_INSTANCE = "newFileMenuUCoreModelInstance";
	public static final String MENU_NEW_FILE_MENU_OTHER = "newFileMenuOther";
	// Settings Pages ------------------------------------------------------------------------------------

	public static final String SETTINGS_WORKSPACE_SETTINGS_PAGE = "de.emir.rcp.settingspage.workspace";
	public static final String SETTINGS_SYSTEM_SETTINGS_PAGE = "de.emir.rcp.settingspage.system";
	public static final String SETTINGS_CONSOLE_SETTINGS_PAGE = "de.emir.rcp.settingspage.console";

	// New File Wizard - File Types ----------------------------------------------------------------------

	public static final String NEW_WIZ_TYPE_FILE = "file.type.file";
	public static final String NEW_WIZ_TYPE_FOLDER = "file.type.folder";
	public static final String NEW_WIZ_TYPE_UCORE_MODEL = "file.type.ucoreModel";
	
	// New File Wizard - Categories
	
	public static final String NEW_FILE_WIZ_GENERAL_CAT = "newFileWizardGeneralCategory";
	public static final String NEW_FILE_WIZ_UCORE_CAT = "newFileWizardUCoreCategory";

}
