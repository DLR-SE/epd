package de.emir.model.universal.physics.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.physics.Characteristic;
import de.emir.model.universal.physics.ICharacteristic;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**

						 * Abstract implementation of an ICharacteristics, mainly used for backward compatibility 
 * @generated 
 */
@UMLImplementation(classifier = Characteristic.class)
abstract public class CharacteristicImpl extends UObjectImpl implements Characteristic , ICharacteristic 
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public CharacteristicImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public CharacteristicImpl(final Characteristic _copy) {
	}
	
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PhysicsPackage.Literals.Characteristic;
	}
	
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "CharacteristicImpl{" +
		"}";
	}
}
