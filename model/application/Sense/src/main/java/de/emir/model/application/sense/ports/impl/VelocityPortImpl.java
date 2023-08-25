package de.emir.model.application.sense.ports.impl;

import java.util.List;

import de.emir.model.application.sense.ErrorModel;
import de.emir.model.application.sense.Measurement;
import de.emir.model.application.sense.impl.SensorPortImpl;
import de.emir.model.application.sense.measurements.VelocityMeasurement;
import de.emir.model.application.sense.ports.PortsPackage;
import de.emir.model.application.sense.ports.VelocityPort;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 Measurement representing the velocity / speed of the measured object, e.g. could be a tachometer, 
 * a wind sensor (wind speed), log (speed through water)
 * @generated 
 */
@UMLImplementation(classifier = VelocityPort.class)
public class VelocityPortImpl extends SensorPortImpl implements VelocityPort  
{
	
	
	/**
	 *	@generated 
	 */
	private VelocityMeasurement mMeasurement = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public VelocityPortImpl(){
		super();
		//set the default values and assign them to this instance 
		setMeasurement(mMeasurement);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public VelocityPortImpl(final VelocityPort _copy) {
		super(_copy);
		mMeasurement = _copy.getMeasurement();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public VelocityPortImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, Geometry _observedRegion, List<ErrorModel> _errorModel, VelocityMeasurement _measurement) {
		super(_name,_allias,_remarks,_observers,_identifier,_observedRegion,_errorModel);
		mMeasurement = _measurement; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PortsPackage.Literals.VelocityPort;
	}

	/**
	 *	@generated 
	 */
	public void setMeasurement(VelocityMeasurement _measurement) {
		Notification<VelocityMeasurement> notification = basicSet(mMeasurement, _measurement, PortsPackage.Literals.VelocityPort_measurement);
		mMeasurement = _measurement;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public VelocityMeasurement getMeasurement() {
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
		if (measurement instanceof VelocityMeasurement == false)
			return false;
		setMeasurement((VelocityMeasurement)measurement);
		return true;
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "VelocityPortImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
