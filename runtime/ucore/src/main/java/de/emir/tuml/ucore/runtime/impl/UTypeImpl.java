package de.emir.tuml.ucore.runtime.impl;

import java.util.List;

import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.impl.UNamedElementImpl;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;

/**
 * @generated
 */
@UMLImplementation(classifier = UType.class)
abstract public class UTypeImpl extends UNamedElementImpl implements UType {

    protected Class<?> mClazz;

    /**
     * Default constructor
     * 
     * @generated
     */
    public UTypeImpl() {
        super();
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public UTypeImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name) {
        super(_documentation, _annotations, _package, _name);
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public UTypeImpl(final UType _copy) {
        super(_copy);
    }

    /**
     * @inheritDoc
     * @generated
     */
    public boolean inherits(final UType query) {
        // TODO:
        // checks if the query type inherits from this type
        throw new UnsupportedOperationException("inherits not yet implemented");
    }

    public UTypeImpl(Class<?> clazz, String name) {
        super(name);
        mClazz = clazz;
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.UType;
    }

    @Override
    public Class<?> getRepresentingClass() {
        return mClazz;
    }

    /**
     * @inheritDoc
     * @generated
     */

    /**
     * @inheritDoc
     * @generated
     */

    /**
     * @generated
     */
    @Override
    public String toString() {
        return "UTypeImpl{" + " documentation = " + getDocumentation() + " name = " + getName() + "}";
    }
}
