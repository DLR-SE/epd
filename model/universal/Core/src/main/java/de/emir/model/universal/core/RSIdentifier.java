package de.emir.model.universal.core;

import de.emir.model.universal.core.MDIdentifier;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "RSIdentifier", parent = MDIdentifier.class)	
public interface RSIdentifier extends MDIdentifier 
{
	/**
	 which is a version for the identifier  
	 * @generated 
	 */
	@UMLProperty(name = "version", associationType = AssociationType.PROPERTY)
	public void setVersion(String _version);
	/**
	 which is a version for the identifier  
	 * @generated 
	 */
	@UMLProperty(name = "version", associationType = AssociationType.PROPERTY)
	public String getVersion();
	/**
	 which unambiguously defines the namespace for the identifier  
	 * @generated 
	 */
	@UMLProperty(name = "codeSpace", associationType = AssociationType.PROPERTY)
	public void setCodeSpace(String _codeSpace);
	/**
	 which unambiguously defines the namespace for the identifier  
	 * @generated 
	 */
	@UMLProperty(name = "codeSpace", associationType = AssociationType.PROPERTY)
	public String getCodeSpace();

	
}
