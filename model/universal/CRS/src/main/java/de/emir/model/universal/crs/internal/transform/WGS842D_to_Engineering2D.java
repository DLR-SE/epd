package de.emir.model.universal.crs.internal.transform;

import java.util.List;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.crs.EngineeringCRS;
import de.emir.model.universal.crs.WGS842D;
import de.emir.model.universal.crs.internal.ICoordinateTransform;
import de.emir.model.universal.crs.internal.calc.IGeodeticCalculator;
import de.emir.model.universal.crs.internal.calc.VincentCalculator;
import de.emir.model.universal.math.Vector2D;


public class WGS842D_to_Engineering2D implements ICoordinateTransform {
	IGeodeticCalculator mCalculator = null;

	public WGS842D_to_Engineering2D() {
		this(new VincentCalculator());
	}

	public WGS842D_to_Engineering2D(IGeodeticCalculator gc) {
		mCalculator = gc;
	}

	@Override
	public boolean canTransform(CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		return src instanceof WGS842D && dst instanceof Engineering2D;
	}

	@Override
	public double[] transform(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
		WGS842D src = (WGS842D)_src;
		EngineeringCRS dst = (EngineeringCRS)_dst;
		// assume the center of src to be expressed in WGS84 == lat / lon
		Vector2D center = (Vector2D)dst.getOrigin();
		double[] da = mCalculator.getDistanceAndAzimuth(center.getX(), center.getY(), in[0], in[1]);
		
		double offset = dst.getOrientationOffset().get(0); //consider the orientation of the destination (source has no orientation)
		Vector2D dir = (Vector2D) dst.bearingToDirection(da[1] - offset, 0); //Double.NaN);
		return new double[]{dir.getX() * da[0], dir.getY() * da[0]};
	}

	@Override
	public double[] transformDirection(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
		WGS842D src = (WGS842D)_src;
		Engineering2D dst = (Engineering2D)_dst;
		
		double mag = Math.sqrt(in[0] * in[0] + in[1] * in[1]);
		double bearing = src.directionToBearing(in[0]/mag, in[1]/mag, Double.NaN).get(0); //TODO: check if mag == 0
		double offset = dst.getOrientationOffset().get(0); //consider the orientation of the destination (source has no orientation)
	
		Vector2D dir = (Vector2D)dst.bearingToDirection(bearing - offset, 0); //Double.NaN);
		return new double[]{dir.getX() * mag, dir.getY() * mag};
	}
}
