package de.emir.rcp;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Path;

import javax.swing.*;

import de.emir.rcp.commands.ShowAboutCommand;
import de.emir.rcp.commands.basics.*;
import de.emir.rcp.commands.basics.perspective.*;
import de.emir.rcp.commands.ep.CommandExtensionPoint;
import de.emir.rcp.commands.ep.ICommandDescriptor;
import de.emir.rcp.events.ExtensionsLoadedEvent;
import de.emir.rcp.ids.Basic;
import de.emir.rcp.jobs.JobStatusBarElement;
import de.emir.rcp.manager.CommandManager;
import de.emir.rcp.manager.EventManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.menu.ep.IMenu;
import de.emir.rcp.menu.ep.MenuExtensionPoint;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.settings.basics.GeneralSettingsPage;
import de.emir.rcp.settings.basics.keybindings.KeyBindingSettingsPage;
import de.emir.rcp.settings.ep.SettingsPageExtensionPoint;
import de.emir.rcp.statusbar.ep.StatusBarExtensionPoint;
import de.emir.rcp.types.Rectangle;
import de.emir.rcp.views.basics.progress.ProgressView;
import de.emir.rcp.views.ep.ViewExtensionPoint;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public class UICorePlugin extends AbstractUIPlugin {

    @Override
    public void registerExtensionPoints() {

        PropertyStore.load();

        ExtensionPointManager.registerExtensionPoint(Basic.VIEW_EXT_POINT,
                PlatformUtil.getViewManager().getViewExtensionPoint());
        ExtensionPointManager.registerExtensionPoint(Basic.CMD_EXT_POINT,
                PlatformUtil.getCommandManager().getCommandExtensionPoint());
        ExtensionPointManager.registerExtensionPoint(Basic.MENU_EXT_POINT,
                PlatformUtil.getMenuManager().getMenuExtensionPoint());
        ExtensionPointManager.registerExtensionPoint(Basic.SETTINGS_PAGE_EXT_POINT,
                PlatformUtil.getSettingsPageManager().getSettingsPageExtensionPoint());
        ExtensionPointManager.registerExtensionPoint(Basic.KEY_BINDING_EXT_POINT,
                PlatformUtil.getKeyBindingManager().getKeyBindingExtensionPoint());

        ExtensionPointManager.registerExtensionPoint(Basic.EDITOR_EXT_POINT,
                PlatformUtil.getEditorManager().getEditorExtensionPoint());

        ExtensionPointManager.registerExtensionPoint(Basic.STATUS_BAR_EXT_POINT,
                PlatformUtil.getStatusBarManager().getStatusBarExtensionPoint());
    }

    @Override
    public void addExtensions() {

        // Add Commands
        CommandExtensionPoint cmdEP = ExtensionPointManager.getExtensionPoint(CommandExtensionPoint.class);

        cmdEP.command(Basic.CMD_EXIT, "Exit Application", new ExitCommand());
        cmdEP.command(Basic.CMD_SETTINGS, "Open Settings Dialog", new OpenSettingsDialogCommand());
        cmdEP.command(Basic.CMD_LOCK_UNLOCK_LAYOUT, "Lock/Unlock Layout", new LockUnlockLayoutCommand());
        cmdEP.command(Basic.CMD_LOAD_LAYOUT, "Load Layout", new LoadLayoutCommand());
        cmdEP.command(Basic.CMD_SAVE_LAYOUT, "Save Layout", new SaveLayoutCommand());
        cmdEP.command(Basic.CMD_IMPORT_LAYOUT, "Import Layout", new ImportLayoutCommand());
        cmdEP.command(Basic.CMD_EXPORT_LAYOUT, "Export Layout", new ExportLayoutCommand());
        cmdEP.command(Basic.CMD_OPEN_VIEW, "Open Show View Dialog", new OpenViewDialogCommand());
        cmdEP.command(Basic.CMD_UNDO, "Undo", new UndoCommand());
        cmdEP.command(Basic.CMD_REDO, "Redo", new RedoCommand());
        cmdEP.command(Basic.CMD_SAVE, "Save", new SaveCommand());
        cmdEP.command(Basic.CMD_SHOW_PROGRESS_VIEW, "Show Progress View", new ShowViewCommand(Basic.PROGRESS_VIEW_ID));

        // TODO: Remove here? - RCP only defines extension points
        // Add Menu Entries
        MenuExtensionPoint menuEP = ExtensionPointManager.getExtensionPoint(MenuExtensionPoint.class);

        menuEP.menuContribution(Basic.MENU_MAIN_MENU).menu("file", "File");

        menuEP.menuContribution("menu:main.file").menuItem("settings", Basic.CMD_SETTINGS).label("Settings...");
        menuEP.menuContribution("menu:main.file").separator("beforeExitSep").before("exit");
        menuEP.menuContribution("menu:main.file").menuItem("exit", Basic.CMD_EXIT).label("Exit");

        // Add Menu Entries: Edit
        menuEP.menuContribution(Basic.MENU_MAIN_MENU).menu("edit", "Edit");
        menuEP.menuContribution("menu:main.edit").menuItem("Undo", Basic.CMD_UNDO).label("Undo")
                .icon("icons/emiricons/32/undo.png");
        menuEP.menuContribution("menu:main.edit").menuItem("Redo", Basic.CMD_REDO).label("Redo")
                .icon("icons/emiricons/32/redo.png");
        menuEP.menuContribution("menu:main.edit").separator("undoredoseparator");

        IMenu layoutMenu = menuEP.menuContribution(Basic.MENU_MAIN_MENU).menu("layout", "Layout");
        layoutMenu.menuItem("lockunlock", Basic.CMD_LOCK_UNLOCK_LAYOUT).label("Unlock Layout");
        layoutMenu.menuItem("openview", Basic.CMD_OPEN_VIEW).label("Open View...");

        IMenu perspectiveMenu = layoutMenu.menu(Basic.PERSPECTIVE_MENU, "Layouts");
        ResourceManager manager = ResourceManager.get(UICorePlugin.class);
        Path layoutFolder = manager.getHomePath().resolve("layouts");

        IMenu defaultMenu = perspectiveMenu.menu("defaultlayoutmenu", "Default Layouts");
        defaultMenu.dynamicMenu("defaultlayout", new LayoutMenuProvider(layoutFolder.resolve("default").toFile()));

        IMenu savedMenu = perspectiveMenu.menu("savedlayoutmenu", "Saved Layouts");
        savedMenu.dynamicMenu("savedlayout", new LayoutMenuProvider(layoutFolder.toFile()));

        perspectiveMenu.separator("internallayouts");
        perspectiveMenu.menuItem("savelayout", Basic.CMD_SAVE_LAYOUT).label("Save");
        perspectiveMenu.menuItem("loadlayout", Basic.CMD_LOAD_LAYOUT).label("Load");
        perspectiveMenu.separator("externallayouts");
        perspectiveMenu.menuItem("importlayout", Basic.CMD_IMPORT_LAYOUT).label("Import");
        perspectiveMenu.menuItem("exportlayout", Basic.CMD_EXPORT_LAYOUT).label("Export");

        // Add Settings Pages
        SettingsPageExtensionPoint spEP = ExtensionPointManager.getExtensionPoint(SettingsPageExtensionPoint.class);
        spEP.page(Basic.SETTINGS_GENERAL_SETTINGS_PAGE, GeneralSettingsPage.class).label("General")
                .icon("icons/emiricons/32/home.png");

        spEP.page("keySettings", KeyBindingSettingsPage.class).label("Key Bindings")
                .icon("icons/emiricons/32/keyboard.png");

        // Status Bar

        StatusBarExtensionPoint sbEP = ExtensionPointManager.getExtensionPoint(StatusBarExtensionPoint.class);
        sbEP.element(JobStatusBarElement.class);

        ViewExtensionPoint viewEP = ExtensionPointManager.getExtensionPoint(ViewExtensionPoint.class);
        viewEP.view(Basic.PROGRESS_VIEW_ID, ProgressView.class).label("Progress")
                .icon("icons/emiricons/32/background_tasks.png");

        //DLR branding
        ICommandDescriptor showAboutCMD = cmdEP.command(
                Basic.CMD_OPEN_ABOUT,
                "About",
                new ShowAboutCommand()
        );

        IMenu helpMenu = menuEP.menuContribution(Basic.MENU_MAIN_MENU)
                .menu("helpMenu", "Help")
                .icon("icons/emiricons/32/help.png", manager)
                .after("layout");
        helpMenu.menuItem("showAbout", showAboutCMD)
                .label("About");

    }

    @Override
    public void postAddExtensions() {

        // Don't change the order!
        PlatformUtil.getMenuManager().fillEntryMap();

        PlatformUtil.getKeyBindingManager().fillBindings();
        PlatformUtil.getViewManager().fillViews();
        PlatformUtil.getEditorManager().fillEditors();

        PlatformUtil.getWindowManager().getMainWindow().loadLayout();
        PlatformUtil.getStatusBarManager().fillStatusBar();

    }

    @Override
    public void preWindowOpen() {

        EventManager.UI.post(new ExtensionsLoadedEvent());

        Rectangle rect = PropertyStore.getContext("System").getValue("MainFrameGeometry", null);
        if (rect != null) {
            PlatformUtil.getWindowManager().getMainWindow().setBounds(rect.getAWTRectangle());
        }
        PlatformUtil.getWindowManager().getMainWindow()
                .setExtendedState(PropertyStore.getContext("System").getValue("Maximized", 0));

        // Databinding between layoutlockstate of the main window and the property
        PropertyContext propCtx = PropertyStore.getContext("System");
        propCtx.getProperty("LayoutLockState", true).addPropertyChangeListener(evt -> {

            Boolean value = propCtx.getValue("LayoutLockState", true);

            if (value != PlatformUtil.getWindowManager().getMainWindow().getLayoutLockState().isViewsLocked()) {
                PlatformUtil.getWindowManager().getMainWindow().getLayoutLockState().setViewsLocked(value);
            }

        });

        PlatformUtil.getWindowManager().getMainWindow().getLayoutLockState().subscribeLockState(c -> {

            boolean oldValue = propCtx.getValue("LayoutLockState", true);

            if (c.booleanValue() != oldValue) {
                propCtx.setValue("LayoutLockState",
                        PlatformUtil.getWindowManager().getMainWindow().getLayoutLockState().isViewsLocked());
            }

        });
        // Sync startup state
        PlatformUtil.getWindowManager().getMainWindow().getLayoutLockState()
                .setViewsLocked(propCtx.getValue("LayoutLockState", true));

    }

    @Override
    public void onWindowCreate(JFrame window) {

        PlatformUtil.getWindowManager().setMainWindow(window);

        PlatformUtil.getMenuManager().init(window);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                CommandManager cm = ServiceManager.get(CommandManager.class);
                cm.executeCommand("de.emir.rcp.swingrcp.exitCommand");
            }
        });

    }

    @Override
    public void preApplicationClose() {
        // remember geometry
        PropertyStore.getContext("System").setValue("MainFrameGeometry",
                new Rectangle(PlatformUtil.getWindowManager().getMainWindow().getBounds()));
        PropertyStore.getContext("System").setValue("Maximized",
                PlatformUtil.getWindowManager().getMainWindow().getExtendedState());

        PlatformUtil.getViewManager().runOnViewShutdown();
    }
}
