package de.emir.model.domain.maritime.vessel.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.domain.maritime.vessel.AutopilotConfiguration;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngularSpeed;
import de.emir.model.universal.units.Speed;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = AutopilotConfiguration.class)
public class AutopilotConfigurationImpl extends UObjectImpl implements AutopilotConfiguration  
{
	
	
	/**
	 Maximum rudder angle the autopilot is allowed to use 
	 * @generated 
	 */
	private Angle mAngleLimit = null;
	/**
	 Maximum rate of turn, the autopilot ia allowed to use 
	 * @generated 
	 */
	private AngularSpeed mMaxTurnRate = null;
	/**
	 minimum speed (sog), the autopilot is allowed to use 
	 * @generated 
	 */
	private Speed mMinSpeed = null;
	/**
	 maximum speed (sog), the autopliot is allowed to use 
	 * @generated 
	 */
	private Speed mMaxSpeed = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public AutopilotConfigurationImpl(){
		super();
		//set the default values and assign them to this instance 
		setAngleLimit(mAngleLimit);
		setMaxTurnRate(mMaxTurnRate);
		setMinSpeed(mMinSpeed);
		setMaxSpeed(mMaxSpeed);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public AutopilotConfigurationImpl(final AutopilotConfiguration _copy) {
		mAngleLimit = _copy.getAngleLimit();
		mMaxTurnRate = _copy.getMaxTurnRate();
		mMinSpeed = _copy.getMinSpeed();
		mMaxSpeed = _copy.getMaxSpeed();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public AutopilotConfigurationImpl(Angle _angleLimit, AngularSpeed _maxTurnRate, Speed _minSpeed, Speed _maxSpeed) {
		mAngleLimit = _angleLimit; 
		mMaxTurnRate = _maxTurnRate; 
		mMinSpeed = _minSpeed; 
		mMaxSpeed = _maxSpeed; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.AutopilotConfiguration;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 Maximum rudder angle the autopilot is allowed to use 
	 * @generated 
	 */
	public void setAngleLimit(Angle _angleLimit) {
		Notification<Angle> notification = basicSet(mAngleLimit, _angleLimit, VesselPackage.Literals.AutopilotConfiguration_angleLimit);
		mAngleLimit = _angleLimit;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 Maximum rudder angle the autopilot is allowed to use 
	 * @generated 
	 */
	public Angle getAngleLimit() {
		return mAngleLimit;
	}
	/**
	 Maximum rate of turn, the autopilot ia allowed to use 
	 * @generated 
	 */
	public void setMaxTurnRate(AngularSpeed _maxTurnRate) {
		Notification<AngularSpeed> notification = basicSet(mMaxTurnRate, _maxTurnRate, VesselPackage.Literals.AutopilotConfiguration_maxTurnRate);
		mMaxTurnRate = _maxTurnRate;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 Maximum rate of turn, the autopilot ia allowed to use 
	 * @generated 
	 */
	public AngularSpeed getMaxTurnRate() {
		return mMaxTurnRate;
	}
	/**
	 minimum speed (sog), the autopilot is allowed to use 
	 * @generated 
	 */
	public void setMinSpeed(Speed _minSpeed) {
		Notification<Speed> notification = basicSet(mMinSpeed, _minSpeed, VesselPackage.Literals.AutopilotConfiguration_minSpeed);
		mMinSpeed = _minSpeed;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 minimum speed (sog), the autopilot is allowed to use 
	 * @generated 
	 */
	public Speed getMinSpeed() {
		return mMinSpeed;
	}
	/**
	 maximum speed (sog), the autopliot is allowed to use 
	 * @generated 
	 */
	public void setMaxSpeed(Speed _maxSpeed) {
		Notification<Speed> notification = basicSet(mMaxSpeed, _maxSpeed, VesselPackage.Literals.AutopilotConfiguration_maxSpeed);
		mMaxSpeed = _maxSpeed;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 maximum speed (sog), the autopliot is allowed to use 
	 * @generated 
	 */
	public Speed getMaxSpeed() {
		return mMaxSpeed;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "AutopilotConfigurationImpl{" +
		"}";
	}
}
