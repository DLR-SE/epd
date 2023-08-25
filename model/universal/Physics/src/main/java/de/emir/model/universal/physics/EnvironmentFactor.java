package de.emir.model.universal.physics;

import de.emir.model.universal.physics.LocatableObject;
import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "EnvironmentFactor", isAbstract = true, parent = LocatableObject.class)	
public interface EnvironmentFactor extends LocatableObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "areaOfInfluence", associationType = AssociationType.COMPOSITE)
	public void setAreaOfInfluence(Geometry _areaOfInfluence);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "areaOfInfluence", associationType = AssociationType.COMPOSITE)
	public Geometry getAreaOfInfluence();
	
}
