package de.emir.model.universal.spatial.ops;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import java.util.List;

import de.emir.model.universal.spatial.EFeature;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.SpatialLayer;
import de.emir.model.universal.spatial.delegate.ISpatialLayerDelegationInterface;

/**
 *	@generated 
 */
public class SpatialLayerOperations  implements ISpatialLayerDelegationInterface{
	
	/**
	 * @inheritDoc
	 * @generated
	*/
	public List<EFeature> queryFeatures(SpatialLayer self, final Geometry geom, final boolean exactQuery)
	{
		//TODO: 
		// 
		//  * returns all features that intersect with the given query geometry (geom). 
		//  * if exactQuery is set to false, only the corresponding envelopes will be checked, otherwise a full intersection test is performed (slower)
		//  
		throw new UnsupportedOperationException("queryFeatures not yet implemented");
	}
}
