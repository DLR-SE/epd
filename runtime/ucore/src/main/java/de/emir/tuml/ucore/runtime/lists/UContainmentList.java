package de.emir.tuml.ucore.runtime.lists;

import java.util.ArrayList;
import java.util.Collection;

import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.impl.NotificationImpl;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;

public class UContainmentList<E extends UObject> extends ArrayList<E> {

    private static final String WEAK_PROPERTY = "WeakProperty";
    
    
	private UObject 				mOwner;
    private UStructuralFeature 		mOwnedFeature;
    private boolean					mIsWeakProperty;

    UContainmentList() {
    }

    protected UContainmentList(UObject owner) {
        if (owner == null)
            throw new NullPointerException("Owner may not be null");
        mOwner = owner;
    }

    public UContainmentList(UObject owner, UStructuralFeature feature) {
        this(owner);
        if (feature == null) {
            throw new NullPointerException("Feature may not be null");
        }
        setOwningFeature(feature);
    }

    @Override
    public boolean add(E element) {
        return _internalAdd(size(), element);
    }

    @Override
    public void add(int index, E element) {
        _internalAdd(index, element);
    }

    @Override
    public E remove(int index) {
        return _internalRemove(index);
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index < 0 || index > size())
            return false;
        return remove(index) != null;
    }

    @Override
    public boolean removeAll(Collection<?> list) {
        if (list != null) {
            for (Object e : list)
                ((UObjectImpl) e)._internalGetInternalUObject().removeFromContainer(null);
        } else
            return false;

        boolean result = super.removeAll(list);
        if (result) {
            for (Object e : list)
                ((UObjectImpl) e)._internalGetInternalUObject().setContainer(null, null);
            if (getOwner() != null && getOwner().needNotification(getOwningFeature())
                    && getOwner() instanceof UObjectImpl)
                ((UObjectImpl) getOwner()).dispatchNotification(new NotificationImpl<>(list, null, getOwner(),
                        getOwningFeature(), NotificationType.REMOVE_MANY));
        }
        return result;
    }

    @Override
    public boolean addAll(Collection<? extends E> list) {
        if (list != null) {
            for (E e : list) {
                UObjectImpl uobj = (UObjectImpl) e;
                if (uobj._hasContainer())
                    uobj._internalGetInternalUObject().removeFromContainer(e);
            }
        } else
            return false;
        boolean result = super.addAll(list);
        if (result) {
            for (E e : list)
                ((UObjectImpl) e)._internalGetInternalUObject().setContainer(getOwner(), getOwningFeature());
            if (getOwner() != null && getOwner().needNotification(getOwningFeature())
                    && getOwner() instanceof UObjectImpl)
                ((UObjectImpl) getOwner()).dispatchNotification(
                        new NotificationImpl<>(null, list, getOwner(), getOwningFeature(), NotificationType.ADD_MANY));
        }
        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> list) {
        boolean changeContainer = mOwnedFeature != null
                && mOwnedFeature.getAggregation() != UAssociationType.AGGREGATION;
        if (changeContainer && list != null) {
            for (E e : list)
                ((UObjectImpl) e)._internalGetInternalUObject().removeFromContainer(e);
        } else
            return false;
        boolean result = super.addAll(index, list);
        if (result) {
            if (changeContainer)
                for (E e : list)
                    ((UObjectImpl) e)._internalGetInternalUObject().setContainer(getOwner(), getOwningFeature());
            if (getOwner() != null && getOwner().needNotification(getOwningFeature())
                    && getOwner() instanceof UObjectImpl)
                ((UObjectImpl) getOwner()).dispatchNotification(
                        new NotificationImpl<>(null, list, getOwner(), getOwningFeature(), NotificationType.ADD_MANY));
        }
        return result;
    }
   
    protected boolean _shallChangeContainer(E element) {
    	boolean changeContainer = 
    			mOwnedFeature != null
            && 	mOwnedFeature.getAggregation() != UAssociationType.AGGREGATION && element != null
            && 	element instanceof UObjectImpl;
    	if (mIsWeakProperty && changeContainer) {
    		if (element.getUContainer() != null) {
    			return false;
    		}
    	}
    	return changeContainer;
    }
    
    protected boolean _internalAdd(int index, E element) {
        boolean changeContainer = _shallChangeContainer(element); 
//        			mOwnedFeature != null
//                && 	mOwnedFeature.getAggregation() != UAssociationType.AGGREGATION && element != null
//                && 	element instanceof UObjectImpl;
        if (changeContainer) {
            ((UObjectImpl) element)._internalGetInternalUObject().removeFromContainer(element);
        }
        super.add(index, element);
        if (changeContainer) {
            ((UObjectImpl) element)._internalGetInternalUObject().setContainer(getOwner(), getOwningFeature());
        }
        if (getOwner() != null && getOwner().needNotification(getOwningFeature()) && getOwner() instanceof UObjectImpl)
            ((UObjectImpl) getOwner()).dispatchNotification(
                    new NotificationImpl<>(null, element, getOwner(), getOwningFeature(), NotificationType.ADD));
        return true;
    }

    private E _internalRemove(int index) {

        E element = super.remove(index);
        if (element != null) {
            boolean changeContainer = mOwnedFeature != null
                    && mOwnedFeature.getAggregation() != UAssociationType.AGGREGATION;
            if (changeContainer && element != null && element instanceof UObjectImpl) {
                ((UObjectImpl) element)._internalGetInternalUObject().removeFromContainer(element);
            }
            if (getOwner() != null && getOwner().needNotification(getOwningFeature())
                    && getOwner() instanceof UObjectImpl)
                ((UObjectImpl) getOwner()).dispatchNotification(
                        new NotificationImpl<>(element, null, getOwner(), getOwningFeature(), NotificationType.REMOVE));
        }
        return element;
    }

    protected UObject getOwner() {
        return mOwner;
    }

    public UStructuralFeature getOwningFeature() {
        return mOwnedFeature;
    }

    protected void setOwningFeature(UStructuralFeature f) {
        mOwnedFeature = f;
        mIsWeakProperty = f.getAnnotation(WEAK_PROPERTY) != null;
    }
}
