package de.emir.epd.mapview.basics.tilesources;

import de.emir.epd.mapview.views.map.AbstractTileSource;

public class OpenStreetMapTileSource extends AbstractTileSource {

	private static final int max = 19;

	/**
	 * Default constructor
	 */
	public OpenStreetMapTileSource() {
		super("OpenStreetMap", 1, max - 2, max, 256, true, true, "http://tile.openstreetmap.org", "x", "y", "z");
	}

	@Override
	public String getTileUrl(int x, int y, int zoom) {
		zoom = max - zoom;
        return this.baseURL + "/" + zoom + "/" + x + "/" + y + ".png";
	}
	
	@Override
    public String getAttribution() {
        return "<html>\u00A9 <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap contributors</a></html>";
    }

}
