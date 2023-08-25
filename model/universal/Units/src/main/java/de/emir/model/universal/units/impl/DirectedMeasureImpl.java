package de.emir.model.universal.units.impl;

import de.emir.model.universal.units.DirectedMeasure;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = DirectedMeasure.class)
abstract public class DirectedMeasureImpl extends UObjectImpl implements DirectedMeasure  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public DirectedMeasureImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public DirectedMeasureImpl(final DirectedMeasure _copy) {
	}
	
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.DirectedMeasure;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "DirectedMeasureImpl{" +
		"}";
	}
}
