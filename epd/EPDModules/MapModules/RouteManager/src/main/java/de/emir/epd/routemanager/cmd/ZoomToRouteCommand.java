package de.emir.epd.routemanager.cmd;

import de.emir.epd.mapview.views.map.MapView;
import de.emir.epd.routemanager.IRouteManager;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.rcp.manager.util.PlatformUtil;

public class ZoomToRouteCommand extends AbstractSelectedSingleRouteCommand {

    private IRouteManager mRouteManager;

	public ZoomToRouteCommand(IRouteManager rm) {
        super();
        mRouteManager = rm;
        mRouteManager.subscribeRouteChanges(c -> handleCanExecute());

    }

    @Override
    protected void handleCanExecute() {
        super.handleCanExecute();
        if (canExecute()) {
            Route route = getSelectedRoute();
            if (route == null) {
            	return;
            }
            if (mRouteManager != null && !mRouteManager.isVisible(route)) {
                setCanExecute(false);
            }
        }
    }

    @Override
    public void execute() {
        Route selected = getSelectedRoute();

        if (selected == null) {
            return;
        }

        MapView mapView = PlatformUtil.getViewManager().getView(MapView.class);

        if (mapView == null) {
            return;
        }

        mapView.zoomToEnvelope(selected.getEnvelope());

    }

}
