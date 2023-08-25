package de.emir.model.universal.spatial;

import java.util.List;
import de.emir.model.universal.spatial.GraphEdge;
import de.emir.model.universal.spatial.GraphNode;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass	
public interface Graph extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "nodes", associationType = AssociationType.PROPERTY)
	public List<GraphNode> getNodes();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "edges", associationType = AssociationType.PROPERTY)
	public List<GraphEdge> getEdges();
	/**
	 returns the CRS for this graph, that is the CRS of the first node 
	 * @generated 
	 */
	CoordinateReferenceSystem getCRS();
	/**
	
	 * Set the given CRS for all nodes, without changing the instance of the Coordinate (e.g. the values of each node's coordiante is changed)
	 * @generated 
	 */
	void applyCRS(final CoordinateReferenceSystem crs);

	
}
