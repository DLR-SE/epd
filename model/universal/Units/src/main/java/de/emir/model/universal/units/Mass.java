package de.emir.model.universal.units;

import de.emir.model.universal.units.MassUnit;
import de.emir.model.universal.units.Measure;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "Mass", parent = Measure.class)	
public interface Mass extends Measure 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setUnit(MassUnit _unit);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public MassUnit getUnit();
	/**
	 
	 * returns the mass value with the provided unit 
	 * @generated 
	 */
	double getAs(final MassUnit unit);
	/**
	 *	@generated 
	 */
	void set(final double value, final MassUnit unit);
	/**
	 *	@generated 
	 */
	void set(final Mass other);
	/**
	 *	@generated 
	 */
	boolean smaller(final Mass other);
	/**
	 *	@generated 
	 */
	boolean greater(final Mass other);
	/**
	 *	@generated 
	 */
	boolean equals(final Mass other, final double epsilon);
	/**
	 *	@generated 
	 */
	Mass add(final Mass other);
	/**
	 *	@generated 
	 */
	void addLocal(final Mass other);
	/**
	 *	@generated 
	 */
	Mass sub(final Mass other);
	/**
	 *	@generated 
	 */
	void subLocal(final Mass other);
	/**
	 *	@generated 
	 */
	String readableString();
	
}
