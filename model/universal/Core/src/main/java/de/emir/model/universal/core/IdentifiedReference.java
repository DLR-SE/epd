package de.emir.model.universal.core;

import de.emir.model.universal.core.ModelReference;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass	
public interface IdentifiedReference extends ModelReference 
{
	/**
	 contains the code of an RSIdentifier of an NamedElement 
	 * @generated 
	 */
	@UMLProperty(name = "referencedIdentifier", associationType = AssociationType.PROPERTY)
	public void setReferencedIdentifier(String _referencedIdentifier);
	/**
	 contains the code of an RSIdentifier of an NamedElement 
	 * @generated 
	 */
	@UMLProperty(name = "referencedIdentifier", associationType = AssociationType.PROPERTY)
	public String getReferencedIdentifier();

	
}
