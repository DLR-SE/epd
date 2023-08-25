package de.emir.tuml.ucore.runtime.impl;

import java.util.ArrayList;
import java.util.List;

import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.impl.UClassifierImpl;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;

/**
 * @generated
 */
@UMLImplementation(classifier = UInterface.class)
public class UInterfaceImpl extends UClassifierImpl implements UInterface {

    /**
     * Default constructor
     * 
     * @generated
     */
    public UInterfaceImpl() {
        super();
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public UInterfaceImpl(final UInterface _copy) {
        super(_copy);
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public UInterfaceImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name,
            List<UClassifier> _interfaces, List<UOperation> _operations) {
        super(_documentation, _annotations, _package, _name, _interfaces, _operations);
    }

    public UInterfaceImpl(Class<?> clazz) {
        // TODO
    }

    public static UInterfaceImpl createInterfaceWithReflection(UPackageImpl p, Class<?> clazz, String name) {
        return new UInterfaceImpl(p, clazz, name);
    }

    private UInterfaceImpl(UPackage p, Class<?> clazz, String name) {
        super(false, clazz, name);
        setPackage(p);
    }

    public UInterfaceImpl(String name, Class<?> clazz) {
        super(true, clazz, name);
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.UInterface;
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
        return "UInterfaceImpl{" + " documentation = " + getDocumentation() + " name = " + getName() + "}";
    }

}
