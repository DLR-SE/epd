package de.emir.model.universal.spatial.crs;

import java.util.HashMap;


import org.geotools.api.geometry.Position;
import org.geotools.api.referencing.FactoryException;
import org.geotools.api.referencing.operation.MathTransform;
import org.geotools.api.referencing.operation.TransformException;
import org.geotools.geometry.Position2D;
import org.geotools.geometry.Position3D;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.NativeCRS;
import de.emir.model.universal.crs.internal.ICoordinateTransform;
import de.emir.model.universal.crs.internal.TransformFactory;
import de.emir.model.universal.crs.util.CRSUtils;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;

public class NativeCRSTransform implements ICoordinateTransform {

	
	public static void init(){
		TransformFactory.registerTransformation(new NativeCRSTransform());
	}
	
	private HashMap<String, org.geotools.api.referencing.crs.CoordinateReferenceSystem> mNativeCRSMap = new HashMap<>();//[WKT, CRS]

	static DefaultGeographicCRS _native_emir_wgs = null;
	static DefaultGeographicCRS _native_wgs = null;
	
	static DefaultGeographicCRS getDefaultEMIRWGS842D(){
		if (_native_emir_wgs == null)
			_native_emir_wgs = createDefaultEMIRWGS842D();
		return _native_emir_wgs;
	}
	static DefaultGeographicCRS getNativeWGS(){
		if (_native_wgs == null)
			_native_emir_wgs = DefaultGeographicCRS.WGS84;
		return _native_emir_wgs;
	}
	
	private static DefaultGeographicCRS createDefaultEMIRWGS842D() {
		try {
			return (DefaultGeographicCRS) CRS.decode("urn:ogc:def:crs:EPSG:6.6:4326");
		} catch (FactoryException e) {
			e.printStackTrace();
			return null;
		}
	}
		
	
	private org.geotools.api.referencing.crs.CoordinateReferenceSystem getCRSFromWKT(String wkt){
		if (mNativeCRSMap.containsKey(wkt))
			return mNativeCRSMap.get(wkt);
		try {
			org.geotools.api.referencing.crs.CoordinateReferenceSystem result = CRS.parseWKT(wkt);
			if (result != null){
				mNativeCRSMap.put(wkt, result);
				return result;
			}
		} catch (FactoryException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean canTransform(CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		if (src instanceof NativeCRS || dst instanceof NativeCRS)
			return true;
		return false;
	}

	@Override
	public double[] transform(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst) {
		if (_src instanceof NativeCRS){
			org.geotools.api.referencing.crs.CoordinateReferenceSystem nativeCRS = getCRSFromWKT(((NativeCRS)_src).getWkt());
			//now transform into the WGS84 as used in emir (lat/lon)
			if (nativeCRS == null)
				return null;
			try {
				MathTransform transform = CRS.findMathTransform(nativeCRS, getDefaultEMIRWGS842D());
				if (transform != null){
					Position dp_src = in.length == 2 ? new Position2D(in[0], in[1]) : new Position3D(in[0], in[1], in[2]);
					Position dp = transform.transform(dp_src, null);
					if (dp != null)
						return CRSUtils.transform(dp.getCoordinate(), CRSUtils.WGS84_2D, _dst);
				}
			} catch (FactoryException e) {
				e.printStackTrace();
			} catch (TransformException e) {
                throw new RuntimeException(e);
            }
            return null;
		}
		if (_dst instanceof NativeCRS){
			double[] eMIRWGS = CRSUtils.transform(in, _src, CRSUtils.WGS84_2D);
			if (eMIRWGS != null){
				org.geotools.api.referencing.crs.CoordinateReferenceSystem nativeCRS = getCRSFromWKT(((NativeCRS)_dst).getWkt());
				if (nativeCRS == null)
					return null;
				try {
					MathTransform transform = CRS.findMathTransform(getDefaultEMIRWGS842D(), nativeCRS);
					if (transform != null){
						Position dp_src = eMIRWGS.length == 2 ? new Position2D(eMIRWGS[0], eMIRWGS[1]) : new Position3D(eMIRWGS[0], eMIRWGS[1], eMIRWGS[2]);
						Position dp = transform.transform(dp_src, null);
						if (dp != null)
							return dp.getCoordinate();
					}
				} catch (FactoryException | TransformException e) {
					e.printStackTrace();
				}
                return null;
			}
		}
		return null;
	}
	@Override
	public double[] transformDirection(double[] ds, CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		// TODO
		return null;
	}
}
