package de.emir.epd.example;

import de.emir.epd.example.basic.ExampleBasic;
import de.emir.epd.example.cmd.ExampleCommand;
import de.emir.epd.example.map.ExampleLayer;
import de.emir.epd.example.map.ExampleTool;
import de.emir.epd.example.settings.ExampleSettingsPage;
import de.emir.epd.example.view.ExampleView;
import de.emir.epd.mapview.cmds.SetToolActiveStateCommand;
import de.emir.epd.mapview.ep.MapViewExtensionPoint;
import de.emir.epd.mapview.ids.MVBasic;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.rcp.commands.ep.CommandExtensionPoint;
import de.emir.rcp.commands.ep.ICommandDescriptor;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.menu.ep.IMenu;
import de.emir.rcp.menu.ep.MenuExtensionPoint;
import de.emir.rcp.settings.ep.SettingsPageExtensionPoint;
import de.emir.rcp.views.ep.IViewGroup;
import de.emir.rcp.views.ep.ViewExtensionPoint;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public class ExamplePlugin extends AbstractUIPlugin {


    @Override
    public void preWindowOpen() {
        Coordinate coordinate = new CoordinateImpl();

        //initial position
        coordinate.setLatLon(53.148729, 8.200781);

        // register coordinate to display and move using map layer and tool
        PlatformUtil.getSelectionManager().setSelection(
                ExampleBasic.EXAMPLE_SELECTION_CONTEXT,
                coordinate
        );
    }

    @Override
    public void addExtensions() {
        // searches for the classloader for this plugin and searches for resources in its current classpath
        ResourceManager rsm = ResourceManager.get(ExamplePlugin.class);

        // retrieve ViewExtensionPoint which manages views
        ViewExtensionPoint viewEP = ExtensionPointManager.getExtensionPoint(ViewExtensionPoint.class);
        // register group (is used to structure views in groups in the interface)
        IViewGroup viewGroup = viewEP.group(ExampleBasic.EXAMPLE_GROUP_ID)
                .label("Example");
        viewGroup.view(ExampleBasic.EXAMPLE_VIEW_ID, ExampleView.class)
                .label("Example View")
                .icon("/icons/question_mark.png", rsm);

        // retrieve SettingsPageExtensionPoint which manages settings pages
        SettingsPageExtensionPoint sep = ExtensionPointManager.getExtensionPoint(SettingsPageExtensionPoint.class);
        // register settings page
        sep.page(ExampleBasic.EXAMPLE_SETTINGS_PAGE_ID, ExampleSettingsPage.class)
                .label("Example Settings")
                .icon("/icons/question_mark.png", rsm);

        // retrieve MapViewExtensionPoint which manages a map
        MapViewExtensionPoint mvEP = ExtensionPointManager.getExtensionPoint(MapViewExtensionPoint.class);
        //register layer
        mvEP.layer(ExampleBasic.EXAMPLE_LAYER_ID, ExampleLayer.class)
                .label("Example Layer");
        // register tool
        mvEP.tool(ExampleBasic.EXAMPLE_TOOL_ID, ExampleTool.class);

        // retrieve CommandExtensionPoint which manages command entries
        CommandExtensionPoint cmdEP = ExtensionPointManager.getExtensionPoint(CommandExtensionPoint.class);
        // tools are not automatically added to mapview toolbar, so register a command to set our tool active
        // command gets activated if the corresponding button is clicked (toolbar contribution is at the bottom)
        ICommandDescriptor setToolActive = cmdEP.command(
                "setExampleToolActive",
                "Example Tool",
                new SetToolActiveStateCommand(ExampleBasic.EXAMPLE_TOOL_ID));
        // register go to command
        ICommandDescriptor exampleCMD = cmdEP.command(
                "runExampleCMD",
                "Go To",
                new ExampleCommand()
        );

        // retrieve MenuExtensionPoint which manages menu entries
        MenuExtensionPoint mEP = ExtensionPointManager.getExtensionPoint(MenuExtensionPoint.class);
        // register our commands (activate tool and go to command), epd will create buttons depending on the
        // command type (AbstractCommand, AbstractCheckableCommand, etc.)
        mEP.menuContribution(MVBasic.MAP_VIEW_TOOLBAR_ID)
                .menuItem("editCoordinateExample", setToolActive)
                .label("Example Tool");
        mEP.menuContribution(MVBasic.MAP_VIEW_TOOLBAR_ID)
                .menuItem("gotoExampleCommand", exampleCMD)
                .label("Go To");
        
        mEP.menuContribution(MVBasic.MAP_VIEW_TOOLBAR_ID).separator("ExamplePluginSeparator");
    }
}
