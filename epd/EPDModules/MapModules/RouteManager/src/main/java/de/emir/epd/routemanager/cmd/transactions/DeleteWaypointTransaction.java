package de.emir.epd.routemanager.cmd.transactions;

import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.WayPoints;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.rcp.model.AbstractModelTransaction;

import java.util.List;

public class DeleteWaypointTransaction extends AbstractModelTransaction {

    private Route route;
    private Waypoint waypoint;

    private boolean canUndo = false;
    private boolean canRedo = false;

    private int index = -1;

    public DeleteWaypointTransaction(Route route, Waypoint waypoint){
        this.route = route;
        this.waypoint = waypoint;
    }

    @Override
    public void run() {
        WayPoints wayPoints = route.getWaypoints();
        if (wayPoints != null){
            List<Waypoint> wpList = wayPoints.getWaypoints();
            index = wpList.indexOf(waypoint);
            if (index != -1){
                canUndo = wpList.remove(index) != null;
                canRedo = false;
            }
        }
    }

    @Override
    public void undo() {
        WayPoints wayPoints = route.getWaypoints();
        if (wayPoints != null){
            List<Waypoint> wpList = wayPoints.getWaypoints();
            wpList.add(index, waypoint);
            canRedo = true;
            canUndo = false;
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
        return canUndo;
    }

    @Override
    public boolean canRedo() {
        return canRedo;
    }
}
