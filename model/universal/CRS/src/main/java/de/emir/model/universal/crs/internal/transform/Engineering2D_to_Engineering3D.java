package de.emir.model.universal.crs.internal.transform;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.crs.Engineering3D;
import de.emir.model.universal.crs.internal.ICoordinateTransform;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.Vector3D;

public class Engineering2D_to_Engineering3D implements ICoordinateTransform {
	
	@Override
	public boolean canTransform(CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		return src instanceof Engineering2D && dst instanceof Engineering3D;
	}

	@Override
	public double[] transform(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
		Engineering2D src = (Engineering2D)_src;
		Engineering3D dst = (Engineering3D)_dst;
		// cast has been checked before (see javadoc of ICoordinateTransform)
		Vector2D src_c = (Vector2D)src.getOrigin();
		Vector3D dst_c = (Vector3D)dst.getOrigin();
		//need to transform dst_c into this crs, otherwise we can not compare both origins
		src_c = (Vector2D)CRSUtils.transform(src_c, CRSUtils.WGS84_2D, dst);
		dst_c = (Vector3D)CRSUtils.transform(dst_c, CRSUtils.WGS84_3D, dst);
//		Vector2D d = dst_c.sub(src_c);
		// this is actually a 3x3 Matrix multiplication with a 2x1 vector 
//		double angle = src.getOrientationOffset().get(0) - dst.getOrientationOffset().get(0);
//		double sin = Math.sin(angle);
//		double cos = Math.cos(angle);
//		double x = in[0] * cos + in[1] * sin + d.getX();
//		double y = in[0] * -sin + in[1] * cos + d.getY();
		return null; //new double[]{0,0, 0};
	}

	@Override
	public double[] transformDirection(double[] ds, CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		double[] wgs = CRSUtils.transformDirection(ds[0], ds[1], ds.length>=3?ds[2]:0, src, CRSUtils.WGS84_3D);
        return CRSUtils.transformDirection(wgs[0], wgs[1], wgs.length>=3?wgs[2]:00, CRSUtils.WGS84_3D, dst);
	}
	
	
	
}
