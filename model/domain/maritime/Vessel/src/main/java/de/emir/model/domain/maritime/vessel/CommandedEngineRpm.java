package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.MachineCommand;
import de.emir.model.universal.units.AngularSpeed;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * A command directed to change the rpm of a single engine
 * @generated 
 */
@UMLClass(parent = MachineCommand.class)	
public interface CommandedEngineRpm extends MachineCommand 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "rpm", associationType = AssociationType.COMPOSITE)
	public void setRpm(AngularSpeed _rpm);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "rpm", associationType = AssociationType.COMPOSITE)
	public AngularSpeed getRpm();
	
}
