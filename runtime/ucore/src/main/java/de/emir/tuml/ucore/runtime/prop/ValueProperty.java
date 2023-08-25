package de.emir.tuml.ucore.runtime.prop;

import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;

public class ValueProperty<T> extends GenericProperty<T> {

	
	public ValueProperty(String name, String description) {
        super(name, description, true);
    }

    public ValueProperty(final String name, final String description, final boolean editable) {
        super(name, description, editable, null);
    }

    public ValueProperty(final String name, final String description, final boolean editable, final T value) {
    	super(name, description, editable, value);
    }

    public ValueProperty(ValueProperty<T> _copy) {
        super(_copy);
    }
}
