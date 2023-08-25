package de.emir.model.universal.physics;

import de.emir.model.universal.math.Vector;
import de.emir.model.universal.physics.Torque;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.DirectedMeasure;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = DirectedMeasure.class)	
public interface Force extends DirectedMeasure 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "direction", associationType = AssociationType.PROPERTY)
	public void setDirection(Vector _direction);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "direction", associationType = AssociationType.PROPERTY)
	public Vector getDirection();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "origin", associationType = AssociationType.PROPERTY)
	public void setOrigin(Coordinate _origin);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "origin", associationType = AssociationType.PROPERTY)
	public Coordinate getOrigin();

	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	double getMagnitude();
	
	/**
	 *	@generated 
	 */
	Torque getTorque(final Coordinate atPosition);
	
	/**
	 *	@generated 
	 */
	Force getForceAtPosition(final Coordinate atPosition);
	
}
