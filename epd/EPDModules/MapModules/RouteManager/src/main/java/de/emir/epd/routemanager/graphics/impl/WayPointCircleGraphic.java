package de.emir.epd.routemanager.graphics.impl;

import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;
import de.emir.epd.routemanager.graphics.IGraphic;
import de.emir.model.domain.maritime.iec61174.Waypoint;

import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;

public class WayPointCircleGraphic extends Arc2D.Double implements IGraphic<WayPointCircleGraphic> {

    private static final long serialVersionUID = 863356670916990376L;
    private static double WP_CIRCLE_SIZE = 15;
    protected Waypoint waypoint;


    public WayPointCircleGraphic(Waypoint waypoint) {
        super();
        this.waypoint = waypoint;
    }

    public Waypoint getWaypoint() {
        return waypoint;
    }

    @Override
    public WayPointCircleGraphic paint(BufferingGraphics2D g, IDrawContext c) {

        int zoom = c.getZoom();
        int maxZoom = c.getMaximumZoomLevel();

        WP_CIRCLE_SIZE = maxZoom - zoom;

        Point2D point2D = c.convert(waypoint.getPosition().getLongitude(), waypoint.getPosition().getLatitude());

        //computes the middle of a circle
        double x = point2D.getX() - (WP_CIRCLE_SIZE / 2.0);
        double y = point2D.getY() - (WP_CIRCLE_SIZE / 2.0);

        setArc(x, y, WP_CIRCLE_SIZE, WP_CIRCLE_SIZE, 0, 360, Arc2D.CHORD);

        g.draw(this);
        return this;
    }
}
