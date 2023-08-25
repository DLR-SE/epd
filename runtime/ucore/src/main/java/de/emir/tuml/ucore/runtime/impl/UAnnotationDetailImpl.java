package de.emir.tuml.ucore.runtime.impl;

import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UAnnotationDetail;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;

/**
 * @generated
 */
@UMLImplementation(classifier = UAnnotationDetail.class)
public class UAnnotationDetailImpl extends UObjectImpl implements UAnnotationDetail {
    /**
     * @generated
     */
    private String mKey = "";
    /**
     * @generated
     */
    private String mValue = "";

    /**
     * Default constructor
     * 
     * @generated
     */
    public UAnnotationDetailImpl() {
        super();
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public UAnnotationDetailImpl(final UAnnotationDetail _copy) {
        mKey = _copy.getKey();
        mValue = _copy.getValue();
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public UAnnotationDetailImpl(String _key, String _value) {
        mKey = _key;
        mValue = _value;
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.UAnnotationDetail;
    }

    /**
     * @generated
     */
    public void setKey(String _key) {
        if (needNotification(RuntimePackage.Literals.UAnnotationDetail_key)) {
            String _oldValue = mKey;
            mKey = _key;
            notify(_oldValue, mKey, RuntimePackage.Literals.UAnnotationDetail_key, NotificationType.SET);
        } else {
            mKey = _key;
        }
    }

    /**
     * @generated
     */
    public String getKey() {
        return mKey;
    }

    /**
     * @generated
     */
    public void setValue(String _value) {
        if (needNotification(RuntimePackage.Literals.UAnnotationDetail_value)) {
            String _oldValue = mValue;
            mValue = _value;
            notify(_oldValue, mValue, RuntimePackage.Literals.UAnnotationDetail_value, NotificationType.SET);
        } else {
            mValue = _value;
        }
    }

    /**
     * @generated
     */
    public String getValue() {
        return mValue;
    }

    /**
     * @generated
     */
    @Override
    public String toString() {
        return "UAnnotationDetailImpl{" + " key = " + getKey() + " value = " + getValue() + "}";
    }
}
