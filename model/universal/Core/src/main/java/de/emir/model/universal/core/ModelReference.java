package de.emir.model.universal.core;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLInterface;
import de.emir.tuml.ucore.runtime.utils.Pointer;

/**
 *	@generated 
 */
@UMLInterface(name = "ModelReference")
public interface ModelReference extends UObject 
{
	
	
	
	/**
	
	 * returns a pointer to the referenced object or null if the object could not be found or there is any other 
	 * error within the configuration of the ModelReference 
	 * @returns a pointer to the referenced element or null
	 * @generated 
	 */
	Pointer getPointer();
}
