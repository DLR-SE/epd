package de.emir.model.universal.physics;

import de.emir.model.universal.physics.EnvironmentLayer;
import de.emir.model.universal.physics.LocatableObject;
import java.util.List;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "ObjectLayer", parent = EnvironmentLayer.class)	
public interface ObjectLayer extends EnvironmentLayer 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "objects", associationType = AssociationType.COMPOSITE)
	public List<LocatableObject> getObjects();
	
}
