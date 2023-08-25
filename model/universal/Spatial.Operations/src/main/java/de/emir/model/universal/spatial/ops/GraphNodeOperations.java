package de.emir.model.universal.spatial.ops;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.GraphNode;
import de.emir.model.universal.spatial.delegate.IGraphNodeDelegationInterface;

/**
 *	@generated 
 */
public class GraphNodeOperations  implements IGraphNodeDelegationInterface{

	/**
	 * @inheritDoc
	 * @generated
	*/
	public CoordinateReferenceSystem getCRS(GraphNode self)
	{
		//TODO: 
		// 
		//  * Returns the CRS of the coordinate
		//  
		throw new UnsupportedOperationException("getCRS not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	*/
	public void applyCRS(GraphNode self, final CoordinateReferenceSystem crs)
	{
		//TODO: 
		//  Change the coordinate reference system of this node, without changing the instance of the coordinate (e.g. manipulates the values of x,y,z) 
		throw new UnsupportedOperationException("applyCRS not yet implemented");
	}
}
