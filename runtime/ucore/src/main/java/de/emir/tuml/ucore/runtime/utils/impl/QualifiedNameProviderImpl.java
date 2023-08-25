package de.emir.tuml.ucore.runtime.utils.impl;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.utils.QualifiedName;
import de.emir.tuml.ucore.runtime.utils.QualifiedNameProvider;
import de.emir.tuml.ucore.runtime.utils.UtilsPackage;

/**
 * 
 * creates a qualified name for an UObject therefore the QualifiedNameProvider checks the objects's class if it contains
 * a name feature
 * 
 * @generated
 */
@UMLImplementation(classifier = QualifiedNameProvider.class)
public class QualifiedNameProviderImpl extends UObjectImpl implements QualifiedNameProvider {

    /**
     * Default constructor
     * 
     * @generated
     */
    public QualifiedNameProviderImpl() {
        super();
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public QualifiedNameProviderImpl(final QualifiedNameProvider _copy) {
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return UtilsPackage.Literals.QualifiedNameProvider;
    }

    //////////////////////////////////////////////////////////////////
    // Operations //
    //////////////////////////////////////////////////////////////////
    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public QualifiedName get(final UObject _obj) {
        return get(_obj, 3);
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public QualifiedName get(final UObject _obj, final int _depth) {
        if (_obj == null || _depth == 0)
            return null;
        UClass cl = _obj.getUClassifier();
        UStructuralFeature feature = cl.getFeature("name");
        String name = null;
        if (feature != null) {
            Object v = feature.get(_obj);
            if (v != null)
                name = v.toString();
        }
        QualifiedName parent = null;
        if (_obj.getUContainer() != null) {
            parent = get(_obj.getUContainer(), _depth - 1);
        }
        if (parent != null)
            if (name != null)
                return parent.appendBack(name);
            else
                return parent;
        else if (name == null)
            return null;
        return new QualifiedNameImpl(name);
    }

    /**
     * @generated
     */
    @Override
    public String toString() {
        return "QualifiedNameProviderImpl{" + "}";
    }
}
