package de.emir.tuml.ucore.runtime.prop;

import java.beans.PropertyChangeListener;
import java.util.HashMap;

import de.emir.tuml.ucore.runtime.IDisposable;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UModelElement;
import de.emir.tuml.ucore.runtime.UNamedElement;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.impl.ObjectPointerImpl;

public class PointerProperty extends AbstractProperty<Object> implements IDisposable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3982043132765964321L;
	
	
	
	private Pointer 										mPointer;
	private HashMap<PropertyChangeListener, IDisposable> 	mListenerMap = new HashMap<>();

	public PointerProperty(UObject instance, UStructuralFeature feature, int listIndex) {
		this(PointerOperations.create(instance, feature, listIndex));
	}
	
	public PointerProperty(Pointer ptr) {
		mPointer = ptr;
	}

	@Override
	public String getName() {
        if (mPointer instanceof ObjectPointerImpl) {
            if (mPointer.getUObject() instanceof UNamedElement) {
                return ((UNamedElement) mPointer.getUObject()).getName();
            }
            else return mPointer.getUObject().getUClassifier().getName();
        }
		return mPointer.getPointedFeature().getName();
	}


	@Override
	public String getDescription() {
        if (mPointer instanceof ObjectPointerImpl) {
            if (mPointer.getUObject() instanceof UModelElement) {
                return ((UModelElement) mPointer.getUObject()).getDocumentation();
            }
            else return mPointer.getUObject().getUClassifier().getDocumentation();
        }
		return mPointer.getPointedFeature().getDocumentation();
	}
	

	@Override
	public Class<?> getType() {
		UType type = mPointer.getType();
		if (type == null) 
			type = mPointer.getExpectedType();
		return type.getRepresentingClass();
	}

	@Override
	public Object getValue() {
		return mPointer.getValue();
	}

	@Override
	public void setValue(Object value) {
		mPointer.setValue(value);
	}

	@Override
	public boolean isEditable() {
        if (mPointer instanceof ObjectPointerImpl) {
            if (mPointer.getUObject() instanceof UModelElement) {
                return ((UModelElement) mPointer.getUObject()).isFrozen();
            }
        }
		return mPointer.getPointedFeature().isReadOnly() == false;
	}

	@Override
	public String getCategory() {
        if (mPointer instanceof ObjectPointerImpl) {
            if (mPointer.getUObject() instanceof UModelElement) {
                return ((UModelElement) mPointer.getUObject()).getPackage().getName();
            }
        }
		return mPointer.getPointedFeature().getOwner().getName();
	}

	public Pointer getPointer() {
		return mPointer;
	}
	
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		super.addPropertyChangeListener(listener);	
		if (getPointer() != null && getPointer().isValid()){
			IValueChangeListener vcl = new IValueChangeListener() {
				@Override
				public void onValueChange(Notification notification) {
					firePropertyChange(notification.getOldValue(), notification.getNewValue());
				}
			};
			IDisposable disp = PointerOperations.observePointer(getPointer(), vcl);
			mListenerMap.put(listener, disp);
		}
	}
	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		IDisposable disp = mListenerMap.remove(listener);
		if (disp != null) {
			disp.dispose();
		}else {
			ULog.error("Failed to remove PropertyChangeListener: may not registered?");
		}
		super.removePropertyChangeListener(listener);
	}

	@Override
	public IProperty copy() {
		return new PointerProperty(getPointer());
	}

	@Override
	public void dispose() {
		super.dispose();
		for (IDisposable disp : mListenerMap.values()) {
			disp.dispose();
		}
		mListenerMap.clear();
	}

	@Override
	public boolean isDisposed() {
		return false;
	}

}
