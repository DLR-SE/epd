package de.emir.model.application.sensor.ais.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.application.sensor.ais.AISCharacteristic;
import de.emir.model.application.sensor.ais.AISPort;
import de.emir.model.application.sensor.ais.AisPackage;
import de.emir.model.universal.physics.impl.CharacteristicImpl;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;


/**
 
 * Characteristic that indicates, that an Object has been captured / discovered through an AIS sensor
 * @generated 
 */
@UMLImplementation(classifier = AISCharacteristic.class)
public class AISCharacteristicImpl extends CharacteristicImpl implements AISCharacteristic  
{
	
	
	/**
	 contains the time of the last AIS measurement 
	 * @generated 
	 */
	private Time mLastMeasurement = null;
	/**
	 the device that has been used 
	 * @generated 
	 */
	private AISPort mMeasurementDevice = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public AISCharacteristicImpl(){
		super();
		//set the default values and assign them to this instance 
		setLastMeasurement(mLastMeasurement);
		setMeasurementDevice(mMeasurementDevice);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public AISCharacteristicImpl(final AISCharacteristic _copy) {
		super(_copy);
		mLastMeasurement = _copy.getLastMeasurement();
		mMeasurementDevice = _copy.getMeasurementDevice();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public AISCharacteristicImpl(Time _lastMeasurement, AISPort _measurementDevice) {
		super();
		mLastMeasurement = _lastMeasurement; 
		mMeasurementDevice = _measurementDevice; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return AisPackage.Literals.AISCharacteristic;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 contains the time of the last AIS measurement 
	 * @generated 
	 */
	public void setLastMeasurement(Time _lastMeasurement) {
		Notification<Time> notification = basicSet(mLastMeasurement, _lastMeasurement, AisPackage.Literals.AISCharacteristic_lastMeasurement);
		mLastMeasurement = _lastMeasurement;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 contains the time of the last AIS measurement 
	 * @generated 
	 */
	public Time getLastMeasurement() {
		return mLastMeasurement;
	}
	/**
	 the device that has been used 
	 * @generated 
	 */
	public void setMeasurementDevice(AISPort _measurementDevice) {
		Notification<AISPort> notification = basicSet(mMeasurementDevice, _measurementDevice, AisPackage.Literals.AISCharacteristic_measurementDevice);
		mMeasurementDevice = _measurementDevice;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 the device that has been used 
	 * @generated 
	 */
	public AISPort getMeasurementDevice() {
		return mMeasurementDevice;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "AISCharacteristicImpl{" +
		"}";
	}
}
