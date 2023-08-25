package de.emir.epd.routemanager.graphics.impl;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;
import de.emir.epd.routemanager.graphics.IGraphic;
import de.emir.model.universal.spatial.Coordinate;

public class ArrowGraphic extends Polygon implements IGraphic<ArrowGraphic> {
    private final LegLineGraphic line;

    private Shape s = new Polygon(new int[]{0, 0, 10, 0}, new int[]{-5, 5, 0, -5}, 4);

    public ArrowGraphic(LegLineGraphic line) {
        this.line = line;
    }

    @Override
    public ArrowGraphic paint(BufferingGraphics2D g, IDrawContext c) {

        AffineTransform transform = g.getTransform();

        double l = getLength(c);

        int arrowCount = (int) ((l - 25) / 50);

        g.translate(line.x1, line.y1);
        g.translate(-2, 0);
        g.rotate(getRouteAngle(c));
        g.translate(50, 0);


        for (int i = 0; i < arrowCount; i++) {

            g.fill(s);
            g.translate(50, 0);
        }

        g.setTransform(transform);
        return this;
    }

    private double getLength(IDrawContext c) {
        Coordinate startPoint = line.getStart().getPosition();
        Coordinate endPoint = line.getEnd().getPosition();

        Point2D pxStart = c.convert(startPoint.getLongitude(), startPoint.getLatitude());
        Point2D pxEnd = c.convert(endPoint.getLongitude(), endPoint.getLatitude());

        double dx = pxEnd.getX() - pxStart.getX();
        double dy = pxEnd.getY() - pxStart.getY();

        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    private double getRouteAngle(IDrawContext c) {

        Coordinate startPoint = line.getStart().getPosition();
        Coordinate endPoint = line.getEnd().getPosition();

        Point2D pxStart = c.convert(startPoint.getLongitude(), startPoint.getLatitude());
        Point2D pxEnd = c.convert(endPoint.getLongitude(), endPoint.getLatitude());

        double dx = pxEnd.getX() - pxStart.getX();
        double dy = pxEnd.getY() - pxStart.getY();

        return Math.atan2(dy, dx) - Math.atan2(0, 1);

    }

    public LegLineGraphic getLine() {
        return line;
    }
    
    
}
