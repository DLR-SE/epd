package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.SteeringCommand;
import de.emir.model.domain.maritime.vessel.VesselCharacteristic;
import java.util.List;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * The StandingCommands characteristic holds all currently valid commands
 * which are not related to the machines (e.g. contains no MachineCommands)
 * Commands within this characteristic are typically given by an responsible nautical officer. 
 * @generated 
 */
@UMLClass(parent = VesselCharacteristic.class)	
public interface StandingCommands extends VesselCharacteristic 
{
	/**
	 List of currently valid steering commands 
	 * @generated 
	 */
	@UMLProperty(name = "commands", associationType = AssociationType.COMPOSITE)
	public List<SteeringCommand> getCommands();
	
}
