/*
 * Copyright 2015 Matthew Aguirre
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.emir.tuml.ucore.runtime.prop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * AbstractProperty. <br>
 *
 */
public abstract class AbstractProperty<T> implements IProperty<T> {

    // PropertyChangeListeners are not serialized.
    private transient PropertyChangeSupport listeners = new PropertyChangeSupport(this);

    private IProperty 						parentProperty = null;
    private List<IProperty<?>> 				childProperties = null;

    public AbstractProperty() {
    }

    public AbstractProperty(AbstractProperty<T> _copy) {
        if (_copy.childProperties != null) {
            childProperties = new ArrayList<>();
            for (IProperty _p : _copy.childProperties)
                childProperties.add(_p.copy());
        }
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        listeners.addPropertyChangeListener(listener);
        Collection<IProperty<?>> subProperties = getSubProperties();
        if (subProperties != null) {
            for (IProperty sub : subProperties) {
                if (sub == null) {
                    continue;
                }
                sub.addPropertyChangeListener(listener);
            }
        }
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        listeners.removePropertyChangeListener(listener);
        Collection<IProperty<?>> subProperties = getSubProperties();
        if (subProperties != null) {
            for (IProperty sub : subProperties)
                sub.removePropertyChangeListener(listener);
        }
    }

    public void firePropertyChange(Object oldValue, Object newValue) {
        listeners.firePropertyChange("value", oldValue, newValue);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        listeners = new PropertyChangeSupport(this);
    }

    @Override
    @JsonIgnore
    public IProperty getParentProperty() {
        return parentProperty;
    }

    @JsonIgnore
    public void setParentProperty(IProperty prop) {
        if (parentProperty != null && parentProperty != prop)
            ((AbstractProperty) parentProperty)._removeChild(this);
        parentProperty = prop;
        if (parentProperty != null && parentProperty instanceof AbstractProperty)
            ((AbstractProperty) parentProperty)._addChild(this);
    }

    public void addChild(IProperty prop) {
        if (prop instanceof AbstractProperty) {
            ((AbstractProperty) prop).setParentProperty(this);
            _addChild((AbstractProperty) prop);
        } else
            childProperties.add(prop);
    }

    public void removeChild(IProperty obj) {
        if (childProperties != null)
            childProperties.remove(obj);
    }

    /**
     * removes a child, without notifing the child about the changed parent normally this method is called by the child
     * when a new parent is set
     * 
     * @param child
     */
    private void _removeChild(AbstractProperty child) {
        if (childProperties != null)
            childProperties.remove(child);
    }

    /**
     * adds a new child to the internal list (creates the list if required) but only if the child is not yet part of the
     * child list (e.g. the child will be added only once). this method does not change the parent of the child, since
     * it is normally called from the setParentProperty of the child
     * 
     * @param child
     */
    private void _addChild(AbstractProperty child) {
        if (childProperties == null)
            childProperties = new ArrayList<>();
        if (!childProperties.contains(child))
            childProperties.add(child);
    }

    @Override
    public List<IProperty<?>> getSubProperties() {
        return childProperties;
    }

    @Override
    @JsonIgnore
    public boolean isDisposed() {
    	return listeners == null || listeners.getPropertyChangeListeners().length == 0;
    }
    @Override
    public void dispose() {
    	while(listeners.getPropertyChangeListeners().length > 0)
    		removePropertyChangeListener(listeners.getPropertyChangeListeners()[0]);
    }
    
    @Override
    public String toString() {
        return "Property [" + getName() + "]";
    }
}
