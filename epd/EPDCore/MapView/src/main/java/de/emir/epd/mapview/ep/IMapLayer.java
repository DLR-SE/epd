package de.emir.epd.mapview.ep;

import de.emir.epd.mapview.views.map.AbstractMapLayerSettingsPanel;

/**
 * Interface for Map Layers. This stores information for each map layer.
 */
public interface IMapLayer {

	/**
	 * Sets the label of the map layer which should be displayed, for example in the Map Layer selection window.
	 * @param label Label to assign to the map layer.
	 * @return MapLayer with assigned label.
	 */
	IMapLayer label(String label);

	/**
	 * Sets the icon of the map layer which should be displayed, for example in the Map Layer selection window.
	 * @param path Path of the icon to assign to the map layer.
	 * @return MapLayer with assigned icon.
	 */
	IMapLayer icon(String path);

	/**
	 * Sets the settings panel of the map layer which should be displayed, for example in the Map Layer selection window.
	 * This allows to open a settings window directly in the map layer selection for quick access.
	 * @param settingsPanelClass Class of the AbstractMapLayerSettingsPanel to show.
	 * @return MapLayer with assigned settings panel.
	 */
	IMapLayer settingsPanel(Class<? extends AbstractMapLayerSettingsPanel> settingsPanelClass);
	
	
	/**
	 * Set the z order of the layer. Can be set to move a layer above or below another one
	 * @param o Z index to set. The higher the index the higher up the layer sits in the hierarchy.
	 * @return MapLayer with assigned z order.
	 */
	IMapLayer zOrder(double o);
	
}