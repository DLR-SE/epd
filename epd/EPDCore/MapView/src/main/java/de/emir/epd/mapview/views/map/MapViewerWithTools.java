package de.emir.epd.mapview.views.map;

import de.emir.epd.mapview.ep.MapLayer;
import de.emir.epd.mapview.manager.MapViewManager;
import de.emir.epd.mapview.views.map.cache.StoreableTileCache;
import de.emir.epd.mapview.views.tools.AbstractMapViewTool;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.logging.ULog;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.painter.AbstractPainter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.List;
import java.util.*;
import java.util.concurrent.*;

/**
 * The MapViewerWithTools handles the rendering delegation for all map layers to the JXMapViewer.
 */
public class MapViewerWithTools extends AbstractPainter<JXMapViewer> implements MouseListener, MouseWheelListener, MouseMotionListener,
        KeyListener, IDrawContext {

    // This specifies the number of threads used for rendering map layers. It should be at least 4 but up to the
    // number of available processors.
    private static final int PARALLEL_RENDER_THREADS = Math.min(4, Runtime.getRuntime().availableProcessors());

    private final DecimalFormat df = new DecimalFormat("0.00000");

    protected CustomJXMapViewer viewer;

    private final Color infoBoxFG = new Color(255, 255, 255);
    private final Color infoBoxBG = new Color(0, 0, 0, 180);

    private final Color wpSelectionColor = new Color(0, 0, 0);

    private final CustomPanMouseInputListener basicMouseListener;
    private final ZoomMouseWheelListenerCursor basicMouseWheelListener;
    private final PanKeyListener basicKeyListener;

    private MapViewerDrawRunnable drawRunnable;
    private Thread drawThread;

    private final List<LayerController> layerControllers = new ArrayList<>();

    private Point mousePosition = null;

    private final Map<String, LayerController> toolLayerControllers = new HashMap<>();

    private volatile LayerController activeToolLayerController = null;

    private final MapViewerCursorAdapter cursorAdapter;

    private StoreableTileCache tileCache;

    private int threadNumber = 0;

    private final ExecutorService renderExecutor = Executors.newFixedThreadPool(PARALLEL_RENDER_THREADS, r -> {
        Thread t = new Thread(r, "MapViewRenderThread-" + threadNumber);
        threadNumber++;
        t.setDaemon(true);
        return t;
    });

    /**
     * Reference to the MapView containing this MapViewerWithTools.
     */
    private final MapView mapView;

    public MapViewerWithTools(MapView mapView) {
        super(true);
        this.mapView = mapView;
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
        createActiveToolListener();
    }

    /**
     * Sets all registered LayerControllers to dirty to force redrawing of all layers.
     */
    private void setAllLayersDirty() {
        setDirty(true);
        for (LayerController lc : layerControllers) {
            lc.getLayer().setDirty(true);
        }
        if (activeToolLayerController != null) {
            activeToolLayerController.getLayer().setDirty(true);
        }
    }

    private void createActiveToolListener() {
        ServiceManager.get(MapViewManager.class).subscribeActiveToolChanged(opt -> {
            if (opt.isEmpty()) {
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

    /**
     * Loads all registered Layers and assigns a LayerController for each layer. This LayerController is used for
     * executing Map Layer draw calls.
     */
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
                ULog.error("Error while adding layer controller to MapViewerWithTools: {}", e.getMessage());
            }
        }
    }

    /**
     * Issues a redraw call to all available LayerControllers.
     */
    public void redrawLayers() {
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (LayerController lc : layerControllers) {
            if (!lc.getLayer().isVisible()) continue;
            futures.add(CompletableFuture.runAsync(() -> lc.handlePaint(this), renderExecutor));
        }
        if (activeToolLayerController != null) {
            futures.add(CompletableFuture.runAsync(() -> activeToolLayerController.handlePaint(this), renderExecutor));
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).thenRun(() -> {
            setDirty(true);
        });
    }

    /**
     * Starts the main thread for Drawing.
     */
    public void startDrawThread() {
        if (drawRunnable != null) {
            drawRunnable.stop();
        }
        drawRunnable = new MapViewerDrawRunnable(this);
        drawThread = new Thread(drawRunnable, "LayerDrawThread-Main");
        drawThread.start();
    }

    /**
     * Stops the main thread for drawing.
     */
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

    /**
     * Draws the current position info for the mouse cursor.
     * @param g Graphics to draw position info to.
     * @param x X position of the info on screen.
     * @param y Y position of the info on screen.
     */
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

    /**
     * Pans and zooms to a specified position.
     * @param longitude Longitude to go to
     * @param latitude Latitude to go to.
     * @param zoom Zoom level to set.
     */
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

    /**
     * Mouse listener for drag events. Issues redraw command to all LayerControllers.
     * @param e the event to be processed.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();
        if (tool != null) {
            tool.mouseDragged(e);
        }
        for (LayerController lc : layerControllers) {
            if (!lc.getLayer().isVisible()) {
                continue;
            }
            lc.getLayer().mouseDragged(e);
            if (e.isConsumed()) {
                return;
            }
        }
        if (!e.isConsumed()) {
            basicMouseListener.mouseDragged(e);
        }
    }

    /**
     * Mouse listener for move events. Issues redraw command to all LayerControllers.
     * @param e the event to be processed.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition = e.getPoint();
        AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();
        if (tool != null) {
            tool.mouseMoved(e);
        }
        for (LayerController lc : layerControllers) {
            if (!lc.getLayer().isVisible()) {
                continue;
            }
            lc.getLayer().mouseMoved(e);
            if (e.isConsumed()) {
                cursorAdapter.setCursor();
                cursorAdapter.clear();
                return;
            }
        }
        if (!e.isConsumed()) {
            basicMouseListener.mouseMoved(e);
        }
        cursorAdapter.setCursor();
        cursorAdapter.clear();
    }

    /**
     * Mouse listener for mouse wheel move events. Issues redraw command to all LayerControllers.
     * @param e the event to be processed.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();
        if (tool != null) {
            tool.mouseWheelMoved(e);
        }
        if (!e.isConsumed()) {
            basicMouseWheelListener.mouseWheelMoved(e);
        }
    }

    /**
     * Mouse listener for click events. Issues redraw command to all LayerControllers.
     * @param e the event to be processed.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();
        if (tool != null) {
            tool.mouseClicked(e);
        }
        for (LayerController lc : layerControllers) {
            if (!lc.getLayer().isVisible()) {
                continue;
            }
            lc.getLayer().mouseClicked(e);
            if (e.isConsumed()) {
                return;
            }
        }
        if (!e.isConsumed()) {
            basicMouseListener.mouseClicked(e);
        }
    }

    /**
     * Mouse listener for press events. Issues redraw command to all LayerControllers.
     * @param e the event to be processed.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();
        if (tool != null) {
            tool.mousePressed(e);
        }
        for (LayerController lc : layerControllers) {
            if (!lc.getLayer().isVisible()) {
                continue;
            }
            lc.getLayer().mousePressed(e);
            if (!lc.getLayer().isVisible()) {
                continue;
            }
            if (e.isConsumed()) {
                return;
            }
        }
        if (!e.isConsumed()) {
            basicMouseListener.mousePressed(e);
        }
    }

    /**
     * Mouse listener for release events. Issues redraw command to all LayerControllers.
     * @param e the event to be processed.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();
        if (tool != null) {
            tool.mouseReleased(e);
        }
        for (LayerController lc : layerControllers) {
            if (!lc.getLayer().isVisible()) {
                continue;
            }
            lc.getLayer().mouseReleased(e);
            if (e.isConsumed()) {
                return;
            }
        }
        if (!e.isConsumed()) {
            basicMouseListener.mouseReleased(e);
        }
    }

    /**
     * Mouse listener for mouse enter events. Issues redraw command to all LayerControllers.
     * @param e the event to be processed.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        mousePosition = e.getPoint();
        AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();
        if (tool != null) {
            tool.mouseEntered(e);
        }
        for (LayerController lc : layerControllers) {
            if (!lc.getLayer().isVisible()) {
                continue;
            }
            lc.getLayer().mouseEntered(e);
            if (e.isConsumed()) {
                return;
            }
        }
        if (!e.isConsumed()) {
            basicMouseListener.mouseEntered(e);
        }
    }

    /**
     * Mouse listener for mouse exit events. Issues redraw command to all LayerControllers.
     * @param e the event to be processed.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        mousePosition = null;
        AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();
        if (tool != null) {
            tool.mouseExited(e);
        }
        for (LayerController lc : layerControllers) {
            if (!lc.getLayer().isVisible()) {
                continue;
            }
            lc.getLayer().mouseExited(e);
            if (e.isConsumed()) {
                return;
            }
        }
        if (!e.isConsumed()) {
            basicMouseListener.mouseExited(e);
        }
    }

    /**
     * Gets the underlying JXMapViewer component.
     */
    public CustomJXMapViewer getJXMapViewer() {
        return viewer;
    }

    /**
     * Sets the tile source for the map layer.
     * @param ts TileSourceFactory to use.
     */
    public void setTileSource(TileFactoryInfo ts) {
        DefaultTileFactory dtf = new DefaultTileFactory(ts);
        if (tileCache != null) {
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

    /**
     * Delegates keystrokes to the map.
     * @param e the event to be processed.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();
        if (tool != null) {
            tool.keyTyped(e);
        }
        if (!e.isConsumed()) {
            basicKeyListener.keyTyped(e);
        }
    }

    /**
     * Delegates key presses to the map.
     * @param e the event to be processed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();
        if (tool != null) {
            tool.keyPressed(e);
        }
        if (!e.isConsumed()) {
            basicKeyListener.keyPressed(e);
        }
    }

    /**
     * Overrides the setDirty flag to allow access outside of the protected scope. This tells the MapViewer
     * that the redrawing should be initiated.
     * @param d True if redraw should be done.
     */
    @Override
    public void setDirty(boolean d) {
        super.setDirty(d);
    }

    /**
     * Gets the dirty flag. If true, the MapViewer should be redrawn.
     * @return True if redraw should be done.
     */
    @Override
    public boolean isDirty() {
        return super.isDirty();
    }

    /**
     * Delegates the key release to the map.
     * @param e the event to be processed.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        AbstractMapViewTool tool = ServiceManager.get(MapViewManager.class).getActiveTool();
        if (tool != null) {
            tool.keyReleased(e);
        }
        if (!e.isConsumed()) {
            basicKeyListener.keyReleased(e);
        }
    }

    /**
     * Issues a size update to all layers. This is to allow layers to scale dynamically.
     */
    public void updateSizes() {
        for (LayerController lc : layerControllers) {
            lc.setSize(viewer.getSize());
        }
        for (LayerController lc : toolLayerControllers.values()) {
            lc.setSize(viewer.getSize());
        }
    }

    /**
     * Gets the current size of the map.
     * @return Size of the map.
     */
    @Override
    public Dimension getSize() {
        return viewer.getSize();
    }

    /**
     * Gets the current mouse position.
     * @return Mouse position.
     */
    @Override
    public Point getMousePosition() {
        return mousePosition;
    }

    /**
     * Draws an info box on the map at a specified position. This could display useful text on the map.
     * @param g Graphics to draw to.
     * @param text Text to show.
     * @param x X position of the text.
     * @param y Y position of the text.
     * @param hAlign Horizontal align. Should be one of SwingConstants.CENTER, RIGHT or LEFT.
     * @param vAlign Vertical align. Should be one of SwingConstants.CENTER, BOTTOM or TOP.
     */
    @Override
    public void drawInfobox(BufferingGraphics2D g, String text, int x, int y, int hAlign, int vAlign) {
        String[] stringParts = text.split("\n");
        if (stringParts.length <= 1) {
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
        } else {
            int height = g.getFontMetrics().getHeight() * stringParts.length;
            int width = 0;
            for (String string : stringParts) {
                int maxWidth = g.getFontMetrics().stringWidth(string);
                if (maxWidth > width) {
                    width = maxWidth;
                }
            }
            g.setColor(infoBoxBG);
            g.fillRoundRect(x - 2, y - g.getFontMetrics().getHeight(), width + 4, height + g.getFontMetrics().getHeight() - 4, 5, 5);

            g.setColor(infoBoxFG);
            for (int i = 0; i < stringParts.length; i++) {
                g.drawString(stringParts[i], x, y + g.getFontMetrics().getHeight() * i);
            }
        }
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

    /**
     * Gets the current zoom of the map.
     * @return
     */
    @Override
    public int getZoom() {
        return viewer.getZoom();
    }

    /**
     * Does the painting of the layers. This method will will the AbstractPainters internal buffer which is then drawn
     * as soon as the dirty flag is set to true.
     * @param g Graphics to draw to.
     * @param jxMapViewer JXMapViewer instance to use.
     * @param width Width of the map.
     * @param height Height of the map.
     */
    @Override
    protected void doPaint(Graphics2D g, JXMapViewer jxMapViewer, int width, int height) {
        g.setBackground(new Color(0, 0, 0, 0));
        // Sometimes when ui changes happen the transform of jxmapviewer is not a
        // uniform matrix
        AffineTransform transform = g.getTransform();
        Color color = g.getColor();
        Color bg = g.getBackground();
        Font font = g.getFont();
        Paint paint = g.getPaint();
        for (LayerController lc : layerControllers) {
            if (!lc.getLayer().isVisible()) {
                continue;
            }
            g.setTransform(transform);
            g.setColor(color);
            g.setBackground(bg);
            g.setPaint(paint);
            g.setFont(font);
            lc.paint(g);
        }
        if (activeToolLayerController != null) {
            g.setTransform(transform);
            g.setColor(color);
            g.setBackground(bg);
            g.setPaint(paint);
            g.setFont(font);
            activeToolLayerController.paint(g);
        }
        g.dispose();
    }

    /**
     * Gets all registered LayerControllers.
     * @return List of all registered LayerControllers.
     */
    public List<LayerController> getLayerController() {
        return Collections.unmodifiableList(layerControllers);
    }

    /**
     * Gets the current bounds of the map viewport.
     * @return Dimensions of the viewport.
     */
    @Override
    public Rectangle getViewportBounds() {
        return viewer.getViewportBounds();
    }

    /**
     * Gets the current bounds of the map. In comparison to getViewportBounds, this returns a rectangle that starts
     * at 0,0 with the width and height of the viewport instead of the actual starting point of the viewport relative
     * to the application.
     * @return Dimensions of the viewport starting at 0,0.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(0, 0, getViewportBounds().width, getViewportBounds().height);
    }

    /**
     * Gets the currently active tool layer controller.
     * @return Currently active tool layer controller or null if not-existing.
     */
    public LayerController getActiveToolLayerController() {
        return activeToolLayerController;
    }

    /**
     * Gets the size of the map tiles for zoom level 0.
     * @return Size of a single map tile.
     */
    @Override
    public int getTileSize() {
        return viewer.getTileFactory().getTileSize(0);
    }

    /**
     * Gets the current center position of the currently displayed map.
     * @return GeoPosition of the center of the current selection on the map.
     */
    @Override
    public GeoPosition getCenterPosition() {
        return viewer.getCenterPosition();
    }

    /**
     * Gets the maximum zoom level of the map.
     * @return Maximum zoom level.
     */
    @Override
    public int getMaximumZoomLevel() {
        return viewer.getTileFactory().getInfo().getMaximumZoomLevel();
    }

    /**
     * Gets the underlying map view.
     * @return MapView.
     */
    public MapView getMapView() {
        return this.mapView;
    }

}
