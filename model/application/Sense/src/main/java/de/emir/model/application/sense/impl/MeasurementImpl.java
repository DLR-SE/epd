package de.emir.model.application.sense.impl;

import de.emir.model.application.sense.Measurement;
import de.emir.model.application.sense.SensePackage;
import de.emir.model.application.sense.SensorPort;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Measurement.class)
abstract public class MeasurementImpl extends UObjectImpl implements Measurement  
{
	
	
	/**
	 Time, when the measurement was created 
	 * @generated 
	 */
	private Time mTimestamp = null;
	/**
	 *	@generated 
	 */
	private SensorPort mSender = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public MeasurementImpl(){
		super();
		//set the default values and assign them to this instance 
		setTimestamp(mTimestamp);
		setSender(mSender);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public MeasurementImpl(final Measurement _copy) {
		mTimestamp = _copy.getTimestamp();
		mSender = _copy.getSender();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public MeasurementImpl(Time _timestamp, SensorPort _sender) {
		mTimestamp = _timestamp; 
		mSender = _sender; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SensePackage.Literals.Measurement;
	}

	/**
	 Time, when the measurement was created 
	 * @generated 
	 */
	public void setTimestamp(Time _timestamp) {
		Notification<Time> notification = basicSet(mTimestamp, _timestamp, SensePackage.Literals.Measurement_timestamp);
		mTimestamp = _timestamp;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 Time, when the measurement was created 
	 * @generated 
	 */
	public Time getTimestamp() {
		return mTimestamp;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "MeasurementImpl{" +
		"}";
	}

	/**
	 *	@generated 
	 */
	public SensorPort getSender() {
		return mSender;
	}

	/**
	 *	@generated 
	 */
	public void setSender(SensorPort _sender) {
		Notification<SensorPort> notification = basicSet(mSender, _sender, SensePackage.Literals.Measurement_sender);
		mSender = _sender;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
}
