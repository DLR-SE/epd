package de.emir.rcp.properties;

import de.emir.tuml.ucore.runtime.prop.IProperty;

/**
 * 
 * @author sschweigert
 * \ingroup properties
 */
public interface IPropertyEditorProvider
{

	/**
	 * Provides a editor for the given property or returns null
	 * @param property property that shall be manipulated
	 * @return an editor that can display and manipulate the property or null
	 */
	public IPropertyEditor provide(IProperty property);
}
