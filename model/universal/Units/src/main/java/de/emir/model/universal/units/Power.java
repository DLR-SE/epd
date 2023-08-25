package de.emir.model.universal.units;

import de.emir.model.universal.units.Measure;
import de.emir.model.universal.units.PowerUnit;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = Measure.class)	
public interface Power extends Measure 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY)
	public void setUnit(PowerUnit _unit);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY)
	public PowerUnit getUnit();

	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	double getAs(final PowerUnit dst_unit);
	
	/**
	 *	@generated 
	 */
	void set(final double value, final PowerUnit unit);
	
	/**
	 *	@generated 
	 */
	void set(final Power other);
	
	/**
	 *	@generated 
	 */
	boolean smaller(final Power other);
	
	/**
	 *	@generated 
	 */
	boolean greater(final Power other);
	
	/**
	 *	@generated 
	 */
	boolean equals(final Power other);
	
}
