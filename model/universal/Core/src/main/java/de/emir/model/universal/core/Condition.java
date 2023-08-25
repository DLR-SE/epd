package de.emir.model.universal.core;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLInterface;

/**

 * The state of an environment or situation in which a Performer performs
 * @generated 
 */
@UMLInterface(name = "Condition")
public interface Condition extends UObject 
{
	
	/**
	 *	@generated 
	 */
	boolean evaluate();
}
