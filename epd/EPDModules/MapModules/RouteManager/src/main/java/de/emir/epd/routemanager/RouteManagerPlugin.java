package de.emir.epd.routemanager;

import org.apache.logging.log4j.Logger;

import de.emir.epd.mapview.cmds.SetToolActiveStateCommand;
import de.emir.epd.mapview.ep.MapViewExtensionPoint;
import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.routemanager.cmd.CopyRouteCommand;
import de.emir.epd.routemanager.cmd.CopyXMLRouteCommand;
import de.emir.epd.routemanager.cmd.DeleteRouteCommand;
import de.emir.epd.routemanager.cmd.ExportAllRouteCommand;
import de.emir.epd.routemanager.cmd.ExportRouteCommand;
import de.emir.epd.routemanager.cmd.ImportRouteClipboardCommand;
import de.emir.epd.routemanager.cmd.ImportRouteCommand;
import de.emir.epd.routemanager.cmd.InvertRouteCommand;
import de.emir.epd.routemanager.cmd.OpenRoutePropertiesCommand;
import de.emir.epd.routemanager.cmd.RefreshRoutesCommand;
import de.emir.epd.routemanager.cmd.ZoomToRouteCommand;
import de.emir.epd.routemanager.cmd.popup.AppendWayPointCommand;
import de.emir.epd.routemanager.cmd.popup.DeleteWayPointCommand;
import de.emir.epd.routemanager.cmd.popup.HideRouteCommand;
import de.emir.epd.routemanager.ids.RouteManagerBasic;
import de.emir.epd.routemanager.impl.RouteAdapterManager;
import de.emir.epd.routemanager.impl.RouteManager;
import de.emir.epd.routemanager.impl.adapter.UObjectModelAdapter;
import de.emir.epd.routemanager.map.EditRouteTool;
import de.emir.epd.routemanager.map.NewRouteTool;
import de.emir.epd.routemanager.map.RouteLayer;
import de.emir.epd.routemanager.view.RouteListView;
import de.emir.rcp.commands.ep.CommandExtensionPoint;
import de.emir.rcp.commands.ep.ICommandDescriptor;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.menu.ep.MenuExtensionPoint;
import de.emir.rcp.views.ep.IViewGroup;
import de.emir.rcp.views.ep.ViewExtensionPoint;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

/**
 * The RouteManagerPlugin adds a RouteManager into the EPD, as well as an Layer
 * for the MapView and an View to display all routes of the current model.
 *
 * MainClasses: - IRouteManager (implemented by impl.RouteManager): The
 * IRouteManager manages all routes within a certain model. Those Routes are
 * organized into groups (usually but not necessarily) based on an
 * VoyageCharacteristic. The manager provides convinient acces to all routes and
 * specific / view relevant properties of those routes (e.g. visibility) The
 * Routes are collected by an IRouteAdapter (managed through the ExtensionPoint
 * IRouteAdapterManager). Thereby each RouteManager may have access to all
 * RouteAdapters. Another usefull capability of the RouteManager is the
 * observation of all Routes and route groups. To get notified about Route or
 * RouteGroup changes you can register an consumer within the RouteManager, that
 * will collect all events and forward them. - IRouteAdapter: Collects all
 * Routes and RouteGroups from a given Model (usually connected the
 * ModelProvider) - IRouteAccessModel : Hierarchical RouteGroup implementation
 * 
 * @note there may exist different instances of the RouteManager, to reuse its
 *       capabilities in other projects. However, the RouteManagerPlugin
 *       registers a default RouteManager that is used by the RouteView as well
 *       as the RouteLayer. This default RouteManager can be accessed through: -
 *       ServiceManager.getByID(RouteManagerBasic.DEFAULT_ROUTE_MANAGER) -
 *       IRouteManager.getDefaultRouteManager()
 * @author JMentjes, FKlein, sschweigert
 *
 */
public class RouteManagerPlugin extends AbstractUIPlugin {
	private static final Logger LOG = ULog.getLogger(RefreshRoutesCommand.class);

	@Override
	public void registerExtensionPoints() {
		IRouteAdapterManager ram = new RouteAdapterManager();
		ExtensionPointManager.registerExtensionPoint(RouteManagerBasic.EP_ROUTE_ADAPTER_MANAGER, ram);

		// register an data model based route adapter
		IRouteAdapterManager extensionPoint = ExtensionPointManager.getExtensionPoint(IRouteAdapterManager.class);
		
		if (extensionPoint != null) {
			extensionPoint.registerRouteAdapter(new UObjectModelAdapter());			
		}
		else {
			LOG.error("Could not register RouteAdapter");
		}

		IRouteManager defaultRouteManager = new RouteManager(RouteManagerBasic.DEFAULT_ROUTE_MANAGER,
				PlatformUtil.getModelManager().getModelProvider());
		ServiceManager.register(RouteManagerBasic.DEFAULT_ROUTE_MANAGER, defaultRouteManager);
	}

	@Override
	public void addExtensions() {
		IRouteManager defaultRouteManager = IRouteManager.getDefaultRouteManager();
		ResourceManager rm = ResourceManager.get(getClass());

		ViewExtensionPoint viewEp = ExtensionPointManager.getExtensionPoint(ViewExtensionPoint.class);
		if (viewEp == null) {
			LOG.error("Could not get ViewExtensionPoint to register RouteListView");
			return;
		}

		IViewGroup group = viewEp.group(RouteManagerBasic.ROUTE_VIEW_GROUP_ID).label("Route")
				.icon("icons/emiricons/32/action_chains.png", rm);

		group.view(RouteManagerBasic.ROUTE_LIST_VIEW_ID, RouteListView.class).label("Routes")
				.icon("icons/emiricons/32/route.png", rm);

		// MapView EP
		MapViewExtensionPoint mapviewEP = ExtensionPointManager.getExtensionPoint(MapViewExtensionPoint.class);
		if (mapviewEP  == null) {
			LOG.error("Could not get MapViewExtensionPoint to register RouteLayer, NewRouteTool, and EditRouteTool");
			return;
		}
		mapviewEP .layer(RouteManagerBasic.ROUTE_LAYER_ID, RouteLayer.class).label("RouteLayer")
				.icon("icons/emiricons/32/route.png").zOrder(2);
		mapviewEP .tool(RouteManagerBasic.NEW_ROUTE_TOOL_ID, NewRouteTool.class);
		mapviewEP .tool(RouteManagerBasic.EDIT_ROUTE_TOOL_ID, EditRouteTool.class);

		CommandExtensionPoint commandEP = ExtensionPointManager.getExtensionPoint(CommandExtensionPoint.class);
		if (commandEP == null) {
			LOG.error("Could not get CommandExtensionPoint to register editRouteToolCommand and newRouteToolCommand");
			return;
		}
		ICommandDescriptor newRouteCommand = commandEP.command("newRouteToolCommand", "Activate New Route Tool",
				new SetToolActiveStateCommand(RouteManagerBasic.NEW_ROUTE_TOOL_ID));
		ICommandDescriptor editRouteCommand = commandEP.command("editRouteToolCommand", "Activate Edit Route Tool",
				new SetToolActiveStateCommand(RouteManagerBasic.EDIT_ROUTE_TOOL_ID));

		// Popup
		ICommandDescriptor appendWaypointCommand = commandEP.command("appendWaypointCommand", "Append Waypoint",
				new AppendWayPointCommand());
		ICommandDescriptor deleteWaypointCommand = commandEP.command("deleteWaypointCommand", "Delete Waypoint",
				new DeleteWayPointCommand());
		ICommandDescriptor hideRouteCommand = commandEP.command("hideRouteCommand", "Hide Route",
				new HideRouteCommand(defaultRouteManager));

		// View & Popup
		ICommandDescriptor copyRouteCommand = commandEP.command("copyRouteCommand", "Duplicate Route",
				new CopyRouteCommand(defaultRouteManager));
		ICommandDescriptor copyXMLRouteCommand = commandEP.command("copyXMLRouteCommand", "Copy Route XML",
				new CopyXMLRouteCommand());
		ICommandDescriptor openPropertiesCommand = commandEP.command("openPropertiesCommand", "Open Properties",
				new OpenRoutePropertiesCommand());
		ICommandDescriptor invertRouteCommand = commandEP.command("invertRouteCommand", "Invert Route",
				new InvertRouteCommand(defaultRouteManager));
		ICommandDescriptor deleteRoutesCmd = commandEP.command("deleteRouteCommand", "Delete Route(s)",
				new DeleteRouteCommand(defaultRouteManager));
		ICommandDescriptor exportAllRoutesCmd = commandEP.command("exportAllRoutesCommand", "Export All Routes",
				new ExportAllRouteCommand(defaultRouteManager));
		ICommandDescriptor exportRouteCmd = commandEP.command("exportRouteCommand", "Export Route",
				new ExportRouteCommand());
		ICommandDescriptor importRouteCmd = commandEP.command("importRouteCommand", "Import Route",
				new ImportRouteCommand(defaultRouteManager));
		ICommandDescriptor importRouteClipboardCmd = commandEP.command("importRouteClipboardCommand",
				"Import Route Clipboard", new ImportRouteClipboardCommand(defaultRouteManager));
		ICommandDescriptor zoomToRouteCmd = commandEP.command("zoomToRouteCommand", "Zoom To Route",
				new ZoomToRouteCommand(defaultRouteManager));
		ICommandDescriptor refreshRoutesCmd = commandEP.command("refreshRoutesCommand",
				"Refresh Routes", new RefreshRoutesCommand());
		// Menu Entries

		MenuExtensionPoint menuEP = ExtensionPointManager.getExtensionPoint(MenuExtensionPoint.class);
		if (menuEP  == null) {
			LOG.error("Could not get MenuExtensionPoint to register route menue buttons");
			return;
		}
		menuEP.menuContribution(MVBasic.MAP_VIEW_TOOLBAR_ID).separator("routeToolsSeparator")
				.after("lockViewOnCurrentSelection");
		menuEP.menuContribution(MVBasic.MAP_VIEW_TOOLBAR_ID).menuItem("newRouteToolItem", newRouteCommand)
				.after("routeToolsSeparator").icon("icons/emiricons/32/add_road.png");
		menuEP.menuContribution(MVBasic.MAP_VIEW_TOOLBAR_ID).menuItem("editRouteToolItem", editRouteCommand)
				.after("routeToolsSeparator").icon("icons/emiricons/32/edit_road.png");

		menuEP.menuContribution(RouteManagerBasic.ROUTE_POPUP_MENU)
				.menuItem("appendWaypointCommand", appendWaypointCommand).label("Append Waypoint");
		menuEP.menuContribution(RouteManagerBasic.ROUTE_POPUP_MENU).menuItem("copyRouteCommand", copyRouteCommand)
				.label("Duplicate Route");
		menuEP.menuContribution(RouteManagerBasic.ROUTE_POPUP_MENU).menuItem("copyXMLRouteCommand", copyXMLRouteCommand)
				.label("Copy Route XML");
		menuEP.menuContribution(RouteManagerBasic.ROUTE_POPUP_MENU)
				.menuItem("deleteWaypointCommand", deleteWaypointCommand).label("Delete Waypoint");
		menuEP.menuContribution(RouteManagerBasic.ROUTE_POPUP_MENU).menuItem("hideRouteCommand", hideRouteCommand)
				.label("Hide Route");
		menuEP.menuContribution(RouteManagerBasic.ROUTE_POPUP_MENU)
				.menuItem("openPropertiesCommand", openPropertiesCommand).label("Open Properties");
		menuEP.menuContribution(RouteManagerBasic.ROUTE_POPUP_MENU).menuItem("reverseRouteCommand", invertRouteCommand)
				.label("Reverse Route");

		// Waypoint popup
		menuEP.menuContribution(RouteManagerBasic.WAYPOINT_POPUP_MENU).menuItem("deleteWaypoint", deleteWaypointCommand)
				.label("Delete Waypoint");
		menuEP.menuContribution(RouteManagerBasic.WAYPOINT_POPUP_MENU).separator("RouteSeperator");
		menuEP.menuContribution(RouteManagerBasic.WAYPOINT_POPUP_MENU).menuItem("hideRouteCommand", hideRouteCommand)
				.label("Hide Route");

		// Menu Entries - Route List View
		menuEP.menuContribution(RouteManagerBasic.ROUTE_LIST_VIEW_TOOLBAR)
				.menuItem("routeProperties", openPropertiesCommand).label("Properties")
				.icon("icons/emiricons/32/construction.png", rm);
		menuEP.menuContribution(RouteManagerBasic.ROUTE_LIST_VIEW_TOOLBAR).menuItem("invertRoute", invertRouteCommand)
				.label("Invert").icon("icons/emiricons/32/change_circle.png", rm);
		menuEP.menuContribution(RouteManagerBasic.ROUTE_LIST_VIEW_TOOLBAR).separator("RouteModSep");
		menuEP.menuContribution(RouteManagerBasic.ROUTE_LIST_VIEW_TOOLBAR).menuItem("copyRoute", copyRouteCommand)
				.label("Duplicate").icon("icons/emiricons/32/replica.png", rm);
		menuEP.menuContribution(RouteManagerBasic.ROUTE_LIST_VIEW_TOOLBAR).menuItem("copyXMLRoute", copyXMLRouteCommand)
				.label("Copy").icon("icons/emiricons/32/content_copy.png", rm);
		menuEP.menuContribution(RouteManagerBasic.ROUTE_LIST_VIEW_TOOLBAR)
				.menuItem("importRouteClipboard", importRouteClipboardCmd).label("Paste")
				.icon("icons/emiricons/32/content_paste.png", rm);
		menuEP.menuContribution(RouteManagerBasic.ROUTE_LIST_VIEW_TOOLBAR).menuItem("deleteRoute", deleteRoutesCmd)
				.label("Delete").icon("icons/emiricons/32/delete.png", rm);
		menuEP.menuContribution(RouteManagerBasic.ROUTE_LIST_VIEW_TOOLBAR).menuItem("exportAllRoutes", exportAllRoutesCmd)
				.label("Export All").icon("icons/emiricons/32/outbox.png", rm);
		menuEP.menuContribution(RouteManagerBasic.ROUTE_LIST_VIEW_TOOLBAR).menuItem("exportRoute", exportRouteCmd)
				.label("Export").icon("icons/emiricons/32/unarchive.png", rm);
		menuEP.menuContribution(RouteManagerBasic.ROUTE_LIST_VIEW_TOOLBAR).menuItem("importRoute", importRouteCmd)
				.label("Import").icon("icons/emiricons/32/archive.png", rm);		
		menuEP.menuContribution(RouteManagerBasic.ROUTE_LIST_VIEW_TOOLBAR).separator("RouteIOSep");
		menuEP.menuContribution(RouteManagerBasic.ROUTE_LIST_VIEW_TOOLBAR).menuItem("refreshRoutes", refreshRoutesCmd)
				.label("Refresh").icon("icons/emiricons/32/sync.png", rm);
		menuEP.menuContribution(RouteManagerBasic.ROUTE_LIST_VIEW_TOOLBAR)
				.menuItem(RouteManagerBasic.MENU_ZOOM_TO, zoomToRouteCmd).label("Zoom To")
				.icon("icons/emiricons/32/my_location.png", rm);

		// bind the default route manager to the model provider
		PlatformUtil.getModelManager().getModelProvider().subscribeModel(subsc -> {
			Object model = PlatformUtil.getModelManager().getModelProvider().getModel();
			if (model != null)
				defaultRouteManager.rebuildModel(model);
		});

	}

	@Override
	public void preApplicationClose() {
		IRouteManager.getDefaultRouteManager().dispose();
		super.preApplicationClose();
	}
}
