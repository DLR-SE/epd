package de.emir.rcp.properties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.AbstractProperty;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;
import de.emir.tuml.ucore.runtime.utils.QualifiedName;
import de.emir.tuml.ucore.runtime.utils.impl.QualifiedNameImpl;

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
            /* Old method, no subproperties for qualified names:
//          property = new GenericProperty<T>(name, description, editable, null);
//			property.setValue(defaultValue);
            */
            
            property = _getOrCreateProperty(QualifiedNameImpl.createWithRegEx(name, "\\."), description, editable, true);
            if (property.getValue() == null) {
                property.setValue(defaultValue);
            }
			properties.put(name, property);
			if (mListener != null)
				for (PropertyChangeListener pcl : mListener) {
					property.addPropertyChangeListener(pcl);
				}
		}
		
		return property;
		
	}
    
    private IProperty _getOrCreateProperty(QualifiedName qn, String desc, boolean editable, boolean create) {
        // returns one of the top level properties for this element, based on the qualified name. if the property does
        // not yet exits, it will be created
        if (qn == null || qn.isEmpty())
            throw new UnsupportedOperationException("require a valid property name");
        if (properties == null) {
            if (create)
                properties = new HashMap<>();
            else
                return null; // there are no properties available
        }

        IProperty prop = properties.get(qn.firstSegment());
        if (prop == null) {
            if (create) {
                ULog.trace("create new property: " + qn.firstSegment());
                prop = new GenericProperty(qn.firstSegment(), desc, editable);
                properties.put(qn.firstSegment(), prop);
            } else
                return null;
        }
        if (qn.numSegments() == 1)
            return prop;
        return _getOrCreateProperty(prop, qn.removeSegmentsFromStart(1), desc, editable, create);
    }

    private IProperty _getOrCreateProperty(IProperty parent, QualifiedName qn, String desc, boolean editable,
            boolean create) {
        // returns or creates a sub property of the parent property
        if (qn == null || qn.isEmpty())
            throw new UnsupportedOperationException("require a valid property name");
        if (parent == null || parent instanceof AbstractProperty == false)
            throw new NullPointerException("Require valid parent property");
        String n = qn.firstSegment();
        IProperty prop = null;
        if (parent.getSubProperties() != null) {
            for (Object sub_obj : parent.getSubProperties()) {
                IProperty<?> sub = (IProperty) sub_obj;
                if (sub.getName().equals(n)) {
                    prop = sub;
                    break;
                }
            }
        }
        if (prop == null) { // could not find an existing child, thus we create a new one
            if (create) {
                ULog.trace("create new sub property: " + n + " in parent: " + parent.getName());
                prop = new GenericProperty(qn.firstSegment(), desc, editable);
                ((AbstractProperty) parent).addChild(prop);
            } else
                return null;
        }
        if (qn.numSegments() == 1)
            return prop;
        return _getOrCreateProperty(prop, qn.removeSegmentsFromStart(1), desc, editable, create);// recursive call but
                                                                                                 // with shorter
                                                                                                 // qualified name
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
    @JsonIgnore
	public <T> T getValue(String name) {
		return (T)getProperty(name, null).getValue();
	}

    @JsonIgnore
	public void setValue(String name, Object value) {
		getProperty(name, null).setValue(value);
	}

    @JsonIgnore     
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
    
    /**
     * Add a complete property to the context uncluding possible subproperties.
     * 
     * @param property the property to add
     */
    public void addProperty(IProperty property) {
        properties.put(property.getName(), property); // TODO: automatically determine subproperties
    }
}
