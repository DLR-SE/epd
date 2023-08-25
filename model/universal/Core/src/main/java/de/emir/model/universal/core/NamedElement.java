package de.emir.model.universal.core;

import de.emir.model.universal.core.RSIdentifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "NamedElement", isAbstract = true)	
public interface NamedElement extends UObject 
{
	/**
	 The primary name by which the object can be identified 
	 * @generated 
	 */
	@UMLProperty(name = "name", associationType = AssociationType.COMPOSITE, lowerBound = 1)
	public void setName(RSIdentifier _name);
	/**
	 The primary name by which the object can be identified 
	 * @generated 
	 */
	@UMLProperty(name = "name", associationType = AssociationType.COMPOSITE, lowerBound = 1)
	public RSIdentifier getName();
	
	/**
	 *	@generated 
	 */
	void setName(final String name);
	/**
	 @Brief: Convenience Method to check if the element has a valid name
	 * Checks if the element has a valid name. 
	 * A name is valid if: 
	 * - getName() != null
	 * - getName().getCode() != null 
	 * - getName().getCode().isEmpty() == false
	 * @return
	 * @generated 
	 */
	boolean hasValidName();
	/**
	 @Brief Convenience Method to return the code of this element 
	 * @return the value of getName().getCode() if hasValidName() returns true, null otherwise
	 * @generated 
	 */
	String getNameAsString();
	

}
