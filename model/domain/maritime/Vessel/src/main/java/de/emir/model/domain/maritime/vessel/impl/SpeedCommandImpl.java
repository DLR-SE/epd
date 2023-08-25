package de.emir.model.domain.maritime.vessel.impl;

import de.emir.model.domain.maritime.vessel.SpeedCommand;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.core.IdentifiedObject;
import de.emir.model.universal.units.Speed;
import de.emir.model.domain.maritime.vessel.impl.SteeringCommandImpl;
import de.emir.model.universal.core.ModelReference;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.NotificationType;


/**

 * The commanded speed for the vessel, which is not directly
 * depended on any specific engine rpm or propeller pitch
 * @generated 
 */
@UMLImplementation(classifier = SpeedCommand.class)
public class SpeedCommandImpl extends SteeringCommandImpl implements SpeedCommand  
{
	
	
	/**
	 *	@generated 
	 */
	private Speed mSpeed = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public SpeedCommandImpl(){
		super();
		//set the default values and assign them to this instance 
		setSpeed(mSpeed);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public SpeedCommandImpl(final SpeedCommand _copy) {
		super(_copy);
		mSpeed = _copy.getSpeed();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public SpeedCommandImpl(Time _creationTime, ModelReference _source, Speed _speed) {
		super(_creationTime,_source);
		mSpeed = _speed; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.SpeedCommand;
	}

	/**
	 *	@generated 
	 */
	public void setSpeed(Speed _speed) {
		Notification<Speed> notification = basicSet(mSpeed, _speed, VesselPackage.Literals.SpeedCommand_speed);
		mSpeed = _speed;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Speed getSpeed() {
		return mSpeed;
	}

	/**
	* @generated not
	*/
	@Override
	public String toString() {
		return "SpeedCommandImpl{ speed = " + getSpeed() + "}";
	}
}
