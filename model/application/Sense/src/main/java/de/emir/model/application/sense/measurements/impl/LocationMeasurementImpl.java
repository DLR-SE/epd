package de.emir.model.application.sense.measurements.impl;

import de.emir.model.application.sense.SensorPort;
import de.emir.model.application.sense.impl.MeasurementImpl;
import de.emir.model.application.sense.measurements.LocationMeasurement;
import de.emir.model.application.sense.measurements.MeasurementsPackage;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.NotificationType;


/**
 Measurement that holds the location of a not further specified object 
 * this could be for example an GPS, Lorence or Galileo measure. Inherited structs may extend some additional 
 * metadata like used satellites (if Satellite based sensor)
 * @generated 
 */
@UMLImplementation(classifier = LocationMeasurement.class)
public class LocationMeasurementImpl extends MeasurementImpl implements LocationMeasurement  
{
	
	
	/**
	 *	@generated 
	 */
	private Coordinate mLocation = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public LocationMeasurementImpl(){
		super();
		//set the default values and assign them to this instance 
		setLocation(mLocation);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public LocationMeasurementImpl(final LocationMeasurement _copy) {
		super(_copy);
		mLocation = _copy.getLocation();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public LocationMeasurementImpl(Time _timestamp, SensorPort _sender, Coordinate _location) {
		super(_timestamp,_sender);
		mLocation = _location; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return MeasurementsPackage.Literals.LocationMeasurement;
	}

	/**
	 *	@generated 
	 */
	public void setLocation(Coordinate _location) {
		Notification<Coordinate> notification = basicSet(mLocation, _location, MeasurementsPackage.Literals.LocationMeasurement_location);
		mLocation = _location;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Coordinate getLocation() {
		return mLocation;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "LocationMeasurementImpl{" +
		"}";
	}
}
