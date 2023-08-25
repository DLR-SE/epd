package de.emir.rcp.other;

import de.emir.rcp.commands.ep.CommandExtensionPoint;
import de.emir.rcp.commands.ep.ICommandDescriptor;
import de.emir.rcp.ep.ExtensionPointUtil;
import de.emir.rcp.ids.Basic;
import de.emir.rcp.manager.MenuManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.menu.ep.IMenu;
import de.emir.rcp.menu.ep.MenuExtensionPoint;
import de.emir.rcp.other.cmd.ShowNavigationTool;
import de.emir.rcp.other.navtool.BearingTool;
import de.emir.rcp.other.navtool.CoordinateTransform;
import de.emir.rcp.other.navtool.ManeuverbordBearingToolPanel;
import de.emir.rcp.other.navtool.MultiManeuverbordBearingToolPanel;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;

public class NavigationToolsPlugin extends AbstractUIPlugin {

	private static final String MENUCONTRIBUTION_NAVIGATION_TOOLS = "de.emir.rcp.NavigationTools";
	
	private static final String MENU_NAVIGATION_TOOLS = "NavigationTools";

	private static final String MENUITEM_COORDINATE_TRANSFORM = "CoordinateTransform";
	private static final String MENUITEM_BEARING_TOOL = "BearingTool";
	private static final String MENUITEM_MANEUVERBORD_TOOL = "ManeuverBord";
	private static final String MENUITEM_MULTI_MANEUVERBORD_TOOL = "MultiManeuverBord";	
	
	private static final String CMD_SHOW_COORDINATE_TRANSFORM_TOOL = "CMD:de.emir.rcp.NavigationTools.ShowCoordinateTransformTool";
	private static final String CMD_SHOW_BEARING_TOOL = "CMD:de.emir.rcp.NavigationTools.ShowBearingTool";
	private static final String CMD_SHOW_MANEUVERBORD_TOOL = "CMD:de.emir.rcp.NavigationTools.ManeuverBord";
	private static final String CMD_SHOW_MULTI_MANEUVERBORD_TOOL = "CMD:de.emir.rcp.NavigationTools.MultiManeuverBord";
	

	
	

	@Override
	public void addExtensions() {
		super.addExtensions();
		
		MenuExtensionPoint mep = ExtensionPointManager.getExtensionPoint(MenuExtensionPoint.class);
		CommandExtensionPoint cep = ExtensionPointManager.getExtensionPoint(CommandExtensionPoint.class);
		
		ICommandDescriptor showCoordTransfCmd = cep.command(
				CMD_SHOW_COORDINATE_TRANSFORM_TOOL,
				"Show Coordinate Transform Tool",
				new ShowNavigationTool(CoordinateTransform.createAction())
		);
		ICommandDescriptor showBearingToolCmd = cep.command(
				CMD_SHOW_BEARING_TOOL,
				"Show Bearing Tool",
				new ShowNavigationTool(BearingTool.createAction())
		);
		ICommandDescriptor showManeuverboardToolCmd = cep.command(
				CMD_SHOW_MANEUVERBORD_TOOL,
				"Show Bearing Tool",
				new ShowNavigationTool(ManeuverbordBearingToolPanel.createAction())
		);
		ICommandDescriptor showMultiManeuverboardToolCmd = cep.command(
				CMD_SHOW_MULTI_MANEUVERBORD_TOOL,
				"Show Multi-Bearing Tool",
				new ShowNavigationTool(MultiManeuverbordBearingToolPanel.createAction())
		);
		
		IMenu mc = mep.menuContribution(Basic.MENU_MAIN_MENU)
				.menu(MENU_NAVIGATION_TOOLS, "Navigation Tools")
				.before("layout")
				.after("edit");
		
		mc.menuItem(MENUITEM_COORDINATE_TRANSFORM, showCoordTransfCmd)
				.label("Coordinate Transform");
		mc.menuItem(MENUITEM_BEARING_TOOL, showBearingToolCmd)
				.label("Bearing Tool");
		mc.menuItem(MENUITEM_MANEUVERBORD_TOOL, showManeuverboardToolCmd)
				.label("Maneuverbord");
		mc.menuItem(MENUITEM_MULTI_MANEUVERBORD_TOOL, showMultiManeuverboardToolCmd)
				.label("Multi Maneuverbord"); //TODO: need a better name
	}
}
