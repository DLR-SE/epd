package de.emir.epd.mapview.ep;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import de.emir.epd.mapview.views.map.AbstractMapLayer;
import de.emir.epd.mapview.views.map.AbstractTileSource;
import de.emir.epd.mapview.views.tools.AbstractMapViewTool;
import de.emir.tuml.ucore.runtime.extension.IExtensionPoint;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class MapViewExtensionPoint implements IExtensionPoint {

	private Map<String, MapLayer> layers = new HashMap<>();

	private Map<String, MapTool> tools = new HashMap<>();
	private Map<String, TileSource> tileSources = new HashMap<>();

	private static final Logger log = ULog.getLogger(MapViewExtensionPoint.class);

	public IMapLayer layer(String id, Class<? extends AbstractMapLayer> layerClass) {

		MapLayer layer = layers.get(id);

		if (layer == null) {
			layer = new MapLayer(id);
			layers.put(id, layer);
		}

		layer.setLayerClass(layerClass);

		return layer;

	}

	public IMapTool tool(String id, Class<? extends AbstractMapViewTool> toolClass) {

		MapTool tool = tools.get(id);

		if (tool == null) {
			tool = new MapTool(id);
			tools.put(id, tool);
		}

		tool.setToolClass(toolClass);

		return tool;

	}

	public ITileSource tileSource(String id, Class<? extends AbstractTileSource> tsClass) {

		TileSource ts = tileSources.get(id);

		if (ts == null) {
			ts = new TileSource(id);
			tileSources.put(id, ts);
		}

		ts.setTileSourceClass(tsClass);

		return ts;

	}
	
	
	public List<MapLayer> getOrderedLayers() {
		
		List<MapLayer> result = new ArrayList<>();
		
		Collection<MapLayer> values = layers.values();
		
		result.addAll(values);
		
		Collections.sort(result, new LayerComparator());
		
		return result;
		
		
	}
	
	private class LayerComparator implements Comparator<MapLayer> {

		@Override
		public int compare(MapLayer o1, MapLayer o2) {
			
			double z1 = o1.getzOrder();
			double z2 = o2.getzOrder();
			
			if(z1 > z2) {
				return 1;
			}
			
			if(z1 < z2) {
				return -1;
			}
			
			
			return 0;
		}
		
	}

	public Map<String, MapLayer> getLayers() {
		return layers;
	}

	public Map<String, TileSource> getTileSources() {
		return tileSources;
	}

	public Map<String, MapTool> getTools() {
		return tools;
	}

}
