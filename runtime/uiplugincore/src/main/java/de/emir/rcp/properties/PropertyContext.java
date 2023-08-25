package de.emir.rcp.properties;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;

public class PropertyContext {

	private Map<String, IProperty<?>> properties = new HashMap<>();

	private ArrayList<PropertyChangeListener> 		mListener = new ArrayList<>();

	
	public <T> IProperty<T> getProperty(String name) {
		return getProperty(name, null);
	}
	
	public <T> IProperty<T> getProperty(String name, T defaultValue) {
		return getProperty(name, "", defaultValue);
	}
	
	public <T> IProperty<T> getProperty(String name, String description, T defaultValue) {
		return getProperty(name, description, true, defaultValue);		
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> IProperty<T> getProperty(String name, String description, boolean editable, T defaultValue) {
		
		IProperty<T> property = (IProperty<T>) properties.get(name);
		
		if(property == null) {
			
			property = new GenericProperty<T>(name, description, editable, null);
			property.setValue(defaultValue);
			properties.put(name, property);
			if (mListener != null)
				for (PropertyChangeListener pcl : mListener) {
					property.addPropertyChangeListener(pcl);
				}
		}
		
		return property;
		
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getValue(String name, T defaultValue) {
		
		IProperty property = getProperty(name, defaultValue);
		return property.getValue() == null ? defaultValue : (T) property.getValue();
	}
	
	public boolean hasProperty(String name) {
		return properties.get(name) != null;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getValue(String name) {
		return (T)getProperty(name, null).getValue();
	}

	public void setValue(String name, Object value) {
		getProperty(name, null).setValue(value);
	}

	public Collection<IProperty> getAllProperties() {
		return Collections.unmodifiableCollection(properties.values());
	}
	

	/**
	 * register a listener that will be delegated to all existing and future properties within this context
	 * @param listener
	 */
	public void registerListener(PropertyChangeListener listener) {
		if (listener == null) return ;
		if (mListener.contains(listener))
			return ;
		for (IProperty p : properties.values()){
			p.addPropertyChangeListener(listener);
		}
		mListener.add(listener);
	}
	
	/** 
	 * remove a listener from all properties within this context
	 * @param listener
	 */
	public void removeListener(PropertyChangeListener listener) {
		if (listener == null) return ;
		mListener.remove(listener);
		for (IProperty p : properties.values())
			p.removePropertyChangeListener(listener);
	}
}
