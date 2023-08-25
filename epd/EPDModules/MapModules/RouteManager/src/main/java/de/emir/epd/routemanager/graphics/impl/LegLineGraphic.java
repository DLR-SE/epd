package de.emir.epd.routemanager.graphics.impl;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;
import de.emir.epd.routemanager.graphics.IGraphic;
import de.emir.model.domain.maritime.iec61174.Waypoint;

public class LegLineGraphic extends Line2D.Double implements IGraphic<LegLineGraphic> {

    private static final long serialVersionUID = 8672516887797155512L;

    private Waypoint start;
    private Waypoint end;

    public LegLineGraphic(Waypoint start, Waypoint end) {
        super();
        this.start = start;
        this.end = end;
    }

    public Waypoint getStart() {
        return start;
    }

    public Waypoint getEnd() {
        return end;
    }

    @Override
    public LegLineGraphic paint(BufferingGraphics2D g, IDrawContext c) {
        Point2D startPoint = c.convert(start.getPosition().getLongitude(), start.getPosition().getLatitude());
        Point2D endPoint = c.convert(end.getPosition().getLongitude(), end.getPosition().getLatitude());
        setLine(startPoint, endPoint);
        g.draw(this);
        return this;
    }
}
