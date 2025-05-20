package de.emir.rcp;

import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.rcp.commands.ep.CommandExtensionPoint;
import de.emir.rcp.commands.ep.ICommandDescriptor;
import de.emir.rcp.editor.model.FormEditorPartManager;
import de.emir.rcp.editor.model.IFormEditorPart;
import de.emir.rcp.editor.text.TextEditor;
import de.emir.rcp.editors.ep.EditorExtensionPoint;
import de.emir.rcp.editors.ep.IEditor;
import de.emir.rcp.ids.Basic;
import de.emir.rcp.ids.GVBasic;
import de.emir.rcp.keybindings.ep.KeyBindingExtensionPoint;
import de.emir.rcp.manager.NewFileWizardManager;
import de.emir.rcp.manager.OperationManager;
import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.menu.ep.IMenu;
import de.emir.rcp.menu.ep.IRadioGroup;
import de.emir.rcp.menu.ep.MenuExtensionPoint;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.properties.provider.editor.AttributeEditorProvider;
import de.emir.rcp.properties.provider.editor.ComplexTypeEditorProvider;
import de.emir.rcp.properties.provider.editor.ListEditorProvider;
import de.emir.rcp.properties.provider.editor.MeasurementEditorProvider;
import de.emir.rcp.properties.provider.editor.ReferenceEditorProvider;
import de.emir.rcp.properties.provider.editor.UnitEditorProvider;
import de.emir.rcp.properties.provider.property.NamedElementPropertyProvider;
import de.emir.rcp.properties.provider.property.UStructuralFeaturePropertyProvider;
import de.emir.rcp.properties.ui.editors.CoordinateSequenceEditor;
import de.emir.rcp.settings.ConsoleSettingsPage;
import de.emir.rcp.settings.SystemSettingsPage;
import de.emir.rcp.settings.WorkspaceSettingsPage;
import de.emir.rcp.settings.ep.SettingsPageExtensionPoint;
import de.emir.rcp.views.console.ConsoleView;
import de.emir.rcp.views.console.cmd.ClearConsoleCommand;
import de.emir.rcp.views.console.cmd.LockTailingCommand;
import de.emir.rcp.views.console.cmd.SetClassLogLevelCommand;
import de.emir.rcp.views.console.cmd.SetLogLevelCommand;
import de.emir.rcp.views.ep.IViewGroup;
import de.emir.rcp.views.ep.ViewExtensionPoint;
import de.emir.rcp.views.operations.OperationView;
import de.emir.rcp.views.properties.PropertyView;
import de.emir.rcp.views.workspace.WorkspaceView;
import de.emir.rcp.views.workspace.cmd.*;
import de.emir.rcp.wizards.cmd.OpenNewFileWizardCommand;
import de.emir.rcp.wizards.ep.INewFileWizardCategory;
import de.emir.rcp.wizards.ep.NewFileWizardExtensionPoint;
import de.emir.rcp.wizards.types.file.NewFileWizardBasicFileInformation;
import de.emir.rcp.wizards.types.folder.NewFileWizardBasicFolderInformation;
import de.emir.rcp.wizards.types.ucore.NewFileWizardUCoreModelInformation;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import java.awt.Component;
import org.apache.logging.log4j.Level;

import java.io.File;
import javax.swing.Icon;

public class GeneralViewsPlugin extends AbstractUIPlugin {
	private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager
			.getLogger(GeneralViewsPlugin.class);
	
	@Override
	public void registerExtensionPoints() {
		ExtensionPointManager.registerExtensionPoint(GVBasic.EXT_POINT_NEW_FILE_WIZ,
				NewFileWizardManager.getNewFileWizardExtensionPoint());
		ExtensionPointManager.registerExtensionPoint(GVBasic.EXT_POINT_PROPERTY_MANAGER, PropertyManager.getInstance());
		ExtensionPointManager.registerExtensionPoint(GVBasic.EXT_POINT_OPERATION_MANAGER, new OperationManager());
		try {
			ExtensionPointManager.registerExtensionPoint(FormEditorPartManager.FORM_PROVIDER_EXTENSIONPOINT_ID,
				new FormEditorPartManager());
		} catch (IllegalAccessException e) {
			LOG.error(e);
		}
	}

        public static IFormEditorPart createCoordinateSequenceEditorPartProvider() {
        return new IFormEditorPart() {
            @Override
            public Component createComposite(Pointer pointer) {
                Object value = pointer.getValue();
                if (value instanceof CoordinateSequence cs) {
                    CoordinateSequenceEditor cse = new CoordinateSequenceEditor();
                    IProperty<CoordinateSequence> prop = new GenericProperty<>("CoordinateSequence", "", true, cs);
                    cse.setProperty(prop);
                    return cse.getEditor();
                }
                return null;
            }

            @Override
            public Icon getIcon() {
                return IconManager.getIcon(IconManager.class, "icons/emiricons/32/edit_coseq.png", IconManager.preferedSmallIconSize()); 
            }

            @Override
            public String getName() {
                return "CoSeq Editor";
            }
        };
    }

    
	@Override
	public void addExtensions() {
		ResourceManager rmgr = ResourceManager.get(getClass()); //used to load icons
		
		ViewExtensionPoint viewEP = ExtensionPointManager.getExtensionPoint(ViewExtensionPoint.class);

		IViewGroup group = viewEP.group(GVBasic.GENERAL_VIEWS_GROUP_ID)
				.label("General")
				.icon("icons/emiricons/32/folder_open.png", rmgr);

		group.view(ConsoleView.UNIQUE_ID, ConsoleView.class)
				.label("Console")
				.icon("icons/emiricons/32/monitoring.png", rmgr);
		group.view(WorkspaceView.UNIQUE_ID, WorkspaceView.class)
				.label("Workspace")
				.icon("icons/emiricons/32/work.png", rmgr);
		group.view(PropertyView.UNIQUE_ID, PropertyView.class)
				.label("Properties")
				.icon("icons/emiricons/32/design_services.png", rmgr);
		group.view(OperationView.UNIQUE_ID, OperationView.class)
				.label("Operations")
				.icon("icons/emiricons/32/architecture.png", rmgr);

		// Editors
		EditorExtensionPoint editorEP = ExtensionPointManager.getExtensionPoint(EditorExtensionPoint.class);
		IEditor textEditor = editorEP
				.editor("de.emir.rcp.swingrcp.editors.TextEditor", TextEditor.class, "Text Editor")
				.fileExtension("xml")
				.icon("icons/emiricons/32/insert_drive_file.png", rmgr);
        
		CommandExtensionPoint cmdEP = ExtensionPointManager.getExtensionPoint(CommandExtensionPoint.class);

		// Console Commands
		ICommandDescriptor cmd = cmdEP.command(
				GVBasic.CMD_CONSOLE_LOCK_TAILING, "Lock / Unlock Tailing", new LockTailingCommand()
		);
		ICommandDescriptor cmdClear = cmdEP.command(
				GVBasic.CMD_CONSOLE_CLEAR, "Clear Console", new ClearConsoleCommand()
		);
		ICommandDescriptor cmdLogLevel = cmdEP.command(
				GVBasic.CMD_CONSOLE_LOG_LEVEL, "Set Log Level", new SetLogLevelCommand()
		);
		ICommandDescriptor cmdClassLogLevel = cmdEP.command(
				GVBasic.CMD_CONSOLE_CLASS_LOG_LEVEL, "Set Class Log Level" ,new SetClassLogLevelCommand()
		);

		// Workspace Commands
		ICommandDescriptor cmdOpenFiles = cmdEP.command(
				GVBasic.CMD_WORKSPACE_OPEN_FILE, "Open File(s)", new OpenFilesCommand()
		);
		ICommandDescriptor cmdOpenFilesWith = cmdEP.command(
				GVBasic.CMD_WORKSPACE_OPEN_WITH, "Open File(s) With...", new OpenFilesWithCommand()
		);
		ICommandDescriptor cmdDeleteFiles = cmdEP.command(
				GVBasic.CMD_WORKSPACE_DELETE, "Delete File(s)", new DeleteFilesCommand()
		);
		ICommandDescriptor cmdCopyFiles = cmdEP.command(
				GVBasic.CMD_WORKSPACE_COPY, "Copy File(s)", new CopyFilesCommand()
		);
		ICommandDescriptor cmdPasteFiles = cmdEP.command(
				GVBasic.CMD_WORKSPACE_PASTE, "Paste File(s)", new PasteFilesCommand()
		);
		ICommandDescriptor cmdRefresh = cmdEP.command(
				GVBasic.CMD_WORKSPACE_REFRESH, "Refresh Workspace", new RefreshWorkspaceCommand()
		);
		ICommandDescriptor cmdLinkWithEditor = cmdEP.command(
				GVBasic.CMD_WORKSPACE_LINK_WITH_EDITOR, "Link with Editor", new LinkWithEditorCommand()
		);

		ICommandDescriptor cmdOpenNewFileWizard = cmdEP.command(
				GVBasic.CMD_NEW_FILE_WIZ_OPEN, "New", new OpenNewFileWizardCommand()
		);
		ICommandDescriptor cmdOpenNewFileWizard_File = cmdEP.command(
				GVBasic.CMD_NEW_FILE_WIZ_NEW_FILE, "New File", new OpenNewFileWizardCommand(GVBasic.NEW_WIZ_TYPE_FILE)
		);
		
		ICommandDescriptor cmdOpenNewFileWizard_Folder = cmdEP.command(
				GVBasic.CMD_NEW_FILE_WIZ_NEW_FOLDER, "New Folder", new OpenNewFileWizardCommand(GVBasic.NEW_WIZ_TYPE_FOLDER)
		);


		ICommandDescriptor cmdShowInSystemExplorer = cmdEP.command(
				GVBasic.CMD_SHOW_IN_SYSTEM_EXPLORER, "Show In System Explorer", new ShowInSystemExplorerCommand()
		);

		MenuExtensionPoint menuEP = ExtensionPointManager.getExtensionPoint(MenuExtensionPoint.class);

		// New File Wizards
		NewFileWizardExtensionPoint wizEP = ExtensionPointManager.getExtensionPoint(NewFileWizardExtensionPoint.class);

		INewFileWizardCategory generalFileCat = wizEP.category(GVBasic.NEW_FILE_WIZ_GENERAL_CAT).label("General");

		wizEP.type(GVBasic.NEW_WIZ_TYPE_FILE, NewFileWizardBasicFileInformation.class)
				.icon("icons/emiricons/32/file.png", rmgr)
				.label("File")
				.inCategory(generalFileCat);

		wizEP.type(GVBasic.NEW_WIZ_TYPE_FOLDER, NewFileWizardBasicFolderInformation.class)
				.icon("icons/emiricons/32/folder.png", rmgr)
				.label("Folder")
				.inCategory(generalFileCat);

		INewFileWizardCategory ucoreCat = wizEP.category(GVBasic.NEW_FILE_WIZ_UCORE_CAT)
				.label("UCore");

		wizEP.type(GVBasic.NEW_WIZ_TYPE_UCORE_MODEL, NewFileWizardUCoreModelInformation.class)
				.iconSize(IconManager.preferedSmallIconSize())
				.icon("icons/emiricons/32/organization.png", rmgr)
				.label("UCore Model Instance")
				.inCategory(ucoreCat);

		// Menu: Files & Editors
		menuEP.menuContribution(ConsoleView.TOOLBAR_ID).menuItem("lockunlocktailing", cmd)
				.icon("icons/emiricons/32/lock.png", rmgr);
		menuEP.menuContribution(ConsoleView.TOOLBAR_ID).menuItem("clearconsole", cmdClear)
				.icon("icons/emiricons/32/delete_forever.png", rmgr);
		menuEP.menuContribution(ConsoleView.TOOLBAR_ID).menuItem("setLogLevel", cmdClassLogLevel)
				.icon("icons/emiricons/32/filter_alt.png", rmgr);

		IRadioGroup<Level> levelGroup = menuEP.menuContribution(ConsoleView.TOOLBAR_ID)
				.menu("consoleLogLevel")
				.icon("icons/emiricons/32/info.png", rmgr)
				.radioGroup("logLevelGroup", Level.class, cmdLogLevel);
		levelGroup.option("Info", Level.INFO)
				.option("Warning", Level.WARN)
				.option("Error", Level.ERROR)
				.option("Debug", Level.DEBUG)
				.option("Trace", Level.TRACE);

		IMenu newMenu = menuEP.menuContribution(WorkspaceView.POPUP_MENU_ID)
				.menu(GVBasic.MENU_NEW_FILE_MENU, "New");

		newMenu.menuItem(GVBasic.MENU_NEW_FILE_MENU_FILE, cmdOpenNewFileWizard_File)
				.label("File")
				.icon("icons/emiricons/32/file.png", rmgr);
		newMenu.menuItem(GVBasic.MENU_NEW_FILE_MENU_FOLDER, cmdOpenNewFileWizard_Folder)
				.label("Folder")
				.icon("icons/emiricons/32/folder.png", rmgr);

		newMenu.separator(GVBasic.MENU_NEW_FILE_MENU_AFTER_BASICS_SEP);

		newMenu.separator("newFileMenuOtherSeperator");

		newMenu.menuItem(GVBasic.MENU_NEW_FILE_MENU_OTHER, cmdOpenNewFileWizard)
				.label("Other...");

		menuEP.menuContribution(WorkspaceView.POPUP_MENU_ID).separator("afterNewSeparator");

		menuEP.menuContribution(WorkspaceView.POPUP_MENU_ID).menuItem("openFilesMenuItem", cmdOpenFiles)
				.label("Open");
		menuEP.menuContribution(WorkspaceView.POPUP_MENU_ID).menuItem("openFilesWithMenuItem", cmdOpenFilesWith)
				.label("Open With...");
		menuEP.menuContribution(WorkspaceView.POPUP_MENU_ID).menu(GVBasic.MENU_SHOW_IN, "Show In")
				.menuItem("showInSystemExplorer", cmdShowInSystemExplorer)
				.label("System Explorer").icon("icons/emiricons/32/file_system.png", rmgr);

		menuEP.menuContribution(WorkspaceView.POPUP_MENU_ID)
				.separator("afterOpenSeparator");
		menuEP.menuContribution(WorkspaceView.POPUP_MENU_ID)
				.menuItem("copyFilesMenuItem", cmdCopyFiles)
				.label("Copy")
				.icon("icons/emiricons/32/content_copy.png", rmgr);
		menuEP.menuContribution(WorkspaceView.POPUP_MENU_ID)
				.menuItem("pasteFilesMenuItem", cmdPasteFiles)
				.label("Paste")
				.icon("icons/emiricons/32/content_paste.png", rmgr);
		menuEP.menuContribution(WorkspaceView.POPUP_MENU_ID).separator("afterPasteSeparator");
		menuEP.menuContribution(WorkspaceView.POPUP_MENU_ID)
				.menuItem("deleteFilesMenuItem", cmdDeleteFiles)
				.label("Delete")
				.icon("icons/emiricons/32/delete.png", rmgr);

		menuEP.menuContribution(WorkspaceView.TOOLBAR_ID)
				.menuItem("refreshWorkspace", cmdRefresh)
				.icon("icons/emiricons/32/sync.png", rmgr);
		menuEP.menuContribution(WorkspaceView.TOOLBAR_ID)
				.menuItem("linkWithEditor", cmdLinkWithEditor)
				.icon("icons/emiricons/32/compare_arrows.png", rmgr);

		// Add Key Bindings
		KeyBindingExtensionPoint kbEP = ExtensionPointManager.getExtensionPoint(KeyBindingExtensionPoint.class);
		kbEP.inView(WorkspaceView.UNIQUE_ID, "DELETE", cmdDeleteFiles);
		kbEP.inView(WorkspaceView.UNIQUE_ID, "ENTER", cmdOpenFiles);
		kbEP.inView(WorkspaceView.UNIQUE_ID, "F5", cmdRefresh);

		kbEP.inEditor(textEditor, "CTRL+S", Basic.CMD_SAVE);
		kbEP.inEditor(textEditor, "CTRL+Z", Basic.CMD_UNDO);
		kbEP.inEditor(textEditor, "CTRL+Y", Basic.CMD_REDO);

//		kbEP.global("CTRL+N", GVBasic.CMD_NEW_FILE_WIZ_OPEN);

		// Settings Pages
		SettingsPageExtensionPoint settingsEP = ExtensionPointManager
				.getExtensionPoint(SettingsPageExtensionPoint.class);

		settingsEP.page(Basic.SETTINGS_GENERAL_SETTINGS_PAGE)
				.page(GVBasic.SETTINGS_WORKSPACE_SETTINGS_PAGE, WorkspaceSettingsPage.class)
				.label("Workspace")
				.icon("icons/emiricons/32/work.png", rmgr);
		
		settingsEP.page(Basic.SETTINGS_GENERAL_SETTINGS_PAGE)
				.page(GVBasic.SETTINGS_SYSTEM_SETTINGS_PAGE, SystemSettingsPage.class)
				.label("System")
				.icon("icons/emiricons/32/settings.png", rmgr);
		
		settingsEP.page(Basic.SETTINGS_GENERAL_SETTINGS_PAGE)
			.page(GVBasic.SETTINGS_CONSOLE_SETTINGS_PAGE, ConsoleSettingsPage.class)
			.label("Console")
			.icon("icons/emiricons/32/monitoring.png", rmgr);
		
        //Register Property extensions; the PropertyManager is the extensionpoint
        //register the property provider
        PropertyManager.getInstance().register(new UStructuralFeaturePropertyProvider());
   		PropertyManager.getInstance().register(new NamedElementPropertyProvider());
  		//register property editor (provider)
  		PropertyManager.getInstance().register(new AttributeEditorProvider());
  		PropertyManager.getInstance().register(new MeasurementEditorProvider());
  		PropertyManager.getInstance().register(new UnitEditorProvider());
  		PropertyManager.getInstance().register(new ComplexTypeEditorProvider());
        PropertyManager.getInstance().register(new ListEditorProvider());
		PropertyManager.getInstance().register(999, new ReferenceEditorProvider());
        
        FormEditorPartManager fepm = (FormEditorPartManager) ExtensionPointManager.getExtensionPoint(FormEditorPartManager.FORM_PROVIDER_EXTENSIONPOINT_ID);
        fepm.registerEditorPart(SpatialPackage.Literals.CoordinateSequence, createCoordinateSequenceEditorPartProvider());  
	}

	
	/**
	 * Utility funtion to return the current path for the workspace (if any)
	 * @return a file pointing to the workspace directory or null
	 */
	public static File getWorkspaceDirectory() {
		IProperty<String> property = PropertyStore.getContext(WorkspaceView.PROPERTY_CONTEXT).getProperty(WorkspaceView.WORKSPACE_LOCATION_PROPERTY);
		if (property == null || property.getValue() == null)
			return null;
		return new File(property.getValue());
	}

}
