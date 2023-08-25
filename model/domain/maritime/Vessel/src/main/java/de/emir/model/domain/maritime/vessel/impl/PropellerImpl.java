package de.emir.model.domain.maritime.vessel.impl;

import java.util.List;

import de.emir.model.domain.maritime.vessel.CommandedPropellerPitch;
import de.emir.model.domain.maritime.vessel.Propeller;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.physics.Capability;
import de.emir.model.universal.physics.ICapability;
import de.emir.model.universal.physics.ICharacteristic;
import de.emir.model.universal.physics.Characteristic;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.RelativeEngineering2D;
import de.emir.model.universal.physics.impl.PhysicalObjectImpl;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.units.Angle;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**

 * A propeller of a ship, used for converting mechanical energy to kinetic energy
 * @generated 
 */
@UMLImplementation(classifier = Propeller.class)
public class PropellerImpl extends PhysicalObjectImpl implements Propeller  
{
	
	
	/**
	 *	@generated 
	 */
	private Angle mPitch = null;
	/**
	 *	@generated 
	 */
	private Angle mMinimumPitch = null;
	/**
	 *	@generated 
	 */
	private Angle mMaximumPitch = null;
	/**
	 *	@generated 
	 */
	private CommandedPropellerPitch mCommandedPitch = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public PropellerImpl(){
		super();
		//set the default values and assign them to this instance 
		setPitch(mPitch);
		setMinimumPitch(mMinimumPitch);
		setMaximumPitch(mMaximumPitch);
		setCommandedPitch(mCommandedPitch);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public PropellerImpl(final Propeller _copy) {
		super(_copy);
		mPitch = _copy.getPitch();
		mMinimumPitch = _copy.getMinimumPitch();
		mMaximumPitch = _copy.getMaximumPitch();
		mCommandedPitch = _copy.getCommandedPitch();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public PropellerImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, RelativeEngineering2D _ownedCoordinateSystem, Pose _pose, List<ICharacteristic> _characteristics, List<ICapability> _capabilities, List<PhysicalObject> _children, Angle _pitch, Angle _minimumPitch, Angle _maximumPitch, CommandedPropellerPitch _commandedPitch) {
		super(_name,_allias,_remarks,_observers,_identifier,_ownedCoordinateSystem,_pose,_characteristics,_capabilities,_children);
		mPitch = _pitch; 
		mMinimumPitch = _minimumPitch; 
		mMaximumPitch = _maximumPitch; 
		mCommandedPitch = _commandedPitch; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.Propeller;
	}

	/**
	 *	@generated 
	 */
	public void setPitch(Angle _pitch) {
		Notification<Angle> notification = basicSet(mPitch, _pitch, VesselPackage.Literals.Propeller_pitch);
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
	 *	@generated 
	 */
	public void setMinimumPitch(Angle _minimumPitch) {
		Notification<Angle> notification = basicSet(mMinimumPitch, _minimumPitch, VesselPackage.Literals.Propeller_minimumPitch);
		mMinimumPitch = _minimumPitch;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Angle getMinimumPitch() {
		return mMinimumPitch;
	}

	/**
	 *	@generated 
	 */
	public void setMaximumPitch(Angle _maximumPitch) {
		Notification<Angle> notification = basicSet(mMaximumPitch, _maximumPitch, VesselPackage.Literals.Propeller_maximumPitch);
		mMaximumPitch = _maximumPitch;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Angle getMaximumPitch() {
		return mMaximumPitch;
	}

	/**
	 *	@generated 
	 */
	public void setCommandedPitch(CommandedPropellerPitch _commandedPitch) {
		Notification<CommandedPropellerPitch> notification = basicSet(mCommandedPitch, _commandedPitch, VesselPackage.Literals.Propeller_commandedPitch);
		mCommandedPitch = _commandedPitch;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public CommandedPropellerPitch getCommandedPitch() {
		return mCommandedPitch;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "PropellerImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
