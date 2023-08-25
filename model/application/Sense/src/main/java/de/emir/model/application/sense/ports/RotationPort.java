package de.emir.model.application.sense.ports;

import de.emir.model.application.sense.SensorPort;
import de.emir.model.application.sense.measurements.RotationMeasurement;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * Measurement that represents the change of the orientation over time, e.g. the rotation. 
 * This could be created by a gyroscope
 * @generated 
 */
@UMLClass(parent = SensorPort.class)	
public interface RotationPort extends SensorPort 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "measurement", associationType = AssociationType.PROPERTY)
	public void setMeasurement(RotationMeasurement _measurement);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "measurement", associationType = AssociationType.PROPERTY)
	public RotationMeasurement getMeasurement();

	
}
