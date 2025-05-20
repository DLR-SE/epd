package de.emir.epd.routemanager.cmd.transactions;

import java.util.List;

import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.WayPoints;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.rcp.model.AbstractModelTransaction;

public class AppendWaypointTransaction extends AbstractModelTransaction {


    private Route route;
    private Waypoint newWaypoint;
    private Waypoint beforeWaypoint;
    private boolean canRedo = false;
    private boolean canUndo = false;
    /**
     * Index of the new waypoint.
     */
    private int index = -1;

    /**
     * Handles the addition of new waypoints into a existing route.
     *
     * @param route the route, where the waypoint must be added
     * @param beforeWaypoint the waypoint, which comes before the waypoint to add
     * @param newWaypoint the waypoint to add to the route
     */
    public AppendWaypointTransaction(Route route, Waypoint beforeWaypoint, Waypoint newWaypoint) {
        this.route = route;
        this.newWaypoint = newWaypoint;
        this.beforeWaypoint = beforeWaypoint;
    }

    /**
     * Adds the waypoint into the route.
     */
    @Override
    public void run() {
        WayPoints wps = route.getWaypoints();
        if (wps != null){
            List<Waypoint> waypoints = wps.getWaypoints();
            index = waypoints.indexOf(beforeWaypoint);
            if (index != -1) {
                index += 1;
                waypoints.add(index, newWaypoint);
                this.canUndo = true;
                this.canRedo = false;
                this.renameWaypoints();
            }
        }
    }

    /**
     * Undo the addition of the new waypoint.
     */
    @Override
    public void undo() {
        WayPoints wps = route.getWaypoints();
        if (wps != null){
            List<Waypoint> waypoints = wps.getWaypoints();
            waypoints.remove(index);
            canUndo = false;
            canRedo = true;
            renameWaypoints();
        }
    }

    /**
     * Redo the addition of the new waypoint into the route.
     */
    @Override
    public void redo() {
        run();
        this.canRedo = false;
        this.canUndo = true;
    }

    @Override
    public boolean canUndo() {
        return this.canUndo;
    }

    @Override
    public boolean canRedo() {
        return this.canRedo;
    }

    /**
     * Rename the waypoints of the Route according to their index, all waypoints with
     * custom (not generated) names are not changed.
     */
    private void renameWaypoints() {
        for (Waypoint w : route.getWaypoints().getWaypoints()) {
            int index = route.getWaypoints().getWaypoints().indexOf(w);
            if (w.getName().matches("WP_\\d+") || w.getName().isEmpty()) {
                w.setName("WP_" + index);
            }
        }
    }
}
