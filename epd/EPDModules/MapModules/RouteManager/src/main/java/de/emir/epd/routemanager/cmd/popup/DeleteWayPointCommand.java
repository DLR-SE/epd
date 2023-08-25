package de.emir.epd.routemanager.cmd.popup;


import de.emir.model.domain.maritime.iec61174.Iec61174Package;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.ModelTransactionStack;
import de.emir.rcp.model.transactions.RemoveValueTransaction;

public class DeleteWayPointCommand extends AbstractWpPopUpCommand {

    public DeleteWayPointCommand() {
        super();
    }

    @Override
    public void execute() {
        Route route = getSelectedRoute();
        Waypoint toDelete = getSelectedWaypoint();

        ModelTransactionStack stack = PlatformUtil.getModelManager().getModelProvider().getTransactionStack();
        stack.run(new RemoveValueTransaction(
                route.getWaypoints(),
                Iec61174Package.Literals.WayPoints_waypoints,
                toDelete));
    }
}
