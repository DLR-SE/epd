package de.emir.model.application.sense.ports.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.application.sense.ErrorModel;
import de.emir.model.application.sense.Measurement;
import de.emir.model.application.sense.impl.SensorPortImpl;
import de.emir.model.application.sense.measurements.ObjectMeasurement;
import de.emir.model.application.sense.ports.ObjectPort;
import de.emir.model.application.sense.ports.PortsPackage;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = ObjectPort.class)
public class ObjectPortImpl extends SensorPortImpl implements ObjectPort  
{
	
	
	/**
	 *	@generated 
	 */
	private ObjectMeasurement mMeasurement = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public ObjectPortImpl(){
		super();
		//set the default values and assign them to this instance 
		setMeasurement(mMeasurement);
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ObjectPortImpl(final ObjectPort _copy) {
		super(_copy);
		mMeasurement = _copy.getMeasurement();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ObjectPortImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, Geometry _observedRegion, List<ErrorModel> _errorModel, ObjectMeasurement _measurement) {
		super(_name,_allias,_remarks,_observers,_identifier,_observedRegion,_errorModel);
		mMeasurement = _measurement; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PortsPackage.Literals.ObjectPort;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setMeasurement(ObjectMeasurement _measurement) {
		Notification<ObjectMeasurement> notification = basicSet(mMeasurement, _measurement, PortsPackage.Literals.ObjectPort_measurement);
		mMeasurement = _measurement;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 *	@generated 
	 */
	public ObjectMeasurement getMeasurement() {
		return mMeasurement;
	}
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean setCurrentMeasurement(final Measurement measurement)
	{
		//TODO: 
		//  replace the current measurement with a new one (convenience method to avoid casting, depending on the port) 
		throw new UnsupportedOperationException("setCurrentMeasurement not yet implemented");
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "ObjectPortImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
