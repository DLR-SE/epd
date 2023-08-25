package de.emir.model.universal.units;

import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngularSpeedUnit;
import de.emir.model.universal.units.Measure;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 
 * Undirected: angular distance divided by time
 * \source (oriented) ISO 19130
 * \note for the directed version see AngularVelocity
 * @generated 
 */
@UMLClass(name = "AngularSpeed", parent = Measure.class)	
public interface AngularSpeed extends Measure 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setUnit(AngularSpeedUnit _unit);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public AngularSpeedUnit getUnit();
	/**
	 
	 * returns the angular speed in the provided unit 
	 * @generated 
	 */
	double getAs(final AngularSpeedUnit unit);
	/**
	 *	@generated 
	 */
	void set(final double value, final AngularSpeedUnit unit);
	/**
	 *	@generated 
	 */
	void set(final AngularSpeed value);
	/**
	 *	@generated 
	 */
	boolean smaller(final AngularSpeed other);
	/**
	 *	@generated 
	 */
	boolean greater(final AngularSpeed other);
	/**
	 *	@generated 
	 */
	boolean equals(final AngularSpeed other, final double epsilon);
	/**
	 *	@generated 
	 */
	AngularSpeed add(final AngularSpeed other);
	/**
	 *	@generated 
	 */
	void addLocal(final AngularSpeed other);
	/**
	 *	@generated 
	 */
	AngularSpeed sub(final AngularSpeed other);
	/**
	 *	@generated 
	 */
	void subLocal(final AngularSpeed other);
	/**
	
	 * @Deprecated use integrate(etablished) instead
	 * @generated 
	 */
	Angle getDeltaAngleOverTime(final Time etablished);
	/**
	 *	@generated 
	 */
	Angle integrate(final Time etablished);
	/**
	 *	@generated 
	 */
	String readableString();
	
}
