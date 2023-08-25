package de.emir.model.application.sense.ports;

import de.emir.model.application.sense.SensorPort;
import de.emir.model.application.sense.measurements.LocationMeasurement;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 Measurement that holds the location of a not further specified object 
 * this could be for example an GPS, Lorence or Galileo measure. Inherited structs may extend some additional 
 * metadata like used satellites (if Satellite based sensor)
 * @generated 
 */
@UMLClass(parent = SensorPort.class)	
public interface LocationPort extends SensorPort 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "measurement", associationType = AssociationType.COMPOSITE)
	public void setMeasurement(LocationMeasurement _measurement);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "measurement", associationType = AssociationType.COMPOSITE)
	public LocationMeasurement getMeasurement();
	
}
