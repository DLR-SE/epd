package de.emir.tuml.ucore.runtime.utils.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;

import java.util.List;

import de.emir.tuml.ucore.runtime.IStructuralElement;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.TypePointer;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.tuml.ucore.runtime.utils.UtilsPackage;

/**
 * The TypedPointer is dynamic pointer type, that search for the listIndex .th instance of a given type inside an
 * UObject (provided by the parent pointer). Thereby the result may change between two calls to getValue(), if in the
 * meantime a new instance has been added.
 * 
 * To find the concrete value, the TypePointer iterates (within each call) through all UStructuralFeatures and checks if
 * the result type inherits the specified type. If that is the case and the value is not null the index value is
 * decremented until the index is <= 0. if the index is <= 0 the result is returned.
 * 
 * With regard to list features the TypePointer iterates through all instances of the list (always decrement the index
 * if the type match) before going to the next feature.
 * 
 * @note the TypePointer does not iterate through operations, as they may have side effects
 * @generated
 */
@UMLImplementation(classifier = TypePointer.class)
public class TypePointerImpl extends UObjectImpl implements TypePointer, Pointer {

    private static final int VALUE_INDEX = 0;
    private static final int FEATURE_INDEX = 1;
    private static final int LIST_INDEX = 2;
    private static final int PARENT_INDEX = 3;

    /**
     * @generated
     */
    private Pointer mParent = null;
    /**
     * @generated
     */
    private UType mType = null;
    /**
     * @generated
     */
    private int mListIndex = 0;

    /**
     * Default constructor
     * 
     * @generated
     */
    public TypePointerImpl() {
        super();
        // set the default values and assign them to this instance
        setParent(mParent);
        setType(mType);
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public TypePointerImpl(final TypePointer _copy) {
        mListIndex = _copy.getListIndex();
        mParent = _copy.getParent();
        mType = _copy.getType();
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public TypePointerImpl(Pointer _parent, UType _type, int _listIndex) {
        mListIndex = _listIndex;
        mParent = _parent;
        mType = _type;
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return UtilsPackage.Literals.TypePointer;
    }

    //////////////////////////////////////////////////////////////////
    // Setter / Getter //
    //////////////////////////////////////////////////////////////////
    /**
     * @generated
     */
    public void setParent(Pointer _parent) {
        Notification<Pointer> notification = basicSet(mParent, _parent, UtilsPackage.Literals.TypePointer_parent);
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
     * @generated
     */
    public void setType(UType _type) {
        Notification<UType> notification = basicSet(mType, _type, UtilsPackage.Literals.TypePointer_type);
        mType = _type;
        if (notification != null) {
            dispatchNotification(notification);
        }
    }

    /**
     * @generated
     */
    public void setListIndex(int _listIndex) {
        if (needNotification(UtilsPackage.Literals.TypePointer_listIndex)) {
            int _oldValue = mListIndex;
            mListIndex = _listIndex;
            notify(_oldValue, mListIndex, UtilsPackage.Literals.TypePointer_listIndex, NotificationType.SET);
        } else {
            mListIndex = _listIndex;
        }
    }

    /**
     * @generated
     */
    public int getListIndex() {
        return mListIndex;
    }

    //////////////////////////////////////////////////////////////////
    // Operations //
    //////////////////////////////////////////////////////////////////

    /**
     * @inheritDoc
     * @generated not
     */
    public Object getValue() {
        Object[] res = _getValue();
        if (res != null)
            return res[VALUE_INDEX];
        return null;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public void setValue(final Object value) {
        Object[] res = _getValue();
        if (res != null)
            PointerOperations.set((UObject) res[PARENT_INDEX], (UStructuralFeature) res[FEATURE_INDEX],
                    (Integer) res[LIST_INDEX], value);
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public void assign(final Object value, final boolean copyContainments) {
        Object[] res = _getValue();
        if (res != null)
            PointerOperations.assign((UObject) res[PARENT_INDEX], (UStructuralFeature) res[FEATURE_INDEX],
                    (Integer) res[LIST_INDEX], value);
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public boolean isValid() {
        return _getValue() != null;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public UType getType() {
        if (mType == null)
            return null;
        if (TypeUtils.isPrimitive(mType.getClass()))
            return mType;

        if (getParent() == null || getParent().isValid() == false)
            return getExpectedType();
        // if we can get the concrete instance we can return the current valid type
        Object[] result = _getValue();
        if (result != null) {
            if (result[VALUE_INDEX] != null) // null is a valid return type
                return ((UObject) result[VALUE_INDEX]).getUClassifier(); // cast should be save, as we know that its not
                                                                         // a primitive type and not a list by
                                                                         // definition
            else if (result[FEATURE_INDEX] != null) // value == null but using the concrete feature we should have a
                                                    // better (at least not worser) approximation of the real type
                return ((IStructuralElement) result[FEATURE_INDEX]).getType();
        }
        // no fallback, as there is currently no instance that fullfills the contract
        return null;
    }

    /**
     * returns the value and some meta information
     * 
     * @return [value, IStructuralElement, listIndex]
     */
    private Object[] _getValue() {
    	Pointer parent = getParent();
        if (parent == null || !parent.isValid())
            return null;
        UObject parentValue = parent.getUObject();// otherwise we would not be able to access the features
        if (parentValue == null)
            return null;
        UClass cl = parentValue.getUClassifier();
        int idx = getListIndex();
        UType expectedType = getExpectedType();
        for (UStructuralFeature feature : cl.getAllStructuralFeatures()) {
            // first find the candidate
            UType featureType = feature.getType();
            if (expectedType.inherits(featureType)) { // feature is a possible candidate
                Object featureValue = feature.get(parentValue);
                if (featureValue != null) {
                    if (feature.isMany()) {
                        List l = (List) featureValue;
                        for (int i = 0; i < l.size(); i++) {
                            UObject listValue = (UObject) l.get(i); // TODO: is that a valid cast?
                            if (listValue != null && listValue.getUClassifier().inherits(expectedType)) {
                                idx--;
                                if (idx < 0) {
                                    return new Object[] { listValue, feature, i, parentValue };
                                }
                            }
                        }
                    } else {
                        UObject ufv = (UObject) featureValue; // TODO: valid cast?
                        if (ufv.getUClassifier().inherits(expectedType)) {
                            idx--;
                            if (idx < 0)
                                return new Object[] { ufv, feature, -1, parentValue };
                        }
                    }
                }
            }
        }
        // @note we do not iterate throught the operations, as they may have side-effects
        return null;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public UType getExpectedType() {
        return mType;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public UObject getUObject() {
        Object[] res = _getValue();
        if (res != null && res[VALUE_INDEX] instanceof UObject)
            return (UObject) res[VALUE_INDEX];
        return null;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public UObject getPointedContainer() {
        Object[] res = _getValue();
        if (res != null)
            return (UObject) res[PARENT_INDEX];
        return null;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public UStructuralFeature getPointedFeature() {
        Object[] res = _getValue();
        if (res != null)
            return (UStructuralFeature) res[FEATURE_INDEX];
        return null;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public void changeAnchor(final UObject root) {
        getParent().changeAnchor(root);
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public Pointer copy() {
        return new TypePointerImpl(mParent, mType, mListIndex);
    }

    /**
     * @generated not
     */
    @Override
    public String toString() {
        String str = "" + getParent() + ".UComponent<" + mType != null ? mType.getName() : "null" + ">";
        if (getListIndex() >= 0)
            str += ":" + getListIndex();
        return str;
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
