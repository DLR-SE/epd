package de.emir.model.domain.maritime.vessel.impl;

import java.util.List;

import de.emir.model.domain.maritime.vessel.ControlSurfaces;
import de.emir.model.domain.maritime.vessel.Rudder;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.units.AngularSpeed;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;


/**

 * These forces and moments arise due to the Control Surfaces (CS) like rudder, fins, etc. movement
 * 
 * \source Mathematical Ship Modeling for Control Applications, 2002, Perez, Blanket
 * @generated 
 */
@UMLImplementation(classifier = ControlSurfaces.class)
public class ControlSurfacesImpl extends UObjectImpl implements ControlSurfaces  
{
	
	
	/**
	 *	@generated 
	 */
	private AngularSpeed mMaximumRateOfTurn = null;
	/**
	 *	@generated 
	 */
	private List<Rudder> mRudders = null;
	/**
	 *	Default constructor
	 *	@generated
	 */
	public ControlSurfacesImpl(){
		super();
		//set the default values and assign them to this instance 
		setMaximumRateOfTurn(mMaximumRateOfTurn);
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ControlSurfacesImpl(AngularSpeed _maximumRateOfTurn, List<Rudder> _rudders) {
		mMaximumRateOfTurn = _maximumRateOfTurn; 
		mRudders = _rudders; 
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ControlSurfacesImpl(final ControlSurfaces _copy) {
		mMaximumRateOfTurn = _copy.getMaximumRateOfTurn();
		mRudders = _copy.getRudders();
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.ControlSurfaces;
	}

	/**
	 *	@generated 
	 */
	public void setMaximumRateOfTurn(AngularSpeed _maximumRateOfTurn) {
		Notification<AngularSpeed> notification = basicSet(mMaximumRateOfTurn, _maximumRateOfTurn, VesselPackage.Literals.ControlSurfaces_maximumRateOfTurn);
		mMaximumRateOfTurn = _maximumRateOfTurn;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public AngularSpeed getMaximumRateOfTurn() {
		return mMaximumRateOfTurn;
	}

	/**
	 *	@generated 
	 */
	public List<Rudder> getRudders() {
		if (mRudders == null) {
			mRudders = new UContainmentList<Rudder>(this, VesselPackage.theInstance.getControlSurfaces_rudders()); 
		}
		return mRudders;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "ControlSurfacesImpl{" +
		"}";
	}
}
