package de.emir.tuml.ucore.runtime.impl;

import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;

/**
 * @generated
 */
@UMLImplementation(classifier = Notification.class)
public class NotificationImpl<T> extends UObjectImpl implements Notification<T> {
    /**
     * @generated
     */
    private NotificationType mType = NotificationType.SET;
    /**
     * @generated
     */
    private UObject mInstance = null;
    /**
     * @generated
     */
    private UStructuralFeature mFeature = null;
    private T mNewValue;
    private T mOldValue;

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public NotificationImpl(NotificationType _type, UObject _instance, UStructuralFeature _feature) {
        mType = _type;
        mInstance = _instance;
        mFeature = _feature;
    }

    /**
     * Default constructor
     * 
     * @generated
     */
    public NotificationImpl() {
        super();
        // set the default values and assign them to this instance
        setType(mType);
        setInstance(mInstance);
        setFeature(mFeature);
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public NotificationImpl(final Notification _copy) {
        mType = _copy.getType();
        mInstance = _copy.getInstance();
        mFeature = _copy.getFeature();
    }

    public NotificationImpl(T oldValue, T newValue, UObject instance, UStructuralFeature feature, NotificationType type) {
        mType = type;
        mFeature = feature;
        mInstance = instance;
        mNewValue = newValue;
        mOldValue = oldValue;
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.Notification;
    }

    /**
     * @generated
     */
    public void setType(NotificationType _type) {
        if (needNotification(RuntimePackage.Literals.Notification_type)) {
            NotificationType _oldValue = mType;
            mType = _type;
            notify(_oldValue, mType, RuntimePackage.Literals.Notification_type, NotificationType.SET);
        } else {
            mType = _type;
        }
    }

    /**
     * @generated
     */
    public NotificationType getType() {
        return mType;
    }

    /**
     * @generated
     */
    public void setInstance(UObject _instance) {
        Notification<UObject> notification = basicSet(mInstance, _instance,
                RuntimePackage.Literals.Notification_instance);
        mInstance = _instance;
        if (notification != null) {
            dispatchNotification(notification);
        }
    }

    /**
     * @generated
     */
    public UObject getInstance() {
        return mInstance;
    }

    /**
     * @generated
     */
    public void setFeature(UStructuralFeature _feature) {
        Notification<UStructuralFeature> notification = basicSet(mFeature, _feature,
                RuntimePackage.Literals.Notification_feature);
        mFeature = _feature;
        if (notification != null) {
            dispatchNotification(notification);
        }
    }

    /**
     * @generated
     */
    public UStructuralFeature getFeature() {
        return mFeature;
    }

    /**
     * @generated not
     */
    @Override
    public String toString() {
        return "NotificationImpl{ " + " Type: " + getType() + " Instance: " + getInstance() + " Feature: "
                + (getFeature() != null ? getFeature().getName() : "Unknown") + " OldValue: " + getOldValue()
                + " NewValue: " + getNewValue() + "}";
    }

    @Override
    public T getNewValue() {
        return mNewValue;
    }

    @Override
    public T getOldValue() {
        return mOldValue;
    }

}
