package de.emir.model.universal.units;

import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Measure;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 
 * The measure of distance as an integral, i.e. the limit of an infinite sum of distances between points on a curve.  For example the length of curve, the perimeter of a polygon as the length of the boundary.
 * @generated 
 */
@UMLClass(name = "Length", parent = Measure.class)	
public interface Length extends Measure 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setUnit(DistanceUnit _unit);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public DistanceUnit getUnit();
	/**
	 transform the value into the given unit, taking into account the current unit 
	 * @generated 
	 */
	double getAs(final DistanceUnit dst_unit);
	/**
	 *	@generated 
	 */
	void set(final double value, final DistanceUnit unit);
	/**
	 *	@generated 
	 */
	void set(final Length other);
	/**
	 *	@generated 
	 */
	String readableString();
	
	/**
	 *	@generated 
	 */
	boolean smaller(final Length other);
	/**
	 *	@generated 
	 */
	boolean greater(final Length other);
	/**
	 *	@generated 
	 */
	boolean equals(final Length other);
	/**
	 *	@generated 
	 */
	Length add(final Length other);
	/**
	 *	@generated 
	 */
	void addLocal(final Length other);
	/**
	 *	@generated 
	 */
	Length sub(final Length other);
	/**
	 *	@generated 
	 */
	void subLocal(final Length other);
	/**
	 *	@generated 
	 */
	Length divide(final double factor);
	/**
	 *	@generated 
	 */
	void divideLocal(final double factor);
	/**
	 *	@generated 
	 */
	Length multiply(final double factor);
	/**
	 *	@generated 
	 */
	void multiplyLocal(final double factor);
	/**
	 linear interpolation between two length's
	 * @generated 
	 */
	Length lerp(final Length other, final double factor);
	
}
