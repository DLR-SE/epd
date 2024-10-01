package de.emir.model.universal.crs.internal.transform;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.Engineering3D;
import de.emir.model.universal.crs.WGS842D;
import de.emir.model.universal.crs.WGS843D;
import de.emir.model.universal.crs.internal.ICoordinateTransform;
import de.emir.model.universal.math.Vector;
import net.sf.geographiclib.GeodesicData;

public class WGS842D_to_WGS843D implements ICoordinateTransform {

    @Override
    public boolean canTransform(CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
        return src instanceof WGS842D && dst instanceof WGS843D;
    }

    @Override
    public double[] transform(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
        return new double[]{in[0], in[1], 0};
    }
    @Override
    public double[] transformDirection(double[] ds, CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
        return new double[]{ds[0], ds[1], 0};
    }
}
