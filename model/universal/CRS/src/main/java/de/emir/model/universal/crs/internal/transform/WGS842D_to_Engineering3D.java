package de.emir.model.universal.crs.internal.transform;

import java.util.List;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.crs.Engineering3D;
import de.emir.model.universal.crs.WGS842D;
import de.emir.model.universal.crs.WGS84CRS;
import de.emir.model.universal.crs.internal.ICoordinateTransform;
import de.emir.model.universal.crs.internal.calc.IGeodeticCalculator;
import de.emir.model.universal.crs.internal.calc.VincentCalculator;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.Vector3D;


public class WGS842D_to_Engineering3D implements ICoordinateTransform {
	IGeodeticCalculator mCalculator = null;

	public WGS842D_to_Engineering3D() {
		this(new VincentCalculator());
	}

	public WGS842D_to_Engineering3D(IGeodeticCalculator gc) {
		mCalculator = gc;
	}

	@Override
	public boolean canTransform(CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		return src instanceof WGS84CRS && dst instanceof Engineering3D;
	}

	@Override
	public double[] transform(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
		WGS84CRS src = (WGS84CRS)_src;
		Engineering3D dst = (Engineering3D)_dst;
		// assume the center of src to be expressed in WGS84 == lat / lon
		Vector3D center = (Vector3D)dst.getOrigin();
		double[] da = mCalculator.getDistanceAndAzimuth(center.getX(), center.getY(), in[0], in[1]);
		
		double offset = dst.getOrientationOffset().get(0); //consider the orientation of the destination (source has no orientation)
		Vector3D dir = (Vector3D)dst.bearingToDirection(-(da[1] - offset), 0);
		return new double[]{dir.getX() * da[0], dir.getY() * da[0], dir.getZ() * da[0]};
	}

	@Override
	public double[] transformDirection(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
		WGS84CRS src = (WGS84CRS)_src;
		Engineering3D dst = (Engineering3D)_dst;
		
		double mag = Math.sqrt(in[0] * in[0] + in[1] * in[1]);
		double bearing = src.directionToBearing(in[0]/mag, in[1]/mag, Double.NaN).get(0); //TODO: check if mag == 0
		List<Double> offsetL = dst.getOrientationOffset();
		double offsetAzimuth = offsetL.get(0); //consider the orientation of the destination (source has no orientation)
		double offsetOmega = offsetL.size() >= 2 ? offsetL.get(1) : 0;
		Vector3D dir = (Vector3D)dst.bearingToDirection(bearing - offsetAzimuth, offsetOmega);
		return new double[]{dir.getX() * mag, dir.getY() * mag, dir.getZ() * mag};
	}
}
