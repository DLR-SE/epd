package de.emir.model.universal.core;

import de.emir.model.universal.core.Observer;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import de.emir.tuml.ucore.runtime.utils.Pointer;

/**
 *	@generated 
 */
@UMLClass(parent = Observer.class)	
public interface MemberObserver extends Observer 
{
	/**
	 textual description from the instance, using the features (and list indices) to the 
	 * required / observed feature
	 * @note the pointerString shall follow this BNF: <FeatureName>(:<ListIndex>)?(,<FeatureName>(:<ListIndex>)?)*
	 * @generated 
	 */
	@UMLProperty(name = "pointerString", associationType = AssociationType.PROPERTY)
	public void setPointerString(String _pointerString);
	/**
	 textual description from the instance, using the features (and list indices) to the 
	 * required / observed feature
	 * @note the pointerString shall follow this BNF: <FeatureName>(:<ListIndex>)?(,<FeatureName>(:<ListIndex>)?)*
	 * @generated 
	 */
	@UMLProperty(name = "pointerString", associationType = AssociationType.PROPERTY)
	public String getPointerString();

	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	
	 * Checks the pointerString, if it follows the required grammar and can create a valid pointer. 
	 * @generated 
	 */
	boolean checkPointerString();
	
	/**
	
	 * Creates a pointer from the given information, e.g. parses the pointerString depending on the 
	 * root instance. 
	 * @returns a pointer to the referenced element or null, if either the pointerString is invalid or the rootInstance is not set
	 * @generated 
	 */
	Pointer getPointer();
	
}
