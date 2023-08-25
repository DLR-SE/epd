package de.emir.model.application.sensor.ais.impl;

import de.emir.model.application.sense.SensorPort;
import de.emir.model.application.sense.measurements.impl.PositionMeasurementImpl;
import de.emir.model.application.sensor.ais.AISMeasurement;
import de.emir.model.application.sensor.ais.AisPackage;
import de.emir.model.universal.physics.LocatableObject;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = AISMeasurement.class)
public class AISMeasurementImpl extends PositionMeasurementImpl implements AISMeasurement  
{
	
	
	/**
	 *	@generated 
	 */
	private int mMessageID = 0;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public AISMeasurementImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public AISMeasurementImpl(final AISMeasurement _copy) {
		super(_copy);
		mMessageID = _copy.getMessageID();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public AISMeasurementImpl(Time _timestamp, SensorPort _sender, LocatableObject _object, int _messageID) {
		super(_timestamp,_sender,_object);
		mMessageID = _messageID;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return AisPackage.Literals.AISMeasurement;
	}

	/**
	 *	@generated 
	 */
	public void setMessageID(int _messageID) {
		if (needNotification(AisPackage.Literals.AISMeasurement_messageID)){
			int _oldValue = mMessageID;
			mMessageID = _messageID;
			notify(_oldValue, mMessageID, AisPackage.Literals.AISMeasurement_messageID, NotificationType.SET);
		}else{
			mMessageID = _messageID;
		}
	}

	/**
	 *	@generated 
	 */
	public int getMessageID() {
		return mMessageID;
	}

	/**
	* @generated not
	*/
	@Override
	public String toString() {
		return "AISMeasurementImpl{" +
		" messageID = " + getMessageID() + " Object: " + getObject() + 
		"}";
	}
}
