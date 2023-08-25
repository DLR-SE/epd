package de.emir.model.universal.crs.internal;

import java.util.HashMap;
import java.util.HashSet;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.internal.transform.Engineering2D_to_Engineering2D;
import de.emir.model.universal.crs.internal.transform.Engineering2D_to_Engineering3D;
import de.emir.model.universal.crs.internal.transform.Engineering2D_to_Polar2D;
import de.emir.model.universal.crs.internal.transform.Engineering2D_to_WGS842D;
import de.emir.model.universal.crs.internal.transform.Engineering2D_to_WGS843D;
import de.emir.model.universal.crs.internal.transform.Engineering3D_to_Engineering3D;
import de.emir.model.universal.crs.internal.transform.Engineering3D_to_WGS842D;
import de.emir.model.universal.crs.internal.transform.Engineering3D_to_WGS843D;
import de.emir.model.universal.crs.internal.transform.Polar2D_to_Engineering2D;
import de.emir.model.universal.crs.internal.transform.Polar2D_to_WGS842D;
import de.emir.model.universal.crs.internal.transform.WGS842D_to_Engineering2D;
import de.emir.model.universal.crs.internal.transform.WGS842D_to_Engineering3D;
import de.emir.model.universal.crs.internal.transform.WGS842D_to_Polar2D;

public class TransformFactory {
	
	
	static HashSet<ICoordinateTransform> sTransformations;
	static HashMap<Class<?>, HashMap<Class<?>, ICoordinateTransform>> sPreCheckedTransformations = new HashMap<>();

	/** 
	 * Register a new coordinate transform operation
	 * @param transform
	 */
	public static void registerTransformation(ICoordinateTransform transform) {
		if(transform != null) {
			if (sTransformations == null)
				init();
			sTransformations.add(transform);
		}
	}

	public static void removeTransformation(ICoordinateTransform transform) {
		if (sTransformations != null && transform != null)
			sTransformations.remove(transform);
	}

	/** 
	 * searchs a transform operation within the registered list
	 * if a transformation could be found, it is stored for future calls
	 * @param src
	 * @param dst
	 * @return a transformation that can transform from src into dst coordiante reference system or null if no transform could be found
	 */
	public static synchronized ICoordinateTransform getTransform(CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		if (sTransformations == null){
			init();
		}
		HashMap<Class<?>, ICoordinateTransform> tmp1 = sPreCheckedTransformations.get(src.getClass());
		if (tmp1 != null) {
			ICoordinateTransform transform = tmp1.get(dst.getClass());
			if(transform != null) 
				return transform;
		}
		for (ICoordinateTransform t : sTransformations) {
			if (t.canTransform(src, dst)) {
				if (tmp1 == null) {
					sPreCheckedTransformations.put(src.getClass(), tmp1 = new HashMap<>());
				}
				tmp1.put(dst.getClass(), t);
				return t;
			}
		}
		return null;
	}
	
	static void init() {
		sTransformations = new HashSet<ICoordinateTransform>();
		//2d to 2d transformations
		registerTransformation(new Engineering2D_to_Engineering2D());
		registerTransformation(new Engineering2D_to_Polar2D());
		registerTransformation(new Engineering2D_to_WGS842D());
		registerTransformation(new Polar2D_to_Engineering2D());
		registerTransformation(new Polar2D_to_WGS842D());
		registerTransformation(new WGS842D_to_Engineering2D());
		registerTransformation(new WGS842D_to_Polar2D());
		
		//3d to 3d transformation
		registerTransformation(new Engineering2D_to_WGS843D());
		
		//3d to 2d transformation
		registerTransformation(new Engineering3D_to_WGS842D());
		registerTransformation(new Engineering3D_to_WGS843D());
		registerTransformation(new Engineering3D_to_Engineering3D());
		//wgs843D is also handled by WGS842D_to_Engineering3D
		
		//2d to 3d transformation
		registerTransformation(new Engineering2D_to_WGS843D());
		registerTransformation(new Engineering2D_to_Engineering3D());
		registerTransformation(new WGS842D_to_Engineering3D());
	}
	
}
