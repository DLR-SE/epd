package de.emir.model.universal.physics;

import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.physics.LocatableObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 Convinience CRS that follows the referenced LocatableObject and thus creates
 * a stable coordinate reference system on top of the referenced object. 
 * @generated 
 */
@UMLClass(parent = Engineering2D.class)	
public interface RelativeEngineering2D extends Engineering2D 
{
	/**
	 locatable object, used to resolve the origin and orientation offset for this local coordinate reference system 
	 * @generated 
	 */
	@UMLProperty(name = "reference", associationType = AssociationType.SHARED)
	public void setReference(LocatableObject _reference);
	/**
	 locatable object, used to resolve the origin and orientation offset for this local coordinate reference system 
	 * @generated 
	 */
	@UMLProperty(name = "reference", associationType = AssociationType.SHARED)
	public LocatableObject getReference();
	
}
