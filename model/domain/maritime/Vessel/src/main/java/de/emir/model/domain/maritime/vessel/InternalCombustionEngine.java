package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.CommandedEngineRpm;
import de.emir.model.domain.maritime.vessel.Engine;
import de.emir.model.universal.units.AngularSpeed;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * An engine that converts chemical energy to mechanical energy
 * @generated 
 */
@UMLClass(parent = Engine.class)	
public interface InternalCombustionEngine extends Engine 
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
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "maximumRpm", associationType = AssociationType.COMPOSITE)
	public void setMaximumRpm(AngularSpeed _maximumRpm);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "maximumRpm", associationType = AssociationType.COMPOSITE)
	public AngularSpeed getMaximumRpm();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "minimumRpm", associationType = AssociationType.COMPOSITE)
	public void setMinimumRpm(AngularSpeed _minimumRpm);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "minimumRpm", associationType = AssociationType.COMPOSITE)
	public AngularSpeed getMinimumRpm();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "commandedRpm", associationType = AssociationType.COMPOSITE)
	public void setCommandedRpm(CommandedEngineRpm _commandedRpm);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "commandedRpm", associationType = AssociationType.COMPOSITE)
	public CommandedEngineRpm getCommandedRpm();
	
}
