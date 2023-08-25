package de.emir.model.universal.spatial;

import de.emir.model.universal.SpatialModel;
import de.emir.model.universal.spatial.crs.NativeCRSTransform;
import de.emir.model.universal.spatial.ops.CoordinateOperations;
import de.emir.model.universal.spatial.ops.EFeatureOperations;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.universal.spatial.ops.EnvelopeOperations;
import de.emir.model.universal.spatial.ops.CoordinateSequenceOperations;
import de.emir.model.universal.spatial.ops.GenericEFeatureOperations;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.spatial.ops.PoseOperations;
import de.emir.model.universal.spatial.ops.SpatialLayerContainerOperations;
import de.emir.model.universal.spatial.ops.GraphEdgeOperations;
import de.emir.model.universal.spatial.ops.GraphNodeOperations;
import de.emir.model.universal.spatial.ops.GraphOperations;
import de.emir.model.universal.spatial.ops.SpatialLayerOperations;
import de.emir.tuml.ucore.runtime.DelegateFactory;
import de.emir.tuml.ucore.runtime.DelegateFactory.DelegateProvider;
import de.emir.tuml.ucore.runtime.UCorePlugin;

/**
 *	@generated 
 */
public class SpatialDelegateProviders implements UCorePlugin
{
	/**
	 * @generated not ID(DelegateProvider_register)
	 */
	public static void register(){
		//initialize the data model
		SpatialModel.init();
		
		//register delegate classes in global factory
		DelegateFactory.registerProvider(SpatialPackage.Literals.Coordinate, new DelegateProvider(CoordinateOperations.class));
		DelegateFactory.registerProvider(SpatialPackage.Literals.Pose, new DelegateProvider(PoseOperations.class));
		DelegateFactory.registerProvider(SpatialPackage.Literals.Envelope, new DelegateProvider(EnvelopeOperations.class));
		DelegateFactory.registerProvider(SpatialPackage.Literals.Geometry, new DelegateProvider(GeometryOperations.class));
		DelegateFactory.registerProvider(SpatialPackage.Literals.EFeature, new DelegateProvider(EFeatureOperations.class));
		DelegateFactory.registerProvider(SpatialPackage.Literals.GenericEFeature, new DelegateProvider(GenericEFeatureOperations.class));
		DelegateFactory.registerProvider(SpatialPackage.Literals.SpatialLayer, new DelegateProvider(SpatialLayerOperations.class));
		DelegateFactory.registerProvider(SpatialPackage.Literals.SpatialLayerContainer, new DelegateProvider(SpatialLayerContainerOperations.class));
		
		NativeCRSTransform.init();
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
