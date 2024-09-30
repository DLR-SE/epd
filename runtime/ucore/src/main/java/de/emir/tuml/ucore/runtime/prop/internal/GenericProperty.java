package de.emir.tuml.ucore.runtime.prop.internal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.emir.tuml.ucore.runtime.IDisposable;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.prop.AbstractProperty;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class GenericProperty<T> extends AbstractProperty<T> implements IDisposable {

    /**
     * 
     */
    private static final long serialVersionUID = -8405591448562089600L;
    private final String name;
    private final String description;
    private final boolean editable;

    protected T value;
    
    //////////////// Optional created values ///////////////////
	private Pointer 		pointer;
	private IDisposable		pointerDisposable;

    public GenericProperty(String name, String description) {
        this(name, description, true);
    }

    public GenericProperty(final String name, final String description, final boolean editable) {
        this(name, description, editable, null);
    }
                
    @JsonCreator
    public GenericProperty(final String name, final String description, final boolean editable, final T value) {
        super();
        this.name = name;
        this.description = description;
        this.editable = editable;

        this.value = value;
    }

    public GenericProperty(GenericProperty<T> _copy) {
        super(_copy);
        name = _copy.name;
        description = _copy.description;
        editable = _copy.editable;
        value = _copy.value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Class<?> getType() {
        return value != null ? value.getClass() : null;
    }
    
    public void setType(Class<?> type) {
        // do nothing
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        if (value != this.value) {
            Object oldValue = this.value;
            this.value = (T) value;
            firePropertyChange(oldValue, this.value);
        }
    }

    @Override
    public boolean isEditable() {
        return editable;
    }

    @Override
    public String getCategory() {
        // TODO
        return null;
    }

    @Override
    public IProperty copy() {
        return new GenericProperty(this);
    }

    public T get() {
        return getValue();
    }

    public void set(T value) {
        setValue(value);
    }

    public Pointer getPointer() {
    	if (pointer == null) {
	    	Object value = getValue();
	    	if (value != null && value instanceof UObject) {
	    		pointer = PointerOperations.create((UObject)value);
	    		pointerDisposable = PointerOperations.observePointer(
                        pointer,
                        (ITreeValueChangeListener) pl -> firePropertyChange(pl.getOldValue(), pl.getNewValue()
                        )
                );
	    	}
    	}
    	return pointer;
    }
    
    
	@Override
	public void dispose() {
		super.dispose();
		if (pointerDisposable != null)
			pointerDisposable.dispose();
		pointer = null;
		pointerDisposable = null;
	}

	@Override
	public boolean isDisposed() {
		return super.isDisposed() || pointerDisposable != null;
	}
    
    
    

}
