package de.emir.model.universal.physics;

import de.emir.model.universal.physics.EnvironmentFactor;
import de.emir.model.universal.physics.EnvironmentLayer;
import java.util.List;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "EnvironmentFactorLayer", parent = EnvironmentLayer.class)	
public interface EnvironmentFactorLayer extends EnvironmentLayer 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "environmentFactors", associationType = AssociationType.COMPOSITE)
	public List<EnvironmentFactor> getEnvironmentFactors();
	
}
