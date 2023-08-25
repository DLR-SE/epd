package de.emir.model.universal.crs;

import de.emir.model.universal.core.IdentifiedObject;
import de.emir.model.universal.crs.CoordinateSystemAxis;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
The set of coordinate system axes that spans a given coordinate space. A coordinate system (CS)
 * is derived from a set of (mathematical) rules for specifying how coordinates in a given space
 * are to be assigned to points. The coordinate values in a coordinate tuple shall be recorded in
 * the order in which the coordinate system axes associations are recorded, whenever those
 * coordinates use a coordinate reference system that uses this coordinate system.
 * [GeoAPI]
 * @generated 
 */
@UMLClass(name = "CoordinateSystem", isAbstract = true, parent = IdentifiedObject.class)	
public interface CoordinateSystem extends IdentifiedObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "axes", associationType = AssociationType.COMPOSITE, lowerBound = 1)
	public void setAxes(CoordinateSystemAxis _axes);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "axes", associationType = AssociationType.COMPOSITE, lowerBound = 1)
	public CoordinateSystemAxis getAxes();
	
}
