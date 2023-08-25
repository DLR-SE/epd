package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.CommandedRudderAngle;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngularSpeed;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "Rudder", parent = PhysicalObject.class)	
public interface Rudder extends PhysicalObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "rateOfTurn", associationType = AssociationType.COMPOSITE)
	public void setRateOfTurn(AngularSpeed _rateOfTurn);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "rateOfTurn", associationType = AssociationType.COMPOSITE)
	public AngularSpeed getRateOfTurn();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "maximumAngle", associationType = AssociationType.COMPOSITE)
	public void setMaximumAngle(Angle _maximumAngle);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "maximumAngle", associationType = AssociationType.COMPOSITE)
	public Angle getMaximumAngle();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "commandedAngle", associationType = AssociationType.COMPOSITE)
	public void setCommandedAngle(CommandedRudderAngle _commandedAngle);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "commandedAngle", associationType = AssociationType.COMPOSITE)
	public CommandedRudderAngle getCommandedAngle();
	
}
