package de.emir.model.domain.maritime.vessel.impl;

import java.util.List;

import de.emir.model.domain.maritime.vessel.CommandedValue;
import de.emir.model.domain.maritime.vessel.Engine;
import de.emir.model.domain.maritime.vessel.EngineBuildInformation;
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
import de.emir.model.universal.units.Acceleration;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Engine.class)
public class EngineImpl extends PhysicalObjectImpl implements Engine  
{
	
	
	/**
	 *	@generated 
	 */
	private EngineBuildInformation mBuildInformation = null;
	/**
	 *	@generated 
	 */
	private Acceleration mAcceleration = null;
	/**
	 *	@generated 
	 */
	private CommandedValue mCommandedEngineForce = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public EngineImpl(){
		super();
		//set the default values and assign them to this instance 
		setBuildInformation(mBuildInformation);
		setAcceleration(mAcceleration);
		setCommandedEngineForce(mCommandedEngineForce);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public EngineImpl(final Engine _copy) {
		super(_copy);
		mBuildInformation = _copy.getBuildInformation();
		mAcceleration = _copy.getAcceleration();
		mCommandedEngineForce = _copy.getCommandedEngineForce();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public EngineImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, RelativeEngineering2D _ownedCoordinateSystem, Pose _pose, List<ICharacteristic> _characteristics, List<ICapability> _capabilities, List<PhysicalObject> _children, EngineBuildInformation _buildInformation, Acceleration _acceleration, CommandedValue _commandedEngineForce) {
		super(_name,_allias,_remarks,_observers,_identifier,_ownedCoordinateSystem,_pose,_characteristics,_capabilities,_children);
		mBuildInformation = _buildInformation; 
		mAcceleration = _acceleration; 
		mCommandedEngineForce = _commandedEngineForce; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.Engine;
	}

	/**
	 *	@generated 
	 */
	public void setBuildInformation(EngineBuildInformation _buildInformation) {
		Notification<EngineBuildInformation> notification = basicSet(mBuildInformation, _buildInformation, VesselPackage.Literals.Engine_buildInformation);
		mBuildInformation = _buildInformation;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public EngineBuildInformation getBuildInformation() {
		return mBuildInformation;
	}

	/**
	 *	@generated 
	 */
	public void setAcceleration(Acceleration _acceleration) {
		Notification<Acceleration> notification = basicSet(mAcceleration, _acceleration, VesselPackage.Literals.Engine_acceleration);
		mAcceleration = _acceleration;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public void setCommandedEngineForce(CommandedValue _commandedEngineForce) {
		Notification<CommandedValue> notification = basicSet(mCommandedEngineForce, _commandedEngineForce, VesselPackage.Literals.Engine_commandedEngineForce);
		mCommandedEngineForce = _commandedEngineForce;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public CommandedValue getCommandedEngineForce() {
		return mCommandedEngineForce;
	}

	/**
	 *	@generated 
	 */
	public Acceleration getAcceleration() {
		return mAcceleration;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "EngineImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
