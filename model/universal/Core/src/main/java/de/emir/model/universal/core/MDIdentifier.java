package de.emir.model.universal.core;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 Identifier from ISO 19115-3 Metadata
 * \note for now the CI_Citation (also ISO 19115-3) is missing and should be added if required
 * @generated 
 */
@UMLClass(name = "MDIdentifier")	
public interface MDIdentifier extends UObject 
{
	/**
	 Identifier code or name, often from a controlled list or pattern defined by a code space.  
	 * @generated 
	 */
	@UMLProperty(name = "code", associationType = AssociationType.PROPERTY)
	public void setCode(String _code);
	/**
	 Identifier code or name, often from a controlled list or pattern defined by a code space.  
	 * @generated 
	 */
	@UMLProperty(name = "code", associationType = AssociationType.PROPERTY)
	public String getCode();

	
}
