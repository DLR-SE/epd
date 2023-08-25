package de.emir.model.universal.units;

import de.emir.model.universal.units.AccelerationUnit;
import de.emir.model.universal.units.Measure;
import de.emir.model.universal.units.Speed;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 
 * Undirected: Acceleration is the rate of change of velocity as a function of time.
 * In calculus terms, acceleration is the second derivative of position with respect to time or, alternately, the first derivative of the velocity with respect to time.
 * The SI units for acceleration are m / s^2 (meters per second squared or meters per second per second), however it may be defined using other combinations of units, as defined in AccelerationUnit
 *    
 * \source (oriented) ISO 19130 - SD_UndirectedAcceleration
 * \note for the directed version see Velocity
 * @generated 
 */
@UMLClass(name = "Acceleration", parent = Measure.class)	
public interface Acceleration extends Measure 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setUnit(AccelerationUnit _unit);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public AccelerationUnit getUnit();
	/**
	 
	 * returns the acceleration in the provided unit 
	 * @generated 
	 */
	double getAs(final AccelerationUnit unit);
	/**
	 *	@generated 
	 */
	void set(final double value, final AccelerationUnit unit);
	/**
	 *	@generated 
	 */
	void set(final Acceleration other);
	/**
	 *	@generated 
	 */
	boolean smaller(final Acceleration other);
	/**
	 *	@generated 
	 */
	boolean greater(final Acceleration other);
	/**
	 *	@generated 
	 */
	boolean equals(final Acceleration other, final double epsilon);
	/**
	 *	@generated 
	 */
	Acceleration add(final Acceleration other);
	/**
	 *	@generated 
	 */
	void addLocal(final Acceleration other);
	/**
	 *	@generated 
	 */
	Acceleration sub(final Acceleration other);
	/**
	 *	@generated 
	 */
	void subLocal(final Acceleration other);
	/**
	 *	@generated 
	 */
	Speed getDeltaSpeedOverTime(final Time etablished);
	/**
	 *	@generated 
	 */
	String readableString();
	
}
