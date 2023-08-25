package de.emir.epd.mapview.views.map;

import de.emir.epd.mapview.ep.MapLayer;
import de.emir.epd.mapview.manager.MapViewManager;
import de.emir.epd.mapview.views.map.cache.StoreableTileCache;
import de.emir.epd.mapview.views.tools.AbstractMapViewTool;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.List;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class MapViewerWithTools implements Painter<JXMapViewer>, MouseListener, MouseWheelListener, MouseMotionListener,
		KeyListener, IDrawContext {

	private static final int PARALLEL_RENDER_THREADS = Runtime.getRuntime().availableProcessors();

	private DecimalFormat df = new DecimalFormat("0.00000");

	protected CustomJXMapViewer viewer;

	private Color infoBoxFG = new Color(255, 255, 255);
	private Color infoBoxBG = new Color(0, 0, 0, 180);

	private Color wpSelectionColor = new Color(0, 0, 0);

	private CustomPanMouseInputListener basicMouseListener;
	private ZoomMouseWheelListenerCursor basicMouseWheelListener;
	private PanKeyListener basicKeyListener;

	private MapViewerDrawRunnable drawRunnable;
	private Thread drawThread;

	private List<LayerController> layerControllers = new ArrayList<>();

	private Point mousePosition = null;

	private BlockingQueue<LayerController> renderJobQueue = new LinkedBlockingQueue<>();

	private CountDownLatch allJobsDoneLatch;

	private Map<String, LayerController> toolLayerControllers = new HashMap<>();

	private LayerController activeToolLayerController = null;

	private boolean isDirty;

	private MapViewerCursorAdapter cursorAdapter;

	private BufferedImage layerBuffer;

	private StoreableTileCache tileCache;

	public MapViewerWithTools() {

		viewer = new CustomJXMapViewer();

		cursorAdapter = new MapViewerCursorAdapter(viewer);

		viewer.setAddressLocation(new GeoPosition(0, 0));
		viewer.setZoom(10);

		basicMouseListener = new CustomPanMouseInputListener(viewer);
		basicMouseWheelListener = new ZoomMouseWheelListenerCursor(viewer);
		basicKeyListener = new PanKeyListener(viewer);

		viewer.addMouseListener(this);
		viewer.addMouseMotionListener(this);

		viewer.addMouseWheelListener(this);
		viewer.addKeyListener(this);

		viewer.setOverlayPainter(this);

		createLayers();

		viewer.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				updateSizes();

			}
		});

		viewer.addPropertyChangeListener("centerPosition", evt -> setAllLayersDirty());
		viewer.addPropertyChangeListener("zoom", evt -> setAllLayersDirty());

		createRenderThreads();

		createActiveToolListener();

	}

	private void setAllLayersDirty() {

		isDirty = true;

		for (LayerController lc : layerControllers) {
			lc.getLayer().setDirty(true);
		}

		if (activeToolLayerController != null) {
			activeToolLayerController.getLayer().setDirty(true);
		}

	}

	private void createActiveToolListener() {

		ServiceManager.get(MapViewManager.class).subscribeActiveToolChanged(opt -> {
			
			
			
			if (opt.isPresent() == false) {

				activeToolLayerController = null;
				return;
			}

			AbstractMapViewTool tool = opt.get();
			
			String toolID = tool.getId();
			LayerController lc = toolLayerControllers.get(toolID);

			if (lc == null) {
				createLayerControllerForTool(tool);
				lc = toolLayerControllers.get(toolID);
				lc.setSize(viewer.getSize());
			}

			activeToolLayerController = lc;
			
		});

	}

	private void createLayerControllerForTool(AbstractMapViewTool tool) {

		String toolId = tool.getId();

		LayerController lc = new LayerController(tool);
		toolLayerControllers.put(toolId, lc);

	}

	private void createRenderThreads() {

		for (int i = 0; i < PARALLEL_RENDER_THREADS; i++) {

			Thread renderThread = new Thread(new Runnable() {

				@Override
				public void run() {

					while (true) {
						try {

							LayerController lc = renderJobQueue.take();
							lc.handlePaint(MapViewerWithTools.this);
							allJobsDoneLatch.countDown();

						} catch (InterruptedException e) {
							return;
						}
					}

				}
			}, "MapViewRenderThread-" + i);

			renderThread.start();
		}

	}

	private void createLayers() {

		List<MapLayer> epLayers = ServiceManager.get(MapViewManager.class).getExtensionPoint().getOrderedLayers();

		
		
		for (MapLayer epLayer : epLayers) {

			String layerId = epLayer.getId();
			
			
			Class<? extends AbstractMapLayer> layerClass = epLayer.getLayerClass();

			try {

				AbstractMapLayer layer = layerClass.newInstance();
				layer.setId(layerId);
				layer.setCursorAdapter(cursorAdapter);
				layer.init();

				LayerController lc = new LayerController(layer);

				lc.subscribeVisibility(c -> setDirty(true));

				layerControllers.add(lc);

			} catch (Exception e) {

				e.printStackTrace();
			}

		}

	}

	public void redrawLayers() {

		int jobsCount = layerControllers.size();

		if (activeToolLayerController != null) {
			jobsCount += 1;
		}

		allJobsDoneLatch = new CountDownLatch(jobsCount);

		for (LayerController lc : layerControllers) {
			try {
				renderJobQueue.put(lc);
			} catch (InterruptedException e) {

				// Can't be reached cause LinkedBlockingQueue is to big
			}

		}

		if (activeToolLayerController != null) {
			try {

				renderJobQueue.put(activeToolLayerController);
			} catch (InterruptedException e) {

				// Can't be reached cause LinkedBlockingQueue is to big
			}
		}

		try {
			allJobsDoneLatch.await();

			fillBufferImage();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}

		// Combine Layers

	}

	private void fillBufferImage() {
		
		if(viewer.getSize().width == 0) {
			return;
		}
		
		BufferedImage tmpBuffer = createBufferedImage();
		
		if(tmpBuffer == null) {
			return;
		}
		
		Graphics2D g = tmpBuffer.createGraphics();

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Sometimes when ui changes happen the transform of jxmapviewer is not a
		// uniform matrix
		AffineTransform transform = g.getTransform();

		Color color = g.getColor();
		Color bg = g.getBackground();
		Font font = g.getFont();

		for (LayerController lc : layerControllers) {

			if (lc.getLayer().isVisible() == false) {
				continue;
			}

			g.setTransform(transform);
			g.setColor(color);
			g.setBackground(bg);
			g.setFont(font);

			lc.paint(g);

		}

		if (activeToolLayerController != null) {
			g.setTransform(transform);
			g.setColor(color);
			g.setBackground(bg);
			g.setFont(font);

			activeToolLayerController.paint(g);
		}
		
		g.dispose();
		layerBuffer = tmpBuffer;

	}

	private BufferedImage createBufferedImage() {

		Dimension size = viewer.getSize();
		
		if(size == null || size.getWidth() <= 0 || size.getHeight() <= 0) {
			return null;
		}
		
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		GraphicsConfiguration config = device.getDefaultConfiguration();
		
		return config.createCompatibleImage(size.width, size.height, Transparency.TRANSLUCENT);

	}

	@Override
	public void paint(Graphics2D g, JXMapViewer jxViewer, int width, int height) {

		g.drawImage(layerBuffer, 0, 0, null);
	}

	public void startDrawThread() {

		if (drawRunnable != null) {
			drawRunnable.stop();
		}

		drawRunnable = new MapViewerDrawRunnable(this);
		drawThread = new Thread(drawRunnable, "LayerDrawThread-Main");
		drawThread.start();

	}

	protected void stopDrawThread() {

		if (drawRunnable != null) {
			drawRunnable.stop();
		}

		drawRunnable = null;
		drawThread = null;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.emir.epd.mapview.views.IDrawContext#drawPositionInfo(java.awt.
	 * Graphics, de.emir.epd.mapview.views.Coordinate)
	 */
	@Override
	public void drawPositionInfo(BufferingGraphics2D g, GeoPosition gp, int x, int y) {

		String text = df.format(gp.getLatitude()) + " | " + df.format(gp.getLongitude());

		drawInfobox(g, text, (int) (x + 12), (int) (y - 12));

	}

	@Override
	public void drawPositionInfo(BufferingGraphics2D g, int x, int y) {
		GeoPosition gp = convert(new Point2D.Double(x, y));
		drawPositionInfo(g, gp, x, y);
		ServiceManager.get(MapViewManager.class).setCursorPosition(gp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.emir.epd.mapview.views.IDrawContext#drawFocusArc(java.awt.Graphics,
	 * de.emir.epd.mapview.views.Coordinate)
	 */
	@Override
	public void drawFocusArc(Graphics g, GeoPosition gp) {
		Point2D pp = convert(gp);
		g.setColor(wpSelectionColor);
		g.drawArc((int) (pp.getX() - 6), (int) (pp.getY() - 6), 11, 11, 0, 360);

	}

	public void gotoPosition(double longitude, double latitude, int zoom) {
		viewer.setAddressLocation(new GeoPosition(latitude, longitude));
		viewer.setZoom(zoom);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.emir.epd.mapview.views.IDrawContext#convert(double, double)
	 */
	@Override
	public Point2D convert(double longitude, double latitude) {
		return viewer.convertGeoPositionToPoint(new GeoPosition(latitude, longitude));
	}

	@Override
	public Point2D convert(Coordinate coordinate) {
		return viewer.convertGeoPositionToPoint(new GeoPosition(coordinate.getLatitude(), coordinate.getLongitude()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.emir.epd.mapview.views.IDrawContext#convert(de.emir.epd.mapview.views.
	 * Coordinate)
	 */
	@Override
	public Point2D convert(GeoPosition gp) {
		return convert(gp.getLongitude(), gp.getLatitude());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.emir.epd.mapview.views.IDrawContext#convert(int, int)
	 */
	@Override
	public GeoPosition convert(Point2D p) {
		return viewer.convertPointToGeoPosition(p);
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();

		if (tool != null) {
			tool.mouseDragged(e);
		}

		for (LayerController lc : layerControllers) {

			if (lc.getLayer().isVisible() == false) {
				continue;
			}

			lc.getLayer().mouseDragged(e);

			if (e.isConsumed() == true) {
				return;
			}
		}

		if (e.isConsumed() == false) {
			basicMouseListener.mouseDragged(e);
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		mousePosition = e.getPoint();

		AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();

		if (tool != null) {
			tool.mouseMoved(e);
		}

		for (LayerController lc : layerControllers) {

			if (lc.getLayer().isVisible() == false) {
				continue;
			}

			lc.getLayer().mouseMoved(e);

			if (e.isConsumed() == true) {

				cursorAdapter.setCursor();
				cursorAdapter.clear();
				return;
			}
		}

		if (e.isConsumed() == false) {
			basicMouseListener.mouseMoved(e);
		}

		cursorAdapter.setCursor();
		cursorAdapter.clear();

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();

		if (tool != null) {
			tool.mouseWheelMoved(e);
		}

		if (e.isConsumed() == false) {
			basicMouseWheelListener.mouseWheelMoved(e);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();

		if (tool != null) {
			tool.mouseClicked(e);
		}

		for (LayerController lc : layerControllers) {

			if (lc.getLayer().isVisible() == false) {
				continue;
			}

			lc.getLayer().mouseClicked(e);

			if (e.isConsumed() == true) {

				return;
			}
		}

		if (e.isConsumed() == false) {
			basicMouseListener.mouseClicked(e);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

		AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();

		if (tool != null) {
			tool.mousePressed(e);
		}

		for (LayerController lc : layerControllers) {

			if (lc.getLayer().isVisible() == false) {
				continue;
			}

			lc.getLayer().mousePressed(e);

			if (lc.getLayer().isVisible() == false) {
				continue;
			}

			if (e.isConsumed() == true) {
				return;
			}
		}

		if (e.isConsumed() == false) {
			basicMouseListener.mousePressed(e);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();

		if (tool != null) {
			tool.mouseReleased(e);
		}

		for (LayerController lc : layerControllers) {

			if (lc.getLayer().isVisible() == false) {
				continue;
			}

			lc.getLayer().mouseReleased(e);

			if (e.isConsumed() == true) {
				return;
			}
		}

		if (e.isConsumed() == false) {
			basicMouseListener.mouseReleased(e);
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {

		mousePosition = e.getPoint();

		AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();

		if (tool != null) {
			tool.mouseEntered(e);
		}

		for (LayerController lc : layerControllers) {

			if (lc.getLayer().isVisible() == false) {
				continue;
			}

			lc.getLayer().mouseEntered(e);

			if (e.isConsumed() == true) {
				return;
			}
		}

		if (e.isConsumed() == false) {
			basicMouseListener.mouseEntered(e);
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {

		mousePosition = null;

		AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();

		if (tool != null) {
			tool.mouseExited(e);
		}

		for (LayerController lc : layerControllers) {

			if (lc.getLayer().isVisible() == false) {
				continue;
			}

			lc.getLayer().mouseExited(e);

			if (e.isConsumed() == true) {
				return;
			}
		}

		if (e.isConsumed() == false) {
			basicMouseListener.mouseExited(e);
		}

	}

	public CustomJXMapViewer getJXMapViewer() {
		return viewer;
	}

	public void setTileSource(TileFactoryInfo ts) {

		DefaultTileFactory dtf = new DefaultTileFactory(ts);
		
		if(tileCache != null) {
			tileCache.dispose();
		}
		
		tileCache = new StoreableTileCache();
		
		dtf.setTileCache(tileCache);
		int oldZoom = viewer.getZoom();
		GeoPosition oldCenter = viewer.getCenterPosition();
		viewer.setTileFactory(dtf);
		viewer.setCenterPosition(oldCenter);
		viewer.setZoom(oldZoom);

	}

	@Override
	public void keyTyped(KeyEvent e) {

		AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();

		if (tool != null) {
			tool.keyTyped(e);
		}

		if (e.isConsumed() == false) {
			basicKeyListener.keyTyped(e);
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();

		if (tool != null) {
			tool.keyPressed(e);
		}

		if (e.isConsumed() == false) {
			basicKeyListener.keyPressed(e);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();

		if (tool != null) {
			tool.keyReleased(e);
		}

		if (e.isConsumed() == false) {
			basicKeyListener.keyReleased(e);
		}

	}

	public void updateSizes() {
		for (LayerController lc : layerControllers) {
			lc.setSize(viewer.getSize());
		}

		for (LayerController lc : toolLayerControllers.values()) {
			lc.setSize(viewer.getSize());
		}

	}

	@Override
	public Dimension getSize() {
		return viewer.getSize();
	}

	@Override
	public Point getMousePosition() {
		return mousePosition;
	}

	@Override
	public void drawInfobox(BufferingGraphics2D g, String text, int x, int y, int hAlign, int vAlign) {

		int width = g.getFontMetrics().stringWidth(text);
		int height = g.getFontMetrics().getHeight();

		int offX = 0;
		int offY = 0;

		if (hAlign == SwingConstants.CENTER) {
			offX = -width / 2;
		} else if (hAlign == SwingConstants.RIGHT) {
			offX = -width;
		}

		if (vAlign == SwingConstants.CENTER) {
			offY = -height / 2;
		} else if (vAlign == SwingConstants.BOTTOM) {
			offY = -height;
		}

		g.setColor(infoBoxBG);
		g.fillRoundRect(x + offX, y - height + offY, width + 4, height + 4, 5, 5);

		g.setColor(infoBoxFG);
		g.drawString(text, x + 2 + offX, y - 2 + offY);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.emir.epd.mapview.views.IDrawContext#drawInfobox(java.awt.Graphics,
	 * java.lang.String, int, int)
	 */
	@Override
	public void drawInfobox(BufferingGraphics2D g, String text, int x, int y) {
		drawInfobox(g, text, x, y, SwingConstants.LEFT, SwingConstants.TOP);
	}

	@Override
	public int getZoom() {
		return viewer.getZoom();
	}

	public boolean isDirty() {
		return isDirty;
	}

	public void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public List<LayerController> getLayerController() {
		return Collections.unmodifiableList(layerControllers);
	}

	@Override
	public Rectangle getViewportBounds() {
		return viewer.getViewportBounds();
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(0, 0, getViewportBounds().width, getViewportBounds().height);
	}

	public LayerController getActiveToolLayerController() {
		return activeToolLayerController;
	}

	@Override
	public int getTileSize() {
		return viewer.getTileFactory().getTileSize(0);
	}

	@Override
	public GeoPosition getCenterPosition() {
		return viewer.getCenterPosition();
	}

	@Override
	public int getMaximumZoomLevel() {
		return viewer.getTileFactory().getInfo().getMaximumZoomLevel();
	}

}
