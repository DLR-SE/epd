package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.MachineCommand;
import de.emir.model.universal.units.Angle;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * A specific command for a rudder angle change. Directed
 * to only one rudder.
 * @generated 
 */
@UMLClass(parent = MachineCommand.class)	
public interface CommandedRudderAngle extends MachineCommand 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "angle", associationType = AssociationType.COMPOSITE)
	public void setAngle(Angle _angle);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "angle", associationType = AssociationType.COMPOSITE)
	public Angle getAngle();
	
}
