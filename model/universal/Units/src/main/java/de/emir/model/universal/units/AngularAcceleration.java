package de.emir.model.universal.units;

import de.emir.model.universal.units.AngularAccelerationUnit;
import de.emir.model.universal.units.AngularSpeed;
import de.emir.model.universal.units.Measure;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 
 * Undirected: AngularAcceleration is the rate of change of the angular velocity as a function of time.
 * In calculus terms, AngularAcceleration is the second derivative of angle with respect to time or, alternately, the first derivative of the angular velocity with respect to time.
 * The SI units for AngularAcceleration are rad / s^2 (radians per second squared or radians per second per second), however it may be defined using other combinations of units, as defined in AngularAccelerationUnit
 *    
 * \source (oriented) ISO 19130 - SD_UndirectedAngularAcceleration
 * \note for the directed version see AngularVelocity
 * @generated 
 */
@UMLClass(name = "AngularAcceleration", parent = Measure.class)	
public interface AngularAcceleration extends Measure 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setUnit(AngularAccelerationUnit _unit);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public AngularAccelerationUnit getUnit();
	/**
	 
	 * returns the angular acceleration in the provided unit 
	 * @generated 
	 */
	double getAs(final AngularAccelerationUnit unit);
	/**
	 *	@generated 
	 */
	void set(final double value, final AngularAccelerationUnit unit);
	/**
	 *	@generated 
	 */
	void set(final AngularAcceleration other);
	/**
	 *	@generated 
	 */
	boolean smaller(final AngularAcceleration other);
	/**
	 *	@generated 
	 */
	boolean greater(final AngularAcceleration other);
	/**
	 *	@generated 
	 */
	boolean equals(final AngularAcceleration other, final double epsilon);
	/**
	 *	@generated 
	 */
	AngularAcceleration add(final AngularAcceleration other);
	/**
	 *	@generated 
	 */
	void addLocal(final AngularAcceleration other);
	/**
	 *	@generated 
	 */
	AngularAcceleration sub(final AngularAcceleration other);
	/**
	 *	@generated 
	 */
	void subLocal(final AngularAcceleration other);
	/**
	 *	@generated 
	 */
	AngularSpeed getDeltaSpeedOverTime(final Time etablished);
	/**
	 *	@generated 
	 */
	String readableString();
	
}
