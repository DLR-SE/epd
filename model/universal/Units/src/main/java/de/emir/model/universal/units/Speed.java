package de.emir.model.universal.units;

import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.Measure;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 
 * Undirected: distance divided by time
 * \source (oriented) ISO 19130
 * \note for the directed version see Velocity
 * @generated 
 */
@UMLClass(name = "Speed", parent = Measure.class)	
public interface Speed extends Measure 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setUnit(SpeedUnit _unit);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public SpeedUnit getUnit();
	/**
	 
	 * returns the speed in the provided unit 
	 * @generated 
	 */
	double getAs(final SpeedUnit unit);
	/**
	 *	@generated 
	 */
	void set(final double value, final SpeedUnit unit);
	/**
	 *	@generated 
	 */
	void set(final Speed speed);
	/**
	 *	@generated 
	 */
	boolean smaller(final Speed other);
	/**
	 *	@generated 
	 */
	boolean greater(final Speed other);
	/**
	 *	@generated 
	 */
	boolean equals(final Speed other, final double epsilon);
	/**
	 *	@generated 
	 */
	Speed add(final Speed other);
	/**
	 *	@generated 
	 */
	void addLocal(final Speed other);
	/**
	 *	@generated 
	 */
	Speed sub(final Speed other);
	/**
	 *	@generated 
	 */
	void subLocal(final Speed other);
	/**
	 *	@generated 
	 */
	String readableString();
	
	/**
	
	 * @deprecated use integrate instead
	 * @generated 
	 */
	Distance getDistanceOverTime(final Time etablished);
	/**
	 *	@generated 
	 */
	Distance integrate(final Time etablished);
	/**
	 *	@generated 
	 */
	Speed lerp(final Speed other, final double factor);
	
}
