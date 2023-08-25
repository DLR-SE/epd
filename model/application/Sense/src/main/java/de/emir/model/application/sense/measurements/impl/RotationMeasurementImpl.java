package de.emir.model.application.sense.measurements.impl;

import de.emir.model.application.sense.SensorPort;
import de.emir.model.application.sense.impl.MeasurementImpl;
import de.emir.model.application.sense.measurements.MeasurementsPackage;
import de.emir.model.application.sense.measurements.RotationMeasurement;
import de.emir.model.universal.units.AngularVelocity;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.NotificationType;


/**

 * Measurement that represents the change of the orientation over time, e.g. the rotation. 
 * This could be created by a gyroscope
 * @generated 
 */
@UMLImplementation(classifier = RotationMeasurement.class)
public class RotationMeasurementImpl extends MeasurementImpl implements RotationMeasurement  
{
	
	
	/**
	 *	@generated 
	 */
	private AngularVelocity mVelocity = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public RotationMeasurementImpl(){
		super();
		//set the default values and assign them to this instance 
		setVelocity(mVelocity);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public RotationMeasurementImpl(final RotationMeasurement _copy) {
		super(_copy);
		mVelocity = _copy.getVelocity();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public RotationMeasurementImpl(Time _timestamp, SensorPort _sender, AngularVelocity _velocity) {
		super(_timestamp,_sender);
		mVelocity = _velocity; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return MeasurementsPackage.Literals.RotationMeasurement;
	}

	/**
	 *	@generated 
	 */
	public void setVelocity(AngularVelocity _velocity) {
		Notification<AngularVelocity> notification = basicSet(mVelocity, _velocity, MeasurementsPackage.Literals.RotationMeasurement_velocity);
		mVelocity = _velocity;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public AngularVelocity getVelocity() {
		return mVelocity;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "RotationMeasurementImpl{" +
		"}";
	}
}
