package de.emir.model.domain.maritime.iec61174.util;

import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.WayPoints;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.CoordinateSequenceImpl;
import de.emir.model.universal.spatial.impl.EnvelopeImpl;
import de.emir.model.universal.spatial.sf.LineString;
import de.emir.model.universal.spatial.sf.impl.LineStringImpl;
import de.emir.model.universal.spatial.sf.ops.GeometryOperationUtil;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.impl.AngleImpl;
import de.emir.model.universal.units.impl.DistanceImpl;
import de.emir.tuml.ucore.runtime.logging.ULog;
import java.util.ArrayList;
import java.util.List;
import org.locationtech.jts.geom.Point;

/**
 *
 * @author Behrensen, Stefan
 */
public class RouteUtil {
    /**
     * Calculates the bounding boxes for a given route. The size of the boxes can be limited.
     * 
     * @param route the route to calculate the bboxes for
     * @param minDist the minimal distance to accept for a bbox in NM
     * @param maxDist the maximal distance to accept for a bbox in NM
     * @param padding the additional padding around the bboxes in meter
     * 
     * @return a list of envelopes representing the bboxes along the route
     */
    public static List<Envelope> calculateBBoxes(Route route, double minDist, double maxDist, double padding) {
        List<Envelope> result = new ArrayList<>();
        int numwp = route.getWaypoints().getWaypoints().size();
        if (numwp < 2) return result;
        List<Waypoint> wps = route.getWaypoints().getWaypoints();
        Waypoint wp0 = wps.get(0);
        for (int i = 1; i < numwp; i++) {
            Envelope bbox = new EnvelopeImpl(wp0.getPosition(), wps.get(i).getPosition());
            double bbsize = bbox.getSize(DistanceUnit.NAUTICAL_MILES).getLength();
            boolean stopratio = false;
            double ratio = 2.0000;
            if (bbsize > maxDist) {
                result.addAll(splitBBoxes(bbox, maxDist));
                wp0 = wps.get(i);
            } else if (bbsize >= minDist || i >= numwp - 1) {
                result.add(bbox);
                wp0 = wps.get(i);
            } else {
                CoordinateSequence cs = new CoordinateSequenceImpl();
                cs.setCrs(CRSUtils.WGS84_2D);
                cs.addCoordinate(wp0.getPosition());
                while (bbsize < minDist && !stopratio && i <= numwp - 1) {
                    i++;
                    cs.addCoordinate(wps.get(i - 1).getPosition());
                    bbox = new EnvelopeImpl(cs.getEnvelope());
                    bbsize = bbox.getSize(DistanceUnit.NAUTICAL_MILES).getLength();
                }
                result.add(bbox);
                i--;
                wp0 = wps.get(i);
            }
        }
        // Apply paddingg if necessary
        if (padding > 0d) {
            for (Envelope bbox : result) {
                //Calculate additional coordinates to grow envelope
                bbox.correct();
                Coordinate c1 = new CoordinateImpl(bbox.getMinPoint().getTarget(new DistanceImpl(padding, DistanceUnit.METER), /*az1*/ new AngleImpl(225, AngleUnit.DEGREE)));
                Coordinate c2 = new CoordinateImpl(bbox.getMaxPoint().getTarget(new DistanceImpl(padding, DistanceUnit.METER), /*az2*/ new AngleImpl(45, AngleUnit.DEGREE)));

                //bbox = new EnvelopeImpl(c1, c2);
                bbox.setMinPoint(c1);
                bbox.setMaxPoint(c2);
            }
        }
        return result;
    } 
    
    /**
     * Creates multiple bounding boxes along a long leg of a route.
     * 
     * @param bbox the legs bounding box
     * @param maxDist the maximum diagonal distnce for each new bounding box
     * @return the list of new bounding boxes
     */
    public static List<Envelope> splitBBoxes(Envelope bbox, double maxDist) {
        List<Envelope> result = new ArrayList<>();
        if (bbox.getSize(DistanceUnit.NAUTICAL_MILES).getLength() < maxDist) {
            result.add(bbox);
        } else {
            Envelope bbox0 = new EnvelopeImpl(bbox.getMinPoint(), bbox.getCenter());
            Envelope bbox1 = new EnvelopeImpl(bbox.getCenter(), bbox.getMaxPoint());
            result.addAll(splitBBoxes(bbox0, maxDist));
            result.addAll(splitBBoxes(bbox1, maxDist));
        }
        return result;
    }
    
    public static final Distance DEFAULT_XTD = new DistanceImpl(8, DistanceUnit.METER);
    
    public static CoordinateSequence createCorridor(Route route) {
        return createCorridor(route, DEFAULT_XTD, DEFAULT_XTD);
    }
    
    public static List<LineString> getSegments(Route route) {
        List<LineString> result = new ArrayList<>();
        if (route.getWaypoints() == null || route.getWaypoints().getWaypoints() == null || route.getWaypoints().getWaypoints().size() < 2) {
            return result;
        }
        for (int i = 0; i < route.getWaypoints().getWaypoints().size() - 1; i++) {
            CoordinateSequenceImpl csq = new CoordinateSequenceImpl();
            csq.addCoordinate(route.getWaypoints().getWaypoints().get(i).getPosition());
            csq.addCoordinate(route.getWaypoints().getWaypoints().get(i + 1).getPosition());
            result.add(new LineStringImpl(csq));
        }
        return result;
    }
    
    public static CoordinateSequence createCorridor(Route route, Distance psXTD, Distance sbXTD) {
        if (route == null) {
            return null;
        }

        WayPoints wayPoints = route.getWaypoints();
        if (wayPoints == null) {
            return null;
        }

        List<Waypoint> wps = wayPoints.getWaypoints();

        List<CoordinateSequence> lines = new ArrayList<>();
        Waypoint last = null;
        Coordinate lastPoint = null;
        for (int i = 0; i < wps.size(); i++) {
            Waypoint wp = wps.get(i);
            Coordinate pos = wp.getPosition();

            if (last == null) {
                Waypoint next = wps.get(i + 1);
                Angle a = pos.getAzimuth(next.getPosition());
                a = a.add(90, AngleUnit.DEGREE);

                Coordinate nextPos = pos.getTarget((wp.getLeg() != null && wp.getLeg().getStarboardXTD() != null) ? wp.getLeg().getStarboardXTD() : sbXTD, a);
                CoordinateSequence line = new CoordinateSequenceImpl();
                line.addCoordinate(pos);
                line.addCoordinate(nextPos);
                lines.add(line);
                lastPoint = nextPos;
            } else {
                Angle a = last.getPosition().getAzimuth(pos);
                a = a.add(90, AngleUnit.DEGREE);
                Coordinate nextPos = pos.getTarget(wp.getLeg().getStarboardXTD() != null ? wp.getLeg().getStarboardXTD() : sbXTD, a);

                if (i != wps.size() - 1) {

                    Waypoint next = wps.get(i + 1);
                    Angle a1 = pos.getAzimuth(next.getPosition());
                    a1 = a1.add(90, AngleUnit.DEGREE);
                    Coordinate nextPos1 = pos.getTarget(wp.getLeg().getStarboardXTD() != null ? wp.getLeg().getStarboardXTD() : sbXTD, a1);
                    CoordinateSequence line = new CoordinateSequenceImpl();
                    line.addCoordinate(lastPoint);
                    line.addCoordinate(nextPos);
                    lines.add(line);

                    line = new CoordinateSequenceImpl();
                    line.addCoordinate(nextPos);
                    line.addCoordinate(nextPos1);
                    lines.add(line);
                    lastPoint = nextPos1;
                } else {
                    CoordinateSequence line = new CoordinateSequenceImpl();
                    line.addCoordinate(lastPoint);
                    line.addCoordinate(nextPos);
                    lines.add(line);
                    lastPoint = nextPos;
                }
            }
            last = wp;

            if (i == wps.size() - 1) {
                CoordinateSequence line = new CoordinateSequenceImpl();
                line.addCoordinate(lastPoint);
                line.addCoordinate(pos);
                lines.add(line);
            }
        }

        for (int i = wps.size() - 1; i >= 0; i--) {
            Waypoint wp = wps.get(i);
            Coordinate pos = wp.getPosition();

            if (last == wp) {
                Waypoint prev = wps.get(i - 1);
                Angle a = pos.getAzimuth(prev.getPosition());
                a = a.add(90, AngleUnit.DEGREE);
                Coordinate prevPos = pos.getTarget(wp.getLeg().getPortsideXTD() != null ? wp.getLeg().getPortsideXTD() : psXTD, a);

                CoordinateSequence line = new CoordinateSequenceImpl();
                line.addCoordinate(pos);
                line.addCoordinate(prevPos);
                lines.add(line);
                lastPoint = prevPos;
            } else {
                Angle a = last.getPosition().getAzimuth(pos);
                a = a.add(90, AngleUnit.DEGREE);
                Coordinate nextPos = pos.getTarget((wp.getLeg() != null && wp.getLeg().getPortsideXTD() != null) ? wp.getLeg().getPortsideXTD() : psXTD, a);

                if (i != 0) {
                    Waypoint next = wps.get(i - 1);
                    Angle a1 = pos.getAzimuth(next.getPosition());
                    a1 = a1.add(90, AngleUnit.DEGREE);
                    Coordinate nextPos1 = pos.getTarget(wp.getLeg().getPortsideXTD() != null ? wp.getLeg().getPortsideXTD() : psXTD, a1);

                    CoordinateSequence line = new CoordinateSequenceImpl();
                    line.addCoordinate(lastPoint);
                    line.addCoordinate(nextPos);
                    lines.add(line);
                    line = new CoordinateSequenceImpl();
                    line.addCoordinate(nextPos);
                    line.addCoordinate(nextPos1);
                    lines.add(line);

                    lastPoint = nextPos1;
                } else {
                    CoordinateSequence line = new CoordinateSequenceImpl();
                    line.addCoordinate(lastPoint);
                    line.addCoordinate(nextPos);
                    lines.add(line);
                    lastPoint = nextPos;
                }
            }
            last = wp;

            if (i == 0) {
                CoordinateSequence line = new CoordinateSequenceImpl();
                line.addCoordinate(lastPoint);
                line.addCoordinate(pos);
                lines.add(line);
            }
        }

        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            CoordinateSequence firstLine = lines.get(i);

            if (i < lines.size() - 2) {
                CoordinateSequence secondLine = lines.get(i + 2);
                // TODO Convert geometry to jts.geom to find intersection
                
                LineString l1 = new LineStringImpl();
                l1.setPoints(firstLine);
                LineString l2 = new LineStringImpl();
                l2.setPoints(secondLine);
                org.locationtech.jts.geom.Geometry intersection = GeometryOperationUtil.getNativeGeometry(l1).intersection(GeometryOperationUtil.getNativeGeometry(l2));
                if (intersection instanceof Point) {
                    Point inter = (Point) intersection;

//                    firstLine = geometryFactory.createLineString(new org.locationtech.jts.geom.Coordinate[]{firstLine.getCoordinateN(0), inter.getCoordinate()});
                    Coordinate flc0 = new CoordinateImpl(firstLine.getCoordinate(0));
                    CoordinateSequence firstLine2 = new CoordinateSequenceImpl();
                    firstLine2.addCoordinate(firstLine.getCoordinate(0));
                    firstLine2.addCoordinate(new CoordinateImpl(inter.getCoordinate().getX(), inter.getCoordinate().getY(), inter.getCoordinate().getZ(), firstLine.getCrs()));
//                    secondLine = geometryFactory.createLineString(new org.locationtech.jts.geom.Coordinate[]{inter.getCoordinate(), secondLine.getCoordinateN(1)});
                    CoordinateSequence secondLine2 = new CoordinateSequenceImpl();
                    secondLine2.addCoordinate(new CoordinateImpl(inter.getCoordinate().getX(), inter.getCoordinate().getY(), inter.getCoordinate().getZ(), flc0.getCrs()));
                    secondLine2.addCoordinate(secondLine.getCoordinate(1));

                    lines.set(i, firstLine2);
                    lines.set(i + 2, secondLine2);

                    coordinates.add(firstLine2.getCoordinate(0));
                    coordinates.add(firstLine2.getCoordinate(1));

                    i += 1;
                } else {
                    coordinates.add(firstLine.getCoordinate(0));
                    coordinates.add(firstLine.getCoordinate(1));
                }
            } else {
                CoordinateSequence l = lines.get(i);
                coordinates.add(l.getCoordinate(0));
                coordinates.add(l.getCoordinate(1));
            }
        }

        if (coordinates.get(0).toVector2D().equals(coordinates.get(coordinates.size() - 1).toVector2D()) == false) {
            coordinates.add(coordinates.get(coordinates.size() - 1));
        }

        try {
            //return geometryFactory.createPolygon(coordinates.toArray(new org.locationtech.jts.geom.Coordinate[0]));
            CoordinateSequence poly = new CoordinateSequenceImpl(coordinates);
            return poly;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
//        return null;
    }
}
