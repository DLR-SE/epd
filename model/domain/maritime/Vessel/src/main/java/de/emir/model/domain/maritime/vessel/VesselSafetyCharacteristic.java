package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.VesselCharacteristic;
import de.emir.model.universal.units.Length;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = VesselCharacteristic.class)	
public interface VesselSafetyCharacteristic extends VesselCharacteristic 
{
	/**
	
	 * Under Keel Clearance, space between keel and ground
	 * @generated 
	 */
	@UMLProperty(name = "underKeelClearance", associationType = AssociationType.COMPOSITE)
	public void setUnderKeelClearance(Length _underKeelClearance);
	/**
	
	 * Under Keel Clearance, space between keel and ground
	 * @generated 
	 */
	@UMLProperty(name = "underKeelClearance", associationType = AssociationType.COMPOSITE)
	public Length getUnderKeelClearance();
	/**
	
	 * Space around a ship where no other ship should be 
	 * @generated 
	 */
	@UMLProperty(name = "personalSpace", associationType = AssociationType.COMPOSITE)
	public void setPersonalSpace(Length _personalSpace);
	/**
	
	 * Space around a ship where no other ship should be 
	 * @generated 
	 */
	@UMLProperty(name = "personalSpace", associationType = AssociationType.COMPOSITE)
	public Length getPersonalSpace();
	
}
