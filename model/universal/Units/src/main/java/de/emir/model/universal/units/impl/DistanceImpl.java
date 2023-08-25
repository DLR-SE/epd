package de.emir.model.universal.units.impl;

import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.units.impl.LengthImpl;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**

 * Used as a type for returning distances and possibly lengths. Care must be taken when using distance where length is meant. The distance from start to end of a curve is not the length of the curve, but represents the length of the shortest curve between these two points. Since Distance is a length of some curve (albeit a hypothetical one), the unit of measure is the same.
 * @generated 
 */
@UMLImplementation(classifier = Distance.class)
public class DistanceImpl extends LengthImpl implements Distance  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public DistanceImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public DistanceImpl(final Distance _copy) {
		super(_copy);
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public DistanceImpl(double _value, DistanceUnit _unit) {
		super(_value,_unit);
	}
	
	public DistanceImpl(Length l) {
		this(l.getValue(), l.getUnit());
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.Distance;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Distance lerp(final Distance other, final double factor)
	{
		if (other == null)
			return new DistanceImpl(this);
		return new DistanceImpl(lerp(getValue(), other.getAs(getUnit()), factor), getUnit());
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "DistanceImpl{" +
		" value = " + getValue() + 
		"}";
	}

	
}
