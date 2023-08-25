package de.emir.model.universal.physics;
import de.emir.model.universal.physics.ICharacteristic;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLInterface;

/**
 An (I)Characteristic is used to specialize an PhysicalObject. 
 * The interface is meant to be empty and has to be filled by implementing classes.
 * @note ICharacteristics are identified (within a PhysicalObject) through its type (UClassifier). However, it
 * is possible to add more than one characteristic of the same type to an PhysicalObject
 * @generated 
 */
@UMLInterface(name = "ICharacteristic")
public interface ICharacteristic extends UObject 
{
	
	
	
}
