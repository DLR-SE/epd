package de.emir.model.universal.spatial.delegate;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.GraphNode;
import de.emir.tuml.ucore.runtime.IDelegateInterface;

/**
*	delegation interface
*	@generated
*/
public interface IGraphNodeDelegationInterface extends IDelegateInterface{

	/**
	
	 * Returns the CRS of the coordinate
	 * @generated 
	 */
	CoordinateReferenceSystem getCRS(GraphNode self);

	/**
	 Change the coordinate reference system of this node, without changing the instance of the coordinate (e.g. manipulates the values of x,y,z) 
	 * @generated 
	 */
	void applyCRS(GraphNode self, final CoordinateReferenceSystem _crs);
}
