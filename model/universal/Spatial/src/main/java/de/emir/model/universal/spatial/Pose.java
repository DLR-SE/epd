package de.emir.model.universal.spatial;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.Orientation;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "Pose")	
public interface Pose extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "coordinate", associationType = AssociationType.COMPOSITE, lowerBound = 1)
	public void setCoordinate(Coordinate _coordinate);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "coordinate", associationType = AssociationType.COMPOSITE, lowerBound = 1)
	public Coordinate getCoordinate();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "orientation", associationType = AssociationType.COMPOSITE)
	public void setOrientation(Orientation _orientation);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "orientation", associationType = AssociationType.COMPOSITE)
	public Orientation getOrientation();
	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	String readableString();
	
	/**
	 *	@generated 
	 */
	Pose copy();
	
	/**
	 *	@generated 
	 */
	void set(final Coordinate coord, final Orientation ori);
	
}
