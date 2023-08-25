package de.emir.model.domain.maritime.vessel.impl;

import de.emir.model.domain.maritime.vessel.Displacement;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.units.Mass;
import de.emir.model.universal.units.MassUnit;
import de.emir.model.universal.units.Volume;
import de.emir.model.universal.units.impl.MassImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 
 * A measurement of the weight of the vessel, usually used for warships. 
 * Merchant ships are usually measured based on the volume of cargo space; see tonnage. 
 * @generated 
 */
@UMLImplementation(classifier = Displacement.class)
public class DisplacementImpl extends UObjectImpl implements Displacement  
{
	
	
	/**
	
	 * The weight of the ship excluding cargo, fuel, ballast, stores, passengers, and crew, but with water in the boilers to steaming level.
	 * @generated 
	 */
	private Volume mLight = null;
	/**
	
	 * The weight of the ship including cargo, passengers, fuel, water, stores, dunnage and such other items necessary for use on a voyage, which brings the vessel down to her load draft.
	 * @generated 
	 */
	private Volume mLoaded = null;
	/**
	
	 * (DWT) - The difference between displacement, light and displacement, loaded. A measure of the ship's total carrying capacity.
	 * @generated 
	 */
	private Volume mDeadweight = null;
	/**
	
	 * The weight remaining after deducting fuel, water, stores, dunnage and such other items necessary for use on a voyage from the deadweight of the vessel.
	 * @generated 
	 */
	private Volume mCargoDeadweigthTons = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public DisplacementImpl(){
		super();
		//set the default values and assign them to this instance 
		setLight(mLight);
		setLoaded(mLoaded);
		setDeadweight(mDeadweight);
		setCargoDeadweigthTons(mCargoDeadweigthTons);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public DisplacementImpl(final Displacement _copy) {
		mLight = _copy.getLight();
		mLoaded = _copy.getLoaded();
		mDeadweight = _copy.getDeadweight();
		mCargoDeadweigthTons = _copy.getCargoDeadweigthTons();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public DisplacementImpl(Volume _light, Volume _loaded, Volume _deadweight, Volume _cargoDeadweigthTons) {
		mLight = _light; 
		mLoaded = _loaded; 
		mDeadweight = _deadweight; 
		mCargoDeadweigthTons = _cargoDeadweigthTons; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.Displacement;
	}

	/**
	
	 * The weight of the ship excluding cargo, fuel, ballast, stores, passengers, and crew, but with water in the boilers to steaming level.
	 * @generated 
	 */
	public void setLight(Volume _light) {
		Notification<Volume> notification = basicSet(mLight, _light, VesselPackage.Literals.Displacement_light);
		mLight = _light;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	
	 * The weight of the ship excluding cargo, fuel, ballast, stores, passengers, and crew, but with water in the boilers to steaming level.
	 * @generated 
	 */
	public Volume getLight() {
		return mLight;
	}

	/**
	
	 * The weight of the ship including cargo, passengers, fuel, water, stores, dunnage and such other items necessary for use on a voyage, which brings the vessel down to her load draft.
	 * @generated 
	 */
	public void setLoaded(Volume _loaded) {
		Notification<Volume> notification = basicSet(mLoaded, _loaded, VesselPackage.Literals.Displacement_loaded);
		mLoaded = _loaded;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	
	 * The weight of the ship including cargo, passengers, fuel, water, stores, dunnage and such other items necessary for use on a voyage, which brings the vessel down to her load draft.
	 * @generated 
	 */
	public Volume getLoaded() {
		return mLoaded;
	}

	/**
	
	 * (DWT) - The difference between displacement, light and displacement, loaded. A measure of the ship's total carrying capacity.
	 * @generated 
	 */
	public void setDeadweight(Volume _deadweight) {
		Notification<Volume> notification = basicSet(mDeadweight, _deadweight, VesselPackage.Literals.Displacement_deadweight);
		mDeadweight = _deadweight;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	
	 * (DWT) - The difference between displacement, light and displacement, loaded. A measure of the ship's total carrying capacity.
	 * @generated 
	 */
	public Volume getDeadweight() {
		return mDeadweight;
	}

	/**
	
	 * The weight remaining after deducting fuel, water, stores, dunnage and such other items necessary for use on a voyage from the deadweight of the vessel.
	 * @generated 
	 */
	public void setCargoDeadweigthTons(Volume _cargoDeadweigthTons) {
		Notification<Volume> notification = basicSet(mCargoDeadweigthTons, _cargoDeadweigthTons, VesselPackage.Literals.Displacement_cargoDeadweigthTons);
		mCargoDeadweigthTons = _cargoDeadweigthTons;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	
	 * The weight remaining after deducting fuel, water, stores, dunnage and such other items necessary for use on a voyage from the deadweight of the vessel.
	 * @generated 
	 */
	public Volume getCargoDeadweigthTons() {
		return mCargoDeadweigthTons;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Mass getDeadWeightMass(final float seawaterFactor)
	{
		if (getDeadweight() == null)
			return null;
		return new MassImpl(getDeadweight().getValue() * 1025 * seawaterFactor, MassUnit.KILOGRAM);
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "DisplacementImpl{" +
		"}";
	}
}
