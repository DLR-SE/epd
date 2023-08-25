package de.emir.epd.routemanager.map;

import java.awt.event.MouseEvent;

import org.jxmapviewer.viewer.GeoPosition;

import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.mapview.views.map.MapViewerWithTools;
import de.emir.epd.mapview.views.tools.AbstractMapViewTool;
import de.emir.epd.routemanager.IRouteManager;
import de.emir.epd.routemanager.cmd.transactions.AppendWaypointTransaction;
import de.emir.epd.routemanager.cmd.transactions.DeleteWaypointTransaction;
import de.emir.epd.routemanager.ids.RouteManagerBasic;
import de.emir.model.domain.maritime.iec61174.Iec61174Package;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.domain.maritime.iec61174.impl.WaypointImpl;
import de.emir.model.universal.crs.impl.WGS842DImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.transactions.CompoundTransaction;
import de.emir.rcp.model.transactions.SetValueTransaction;

public class EditRouteTool extends AbstractMapViewTool {

    private Coordinate oldValues;
    private Waypoint currentSelection;
    private MapViewerWithTools viewer;

    //checks whether the waypoint is actually dragged or just clicked
    private boolean mouseDragged = false;

    //saves the time an object was pressed, just to compute how long an object was pressed
    private long mouseHold = 0;

    public EditRouteTool() {
        updateUsable();
        IRouteManager.getDefaultRouteManager().subscribeStructureChanges(sub -> updateUsable());
    }

    private void updateUsable() {
        setUseable(IRouteManager.getDefaultRouteManager().getAllRoutes().isEmpty() == false);
    }

    @Override
    public void activate() {
        reset();
    }

    @Override
    public void deactivate() {
        reset();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            // 1. Gucke ob in "ROUTE_FOCUS_CONTEXT" ein objekt vom typ WP hinterlegt ist
            // 2. Wenn ja, speichere referenz auf dieses objekt in globaler variable
            // Speichere dir alte koordinaten dieses WP (lat, lon) in globaler variable
            Object obj = PlatformUtil.getSelectionManager().getSelectedObject(MVBasic.MAP_FOCUS_CTX);
            if (obj instanceof Waypoint) {
                currentSelection = (Waypoint) obj;
                oldValues = currentSelection.getPosition().copy();
                mouseHold = e.getWhen();
                e.consume();
            }
        }
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){
            Object object = PlatformUtil.getSelectionManager().getSelectedObject(RouteManagerBasic.CTX_LINE_SELECTION);
           
            if (object instanceof Waypoint) {
                Waypoint waypoint = (Waypoint) object;
                
                GeoPosition geoPosition = viewer.convert(e.getPoint());
                Waypoint nextWP = new WaypointImpl();
                Coordinate coordinate = new CoordinateImpl(geoPosition.getLatitude(), geoPosition.getLongitude(), new WGS842DImpl());
                nextWP.setPosition(coordinate);
                
                
                Route route = (Route) waypoint.getUContainer().getUContainer();
                                
                PlatformUtil.getModelManager().getModelProvider().getTransactionStack().run(new AppendWaypointTransaction(route, waypoint, nextWP));
                viewer.redrawLayers();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 1) {
                        
            // Merke dir neue Werte (lan, lat)
            // Setze koordinaten des WP zurück auf alte werte
            // Setcommand nutzen um auf neue werte zu setzen

            if (currentSelection != null && mouseDragged) {

                Coordinate newValues = currentSelection.getPosition();
                currentSelection.setPosition(oldValues);

                CompoundTransaction ct = new CompoundTransaction();

                ct.add(new SetValueTransaction(currentSelection, Iec61174Package.Literals.Waypoint_position,
                        newValues));

                PlatformUtil.getModelManager().getModelProvider().getTransactionStack().run(ct);
                e.consume();

                currentSelection = null;
                mouseDragged = false;
                mouseHold = 0;
            }else if (currentSelection != null && (mouseDragged == false)) {
                //delete waypoint if it's not dragged and clickhold is higher equals one second
                long time = e.getWhen() - mouseHold;
                if (time >= 1000 ) {
                    PlatformUtil.getModelManager().getModelProvider().getTransactionStack().run(
                            new DeleteWaypointTransaction((Route) currentSelection.getUContainer().getUContainer(), currentSelection));
                    viewer.redrawLayers();   
                }
                
                currentSelection = null;
                mouseDragged = false;
                mouseHold = 0;
            }
        }
        reset();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Wenn globale WP variable != null:
        // Berechne koordinaten der momentanen Mausposition
        // Setze diese Koordinaten im WP (ohne setcommand)

        if (currentSelection != null) {
            GeoPosition geoPosition = viewer.convert(e.getPoint());
            Coordinate coordinate = new CoordinateImpl();
            coordinate.setLatLon(geoPosition.getLatitude(), geoPosition.getLongitude());
            currentSelection.setPosition(coordinate);
            mouseDragged = true;
            e.consume();
        }

    }

    private void reset() {
        // Wenn globale Variable mit WP != null:
        // Setze lan lot auf alte Werte, setze alle variablen auf 0/null
        if (currentSelection != null) {
            currentSelection.setPosition(oldValues);
        }

        currentSelection = null;
        oldValues = null;
        mouseDragged = false;
        mouseHold = 0;
    }

    @Override
    public void init(MapViewerWithTools viewer) {
        this.viewer = viewer;

    }

    @Override
    public void modelChanged() {

    }
}
