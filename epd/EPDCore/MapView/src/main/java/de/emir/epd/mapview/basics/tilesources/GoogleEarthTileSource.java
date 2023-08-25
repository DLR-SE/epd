package de.emir.epd.mapview.basics.tilesources;

import de.emir.epd.mapview.views.map.AbstractTileSource;

public class GoogleEarthTileSource extends AbstractTileSource {

	public GoogleEarthTileSource() {
		super(0, 15, 17, 256, true, true, "http://mt1.google.com/vt/lyrs=y", "x", "y", "z");

	}

	public String getTileUrl(int x, int y, int zoom) {
		zoom = 17 - zoom;
		
		return this.baseURL + "&x=" + x + "&y=" + y + "&z=" + zoom;
	}
	
	@Override
    public String getAttribution() {
        return "<html>\u00A9 <a href=\"https://www.google.com/help/terms_maps/\">Google</a></html>";
    }
}
