package de.emir.epd.mapview.measurements.tools;

import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;
import de.emir.epd.mapview.views.map.MapViewerWithTools;
import de.emir.epd.mapview.views.tools.AbstractMapViewTool;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.AngleUnit;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class AngleTool extends AbstractMapViewTool {

	private BufferedImage pinImage;
	private Dimension piSize;

	private GeoPosition start = null;
	private GeoPosition center = null;
	private GeoPosition end = null;

	private MapViewerWithTools viewer;
	private Cursor pinCursor;

	private Dimension pinOnMapSize = new Dimension(32, 32);
	private double distance;

	private DecimalFormat df = new DecimalFormat("0");

	public AngleTool() {
		pinImage = IconManager.getImage(this, "icons/emiricons/32/location_on.png");
		piSize = new Dimension(pinImage.getWidth(), pinImage.getHeight());

		Toolkit toolkit = Toolkit.getDefaultToolkit();

		Point hotspot = new Point(15, 31);
		pinCursor = toolkit.createCustomCursor(pinImage, hotspot, "mapPin");
	}

	@Override
	public void init(MapViewerWithTools viewer) {
		this.viewer = viewer;

	}

	@Override
	public void activate() {

	}

	@Override
	public void deactivate() {
		start = null;
		end = null;
		center = null;
		viewer.getJXMapViewer().setCursor(Cursor.getDefaultCursor());

		setDirty(true);

	}

	@Override
	public void modelChanged() {

	}

	@Override
	public void paint(BufferingGraphics2D g, IDrawContext c) {

		Point2D pxStart = null;
		Point2D pxCenter = null;
		Point2D pxEnd = null;

		if (center != null) {
			pxCenter = viewer.convert(center);

			if (start != null) {
				pxStart = viewer.convert(start);

			} else {
				pxStart = c.getMousePosition();

			}
		}

		if (start != null) {

			if (end != null) {

				pxEnd = viewer.convert(end);

			} else {

				pxEnd = c.getMousePosition();

			}

		}

		if (pxStart != null && pxCenter != null) {
			
			g.setColor(Color.DARK_GRAY);
			g.draw(new Line2D.Double(pxStart, pxCenter));

		}

		if (pxCenter != null && pxEnd != null) {
			g.draw(new Line2D.Double(pxEnd, pxCenter));
		}
		
		if(pxStart != null && pxCenter != null && pxEnd != null && center != null && start != null) {
			
			GeoPosition tmpEnd = end;
			
			if(tmpEnd == null) {
				// Use mouse pos if end not set
				tmpEnd = c.convert(pxEnd);
			}
			
//			
//			double angle = getAngle(start.getLatitude(), start.getLongitude(), center.getLatitude(),
//					center.getLongitude(), tmpEnd.getLatitude(), tmpEnd.getLongitude());
			
//			System.out.println(angle);
			
			
			int width = 70;
			int height = 70;
			
			g.setBackground(new Color(0, 0, 0, 50));
			
			Coordinate cs = new CoordinateImpl(start.getLatitude(), start.getLongitude(), CRSUtils.WGS84_2D);
			Coordinate cc = new CoordinateImpl(center.getLatitude(), center.getLongitude(), CRSUtils.WGS84_2D);
			Coordinate te = new CoordinateImpl(tmpEnd.getLatitude(), tmpEnd.getLongitude(), CRSUtils.WGS84_2D);
			double angle1 = cs.getAzimuth(cc).getAs(AngleUnit.DEGREE);
			double angle2 = te.getAzimuth(cc).getAs(AngleUnit.DEGREE);
			g.fill(new Arc2D.Double(pxCenter.getX() - width/2.0, pxCenter.getY() - height/2.0, width, height, -angle1 + 90, -(angle2 - angle1), Arc2D.OPEN));
			
		}
		

		drawPoint(g, pxStart);
		drawPoint(g, pxCenter);
		drawPoint(g, pxEnd);

	}

	private String getAngleString() {

		String unit = "m";

		double d = distance;

		if (distance > 1000) {

			unit = "km";
			d = d / 1000;
		}

		String distFormated = df.format(d);

		return distFormated + " " + unit;

	}

	private void drawPoint(BufferingGraphics2D g, Point2D pos) {

		if (pos == null) {
			return;
		}

		g.drawImage(pinImage, (int)(pos.getX() - 15), (int)(pos.getY() - 31), (int)(pos.getX() + pinOnMapSize.width - 15), (int)(pos.getY() + pinOnMapSize.height - 31),
				0, 0, piSize.width, piSize.height);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if(center == null || end != null) {
			
			end = null;
			start = null;
			center = viewer.convert(new Point2D.Double(e.getX(), e.getY()));
			setDirty(true);
			return;
			
		}
		
		if(center != null && start == null) {
			
			start = viewer.convert(new Point2D.Double(e.getX(), e.getY()));
			setDirty(true);
			return;
		}	

		end = viewer.convert(new Point2D.Double(e.getX(), e.getY()));
		
		// Calculate angle

		// try {
		// CoordinateReferenceSystem crs = CRS.decode("EPSG:4326");
		//
		// GeodeticCalculator gc = new GeodeticCalculator(crs);
		//
		// Coordinate cS = new Coordinate(start.getLatitude(), start.getLongitude());
		// Coordinate cE = new Coordinate(end.getLatitude(), end.getLongitude());
		//
		//
		// gc.setStartingPosition(JTS.toDirectPosition(cS, crs));
		// gc.setDestinationPosition(JTS.toDirectPosition(cE, crs));
		//
		// distance = gc.getOrthodromicDistance();
		//
		// int totalmeters = (int) distance;
		// int km = totalmeters / 1000;
		// int meters = totalmeters - (km * 1000);
		// float remaining_cm = (float) (distance - totalmeters) * 10000;
		// remaining_cm = Math.round(remaining_cm);
		// float cm = remaining_cm / 100;
		//
		//
		// } catch (FactoryException | TransformException e1) {
		// e1.printStackTrace();
		// }

	}

//	private double getAngle(double lat1, double lon1, double lat2, double lon2, double lat3, double lon3) {
//
//		double result = Double.MAX_VALUE;
//		
//		Coordinate c1 = new Coordinate
//		double angle1 = getGlobalAngle(lat2, lon2, lat1, lon1);
//		double angle2 = getGlobalAngle(lat2, lon2, lat3, lon3);
//
//		result = angle1 - angle2;
//		
//		return result;
//	}
	
	

	@Override
	public void mouseEntered(MouseEvent e) {
		viewer.getJXMapViewer().setCursor(pinCursor);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		viewer.getJXMapViewer().setCursor(Cursor.getDefaultCursor());
	}

	@Override
	public boolean isDirty() {
		if (center != null) {
			return true;
		}

		return super.isDirty();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

		e.consume();
	}

}
