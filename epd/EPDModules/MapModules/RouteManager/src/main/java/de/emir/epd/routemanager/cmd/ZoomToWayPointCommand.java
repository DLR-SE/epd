package de.emir.epd.routemanager.cmd;

import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.mapview.views.map.MapView;
import de.emir.epd.routemanager.cmd.popup.AbstractWpPopUpCommand;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.rcp.manager.util.PlatformUtil;

public class ZoomToWayPointCommand extends AbstractWpPopUpCommand {

    public ZoomToWayPointCommand() {
        super();
    }

    @Override
    public void execute() {
        MapView mapView = (MapView) PlatformUtil.getViewManager().getVisibleView(MVBasic.MAP_VIEW_ID);
        if (mapView == null) {
            return;
        }

        try {
            Waypoint point = getSelectedWaypoint();
            mapView.zoomToCoordinate(point.getPosition());
        } catch (Exception exc) {
            Route selected = getSelectedRoute();
            if (selected.getWaypoints().getWaypoints().size() == 0) {
                return;
            }

            Waypoint point = selected.getWaypoints().getWaypoints().get(0);
            mapView.zoomToCoordinate(point.getPosition());
        }
    }
}
