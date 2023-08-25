package de.emir.model.universal.spatial;

import java.util.List;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass	
public interface RoutingGraph extends UObject 
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

	
}
