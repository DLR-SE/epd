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
import de.emir.model.universal.math.Vector;
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

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean canTransform(CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		return src instanceof WGS84CRS && dst instanceof Engineering3D;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public double[] transform(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
		assert _src instanceof WGS84CRS;
		assert _dst instanceof Engineering3D;
		Engineering3D dst = (Engineering3D) _dst;
		// assume the center of src to be expressed in WGS84 == lat / lon
		Vector center = dst.getOrigin();
		// calculate direction and bearing from center of destination crs and point in source crs
		double[] da = mCalculator.getDistanceAndAzimuth(center.get(0), center.get(1), in[0], in[1]);
		double offset = dst.getOrientationOffset().getFirst(); //consider the orientation of the destination (source has no orientation)
		// transform bearing to direction
		Vector3D dir = (Vector3D)dst.bearingToDirection(-(da[1] - offset), 0);
		// Calculate point in destination crs by multiplying the direction vector of length 1 with the distance between the center of destination crs and point in source crs
		return new double[]{dir.getX() * da[0], dir.getY() * da[0], dir.getZ() * da[0]};
	}

	/**
	 * @inheritDoc
	 */
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
