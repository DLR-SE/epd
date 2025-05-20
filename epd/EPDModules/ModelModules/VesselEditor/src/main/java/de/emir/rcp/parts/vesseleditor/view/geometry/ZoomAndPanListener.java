package de.emir.rcp.parts.vesseleditor.view.geometry;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

/**
 * Listener that can be attached to a Component to implement Zoom and Pan functionality.
 * https://community.oracle.com/thread/1263955
 *
 * @author Sorin Postelnicu
 * @author Kojy
 * @since Jul 14, 2009
 * @since March 15 2018
 */
public class ZoomAndPanListener implements MouseListener, MouseMotionListener, MouseWheelListener {
    public static final int DEFAULT_MIN_ZOOM_LEVEL = 0;
    public static final int DEFAULT_MAX_ZOOM_LEVEL = 20;
    public static final double DEFAULT_ZOOM_MULTIPLICATION_FACTOR = 1.15;

    private Component targetComponent;

    private int zoomLevel = 20;
    private int minZoomLevel = DEFAULT_MIN_ZOOM_LEVEL;
    private int maxZoomLevel = DEFAULT_MAX_ZOOM_LEVEL;
    private double zoomMultiplicationFactor = DEFAULT_ZOOM_MULTIPLICATION_FACTOR;

    private Point dragStartScreen;
    private Point dragEndScreen;
    private AffineTransform coordTransform = new AffineTransform();

    private boolean dragEnabled = true;

    public ZoomAndPanListener(Component targetComponent) {
        this.targetComponent = targetComponent;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        dragStartScreen = e.getPoint();
        dragEndScreen = null;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        moveCamera(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        zoomCamera(e);
    }

    private void moveCamera(MouseEvent e) {
        if (isDragEnabled()) {
            try {
                dragEndScreen = e.getPoint();
                Point2D dragStart = transformPoint(dragStartScreen);
                Point2D dragEnd = transformPoint(dragEndScreen);
                double dx = dragEnd.getX() - dragStart.getX();
                double dy = dragEnd.getY() - dragStart.getY();

                coordTransform.translate(dx, dy);
                dragStartScreen = dragEndScreen;
                dragEndScreen = null;
                targetComponent.repaint();
            } catch (NoninvertibleTransformException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void zoomCamera(MouseWheelEvent e) {
        try {
            int wheelRotation = e.getWheelRotation();
            Point p = e.getPoint();
            if (wheelRotation > 0) {
                //zoom out
                if (zoomLevel < maxZoomLevel) {
                    zoomLevel++;
                    Point2D p1 = transformPoint(p);
                    coordTransform.scale(1.0 / zoomMultiplicationFactor, 1.0 / zoomMultiplicationFactor);
                    Point2D p2 = transformPoint(p);
                    coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
                    targetComponent.repaint();
                }
            } else {
                //zoom in
                if (zoomLevel > minZoomLevel) {
                    zoomLevel--;
                    Point2D p1 = transformPoint(p);
                    coordTransform.scale(zoomMultiplicationFactor, zoomMultiplicationFactor);
                    Point2D p2 = transformPoint(p);
                    coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
                    targetComponent.repaint();
                }
            }
        } catch (NoninvertibleTransformException ex) {
            ex.printStackTrace();
        }
    }

    protected Point2D transformPoint(Point p1) throws NoninvertibleTransformException {
        AffineTransform inverse = coordTransform.createInverse();
        Point2D.Float p2 = new Point2D.Float();
        inverse.transform(p1, p2);
        return p2;
    }

    protected Point2D transformPoint(Point2D p1) throws NoninvertibleTransformException {
        AffineTransform inverse = coordTransform.createInverse();
        Point2D.Float p2 = new Point2D.Float();
        inverse.transform(p1, p2);
        return p2;
    }

    protected Shape transformLine(Line2D l1) throws NoninvertibleTransformException {
        AffineTransform inverse = coordTransform.createInverse();
        return inverse.createTransformedShape(l1);
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(int zoomLevel) {
        if (zoomLevel > maxZoomLevel) zoomLevel = maxZoomLevel;
        if (zoomLevel < minZoomLevel) zoomLevel = minZoomLevel;
        if (zoomLevel == this.zoomLevel) return;

        try {
            Point2D p = getCoordTransform().transform(getLocationInCoordSystem(), new Point2D.Double());
            if (zoomLevel > this.zoomLevel) {
                //zoom out
                if (zoomLevel < maxZoomLevel) {
                    zoomLevel++;
                    Point2D p1 = transformPoint(p);
                    coordTransform.scale(1 / zoomMultiplicationFactor, 1 / zoomMultiplicationFactor);
                    Point2D p2 = transformPoint(p);
                    coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
                    targetComponent.repaint();
                }
            } else {
                //zoom in
                if (zoomLevel > minZoomLevel) {
                    zoomLevel--;
                    Point2D p1 = transformPoint(p);
                    coordTransform.scale(zoomMultiplicationFactor, zoomMultiplicationFactor);
                    Point2D p2 = transformPoint(p);
                    coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
                    targetComponent.repaint();
                }
            }
        } catch (NoninvertibleTransformException ex) {
            ex.printStackTrace();
        }

        this.zoomLevel = zoomLevel;
    }

    public double getZoomMultiplicationFactor() {
        return zoomMultiplicationFactor;
    }

    public Rectangle getCoordViewPort() {
        Rectangle transformed = null;
        try {
            Line2D width = new Line2D.Double(0, 0, targetComponent.getWidth(), 0);
            transformed = transformLine(width).getBounds();

            Line2D line2D = new Line2D.Double(0, 0, 0, targetComponent.getHeight());
            Shape height = transformLine(line2D);
            transformed.setSize((int) transformed.getWidth(), (int) height.getBounds().getHeight());


            Point2D point2D = getLocationInCoordSystem();
            transformed.setLocation((int) point2D.getX(), (int) point2D.getY());

            return transformed;
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
        }

        return transformed;

    }

    public Point2D getLocationInCoordSystem() {
        try {
            return transformPoint(new Point(targetComponent.getWidth() / 2, targetComponent.getHeight() / 2));
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setLocationInCoordSystem(Point dragEnd) {
        Point2D dragStart = getLocationInCoordSystem();
        double dx = -(dragEnd.getX() - dragStart.getX());
        double dy = -(dragEnd.getY() - dragStart.getY());

        coordTransform.translate(dx, dy);
        Point2D point2D = getCoordTransform().transform(dragEnd, new Point2D.Double());
        dragStartScreen = new Point((int) point2D.getX(), (int) point2D.getY());
        targetComponent.repaint();
    }

    public void centerOnRectangle(Rectangle rectangle) {
        setLocationInCoordSystem(rectangle.getLocation());
    }

    public void zoomToBestFit(Rectangle rectangle) {

        //TODO use math to calculate zoom.... was just to tired

        Rectangle viewPort = getCoordViewPort();
        if (rectangle.getHeight() > viewPort.getHeight()) {
            //zoom out
            int currentZoom = zoomLevel;
            double currentHeight = viewPort.getHeight();
            while (currentHeight < rectangle.getHeight()) {
                currentHeight *= zoomMultiplicationFactor;
                currentZoom++;
            }

            setZoomLevel(currentZoom);
        } else {
            //zoom in
            int currentZoom = zoomLevel;
            double currentHeight = viewPort.getHeight();
            while (currentHeight > rectangle.getHeight()) {
                currentHeight /= zoomMultiplicationFactor;
                currentZoom--;
            }

            setZoomLevel(currentZoom);
        }

        centerOnRectangle(rectangle);

    }

    public AffineTransform getCoordTransform() {
        return coordTransform;
    }

    public void setCoordTransform(AffineTransform coordTransform) {
        this.coordTransform = coordTransform;
    }

    public boolean isDragEnabled() {
        return dragEnabled;
    }

    public void setDragEnabled(boolean dragEnabled) {
        this.dragEnabled = dragEnabled;
    }
}