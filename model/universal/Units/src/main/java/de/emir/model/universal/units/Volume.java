package de.emir.model.universal.units;

import de.emir.model.universal.units.Measure;
import de.emir.model.universal.units.VolumeUnit;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "Volume", parent = Measure.class)	
public interface Volume extends Measure 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY)
	public VolumeUnit getUnit();
	/**
	 *	@generated 
	 */
	double getAs(final VolumeUnit dst_unit);
	/**
	 *	@generated 
	 */
	void set(final double value, final VolumeUnit unit);
	/**
	 *	@generated 
	 */
	void set(final Volume other);
	/**
	 *	@generated 
	 */
	boolean smaller(final Volume other);
	/**
	 *	@generated 
	 */
	boolean greater(final Volume other);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY)
	public void setUnit(VolumeUnit _unit);
	/**
	 *	@generated 
	 */
	boolean equals(final Volume other);
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	String readableString();
	
}
