package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.MachineCommand;
import de.emir.model.universal.units.Angle;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * A command directed to change the pitch of a specific
 * propeller
 * @generated 
 */
@UMLClass(parent = MachineCommand.class)	
public interface CommandedPropellerPitch extends MachineCommand 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "pitch", associationType = AssociationType.COMPOSITE)
	public void setPitch(Angle _pitch);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "pitch", associationType = AssociationType.COMPOSITE)
	public Angle getPitch();
	
}
