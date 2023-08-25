package de.emir.rcp.properties;

import java.awt.Component;
import java.beans.PropertyChangeListener;

import de.emir.tuml.ucore.runtime.prop.IProperty;

public interface IPropertyEditor
{	
	public void setProperty(IProperty property);
	
	public IProperty getProperty();
	
	
	/**
	 * returns the editor for the property
	 * @return
	 */
	public Component getEditor();

	/**
	 * disables the editor as well
	 * this method will be called, right before the editor component is disposed as well
	 */
	public void dispose();

	/**
	 * forwards a property change listener to the property, but takes care, that the listener will be removed, as soon as the 
	 * component has been remvoed, the listener will also be removed
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener);

	
}
