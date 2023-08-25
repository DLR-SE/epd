package de.emir.model.universal.crs.internal.transform;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.crs.Polar2D;
import de.emir.model.universal.crs.internal.ICoordinateTransform;
import net.jafama.FastMath;

public class Polar2D_to_Engineering2D implements ICoordinateTransform {
	
	Polar2D_to_WGS842D mPol2Wgs = new Polar2D_to_WGS842D();
	WGS842D_to_Engineering2D mWgs2Eng = new WGS842D_to_Engineering2D();

	@Override
	public boolean canTransform(CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		return src != null && dst != null && dst instanceof Engineering2D && src instanceof Polar2D;
	}

	@Override
	public double[] transform(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
		Polar2D src = (Polar2D)_src;
		Engineering2D dst = (Engineering2D)_dst;
		// FIXME: until we find a better solution we do the following
		// - convert from Polar2D into WGS84
		// - convert in_{WGS84} into Engineering but with dst_center 
//		double[] in_wgs = mPol2Wgs.transform(in, _src, CRSUtils.WGS84_2D);
//		double[] result = mWgs2Eng.transform(in_wgs, CRSUtils.WGS84_2D, dst);
//		
		double distance = in[0];
		double angle = in[1]; //in radian
		double angleOffset = src.getOrientationOffset().isEmpty() ? 0 : src.getOrientationOffset().get(0);
		angle += angleOffset;
		
		//convert into 2D Cartesian CRS with orientation-offset of 0
		double x = FastMath.cos(angle) * distance;
		double y = FastMath.sin(angle) * distance;
		
		return new double[]{y,-x};
	}

	@Override
	public double[] transformDirection(double[] ds, CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		// TODO
		return null;
	}
}
