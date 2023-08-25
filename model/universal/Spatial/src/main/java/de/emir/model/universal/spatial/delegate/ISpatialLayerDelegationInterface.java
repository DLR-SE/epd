package de.emir.model.universal.spatial.delegate;

import java.util.List;

import de.emir.model.universal.spatial.EFeature;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.SpatialLayer;
import de.emir.tuml.ucore.runtime.IDelegateInterface;

/**
*	delegation interface
*	@generated
*/
public interface ISpatialLayerDelegationInterface extends IDelegateInterface{
	/**
	
	 * returns all features that intersect with the given query geometry (geom). 
	 * if exactQuery is set to false, only the corresponding envelopes will be checked, otherwise a full intersection test is performed (slower)
	 * @generated 
	 */
	List<EFeature> queryFeatures(SpatialLayer self, final Geometry _geom, final boolean _exactQuery);
}
