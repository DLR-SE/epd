package de.emir.model.domain.maritime.vessel.impl;

import de.emir.model.domain.maritime.vessel.ControlSurfaces;
import de.emir.model.domain.maritime.vessel.DynamicSystem;
import de.emir.model.domain.maritime.vessel.PropulsionSystem;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.domain.maritime.vessel.impl.VesselCharacteristicImpl;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.NotificationType;


/**

 * \source Mathematical Ship Modeling for Control Applications, 2002, Perez, Blanket
 * @generated 
 */
@UMLImplementation(classifier = DynamicSystem.class)
public class DynamicSystemImpl extends VesselCharacteristicImpl implements DynamicSystem  
{
	
	
	/**
	 *	@generated 
	 */
	private PropulsionSystem mPropulsion = null;
	/**
	 *	@generated 
	 */
	private ControlSurfaces mControl = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public DynamicSystemImpl(){
		super();
		//set the default values and assign them to this instance 
		setPropulsion(mPropulsion);
		setControl(mControl);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public DynamicSystemImpl(final DynamicSystem _copy) {
		super(_copy);
		mPropulsion = _copy.getPropulsion();
		mControl = _copy.getControl();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public DynamicSystemImpl(PropulsionSystem _propulsion, ControlSurfaces _control) {
		super();
		mPropulsion = _propulsion; 
		mControl = _control; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.DynamicSystem;
	}

	/**
	 *	@generated 
	 */
	public void setPropulsion(PropulsionSystem _propulsion) {
		Notification<PropulsionSystem> notification = basicSet(mPropulsion, _propulsion, VesselPackage.Literals.DynamicSystem_propulsion);
		mPropulsion = _propulsion;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public PropulsionSystem getPropulsion() {
		return mPropulsion;
	}

	/**
	 *	@generated 
	 */
	public void setControl(ControlSurfaces _control) {
		Notification<ControlSurfaces> notification = basicSet(mControl, _control, VesselPackage.Literals.DynamicSystem_control);
		mControl = _control;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public ControlSurfaces getControl() {
		return mControl;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "DynamicSystemImpl{" +
		"}";
	}
}
