package de.emir.model.domain.maritime.vessel;

import de.emir.model.universal.units.Mass;
import de.emir.model.universal.units.Volume;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 
 * A measurement of the weight of the vessel, usually used for warships. 
 * Merchant ships are usually measured based on the volume of cargo space; see tonnage. 
 * @generated 
 */
@UMLClass(name = "Displacement")	
public interface Displacement extends UObject 
{
	/**
	
	 * The weight of the ship excluding cargo, fuel, ballast, stores, passengers, and crew, but with water in the boilers to steaming level.
	 * @generated 
	 */
	@UMLProperty(name = "light", associationType = AssociationType.COMPOSITE)
	public void setLight(Volume _light);
	/**
	
	 * The weight of the ship excluding cargo, fuel, ballast, stores, passengers, and crew, but with water in the boilers to steaming level.
	 * @generated 
	 */
	@UMLProperty(name = "light", associationType = AssociationType.COMPOSITE)
	public Volume getLight();
	/**
	
	 * The weight of the ship including cargo, passengers, fuel, water, stores, dunnage and such other items necessary for use on a voyage, which brings the vessel down to her load draft.
	 * @generated 
	 */
	@UMLProperty(name = "loaded", associationType = AssociationType.COMPOSITE)
	public void setLoaded(Volume _loaded);
	/**
	
	 * The weight of the ship including cargo, passengers, fuel, water, stores, dunnage and such other items necessary for use on a voyage, which brings the vessel down to her load draft.
	 * @generated 
	 */
	@UMLProperty(name = "loaded", associationType = AssociationType.COMPOSITE)
	public Volume getLoaded();
	/**
	
	 * (DWT) - The difference between displacement, light and displacement, loaded. A measure of the ship's total carrying capacity.
	 * @generated 
	 */
	@UMLProperty(name = "deadweight", associationType = AssociationType.COMPOSITE)
	public void setDeadweight(Volume _deadweight);
	/**
	
	 * (DWT) - The difference between displacement, light and displacement, loaded. A measure of the ship's total carrying capacity.
	 * @generated 
	 */
	@UMLProperty(name = "deadweight", associationType = AssociationType.COMPOSITE)
	public Volume getDeadweight();
	/**
	
	 * The weight remaining after deducting fuel, water, stores, dunnage and such other items necessary for use on a voyage from the deadweight of the vessel.
	 * @generated 
	 */
	@UMLProperty(name = "cargoDeadweigthTons", associationType = AssociationType.COMPOSITE)
	public void setCargoDeadweigthTons(Volume _cargoDeadweigthTons);
	/**
	
	 * The weight remaining after deducting fuel, water, stores, dunnage and such other items necessary for use on a voyage from the deadweight of the vessel.
	 * @generated 
	 */
	@UMLProperty(name = "cargoDeadweigthTons", associationType = AssociationType.COMPOSITE)
	public Volume getCargoDeadweigthTons();
	
	/**
	 returns the dead weight mass, in relation to seawater (1025 kg/m^3)
	 * @param seawaterFactor scales the mass to get the displaced weight in fresh water use 1000 [kg/m^3] / 1025 [kg/m^3]
	 * @return the mass of the displaced water
	 * @generated 
	 */
	Mass getDeadWeightMass(final float seawaterFactor);
	
}
