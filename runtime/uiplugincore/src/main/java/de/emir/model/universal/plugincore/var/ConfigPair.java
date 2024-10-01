package de.emir.model.universal.plugincore.var;

import de.emir.model.universal.plugincore.ConfigVariable;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = ConfigVariable.class)	
public interface ConfigPair extends ConfigVariable 
{
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
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "value", associationType = AssociationType.PROPERTY)
	public void setValue(ConfigVariable _value);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "value", associationType = AssociationType.PROPERTY)
	public ConfigVariable getValue();

	
}
