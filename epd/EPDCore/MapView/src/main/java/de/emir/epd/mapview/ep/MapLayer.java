package de.emir.epd.mapview.ep;

import de.emir.epd.mapview.views.map.AbstractMapLayer;
import de.emir.epd.mapview.views.map.AbstractMapLayerSettingsPanel;

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

	@Override
	public IMapLayer icon(String path) {
		this.iconPath = path;
		return this;
	}

	public String getIconPath() {
		return iconPath;
	}

	@Override
	public IMapLayer settingsPanel(Class<? extends AbstractMapLayerSettingsPanel> settingsPanelClass) {
		
		this.settingsPanel = new MapLayerSettingsPanel();
		this.settingsPanel.setSettingsPanelClass(settingsPanelClass);
		
		return this;

	}
	
	public MapLayerSettingsPanel getSettingsPanel() {
		return settingsPanel;
	}

	@Override
	public IMapLayer zOrder(double index) {
		this.zOrder = index;
		return this;
	}
	
	public double getzOrder() {
		return zOrder;
	}

}
