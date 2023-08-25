package de.emir.rcp.properties;

import java.util.Map;

import de.emir.tuml.ucore.runtime.prop.IProperty;

/**
 * 
 * @author sschweigert
 * \ingroup properties
 */
public interface IPropertyProvider
{
	
	/**
	 * contribute properties for the given (U)Object
	 * @param input object to create properties for
	 * @param properties map, containing the name of the property and the property itself
	 * @warn take care if a property with the same name has already been registered, otherwise the old property will be overwritten.
	 */
	public void contributeProperties(Object input, Map<String, IProperty> properties);
}
