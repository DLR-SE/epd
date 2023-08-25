package de.emir.model.application.sense.measurements;

import de.emir.model.application.sense.Measurement;
import de.emir.model.universal.units.Distance;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = Measurement.class)	
public interface DistanceMeasurement extends Measurement 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "distance", associationType = AssociationType.PROPERTY)
	public void setDistance(Distance _distance);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "distance", associationType = AssociationType.PROPERTY)
	public Distance getDistance();

	
}
