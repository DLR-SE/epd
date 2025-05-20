package de.emir.epd.routemanager.util;

import de.emir.model.domain.maritime.iec61174.*;
import de.emir.model.domain.maritime.iec61174.impl.*;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.CoordinateSequenceImpl;
import de.emir.model.universal.spatial.impl.EnvelopeImpl;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.service.routeservices.impl.RouteExportImpl;
import de.emir.service.routeservices.impl.RouteImportImpl;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RouteUtil {
    private static final Logger logger = LogManager.getLogger(RouteUtil.class);

    /**
     * Performs a deep reverse of a route.
     */
    public static Route reverse(Route route) {
        Route newRoute = new RouteImpl();
        List<Waypoint> waypoints = new LinkedList<>();

        int routeSize = route.getWaypoints().getWaypoints().size() - 1;
        int j = 0;

        for (int i = routeSize; i > -1; i--) {
            Waypoint newRouteWaypoint = new WaypointImpl(route.getWaypoints().getWaypoints().get(i));
            waypoints.add(newRouteWaypoint);
            j++;
        }

        // Iterates through the list of waypoints beginning at the second
        // waypoint in the route, while
        // creating route legs in a backward fashion. The values of the original
        // leg is copied also.
        // Perhaps this can be done more efficiently with a copy constructor (or
        // method) for route legs, but
        // forward referencing a waypoint which has not been created has to be
        // solved in some way...
        for (int i = 1; i < waypoints.size(); i++) {
            Waypoint currWaypoint = waypoints.get(i);
            Waypoint prevWaypoint = waypoints.get(i - 1);
            Leg routeLeg = waypoints.get(i).getLeg();
            if (routeLeg == null) {
                routeLeg = new LegImpl();
            }
            Leg newRouteLeg = new LegImpl(routeLeg);


            //TODO verify
//            prevWaypoint.setOutLeg(newRouteLeg);
//            currWaypoint.setInLeg(newRouteLeg);

            currWaypoint.setLeg(newRouteLeg);
            prevWaypoint.setLeg(newRouteLeg);
        }
        WayPoints wayPoints = new WayPointsImpl(null, null, waypoints);
        newRoute.setWaypoints(wayPoints);

//        adjustStartTime();
//        calcValues(true);

        newRoute.setName(route.getName() + "_reversed");

        return newRoute;
    }


    /**
     * Creates a deep copy of a route object
     * @param selectedRoute route to copy
     * @return deep copied rout
     */
    public static Route copyRoute(Route selectedRoute) {
        if (selectedRoute == null) {
            return null;
        }
        Route routeCopy = new RouteImpl(selectedRoute);
        WayPoints wayPoints = routeCopy.getWaypoints();
        if (wayPoints != null){
            wayPoints = new WayPointsImpl(wayPoints);
            routeCopy.setWaypoints(wayPoints);


            DefaultWayPoint defaultWP = wayPoints.getDefaultWaypoint();
            if (defaultWP != null){
                defaultWP = new DefaultWayPointImpl(defaultWP);
                wayPoints.setDefaultWaypoint(defaultWP);
            }

            List<Waypoint> waypoints = wayPoints.getWaypoints();
            List<Waypoint> copyPoints = new ArrayList<>();
            for (Waypoint waypoint : waypoints){
                Leg leg = waypoint.getLeg();
                if (leg != null){
                    leg = new LegImpl(leg);
                }

                Coordinate coordinate = waypoint.getPosition();
                if (coordinate != null){
                    coordinate = new CoordinateImpl(coordinate);
                }

                Waypoint copy = new WaypointImpl(waypoint);
                copy.setLeg(leg);
                copy.setPosition(coordinate);

                copyPoints.add(copy);
            }

            WayPoints copiedWaypoint = new WayPointsImpl();
            copiedWaypoint.setDefaultWaypoint(defaultWP);
            copiedWaypoint.getWaypoints().addAll(copyPoints);


            routeCopy.setWaypoints(copiedWaypoint);
        }

        routeCopy.setName(routeCopy.getName() + " copy");

        return routeCopy;
    }


    /**
     * Extracts and returns the file extension from the provided filename.
     * If the filename is null, empty, or does not contain an extension, an empty string is returned.
     *
     * @param filename the name of the file from which to extract the extension
     * @return the file extension as a String, or an empty string if no extension is found
     */
    public static String getFileExtension(String filename) {
        if (filename != null) {
            int dotIndex = filename.lastIndexOf('.');
            if (dotIndex > 0 && dotIndex < filename.length() - 1) {
                return filename.substring(dotIndex + 1);
            }
        }
        return "";
    }

    public static Route loadRouteFromFile(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                Route route = RouteImportImpl.getRoute(sb.toString(), CRSUtils.WGS84_2D, getFileExtension(file.getName()));
                if (route != null) {
                    return route;
                }
            } finally {
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean saveRouteToFile(File file, Route route) throws IOException {
        if (!file.exists()) {
            boolean bool = file.createNewFile();
            if (!bool) {
                logger.error("Couldn't save file for route: " + route.getName());
                return false;
            }
        }
        return RouteExportImpl.exportRoute(route, file);
    }
}
