package de.emir.tuml.ucore.runtime.impl;

import java.util.ArrayList;
import java.util.List;

import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.impl.UClassifierImpl;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;
import de.emir.tuml.ucore.runtime.utils.internal.builder.EnumerationBuilder;

/**
 * @generated
 */
@UMLImplementation(classifier = UEnum.class)
public class UEnumImpl extends UClassifierImpl implements UEnum {
    /**
     * @generated
     */
    private List<UEnumerator> mLiterals = null;

    /**
     * Default constructor
     * 
     * @generated
     */
    public UEnumImpl() {
        super();
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public UEnumImpl(final UEnum _copy) {
        super(_copy);
        mLiterals = _copy.getLiterals();
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public UEnumImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name,
            List<UClassifier> _interfaces, List<UOperation> _operations, List<UEnumerator> _literals) {
        super(_documentation, _annotations, _package, _name, _interfaces, _operations);
        mLiterals = _literals;
    }

    public static UEnumImpl createEnumerationWithReflection(String name, Class<?> clazz) {
        return new UEnumImpl(clazz, name);
    }

    private UEnumImpl(Class<?> clazz, String name) {
        super(false, clazz, name);
        mLiterals = EnumerationBuilder.getLiterals(clazz);
    }

    public UEnumImpl(String name, Class<?> clazz) {
        super(true, clazz, name);
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.UEnum;
    }

    /**
     * @generated
     */
    public List<UEnumerator> getLiterals() {
        if (mLiterals == null) {
            mLiterals = new UContainmentList<UEnumerator>(this, RuntimePackage.theInstance.getUEnum_literals());
        }
        return mLiterals;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public Object createNewInstance(final String _name) {
        UEnumerator en = getEnumerator(_name);
        return createNewInstance(en);
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public Object createNewInstance(final int _value) {
        return createNewInstance(getEnumerator(_value));
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public Object createNewInstance(final UEnumerator _enumerator) {
        if (_enumerator instanceof UEnumeratorImpl)
            return ((UEnumeratorImpl) _enumerator).createNewInstance();
        return null;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UEnumerator getEnumerator(final String _name) {
        for (UEnumerator en : getLiterals())
            if (en.getName().equals(_name))
                return en;
        return null;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UEnumerator getEnumerator(final int _value) {
        for (UEnumerator en : getLiterals())
            if (en.getValue() == _value)
                return en;
        return null;
    }

    /**
     * @inheritDoc
     * @generated
     */
    public UEnumerator createLiteral(final String name, final int value) {
        // TODO:
        //
        // * creates a new Literal
        // * @precondition isFrozen() == false
        //
        throw new UnsupportedOperationException("createLiteral not yet implemented");
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public List<UStructuralFeature> getAllStructuralFeatures() {
        return new ArrayList<>();
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void build() {

    }

    /**
     * @generated
     */
    @Override
    public String toString() {
        return "UEnumImpl{" + " documentation = " + getDocumentation() + " name = " + getName() + "}";
    }

    @Override
    public UEnumerator getLiteral(String name) {
        for (UEnumerator l : getLiterals())
            if (l.getName().equals(name))
                return l;
        return null;
    }
}
