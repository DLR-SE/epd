package de.emir.model.domain.maritime.vessel.impl;

import java.util.List;

import de.emir.model.domain.maritime.vessel.Engine;
import de.emir.model.domain.maritime.vessel.Propeller;
import de.emir.model.domain.maritime.vessel.PropulsionSystem;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.units.Acceleration;
import de.emir.model.universal.units.Velocity;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;


/**

 * Propulsion is a means of creating force leading to movement. 
 * 
 * A propulsion system consists of a source of mechanical power, and a propulsor (means of converting this power into propulsive force).
 * A technological system uses an engine or motor as the power source, and wheels and axles, propellers, or a propulsive nozzle to generate the force. 
 * Components such as clutches or gearboxes may be needed to connect the motor to axles, wheels, or propellors.
 * 
 * \source wikipedia
 * @generated 
 */
@UMLImplementation(classifier = PropulsionSystem.class)
public class PropulsionSystemImpl extends UObjectImpl implements PropulsionSystem  
{
	
	
	/**
	 *	@generated 
	 */
	private Velocity mMaximumVelocity = null;
	/**
	 may be negativ to indicate backwards movements 
	 * @generated 
	 */
	private Velocity mMinimumVelocity = null;
	/**
	 *	@generated 
	 */
	private Acceleration mMaximumAcceleration = null;
	/**
	 *	@generated 
	 */
	private Acceleration mMinimumAcceleration = null;
	/**
	 *	@generated 
	 */
	private List<Engine> mEngines = null;
	/**
	 *	@generated 
	 */
	private List<Propeller> mPropellers = null;
	/**
	 *	Default constructor
	 *	@generated
	 */
	public PropulsionSystemImpl(){
		super();
		//set the default values and assign them to this instance 
		setMaximumVelocity(mMaximumVelocity);
		setMinimumVelocity(mMinimumVelocity);
		setMaximumAcceleration(mMaximumAcceleration);
		setMinimumAcceleration(mMinimumAcceleration);
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public PropulsionSystemImpl(Velocity _maximumVelocity, Velocity _minimumVelocity, Acceleration _maximumAcceleration, Acceleration _minimumAcceleration, List<Engine> _engines, List<Propeller> _propellers) {
		mMaximumVelocity = _maximumVelocity; 
		mMinimumVelocity = _minimumVelocity; 
		mMaximumAcceleration = _maximumAcceleration; 
		mMinimumAcceleration = _minimumAcceleration; 
		mEngines = _engines; 
		mPropellers = _propellers; 
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public PropulsionSystemImpl(final PropulsionSystem _copy) {
		mMaximumVelocity = _copy.getMaximumVelocity();
		mMinimumVelocity = _copy.getMinimumVelocity();
		mMaximumAcceleration = _copy.getMaximumAcceleration();
		mMinimumAcceleration = _copy.getMinimumAcceleration();
		mEngines = _copy.getEngines();
		mPropellers = _copy.getPropellers();
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.PropulsionSystem;
	}

	/**
	 *	@generated 
	 */
	public void setMaximumVelocity(Velocity _maximumVelocity) {
		Notification<Velocity> notification = basicSet(mMaximumVelocity, _maximumVelocity, VesselPackage.Literals.PropulsionSystem_maximumVelocity);
		mMaximumVelocity = _maximumVelocity;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Velocity getMaximumVelocity() {
		return mMaximumVelocity;
	}

	/**
	 may be negativ to indicate backwards movements 
	 * @generated 
	 */
	public void setMinimumVelocity(Velocity _minimumVelocity) {
		Notification<Velocity> notification = basicSet(mMinimumVelocity, _minimumVelocity, VesselPackage.Literals.PropulsionSystem_minimumVelocity);
		mMinimumVelocity = _minimumVelocity;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 may be negativ to indicate backwards movements 
	 * @generated 
	 */
	public Velocity getMinimumVelocity() {
		return mMinimumVelocity;
	}

	/**
	 *	@generated 
	 */
	public void setMaximumAcceleration(Acceleration _maximumAcceleration) {
		Notification<Acceleration> notification = basicSet(mMaximumAcceleration, _maximumAcceleration, VesselPackage.Literals.PropulsionSystem_maximumAcceleration);
		mMaximumAcceleration = _maximumAcceleration;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Acceleration getMaximumAcceleration() {
		return mMaximumAcceleration;
	}

	/**
	 *	@generated 
	 */
	public void setMinimumAcceleration(Acceleration _minimumAcceleration) {
		Notification<Acceleration> notification = basicSet(mMinimumAcceleration, _minimumAcceleration, VesselPackage.Literals.PropulsionSystem_minimumAcceleration);
		mMinimumAcceleration = _minimumAcceleration;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Acceleration getMinimumAcceleration() {
		return mMinimumAcceleration;
	}

	/**
	 *	@generated 
	 */
	public List<Engine> getEngines() {
		if (mEngines == null) {
			mEngines = new UContainmentList<Engine>(this, VesselPackage.theInstance.getPropulsionSystem_engines()); 
		}
		return mEngines;
	}

	/**
	 *	@generated 
	 */
	public List<Propeller> getPropellers() {
		if (mPropellers == null) {
			mPropellers = new UContainmentList<Propeller>(this, VesselPackage.theInstance.getPropulsionSystem_propellers()); 
		}
		return mPropellers;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "PropulsionSystemImpl{" +
		"}";
	}
}
