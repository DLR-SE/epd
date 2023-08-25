package de.emir.model.universal.units;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import java.time.Instant;

/**
 *	@generated 
 */
@UMLClass(name = "Time", parent = Measure.class)	
public interface Time extends Measure, Comparable<Time>, java.io.Serializable
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setUnit(TimeUnit _unit);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public TimeUnit getUnit();
	/**
	 returns the time value in the given unit, taking into account the current unit 
	 * @generated 
	 */
	double getAs(final TimeUnit targetUnit);
	/**
	 *	@generated 
	 */
	void set(final double value, final TimeUnit unit);
	/**
	 *	@generated 
	 */
	void set(final Time value);
	/**
	 *	@generated 
	 */
	boolean smaller(final Time other);
	/**
	 *	@generated 
	 */
	boolean greater(final Time other);
	/**
	 compares the time with another one, allowing a small epsilon
	 * @returns false if the difference between both times, is more than the epsilon value
	 * @generated 
	 */
	boolean equals(final Time other, final double epsilon);
	/**
	 *	@generated 
	 */
	Time add(final Time other);
	/**
	 *	@generated 
	 */
	void addLocal(final Time other);
	/**
	 *	@generated 
	 */
	void addLocal(final double value, final TimeUnit unit);
	/**
	 *	@generated 
	 */
	Time sub(final Time other);
	/**
	 *	@generated 
	 */
	void subLocal(final Time other);
	/**
	 *	@generated 
	 */
	String readableString();
	
	/**
	 *	@generated 
	 */
	int getYear();
	
	/**
	 *	@generated 
	 */
	int getMonth();
	
	/**
	 *	@generated 
	 */
	int getDay();
	
	/**
	 *	@generated 
	 */
	int getHourOfDay();
	
	/**
	 *	@generated 
	 */
	int getMinuteOfHour();
	
	/**
	 *	@generated 
	 */
	int getSecondsOfMinute();
	/**
	 *	@generated 
	 */
	int getMillisecondsOfSecond();
	
	
	Instant getDateTime();
	
	
}
