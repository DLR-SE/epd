package de.emir.model.domain.maritime.vessel.impl;

import de.emir.model.domain.maritime.vessel.VesselCharacteristic;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.physics.impl.CharacteristicImpl;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = VesselCharacteristic.class)
abstract public class VesselCharacteristicImpl extends CharacteristicImpl implements VesselCharacteristic  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public VesselCharacteristicImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public VesselCharacteristicImpl(final VesselCharacteristic _copy) {
		super(_copy);
	}
	
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.VesselCharacteristic;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "VesselCharacteristicImpl{" +
		"}";
	}
}
