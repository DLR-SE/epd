package de.emir.epd.mapview.ep;

import de.emir.epd.mapview.views.tools.AbstractMapViewTool;

public class MapTool implements IMapTool {

	private String id;
	private Class<? extends AbstractMapViewTool> toolClass;

	public MapTool(String id) {
		this.id = id;
	}

	public void setToolClass(Class<? extends AbstractMapViewTool> toolClass) {
		this.toolClass = toolClass;
		
	}
	
	public String getId() {
		return id;
	}
	
	public Class<? extends AbstractMapViewTool> getToolClass() {
		return toolClass;
	}

}
