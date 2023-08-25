package de.emir.model.universal.spatial.delegate;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Graph;
import de.emir.tuml.ucore.runtime.IDelegateInterface;

/**
*	delegation interface
*	@generated
*/
public interface IGraphDelegationInterface extends IDelegateInterface{

	/**
	
	 * Set the given CRS for all nodes, without changing the instance of the Coordinate (e.g. the values of each node's coordiante is changed)
	 * @generated 
	 */
	void applyCRS(Graph self, final CoordinateReferenceSystem _crs);

	/**
	 returns the CRS for this graph, that is the CRS of the first node 
	 * @generated 
	 */
	CoordinateReferenceSystem getCRS(Graph self);
}
