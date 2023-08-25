package de.emir.model.universal.physics.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.physics.Capability;
import de.emir.model.universal.physics.ICapability;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**

 * Abstract implementation of an ICapability used for backward compatibility
 * @generated 
 */
@UMLImplementation(classifier = Capability.class)
abstract public class CapabilityImpl extends UObjectImpl implements Capability , ICapability 
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public CapabilityImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public CapabilityImpl(final Capability _copy) {
	}
	
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PhysicsPackage.Literals.Capability;
	}
	
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "CapabilityImpl{" +
		"}";
	}
}
