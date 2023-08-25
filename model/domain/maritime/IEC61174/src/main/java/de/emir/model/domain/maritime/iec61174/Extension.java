package de.emir.model.domain.maritime.iec61174;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass	
public interface Extension extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "manufactor", associationType = AssociationType.PROPERTY)
	public void setManufactor(String _manufactor);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "manufactor", associationType = AssociationType.PROPERTY)
	public String getManufactor();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "name", associationType = AssociationType.PROPERTY)
	public void setName(String _name);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "name", associationType = AssociationType.PROPERTY)
	public String getName();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "version", associationType = AssociationType.PROPERTY)
	public void setVersion(String _version);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "version", associationType = AssociationType.PROPERTY)
	public String getVersion();

	
}
