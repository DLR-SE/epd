package de.emir.epd.mapview.ep;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.emir.epd.mapview.views.map.AbstractMapLayer;
import de.emir.epd.mapview.views.map.AbstractTileSource;
import de.emir.epd.mapview.views.tools.AbstractMapViewTool;
import de.emir.tuml.ucore.runtime.extension.IExtensionPoint;

/**
 * This is the MapViewExtensionPoint. It handles registering of Map Layers, Tools and Tile Sources to the JXMapViewer.
 */
public class MapViewExtensionPoint implements IExtensionPoint {

	private final Map<String, MapLayer> layers = new HashMap<>();
	private final Map<String, MapTool> tools = new HashMap<>();
	private final Map<String, TileSource> tileSources = new HashMap<>();

	/**
	 * Registers a new layer to the map. This should be called during the addExtensions method of a plugin entrypoint.
	 * @param id ID of the layer. This should be unique to identify the used layer which can be retrieved using
	 *           the getLayers or getOrderedLayers method of this extension point.
	 * @param layerClass Class of the layer to instantiate. This class represents the Map layer that should be
	 *                   registered which should extend AbstractMapLayer
	 * @return Constructed map layer.
	 */
	public IMapLayer layer(String id, Class<? extends AbstractMapLayer> layerClass) {
        MapLayer layer = layers.computeIfAbsent(id, MapLayer::new);
        layer.setLayerClass(layerClass);
		return layer;
	}

	/**
	 * Registers a new map tool to the map. These are tools which can execute operations on the map such as calculating
	 * distances, editing routes etc. by the user by interfacing with the map using the cursor.
	 * @param id ID of the tool. This should be unique to identify the used tool which can be retrieved using
	 * 	 *           the getTools method of this extension point. This class represents the tool that should be
	 * 	 *                   registered which should extend AbstractMapViewTool.
	 * @param toolClass Tool class to register.
	 * @return Constructed MapTool.
	 */
	public IMapTool tool(String id, Class<? extends AbstractMapViewTool> toolClass) {
        MapTool tool = tools.computeIfAbsent(id, MapTool::new);
        tool.setToolClass(toolClass);
		return tool;
	}

	/**
	 * Registers a new tile source with the extension point. This can later be accessed by its id.
	 * @param id ID of the tile to register. This should be unique to identify the used tile source which can be retrieved using
	 * 	 *           the getTileSources method of this extension point.
	 * @param tsClass TileSource class to instantiate. This class represents the source that should be
	 * 	 * 	 *                   registered which should extend AbstractTileSource.
	 * @return Constructed TileSource.
	 */
	public ITileSource tileSource(String id, Class<? extends AbstractTileSource> tsClass) {
        TileSource ts = tileSources.computeIfAbsent(id, TileSource::new);
        ts.setTileSourceClass(tsClass);
		return ts;
	}

	/**
	 * Gets all registered Map layers in sorted order. This method respects the order specified by the zOrder parameter
	 * of the MapLayer. The z Order defines the order in which map tiles are layered on top of each other. The higher
	 * the z index the higher the layer sits compared to other layers.
	 * @return List of z-Order ordered layers.
	 */
	public List<MapLayer> getOrderedLayers() {
        Collection<MapLayer> values = layers.values();
        List<MapLayer> result = new ArrayList<>(values);
		result.sort(new LayerComparator());
		return result;
	}

	/**
	 * Comparator used for sorting the layers by z order.
	 */
	private static class LayerComparator implements Comparator<MapLayer> {

		/**
		 * Compares the z order of two map layers to each other. This allows sorting
		 * of map layers based on their z index.
		 * @param o1 the first object to be compared.
		 * @param o2 the second object to be compared.
		 * @return 0 if equal, -1 if first MapLayer is smaller than second, 1 if bigger.
		 */
		@Override
		public int compare(MapLayer o1, MapLayer o2) {
			double z1 = o1.getzOrder();
			double z2 = o2.getzOrder();
            return Double.compare(z1, z2);
        }
	}

	/**
	 * Gets the unordered Map of all registered MapLayers. Use getOrderedLayers to respect the z index of each MapLayer.
	 * @return Map of all registered layers and their IDs.
	 */
	public Map<String, MapLayer> getLayers() {
		return layers;
	}

	/**
	 * Gets the Map of all registered TileSources.
	 * @return Map of all registered TileSources and their IDs.
	 */
	public Map<String, TileSource> getTileSources() {
		return tileSources;
	}

	/**
	 * Gets the Map of all registered MapTools.
	 * @return Map of all registered MapTools and their IDs.
	 */
	public Map<String, MapTool> getTools() {
		return tools;
	}

}
