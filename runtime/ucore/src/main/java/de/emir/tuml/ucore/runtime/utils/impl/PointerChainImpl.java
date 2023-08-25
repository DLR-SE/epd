package de.emir.tuml.ucore.runtime.utils.impl;

import de.emir.tuml.ucore.runtime.IStructuralElement;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.PointerChain;
import de.emir.tuml.ucore.runtime.utils.impl.FeaturePointerImpl;
import de.emir.tuml.ucore.runtime.utils.UtilsPackage;

/**
 * @generated
 */
@UMLImplementation(classifier = PointerChain.class)
public class PointerChainImpl extends FeaturePointerImpl implements PointerChain {
    /**
     * @generated
     */
    private Pointer mParent = null;

    /**
     * Default constructor
     * 
     * @generated
     */
    public PointerChainImpl() {
        super();
        // set the default values and assign them to this instance
        setParent(mParent);
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public PointerChainImpl(final PointerChain _copy) {
        super(_copy);
        mParent = _copy.getParent();
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public PointerChainImpl(UObject _theInstance, int _listIndex, IStructuralElement _feature, Pointer _parent) {
        super(_theInstance, _listIndex, _feature);
        mParent = _parent;
    }

    public PointerChainImpl(Pointer parent, UStructuralFeature feature) {
        this(parent, feature, -1);
    }

    public PointerChainImpl(Pointer parent, IStructuralElement feature, int listIndex) {
        mParent = parent;
        setFeature(feature);
        setListIndex(listIndex);
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return UtilsPackage.Literals.PointerChain;
    }

    /**
     * @generated
     */
    public void setParent(Pointer _parent) {
        Notification<Pointer> notification = basicSet(mParent, _parent, UtilsPackage.Literals.PointerChain_parent);
        mParent = _parent;
        if (notification != null) {
            dispatchNotification(notification);
        }
    }

    /**
     * @generated
     */
    public Pointer getParent() {
        return mParent;
    }

    /**
     * @generated not
     */
    @Override
    public String toString() {
        return getParent() + "." + (getFeature() != null ? getFeature().getName() : "null")
                + ((getListIndex() < 0) ? "" : (":" + getListIndex()));

    }

    @Override
    public boolean isValid() {
        Pointer parent = getParent();
    	if (parent == null || getFeature() == null)
        	return false;
        return parent.isValid();
    }

    @Override
    public UObject getTheInstance() {
        Object parent_value = getParent().getValue();
        if (parent_value != null && parent_value instanceof UObject)
            return (UObject) parent_value;
        return super.getTheInstance();
    }

    @Override
    public Object getValue() {
        Object parent_value = getParent().getValue();
        if (parent_value == null || parent_value instanceof UObject == false)
            return null;
        return PointerOperations.getValue((UObject) parent_value, getFeature(), getListIndex());
    }

    @Override
    public void setValue(Object _value) {
        Object parent_value = getParent().getValue();
        if (parent_value == null || parent_value instanceof UObject == false)
            return;
        PointerOperations.set((UObject) parent_value, getFeature(), getListIndex(), _value);
    }

    @Override
    public void changeAnchor(UObject root) {
        getParent().changeAnchor(root);
    }

    @Override
    public Pointer copy() {
        if (getParent() != null) {
            Pointer pc = getParent().copy();
            return new PointerChainImpl(pc, getFeature(), getListIndex());
        }
        return new PointerChainImpl(this);
    }
}
