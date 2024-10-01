package de.emir.epd.mapview.views.map;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import de.emir.model.universal.spatial.Coordinate;
import org.jxmapviewer.viewer.GeoPosition;

public interface IDrawContext {

	void drawInfobox(BufferingGraphics2D g, String text, int x, int y);
	void drawInfobox(BufferingGraphics2D g, String text, int x, int y, int hAlign, int vAlign);

	void drawPositionInfo(BufferingGraphics2D g, GeoPosition gp, int x, int y);
	void drawPositionInfo(BufferingGraphics2D g, int x, int y);

	void drawFocusArc(Graphics g, GeoPosition gp);

	/**
	 * Converts latitude and longitude values to pixel coordinates	
	 * @param longitude Longitude of point to convert
	 * @param latitude Latitude of point to convert
	 * @return 2D point on map
	 */
	Point2D convert(double longitude, double latitude);

	/**
	 * Converts a Coordinate object to pixel coordinates	
	 * @param longitude Coordinate of point to convert
	 * @return 2D point on map
	 */
	Point2D convert(Coordinate coordinate);

	/**
	 * Converts a GeoPosition object to pixel coordinates
	 * @param gp GeoPosition of point to convert
	 * @return 2D point on map
	 */
	Point2D convert(GeoPosition gp);

	/**
	 * Converts pixel coordinates to a GeoPosition object
	 * @param pixel Point2D to convert
	 * @return GeoPosition of object
	 */
	GeoPosition convert(Point2D pixel);
	
	Dimension getSize();
	
	int getZoom();
	
	Point getMousePosition();
	Rectangle getViewportBounds();
	Rectangle getBounds();
	
	int getTileSize();
	GeoPosition getCenterPosition();
	int getMaximumZoomLevel();

}