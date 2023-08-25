package de.emir.model.application.sense.ports;

import de.emir.model.application.sense.SensorPort;
import de.emir.model.application.sense.measurements.ObjectMeasurement;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = SensorPort.class)	
public interface ObjectPort extends SensorPort 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "measurement", associationType = AssociationType.PROPERTY)
	public void setMeasurement(ObjectMeasurement _measurement);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "measurement", associationType = AssociationType.PROPERTY)
	public ObjectMeasurement getMeasurement();

	
}
