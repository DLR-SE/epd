package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.CommandedPropellerPitch;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.units.Angle;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * A propeller of a ship, used for converting mechanical energy to kinetic energy
 * @generated 
 */
@UMLClass(parent = PhysicalObject.class)	
public interface Propeller extends PhysicalObject 
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
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "minimumPitch", associationType = AssociationType.COMPOSITE)
	public void setMinimumPitch(Angle _minimumPitch);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "minimumPitch", associationType = AssociationType.COMPOSITE)
	public Angle getMinimumPitch();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "maximumPitch", associationType = AssociationType.COMPOSITE)
	public void setMaximumPitch(Angle _maximumPitch);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "maximumPitch", associationType = AssociationType.COMPOSITE)
	public Angle getMaximumPitch();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "commandedPitch", associationType = AssociationType.COMPOSITE)
	public void setCommandedPitch(CommandedPropellerPitch _commandedPitch);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "commandedPitch", associationType = AssociationType.COMPOSITE)
	public CommandedPropellerPitch getCommandedPitch();
	
}
