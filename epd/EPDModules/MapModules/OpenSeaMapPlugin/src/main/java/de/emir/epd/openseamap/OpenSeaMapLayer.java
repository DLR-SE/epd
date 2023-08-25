package de.emir.epd.openseamap;

import de.emir.epd.mapview.views.map.AbstractMapLayer;
import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;
import de.emir.epd.mapview.views.map.cache.StoreableTileCache;
import de.emir.epd.openseamap.ids.OpenSeaMapBasics;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import org.jxmapviewer.viewer.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Draws an additional layer to the map to display OpenSeaMap information.
 * 
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public class OpenSeaMapLayer extends AbstractMapLayer implements PropertyChangeListener {
	/** The tile factory to load map tiles from. */
	private DefaultTileFactory tf;
	private PropertyContext context;
	private IProperty<Boolean> enabledProp;

	private long lastDrawTime;
	private boolean enabled;
	
	private StoreableTileCache tileCache;

	/**
	 * Default constructor. Creates the tile factory.
	 */
	public OpenSeaMapLayer() {
		super();
		// super(false);
		context = PropertyStore.getContext(OpenSeaMapBasics.OPENSEA_MAP_PROP_CONTEXT);
		enabledProp = context.getProperty(OpenSeaMapBasics.OPENSEA_MAP_PROP_ENABLED, true);
		enabledProp.addPropertyChangeListener(this);
		updateSettings();

		tf = new DefaultTileFactory(new OpenSeaMapTileSource());
		if(tileCache != null) {
			tileCache.dispose();
		}
		
		tileCache = new StoreableTileCache();
		
		tf.setTileCache(tileCache);
		tf.addTileListener(new TileListener() {
			@Override
			public void tileLoaded(Tile tile) {
				OpenSeaMapLayer.this.setDirty(true);

			}
		});
		startTimeoutCheckerThread();
	}

	private void updateSettings() {
		enabled = (boolean) enabledProp.getValue();
		OpenSeaMapLayer.this.setDirty(true);
	}

	@Override
	public void paint(BufferingGraphics2D g, IDrawContext c) {

		Rectangle2D view = new Rectangle2D.Double();
		Rectangle2D temp = c.getViewportBounds();
		view.setRect(0, 0, temp.getWidth(), temp.getHeight());

		int zoom = c.getZoom();

		GeoPosition centerPosition = c.getCenterPosition();

		int maxZoom = c.getMaximumZoomLevel();

		int maxZoomTemp = tf.getInfo().getMaximumZoomLevel();

		int dMaxZoom = maxZoom - maxZoomTemp;

		// 512
		int size = tf.getTileSize(zoom);

		double zoomAddition = Math.log10(size / (double) c.getTileSize()) / Math.log10(2);
		zoom = zoom + Math.round((float) zoomAddition) - dMaxZoom;

		//return if zoom > max
		if(zoom < 0){
			return;
		}

		Point2D center = tf.geoToPixel(centerPosition, zoom);

		// Coordinates in world pixel space
		Rectangle viewPortBounds = c.getViewportBounds();

		viewPortBounds = calculateViewportBounds(viewPortBounds.width, viewPortBounds.height, center);

		viewPortBounds.x = viewPortBounds.x;
		viewPortBounds.y = viewPortBounds.y;
		// calculate the "visible" viewport area in tiles
		int numWide = (int) (viewPortBounds.width / size + 2);
		int numHigh = (int) (viewPortBounds.height / size + 2);

		TileFactoryInfo info = tf.getInfo();

		// Calculate world pixel coordinates of the first tile within the view port
		int tpx = (int) Math.floor(viewPortBounds.getX() / info.getTileSize(0));
		int tpy = (int) Math.floor(viewPortBounds.getY() / info.getTileSize(0));

		for (int x = tpx; x <= tpx + numWide; x++) {
			for (int y = tpy; y <= tpy + numHigh; y++) {

				int canvasX = x * size - viewPortBounds.x;
				int canvasY = y * size - viewPortBounds.y;

				Tile tile = tf.getTile(x, y, zoom);
				BufferedImage bufferedImage = tile.getImage();

				if (bufferedImage == null) {
					continue;
				}

				Rectangle2D rectangle2D = new Rectangle2D.Double();
				rectangle2D.setRect(canvasX, canvasY, bufferedImage.getWidth(), bufferedImage.getHeight());

				// prevents image from being drawn out of the viewport
				if (view.intersects(rectangle2D)) {
					g.drawImage(bufferedImage, canvasX, canvasY);
				}
			}
		}

		lastDrawTime = System.currentTimeMillis();
	}

	private Rectangle calculateViewportBounds(int width, int height, Point2D center) {
		// calculate the "visible" viewport area in pixels (insets excluded cause
		// parameters width and height should already take them into account)
		double viewportX = (center.getX() - width / 2.0);
		double viewportY = (center.getY() - height / 2.0);
		return new Rectangle((int) viewportX, (int) viewportY, width, height);
	}

	private void startTimeoutCheckerThread() {
		Thread timeOutThread = new Thread(() -> {
			while (true) {

				if (System.currentTimeMillis() - lastDrawTime > 500) {

					setDirty(true);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						ULog.error(e);
					}
				} else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						ULog.error(e);
					}
				}
			}
		});
		timeOutThread.start();
	}

	@Override
	public boolean isVisibilityUserControlled() {
		return true;
	}

	@Override
	public void modelChanged() {

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		updateSettings();
		OpenSeaMapLayer.this.setDirty(true);
	}
}