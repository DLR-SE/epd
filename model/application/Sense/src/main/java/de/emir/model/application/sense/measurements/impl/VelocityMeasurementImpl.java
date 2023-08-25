package de.emir.model.application.sense.measurements.impl;

import de.emir.model.application.sense.SensorPort;
import de.emir.model.application.sense.impl.MeasurementImpl;
import de.emir.model.application.sense.measurements.MeasurementsPackage;
import de.emir.model.application.sense.measurements.VelocityMeasurement;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.Velocity;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.NotificationType;


/**
 Measurement representing the velocity / speed of the measured object, e.g. could be a tachometer, 
 * a wind sensor (wind speed), log (speed through water)
 * @generated 
 */
@UMLImplementation(classifier = VelocityMeasurement.class)
public class VelocityMeasurementImpl extends MeasurementImpl implements VelocityMeasurement  
{
	
	
	/**
	 *	@generated 
	 */
	private Velocity mVelocity = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public VelocityMeasurementImpl(){
		super();
		//set the default values and assign them to this instance 
		setVelocity(mVelocity);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public VelocityMeasurementImpl(final VelocityMeasurement _copy) {
		super(_copy);
		mVelocity = _copy.getVelocity();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public VelocityMeasurementImpl(Time _timestamp, SensorPort _sender, Velocity _velocity) {
		super(_timestamp,_sender);
		mVelocity = _velocity; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return MeasurementsPackage.Literals.VelocityMeasurement;
	}

	/**
	 *	@generated 
	 */
	public void setVelocity(Velocity _velocity) {
		Notification<Velocity> notification = basicSet(mVelocity, _velocity, MeasurementsPackage.Literals.VelocityMeasurement_velocity);
		mVelocity = _velocity;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Velocity getVelocity() {
		return mVelocity;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "VelocityMeasurementImpl{" +
		"}";
	}
}
