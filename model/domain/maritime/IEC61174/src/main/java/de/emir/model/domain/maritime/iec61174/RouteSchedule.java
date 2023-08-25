package de.emir.model.domain.maritime.iec61174;

import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass	
public interface RouteSchedule extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "estimatedTimeOfDepature", associationType = AssociationType.PROPERTY)
	public void setEstimatedTimeOfDepature(Time _estimatedTimeOfDepature);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "estimatedTimeOfDepature", associationType = AssociationType.PROPERTY)
	public Time getEstimatedTimeOfDepature();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "estimatedTimeOfArrival", associationType = AssociationType.PROPERTY)
	public void setEstimatedTimeOfArrival(Time _estimatedTimeOfArrival);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "estimatedTimeOfArrival", associationType = AssociationType.PROPERTY)
	public Time getEstimatedTimeOfArrival();
	
}
