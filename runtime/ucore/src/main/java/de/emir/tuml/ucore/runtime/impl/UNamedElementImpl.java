package de.emir.tuml.ucore.runtime.impl;

import java.util.List;

import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UNamedElement;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.impl.UModelElementImpl;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.utils.QualifiedName;
import de.emir.tuml.ucore.runtime.utils.impl.QualifiedNameImpl;

/**
 * @generated
 */
@UMLImplementation(classifier = UNamedElement.class)
abstract public class UNamedElementImpl extends UModelElementImpl implements UNamedElement {
    /**
     * @generated
     */
    private String mName = "";

    /**
     * Default constructor
     * 
     * @generated
     */
    public UNamedElementImpl() {
        super();
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public UNamedElementImpl(final UNamedElement _copy) {
        super(_copy);
        mName = _copy.getName();
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public UNamedElementImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name) {
        super(_documentation, _annotations, _package);
        mName = _name;
    }

    public UNamedElementImpl(String name) {
        super();
        mName = name;
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.UNamedElement;
    }

    /**
     * @generated
     */
    public void setName(String _name) {
        if (needNotification(RuntimePackage.Literals.UNamedElement_name)) {
            String _oldValue = mName;
            mName = _name;
            notify(_oldValue, mName, RuntimePackage.Literals.UNamedElement_name, NotificationType.SET);
        } else {
            mName = _name;
        }
    }

    /**
     * @generated
     */
    public String getName() {
        return mName;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public QualifiedName getQualifiedName() {
        UObject container = getUContainer();
        QualifiedName qn = null;
        if (container != null && container instanceof UNamedElement) {
            qn = ((UNamedElement) container).getQualifiedName();
        } else
            qn = new QualifiedNameImpl();
        return qn.appendBack(getName());
    }

    /**
     * @inheritDoc
     * @generated
     */

    /**
     * @generated
     */
    @Override
    public String toString() {
        return "UNamedElementImpl{" + " documentation = " + getDocumentation() + " name = " + getName() + "}";
    }
}
