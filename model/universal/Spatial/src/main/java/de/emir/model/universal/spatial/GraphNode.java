package de.emir.model.universal.spatial;

import java.util.List;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.GraphEdge;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass	
public interface GraphNode extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "coordinate", associationType = AssociationType.COMPOSITE)
	public void setCoordinate(Coordinate _coordinate);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "coordinate", associationType = AssociationType.COMPOSITE)
	public Coordinate getCoordinate();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "edges", associationType = AssociationType.SHARED)
	public List<GraphEdge> getEdges();
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
	/**
	
	 * Returns the CRS of the coordinate
	 * @generated 
	 */
	CoordinateReferenceSystem getCRS();
	/**
	 Change the coordinate reference system of this node, without changing the instance of the coordinate (e.g. manipulates the values of x,y,z) 
	 * @generated 
	 */
	void applyCRS(final CoordinateReferenceSystem crs);

	
}
