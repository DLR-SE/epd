package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.ControlSurfaces;
import de.emir.model.domain.maritime.vessel.PropulsionSystem;
import de.emir.model.domain.maritime.vessel.VesselCharacteristic;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * \source Mathematical Ship Modeling for Control Applications, 2002, Perez, Blanket
 * @generated 
 */
@UMLClass(name = "DynamicSystem", parent = VesselCharacteristic.class)	
public interface DynamicSystem extends VesselCharacteristic 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "propulsion", associationType = AssociationType.COMPOSITE)
	public void setPropulsion(PropulsionSystem _propulsion);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "propulsion", associationType = AssociationType.COMPOSITE)
	public PropulsionSystem getPropulsion();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "control", associationType = AssociationType.COMPOSITE)
	public void setControl(ControlSurfaces _control);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "control", associationType = AssociationType.COMPOSITE)
	public ControlSurfaces getControl();
	
}
