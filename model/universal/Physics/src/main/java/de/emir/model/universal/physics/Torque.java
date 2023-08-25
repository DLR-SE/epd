package de.emir.model.universal.physics;

import de.emir.model.universal.math.Vector;
import de.emir.model.universal.units.DirectedMeasure;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = DirectedMeasure.class)	
public interface Torque extends DirectedMeasure 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "value", associationType = AssociationType.PROPERTY)
	public void setValue(Vector _value);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "value", associationType = AssociationType.PROPERTY)
	public Vector getValue();

	
}
