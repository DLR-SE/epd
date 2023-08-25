package de.emir.model.application.sense.ports;

import de.emir.model.application.sense.SensorPort;
import de.emir.model.application.sense.measurements.TemperatureMeasurement;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 Measurement containing the temperature of something 
 * @generated 
 */
@UMLClass(parent = SensorPort.class)	
public interface TemperaturePort extends SensorPort 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "measurement", associationType = AssociationType.PROPERTY)
	public void setMeasurement(TemperatureMeasurement _measurement);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "measurement", associationType = AssociationType.PROPERTY)
	public TemperatureMeasurement getMeasurement();

	
}
