package de.emir.model.domain.maritime.vessel.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.domain.maritime.vessel.AutopilotAlarm;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = AutopilotAlarm.class)
public class AutopilotAlarmImpl extends UObjectImpl implements AutopilotAlarm  
{
	
	
	/**
	 *	@generated 
	 */
	private String mMessage = "";
	/**
	 *	@generated 
	 */
	private Time mCreationTime = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public AutopilotAlarmImpl(){
		super();
		//set the default values and assign them to this instance 
		setCreationTime(mCreationTime);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public AutopilotAlarmImpl(final AutopilotAlarm _copy) {
		mMessage = _copy.getMessage();
		mCreationTime = _copy.getCreationTime();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public AutopilotAlarmImpl(String _message, Time _creationTime) {
		mMessage = _message;
		mCreationTime = _creationTime; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.AutopilotAlarm;
	}
	
	/**
	 *	@generated 
	 */
	public void setMessage(String _message) {
		if (needNotification(VesselPackage.Literals.AutopilotAlarm_message)){
			String _oldValue = mMessage;
			mMessage = _message;
			notify(_oldValue, mMessage, VesselPackage.Literals.AutopilotAlarm_message, NotificationType.SET);
		}else{
			mMessage = _message;
		}
	}

	/**
	 *	@generated 
	 */
	public String getMessage() {
		return mMessage;
	}
	/**
	 *	@generated 
	 */
	public void setCreationTime(Time _creationTime) {
		Notification<Time> notification = basicSet(mCreationTime, _creationTime, VesselPackage.Literals.AutopilotAlarm_creationTime);
		mCreationTime = _creationTime;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 *	@generated 
	 */
	public Time getCreationTime() {
		return mCreationTime;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "AutopilotAlarmImpl{" +
		" message = " + getMessage() + 
		"}";
	}
}
