package de.emir.epd.mapview.ids;

import de.emir.rcp.ids.Basic;

public class MVBasic {

	// Property Contexts
	public static final String MAP_VIEW_PROP_CONTEXT = "de.emir.epd.mapview.propertiesContext.MapViewPropertiesContext";
	
	// Selection Contexts
	public static final String MAP_SELECTION_CTX = "de.emir.epd.mapview.MapSelectionContext";
	public static final String MAP_FOCUS_CTX = "de.emir.epd.mapview.MapFocusContext";
	
	// Properties
	public static final String MAP_VIEW_PROP_TILE_SOURCE = "de.emir.epd.mapview.property.TileSource";
	public static final String MAP_VIEW_PROP_WMS_URL = "de.emir.epd.mapview.property.WMSURL";
	public static final String MAP_VIEW_PROP_WMS_LAYER = "de.emir.epd.mapview.property.WMSLayer";
	public static final String MAP_VIEW_PROP_CACHE_TILES_ON_HARD_DRIVE = "de.emir.epd.mapview.property.CacheTilesOnHardDrive";
	public static final String MAP_VIEW_PROP_MAX_HARD_DRIVE_CACHED_TILES = "de.emir.epd.mapview.property.MaxHardDriveCachedTiles";
	
	public static final String MAP_VIEW_PROP_LOCK_VIEW_ON_CURRENT_SELECTION = "de.emir.epd.mapview.property.LockViewOnCurrentSelection";
	
	// Extension Points
	public static final String MAP_VIEW_EXT_POINT = "de.emir.epd.mapview.mapViewExtensionPoint";
	
	
	// Menu Roots
	public static final String MAP_VIEW_TOOLBAR_ID = Basic.TOOLBAR_IDENTIFIER + "MapViewToolbar";
	public static final String LAYER_VIEW_TOOLBAR_ID = Basic.TOOLBAR_IDENTIFIER + "MapLayerViewToolbar";
	
	// Tile Sources
	public static final String OPEN_STREET_MAP_TILE_SOURCE_ID = "de.emir.epd.mapview.tilesources.OSMTileSource";
	public static final String GOOGLE_MAPS_TILE_SOURCE_ID = "de.emir.epd.mapview.tilesources.GoogleMapsTileSource";
	public static final String GOOGLE_EARTH_TILE_SOURCE_ID = "de.emir.epd.mapview.tilesources.GoogleEarthTileSource";
	public static final String WMS_TILE_SOURCE_ID = "de.emir.epd.mapview.tilesources.WMSTileSource";
	
	// Views
	public static final String MAP_VIEW_GROUP_ID = "de.emir.epd.mapview.views.MapViewGroup";
	public static final String MAP_VIEW_ID = "de.emir.epd.mapview.views.MapView";
	public static final String MAP_VIEW_LAYER_VIEW_ID = "de.emir.epd.mapview.views.LayerView";
	
	// Settings pages
	public static final String MAP_VIEW_SETTINGS_PAGE_ID = "de.emir.epd.mapview.settings";

	// Map View Properties
	public static final String MAP_VIEW_VISIBLE_LAYERS_PROP = "de.emir.epd.mapview.visibleLayers";
	public static final String MAP_VIEW_EXPANDED_LAYERS_PROP = "de.emir.epd.mapview.expandedLayers";
	public static final String MAP_VIEW_POSITION_PROP_LAT = "de.emir.epd.mapview.positionLat";
	public static final String MAP_VIEW_POSITION_PROP_LON = "de.emir.epd.mapview.positionLon";
	public static final String MAP_VIEW_ZOOM_PROP = "de.emir.epd.mapview.zoom";

	// Menu Entries
	public static final String SHOW_ALL_LAYERS_MENU_ID = "showAllLayersMenuItem";
	public static final String HIDE_ALL_LAYERS_MENU_ID = "hideAllLayersMenuItem";

	// Commands
	public static final String SHOW_ALL_LAYERS_COMMAND = "de.emir.epd.mapview.showAllLayersCommand";
	public static final String HIDE_ALL_LAYERS_COMMAND = "de.emir.epd.mapview.hideAllLayersCommand";
	public static final String LOCK_VIEW_ON_CURRENT_SELECTION_COMMAND = "de.emir.epd.mapview.lockViewOnCurrentSelectionCommand";
	
	public static final String ACTIVATE_BASIC_SELECTION_TOOL_CMD = "de.emir.epd.mapview.activateBasicSelectionToolCommand";

	// Tools
	public static final String TOOLS_BASIC_SELECTION = "de.emir.epd.mapview.basicSelectionTool";








	
}
