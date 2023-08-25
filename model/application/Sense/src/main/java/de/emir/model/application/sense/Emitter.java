package de.emir.model.application.sense;

import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(isAbstract = true, parent = PhysicalObject.class)	
public interface Emitter extends PhysicalObject 
{
	/**
	 Area / Range of the emitter. Only sensors within this area shall be capable of receiving the emitted value
	 * if the areaOfInfluence is not defined, the emitted value is visible everywhere. 
	 * @generated 
	 */
	@UMLProperty(name = "areaOfInfluence", associationType = AssociationType.PROPERTY)
	public void setAreaOfInfluence(Geometry _areaOfInfluence);
	/**
	 Area / Range of the emitter. Only sensors within this area shall be capable of receiving the emitted value
	 * if the areaOfInfluence is not defined, the emitted value is visible everywhere. 
	 * @generated 
	 */
	@UMLProperty(name = "areaOfInfluence", associationType = AssociationType.PROPERTY)
	public Geometry getAreaOfInfluence();

	
}
