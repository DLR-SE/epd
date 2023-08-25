package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.CommandedValue;
import de.emir.model.universal.core.IdentifiedObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 Abstract command, related to steering, like direction / heading / speed
 * typically a command that is given and executed by some nautical officer
 * @generated 
 */
@UMLClass(isAbstract = true, parent = CommandedValue.class)	
public interface SteeringCommand extends CommandedValue 
{
	
}
