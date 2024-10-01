package de.emir.rcp.parts.vesseleditor.view.geometry;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.parts.VesselEditorBasic;
import de.emir.rcp.parts.vesseleditor.cmds.transactions.AddPointTransaction;
import de.emir.rcp.parts.vesseleditor.cmds.transactions.RemovePointTransaction;
import de.emir.rcp.parts.vesseleditor.utils.View;
import de.emir.rcp.parts.vesseleditor.view.helper.DragProperties;
import de.emir.rcp.parts.vesseleditor.view.helper.DragWrapper;
import de.emir.rcp.parts.vesseleditor.view.helper.LineWrapper;
import de.emir.tuml.ucore.runtime.UStructuralFeature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

public class GeometryPanel extends AbstractGeometryPanel {
    private static final Color POINT_MARKED = Color.RED;
    private static final Color POINT_NORMAL = Color.GRAY.darker().darker();
    private static final Color LINE_NORMAL = Color.GRAY.darker();
    private static final Color BACKGROUND_COLOR = Color.GRAY;
    private static final Color GRID_COLOR = Color.GRAY.brighter();
    private static final Color MOUSE_COORDINATE_COLOR = Color.WHITE;

    protected boolean init = true;

    protected double EDIT_POINT_SIZE = 10;
    protected double LINE_WIDTH = 10;
    protected double MOUSE_COORDINATE_SIZE = 10;

    protected List<Coordinate> markedPoints;
    protected List<DragWrapper> draggableElements;
    protected List<LineWrapper> lineWrappers;

    protected UStructuralFeature horizontalFeature;
    protected UStructuralFeature verticalFeature;

    public GeometryPanel(Geometry geometry, View view) {
        super(geometry, view);

        this.markedPoints = new ArrayList<>();
        this.draggableElements = new ArrayList<>();
        this.lineWrappers = new ArrayList<>();

        setBackground(BACKGROUND_COLOR);
    }

    @Override
    protected void initListeners() {
        DragHandler dragHandler = new DragHandler();
        addMouseListener(dragHandler);
        addMouseMotionListener(dragHandler);

        super.initListeners();
    }

    @Override
    public void changeGeometry(Geometry geometry, View view) {
        switch (view) {
            case TOP:
                horizontalFeature = SpatialPackage.Literals.Coordinate_x;
                verticalFeature = SpatialPackage.Literals.Coordinate_y;
                break;
            case SIDE:
                horizontalFeature = SpatialPackage.Literals.Coordinate_y;
                verticalFeature = SpatialPackage.Literals.Coordinate_z;
                break;
            case FRONT:
                horizontalFeature = SpatialPackage.Literals.Coordinate_x;
                verticalFeature = SpatialPackage.Literals.Coordinate_z;
                break;
            default:
                break;
        }

        super.changeGeometry(geometry, view);
    }

    /**
     * Draw basic grid, points and lines
     *
     * @param g1
     */
    @Override
    public void paint(Graphics g1) {
        super.paint(g1);
        Graphics2D g = (Graphics2D) g1;
        if (init) {
            // Initialize the viewport by moving the origin to the center of the window,
            // and inverting the y-axis to point upwards.
            init = false;
            Dimension d = getSize();
            int xc = d.width / 2;
            int yc = d.height / 2;
            g.translate(xc, yc);
            g.scale(1, -1);
            // Save the viewport to be updated by the ZoomAndPanListener
            getZoomAndPanListener().setCoordTransform(g.getTransform());
            getZoomAndPanListener().setLocationInCoordSystem(new Point(getWidth() / 2, getHeight() / 2));
        } else {
            // Restore the viewport after it was updated by the ZoomAndPanListener
            g.setTransform(getZoomAndPanListener().getCoordTransform());
        }

        if (isShowGridLines()) {
            drawGrid(g);
        }

        if (isShowAxis()) {
            drawAxis(g);
        }

        drawMouseCoordinates(g);

        if (getGeometry() == null) {
            return;
        }

        draggableElements.clear();
        lineWrappers.clear();

        double zoomFactor = (getZoomAndPanListener().getZoomMultiplicationFactor() * getZoomAndPanListener().getZoomLevel());
        zoomFactor = (zoomFactor / 10);

        EDIT_POINT_SIZE = (8 - Math.abs(zoomFactor));
        LINE_WIDTH = (5 - Math.abs(zoomFactor));

        double zoomLevel = getZoomAndPanListener().DEFAULT_MAX_ZOOM_LEVEL - getZoomAndPanListener().getZoomLevel();
        MOUSE_COORDINATE_SIZE = (20 - Math.abs(zoomLevel - 2));
        if (MOUSE_COORDINATE_SIZE < 2) {
            MOUSE_COORDINATE_SIZE = 2;
        }

        //draw lines before point, points need to be on top of the lines
        drawLines(g);

        drawPoints(g);
    }

    protected void drawGrid(Graphics2D g) {
        g.setColor(GRID_COLOR);

        int rows = 50;
        int columns = 50;

        int htOfRow = getHeight() / rows;
        for (int k = 0; k <= rows; k++) {
            g.drawLine(0, k * htOfRow, getWidth(), k * htOfRow);
        }

        int wdOfRow = getWidth() / columns;
        for (int k = 0; k <= columns; k++) {
            g.drawLine(k * wdOfRow, 0, k * wdOfRow, getHeight());
        }

        g.drawLine(getWidth(), 0, getWidth(), getHeight());
        g.drawLine(0, getHeight(), getWidth(), getHeight());
    }

    protected void drawAxis(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);

        if(getGeometry() == null){
            return;
        }

        Envelope env = getGeometry().getEnvelope();
        if(env == null){
            return;
        }
        Coordinate centerCoordinate = env.getCenter();
        Point2D center = coordinateToPoint(centerCoordinate);

        Line2D xAxis = new Line2D.Double(0, center.getY(), getWidth(), center.getY());
        Line2D yAxis = new Line2D.Double(center.getX(), 0, center.getX(), getHeight());

        graphics2D.draw(xAxis);
        graphics2D.draw(yAxis);
    }

    protected void drawPoints(Graphics2D graphics2D) {

        Geometry geometry = getGeometry();

        int geometrySize = geometry.numCoordinates();

        for (int i = 0; i < geometrySize - 1; i++) {

            Point2D point2D = coordinateToPoint(i);

            //check whether It's visible or not
            if (isInViewPort(point2D) == false) {
                continue;
            }

            Coordinate currentCord = geometry.getCoordinate(i);

            //set point color
            if (markedPoints.contains(currentCord)) {
                graphics2D.setPaint(POINT_MARKED);
            } else {
                graphics2D.setPaint(POINT_NORMAL);
            }

            Arc2D.Double arc = new Arc2D.Double();
            arc.setArc(point2D.getX() - (EDIT_POINT_SIZE / 2), point2D.getY() - (EDIT_POINT_SIZE / 2), EDIT_POINT_SIZE, EDIT_POINT_SIZE, 0, 360, Arc2D.CHORD);

            draggableElements.add(new DragWrapper(arc, currentCord, new DragProperties(true, true, false, null)));

            graphics2D.fill(arc);
        }
    }

    protected void drawLines(Graphics2D graphics2D) {
        graphics2D.setPaint(LINE_NORMAL);
        graphics2D.setStroke(new BasicStroke((float) LINE_WIDTH));

        int geometrySize = getGeometry().numCoordinates();
        Point2D lastPoint = null;
        
        if (geometrySize < 1) return;
        if (geometrySize == 1) {
            Point2D p = coordinateToPoint(0);
            if (p != null) {
                graphics2D.drawOval((int) p.getX(), (int) p.getY(), 2, 2);
            }
            return;
        }
        
        for (int i = 0; i < geometrySize; i++) {
            if (i == geometrySize - 1) {
                Point2D first = coordinateToPoint(0);
                Point2D last = coordinateToPoint(i - 1);

                Line2D line2D = new Line2D.Double(first, last);
                lineWrappers.add(new LineWrapper(line2D, 0, i - 1));

                graphics2D.draw(line2D);
                break;
            }

            Point2D current = coordinateToPoint(i);

            //check whether It's visible or not
            if (lastPoint != null) {

                if (isInViewPort(current) == false || isInViewPort(lastPoint.getX(), lastPoint.getY()) == false) {
                    continue;
                }

                Line2D line2D = new Line2D.Double(lastPoint, current);
                lineWrappers.add(new LineWrapper(line2D, i - 1, i));
                graphics2D.draw(line2D);
            }

            lastPoint = current;
        }
    }

    protected void drawMouseCoordinates(Graphics2D graphics2D) {
        Point mousePosition = getMousePosition();
        if (mousePosition == null) {
            return;
        }

        Point2D point = null;

        try {
            point = getZoomAndPanListener().transformPoint(mousePosition);
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
            return;
        }

        graphics2D.setColor(MOUSE_COORDINATE_COLOR);

        Font font = new Font(null, Font.PLAIN, (int) MOUSE_COORDINATE_SIZE);
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.setToScale(-affineTransform.getScaleX(), affineTransform.getScaleY());
        affineTransform.rotate(Math.toRadians(180), 0, 0);
        Font rotatedFont = font.deriveFont(affineTransform);
        graphics2D.setFont(rotatedFont);

        Coordinate coordinate = mouseToCoordinate(mousePosition);

        if (coordinate == null) return;

        String coordinateString = "" + Math.round(coordinate.getX() * 100.0) / 100.0 + " ; " + Math.round(coordinate.getY() * 100.0) / 100.0;
        graphics2D.drawString(coordinateString, (int) point.getX(), (int) point.getY());
    }

    protected Point2D getViewDependantCoordinate(Coordinate c) {
        double x = (double) c.uGet(horizontalFeature);
        double y = (double) c.uGet(verticalFeature);

        Point2D p = new Point2D.Double(x, y);
        return getPointTransform().transform(p, new Point2D.Double());
    }

    protected class DragHandler extends MouseAdapter {

        private boolean movedSinceStart = false;

        @Override
        public void mouseEntered(MouseEvent e) {
            refresh();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            refresh();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            Point2D transformedPoint = transformMousePoint(e.getPoint());

            if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() >= 2) {
                int boxX = (int) transformedPoint.getX();
                int boxY = (int) transformedPoint.getY();

                int size = (int) LINE_WIDTH;

                if (size == 0) {
                    size = 1;
                }

                for (LineWrapper lineWrapper : lineWrappers) {
                    if (lineWrapper.getShape().intersects(boxX, boxY, size, size)) {
                        Coordinate addingCoord = mouseToCoordinate(e.getPoint());
                        AddPointTransaction transaction = new AddPointTransaction(getGeometry(), lineWrapper.getBefore(), lineWrapper.getAfter(), addingCoord);
                        PlatformUtil.getModelManager().getModelProvider().getTransactionStack().run(transaction);
                    }
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                DragWrapper toRemove = null;

                for (DragWrapper dragWrapper : draggableElements) {
                    if (dragWrapper.getDragProperties().getuClass() == null && dragWrapper.getShape().contains(transformedPoint)) {
                        //default icon, custom title
                        int n = JOptionPane.showConfirmDialog(
                                PlatformUtil.getWindowManager().getMainWindow(),
                                "Do you want to delete this point?",
                                "Point Deletion",
                                JOptionPane.YES_NO_OPTION);
                        if (n == 0) {
                            toRemove = dragWrapper;
                        }

                        break;
                    }
                }

                if (toRemove != null) {
                    RemovePointTransaction removePointTransaction = new RemovePointTransaction(getGeometry(), toRemove.getCoordinate());
                    PlatformUtil.getModelManager().getModelProvider().getTransactionStack().run(removePointTransaction);
                }

            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            markedPoints.clear();

            if (isCanEditPoints()) {

                Point2D transformedPoint = transformMousePoint(e.getPoint());

                if (transformedPoint == null) return;

                for (DragWrapper dragWrapper : draggableElements) {
                    if (dragWrapper.getShape().contains(transformedPoint)) {
                        markedPoints.add(dragWrapper.getCoordinate());
                    }
                }

                refresh();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (markedPoints.size() > 0) {
                getZoomAndPanListener().setDragEnabled(false);

                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (isCanEditPoints()) {
                        movedSinceStart = false;

                        Point2D transformedPoint = transformMousePoint(e.getPoint());

                        if (transformedPoint == null) return;

                        for (DragWrapper dragWrapper : draggableElements) {
                            if (dragWrapper.getShape().contains(transformedPoint)) {
                                PlatformUtil.getSelectionManager().setSelection(VesselEditorBasic.CTX_DRAG_SELECTION_ID, dragWrapper);
                                break;
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            getZoomAndPanListener().setDragEnabled(true);
            Object obj = PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_DRAG_SELECTION_ID);

            if (movedSinceStart == false || (obj instanceof DragWrapper) == false) {
                PlatformUtil.getSelectionManager().setSelection(VesselEditorBasic.CTX_DRAG_SELECTION_ID, null);
                return;
            }

            DragWrapper draggable = (DragWrapper) obj;

            draggable.execute(horizontalFeature, verticalFeature);

            movedSinceStart = false;
            PlatformUtil.getSelectionManager().setSelection(VesselEditorBasic.CTX_DRAG_SELECTION_ID, null);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Object obj = PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_DRAG_SELECTION_ID);

            if (obj instanceof DragWrapper) {
                DragWrapper draggable = (DragWrapper) obj;
                try {
                    movedSinceStart = true;

                    Point2D point2D = getZoomAndPanListener().transformPoint(e.getPoint());

                    draggable.moveCoordinate(pointToCoordinate(point2D), horizontalFeature, verticalFeature);
                } catch (NoninvertibleTransformException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
