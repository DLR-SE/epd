package de.emir.epd.routemanager.cmd;

import de.emir.epd.routemanager.IRouteManager;
import de.emir.epd.routemanager.IRouteManager.IRouteAccessModel;
import de.emir.epd.routemanager.ids.RouteManagerBasic;
import de.emir.epd.routemanager.util.RouteUtil;
import de.emir.model.domain.maritime.iec61174.Route;


/**
 * The CopyRouteCommand creates a copy of the selected route and assign the copy to the UNASSIGNED Route Group within the given RouteManager. 
 * @author sschweigert
 *
 */
public class CopyRouteCommand extends AbstractSelectedSingleRouteCommand {
//	@note it's not desired to copy a route into the same Route-Group as it is already there.
//		This way the route may be assigned to any other vessel / group or even the source vessel, within an additional (explicit) step
	
    private IRouteManager mRouteManager;

	public CopyRouteCommand(IRouteManager rm) {
        super();
        mRouteManager = rm;
    }

    @Override
    public void execute() {
        Route selectedRoute = getSelectedRoute();
        if (selectedRoute == null) {
            return;
        }

        Route route = RouteUtil.copyRoute(selectedRoute);
        IRouteAccessModel ram = mRouteManager.getRouteModel(RouteManagerBasic.UNASIGNED_ROUTE_GROUP);
        ram.assignRoute(route); //TODO this shall be implemented as transaction
    }
}
