package de.emir.epd.openseamap;

import de.emir.epd.mapview.views.map.AbstractTileSource;

public class OpenSeaMapTileSource extends AbstractTileSource {

	private static final int max = 19;

	/**
	 * Default constructor
	 */
	public OpenSeaMapTileSource() {
		super("OpenSeaMap", 1, max - 2, max, 256, true, true, "https://tiles.openseamap.org/seamark", "x", "y", "z");
//		super("OpenWeatherMap", 1, max - 2, max, 256, true, true, "http://openportguide.org/tiles/actual/significant_wave_height/5", "x", "y", "z");
	}

	@Override
	public String getTileUrl(int x, int y, int zoom) {
		zoom = max - zoom;
        return this.baseURL + "/" + zoom + "/" + x + "/" + y + ".png";
	}

}
