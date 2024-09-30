package de.emir.rcp.parts.vesseleditor.view.geometry;

import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.rcp.parts.VesselEditorBasic;
import de.emir.rcp.parts.vesseleditor.utils.View;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.IProperty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;


public abstract class AbstractGeometryPanel extends JPanel {
    private AffineTransform mTransform;
    private Geometry geometry;
    private View view;
    private PropertyContext context;
    private ZoomAndPanListener zoomAndPanListener;

    public AbstractGeometryPanel(Geometry geometry, View view) {
        context = PropertyStore.getContext(VesselEditorBasic.PROP_VESSEL_EDITOR);
        setAutoscrolls(true);

        initListeners();
        changeGeometry(geometry, view);
    }

    /**
     * New geometry available, update transform and assign new values
     *
     * @param geometry
     * @param view
     */
    public void changeGeometry(Geometry geometry, View view) {
        this.view = view;

        if (this.geometry != geometry) {
            this.geometry = geometry;

        }
        updateTransform();

        refresh();
    }

    /**
     * Init mouse listeners and property listeners
     */
    protected void initListeners() {
        this.zoomAndPanListener = new ZoomAndPanListener(this);
        this.addMouseListener(zoomAndPanListener);
        this.addMouseMotionListener(zoomAndPanListener);
        this.addMouseWheelListener(zoomAndPanListener);

        IProperty centerOnShapeProp = context.getProperty(VesselEditorBasic.PROP_CENTER_ON_SHAPE);
        centerOnShapeProp.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Object obj = evt.getNewValue();
                if (obj instanceof Boolean) {
                    Boolean bool = (Boolean) obj;
                    if (bool) {
                        context.setValue(VesselEditorBasic.PROP_CENTER_ON_SHAPE, false);
                        centerOnShape();
                    }
                }
            }
        });

        IProperty zoomToBestFitProp = context.getProperty(VesselEditorBasic.PROP_ZOOM_TO_BEST_FIT);
        zoomToBestFitProp.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Object obj = evt.getNewValue();
                if (obj instanceof Boolean) {
                    Boolean bool = (Boolean) obj;
                    if (bool) {
                        context.setValue(VesselEditorBasic.PROP_ZOOM_TO_BEST_FIT, false);
                        zoomToBestFit();
                    }
                }
            }
        });

        IProperty showGridLinesProp = context.getProperty(VesselEditorBasic.PROP_SHOW_GRID_LINES);
        showGridLinesProp.addPropertyChangeListener(evt -> repaint());
        IProperty toogleShapeEditProp = context.getProperty(VesselEditorBasic.PROP_TOOGLE_SHAPE_EDIT);
        toogleShapeEditProp.addPropertyChangeListener(e -> repaint());
        IProperty showAxisProp = context.getProperty(VesselEditorBasic.PROP_SHOW_AXIS_LINES);
        showAxisProp.addPropertyChangeListener(evt -> repaint());

        addComponentListener(new ComponentListener() {

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentResized(ComponentEvent e) {
                updateTransform();
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
    }

    protected boolean isInViewPort(Point2D point2D) {
        return isInViewPort(point2D.getX(), point2D.getY());
    }

    protected boolean isInViewPort(double x, double y) {
        return x > 0 || y > 0 || x < getWidth() || y < getHeight();
    }

    protected AffineTransform getPointTransform() {
        if (mTransform == null) {
            mTransform = new AffineTransform();
            updateTransform();
        }
        return mTransform;
    }

    protected void updateTransform() {
        if (geometry == null) return;
        
        if (mTransform == null) {
            getPointTransform();
        }

        Envelope env = geometry.getEnvelope();
        if(env == null){
            return;
        }

        Coordinate center = env.getCenter();

        double may = Double.MIN_VALUE, max = Double.MIN_VALUE;

        for (int i = 0; i < geometry.numCoordinates(); i++) {
            Coordinate p = geometry.getCoordinate(i);
            may = Math.max(p.getY(), may);
            max = Math.max(p.getX(), max);
        }

        Vector2D topOrigin = new Vector2DImpl(center.getX(), may);
        Vector2D topTarget = new Vector2DImpl(center.getX(), getHeight() - center.getY());

        Vector2D sideOrigin = new Vector2DImpl(max, center.getX());
        Vector2D sideTarget = new Vector2DImpl(getWidth() - center.getX(), center.getY());

        double scaleHeight = (topTarget.getLength() / topOrigin.getLength()) / 2;
        double scaleWidth = (sideTarget.getLength() / sideOrigin.getLength()) / 2;

        double scale = Math.min(scaleHeight, scaleWidth);
        scale *= 0.8;


        mTransform.setToIdentity();
        mTransform.scale(scale, scale);
        mTransform.translate(((getWidth() / 2) / scale), ((getHeight() / 2) / scale));

        refresh();
    }

    protected Point2D coordinateToPoint(int idx) {
        Coordinate c = geometry.getCoordinate(idx);
        return coordinateToPoint(c);
    }

    protected Point2D coordinateToPoint(Coordinate c) {
        Point2D p = new Point2D.Double(c.getX(), c.getY());
        return getPointTransform().transform(p, new Point2D.Double());
    }

    public Coordinate pointToCoordinate(double dx, double dy) {
        Point2D point2D = null;
        try {
            point2D = getPointTransform().createInverse().transform(new Point((int) dx, (int) dy), null);
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
        }

        if (point2D == null) return null;
        point2D.setLocation(point2D.getX(), point2D.getY());
        return new CoordinateImpl(point2D.getX(), point2D.getY(), geometry.getCRS());
    }

    public Coordinate pointToCoordinate(Point2D point2D) {
        return pointToCoordinate(point2D.getX(), point2D.getY());
    }

    public Coordinate mouseToCoordinate(Point point) {
        Point2D point2D = null;
        try {
            point2D = zoomAndPanListener.transformPoint(point);
        } catch (NoninvertibleTransformException | NullPointerException e) {
            e.printStackTrace();
        }

        if(point2D == null){
            return null;
        }

        return pointToCoordinate(point2D);
    }

    public Point2D transformMousePoint(Point point) {
        try {
            return getZoomAndPanListener().transformPoint(point);
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected View getView() {
        return view;
    }

    protected Geometry getGeometry() {
        return geometry;
    }

    protected ZoomAndPanListener getZoomAndPanListener() {
        return zoomAndPanListener;
    }

    public boolean isCanEditPoints() {
        Object obj = context.getProperty(VesselEditorBasic.PROP_TOOGLE_SHAPE_EDIT).getValue();
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        } else {
            return true;
        }
    }

    public boolean isShowGridLines() {
        Object obj = context.getProperty(VesselEditorBasic.PROP_SHOW_GRID_LINES).getValue();
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        } else {
            return true;
        }
    }

    public boolean isShowAxis() {
        Object obj = context.getProperty(VesselEditorBasic.PROP_SHOW_AXIS_LINES).getValue();
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        } else {
            return true;
        }
    }

    public void zoomToBestFit() {
        Rectangle boundingBox = new Rectangle();
        Coordinate center = geometry.getEnvelope().getCenter();
        Point2D centerPoint = coordinateToPoint(center);

        boundingBox.setLocation((int) centerPoint.getX(), (int) centerPoint.getY());

        List<Coordinate> coordinates = geometry.getEnvelope().getVertices();
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;

        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        //TODO search for a better way to calculate height and width

        for (Coordinate coordinate : coordinates) {
            Point2D point = coordinateToPoint(coordinate);
            if (point.getX() > maxX) {
                maxX = (int) point.getX();
            }
            if (point.getX() < minX) {
                minX = (int) point.getX();
            }

            if (point.getY() > maxY) {
                maxY = (int) point.getY();
            }

            if (point.getY() < minY) {
                minY = (int) point.getY();
            }
        }

        boundingBox.setSize((maxX - minX), (maxY - minY));

        zoomAndPanListener.zoomToBestFit(boundingBox);
    }

    public void centerOnShape() {
        Envelope env = geometry.getEnvelope();
        if(env == null){
            return;
        }
        Coordinate center = env.getCenter();

        Point2D centerPoint = coordinateToPoint(center);

        Rectangle boundingBox = new Rectangle();
        boundingBox.setLocation((int) centerPoint.getX(), (int) centerPoint.getY());

        zoomAndPanListener.centerOnRectangle(boundingBox);
    }

    public void refresh() {
        revalidate();
        repaint();
    }

}
