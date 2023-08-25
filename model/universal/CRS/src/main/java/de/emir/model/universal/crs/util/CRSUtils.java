package de.emir.model.universal.crs.util;


import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.crs.Engineering3D;
import de.emir.model.universal.crs.WGS842D;
import de.emir.model.universal.crs.WGS843D;
import de.emir.model.universal.crs.WGS84CRS;
import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.crs.impl.Engineering3DImpl;
import de.emir.model.universal.crs.impl.WGS842DImpl;
import de.emir.model.universal.crs.impl.WGS843DImpl;
import de.emir.model.universal.crs.internal.ICoordinateTransform;
import de.emir.model.universal.crs.internal.TransformFactory;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.Vector3D;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.math.impl.Vector3DImpl;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class CRSUtils {
	
	public static WGS842D	WGS84_2D = _createDefaultWGS842D();
	
	static WGS842D _createDefaultWGS842D() {
		WGS842DImpl wgs = new WGS842DImpl();
		wgs.getName().setCode("WGS84 2D");
		return wgs;
	}
	
	public static CoordinateReferenceSystem	WGS84_3D = _createDefaultWGS843D();
	
	static CoordinateReferenceSystem _createDefaultWGS843D() {
		WGS843D wgs = new WGS843DImpl();
		wgs.setName("WGS84 3D");
		return wgs;
	}
	
	public static Engineering2D			ENGINEERING_2D = new Engineering2DImpl();
	public static Engineering3D			ENGINEERING_3D = new Engineering3DImpl();
	

	/**
	 * transforms the given coordinates into another coordinate reference system
	 * @param coords coordinates (2 or 3 dimensions) which are expressed using the src coordinate reference system
	 * @param src source coordinate reference system, that describes the coords
	 * @param dst target coordinate reference system
	 * @return the coordinates expressed in the dst coordinate reference system or null, if no transformation could be found
	 */
	public static Vector transform(Vector value, CoordinateReferenceSystem src, CoordinateReferenceSystem dst){
		if (value == null || src == null || dst == null)
			return null;
		if (value.dimensions() == 2){
			Vector2D v = (Vector2D)value;
			return new Vector2DImpl(transform(new double[]{v.getX(), v.getY()}, src, dst));
		}else if (value.dimensions() == 3){
			Vector3D v = (Vector3D)value;
			return new Vector3DImpl(transform(new double[]{v.getX(), v.getY(), v.getZ()}, src, dst));
		}
		return null;
	}
	
	/**
	 * Transforms and direction vector from one coordiante reference system into another
	 * thereby the transformation ignores the position offset of the CRS and just takes the orientation into account
	 * @param value direction vector to be transformed
	 * @param src coordinate reference system of the direction vector
	 * @param dst target coordinate reference system
	 * @return a transformed direction or null if no tranformation could be found
	 */
	public static Vector transformDirection(Vector value, CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		if (value == null || src == null || dst == null)
			return null;
		int d = Math.max(value.dimensions(), Math.min(src.dimension(), dst.dimension()));
		if (d == 2)
			return new Vector2DImpl(transformDirection(value.get(0), value.get(1), d == 2 ? Double.NaN : value.get(2), src, dst));
		else
			return new Vector3DImpl(transformDirection(value.get(0), value.get(1), d == 2 ? Double.NaN : value.get(2), src, dst));
	}
	
	
	/**
	 * Transforms and direction from one coordiante reference system into another
	 * thereby the transformation ignores the position offset of the CRS and just takes the orientation into account
	 * @param x component of the direction
	 * @param y component of the direction
	 * @param z component of the direction or Double.NaN if a 2D transformation should be done 
	 * @param src coordinate reference system of the direction vector
	 * @param dst target coordinate reference system
	 * @return an transformed direction as array double[ox,oy,(oz)] where oz is only given if z != NaN
	 */
	public static double[] transformDirection(double x, double y, double z, CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		if (src.equals(dst))
			return z == z ? new double[] {x,y,z} : new double[] {x,y};
		Class src_cl = src.getClass();
		Class dst_cl = dst.getClass();
		
		if (src_cl == dst_cl && (src instanceof WGS84CRS)){
			//we can do an additional check for WGS84 - if both have the same class, they won't be differ, since WGS84CRS has no attributes
			//TODO: Check for CoordinateSystem
			return z == z ? new double[] {x,y,z} : new double[] {x,y};
		}
		try{
			ICoordinateTransform transform = TransformFactory.getTransform(src, dst);
			if (transform != null){
				return transform.transformDirection(new double[] {x,y,z}, src, dst);
			}else{
				ULog.warn("Failed to find a coordinate transformation from " + src + " to " + dst);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static double[] transform(double x, double y, CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		return transform(new double[]{x,y}, src, dst);
	}
	public static double[] transform(double x, double y, double z, CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		return transform(new double[]{x, y, z}, src, dst);
	}
	/**
	 * transforms the given coordinates into another coordinate reference system
	 * @param coords coordinates (2 or 3 dimensions) which are expressed using the src coordinate reference system
	 * @param src source coordinate reference system, that describes the coords
	 * @param dst target coordinate reference system
	 * @return the coordinates expressed in the dst coordinate reference system or null, if no transformation could be found
	 */
	public static synchronized double[] transform(double[] coords, CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		if (src.equals(dst))
			return coords;
		
		Class src_cl = src.getClass();
		Class dst_cl = dst.getClass();
		
		if (src_cl == dst_cl && (src instanceof WGS84CRS)){
			//we can do an additional check for WGS84 - if both have the same class, they won't be differ, since WGS84CRS has no attributes
			//TODO: Check for CoordinateSystem
			return coords;
		}
			
		try{
			ICoordinateTransform transform = TransformFactory.getTransform(src, dst);
			if (transform != null){
				return transform.transform(coords, src, dst);
			}else{
				ULog.warn("Failed to find a coordinate transformation from " + src + " to " + dst);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	

	
	/**
	 * Converts a decimal degree coordinate representation into a Degree, Minute, Second representation
	 * e.g: 30.263888889� = 30� 15' 50"
	 * @param decimalDegree
	 * @return
	 */
	public static String toDegreeMinuteSecond(double decimalDegree){
		String str = "";
		int d = (int)decimalDegree;
		int m = (int)((decimalDegree - d) * 60);
		int s = (int)((decimalDegree - d - m/60.0) * 3600);
		return d + "° " + m + "' " + s + "''";
	}
	
	public static int getDegrees(double decimalDegree){
		return (int)decimalDegree;
	}
	public static double getDezimalMinute(double decimalDegree){
		return (decimalDegree - (int)decimalDegree) * 60.0;
	}
	public static int getMinute(double decimalDegree){
		return (int)(decimalDegree - getDegrees(decimalDegree)) * 60;
	}
	public static int getSecond(double decimalDegree){
		return (int)(decimalDegree - getDegrees(decimalDegree) - getMinute(decimalDegree)/60.0) * 3600;
	}
	
	
	public static String toDegreeDezimalMinute(double decimalDegree){
		String str = "";
		throw new UnsupportedOperationException();
//		return str; 
	}

	public static double toDezimalDegree(int degrees, double decimalMinute) {
		return degrees + decimalMinute / 60.0;
	}

	/**
	 * 
	 * @param origin
	 * @param distance_meter
	 * @param azimuth_radian
	 * @param crs CoordinateReferenceSystem of the source coordinates
	 * @return
	 */
	public static double[] getTarget(double[] origin, double distance_meter, double azimuth_radian, CoordinateReferenceSystem crs) {
		return getTarget(origin, distance_meter, azimuth_radian, Double.NaN, crs);
	}

	public static double[] getTarget(double[] origin, double distance_meter, double azimuth_radian, double polar_radian, CoordinateReferenceSystem crs) {		
		if (origin == null || origin.length < 2)
			return null;
		return crs.getTarget(origin, distance_meter, azimuth_radian, polar_radian);
	}

	
}