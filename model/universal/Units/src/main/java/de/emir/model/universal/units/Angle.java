package de.emir.model.universal.units;

import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.Measure;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * The amount of rotation needed to bring one line or plane into coincidence with another, generally measured in radians or degrees.
 * @generated 
 */
@UMLClass(name = "Angle", parent = Measure.class)	
public interface Angle extends Measure 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setUnit(AngleUnit _unit);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public AngleUnit getUnit();
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	void normalizeLocal();
	
	/**
	 *	@generated 
	 */
	Angle normalize();
	/**
	 normalize the Angle to an interval between -180ï¿½ and 180ï¿½ or -PI to PI 
	 * @generated 
	 */
	void normalize180Local();
	/**
	 normalize the Angle to an interval between -180ï¿½ and 180ï¿½ or -PI to PI 
	 * @generated 
	 */
	Angle normalize180();
	/**
	
	 * @deprecated use getAs(unit) instead
	 * @generated not 
	 */
	@Deprecated
	double get(final AngleUnit unit);
	/**
	 *	@generated 
	 */
	double getAs(final AngleUnit unit);
	/**
	 *	@generated 
	 */
	void set(final double value, final AngleUnit unit);
	/**
	 *	@generated 
	 */
	void set(final Angle other);
	/**
	 *	@generated 
	 */
	Angle add(final Angle other);
	/**
	 *	@generated 
	 */
	Angle add(final double value, final AngleUnit unit);
	/**
	 *	@generated 
	 */
	void addLocal(final Angle other);
	/**
	 *	@generated 
	 */
	void addLocal(final double value, final AngleUnit unit);
	/**
	 *	@generated 
	 */
	Angle sub(final Angle other);
	/**
	 *	@generated 
	 */
	void subLocal(final Angle other);
	/**
	 *	@generated 
	 */
	String readableString();
	/**
	 *	@generated 
	 */
	Angle sub(final double value, final AngleUnit unit);
	/**
	 *	@generated 
	 */
	void subLocal(final double value, final AngleUnit unit);
	
}
