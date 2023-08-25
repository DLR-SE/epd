package de.emir.model.universal.spatial;

import de.emir.model.universal.spatial.GraphNode;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass	
public interface GraphEdge extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "nodeA", associationType = AssociationType.SHARED)
	public void setNodeA(GraphNode _nodeA);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "nodeA", associationType = AssociationType.SHARED)
	public GraphNode getNodeA();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "nodeB", associationType = AssociationType.SHARED)
	public void setNodeB(GraphNode _nodeB);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "nodeB", associationType = AssociationType.SHARED)
	public GraphNode getNodeB();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "id", associationType = AssociationType.PROPERTY)
	public void setId(long _id);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "id", associationType = AssociationType.PROPERTY)
	public long getId();

	
}
