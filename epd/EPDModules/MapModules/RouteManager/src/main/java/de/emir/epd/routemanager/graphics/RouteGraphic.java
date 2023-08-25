package de.emir.epd.routemanager.graphics;

import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;
import de.emir.epd.routemanager.graphics.impl.ArrowGraphic;
import de.emir.epd.routemanager.graphics.impl.LegLineGraphic;
import de.emir.epd.routemanager.graphics.impl.RouteCorridorGraphic;
import de.emir.epd.routemanager.graphics.impl.WayPointCircleGraphic;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.WayPoints;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.rcp.manager.util.PlatformUtil;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class RouteGraphic {
    protected Route route;
    protected List<WayPointCircleGraphic> wpCircleShapes;
    protected List<ArrowGraphic> arrowShapes;
    protected List<LegLineGraphic> legLines;
    protected RouteCorridorGraphic corridorGraphic;
    protected Color routeColor;
    protected Color colorFocused;
    protected Color corridorColor;
    protected Stroke strokeBG;
    protected Stroke routeLine;
    protected Stroke strokeFocused;

    public RouteGraphic(Route route, boolean active) {
        this.route = route;
        initStyle(active);
    }

    protected void initStyle(boolean active) {
        colorFocused = new Color(0, 109, 9);
        strokeBG = new BasicStroke(2);
        routeLine = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                0, new float[]{5}, 2);
        strokeFocused = new BasicStroke(3);
        routeColor = active ? Color.RED : Color.GRAY;
        corridorColor = new Color(255, 135,000);
    }

    public void paint(BufferingGraphics2D g, IDrawContext c) {
        wpCircleShapes = new ArrayList<>();
        legLines = new ArrayList<>();
        arrowShapes = new ArrayList<>();

        if (route == null)
            return;
        WayPoints wayPoints = route.getWaypoints();
        if (wayPoints == null) {
            return;
        }

        //viewPort bounds
        Rectangle viewPort = new Rectangle(0, 0,
                (int) c.getViewportBounds().getWidth(),
                (int) c.getViewportBounds().getHeight());

        Waypoint lastWP = null;

        for (Waypoint wp : wayPoints.getWaypoints()) {
            Point2D wpPixel = c.convert(wp.getPosition().getLongitude(), wp.getPosition().getLatitude());
            if (viewPort.contains(wpPixel)) {
                WayPointCircleGraphic wpGraphic = new WayPointCircleGraphic(wp);
                wpCircleShapes.add(wpGraphic);
            }

            if (lastWP != null) {
                Point2D lastPoint = c.convert(lastWP.getPosition().getLongitude(), lastWP.getPosition().getLatitude());
                Line2D line = new Line2D.Double(lastPoint, wpPixel);

                if (viewPort.contains(lastPoint) || viewPort.contains(wpPixel) || viewPort.intersectsLine(line)) {
                    LegLineGraphic legLineGraphic = new LegLineGraphic(lastWP, wp);
                    legLines.add(legLineGraphic);
                    ArrowGraphic arrowGraphic = new ArrowGraphic(legLineGraphic);
                    arrowShapes.add(arrowGraphic);
                }

            }

            lastWP = wp;
        }

        corridorGraphic = new RouteCorridorGraphic(wayPoints.getWaypoints());
        g.setStroke(routeLine);
        g.setColor(corridorColor);
        corridorGraphic.paint(g, c);

        g.setColor(routeColor);
        // draw wayPoint shapes
        g.setStroke(strokeBG);
        for (WayPointCircleGraphic wp : wpCircleShapes) {
            wp.paint(g, c);
        }

        // draw legLine shapes
        g.setStroke(routeLine);
        for (LegLineGraphic lg : legLines) {
            lg.paint(g, c);
        }

        // draw arrows on top of legLines
        g.setStroke(strokeBG);
        for (ArrowGraphic arrowGraphic : arrowShapes) {
            arrowGraphic.paint(g, c);
        }

        // check if wayPoint is focused and redraw it
        Object focused = PlatformUtil.getSelectionManager().getSelectedObject(MVBasic.MAP_FOCUS_CTX);

        if (focused instanceof Waypoint) {

            for (Waypoint wp : wayPoints.getWaypoints()) {

                if (wp == focused) {
                    WayPointCircleGraphic wpGraphic = new WayPointCircleGraphic(wp);
                    wpGraphic.paint(g, c);
                    g.setStroke(strokeFocused);
                    g.setColor(colorFocused);
                    g.draw(wpGraphic);
                    break;
                }

            }

        }
    }


    public List<WayPointCircleGraphic> getWaypointCircleShapes() {
        return wpCircleShapes;
    }
    
    

    public List<ArrowGraphic> getArrowShapes() {
        return arrowShapes;
    }

    public void setArrowShapes(List<ArrowGraphic> arrowShapes) {
        this.arrowShapes = arrowShapes;
    }

    public List<LegLineGraphic> getLegLines() {
        return legLines;
    }

    public void setLegLines(List<LegLineGraphic> legLines) {
        this.legLines = legLines;
    }

    public Route getRoute() {
        return route;
    }

}
