package de.emir.epd.mapview.ep;

import de.emir.epd.mapview.views.map.AbstractMapLayer;
import de.emir.epd.mapview.views.map.AbstractMapLayerSettingsPanel;

/**
 * Wrapper class for Map Layers. This stores information for each map layer which is registered.
 */
public class MapLayer implements IMapLayer {

	private Class<? extends AbstractMapLayer> layerClass;
	private String id;
	private String label;
	private String iconPath;
	private MapLayerSettingsPanel settingsPanel;
	private double zOrder = 1.0;

	public MapLayer(String id) {
		this.id = id;
	}

	public void setLayerClass(Class<? extends AbstractMapLayer> layerClass) {
		this.layerClass = layerClass;

	}

	public Class<? extends AbstractMapLayer> getLayerClass() {
		return layerClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.emir.epd.mapview.ep.IMapLayer#label(java.lang.String)
	 */
	@Override
	public IMapLayer label(String label) {
		this.label = label;
		return this;
	}

	public String getLabel() {
		return label;
	}

	public String getId() {
		return id;
	}

	/**
	 * Sets the icon of the map layer which should be displayed, for example in the Map Layer selection window.
	 * @param path Path of the icon to assign to the map layer.
	 * @return MapLayer with assigned icon.
	 */
	@Override
	public IMapLayer icon(String path) {
		this.iconPath = path;
		return this;
	}

	public String getIconPath() {
		return iconPath;
	}

	/**
	 * Sets the settings panel of the map layer which should be displayed, for example in the Map Layer selection window.
	 * This allows to open a settings window directly in the map layer selection for quick access.
	 * @param settingsPanelClass Class of the AbstractMapLayerSettingsPanel to show.
	 * @return MapLayer with assigned settings panel.
	 */
	@Override
	public IMapLayer settingsPanel(Class<? extends AbstractMapLayerSettingsPanel> settingsPanelClass) {
		
		this.settingsPanel = new MapLayerSettingsPanel();
		this.settingsPanel.setSettingsPanelClass(settingsPanelClass);
		
		return this;

	}
	
	public MapLayerSettingsPanel getSettingsPanel() {
		return settingsPanel;
	}

	/**
	 * Set the z order of the layer. Can be set to move a layer above or below another one
	 * @param index Z index to set. The higher the index the higher up the layer sits in the hierarchy.
	 * @return MapLayer with assigned z order.
	 */
	@Override
	public IMapLayer zOrder(double index) {
		this.zOrder = index;
		return this;
	}
	
	public double getzOrder() {
		return zOrder;
	}

}
