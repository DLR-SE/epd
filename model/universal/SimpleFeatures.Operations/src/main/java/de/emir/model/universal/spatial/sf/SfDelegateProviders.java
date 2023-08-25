package de.emir.model.universal.spatial.sf;

import de.emir.model.universal.spatial.SimpleFeatureModel;
import de.emir.model.universal.spatial.sf.ops.LineStringOperations;
import de.emir.model.universal.spatial.sf.ops.LinearRingOperations;
import de.emir.model.universal.spatial.sf.SfPackage;
import de.emir.model.universal.spatial.sf.ops.MultiLineStringOperations;
import de.emir.model.universal.spatial.sf.ops.MultiPolygonOperations;
import de.emir.model.universal.spatial.sf.ops.MultiGeometryOperations;
import de.emir.model.universal.spatial.sf.ops.PointOperations;
import de.emir.model.universal.spatial.sf.ops.PolygonOperations;
import de.emir.model.universal.spatial.sf.ops.WKTGeometryOperations;
import de.emir.tuml.ucore.runtime.DelegateFactory;
import de.emir.tuml.ucore.runtime.DelegateFactory.DelegateProvider;
import de.emir.tuml.ucore.runtime.UCorePlugin;

/**
 *	@generated 
 */
public class SfDelegateProviders implements UCorePlugin
{
	/**
	 * @generated ID(DelegateProvider_register)
	 */
	public static void register(){
		//initialize the data model
		SimpleFeatureModel.init();
		
		//register delegate classes in global factory
//		DelegateFactory.registerProvider(SfPackage.Literals.CoordinateSequence, new DelegateProvider(CoordinateSequenceOperations.class));
		DelegateFactory.registerProvider(SfPackage.Literals.Point, new DelegateProvider(PointOperations.class));
		DelegateFactory.registerProvider(SfPackage.Literals.LineString, new DelegateProvider(LineStringOperations.class));
		DelegateFactory.registerProvider(SfPackage.Literals.MultiLineString, new DelegateProvider(MultiLineStringOperations.class));
		DelegateFactory.registerProvider(SfPackage.Literals.LinearRing, new DelegateProvider(LinearRingOperations.class));
		DelegateFactory.registerProvider(SfPackage.Literals.Polygon, new DelegateProvider(PolygonOperations.class));
		DelegateFactory.registerProvider(SfPackage.Literals.MultiPolygon, new DelegateProvider(MultiPolygonOperations.class));
		DelegateFactory.registerProvider(SfPackage.Literals.WKTGeometry, new DelegateProvider(WKTGeometryOperations.class));
	}
	
	/**
	 * @generated ID(DelegateProvider_initializePlugin)
	 */
	@Override
	public void initializePlugin(){
		//Method is called by plugin loader
		register();		
	}
}
