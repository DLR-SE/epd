package de.emir.model.application.sense.impl;

import java.util.List;

import de.emir.model.application.sense.Measurement;
import de.emir.model.application.sense.Observation;
import de.emir.model.application.sense.SensePackage;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;


/**

 * Contains the observed phenomenon / measurement.
 * the associated meta informations (like observedRegion, FeatureOfInterest, ...) are stored within the referenced port
 * @generated 
 */
@UMLImplementation(classifier = Observation.class)
public class ObservationImpl extends UObjectImpl implements Observation  
{
	
	
	/**
	 *	@generated 
	 */
	private Time mTimestamp = null;
	/**
	 *	@generated 
	 */
	private List<Measurement> mMeasurements = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public ObservationImpl(){
		super();
		//set the default values and assign them to this instance 
		setTimestamp(mTimestamp);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ObservationImpl(final Observation _copy) {
		mTimestamp = _copy.getTimestamp();
		mMeasurements = _copy.getMeasurements();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ObservationImpl(Time _timestamp, List<Measurement> _measurements) {
		mTimestamp = _timestamp; 
		mMeasurements = _measurements; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SensePackage.Literals.Observation;
	}

	/**
	 *	@generated 
	 */
	public void setTimestamp(Time _timestamp) {
		Notification<Time> notification = basicSet(mTimestamp, _timestamp, SensePackage.Literals.Observation_timestamp);
		mTimestamp = _timestamp;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Time getTimestamp() {
		return mTimestamp;
	}

	/**
	 *	@generated 
	 */
	public List<Measurement> getMeasurements() {
		if (mMeasurements == null) {
			mMeasurements = new UContainmentList<Measurement>(this, SensePackage.theInstance.getObservation_measurements()); 
		}
		return mMeasurements;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "ObservationImpl{" +
		"}";
	}
}
