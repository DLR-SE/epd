package de.emir.tuml.ucore.runtime.impl;

import java.util.List;

import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UMultiplicity;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UPrimitiveType;
import de.emir.tuml.ucore.runtime.impl.UNamedElementImpl;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.UTypedElement;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;

/**
 * @generated
 */
@UMLImplementation(classifier = UTypedElement.class)
abstract public class UTypedElementImpl extends UNamedElementImpl implements UTypedElement {
    /**
     * @generated
     */
    private UType mType = null;
    /**
     * @generated
     */
    private UMultiplicity mMultiplicity = null;

    /**
     * Default constructor
     * 
     * @generated
     */
    public UTypedElementImpl() {
        super();
        // set the default values and assign them to this instance
        setType(mType);
        setMultiplicity(mMultiplicity);
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public UTypedElementImpl(final UTypedElement _copy) {
        super(_copy);
        mType = _copy.getType();
        mMultiplicity = _copy.getMultiplicity();
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public UTypedElementImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name,
            UType _type, UMultiplicity _multiplicity) {
        super(_documentation, _annotations, _package, _name);
        mType = _type;
        mMultiplicity = _multiplicity;
    }

    public UTypedElementImpl(String name, UType type, UMultiplicity multi) {
        super(name);
        mType = type;
        mMultiplicity = multi;
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.UTypedElement;
    }

    /**
     * @generated
     */
    public void setType(UType _type) {
        Notification<UType> notification = basicSet(mType, _type, RuntimePackage.Literals.UTypedElement_type);
        mType = _type;
        if (notification != null) {
            dispatchNotification(notification);
        }
    }

    /**
     * @generated
     */
    public UType getType() {
        return mType;
    }

    /**
     * @generated
     */
    public void setMultiplicity(UMultiplicity _multiplicity) {
        Notification<UMultiplicity> notification = basicSet(mMultiplicity, _multiplicity,
                RuntimePackage.Literals.UTypedElement_multiplicity);
        mMultiplicity = _multiplicity;
        if (notification != null) {
            dispatchNotification(notification);
        }
    }

    /**
     * @generated
     */
    public UMultiplicity getMultiplicity() {
        return mMultiplicity;
    }

    //////////////////////////////////////////////////////////////////
    // Operations //
    //////////////////////////////////////////////////////////////////
    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public boolean isMany() {
        return mMultiplicity != null && mMultiplicity.getUpper() != 1;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public boolean isAttribute() {
        return mType instanceof UPrimitiveType || mType instanceof UEnum;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public boolean isReference() {
        return !isAttribute();
    }

    /**
     * @generated
     */
    @Override
    public String toString() {
        return "UTypedElementImpl{" + " documentation = " + getDocumentation() + " name = " + getName() + "}";
    }
}
