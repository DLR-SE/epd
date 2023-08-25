package de.emir.epd.mapview;

import de.emir.epd.mapview.basics.layers.MousePositionInfoLayer;
import de.emir.epd.mapview.basics.layers.ScaleInfoLayer;
import de.emir.epd.mapview.basics.tilesources.GoogleEarthTileSource;
import de.emir.epd.mapview.basics.tilesources.GoogleMapsTileSource;
import de.emir.epd.mapview.basics.tilesources.OpenStreetMapTileSource;
import de.emir.epd.mapview.basics.tilesources.WMSTileSource;
import de.emir.epd.mapview.basics.tools.BasicSelectionTool;
import de.emir.epd.mapview.cmds.LockViewOnCurrentSelectionCommand;
import de.emir.epd.mapview.cmds.SetAllLayerVisibleStateCommand;
import de.emir.epd.mapview.cmds.SetToolActiveStateCommand;
import de.emir.epd.mapview.ep.MapViewExtensionPoint;
import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.mapview.manager.MapViewManager;
import de.emir.epd.mapview.settings.MapViewSettingsPage;
import de.emir.epd.mapview.views.layer.LayerView;
import de.emir.epd.mapview.views.map.MapView;
import de.emir.epd.mapview.views.status.MapStatusBar;
import de.emir.rcp.commands.ep.CommandExtensionPoint;
import de.emir.rcp.commands.ep.ICommandDescriptor;
import de.emir.rcp.ids.Basic;
import de.emir.rcp.keybindings.ep.KeyBindingExtensionPoint;
import de.emir.rcp.menu.ep.IMenuContribution;
import de.emir.rcp.menu.ep.MenuExtensionPoint;
import de.emir.rcp.settings.ep.SettingsPageExtensionPoint;
import de.emir.rcp.statusbar.ep.StatusBarExtensionPoint;
import de.emir.rcp.views.ep.IViewGroup;
import de.emir.rcp.views.ep.ViewExtensionPoint;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public class MapViewPlugin extends AbstractUIPlugin {

    @Override
    public void initializePlugin() {
        ServiceManager.register(new MapViewManager());
    }

    @Override
    public void registerExtensionPoints() {
        ExtensionPointManager.registerExtensionPoint(
                MVBasic.MAP_VIEW_EXT_POINT,
                ServiceManager.get(MapViewManager.class).getExtensionPoint()
        );
    }

    @Override
    public void addExtensions() {

        ResourceManager rmgr = ResourceManager.get(getClass());

        // Views
        ViewExtensionPoint viewEP = ExtensionPointManager.getExtensionPoint(ViewExtensionPoint.class);

        IViewGroup group = viewEP.group(MVBasic.MAP_VIEW_GROUP_ID)
                .label("Map")
                .icon("icons/emiricons/32/map.png", rmgr);

        group.view(MVBasic.MAP_VIEW_ID, MapView.class).label("Map")
                .icon("icons/emiricons/32/map.png");
        group.view(MVBasic.MAP_VIEW_LAYER_VIEW_ID, LayerView.class)
                .label("Map Layers")
                .icon("icons/emiricons/32/layers.png");

        // MapView EP

        MapViewExtensionPoint mvEP = ExtensionPointManager.getExtensionPoint(MapViewExtensionPoint.class);

        mvEP.tileSource(MVBasic.OPEN_STREET_MAP_TILE_SOURCE_ID, OpenStreetMapTileSource.class)
                .label("Open Street Map")
                .icon("icons/unlicensed/osm.png");
        mvEP.tileSource(MVBasic.GOOGLE_MAPS_TILE_SOURCE_ID, GoogleMapsTileSource.class)
                .label("Google Maps")
                .icon("icons/unlicensed/google.png");
        mvEP.tileSource(MVBasic.GOOGLE_EARTH_TILE_SOURCE_ID, GoogleEarthTileSource.class)
                .label("Google Earth")
                .icon("icons/unlicensed/earth.png");
        mvEP.tileSource(MVBasic.WMS_TILE_SOURCE_ID, WMSTileSource.class)
                .label("WMS")
                .icon("icons/emiricons/32/map.png");

        mvEP.layer("MousePositionInfoLayer", MousePositionInfoLayer.class)
                .label("Coordinates")
                .icon("icons/emiricons/32/location_on.png")
                .zOrder(100);
        mvEP.layer("ScaleInfoLayer", ScaleInfoLayer.class)
                .label("Scale")
                .icon("icons/emiricons/32/straighten.png")
                .zOrder(99);
        //TODO this tool is not implemented, what should it do?
        mvEP.tool(MVBasic.TOOLS_BASIC_SELECTION, BasicSelectionTool.class);

        // Settings EP

        SettingsPageExtensionPoint spEP = ExtensionPointManager.getExtensionPoint(SettingsPageExtensionPoint.class);

        spEP.page(MVBasic.MAP_VIEW_SETTINGS_PAGE_ID, MapViewSettingsPage.class)
                .label("Map View")
                .icon("icons/emiricons/32/map.png", rmgr);

        // Commands
        CommandExtensionPoint cmdEP = ExtensionPointManager.getExtensionPoint(CommandExtensionPoint.class);
        ICommandDescriptor cmdShowAllLayers = cmdEP.command(
                MVBasic.SHOW_ALL_LAYERS_COMMAND,
                "Show All Map Layers",
                new SetAllLayerVisibleStateCommand(true)
        );

        ICommandDescriptor cmdHideAllLayers = cmdEP.command(
                MVBasic.HIDE_ALL_LAYERS_COMMAND,
                "Hide All Map Layers",
                new SetAllLayerVisibleStateCommand(false)
        );

        ICommandDescriptor cmdBasicSelectionTool = cmdEP.command(
                MVBasic.ACTIVATE_BASIC_SELECTION_TOOL_CMD,
                "Activate Cursor Tool",
                new SetToolActiveStateCommand(MVBasic.TOOLS_BASIC_SELECTION)
        );

        ICommandDescriptor cmdLockViewOnCurrentSelection = cmdEP.command(
                MVBasic.LOCK_VIEW_ON_CURRENT_SELECTION_COMMAND,
                "Lock Map View On Current Selection",
                new LockViewOnCurrentSelectionCommand()
        );

        // Menu

        MenuExtensionPoint menuEP = ExtensionPointManager.getExtensionPoint(MenuExtensionPoint.class);

        IMenuContribution layerViewToolbar = menuEP.menuContribution(MVBasic.LAYER_VIEW_TOOLBAR_ID);
        layerViewToolbar.menuItem(MVBasic.SHOW_ALL_LAYERS_MENU_ID, cmdShowAllLayers)
                .icon("icons/emiricons/32/visibility.png", rmgr);
        layerViewToolbar.menuItem(MVBasic.HIDE_ALL_LAYERS_MENU_ID, cmdHideAllLayers)
                .icon("icons/emiricons/32/visibility_off.png", rmgr);

        IMenuContribution mapViewToolbar = menuEP.menuContribution(MVBasic.MAP_VIEW_TOOLBAR_ID);
        mapViewToolbar.menuItem("basicSelectionTool", cmdBasicSelectionTool)
                .icon("icons/emiricons/32/cursor2.png", rmgr)
                .tooltip("Cursor Tool");

        mapViewToolbar.menuItem("lockViewOnCurrentSelection", cmdLockViewOnCurrentSelection)
                .icon("icons/emiricons/32/lock.png");
        mapViewToolbar.separator("undoRedoSeparator");
        mapViewToolbar.menuItem("undo", Basic.CMD_UNDO)
                .icon("icons/emiricons/32/undo.png")
                .after("undoRedoSeparator");
        mapViewToolbar.menuItem("redo", Basic.CMD_REDO)
                .icon("icons/emiricons/32/redo.png")
                .after("undoRedoSeparator");

        // Key Bindings
        KeyBindingExtensionPoint kbEP = ExtensionPointManager.getExtensionPoint(KeyBindingExtensionPoint.class);
        kbEP.inView(MVBasic.MAP_VIEW_ID, "ESCAPE", cmdBasicSelectionTool);
        kbEP.inView(MVBasic.MAP_VIEW_ID, "CTRL+Z", Basic.CMD_UNDO);
        kbEP.inView(MVBasic.MAP_VIEW_ID, "CTRL+Y", Basic.CMD_REDO);
        
        // Statusbar for coordinates
        StatusBarExtensionPoint sbEP = ExtensionPointManager.getExtensionPoint(StatusBarExtensionPoint.class);
        if (sbEP != null) {
            sbEP.element(MapStatusBar.class);
        }

    }

}
