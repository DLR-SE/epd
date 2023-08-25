package de.emir.model.domain.maritime.vessel.impl;

import java.util.List;

import de.emir.model.domain.maritime.vessel.CommandedRudderAngle;
import de.emir.model.domain.maritime.vessel.Rudder;
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
import de.emir.model.universal.units.AngularSpeed;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Rudder.class)
public class RudderImpl extends PhysicalObjectImpl implements Rudder  
{
	
	
	/**
	 *	@generated 
	 */
	private AngularSpeed mRateOfTurn = null;
	/**
	 *	@generated 
	 */
	private Angle mMaximumAngle = null;
	/**
	 *	@generated 
	 */
	private CommandedRudderAngle mCommandedAngle = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public RudderImpl(){
		super();
		//set the default values and assign them to this instance 
		setRateOfTurn(mRateOfTurn);
		setMaximumAngle(mMaximumAngle);
		setCommandedAngle(mCommandedAngle);
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public RudderImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, RelativeEngineering2D _ownedCoordinateSystem, Pose _pose, List<ICharacteristic> _characteristics, List<ICapability> _capabilities, List<PhysicalObject> _children, AngularSpeed _rateOfTurn, Angle _maximumAngle, CommandedRudderAngle _commandedAngle) {
		super(_name,_allias,_remarks,_observers,_identifier,_ownedCoordinateSystem,_pose,_characteristics,_capabilities,_children);
		mRateOfTurn = _rateOfTurn; 
		mMaximumAngle = _maximumAngle; 
		mCommandedAngle = _commandedAngle; 
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public RudderImpl(final Rudder _copy) {
		super(_copy);
		mRateOfTurn = _copy.getRateOfTurn();
		mMaximumAngle = _copy.getMaximumAngle();
		mCommandedAngle = _copy.getCommandedAngle();
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.Rudder;
	}

	/**
	 *	@generated 
	 */
	public void setRateOfTurn(AngularSpeed _rateOfTurn) {
		Notification<AngularSpeed> notification = basicSet(mRateOfTurn, _rateOfTurn, VesselPackage.Literals.Rudder_rateOfTurn);
		mRateOfTurn = _rateOfTurn;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public AngularSpeed getRateOfTurn() {
		return mRateOfTurn;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "RudderImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}

	/**
	 *	@generated 
	 */
	public void setCommandedAngle(CommandedRudderAngle _commandedAngle) {
		Notification<CommandedRudderAngle> notification = basicSet(mCommandedAngle, _commandedAngle, VesselPackage.Literals.Rudder_commandedAngle);
		mCommandedAngle = _commandedAngle;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public void setMaximumAngle(Angle _maximumAngle) {
		Notification<Angle> notification = basicSet(mMaximumAngle, _maximumAngle, VesselPackage.Literals.Rudder_maximumAngle);
		mMaximumAngle = _maximumAngle;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Angle getMaximumAngle() {
		return mMaximumAngle;
	}

	/**
	 *	@generated 
	 */
	public CommandedRudderAngle getCommandedAngle() {
		return mCommandedAngle;
	}
}
