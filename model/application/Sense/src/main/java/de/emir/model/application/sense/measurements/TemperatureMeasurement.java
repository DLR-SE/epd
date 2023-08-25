package de.emir.model.application.sense.measurements;

import de.emir.model.application.sense.Measurement;
import de.emir.model.universal.units.Temperature;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 Measurement containing the temperature of something 
 * @generated 
 */
@UMLClass(parent = Measurement.class)	
public interface TemperatureMeasurement extends Measurement 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "temperature", associationType = AssociationType.PROPERTY)
	public void setTemperature(Temperature _temperature);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "temperature", associationType = AssociationType.PROPERTY)
	public Temperature getTemperature();

	
}
