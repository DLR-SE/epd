package de.emir.model.application.sense.ports.impl;

import java.util.List;

import de.emir.model.application.sense.ErrorModel;
import de.emir.model.application.sense.Measurement;
import de.emir.model.application.sense.impl.SensorPortImpl;
import de.emir.model.application.sense.measurements.RotationMeasurement;
import de.emir.model.application.sense.ports.PortsPackage;
import de.emir.model.application.sense.ports.RotationPort;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**

 * Measurement that represents the change of the orientation over time, e.g. the rotation. 
 * This could be created by a gyroscope
 * @generated 
 */
@UMLImplementation(classifier = RotationPort.class)
public class RotationPortImpl extends SensorPortImpl implements RotationPort  
{
	
	
	/**
	 *	@generated 
	 */
	private RotationMeasurement mMeasurement = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public RotationPortImpl(){
		super();
		//set the default values and assign them to this instance 
		setMeasurement(mMeasurement);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public RotationPortImpl(final RotationPort _copy) {
		super(_copy);
		mMeasurement = _copy.getMeasurement();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public RotationPortImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, Geometry _observedRegion, List<ErrorModel> _errorModel, RotationMeasurement _measurement) {
		super(_name,_allias,_remarks,_observers,_identifier,_observedRegion,_errorModel);
		mMeasurement = _measurement; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PortsPackage.Literals.RotationPort;
	}

	/**
	 *	@generated 
	 */
	public void setMeasurement(RotationMeasurement _measurement) {
		Notification<RotationMeasurement> notification = basicSet(mMeasurement, _measurement, PortsPackage.Literals.RotationPort_measurement);
		mMeasurement = _measurement;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public RotationMeasurement getMeasurement() {
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
		if (measurement instanceof RotationMeasurement == false)
			return false;
		setMeasurement((RotationMeasurement)measurement);
		return true;
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "RotationPortImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
