package de.emir.epd.routemanager.cmd.transactions;

import java.util.List;

import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.WayPoints;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.rcp.model.AbstractModelTransaction;

public class AppendWaypointTransaction extends AbstractModelTransaction {


    private Route route;
    private Waypoint waypoint;
    private Waypoint after;
    private boolean canRedo = false;
    private boolean canUndo = false;
    private int index = -1;

    public AppendWaypointTransaction(Route route, Waypoint after, Waypoint waypoint) {
        this.route = route;
        this.waypoint = waypoint;
        this.after = after;
    }

    @Override
    public void run() {
        WayPoints wps = route.getWaypoints();
        if (wps != null){
            List<Waypoint> waypoints = wps.getWaypoints();
            index = waypoints.indexOf(after);
            if (index != -1) {
                index += 1;
                waypoints.add(index, waypoint);
                canUndo = true;
                canRedo = false;
            }
        }
    }

    @Override
    public void undo() {
        WayPoints wps = route.getWaypoints();
        if (wps != null){
            List<Waypoint> waypoints = wps.getWaypoints();
            waypoints.remove(index);
            canUndo = false;
            canRedo = true;
        }
    }

    @Override
    public void redo() {
        run();
        canRedo = false;
        canUndo = true;
    }

    @Override
    public boolean canUndo() {
        return false;
    }

    @Override
    public boolean canRedo() {
        return false;
    }
}
