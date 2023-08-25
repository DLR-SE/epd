package de.emir.model.application.sense.ports;

import de.emir.model.application.sense.SensorPort;
import de.emir.model.application.sense.measurements.VelocityMeasurement;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 Measurement representing the velocity / speed of the measured object, e.g. could be a tachometer, 
 * a wind sensor (wind speed), log (speed through water)
 * @generated 
 */
@UMLClass(parent = SensorPort.class)	
public interface VelocityPort extends SensorPort 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "measurement", associationType = AssociationType.COMPOSITE)
	public void setMeasurement(VelocityMeasurement _measurement);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "measurement", associationType = AssociationType.COMPOSITE)
	public VelocityMeasurement getMeasurement();
	
}
