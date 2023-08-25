package de.emir.model.domain.maritime.vessel.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.domain.maritime.vessel.VesselSafetyCharacteristic;
import de.emir.model.domain.maritime.vessel.impl.VesselCharacteristicImpl;
import de.emir.model.universal.units.Length;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;


/**
 *	@generated 
 */
@UMLImplementation(classifier = VesselSafetyCharacteristic.class)
public class VesselSafetyCharacteristicImpl extends VesselCharacteristicImpl implements VesselSafetyCharacteristic  
{
	
	
	/**
	
	 * Under Keel Clearance, space between keel and ground
	 * @generated 
	 */
	private Length mUnderKeelClearance = null;
	/**
	
	 * Space around a ship where no other ship should be 
	 * @generated 
	 */
	private Length mPersonalSpace = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public VesselSafetyCharacteristicImpl(){
		super();
		//set the default values and assign them to this instance 
		setUnderKeelClearance(mUnderKeelClearance);
		setPersonalSpace(mPersonalSpace);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public VesselSafetyCharacteristicImpl(final VesselSafetyCharacteristic _copy) {
		super(_copy);
		mUnderKeelClearance = _copy.getUnderKeelClearance();
		mPersonalSpace = _copy.getPersonalSpace();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public VesselSafetyCharacteristicImpl(Length _underKeelClearance, Length _personalSpace) {
		super();
		mUnderKeelClearance = _underKeelClearance; 
		mPersonalSpace = _personalSpace; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.VesselSafetyCharacteristic;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	
	 * Under Keel Clearance, space between keel and ground
	 * @generated 
	 */
	public void setUnderKeelClearance(Length _underKeelClearance) {
		Notification<Length> notification = basicSet(mUnderKeelClearance, _underKeelClearance, VesselPackage.Literals.VesselSafetyCharacteristic_underKeelClearance);
		mUnderKeelClearance = _underKeelClearance;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	
	 * Under Keel Clearance, space between keel and ground
	 * @generated 
	 */
	public Length getUnderKeelClearance() {
		return mUnderKeelClearance;
	}
	/**
	
	 * Space around a ship where no other ship should be 
	 * @generated 
	 */
	public void setPersonalSpace(Length _personalSpace) {
		Notification<Length> notification = basicSet(mPersonalSpace, _personalSpace, VesselPackage.Literals.VesselSafetyCharacteristic_personalSpace);
		mPersonalSpace = _personalSpace;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	
	 * Space around a ship where no other ship should be 
	 * @generated 
	 */
	public Length getPersonalSpace() {
		return mPersonalSpace;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "VesselSafetyCharacteristicImpl{" +
		"}";
	}
}
