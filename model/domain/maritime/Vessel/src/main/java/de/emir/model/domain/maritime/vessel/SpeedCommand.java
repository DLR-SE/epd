package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.SteeringCommand;
import de.emir.model.universal.units.Speed;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * The commanded speed for the vessel, which is not directly
 * depended on any specific engine rpm or propeller pitch
 * @generated 
 */
@UMLClass(parent = SteeringCommand.class)	
public interface SpeedCommand extends SteeringCommand 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "speed", associationType = AssociationType.COMPOSITE)
	public void setSpeed(Speed _speed);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "speed", associationType = AssociationType.COMPOSITE)
	public Speed getSpeed();
	
}
