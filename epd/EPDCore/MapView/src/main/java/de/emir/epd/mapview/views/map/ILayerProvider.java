package de.emir.epd.mapview.views.map;

import java.util.Map;

import de.emir.epd.mapview.ep.MapLayer;
import de.emir.epd.mapview.manager.MapViewManager;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;

public interface ILayerProvider {

	/** 
	 * Provides all layer that shall be added to an MapViewer(WithTools). 
	 * @note they may not be visible, depending on other properties, e.g. LayerView
	 * @return
	 */
	public Map<String, MapLayer> provide();

	public static ILayerProvider defaultProvider() {
		return new ILayerProvider() {			
			@Override
			public Map<String, MapLayer> provide() {
				return  ServiceManager.get(MapViewManager.class).getExtensionPoint().getLayers();
			}
		};
	} 
}
