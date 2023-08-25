package de.emir.epd.routemanager.map;

import java.awt.Cursor;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPopupMenu;

import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.mapview.views.map.AbstractMapLayer;
import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;
import de.emir.epd.routemanager.IRouteManager;
import de.emir.epd.routemanager.graphics.RouteGraphic;
import de.emir.epd.routemanager.graphics.impl.LegLineGraphic;
import de.emir.epd.routemanager.graphics.impl.WayPointCircleGraphic;
import de.emir.epd.routemanager.ids.RouteManagerBasic;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.domain.maritime.vessel.VoyageCharacteristic;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;

public class RouteLayer extends AbstractMapLayer implements Observer {
    protected List<RouteGraphic> routeGraphics = new ArrayList<>();

    protected JPopupMenu popupMenu;

    protected ITreeValueChangeListener modelListener;
    protected VoyageCharacteristic currentVC;

    protected IRouteManager mRouteManager;


    public RouteLayer() {
        if (PlatformUtil.getModelManager().getModelProvider() != null && PlatformUtil.getModelManager().getModelProvider().getModel() != null) {
            modelChanged();
        }
        mRouteManager = IRouteManager.getDefaultRouteManager();
//        this.popupMenu = ExtensionPointManager.getExtensionPoint(MenuExtensionPoint.class).getPopupMenus()
//                .get(RouteManagerBasic.ROUTE_POPUP_MENU);

        modelListener = evt -> setDirty(true);

        PlatformUtil.getSelectionManager().subscribe(MVBasic.MAP_FOCUS_CTX, sub -> setDirty(true));
        mRouteManager.subscribeRouteChanges(sub -> setDirty(true));
        mRouteManager.subscribeStructureChanges(sub -> setDirty(true));
    }

    @Override
    public void paint(BufferingGraphics2D g, IDrawContext c) {
        List<Route> routes = mRouteManager.getAllVisibleRoutes();
        paintRoutes(g, c, routes);
    }

    public void paintRoutes(BufferingGraphics2D g, IDrawContext c, List<Route> routes) {
        ArrayList<RouteGraphic> tmpRouteGraphics = new ArrayList<>();
        for (Route route : routes) {
            if (mRouteManager.getRouteViewUtil().isRouteInViewPort(g, c, route) == false) {
                continue;
            }
            RouteGraphic routeGraphic = new RouteGraphic(route, false);
            routeGraphic.paint(g, c);
            tmpRouteGraphics.add(routeGraphic);
        }

        routeGraphics = tmpRouteGraphics;
    }

    @Override
    public boolean isVisibilityUserControlled() {
        return false;
    }

    @Override
    public void modelChanged() {
        setDirty(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        setDirty(true);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Liste der Shape <-> WP zuordnung durchlaufen und auf intersection prüfen

        // Wenn intersection mit WP gefunden:
        // PlatformUtil.getSelectionManager().setSelection("ROUTE_FOCUS_CONTEXT", <focusedWP>)
        // Wenn nicht: PlatformUtil.getSelectionManager().setSelection("ROUTE_FOCUS_CONTEXT", null)

        Waypoint lastFocusWP = null;
        Waypoint focusWP = null;
        Route focusedRoute = null;
        Object lastFocus = PlatformUtil.getSelectionManager().getSelectedObject(MVBasic.MAP_FOCUS_CTX);

        Object lastFocusedLineWP = PlatformUtil.getSelectionManager().getSelectedObject(RouteManagerBasic.CTX_LINE_SELECTION);
        Waypoint lastLineWP = null;
        Waypoint lineWP = null;


        if (lastFocus instanceof Waypoint) {
            lastFocusWP = (Waypoint) lastFocus;
        }

        if (lastFocusedLineWP instanceof Waypoint) {
            lastLineWP = (Waypoint) lastFocusedLineWP;
        }

        for (RouteGraphic routeGraphic : routeGraphics) {
            Shape shape = mRouteManager.getRouteViewUtil().isOnRoute(e, routeGraphic);
            if (shape instanceof WayPointCircleGraphic) {
                focusWP = ((WayPointCircleGraphic) shape).getWaypoint();
                focusedRoute = ((RouteGraphic) routeGraphic).getRoute();
            } else if (shape instanceof LegLineGraphic) {
                lineWP = ((LegLineGraphic) shape).getStart();
                focusedRoute = ((RouteGraphic) routeGraphic).getRoute();
            }
        }

        if (focusWP != lastFocusWP) {
            PlatformUtil.getSelectionManager().setSelection(MVBasic.MAP_FOCUS_CTX, focusWP);
            PlatformUtil.getSelectionManager().setSelection(RouteManagerBasic.CTX_ROUTE_MAP_SELECTION, focusedRoute);
        }

        if (lineWP != lastLineWP) {
            PlatformUtil.getSelectionManager().setSelection(RouteManagerBasic.CTX_LINE_SELECTION, lineWP);
            PlatformUtil.getSelectionManager().setSelection(RouteManagerBasic.CTX_ROUTE_MAP_SELECTION, focusedRoute);
        }

        if (focusWP != null) {
            cursorAdapter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            e.consume();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 3) {
            openPopUpMenu(e);
        }
    }

    private void openPopUpMenu(MouseEvent e) {
//        for (Component comp : popupMenu.getComponents()) {
//            if (comp instanceof JMenuItem) {
//                CustomJMenuItem item = (CustomJMenuItem) comp;
//                AbstractCommand command = CommandManager.getCommand(item.getCommandID());
//
//                if (command != null && command.canExecute() == false) {
//                    item.setVisible(false);
//                }
//            }
//        }
//        this.popupMenu.show(e.getComponent(), e.getX(), e.getY());
    }
}
