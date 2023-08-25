package de.emir.model.universal.crs.internal.transform;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.Polar2D;
import de.emir.model.universal.crs.WGS842D;
import de.emir.model.universal.crs.internal.ICoordinateTransform;
import de.emir.model.universal.crs.internal.calc.IGeodeticCalculator;
import de.emir.model.universal.crs.internal.calc.VincentCalculator;
import de.emir.model.universal.math.Vector2D;

public class WGS842D_to_Polar2D implements ICoordinateTransform {

	IGeodeticCalculator mCalculator = new VincentCalculator();

	@Override
	public boolean canTransform(CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		return src instanceof WGS842D && dst instanceof Polar2D;
	}

	@Override
	public double[] transform(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
		WGS842D src = (WGS842D)_src;
		Polar2D dst = (Polar2D)_dst;
		// assume the center of src to be expressed in WGS84 == lat / lon
		Vector2D center = (Vector2D)dst.getOrigin();
		
		return mCalculator.getDistanceAndAzimuth(center.getX(), center.getY(), in[0], in[1]);
	}

	@Override
	public double[] transformDirection(double[] ds, CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		// TODO
		return null;
	}
}
