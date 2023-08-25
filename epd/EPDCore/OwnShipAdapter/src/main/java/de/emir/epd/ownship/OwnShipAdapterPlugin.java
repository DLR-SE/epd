package de.emir.epd.ownship;

import de.emir.epd.ownship.cmds.ActivateRouteCommand;
import de.emir.epd.ownship.ids.OwnshipAdapterBasic;
import de.emir.epd.ownship.ids.OwnshipBasics;
import de.emir.epd.ownship.settings.OwnshipAdapterSettingsPage;
import de.emir.epd.ownship.settings.OwnshipViewerSettingsPage;
import de.emir.epd.routemanager.ids.RouteManagerBasic;
import de.emir.rcp.commands.ep.CommandExtensionPoint;
import de.emir.rcp.commands.ep.ICommandDescriptor;
import de.emir.rcp.menu.ep.MenuExtensionPoint;
import de.emir.rcp.settings.ep.SettingsPageExtensionPoint;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public class OwnShipAdapterPlugin extends AbstractUIPlugin {

    @Override
    public void addExtensions() {
        
        ResourceManager rmgr = ResourceManager.get(getClass()); //used to load icons
        
        // Settings EP
        SettingsPageExtensionPoint spEP = ExtensionPointManager
                .getExtensionPoint(SettingsPageExtensionPoint.class);

        spEP.page(OwnshipBasics.OWNSHIP_VIEWER_SETTINGS_PAGE_ID)
                .page(OwnshipAdapterBasic.SETTINGS_PAGE_ID, OwnshipAdapterSettingsPage.class)
                .label("AIS Information")
                .icon("icons/emiricons/32/settings_input_antenna.png", rmgr);

        // Commands
        CommandExtensionPoint cmdEP = ExtensionPointManager
                .getExtensionPoint(CommandExtensionPoint.class);
        ICommandDescriptor activateRouteCMD = cmdEP.command(
                OwnshipAdapterBasic.ACTIVATE_ROUTE_COMMAND_ID,
                "Activate Route",
                new ActivateRouteCommand()
        );

        // Menus
        MenuExtensionPoint mEP = ExtensionPointManager.getExtensionPoint(MenuExtensionPoint.class);
        mEP.menuContribution(RouteManagerBasic.ROUTE_LIST_VIEW_TOOLBAR)
                .menuItem("activateRoute", activateRouteCMD)
                .label("Activate")
//                .icon("icons/unlicensed/reverse.png", rmgr)
        ;
    }

}
