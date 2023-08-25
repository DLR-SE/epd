package de.emir.model.universal.crs.internal.transform;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.crs.Engineering3D;
import de.emir.model.universal.crs.internal.ICoordinateTransform;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.Vector3D;

public class Engineering3D_to_Engineering3D implements ICoordinateTransform {
	
	@Override
	public boolean canTransform(CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		return src instanceof Engineering3D && dst instanceof Engineering3D;
	}

	@Override
	public double[] transform(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
		Engineering3D src = (Engineering3D)_src;
		Engineering3D dst = (Engineering3D)_dst;
		// cast has been checked before (see javadoc of ICoordinateTransform)
		Vector3D src_c = (Vector3D)src.getOrigin();
		Vector3D dst_c = (Vector3D)dst.getOrigin();
		//need to transform dst_c into this crs, otherwise we can not compare both origins
		src_c = (Vector3D)CRSUtils.transform(src_c, CRSUtils.WGS84_3D, dst);
		dst_c = (Vector3D)CRSUtils.transform(dst_c, CRSUtils.WGS84_3D, dst);
		Vector3D d = dst_c.sub(src_c);
		// FIXME: The angle handling is INCORRECT  and will only work if both have an orientation offset of 0
		double angle = src.getOrientationOffset().get(0) - dst.getOrientationOffset().get(0);
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		double x = in[0] * cos + in[1] * sin + d.getX();
		double y = in[0] * -sin + in[1] * cos + d.getY();
		
		return new double[]{x,y, in[2] + d.getZ()};
	}

	@Override
	public double[] transformDirection(double[] ds, CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		//
		return null;
	}
	
	
	
}
