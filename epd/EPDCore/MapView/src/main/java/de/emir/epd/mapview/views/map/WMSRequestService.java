package de.emir.epd.mapview.views.map;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.jxmapviewer.viewer.util.MercatorUtils;
import org.jxmapviewer.viewer.wms.WMSService;

import de.emir.epd.mapview.ids.MVBasic;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.IProperty;

/**
 * This class uses the WMSService of JXMapViewer to create WMS Request URLs Its
 * a singleton. Otherwise it would be recreated if the MapView changes its
 * TileSource and we would have to register and deregister the property
 * listeners again and again
 * 
 * @author fklein
 *
 */
public class WMSRequestService extends WMSService implements PropertyChangeListener {

	private static WMSRequestService instance;
	private IProperty urlProp;
	private IProperty layerProp;

	private WMSRequestService() {

		PropertyContext context = PropertyStore.getContext(MVBasic.MAP_VIEW_PROP_CONTEXT);
		urlProp = context.getProperty(MVBasic.MAP_VIEW_PROP_WMS_URL, "");
		layerProp = context.getProperty(MVBasic.MAP_VIEW_PROP_WMS_LAYER, "");

		urlProp.addPropertyChangeListener(this);
		layerProp.addPropertyChangeListener(this);

		updateSettings();
	}

	private void updateSettings() {

		setBaseUrl((String) urlProp.getValue());
		setLayer((String) layerProp.getValue());

	}

	public static WMSRequestService getInstance() {

		if (instance == null) {
			instance = new WMSRequestService();
		}

		return instance;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		updateSettings();
	}

	@Override
	public String toWMSURL(int x, int y, int zoom, int tileSize) {
		String format = "image/png";
		String styles = "";
		String srs = "EPSG:4326";
        int circumference = widthOfWorldInPixels(zoom, tileSize);
		double radius = circumference / (2 * Math.PI);
		double ulx = MercatorUtils.xToLong(x * tileSize, radius);
		double uly = MercatorUtils.yToLat(y * tileSize, radius);
		double lrx = MercatorUtils.xToLong((x + 1) * tileSize, radius);
		double lry = MercatorUtils.yToLat((y + 1) * tileSize, radius);
		String bbox = ulx + "," + uly + "," + lrx + "," + lry;

		String baseURL = getBaseUrl();

		if (baseURL == null) {
			baseURL = "";
		}

		if (baseURL.contains("://") == false) {
			baseURL = "http://" + baseURL;
		}

		if (baseURL.contains("?") == false) {

			baseURL += "?";

		} else {

			if (baseURL.endsWith("?") == false && baseURL.endsWith("&") == false) {
				baseURL += "&";
			}

		}

		String url = baseURL + "version=1.1.1&request=" + "GetMap&Layers=" + getLayer() + "&format=" + format + "&BBOX="
				+ bbox + "&width=" + tileSize + "&height=" + tileSize + "&SRS=" + srs +
				// "&transparent=TRUE"+
				"";

		if (baseURL.toLowerCase().contains("styles") == false) {
			url += "&Styles=" + styles;
		}

		return url;
	}

	private int widthOfWorldInPixels(int zoom, int TILE_SIZE) {
		// int TILE_SIZE = 256;
		int tiles = (int) Math.pow(2, zoom);
        return TILE_SIZE * tiles;
	}

}
