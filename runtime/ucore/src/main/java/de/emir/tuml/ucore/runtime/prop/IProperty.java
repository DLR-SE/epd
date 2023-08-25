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

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.List;

import de.emir.tuml.ucore.runtime.IDisposable;

/**
 * Property. <br>
 * Component of a PropertySheet, based on the java.beans.PropertyDescriptor for easy wrapping of beans in PropertySheet.
 */
public interface IProperty<T> extends Serializable, Cloneable, IDisposable {

    public String getName();

    // public String getDisplayName();

    public String getDescription();

    public Class<?> getType();

    public T getValue();

    public void setValue(T value);

    public boolean isEditable();

    public String getCategory();

    public void addPropertyChangeListener(PropertyChangeListener listener);

    public void removePropertyChangeListener(PropertyChangeListener listener);

    public IProperty getParentProperty();

    public List<IProperty<?>> getSubProperties();

    /**
     * creates a deep copy of the property
     * 
     * @return
     */
    public IProperty copy();
}
