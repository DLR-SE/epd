package de.emir.model.universal.crs.internal.transform;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.crs.WGS843D;
import de.emir.model.universal.crs.internal.ICoordinateTransform;
import de.emir.model.universal.crs.internal.calc.IGeodeticCalculator;
import de.emir.model.universal.crs.internal.calc.VincentCalculator;
import de.emir.model.universal.math.Vector2D;
import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;

public class Engineering2D_to_WGS843D implements ICoordinateTransform {
	
	private IGeodeticCalculator mCalculator = new VincentCalculator();
	private Geodesic			mDirect = Geodesic.WGS84;

	public Engineering2D_to_WGS843D(){
	}
	@Override
	public boolean canTransform(CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		return src instanceof Engineering2D && dst instanceof WGS843D;
	}

	@Override
	public double[] transform(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
		Engineering2D src = (Engineering2D)_src;
		// assume the center of src to be expressed in WGS84 == lat / lon
		Vector2D center = (Vector2D)src.getOrigin();
		double dx = in[0];
		double dy = in[1];
		double square_length = dx * dx + dy * dy;
		double length = Math.sqrt(square_length); //distance from center of Engineering CRS
		double base_angle = src.getOrientationOffset().get(0);
		// rotation of the source crs
		// angle from (dx, dy) agains north (0, 1)
		double angle = src.directionToBearing(dx, dy, 0).get(0);
		angle = base_angle + angle;
		// remove rotation of src crs
		// the calculator expect the angle in degrees and normalized to -180 -> 180
		angle = Math.toDegrees(angle);
		while (angle > 180)
			angle -= 360;
		while (angle < -180)
			angle += 360;
		
		GeodesicData dest = mDirect.Direct(center.get(0), center.get(1), angle, length);
		if (in.length == 2){
			//since we do not have a third component, we have to assume, that the object is on the floor, e.g. at altitude 0.0
			return new double[]{dest.lat2, dest.lon2, 0.0};
		}else if (in.length == 3){
			//the Z-Coordinate is used as altitude in both CRS and both use it as Meters. 
			return new double[]{dest.lat2, dest.lon2, in[2]};
		}
		return new double[]{dest.lat2, dest.lon2};
	}
	@Override
	public double[] transformDirection(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
		Engineering2D src = (Engineering2D)_src;
		// assume the center of src to be expressed in WGS84 == lat / lon
		Vector2D center = (Vector2D)src.getOrigin();
		double dx = in[0];
		double dy = in[1];
		double square_length = dx * dx + dy * dy;
		double length = Math.sqrt(square_length); //distance from center of Engineering CRS
		double base_angle = src.getOrientationOffset().get(0);
		// rotation of the source crs
		// angle from (dx, dy) agains north (0, 1)
		double angle = src.directionToBearing(dx, dy, 0).get(0);
		angle = base_angle - angle;
		
		double sin = Math.sin(angle); //2x2 Matrix - rotation
		double cos = Math.cos(angle);
		double x = in[0] * cos + in[1] * -sin;
		double y = in[0] * sin + in[1] *  cos;
		if (in.length >= 3)
			return new double[] {x,y,in[2]};
		return new double[]{x,y};
	}
}
