package de.emir.epd.routemanager.cmd.popup;


import java.util.List;

import de.emir.model.domain.maritime.iec61174.Iec61174Package;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.domain.maritime.iec61174.impl.LegImpl;
import de.emir.model.domain.maritime.iec61174.impl.WaypointImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.ModelTransactionStack;
import de.emir.rcp.model.transactions.AddValueTransaction;

public class AppendWayPointCommand extends AbstractWpPopUpCommand {

    public AppendWayPointCommand() {
        super();
    }

    @Override
    public void execute() {
        Route route = getSelectedRoute();
        List<Waypoint> waypoints = route.getWaypoints().getWaypoints();

        Waypoint lastWaypoint = waypoints.get(waypoints.size() - 1);
        Waypoint nextLastWaypoint = waypoints.get(waypoints.size() - 2);

        Coordinate startPoint = nextLastWaypoint.getPosition();
        Coordinate endPoint = lastWaypoint.getPosition();

        double slope = (endPoint.getLatitude() - startPoint.getLatitude()) / (endPoint.getLongitude() - startPoint.getLongitude());
        double dx = endPoint.getLongitude() - startPoint.getLongitude();

        double newX = endPoint.getLongitude() + dx;
        double newY = endPoint.getLatitude() + dx * slope;

        Coordinate coordinate = new CoordinateImpl();
        coordinate.setLatLon(newY, newX);

        Waypoint newWaypoint = new WaypointImpl();
        newWaypoint.setPosition(coordinate);
        newWaypoint.setLeg(new LegImpl());
        newWaypoint.setName("WP_" + waypoints.size());

        ModelTransactionStack stack = PlatformUtil.getModelManager().getModelProvider().getTransactionStack();
        stack.run(new AddValueTransaction(
                route.getWaypoints(),
                Iec61174Package.Literals.WayPoints_waypoints,
                newWaypoint));
    }

}
