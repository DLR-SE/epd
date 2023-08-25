package de.emir.model.application.vehicle;

import de.emir.model.application.vehicle.TrackElement;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import java.util.List;

/**
 *	@generated 
 */
@UMLClass	
public interface Track extends UObject 
{
	/**
	 ordered list of elements  
	 * @generated 
	 */
	@UMLProperty(name = "elements", associationType = AssociationType.PROPERTY)
	public List<TrackElement> getElements();

	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 returns the time of the first track point 
	 * @generated 
	 */
	Time first();
	
	/**
	 returns the time of the last track point 
	 * @generated 
	 */
	Time last();
	
	/**
	
	 * returns a new track point for the given time. If there is no measurement available for this time point
	 * the method will (linear) interpolate / may extrapolate the values
	 * @return null, if elements is empty
	 * @generated 
	 */
	TrackElement getPositionAt(final Time p);
	
}
