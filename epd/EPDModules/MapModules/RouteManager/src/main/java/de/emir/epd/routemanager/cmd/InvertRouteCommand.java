package de.emir.epd.routemanager.cmd;

import de.emir.epd.routemanager.IRouteManager;
import de.emir.epd.routemanager.ids.RouteManagerBasic;
import de.emir.epd.routemanager.util.RouteUtil;
import de.emir.model.domain.maritime.iec61174.Route;

/**
 * Inverts the given route and add it into the unasigned group of the associated IRouteManager
 * @author sschweigert
 *
 */
public class InvertRouteCommand extends AbstractSelectedSingleRouteCommand {
//	@note it's not desired to copy a route into the same Route-Group as it is already there.
//	This way the route may be assigned to any other vessel / group or even the source vessel, within an additional (explicit) step
	
    private IRouteManager mRouteManager;

	public InvertRouteCommand(IRouteManager rm) {
        super();
        mRouteManager = rm;
    }

    @Override
    public void execute() {
        Route selectedRoute = getSelectedRoute();
        if (selectedRoute == null) {
            return;
        }
        Route route = RouteUtil.reverse(selectedRoute);
        mRouteManager.getRouteModel(RouteManagerBasic.UNASIGNED_ROUTE_GROUP).assignRoute(route);
    }
}
