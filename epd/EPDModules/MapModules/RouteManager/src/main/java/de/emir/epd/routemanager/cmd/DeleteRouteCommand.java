package de.emir.epd.routemanager.cmd;

import java.util.List;

import de.emir.epd.routemanager.IRouteManager;
import de.emir.epd.routemanager.IRouteManager.IRouteAccessModel;
import de.emir.model.domain.maritime.iec61174.Route;

public class DeleteRouteCommand extends AbstractSelectedRouteCommand {

    private IRouteManager mRouteManager;

	public DeleteRouteCommand(IRouteManager rm) {
        super();
        mRouteManager = rm;
    }

    @Override
    public void execute() {
        List<Route> selectedRoutes = getSelectedRoutes();

        for (Route selectedRoute : selectedRoutes) {
        	IRouteAccessModel group = mRouteManager.getParent(selectedRoute);
        	if (group != null)
        		group.deleteRoute(selectedRoute); //this shall be implemented as transaction
        }
        
    }
}
