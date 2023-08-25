package de.emir.model.universal.crs.internal.transform;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.crs.EngineeringCRS;
import de.emir.model.universal.crs.WGS842D;
import de.emir.model.universal.crs.internal.ICoordinateTransform;
import de.emir.model.universal.crs.internal.calc.IGeodeticCalculator;
import de.emir.model.universal.crs.internal.calc.VincentCalculator;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector2D;
import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;

public class Engineering2D_to_WGS842D implements ICoordinateTransform {
	
	private IGeodeticCalculator mCalculator = new VincentCalculator();
	private Geodesic			mDirect = Geodesic.WGS84;

	public Engineering2D_to_WGS842D(){
	}
	@Override
	public boolean canTransform(CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		return src instanceof Engineering2D && dst instanceof WGS842D;
	}

	@Override
	public double[] transform(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
		EngineeringCRS src = (EngineeringCRS)_src;
		// assume the center of src to be expressed in WGS84 == lat / lon
		Vector center = src.getOrigin();
		double dx = in[0];
		double dy = in[1];
		double square_length = dx * dx + dy * dy;
		double length = Math.sqrt(square_length); //distance from center of Engineering CRS
		double base_angle = src.getOrientationOffset().get(0);
		// rotation of the source crs
		// angle from (dx, dy) agains north (0, 1)
		double angle = -src.directionToBearing(dx, dy, 0).get(0);
		angle = base_angle - angle;
		// remove rotation of src crs
		// the calculator expect the angle in degrees and normalized to -180 -> 180
		angle = Math.toDegrees(angle);
		while (angle > 180)
			angle -= 360;
		while (angle < -180)
			angle += 360;
		
		GeodesicData dest = mDirect.Direct(center.get(0), center.get(1), angle, length);
		
		return new double[]{dest.lat2, dest.lon2};
	}
	@Override
	public double[] transformDirection(double[] ds, CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		// TODO
		return null;
	}
}
