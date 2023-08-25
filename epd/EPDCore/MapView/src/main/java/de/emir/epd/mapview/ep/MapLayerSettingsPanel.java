package de.emir.epd.mapview.ep;

import de.emir.epd.mapview.views.map.AbstractMapLayerSettingsPanel;

public class MapLayerSettingsPanel implements IMapLayerSettingsPanel {
	
	private Class<? extends AbstractMapLayerSettingsPanel> settingsPanelClass;
	private String id;
	
	
	public void setSettingsPanelClass(Class<? extends AbstractMapLayerSettingsPanel> settingsPanelClass) {
		this.settingsPanelClass = settingsPanelClass;
	}
	
	
	public Class<? extends AbstractMapLayerSettingsPanel> getSettingsPanelClass() {
		return settingsPanelClass;
	}


	public void setLayerId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
