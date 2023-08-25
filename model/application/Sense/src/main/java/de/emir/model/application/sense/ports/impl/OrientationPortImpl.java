package de.emir.model.application.sense.ports.impl;

import java.util.List;

import de.emir.model.application.sense.ErrorModel;
import de.emir.model.application.sense.Measurement;
import de.emir.model.application.sense.impl.SensorPortImpl;
import de.emir.model.application.sense.measurements.OrientationMeasurement;
import de.emir.model.application.sense.ports.OrientationPort;
import de.emir.model.application.sense.ports.PortsPackage;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 Measurement that represents the orientation of the measured object, e.g. heading. 
 * Orientation measures may be created by compass sensors. 
 * @generated 
 */
@UMLImplementation(classifier = OrientationPort.class)
public class OrientationPortImpl extends SensorPortImpl implements OrientationPort  
{
	
	
	/**
	 *	@generated 
	 */
	private OrientationMeasurement mMeasurement = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public OrientationPortImpl(){
		super();
		//set the default values and assign them to this instance 
		setMeasurement(mMeasurement);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public OrientationPortImpl(final OrientationPort _copy) {
		super(_copy);
		mMeasurement = _copy.getMeasurement();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public OrientationPortImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, Geometry _observedRegion, List<ErrorModel> _errorModel, OrientationMeasurement _measurement) {
		super(_name,_allias,_remarks,_observers,_identifier,_observedRegion,_errorModel);
		mMeasurement = _measurement; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PortsPackage.Literals.OrientationPort;
	}

	/**
	 *	@generated 
	 */
	public void setMeasurement(OrientationMeasurement _measurement) {
		Notification<OrientationMeasurement> notification = basicSet(mMeasurement, _measurement, PortsPackage.Literals.OrientationPort_measurement);
		mMeasurement = _measurement;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public OrientationMeasurement getMeasurement() {
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
		if (measurement instanceof OrientationMeasurement == false)
			return false;
		setMeasurement((OrientationMeasurement)measurement);
		return true;
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "OrientationPortImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
