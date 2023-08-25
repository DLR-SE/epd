package de.emir.model.application.sense.measurements.impl;

import de.emir.model.application.sense.SensorPort;
import de.emir.model.application.sense.impl.MeasurementImpl;
import de.emir.model.application.sense.measurements.DistanceMeasurement;
import de.emir.model.application.sense.measurements.MeasurementsPackage;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.NotificationType;


/**
 *	@generated 
 */
@UMLImplementation(classifier = DistanceMeasurement.class)
public class DistanceMeasurementImpl extends MeasurementImpl implements DistanceMeasurement  
{
	
	
	/**
	 *	@generated 
	 */
	private Distance mDistance = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public DistanceMeasurementImpl(){
		super();
		//set the default values and assign them to this instance 
		setDistance(mDistance);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public DistanceMeasurementImpl(final DistanceMeasurement _copy) {
		super(_copy);
		mDistance = _copy.getDistance();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public DistanceMeasurementImpl(Time _timestamp, SensorPort _sender, Distance _distance) {
		super(_timestamp,_sender);
		mDistance = _distance; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return MeasurementsPackage.Literals.DistanceMeasurement;
	}

	/**
	 *	@generated 
	 */
	public void setDistance(Distance _distance) {
		Notification<Distance> notification = basicSet(mDistance, _distance, MeasurementsPackage.Literals.DistanceMeasurement_distance);
		mDistance = _distance;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Distance getDistance() {
		return mDistance;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "DistanceMeasurementImpl{" +
		"}";
	}
}
