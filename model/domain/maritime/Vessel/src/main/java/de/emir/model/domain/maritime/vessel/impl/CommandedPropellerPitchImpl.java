package de.emir.model.domain.maritime.vessel.impl;

import de.emir.model.domain.maritime.vessel.CommandedPropellerPitch;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.units.Angle;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.model.domain.maritime.vessel.impl.MachineCommandImpl;
import de.emir.model.universal.core.ModelReference;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.NotificationType;


/**

 * A command directed to change the pitch of a specific
 * propeller
 * @generated 
 */
@UMLImplementation(classifier = CommandedPropellerPitch.class)
public class CommandedPropellerPitchImpl extends MachineCommandImpl implements CommandedPropellerPitch  
{
	
	
	/**
	 *	@generated 
	 */
	private Angle mPitch = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public CommandedPropellerPitchImpl(){
		super();
		//set the default values and assign them to this instance 
		setPitch(mPitch);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public CommandedPropellerPitchImpl(final CommandedPropellerPitch _copy) {
		super(_copy);
		mPitch = _copy.getPitch();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public CommandedPropellerPitchImpl(Time _creationTime, ModelReference _source, Angle _pitch) {
		super(_creationTime,_source);
		mPitch = _pitch; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.CommandedPropellerPitch;
	}

	/**
	 *	@generated 
	 */
	public void setPitch(Angle _pitch) {
		Notification<Angle> notification = basicSet(mPitch, _pitch, VesselPackage.Literals.CommandedPropellerPitch_pitch);
		mPitch = _pitch;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Angle getPitch() {
		return mPitch;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "CommandedPropellerPitchImpl{" +
		"}";
	}
}
