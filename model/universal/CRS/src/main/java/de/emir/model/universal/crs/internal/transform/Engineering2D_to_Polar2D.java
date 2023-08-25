package de.emir.model.universal.crs.internal.transform;


import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.crs.Polar2D;
import de.emir.model.universal.crs.internal.ICoordinateTransform;
import de.emir.model.universal.crs.internal.calc.IGeodeticCalculator;
import de.emir.model.universal.crs.internal.calc.VincentCalculator;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.math.Vector2D;

public class Engineering2D_to_Polar2D implements ICoordinateTransform {
	IGeodeticCalculator mCalculator = new VincentCalculator();
	Engineering2D_to_WGS842D mEng2Wgs = new Engineering2D_to_WGS842D();

	@Override
	public boolean canTransform(CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		return src != null && dst != null && src instanceof Engineering2D && dst instanceof Polar2D;
	}

	@Override
	public double[] transform(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
		Engineering2D src = (Engineering2D)_src;
		Polar2D dst = (Polar2D)_dst;
		// FIXME: until we find a better solution we do the following
		// - convert in_{Eng} into WGS84
		// - convert in_{WGS84} into Polar but with dst_center 
		double[] in_wgs = mEng2Wgs.transform(in, _src, CRSUtils.WGS84_2D);
		// transform into wgs84
		Vector2D dst_center = (Vector2D)dst.getOrigin();
		double[] azimuth_distance = mCalculator.getDistanceAndAzimuth(dst_center.getX(), dst_center.getY(), in_wgs[0], in_wgs[1]);
		// subtract the angle offset of the dst CRS (subtract since the angle rotates Clockwise) //TODO: Check if correct
		double angle = (azimuth_distance[1] - src.getOrientationOffset().get(0)) - dst.getOrientationOffset().get(0);
		// do some normalisation
		while (angle < -2 * Math.PI)
			angle += 2 * Math.PI;
		while (angle > 2 * Math.PI)
			angle -= 2 * Math.PI;
		return new double[]{azimuth_distance[0], angle};
	}

	@Override
	public double[] transformDirection(double[] ds, CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		// TODO
		return null;
	}
}
