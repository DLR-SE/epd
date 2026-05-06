/**
 * 
 */
package de.emir.epd.routemanager.test;

import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;

import de.emir.epd.routemanager.ids.RouteManagerBasic;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.domain.maritime.iec61174.impl.RouteImpl;
import de.emir.model.domain.maritime.iec61174.impl.WayPointsImpl;
import de.emir.model.domain.maritime.iec61174.impl.WaypointImpl;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.serialization.xml.XMLSerializer;

/**
 *  Collection of helper functions for the copy xml route tests.
 */
public class CopyXMLRouteCommandTestHelper {
	private static final Logger LOG = ULog.getLogger(CopyXMLRouteCommandTestHelper.class);

	/**
	 * Tests if exactly one route got selected (in the SelectionManager)
	 * @return true if a single route did get selected else false
	 */
	public static boolean didSelectRoute() {
		List<?> selection = PlatformUtil.getSelectionManager()
				.getSelectedObjectAsList(RouteManagerBasic.CTX_ROUTE_SELECTION);
		// Check if only one object is selected and that it is a route object
		if (selection.size() == 1 && selection.get(0) instanceof Route) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Tests if at least two route got selected (in the SelectionManager)
	 * @return true if at least 2 routes did get selected else false
	 */
	public static boolean didSelectRoutes() {
		List<?> selection = PlatformUtil.getSelectionManager()
				.getSelectedObjectAsList(RouteManagerBasic.CTX_ROUTE_SELECTION);
		// Check if only one object is selected and that it is a route object
		if (selection.size() > 1 && selection.get(0) instanceof Route) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 *	Create dummy route object with 4 waypoints
	 */
	public static Route createDummyRouteObj() {
		double lat = 53.14038;
		double lon1 = 8.220;
		double lon2 = 8.221;
		double lon3 = 8.222;
		double lon4 = 8.223;
		Coordinate coord1 = new CoordinateImpl(lat, lon1, CRSUtils.WGS84_2D);
		Coordinate coord2 = new CoordinateImpl(lat, lon2, CRSUtils.WGS84_2D);
		Coordinate coord3 = new CoordinateImpl(lat, lon3, CRSUtils.WGS84_2D);
		Coordinate coord4 = new CoordinateImpl(lat, lon4, CRSUtils.WGS84_2D);

		Waypoint wp1 = new WaypointImpl();
		wp1.setPosition(coord1);
		Waypoint wp2 = new WaypointImpl();
		wp2.setPosition(coord2);
		Waypoint wp3 = new WaypointImpl();
		wp3.setPosition(coord3);
		Waypoint wp4 = new WaypointImpl();
		wp4.setPosition(coord4);

		WayPointsImpl routeWayPoints = new WayPointsImpl();
		routeWayPoints.getWaypoints().add(wp1);
		routeWayPoints.getWaypoints().add(wp2);
		routeWayPoints.getWaypoints().add(wp3);
		routeWayPoints.getWaypoints().add(wp4);

		Route route = new RouteImpl();
		route.setName("Dummy Route");
		route.setWaypoints(routeWayPoints);
		return route;
	}

	/*
	 * Create dummy route object with 4 waypoints and select it
	 */
	public static Route selectSingleRoute() {
		try {
			Route route = createDummyRouteObj();
			List<Route> selectedRoutes = new ArrayList<>();
			selectedRoutes.add(route);

			// Select created route
			PlatformUtil.getSelectionManager().setSelection(RouteManagerBasic.CTX_ROUTE_SELECTION, selectedRoutes);
			return route;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	
	/*
	 * Create dummy route objects with 4 waypoints
	 */
	public static List<Route> createMultipleDummyRoutes(){
		double lat1 = 53.14038;
		double lat2 = 53.14048;
		double lon1 = 8.220;
		double lon2 = 8.221;
		double lon3 = 8.222;
		double lon4 = 8.223;
		Coordinate coord11 = new CoordinateImpl(lat1, lon1, CRSUtils.WGS84_2D);
		Coordinate coord12 = new CoordinateImpl(lat1, lon2, CRSUtils.WGS84_2D);
		Coordinate coord13 = new CoordinateImpl(lat1, lon3, CRSUtils.WGS84_2D);
		Coordinate coord14 = new CoordinateImpl(lat1, lon4, CRSUtils.WGS84_2D);
		Coordinate coord21 = new CoordinateImpl(lat2, lon1, CRSUtils.WGS84_2D);
		Coordinate coord22 = new CoordinateImpl(lat2, lon2, CRSUtils.WGS84_2D);
		Coordinate coord23 = new CoordinateImpl(lat2, lon3, CRSUtils.WGS84_2D);
		Coordinate coord24 = new CoordinateImpl(lat2, lon4, CRSUtils.WGS84_2D);

		Waypoint wp11 = new WaypointImpl();
		wp11.setPosition(coord11);
		Waypoint wp12 = new WaypointImpl();
		wp12.setPosition(coord12);
		Waypoint wp13 = new WaypointImpl();
		wp13.setPosition(coord13);
		Waypoint wp14 = new WaypointImpl();
		wp14.setPosition(coord14);
		Waypoint wp21 = new WaypointImpl();

		wp21.setPosition(coord21);
		Waypoint wp22 = new WaypointImpl();
		wp22.setPosition(coord22);
		Waypoint wp23 = new WaypointImpl();
		wp23.setPosition(coord23);
		Waypoint wp24 = new WaypointImpl();
		wp24.setPosition(coord24);

		WayPointsImpl routeWayPoints1 = new WayPointsImpl();
		routeWayPoints1.getWaypoints().add(wp11);
		routeWayPoints1.getWaypoints().add(wp12);
		routeWayPoints1.getWaypoints().add(wp13);
		routeWayPoints1.getWaypoints().add(wp14);
		WayPointsImpl routeWayPoints2 = new WayPointsImpl();
		routeWayPoints2.getWaypoints().add(wp21);
		routeWayPoints2.getWaypoints().add(wp22);
		routeWayPoints2.getWaypoints().add(wp23);
		routeWayPoints2.getWaypoints().add(wp24);

		Route route1 = new RouteImpl();
		route1.setName("Dummy Route 1");
		route1.setWaypoints(routeWayPoints1);
		Route route2 = new RouteImpl();
		route2.setName("Dummy Route 2");
		route2.setWaypoints(routeWayPoints1);
		
		List<Route> routes = new ArrayList<>();
		routes.add(route1);
		routes.add(route2);
		return routes;
	}
	
	/*
	 *  Create dummy route objects with 4 waypoints and select them
	 */
	public static List<Route> selectMultipleRoutes() {
		try {
			List<Route> selectedRoutes = createMultipleDummyRoutes();

			// Select created routes
			PlatformUtil.getSelectionManager().setSelection(RouteManagerBasic.CTX_ROUTE_SELECTION, selectedRoutes);
			return selectedRoutes;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/*
	 *  Reads the clipboard and converts its string to a UObject
	 */
	public static UObject readObjectFromClipboard() {
		if (GraphicsEnvironment.isHeadless()) {
			LOG.debug("Running in Headless-Mode, no clipboard available!");
			return null;
		}

		try {
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			String clipboardData = (String) clipboard.getData(DataFlavor.stringFlavor);
			ByteArrayInputStream clipboardDataStream = new ByteArrayInputStream(clipboardData.getBytes("UTF-8"));

			XMLSerializer serializer = new XMLSerializer();
			UObject clipboardObj = serializer.deserialize(clipboardDataStream);

			return clipboardObj;
		} catch (java.lang.IllegalStateException | IOException | UnsupportedFlavorException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * Tests if clipboard is empty
	 */
	public static boolean isClipboardEmpty() {
		if (GraphicsEnvironment.isHeadless()) {
			LOG.debug("Running in Headless-Mode, no clipboard available!");
			return false;
		}

		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		try {
			String clipboardData = (String) clipboard.getData(DataFlavor.stringFlavor);
			return clipboardData.isEmpty();
		} catch (java.lang.IllegalStateException | UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * Tests if nothing is selected in the SelectionManager
	 */
	public static boolean isSelectionEmpty() {
		List<?> selection = PlatformUtil.getSelectionManager()
				.getSelectedObjectAsList(RouteManagerBasic.CTX_ROUTE_SELECTION);
		return selection.size() == 0;
	}

	/*
	 * Sets the users clipboard to an empty string
	 */
	public static void clearClipboard() {
		if (GraphicsEnvironment.isHeadless()) {
			LOG.debug("Running in Headless-Mode, no clipboard available!");
			return;
		}
		try {
			// Clear clipboard
			StringSelection emptyString = new StringSelection("");
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(emptyString, emptyString);
		} catch (java.lang.IllegalStateException e) {
			LOG.error("Cant open clipboard to clear it. Probably another program uses it currently.");
			e.printStackTrace();
			return;
		}
	}

	/*
	 * Clears the SelectionManger from all selections
	 */
	public static void clearSelections() {
		List<Route> emptyList = new ArrayList<>();
		PlatformUtil.getSelectionManager().setSelection(RouteManagerBasic.CTX_ROUTE_SELECTION, emptyList);
	}
	
	/*
	 * Write provided object as an xml string to the users clipboard
	 */
	public static void writeXMLObjectToClipboard(UObject obj) {
		if (GraphicsEnvironment.isHeadless()) {
			LOG.debug("Running in Headless-Mode, no clipboard available!");
			return;
		}

		if (obj == null) {
			LOG.debug("Object to write to clipboard is null.!");
			return;
		}

		OutputStream output = new OutputStream() {
			private StringBuilder string = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b);
			}

			// Netbeans IDE automatically overrides this toString()
			public String toString() {
				return this.string.toString();
			}
		};

		// Serialize selected route object
		XMLSerializer serializer = new XMLSerializer();
		serializer.serialize(obj, output);
		String representation = output.toString();

		try {
			// Copy xml string to clipboard
			StringSelection selection = new StringSelection(representation);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selection, selection);
		} catch (IllegalStateException e) {
			LOG.error("Cant open clipboard to clear it. Probably another program uses it currently.");
			return;
		}
	}

}
