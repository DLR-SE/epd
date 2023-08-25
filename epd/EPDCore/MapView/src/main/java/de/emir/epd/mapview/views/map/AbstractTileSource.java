package de.emir.epd.mapview.views.map;

import org.jxmapviewer.viewer.TileFactoryInfo;

public abstract class AbstractTileSource extends TileFactoryInfo {

	public AbstractTileSource(String name, int minimumZoomLevel, int maximumZoomLevel, int totalMapZoom, int tileSize,
			boolean xright2left, boolean ytop2bottom, String baseURL, String xparam, String yparam, String zparam) {
		super(name, minimumZoomLevel, maximumZoomLevel, totalMapZoom, tileSize, xright2left, ytop2bottom, baseURL, xparam, yparam, zparam);
	}


	public AbstractTileSource(int minimumZoomLevel, int maximumZoomLevel, int totalMapZoom, int tileSize, boolean xright2left,
			boolean ytop2bottom, String baseURL, String xparam, String yparam, String zparam) {
		super(minimumZoomLevel, maximumZoomLevel, totalMapZoom, tileSize, xright2left, ytop2bottom, baseURL, xparam, yparam, zparam);
		
	}
	

}
