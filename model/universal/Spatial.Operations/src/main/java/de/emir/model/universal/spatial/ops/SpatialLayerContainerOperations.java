package de.emir.model.universal.spatial.ops;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import java.util.List;

import de.emir.model.universal.spatial.SpatialLayer;
import de.emir.model.universal.spatial.SpatialLayerContainer;
import de.emir.model.universal.spatial.delegate.ISpatialLayerContainerDelegationInterface;

/**
 
 * The Abstract class SpatialLayerContainer can be used to provide 
 * SpatialLayers, which are dynamically loaded. 
 * This abstract class could be used by a user interface to display 
 * all relevant layers, even if they are not part of the persistent model. 
 * @generated 
 */
public class SpatialLayerContainerOperations  implements ISpatialLayerContainerDelegationInterface{
	
	/**
	 * @inheritDoc
	 * @generated
	*/
	public List<SpatialLayer> provideSpatialLayer(SpatialLayerContainer self)
	{
		//TODO: 
		throw new UnsupportedOperationException("provideSpatialLayer not yet implemented");
	}
}
