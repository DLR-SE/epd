package de.emir.tuml.ucore.runtime.impl;

import java.util.List;

import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UException;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.impl.UTypeImpl;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;

/**
 * @generated
 */
@UMLImplementation(classifier = UException.class)
public class UExceptionImpl extends UTypeImpl implements UException {
    /**
     * @generated
     */
    private String mStandardMessage = "";
    /**
     * @generated
     */
    private UException mSuperType = null;

    /**
     * Default constructor
     * 
     * @generated
     */
    public UExceptionImpl() {
        super();
        // set the default values and assign them to this instance
        setSuperType(mSuperType);
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public UExceptionImpl(final UException _copy) {
        super(_copy);
        mStandardMessage = _copy.getStandardMessage();
        mSuperType = _copy.getSuperType();
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public UExceptionImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name,
            String _standardMessage, UException _superType) {
        super(_documentation, _annotations, _package, _name);
        mStandardMessage = _standardMessage;
        mSuperType = _superType;
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.UException;
    }

    /**
     * @generated
     */
    public void setStandardMessage(String _standardMessage) {
        if (needNotification(RuntimePackage.Literals.UException_standardMessage)) {
            String _oldValue = mStandardMessage;
            mStandardMessage = _standardMessage;
            notify(_oldValue, mStandardMessage, RuntimePackage.Literals.UException_standardMessage,
                    NotificationType.SET);
        } else {
            mStandardMessage = _standardMessage;
        }
    }

    /**
     * @generated
     */
    public String getStandardMessage() {
        return mStandardMessage;
    }

    /**
     * @generated
     */
    public void setSuperType(UException _superType) {
        Notification<UException> notification = basicSet(mSuperType, _superType,
                RuntimePackage.Literals.UException_superType);
        mSuperType = _superType;
        if (notification != null) {
            dispatchNotification(notification);
        }
    }

    /**
     * @generated
     */
    public UException getSuperType() {
        return mSuperType;
    }

    /**
     * @inheritDoc
     * @generated
     */
    public void build() {
        // TODO:
        //
        // * initializes the model element, e.g. create private member for reflection access
        //
        throw new UnsupportedOperationException("build not yet implemented");
    }

    /**
     * @generated
     */
    @Override
    public String toString() {
        return "UExceptionImpl{" + " documentation = " + getDocumentation() + " name = " + getName()
                + " standardMessage = " + getStandardMessage() + "}";
    }
}
