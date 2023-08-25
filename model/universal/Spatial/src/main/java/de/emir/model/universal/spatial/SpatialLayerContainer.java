package de.emir.model.universal.spatial;
import de.emir.model.universal.spatial.SpatialLayer;
import java.util.List;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLInterface;

/**
 
 * The Abstract class SpatialLayerContainer can be used to provide 
 * SpatialLayers, which are dynamically loaded. 
 * This abstract class could be used by a user interface to display 
 * all relevant layers, even if they are not part of the persistent model. 
 * @generated 
 */
@UMLInterface(name = "SpatialLayerContainer")
public interface SpatialLayerContainer extends UObject 
{
	
	/**
	*	delegation interface
	*	@generated
	*/
	public interface ISpatialLayerContainerOperations {
		/**
		 *	@generated 
		 */
		List<SpatialLayer> provideSpatialLayer(SpatialLayerContainer self);
	}
					
	/**
	 *	@generated 
	 */
	List<SpatialLayer> provideSpatialLayer();
}
