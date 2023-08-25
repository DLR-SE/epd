package de.emir.model.application.sense.ports.impl;

import java.util.List;

import de.emir.model.application.sense.ErrorModel;
import de.emir.model.application.sense.Measurement;
import de.emir.model.application.sense.impl.SensorPortImpl;
import de.emir.model.application.sense.measurements.LocationMeasurement;
import de.emir.model.application.sense.ports.LocationPort;
import de.emir.model.application.sense.ports.PortsPackage;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 Measurement that holds the location of a not further specified object 
 * this could be for example an GPS, Lorence or Galileo measure. Inherited structs may extend some additional 
 * metadata like used satellites (if Satellite based sensor)
 * @generated 
 */
@UMLImplementation(classifier = LocationPort.class)
public class LocationPortImpl extends SensorPortImpl implements LocationPort  
{
	
	
	/**
	 *	@generated 
	 */
	private LocationMeasurement mMeasurement = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public LocationPortImpl(){
		super();
		//set the default values and assign them to this instance 
		setMeasurement(mMeasurement);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public LocationPortImpl(final LocationPort _copy) {
		super(_copy);
		mMeasurement = _copy.getMeasurement();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public LocationPortImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, Geometry _observedRegion, List<ErrorModel> _errorModel, LocationMeasurement _measurement) {
		super(_name,_allias,_remarks,_observers,_identifier,_observedRegion,_errorModel);
		mMeasurement = _measurement; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PortsPackage.Literals.LocationPort;
	}

	/**
	 *	@generated 
	 */
	public void setMeasurement(LocationMeasurement _measurement) {
		Notification<LocationMeasurement> notification = basicSet(mMeasurement, _measurement, PortsPackage.Literals.LocationPort_measurement);
		mMeasurement = _measurement;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public LocationMeasurement getMeasurement() {
		return mMeasurement;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean setCurrentMeasurement(final Measurement measurement)
	{
		if (measurement instanceof LocationMeasurement == false)
			return false;
		setMeasurement((LocationMeasurement)measurement);
		return true;
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "LocationPortImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
