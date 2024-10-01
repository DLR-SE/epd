package de.emir.model.universal.plugincore.var;

import de.emir.model.universal.plugincore.ConfigVariable;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(isAbstract = true, parent = ConfigVariable.class)	
public interface AbstractKeyBinding extends ConfigVariable 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "commandID", associationType = AssociationType.PROPERTY)
	public void setCommandID(String _commandID);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "commandID", associationType = AssociationType.PROPERTY)
	public String getCommandID();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "key", associationType = AssociationType.PROPERTY)
	public void setKey(String _key);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "key", associationType = AssociationType.PROPERTY)
	public String getKey();

	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	boolean equals(final Object obj);
	/**
	 *	@generated 
	 */
	AbstractKeyBinding copy();
	
}
