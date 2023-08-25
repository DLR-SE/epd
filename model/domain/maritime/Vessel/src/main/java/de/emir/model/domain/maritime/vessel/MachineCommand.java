package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.CommandedValue;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 An concrete command for a machine
 * typically this commands are read by an machine and produced 
 * by an controlling instance of the vessel (for example a nautical officer) as result / decomposition of steering commands
 * @generated 
 */
@UMLClass(isAbstract = true, parent = CommandedValue.class)	
public interface MachineCommand extends CommandedValue 
{
	
}
