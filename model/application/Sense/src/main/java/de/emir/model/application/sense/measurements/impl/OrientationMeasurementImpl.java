package de.emir.model.application.sense.measurements.impl;

import de.emir.model.application.sense.SensorPort;
import de.emir.model.application.sense.impl.MeasurementImpl;
import de.emir.model.application.sense.measurements.MeasurementsPackage;
import de.emir.model.application.sense.measurements.OrientationMeasurement;
import de.emir.model.universal.units.Orientation;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.NotificationType;


/**
 Measurement that represents the orientation of the measured object, e.g. heading. 
 * Orientation measures may be created by compass sensors. 
 * @generated 
 */
@UMLImplementation(classifier = OrientationMeasurement.class)
public class OrientationMeasurementImpl extends MeasurementImpl implements OrientationMeasurement  
{
	
	
	/**
	 *	@generated 
	 */
	private Orientation mOrientation = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public OrientationMeasurementImpl(){
		super();
		//set the default values and assign them to this instance 
		setOrientation(mOrientation);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public OrientationMeasurementImpl(final OrientationMeasurement _copy) {
		super(_copy);
		mOrientation = _copy.getOrientation();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public OrientationMeasurementImpl(Time _timestamp, SensorPort _sender, Orientation _orientation) {
		super(_timestamp,_sender);
		mOrientation = _orientation; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return MeasurementsPackage.Literals.OrientationMeasurement;
	}

	/**
	 *	@generated 
	 */
	public void setOrientation(Orientation _orientation) {
		Notification<Orientation> notification = basicSet(mOrientation, _orientation, MeasurementsPackage.Literals.OrientationMeasurement_orientation);
		mOrientation = _orientation;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Orientation getOrientation() {
		return mOrientation;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "OrientationMeasurementImpl{" +
		"}";
	}
}
