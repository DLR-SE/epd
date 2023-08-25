package de.emir.tuml.ucore.runtime.impl;

import java.util.List;

import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.impl.UNamedElementImpl;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;

/**
 * @generated
 */
@UMLImplementation(classifier = UEnumerator.class)
public class UEnumeratorImpl extends UNamedElementImpl implements UEnumerator {
    /**
     * @generated
     */
    private int mValue = 0;
    private Object mInstanceValue;

    /**
     * Default constructor
     * 
     * @generated
     */
    public UEnumeratorImpl() {
        super();
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public UEnumeratorImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name,
            int _value) {
        super(_documentation, _annotations, _package, _name);
        mValue = _value;
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public UEnumeratorImpl(final UEnumerator _copy) {
        super(_copy);
        mValue = _copy.getValue();
    }

    public UEnumeratorImpl(Object obj) {
        mInstanceValue = obj;
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.UEnumerator;
    }

    /**
     * @generated
     */
    public void setValue(int _value) {
        if (needNotification(RuntimePackage.Literals.UEnumerator_value)) {
            int _oldValue = mValue;
            mValue = _value;
            notify(_oldValue, mValue, RuntimePackage.Literals.UEnumerator_value, NotificationType.SET);
        } else {
            mValue = _value;
        }
    }

    /**
     * @generated
     */
    public int getValue() {
        return mValue;
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

    public Object createNewInstance() {
        return mInstanceValue;
    }

    /**
     * @generated
     */
    @Override
    public String toString() {
        return "UEnumeratorImpl{" + " documentation = " + getDocumentation() + " name = " + getName() + " value = "
                + getValue() + "}";
    }
}
