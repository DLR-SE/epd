package de.emir.model.universal.physics;

import java.util.List;

import de.emir.model.universal.core.IdentifiedObject;
import de.emir.model.universal.physics.EnvironmentLayer;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "Environment", parent = IdentifiedObject.class)	
public interface Environment extends IdentifiedObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "crs", associationType = AssociationType.COMPOSITE, lowerBound = 1)
	public void setCrs(CoordinateReferenceSystem _crs);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "crs", associationType = AssociationType.COMPOSITE, lowerBound = 1)
	public CoordinateReferenceSystem getCrs();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "layer", associationType = AssociationType.COMPOSITE)
	public List<EnvironmentLayer> getLayer();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "children", associationType = AssociationType.COMPOSITE)
	public List<Environment> getChildren();
	
}
