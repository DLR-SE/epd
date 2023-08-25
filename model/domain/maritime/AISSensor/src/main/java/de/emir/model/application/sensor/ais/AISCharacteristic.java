package de.emir.model.application.sensor.ais;

import de.emir.model.application.sensor.ais.AISPort;
import de.emir.model.universal.physics.Characteristic;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 
 * Characteristic that indicates, that an Object has been captured / discovered through an AIS sensor
 * @generated 
 */
@UMLClass(parent = Characteristic.class)	
public interface AISCharacteristic extends Characteristic 
{
	/**
	 contains the time of the last AIS measurement 
	 * @generated 
	 */
	@UMLProperty(name = "lastMeasurement", associationType = AssociationType.COMPOSITE)
	public void setLastMeasurement(Time _lastMeasurement);
	/**
	 contains the time of the last AIS measurement 
	 * @generated 
	 */
	@UMLProperty(name = "lastMeasurement", associationType = AssociationType.COMPOSITE)
	public Time getLastMeasurement();
	/**
	 the device that has been used 
	 * @generated 
	 */
	@UMLProperty(name = "measurementDevice", associationType = AssociationType.SHARED)
	public void setMeasurementDevice(AISPort _measurementDevice);
	/**
	 the device that has been used 
	 * @generated 
	 */
	@UMLProperty(name = "measurementDevice", associationType = AssociationType.SHARED)
	public AISPort getMeasurementDevice();
	
}
