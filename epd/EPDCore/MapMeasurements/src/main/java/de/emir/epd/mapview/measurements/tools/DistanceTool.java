package de.emir.epd.mapview.measurements.tools;

import de.emir.epd.mapview.measurements.MMBasics;
import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;
import de.emir.epd.mapview.views.map.MapViewerWithTools;
import de.emir.epd.mapview.views.tools.AbstractMapViewTool;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class DistanceTool extends AbstractMapViewTool {

	private PropertyContext measurementContext;
	private IProperty<String> unitProperty;

	private BufferedImage pinImage;
	private Dimension piSize;

	private GeoPosition start = null;
	private GeoPosition end = null;

	private MapViewerWithTools viewer;
	private Cursor pinCursor;

	private Dimension pinOnMapSize = new Dimension(32, 32);
	private Distance distance;

	private DecimalFormat df = new DecimalFormat("0.00");

	public DistanceTool() {
		pinImage = IconManager.getImage(this, "icons/emiricons/32/location_on.png");
		piSize = new Dimension(pinImage.getWidth(), pinImage.getHeight());

		Toolkit toolkit = Toolkit.getDefaultToolkit();

		Point hotspot = new Point(15, 31);
		pinCursor = toolkit.createCustomCursor(pinImage, hotspot, "mapPin");

		measurementContext = PropertyStore.getContext(MMBasics.MEASUREMENT_PROPERTY_CONTEXT);
		unitProperty = measurementContext.getProperty(MMBasics.MEASUREMENT_DISTANCE_PROPERTY, DistanceUnit.NAUTICAL_MILES.name());
		if (unitProperty.getValue() == null){
			unitProperty.setValue(DistanceUnit.NAUTICAL_MILES.name());
		}
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
		viewer.getJXMapViewer().setCursor(Cursor.getDefaultCursor());

		setDirty(true);

	}

	@Override
	public void modelChanged() {

	}

	@Override
	public void paint(BufferingGraphics2D g, IDrawContext c) {

		Point2D pxStart = null;
		Point2D pxEnd = null;

		if (start != null) {
			pxStart = viewer.convert(start);

			if (end != null) {
				pxEnd = viewer.convert(end);

			} else {
				pxEnd = c.getMousePosition();

				if (pxEnd != null) {
					GeoPosition tmpEnd = c.convert(pxEnd);

					distance = new CoordinateImpl(start.getLatitude(), start.getLongitude(), CRSUtils.WGS84_2D).getDistance(new CoordinateImpl(tmpEnd.getLatitude(), tmpEnd.getLongitude(), CRSUtils.WGS84_2D));

				}

			}
		}

		if (pxStart != null && pxEnd != null) {

			g.setColor(Color.DARK_GRAY);
            g.setStroke(new BasicStroke(2));
			g.draw(new Line2D.Double(pxStart, pxEnd));

			double vx = (pxEnd.getX() - pxStart.getX());
			double vy = (pxEnd.getY() - pxStart.getY());

			double r = Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2));

			g.draw(new Arc2D.Double(pxStart.getX() - r, pxStart.getY() - r, 2 * r, 2 * r, 0, 360, Arc2D.OPEN));

			double infoPosX = pxStart.getX() - (pxStart.getX() - pxEnd.getX()) / 2;
			double infoPosY = pxStart.getY() - (pxStart.getY() - pxEnd.getY()) / 2;			
			
			String distFormated = getDistanceString();

			c.drawInfobox(g, distFormated, (int)infoPosX, (int)infoPosY, SwingConstants.CENTER, SwingConstants.TOP);

		}

		drawPoint(g, pxStart);
		drawPoint(g, pxEnd);

	}

	private String getDistanceString() {
		DistanceUnit unit = DistanceUnit.valueOf(unitProperty.getValue());
		String label = null;
		switch (unit){
			case METER:
				label = "m";
				break;
			case NAUTICAL_MILES:
				label = "nm";
				break;
			case MILLIMETER:
				label = "mm";
				break;
			case FOOT:
				label = "ft";
				break;
			case YARD:
				label = "yd";
				break;
			case CABLE:
				label = "cable";
				break;
			case MILES:
				label = "mi";
				break;
			case KILOMETER:
				label = "km";
				break;
			case CENTIMETER:
				label = "cm";
				break;
			default:
				label = unit.getLabel();
		}

		String distFormated = df.format(distance.getAs(unit));

		return distFormated + " " + label;

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

		if (start == null || end != null) {

			end = null;
			start = viewer.convert(new Point2D.Double(e.getX(), e.getY()));
			setDirty(true);
			return;
		}

		end = viewer.convert(new Point2D.Double(e.getX(), e.getY()));

		// Calculate distance
		distance = new CoordinateImpl(start.getLatitude(), start.getLongitude(), CRSUtils.WGS84_2D).getDistance(new CoordinateImpl(end.getLatitude(), end.getLongitude(), CRSUtils.WGS84_2D));

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
		if (start != null) {
			return true;
		}

		return super.isDirty();
	}

}
