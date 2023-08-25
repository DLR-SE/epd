package de.emir.model.domain.maritime.vessel.impl;

import java.util.List;

import de.emir.model.domain.maritime.vessel.CommandedEngineRpm;
import de.emir.model.domain.maritime.vessel.CommandedValue;
import de.emir.model.domain.maritime.vessel.EngineBuildInformation;
import de.emir.model.domain.maritime.vessel.InternalCombustionEngine;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.domain.maritime.vessel.impl.EngineImpl;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.physics.Capability;
import de.emir.model.universal.physics.ICapability;
import de.emir.model.universal.physics.ICharacteristic;
import de.emir.model.universal.physics.Characteristic;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.RelativeEngineering2D;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.units.Acceleration;
import de.emir.model.universal.units.AngularSpeed;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.NotificationType;


/**

 * An engine that converts chemical energy to mechanical energy
 * @generated 
 */
@UMLImplementation(classifier = InternalCombustionEngine.class)
public class InternalCombustionEngineImpl extends EngineImpl implements InternalCombustionEngine  
{
	
	
	/**
	 *	@generated 
	 */
	private AngularSpeed mRpm = null;
	/**
	 *	@generated 
	 */
	private AngularSpeed mMaximumRpm = null;
	/**
	 *	@generated 
	 */
	private AngularSpeed mMinimumRpm = null;
	/**
	 *	@generated 
	 */
	private CommandedEngineRpm mCommandedRpm = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public InternalCombustionEngineImpl(){
		super();
		//set the default values and assign them to this instance 
		setRpm(mRpm);
		setMaximumRpm(mMaximumRpm);
		setMinimumRpm(mMinimumRpm);
		setCommandedRpm(mCommandedRpm);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public InternalCombustionEngineImpl(final InternalCombustionEngine _copy) {
		super(_copy);
		mRpm = _copy.getRpm();
		mMaximumRpm = _copy.getMaximumRpm();
		mMinimumRpm = _copy.getMinimumRpm();
		mCommandedRpm = _copy.getCommandedRpm();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public InternalCombustionEngineImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, RelativeEngineering2D _ownedCoordinateSystem, Pose _pose, List<ICharacteristic> _characteristics, List<ICapability> _capabilities, List<PhysicalObject> _children, EngineBuildInformation _buildInformation, Acceleration _acceleration, CommandedValue _commandedEngineForce, AngularSpeed _rpm, AngularSpeed _maximumRpm, AngularSpeed _minimumRpm, CommandedEngineRpm _commandedRpm) {
		super(_name,_allias,_remarks,_observers,_identifier,_ownedCoordinateSystem,_pose,_characteristics,_capabilities,_children,_buildInformation,_acceleration,_commandedEngineForce);
		mRpm = _rpm; 
		mMaximumRpm = _maximumRpm; 
		mMinimumRpm = _minimumRpm; 
		mCommandedRpm = _commandedRpm; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.InternalCombustionEngine;
	}

	/**
	 *	@generated 
	 */
	public void setRpm(AngularSpeed _rpm) {
		Notification<AngularSpeed> notification = basicSet(mRpm, _rpm, VesselPackage.Literals.InternalCombustionEngine_rpm);
		mRpm = _rpm;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public AngularSpeed getRpm() {
		return mRpm;
	}

	/**
	 *	@generated 
	 */
	public void setMaximumRpm(AngularSpeed _maximumRpm) {
		Notification<AngularSpeed> notification = basicSet(mMaximumRpm, _maximumRpm, VesselPackage.Literals.InternalCombustionEngine_maximumRpm);
		mMaximumRpm = _maximumRpm;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public AngularSpeed getMaximumRpm() {
		return mMaximumRpm;
	}

	/**
	 *	@generated 
	 */
	public void setMinimumRpm(AngularSpeed _minimumRpm) {
		Notification<AngularSpeed> notification = basicSet(mMinimumRpm, _minimumRpm, VesselPackage.Literals.InternalCombustionEngine_minimumRpm);
		mMinimumRpm = _minimumRpm;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public AngularSpeed getMinimumRpm() {
		return mMinimumRpm;
	}

	/**
	 *	@generated 
	 */
	public void setCommandedRpm(CommandedEngineRpm _commandedRpm) {
		Notification<CommandedEngineRpm> notification = basicSet(mCommandedRpm, _commandedRpm, VesselPackage.Literals.InternalCombustionEngine_commandedRpm);
		mCommandedRpm = _commandedRpm;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public CommandedEngineRpm getCommandedRpm() {
		return mCommandedRpm;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "InternalCombustionEngineImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
