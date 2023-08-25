package de.emir.model.application.sense.impl;

import java.util.List;

import de.emir.model.application.sense.ErrorModel;
import de.emir.model.application.sense.SensePackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.lists.ListUtils;
import de.emir.tuml.ucore.runtime.utils.Pointer;


/**

 * \defgroup SensorErrorModel
 * An error model describes the expected error for parts of the sensor Observation
 * @generated 
 */
@UMLImplementation(classifier = ErrorModel.class)
abstract public class ErrorModelImpl extends UObjectImpl implements ErrorModel  
{
	
	
	/**
	 Expected type of observation 
	 * @generated 
	 */
	private UClassifier mObservationType = null;
	/**
	 Pointer to features, that will be affected by this error model
	 * each pointer should be positioned, relative to the observation that must inherit the observationType
	 * @generated 
	 */
	private List<Pointer> mPointers = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public ErrorModelImpl(){
		super();
		//set the default values and assign them to this instance 
		setObservationType(mObservationType);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ErrorModelImpl(final ErrorModel _copy) {
		mObservationType = _copy.getObservationType();
		mPointers = _copy.getPointers();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ErrorModelImpl(UClassifier _observationType, List<Pointer> _pointers) {
		mObservationType = _observationType; 
		mPointers = _pointers; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SensePackage.Literals.ErrorModel;
	}

	/**
	 Expected type of observation 
	 * @generated 
	 */
	public void setObservationType(UClassifier _observationType) {
		Notification<UClassifier> notification = basicSet(mObservationType, _observationType, SensePackage.Literals.ErrorModel_observationType);
		mObservationType = _observationType;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 Expected type of observation 
	 * @generated 
	 */
	public UClassifier getObservationType() {
		return mObservationType;
	}

	/**
	 Pointer to features, that will be affected by this error model
	 * each pointer should be positioned, relative to the observation that must inherit the observationType
	 * @generated 
	 */
	public List<Pointer> getPointers() {
		if (mPointers == null) {
			mPointers = ListUtils.<Pointer>asList(this, SensePackage.theInstance.getErrorModel_pointers()); 
		}
		return mPointers;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "ErrorModelImpl{" +
		"}";
	}
}
