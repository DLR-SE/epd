package de.emir.tuml.ucore.runtime.utils.impl;

import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.ObjectPointer;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.UtilsPackage;

/**
 * @generated
 */
@UMLImplementation(classifier = ObjectPointer.class)
public class ObjectPointerImpl extends UObjectImpl implements ObjectPointer, Pointer {
    /**
     * @generated
     */
    private UObject mTheInstance = null;

    /**
     * Default constructor
     * 
     * @generated
     */
    public ObjectPointerImpl() {
        super();
        // set the default values and assign them to this instance
        setTheInstance(mTheInstance);
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public ObjectPointerImpl(final ObjectPointer _copy) {
        mTheInstance = _copy.getTheInstance();
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public ObjectPointerImpl(UObject _theInstance) {
        mTheInstance = _theInstance;
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return UtilsPackage.Literals.ObjectPointer;
    }

    /**
     * @generated
     */
    public void setTheInstance(UObject _theInstance) {
        Notification<UObject> notification = basicSet(mTheInstance, _theInstance,
                UtilsPackage.Literals.ObjectPointer_theInstance);
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
     * @inheritDoc
     * @generated not
     */
    @Override
    public Object getValue() {
        return mTheInstance;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void setValue(final Object value) {
        assign(value, false);
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void assign(final Object value, final boolean copyContainments) {
        if (value == mTheInstance || mTheInstance == null || value == null) {
            ULog.debug("NULL");
            return;
        }
        // check if the classes match somehow, that is 1) they are the same, or both are UObjects and
        // value.getUClassifier inherits this.getUClassifier()
        boolean valid = value == null || value.getClass() == mTheInstance.getClass();
        UObject uv = null;
        if (valid)
            uv = (UObject) value;
        if (!valid && value instanceof UObject) {
            uv = (UObject) value;
            valid = uv.getUClassifier().inherits(mTheInstance.getUClassifier());
        }
        if (!valid) {
            ULog.error("Can not assign the value: " + value + " to the instance of: " + mTheInstance);
            return;
        }
        for (UStructuralFeature f : mTheInstance.getUClassifier().getAllStructuralFeatures()) {
            // if (f == null)
            // System.out.println("NULL F");
            PointerOperations.assign(mTheInstance, f, f.get(uv));
        }
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public boolean isValid() {
        return getTheInstance() != null;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UType getType() {
        return getTheInstance().getUClassifier();
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UType getExpectedType() {
        if (getTheInstance().getUContainingFeature() != null)
            return getTheInstance().getUContainingFeature().getType();
        return getTheInstance().getUClassifier();
    }

    /**
     * @generated not
     */
    @Override
    public String toString() {
        return "ObjectPointerImpl{" + mTheInstance + "}";
    }

    @Override
    public UObject getUObject() {
        return getTheInstance();
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UStructuralFeature getPointedFeature() {
        return getTheInstance().getUContainingFeature();
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void changeAnchor(final UObject root) {
        mTheInstance = root;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public Pointer copy() {
        return new ObjectPointerImpl(getTheInstance());
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UObject getPointedContainer() {
        return getTheInstance().getUContainer();
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
