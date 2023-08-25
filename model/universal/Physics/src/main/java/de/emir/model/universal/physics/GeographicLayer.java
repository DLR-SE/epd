package de.emir.model.universal.physics;

import de.emir.model.universal.physics.EnvironmentLayer;
import java.util.List;

import de.emir.model.universal.spatial.SpatialLayer;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "GeographicLayer", parent = EnvironmentLayer.class)	
public interface GeographicLayer extends EnvironmentLayer 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "spatials", associationType = AssociationType.COMPOSITE)
	public List<SpatialLayer> getSpatials();
	
}
