package de.emir.model.universal.physics;

import de.emir.model.universal.physics.EnvironmentFactor;
import de.emir.model.universal.units.Velocity;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "Wind", parent = EnvironmentFactor.class)	
public interface Wind extends EnvironmentFactor 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "velocity", associationType = AssociationType.COMPOSITE)
	public void setVelocity(Velocity _velocity);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "velocity", associationType = AssociationType.COMPOSITE)
	public Velocity getVelocity();
	
}
