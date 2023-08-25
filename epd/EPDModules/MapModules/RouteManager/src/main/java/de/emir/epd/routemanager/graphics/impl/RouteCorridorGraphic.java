package de.emir.epd.routemanager.graphics.impl;

import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;
import de.emir.epd.routemanager.graphics.IGraphic;
import de.emir.model.domain.maritime.iec61174.Leg;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.Distance;
import de.emir.tuml.ucore.runtime.logging.ULog;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class RouteCorridorGraphic extends Path2D.Double implements IGraphic<RouteCorridorGraphic> {
    public static final double DOUBLE_EPSILON = 1e-6;
    private List<Waypoint> wps;

    public RouteCorridorGraphic(List<Waypoint> wps) {
        this.wps = wps;
    }

    @Override
    public RouteCorridorGraphic paint(BufferingGraphics2D g, IDrawContext c) {
        //TODO this route drawing doesn't calculate the bounding box correct if the angle is to sharp

        if (check(wps) == false) {
            return this;
        }
        
        this.reset();

        List<Line2D> lines = new ArrayList<>();
        Waypoint last = null;
        Point2D lastPoint = null;
        for (int i = 0; i < wps.size(); i++) {
            Waypoint wp = wps.get(i);
            Coordinate pos = wp.getPosition();

            if (last == null){
                Waypoint next = wps.get(i + 1);
                Angle a = pos.getAzimuth(next.getPosition());
                a = a.add(90, AngleUnit.DEGREE);

                Coordinate nextPos = pos.getTarget(wp.getLeg().getStarboardXTD(), a);

                Point2D p = c.convert(pos.getLongitude(), pos.getLatitude());
                Point2D p1 = c.convert(nextPos.getLongitude(), nextPos.getLatitude());

                lines.add(new Line2D.Double(p, p1));
                lastPoint = p1;
            } else {
                Angle a = last.getPosition().getAzimuth(pos);
                a = a.add(90, AngleUnit.DEGREE);
                Coordinate nextPos = pos.getTarget(wp.getLeg().getStarboardXTD(), a);
                Point2D p1 = c.convert(nextPos.getLongitude(), nextPos.getLatitude());


                if (i != wps.size() -1 ) {

                    Waypoint next = wps.get(i + 1);
                    Angle a1 = pos.getAzimuth(next.getPosition());
                    a1 = a1.add(90, AngleUnit.DEGREE);
                    Coordinate nextPos1 = pos.getTarget(wp.getLeg().getStarboardXTD(), a1);
                    Point2D p2 = c.convert(nextPos1.getLongitude(), nextPos1.getLatitude());

                    lines.add(new Line2D.Double(lastPoint, p1));
                    lines.add(new Line2D.Double(p1, p2));

                    lastPoint = p2;                    
                }else {
                    lines.add(new Line2D.Double(lastPoint, p1));
                    lastPoint = p1;
                }
            }
            last = wp;
            
            if (i == wps.size() - 1){
                Point2D p = c.convert(pos.getLongitude(), pos.getLatitude());
                lines.add(new Line2D.Double(lastPoint, p));
            }
        }

        for (int i = wps.size() - 1; i >= 0; i--) {
            Waypoint wp = wps.get(i);
            Coordinate pos = wp.getPosition();

            if (last == wp){
                Waypoint prev = wps.get(i - 1);
                Angle a = pos.getAzimuth(prev.getPosition());
                a = a.add(90, AngleUnit.DEGREE);
                Coordinate prevPos = pos.getTarget(wp.getLeg().getPortsideXTD(), a);

                Point2D p = c.convert(pos.getLongitude(), pos.getLatitude());
                Point2D p1 = c.convert(prevPos.getLongitude(), prevPos.getLatitude());

                lines.add(new Line2D.Double(p, p1));
                lastPoint = p1;
            }else {
                Angle a = last.getPosition().getAzimuth(pos);
                a = a.add(90, AngleUnit.DEGREE);
                Coordinate nextPos = pos.getTarget(wp.getLeg().getPortsideXTD(), a);
                Point2D p1 = c.convert(nextPos.getLongitude(), nextPos.getLatitude());

                if(i != 0) {
                    Waypoint next = wps.get(i - 1);
                    Angle a1 = pos.getAzimuth(next.getPosition());
                    a1 = a1.add(90, AngleUnit.DEGREE);
                    Coordinate nextPos1 = pos.getTarget(wp.getLeg().getPortsideXTD(), a1);
                    Point2D p2 = c.convert(nextPos1.getLongitude(), nextPos1.getLatitude());

                    lines.add(new Line2D.Double(lastPoint, p1));
                    lines.add(new Line2D.Double(p1, p2));
                    
                    lastPoint = p2;                 
                }else {
                    lines.add(new Line2D.Double(lastPoint, p1));
                    lastPoint = p1;    
                }
                
                
            }
            last = wp;

            if (i == 0){
                Point2D p = c.convert(pos.getLongitude(), pos.getLatitude());
                lines.add(new Line2D.Double(lastPoint, p));
            }
        }

        for(int i = 0; i < lines.size(); i++){           
            Line2D firstLine = lines.get(i);     
            
            if(i < lines.size() - 2){
                Line2D secondLine = lines.get(i + 2);
                
                if (firstLine.intersectsLine(secondLine)){
                    try {

                        Point2D inter = getIntersectPoint(firstLine.getP1(), firstLine.getP2(), secondLine.getP1(), secondLine.getP2());
                        if (inter != null){
                            firstLine.setLine(firstLine.getP1(), inter);
                            secondLine.setLine(inter, secondLine.getP2());
                        }
                        this.append(firstLine, true);
                        i += 1;
                    }catch (Exception e){
                        ULog.error(e);
                    }
                }else {
                    this.append(firstLine, true);
                } 
            }else {
                this.append(lines.get(i), true);
            }
        }

        try {
            this.closePath();
        }catch (Exception e) {
            // TODO: handle exception
        }
        Color col = g.getColor();

        Color alphaModified = new Color(col.getRed(), col.getGreen(), col.getBlue(), 32);
        g.setColor(alphaModified);
        g.fill(this);
//        g.setColor(col);
//        g.draw(this);

        return this;
    }

    public static Point2D.Double getIntersectPoint(Point2D ptA, Point2D ptB, Point2D ptC, Point2D ptD) {
        if (ptA == null || ptB == null || ptC == null || ptD == null) {
            return null;
        }

        Point2D.Double ptP = null;

        double denominator = (ptB.getX() - ptA.getX()) * (ptD.getY() - ptC.getY())
                - (ptB.getY() - ptA.getY()) * (ptD.getX() - ptC.getX());

        if (isDifferentFromZero(denominator)) {
            double numerator = (ptA.getY() - ptC.getY()) * (ptD.getX() - ptC.getX())
                    - (ptA.getX() - ptC.getX()) * (ptD.getY() - ptC.getY());

            double r = numerator / denominator;

            ptP = new Point2D.Double(ptA.getX() + r * (ptB.getX() - ptA.getX()),
                    ptA.getY() + r * (ptB.getY() - ptA.getY()));
        }

        return ptP;
    }

    public static boolean isDifferentFromZero(double val) {
        return Math.copySign(val, 1.0) > DOUBLE_EPSILON;
    }

    private boolean check(List<Waypoint> wps) {

        if (wps == null || wps.size() <= 1) {
            return false;
        }

        for (Waypoint wp : wps) {
            Leg leg = wp.getLeg();
            if (leg == null) {
                return false;
            }

            if(wp.getPosition() == null){
                return false;
            }

            Distance sXTD = leg.getStarboardXTD();
            Distance pXTD = leg.getPortsideXTD();
            if (sXTD == null || pXTD == null || sXTD.getValue() == 0.0 || pXTD.getValue() == 0.0) {
                return false;
            }
        }

        return true;
    }
}
