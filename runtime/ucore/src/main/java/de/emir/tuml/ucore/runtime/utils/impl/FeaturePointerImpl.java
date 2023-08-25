package de.emir.tuml.ucore.runtime.utils.impl;

import java.util.List;

import de.emir.tuml.ucore.runtime.IStructuralElement;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.FeaturePointer;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.UtilsPackage;

/**
 * @generated
 */
@UMLImplementation(classifier = FeaturePointer.class)
public class FeaturePointerImpl extends UObjectImpl implements FeaturePointer, Pointer {
    /**
     * @generated
     */
    private int mListIndex = 0;
    /**
     * @generated
     */
    private UObject mTheInstance = null;
    /**
     * @generated
     */
    private IStructuralElement mFeature = null;

    /**
     * Default constructor
     * 
     * @generated
     */
    public FeaturePointerImpl() {
        super();
        // set the default values and assign them to this instance
        setTheInstance(mTheInstance);
        setFeature(mFeature);
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public FeaturePointerImpl(final FeaturePointer _copy) {
        mListIndex = _copy.getListIndex();
        mTheInstance = _copy.getTheInstance();
        mFeature = _copy.getFeature();
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public FeaturePointerImpl(UObject _theInstance, int _listIndex, IStructuralElement _feature) {
        mListIndex = _listIndex;
        mTheInstance = _theInstance;
        mFeature = _feature;
    }

    public FeaturePointerImpl(UObject parent, UStructuralFeature feature) {
        this(parent, feature, -1);
    }

    public FeaturePointerImpl(UObject parent, UStructuralFeature feature, int listIndex) {
        mTheInstance = parent;
        mFeature = feature;
        mListIndex = listIndex;
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return UtilsPackage.Literals.FeaturePointer;
    }

    /**
     * @generated
     */
    public void setListIndex(int _listIndex) {
        if (needNotification(UtilsPackage.Literals.FeaturePointer_listIndex)) {
            int _oldValue = mListIndex;
            mListIndex = _listIndex;
            notify(_oldValue, mListIndex, UtilsPackage.Literals.FeaturePointer_listIndex, NotificationType.SET);
        } else {
            mListIndex = _listIndex;
        }
    }

    /**
     * @generated
     */
    public void setFeature(IStructuralElement _feature) {
        Notification<IStructuralElement> notification = basicSet(mFeature, _feature,
                UtilsPackage.Literals.FeaturePointer_feature);
        mFeature = _feature;
        if (notification != null) {
            dispatchNotification(notification);
        }
    }

    /**
     * @generated
     */
    public int getListIndex() {
        return mListIndex;
    }

    /**
     * @generated
     */
    public void setTheInstance(UObject _theInstance) {
        Notification<UObject> notification = basicSet(mTheInstance, _theInstance,
                UtilsPackage.Literals.FeaturePointer_theInstance);
        mTheInstance = _theInstance;
        if (notification != null) {
            dispatchNotification(notification);
        }
    }

    /**
     * @generated
     */
    public UObject getTheInstance() {
        return mTheInstance;
    }

    /**
     * @generated
     */
    public IStructuralElement getFeature() {
        return mFeature;
    }

    //////////////////////////////////////////////////////////////////
    // Operations //
    //////////////////////////////////////////////////////////////////
    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public Object getValue() {
        try {
            return PointerOperations.getValue(getTheInstance(), getFeature(), getListIndex());
        } catch (IndexOutOfBoundsException ioobe) {
            return null; // may happen if the last item of the list has recently been deleted
        }
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void setValue(final Object _value) {
        PointerOperations.set(getTheInstance(), getFeature(), getListIndex(), _value);
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void assign(final Object _value, final boolean _copyContainments) {
        PointerOperations.assign(getTheInstance(), getFeature(), getListIndex(), _value);
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UType getExpectedType() {
        return getFeature().getType();
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public boolean isValid() {
    	UObject ti = getTheInstance();
    	if (ti == null) 
    		return false;
    	IStructuralElement f = getFeature();
    	if (f == null)
    		return false;
    	int li = getListIndex();
    	if (li >= 0) {
    		if (f.isMany() == false)
    			return false;
    		List l = (List) PointerOperations.getValue(getTheInstance(), getFeature(), -1);
    		if (li >= l.size())
    			return false;
    	} //if (li < 0) and f.isMany() the pointer points to the list itself
    	return true;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UObject getPointedContainer() {
        return getTheInstance();
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UStructuralFeature getPointedFeature() {
        return mFeature instanceof UStructuralFeature ? (UStructuralFeature) mFeature : null;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void changeAnchor(final UObject root) {
        setTheInstance(root);
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public Pointer copy() {
        return new FeaturePointerImpl(this);
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UType getType() {
        if (getFeature() != null) {
            UStructuralFeature pf = getPointedFeature(); // null if an operation
            if (pf != null && pf.isAttribute())
                return getFeature().getType(); // in this case we do not have to look for a more specific generalized
                                               // type as nothing can inherit from primitive values
            try {
                Object v = getValue();
                if (v instanceof UObject)
                    return ((UObject) v).getUClassifier();
            } catch (Exception e) {
            } // no need for logging, just return the expected type
            return getFeature().getType();
        } else
            return null;
    }

    /**
     * @generated not
     */
    @Override
    public String toString() {
        String str = getTheInstance() + "";
        if (getFeature() != null)
            str += "." + getFeature().getName();
        else
            str += ".unknownFeature";
        if (getListIndex() >= 0)
            str += ":" + getListIndex();
        return str;
    }

    @Override
    public UObject getUObject() {
        Object v = getValue();
        if (v != null && v instanceof UObject)
            return (UObject) v;
        return null;
    }

    @Override
    public int compareTo(Pointer o) {
        if (o.hashCode() == hashCode())
            return 0; // normal object equals
        try {
            return o.getValue().hashCode() - getValue().hashCode();
        } catch (Exception e) {
        }
        return -1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pointer) {
            try {
                return getValue().equals(((Pointer) obj).getValue());
            } catch (Exception e) {
            }
        }
        return super.equals(obj);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get() {
        try {
            return (T) getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
