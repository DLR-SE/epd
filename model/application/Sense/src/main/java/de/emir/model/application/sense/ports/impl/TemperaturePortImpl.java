package de.emir.model.application.sense.ports.impl;

import java.util.List;

import de.emir.model.application.sense.ErrorModel;
import de.emir.model.application.sense.Measurement;
import de.emir.model.application.sense.impl.SensorPortImpl;
import de.emir.model.application.sense.measurements.TemperatureMeasurement;
import de.emir.model.application.sense.ports.PortsPackage;
import de.emir.model.application.sense.ports.TemperaturePort;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 Measurement containing the temperature of something 
 * @generated 
 */
@UMLImplementation(classifier = TemperaturePort.class)
public class TemperaturePortImpl extends SensorPortImpl implements TemperaturePort  
{
	
	
	/**
	 *	@generated 
	 */
	private TemperatureMeasurement mMeasurement = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public TemperaturePortImpl(){
		super();
		//set the default values and assign them to this instance 
		setMeasurement(mMeasurement);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public TemperaturePortImpl(final TemperaturePort _copy) {
		super(_copy);
		mMeasurement = _copy.getMeasurement();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public TemperaturePortImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, Geometry _observedRegion, List<ErrorModel> _errorModel, TemperatureMeasurement _measurement) {
		super(_name,_allias,_remarks,_observers,_identifier,_observedRegion,_errorModel);
		mMeasurement = _measurement; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PortsPackage.Literals.TemperaturePort;
	}

	/**
	 *	@generated 
	 */
	public void setMeasurement(TemperatureMeasurement _measurement) {
		Notification<TemperatureMeasurement> notification = basicSet(mMeasurement, _measurement, PortsPackage.Literals.TemperaturePort_measurement);
		mMeasurement = _measurement;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public TemperatureMeasurement getMeasurement() {
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
		if (measurement instanceof TemperatureMeasurement == false)
			return false;
		setMeasurement((TemperatureMeasurement)measurement);
		return true;
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "TemperaturePortImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
