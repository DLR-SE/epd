package de.emir.model.universal.units.impl;

import de.emir.model.universal.units.Percentage;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.model.universal.units.impl.MeasureImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Percentage.class)
public class PercentageImpl extends MeasureImpl implements Percentage  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public PercentageImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public PercentageImpl(final Percentage _copy) {
		super(_copy);
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public PercentageImpl(double _value) {
		super(_value);
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.Percentage;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "PercentageImpl{" +
		" value = " + getValue() + 
		"}";
	}
}
