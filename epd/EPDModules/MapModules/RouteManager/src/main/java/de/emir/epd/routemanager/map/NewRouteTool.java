package de.emir.epd.routemanager.map;

import java.awt.event.MouseEvent;

import org.jxmapviewer.viewer.GeoPosition;

import de.emir.epd.mapview.views.map.MapViewerWithTools;
import de.emir.epd.mapview.views.tools.AbstractMapViewTool;
import de.emir.epd.routemanager.IRouteManager;
import de.emir.epd.routemanager.IRouteManager.IRouteAccessModel;
import de.emir.epd.routemanager.ids.RouteManagerBasic;
import de.emir.model.domain.maritime.iec61174.Iec61174Package;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.domain.maritime.iec61174.impl.LegImpl;
import de.emir.model.domain.maritime.iec61174.impl.RouteImpl;
import de.emir.model.domain.maritime.iec61174.impl.WayPointsImpl;
import de.emir.model.domain.maritime.iec61174.impl.WaypointImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.transactions.AddValueTransaction;

public class NewRouteTool extends AbstractMapViewTool {
    private Route route;
    private boolean movedSincePressed;
    private MapViewerWithTools viewer;

    public NewRouteTool() {
    }

    @Override
    public void activate() {
        route = null;
    }

    @Override
    public void deactivate() {
        route = null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        movedSincePressed = false;

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        movedSincePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (e.getButton() == 1 && movedSincePressed == false) {
            appendWayPoint(e);
            e.consume();
        }
    }

    private void appendWayPoint(MouseEvent e) {

        if (route == null) {
            route = new RouteImpl();
            route.setName("New Route ");
            route.setWaypoints(new WayPointsImpl());

            IRouteAccessModel ram = IRouteManager.getDefaultRouteManager().getRouteModel(RouteManagerBasic.UNASIGNED_ROUTE_GROUP);
            ram.assignRoute(route);
        }

        Waypoint waypoint = new WaypointImpl();
        waypoint.setName("WP_" + route.getWaypoints().getWaypoints().size());
        GeoPosition geoPosition = viewer.convert(e.getPoint());
        Coordinate coordinate = new CoordinateImpl();
        coordinate.setLatLon(geoPosition.getLatitude(), geoPosition.getLongitude());
        waypoint.setPosition(coordinate);
        waypoint.setLeg(new LegImpl());

        AddValueTransaction transaction = new AddValueTransaction(route.getWaypoints(), Iec61174Package.Literals.WayPoints_waypoints, waypoint);
        PlatformUtil.getModelManager().getModelProvider().getTransactionStack().run(transaction);
        IRouteManager.getDefaultRouteManager().setVisible(route, true);
    }

    @Override
    public void init(MapViewerWithTools viewer) {
        this.viewer = viewer;
    }

    @Override
    public void modelChanged() {

    }

}
