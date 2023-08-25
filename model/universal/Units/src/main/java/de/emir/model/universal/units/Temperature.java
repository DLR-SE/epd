package de.emir.model.universal.units;

import de.emir.model.universal.units.Measure;
import de.emir.model.universal.units.TemperatureUnit;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = Measure.class)	
public interface Temperature extends Measure 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY)
	public void setUnit(TemperatureUnit _unit);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY)
	public TemperatureUnit getUnit();

	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	double getAs(final TemperatureUnit unit);
	
	/**
	 *	@generated 
	 */
	void set(final double value, final TemperatureUnit unit);
	
	/**
	 *	@generated 
	 */
	void set(final Temperature other);
	
	/**
	 *	@generated 
	 */
	Temperature add(final Temperature other);
	
	/**
	 *	@generated 
	 */
	Temperature add(final double value, final TemperatureUnit unit);
	
	/**
	 *	@generated 
	 */
	void addLocal(final Temperature other);
	
	/**
	 *	@generated 
	 */
	void addLocal(final double value, final TemperatureUnit unit);
	
	/**
	 *	@generated 
	 */
	Temperature sub(final Temperature other);
	
	/**
	 *	@generated 
	 */
	Temperature sub(final double value, final TemperatureUnit unit);
	
	/**
	 *	@generated 
	 */
	void subLocal(final Temperature other);
	
	/**
	 *	@generated 
	 */
	void subLocal(final double value, final TemperatureUnit unit);
	
}
