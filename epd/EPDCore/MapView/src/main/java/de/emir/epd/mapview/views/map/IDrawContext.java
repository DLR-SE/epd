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

	Point2D convert(double longitude, double latitude);

	Point2D convert(Coordinate coordinate);

	Point2D convert(GeoPosition gp);
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