package de.emir.model.universal.crs.internal.transform;

import de.emir.model.universal.crs.CRSUtils;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.crs.WGS843D;
import de.emir.model.universal.math.Vector2D;

public class WGS843D_to_Engineering2D extends WGS842D_to_Engineering2D {

	@Override
    public boolean canTransform(CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
        return src instanceof WGS843D && dst instanceof Engineering2D;
    }

	@Override
	public double[] transform(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
		CoordinateReferenceSystem src = CRSUtils.WGS84_2D; // Since we are converting to a 2D CRS we can cheat here.
		return super.transform(in, src, _dst);
	}

	@Override
	public double[] transformDirection(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
		WGS843D src = (WGS843D) _src;
		Engineering2D dst = (Engineering2D) _dst;
		
		double mag = Math.sqrt(in[0] * in[0] + in[1] * in[1]);
		double bearing = src.directionToBearing(in[0]/mag, in[1]/mag, in[2]).get(0); //TODO: check if mag == 0
		double offset = dst.getOrientationOffset().get(0); //consider the orientation of the destination (source has no orientation)
	
		Vector2D dir = (Vector2D) dst.bearingToDirection(bearing - offset, 0); //Double.NaN);
		return new double[]{dir.getX() * mag, dir.getY() * mag};
	}

}
