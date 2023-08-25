package de.emir.tuml.ucore.runtime.prop.internal;

import de.emir.tuml.ucore.runtime.IDisposable;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.prop.AbstractProperty;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class GenericProperty<T extends Object> extends AbstractProperty<T> implements IDisposable {

    /**
     * 
     */
    private static final long serialVersionUID = -8405591448562089600L;
    private final String 	mName;
    private final String 	mDescription;
    private final boolean 	mEditable;

    protected T 			mValue;
    
    
    
    //////////////// Optional created values ///////////////////
	private Pointer 		mPointer;
	private IDisposable		mPointerDisposable;

    public GenericProperty(String name, String description) {
        this(name, description, true);
    }

    public GenericProperty(final String name, final String description, final boolean editable) {
        this(name, description, editable, null);
    }

    public GenericProperty(final String name, final String description, final boolean editable, final T value) {
        super();
        mName = name;
        mDescription = description;
        mEditable = editable;

        mValue = value;
    }

    public GenericProperty(GenericProperty<T> _copy) {
        super(_copy);
        mName = _copy.mName;
        mDescription = _copy.mDescription;
        mEditable = _copy.mEditable;
        mValue = _copy.mValue;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }

    @Override
    public Class<?> getType() {
        return mValue != null ? mValue.getClass() : null;
    }

    @Override
    public T getValue() {
        return mValue;
    }

    @Override
    public void setValue(Object value) {
        if (value != mValue) {
            Object oldValue = mValue;
            mValue = (T) value;
            firePropertyChange(oldValue, mValue);
        }
    }

    @Override
    public boolean isEditable() {
        return mEditable;
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
    	if (mPointer == null) {
	    	Object value = getValue();
	    	if (value != null && value instanceof UObject) {
	    		mPointer = PointerOperations.create((UObject)value);
	    		mPointerDisposable = PointerOperations.observePointer(
                        mPointer,
                        (ITreeValueChangeListener) pl -> firePropertyChange(pl.getOldValue(), pl.getNewValue()
                        )
                );
	    	}
    	}
    	return mPointer;
    }
    
    
	@Override
	public void dispose() {
		super.dispose();
		if (mPointerDisposable != null)
			mPointerDisposable.dispose();
		mPointer = null;
		mPointerDisposable = null;
	}

	@Override
	public boolean isDisposed() {
		return super.isDisposed() || mPointerDisposable != null;
	}
    
    
    

}
