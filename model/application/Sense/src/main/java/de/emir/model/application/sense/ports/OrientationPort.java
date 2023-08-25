package de.emir.model.application.sense.ports;

import de.emir.model.application.sense.SensorPort;
import de.emir.model.application.sense.measurements.OrientationMeasurement;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 Measurement that represents the orientation of the measured object, e.g. heading. 
 * Orientation measures may be created by compass sensors. 
 * @generated 
 */
@UMLClass(parent = SensorPort.class)	
public interface OrientationPort extends SensorPort 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "measurement", associationType = AssociationType.COMPOSITE)
	public void setMeasurement(OrientationMeasurement _measurement);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "measurement", associationType = AssociationType.COMPOSITE)
	public OrientationMeasurement getMeasurement();
	
}
