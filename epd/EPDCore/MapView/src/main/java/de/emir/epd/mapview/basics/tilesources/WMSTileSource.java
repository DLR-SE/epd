package de.emir.epd.mapview.basics.tilesources;

import org.jxmapviewer.viewer.wms.WMSService;

import de.emir.epd.mapview.views.map.AbstractTileSource;
import de.emir.epd.mapview.views.map.WMSRequestService;

public class WMSTileSource extends AbstractTileSource {

	private WMSService service;

	public WMSTileSource() {
		super(0, 15, 17, 512, true, true, "", "x", "y", "zoom");
		
		service = WMSRequestService.getInstance();
	}
	
	@Override
	public String getTileUrl(int x, int y, int zoom)
	{
		int zz = 17 - zoom;
		int z = 4;
		z = (int) Math.pow(2, (double) zz - 1);
		return service.toWMSURL(x - z, z - 1 - y, zz, getTileSize(zoom));
	}

}
