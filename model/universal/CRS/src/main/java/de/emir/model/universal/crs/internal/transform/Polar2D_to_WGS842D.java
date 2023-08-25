package de.emir.model.universal.crs.internal.transform;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.Polar2D;
import de.emir.model.universal.crs.WGS842D;
import de.emir.model.universal.crs.internal.ICoordinateTransform;
import de.emir.model.universal.math.Vector2D;
import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;

public class Polar2D_to_WGS842D implements ICoordinateTransform {
	
	private Geodesic			mDirect = Geodesic.WGS84;

	@Override
	public boolean canTransform(CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		return src != null && dst != null && dst instanceof WGS842D && src instanceof Polar2D;
	}

	@Override
	public double[] transform(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
		Polar2D src = (Polar2D)_src;
		WGS842D dst = (WGS842D)_dst;
		Vector2D src_center = (Vector2D)src.getOrigin();
		
		double angle = Math.toDegrees(in[1] + src.getOrientationOffset().get(0));
		// in[0] is the distance, in[1] the angle //GeodedicCalculator needs the angle in degrees
		// normalize to -180 -> 180 (requirement of GeodeticCalculator)
		while (angle > 180)
			angle -= 360;
		while (angle < -180)
			angle += 360;
		
		GeodesicData dest = mDirect.Direct(src_center.getX(), src_center.getY(), angle, in[0]);
		return new double[]{dest.lat2, dest.lon2}; 
	}

	@Override
	public double[] transformDirection(double[] ds, CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		// TODO
		return null;
	}
}
