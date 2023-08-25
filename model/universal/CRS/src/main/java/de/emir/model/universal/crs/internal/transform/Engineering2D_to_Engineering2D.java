package de.emir.model.universal.crs.internal.transform;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.crs.internal.ICoordinateTransform;
import de.emir.model.universal.crs.util.CRSUtils;

public class Engineering2D_to_Engineering2D implements ICoordinateTransform {
	
	@Override
	public boolean canTransform(CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		return src instanceof Engineering2D && dst instanceof Engineering2D;
	}
	
	WGS842D_to_Engineering2D 	mWGSTransform = new WGS842D_to_Engineering2D();

	@Override
	public double[] transform(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
		Engineering2D src = (Engineering2D)_src;
		Engineering2D dst = (Engineering2D)_dst;

		//need to transform dst_c into this crs, otherwise we can not compare both origins
		double[] src_origin = src.getOrigin().toArray();
		double[] dst_origin = dst.getOrigin().toArray();
		double[] src_arr = mWGSTransform.transform(src_origin, CRSUtils.WGS84_2D, _dst); //no need to use CRSUtils, we now src and dst CRS and the transformation for this
		double[] dst_arr = mWGSTransform.transform(dst_origin, CRSUtils.WGS84_2D, _dst);

		double dx = src_arr[0] - dst_arr[0];
		double dy = src_arr[1] - dst_arr[1];
		// this is actually a 3x3 Matrix multiplication with a 2x1 vector 
		double angle = dst.getOrientationOffset().get(0) - src.getOrientationOffset().get(0);
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		double x = in[0] * cos + in[1] * -sin + dx;
		double y = in[0] * sin + in[1] * cos + dy;
		return new double[]{x,y};
	}

	@Override
	public double[] transformDirection(double[] ds, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
		Engineering2D src = (Engineering2D)_src;
		Engineering2D dst = (Engineering2D)_dst;
		double angle = dst.getOrientationOffset().get(0) - src.getOrientationOffset().get(0);
		double DEGREE = Math.toDegrees(angle);
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		double x = ds[0] * cos + ds[1] * -sin;
		double y = ds[0] * sin + ds[1] *  cos;
		return new double[]{x,y};
	}
	
	
	
}
