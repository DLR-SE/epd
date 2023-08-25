package de.emir.model.universal.units;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * The result from performing the act or process of ascertaining the extent, dimensions, or quantity of some entity.
 * A measure is always associated to a unit of measure. Ratio measures where the two base measures are in the same units (often considered unit-less) should be associated to UnitOfMeasure (same meter/meter for map scale) so that conversions to non-unitless ratios can be accomplished (such as miles/inch).
 * @generated 
 */
@UMLClass(name = "Measure", isAbstract = true)	
public interface Measure extends UObject 
{
	/**
	 The numerical value quantifying the extent or size of some quantity, in the units specified by the associated UnitOfMeasure class.*
	 * @generated 
	 */
	@UMLProperty(name = "value", associationType = AssociationType.PROPERTY)
	public void setValue(double _value);
	/**
	 The numerical value quantifying the extent or size of some quantity, in the units specified by the associated UnitOfMeasure class.*
	 * @generated 
	 */
	@UMLProperty(name = "value", associationType = AssociationType.PROPERTY)
	public double getValue();
	/**
	 rounds the result to the given decimal values
	 * e.g. if numDecimals == 2 and value == 1.22392 => 1.22 == value
	 * @return the old and accurate (not rounded) value
	 * @generated 
	 */
	double roundLocal(final int numDecimals);

	
}
