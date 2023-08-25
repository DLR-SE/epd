package de.emir.epd.mapview.ep;

import de.emir.epd.mapview.views.map.AbstractMapLayerSettingsPanel;

public interface IMapLayer {

	IMapLayer label(String label);
	IMapLayer icon(String path);
	IMapLayer settingsPanel(Class<? extends AbstractMapLayerSettingsPanel> settingsPanelClass);
	
	
	/**
	 * Set the z order of the layer. Can be set to move a layer above or below another one
	 * @param index
	 * @return
	 */
	IMapLayer zOrder(double o);
	
}