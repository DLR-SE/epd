package de.emir.tuml.ucore.runtime.impl;

import java.util.List;

import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UMultiplicity;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UParameter;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UTypedElementImpl;

/**
 * @generated
 */
@UMLImplementation(classifier = UParameter.class)
public class UParameterImpl extends UTypedElementImpl implements UParameter {
    /**
     * @generated
     */
    private UDirectionType mDirection = null;

    /**
     * Default constructor
     * 
     * @generated
     */
    public UParameterImpl() {
        super();
        // set the default values and assign them to this instance
        setDirection(mDirection);
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public UParameterImpl(final UParameter _copy) {
        super(_copy);
        mDirection = _copy.getDirection();
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public UParameterImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name,
            UType _type, UMultiplicity _multiplicity, UDirectionType _direction) {
        super(_documentation, _annotations, _package, _name, _type, _multiplicity);
        mDirection = _direction;
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.UParameter;
    }

    /**
     * @generated
     */
    public void setDirection(UDirectionType _direction) {
        if (needNotification(RuntimePackage.Literals.UParameter_direction)) {
            UDirectionType _oldValue = mDirection;
            mDirection = _direction;
            notify(_oldValue, mDirection, RuntimePackage.Literals.UParameter_direction, NotificationType.SET);
        } else {
            mDirection = _direction;
        }
    }

    /**
     * @generated
     */
    public UDirectionType getDirection() {
        return mDirection;
    }

    //////////////////////////////////////////////////////////////////
    // Operations //
    //////////////////////////////////////////////////////////////////
    /**
     * @inheritDoc
     * @generated
     */

    /**
     * @generated
     */
    @Override
    public String toString() {
        return "UParameterImpl{" + " documentation = " + getDocumentation() + " name = " + getName() + "}";
    }

    @Override
    public void build() {
        // TODO

    }
}
