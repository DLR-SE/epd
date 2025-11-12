package de.emir.epd.mapview.measurements.tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import javax.swing.SwingConstants;

import org.jxmapviewer.viewer.GeoPosition;

import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;
import de.emir.epd.mapview.views.map.MapViewerWithTools;
import de.emir.epd.mapview.views.tools.AbstractMapViewTool;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.AngleUnit;
import de.emir.tuml.ucore.runtime.resources.IconManager;

public class AngleTool extends AbstractMapViewTool {
	private BufferedImage pinImage;
	private Dimension piSize;

	private GeoPosition start = null;
	private GeoPosition center = null;
	private GeoPosition end = null;

	private MapViewerWithTools viewer;
	private Cursor pinCursor;

	private Dimension pinOnMapSize = new Dimension(32, 32);
	/** Remember the instance id of this tools view. */
	protected String viewId = null;

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
		if (!((MapViewerWithTools) c).getMapView().getUniqueId().equals(viewId)) {
			return;
		}
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
            g.setStroke(new BasicStroke(2));
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
			
			int width = 70;
			int height = 70;
			
			g.setBackground(new Color(0, 0, 0, 50));
			
			Coordinate cs = new CoordinateImpl(start.getLatitude(), start.getLongitude(), CRSUtils.WGS84_2D);
			Coordinate cc = new CoordinateImpl(center.getLatitude(), center.getLongitude(), CRSUtils.WGS84_2D);
			Coordinate te = new CoordinateImpl(tmpEnd.getLatitude(), tmpEnd.getLongitude(), CRSUtils.WGS84_2D);
			double angle1 = cs.getAzimuth(cc).getAs(AngleUnit.DEGREE);
			double angle2 = te.getAzimuth(cc).getAs(AngleUnit.DEGREE);
//			g.fill(new Arc2D.Double(pxCenter.getX() - width/2.0, pxCenter.getY() - height/2.0, width, height, angle1, -(angle2 - angle1), Arc2D.OPEN));
            String angleFormated = "";
            if (angle2 < angle1) {
                g.draw(new Arc2D.Double(pxCenter.getX() - width/2.0, pxCenter.getY() - height/2.0, width, height, -90 - angle1, (angle1 - angle2), Arc2D.PIE));
                angleFormated = getAngleString(angle1 - angle2);
            } else {
                g.draw(new Arc2D.Double(pxCenter.getX() - width/2.0, pxCenter.getY() - height/2.0, width, height, -90 - angle2, (angle2 - angle1), Arc2D.PIE));
                angleFormated = getAngleString(angle2 - angle1);
            }
    
            double infoPosX = pxStart.getX() - (pxStart.getX() - pxEnd.getX()) / 2;
			double infoPosY = pxStart.getY() - (pxStart.getY() - pxEnd.getY()) / 2;			

            // only for debugging:
//            angleFormated = angleFormated + "\na1: " + String.format("%.2f", angle1) + "\na2: " + String.format("%.2f", angle2);
			c.drawInfobox(g, angleFormated, (int)infoPosX, (int)infoPosY, SwingConstants.CENTER, SwingConstants.TOP);
		}

		drawPoint(g, pxStart);
		drawPoint(g, pxCenter);
		drawPoint(g, pxEnd);

	}

	private String getAngleString(double angle) {
		String unit = "°";
		return String.format("%.2f", angle) + unit;
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
		if (center == null || end != null) {
			viewId = viewer.getMapView().getUniqueId();
			end = null;
			start = null;
			center = viewer.convert(new Point2D.Double(e.getX(), e.getY()));
			setDirty(true);
			return;
		}
		
		// Only allow the selection of an end point if the viewId matches. 
		if (viewer.getMapView().getUniqueId().equals(viewId)) {
			if (center != null && start == null) {
				start = viewer.convert(new Point2D.Double(e.getX(), e.getY()));
				setDirty(true);
				return;
			}	

			end = viewer.convert(new Point2D.Double(e.getX(), e.getY()));
		}
	}

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
		if (center != null && viewer.getMapView().getUniqueId().equals(viewId)) {
			return true;
		}

		return (super.isDirty() && viewer.getMapView().getUniqueId().equals(viewId));
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		e.consume();
	}
}
