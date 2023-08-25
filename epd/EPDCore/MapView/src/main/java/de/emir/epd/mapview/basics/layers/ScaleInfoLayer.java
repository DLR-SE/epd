package de.emir.epd.mapview.basics.layers;

import de.emir.epd.mapview.views.map.AbstractMapLayer;
import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;
import de.emir.epd.mapview.views.map.MapView;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.impl.DistanceImpl;
import de.emir.rcp.manager.util.PlatformUtil;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class ScaleInfoLayer extends AbstractMapLayer {

	private boolean mRegistered = false;
	private Font mFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
	
	public ScaleInfoLayer() {
		
	}
	 
	@Override
	public void paint(BufferingGraphics2D g, IDrawContext c) {
		if (!mRegistered) {		//register for zoom changes
			MapView mapView = PlatformUtil.getViewManager().getView(MapView.class);
			mapView.getViewer().getJXMapViewer().addPropertyChangeListener(pcl -> {
				if (pcl.getPropertyName().equals("zoom"))
					markDirty();
			});
			mRegistered = true;
		}
		Rectangle bounds = c.getBounds();
		double w = bounds.getWidth();
		double h = bounds.getHeight();
		double h2 = h/2.0;
		//get the coordinates in the center of the image, to avoid curvage effects on high scale levels
		GeoPosition left = c.convert(new Point2D.Double(0.0, h2));
		GeoPosition right = c.convert(new Point2D.Double(w, h2));
		Coordinate cleft = new CoordinateImpl(left.getLatitude(), left.getLongitude(), CRSUtils.WGS84_2D);
		Coordinate cright = new CoordinateImpl(right.getLatitude(), right.getLongitude(), CRSUtils.WGS84_2D);
		Distance dist = cleft.getDistance(cright);
		
		
		String outtext;
		String uomAbbr = "[nM]";
		double dist_nm = dist.getAs(DistanceUnit.NAUTICAL_MILES);
		double dd = dist_nm / 100 * 20.0; // (less than) 20% of the lower right edge will be filled
		if (dd < 0.01f) {
			dd = (int)(dd * 1000) / 1000.0;
            outtext = String.format("%.3f %s", dd, uomAbbr);
		}else if (dd < 0.1f) {
			dd = (int)(dd * 100) / 100.0;
            outtext = String.format("%.3f %s", dd, uomAbbr);
		}else if (dd < 1.0f) { //TODO: change unit to property
        	dd = (int)(dd * 10) / 10.0;
            outtext = String.format("%.3f %s", dd, uomAbbr);
        } else if (dd < 10.0f) {
        	dd = (int)(dd / 1) * 1;
            outtext = String.format("%.2f %s", dd, uomAbbr);
        } else if (dd < 100.0f) {
        	dd = (int)(dd / 10) * 10;
            outtext = String.format("%.1f %s", dd, uomAbbr);
        } else {
        	dd = (int)(dd / 100) * 100;
            outtext = String.format("%.0f %s", dd, uomAbbr);
        }
        
		FontMetrics fm = g.getFontMetrics(mFont);
		int fh = fm.getHeight();
		int fw = fm.stringWidth(outtext);
		
		right = c.convert(new Point2D.Double(w - 20.0, h - 20.0));
		//FIXME CRSUtils.getTarget() calculations aren't quite right, latitude gets shifted but it should stay the same
		double[] nleft = CRSUtils.getTarget(
				new double[] {right.getLatitude(),  right.getLongitude()},
				new DistanceImpl(dd, DistanceUnit.NAUTICAL_MILES).getAs(DistanceUnit.METER),
				Math.toRadians(270),
				CRSUtils.WGS84_2D
		);
		//needed until CRSUtils.getTarget() is fixed
		nleft[0] = right.getLatitude();
		left = new GeoPosition(nleft);
				
		Point2D lp = c.convert(left);
		g.setStroke(new BasicStroke(6));
		g.setColor(Color.DARK_GRAY);		
		g.draw(new Line2D.Double(lp, c.convert(right)));
		
		lp.setLocation(lp.getX() - fw - 5.0, lp.getY() - fh / 4.0);
		g.setFont(mFont);
		g.drawString(outtext, (float)lp.getX(), (float)lp.getY());
		
	}

	@Override
	public boolean isVisibilityUserControlled() {
		return true;
	}

	@Override
	public void modelChanged() {
	}
	
}
