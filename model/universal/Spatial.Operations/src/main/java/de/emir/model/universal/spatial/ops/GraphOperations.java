package de.emir.model.universal.spatial.ops;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Graph;
import de.emir.model.universal.spatial.delegate.IGraphDelegationInterface;

/**
 *	@generated 
 */
public class GraphOperations  implements IGraphDelegationInterface{

	/**
	 * @inheritDoc
	 * @generated
	*/
	public void applyCRS(Graph self, final CoordinateReferenceSystem crs)
	{
		//TODO: 
		// 
		//  * Set the given CRS for all nodes, without changing the instance of the Coordinate (e.g. the values of each node's coordiante is changed)
		//  
		throw new UnsupportedOperationException("applyCRS not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	*/
	public CoordinateReferenceSystem getCRS(Graph self)
	{
		//TODO: 
		//  returns the CRS for this graph, that is the CRS of the first node 
		throw new UnsupportedOperationException("getCRS not yet implemented");
	}
}
