package de.emir.model.application.sense.impl;

import java.util.List;

import de.emir.model.application.sense.ErrorModel;
import de.emir.model.application.sense.Measurement;
import de.emir.model.application.sense.SensePackage;
import de.emir.model.application.sense.SensorPort;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.core.impl.IdentifiedObjectImpl;
import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.lists.ListUtils;


/**

 * A sensor outputs a piece of information (an observed value), the value itself being represented by an ObservationValue.
 * [SSN] 
 * 
 * @note to observe the occurrence of new measurements, put a listener on the measurement Feature
 * @note the measurement feature is defined in specialized classifiers
 * @generated 
 */
@UMLImplementation(classifier = SensorPort.class)
abstract public class SensorPortImpl extends IdentifiedObjectImpl implements SensorPort  
{
	
	
	/**
	
	 * describes the region for which the observation is valid.
	 * Is always given relative to the attribute pose of the associated sensor
	 * @generated 
	 */
	private Geometry mObservedRegion = null;
	/**
	
	 * optional: expected error models
	 * @generated 
	 */
	private List<ErrorModel> mErrorModel = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public SensorPortImpl(){
		super();
		//set the default values and assign them to this instance 
		setObservedRegion(mObservedRegion);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public SensorPortImpl(final SensorPort _copy) {
		super(_copy);
		mObservedRegion = _copy.getObservedRegion();
		mErrorModel = _copy.getErrorModel();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public SensorPortImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, Geometry _observedRegion, List<ErrorModel> _errorModel) {
		super(_name,_allias,_remarks,_observers,_identifier);
		mObservedRegion = _observedRegion; 
		mErrorModel = _errorModel; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SensePackage.Literals.SensorPort;
	}

	/**
	
	 * describes the region for which the observation is valid.
	 * Is always given relative to the attribute pose of the associated sensor
	 * @generated 
	 */
	public void setObservedRegion(Geometry _observedRegion) {
		Notification<Geometry> notification = basicSet(mObservedRegion, _observedRegion, SensePackage.Literals.SensorPort_observedRegion);
		mObservedRegion = _observedRegion;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	
	 * describes the region for which the observation is valid.
	 * Is always given relative to the attribute pose of the associated sensor
	 * @generated 
	 */
	public Geometry getObservedRegion() {
		return mObservedRegion;
	}

	/**
	
	 * optional: expected error models
	 * @generated 
	 */
	public List<ErrorModel> getErrorModel() {
		if (mErrorModel == null) {
			mErrorModel = ListUtils.<ErrorModel>asList(this, SensePackage.theInstance.getSensorPort_errorModel()); 
		}
		return mErrorModel;
	}

	

	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated
	 */

	/**
	 * @inheritDoc
	 * @generated
	 */
	public Measurement getLatestMeasurement()
	{
		//TODO: 
		throw new UnsupportedOperationException("getLatestMeasurement not yet implemented");
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "SensorPortImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
