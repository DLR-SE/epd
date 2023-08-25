package de.emir.epd.routemanager.util;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;
import de.emir.epd.routemanager.graphics.RouteGraphic;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Envelope;

public class RouteViewUtil {
	private static volatile RouteViewUtil INSTANCE;
	private static Object mutex = new Object();
	
	/**
	 * Private constructor for singleton. 
	 */
	private RouteViewUtil() {
	}
	
	public static RouteViewUtil getInstance() {
		RouteViewUtil result = INSTANCE;
		if (result == null) {
			synchronized (mutex) {
				result = INSTANCE;
				if (result == null) {
					result = INSTANCE = new RouteViewUtil();
				}
			}
		}
		return result;
	}
	
    public Shape isOnRoute(MouseEvent e, RouteGraphic list) {
        List<Shape> shapes = new ArrayList<>();
        shapes.addAll(list.getWaypointCircleShapes());
        shapes.addAll(list.getArrowShapes());
        shapes.addAll(list.getLegLines());
        
        return isOnShapes(e, shapes);
    }

    public Shape isOnShapes(MouseEvent e, List<? extends Shape> shapes) {
        for (Shape shape : shapes) {
            if (isOnShape(e, shape)) {
                return shape;
            }
        }
        return null;
    }

    public boolean isOnShape(MouseEvent e, Shape shape) {
        return shape.intersects(e.getX(), e.getY(), 5, 5);
    }

    public boolean isRouteInViewPort(BufferingGraphics2D g, IDrawContext c, Route route) {
        if (route == null) {
            return false;
        }

        Envelope envelope = route.getEnvelope();
        if (envelope == null) {
            return false;
        }

        List<Coordinate> vertices = envelope.getVertices();

        //viewPort bounds
        Rectangle viewPort = new Rectangle(0, 0,
                (int) c.getViewportBounds().getWidth(),
                (int) c.getViewportBounds().getHeight());

        Rectangle2D rectangle2D = new Rectangle2D.Double();
        for (Coordinate coordinate : vertices) {
            rectangle2D.add(c.convert(coordinate.getLongitude(), coordinate.getLatitude()));
        }

        return viewPort.intersects(rectangle2D);
    }
}
