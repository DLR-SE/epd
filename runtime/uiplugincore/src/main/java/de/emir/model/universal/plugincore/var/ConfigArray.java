package de.emir.model.universal.plugincore.var;

import de.emir.model.universal.plugincore.ConfigVariable;
import de.emir.model.universal.plugincore.var.ConfigObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import java.util.List;

/**
 *	@generated 
 */
@UMLClass(parent = ConfigVariable.class)	
public interface ConfigArray extends ConfigVariable 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "array", associationType = AssociationType.PROPERTY)
	public List<ConfigObject> getArray();

	
}
