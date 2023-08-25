package de.emir.model.universal.spatial.delegate;

import java.util.List;

import de.emir.model.universal.spatial.SpatialLayer;
import de.emir.model.universal.spatial.SpatialLayerContainer;
import de.emir.tuml.ucore.runtime.IDelegateInterface;

/**
*	delegation interface
*	@generated
*/
public interface ISpatialLayerContainerDelegationInterface extends IDelegateInterface{
	/**
	 *	@generated 
	 */
	List<SpatialLayer> provideSpatialLayer(SpatialLayerContainer self);
}
