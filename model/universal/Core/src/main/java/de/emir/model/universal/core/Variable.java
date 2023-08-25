package de.emir.model.universal.core;

import de.emir.model.universal.core.IdentifiedObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**

 * An Variable can be used to store (or reference) a single value. 
 * The variable's name and documentation are to be taken from the IdentifiedObject
 * @generated 
 */
@UMLClass(isAbstract = true, parent = IdentifiedObject.class)	
public interface Variable extends IdentifiedObject 
{
	
	/**
	
	 * This method expect the value to be in the correct type, otherwise it may throws an Exception
	 * @generated 
	 */
	void setObjectValue(final Object value);

	/**
	 *	@generated 
	 */
	Object getValueAsObject();
	
}
