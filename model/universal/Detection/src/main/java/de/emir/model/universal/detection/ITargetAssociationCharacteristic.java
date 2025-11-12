package de.emir.model.universal.detection;

import de.emir.model.universal.physics.Characteristic;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

    * The TargetAssociationCharacteristic is used for keeping associations between multiple
    * PhysicalObjects. When a new Target is for example created from fusing AIS and RADAR data,
    * a TargetAssociationCharacteristic could be appended to the AIS object, RADAR target and the
    * newly created object to handle associations between these objects.
 * @generated 
 */
@UMLClass(name = "TargetAssociationCharacteristic", parent = Characteristic.class)	
public interface ITargetAssociationCharacteristic extends Characteristic 
{
	/**
	 Unique association identifier. 
	 * @generated 
	 */
	@UMLProperty(name = "associationID", associationType = AssociationType.PROPERTY)
	public void setAssociationID(String _associationID);
	/**
	 Unique association identifier. 
	 * @generated 
	 */
	@UMLProperty(name = "associationID", associationType = AssociationType.PROPERTY)
	public String getAssociationID();

	
}
