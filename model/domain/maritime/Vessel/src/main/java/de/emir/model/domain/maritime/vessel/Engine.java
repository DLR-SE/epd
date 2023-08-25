package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.CommandedValue;
import de.emir.model.domain.maritime.vessel.EngineBuildInformation;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.units.Acceleration;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "Engine", parent = PhysicalObject.class)	
public interface Engine extends PhysicalObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "buildInformation", associationType = AssociationType.COMPOSITE)
	public void setBuildInformation(EngineBuildInformation _buildInformation);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "buildInformation", associationType = AssociationType.COMPOSITE)
	public EngineBuildInformation getBuildInformation();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "acceleration", associationType = AssociationType.COMPOSITE)
	public void setAcceleration(Acceleration _acceleration);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "acceleration", associationType = AssociationType.COMPOSITE)
	public Acceleration getAcceleration();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "commandedEngineForce", associationType = AssociationType.COMPOSITE)
	public void setCommandedEngineForce(CommandedValue _commandedEngineForce);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "commandedEngineForce", associationType = AssociationType.COMPOSITE)
	public CommandedValue getCommandedEngineForce();
	
}
