package de.emir.tuml.ucore.runtime.prop;

import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;

public class NullableValueProperty<T extends Object>  extends GenericProperty<T> implements INullableProperty<T>{

	private Class<?> 	mClass;
	
	public NullableValueProperty(String name, String description, boolean editable, T value) {
		super(name, description, editable, value);
		if (value == null)
			throw new IllegalArgumentException("Value may not be null - use other constructor for this feature");
		mClass = value.getClass();
	}
	public NullableValueProperty(String name, String description, boolean editable, Class<T> clazz) {
		super(name, description, editable, null);
		mClass = clazz;
	}

	public NullableValueProperty(NullableValueProperty<T> _copy) {
		super(_copy);
		mClass = _copy.mClass; 
	}
	
	
	@Override
	public Class<?> getType() {
		if (mValue == null)
			return mClass;
		return mValue.getClass();
	}
	
	

	
}
