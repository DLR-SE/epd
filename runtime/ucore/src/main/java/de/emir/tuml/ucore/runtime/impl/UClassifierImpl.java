package de.emir.tuml.ucore.runtime.impl;

import java.util.ArrayList;
import java.util.List;

import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.impl.UTypeImpl;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.lists.ListUtils;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.internal.builder.UClassBuilder;

/**
 * @generated
 */
@UMLImplementation(classifier = UClassifier.class)
abstract public class UClassifierImpl extends UTypeImpl implements UClassifier {
    /**
     * @generated
     */
    private List<UClassifier> mInterfaces = null;
    /**
     * @generated
     */
    private List<UOperation> mOperations = null;

    /**
     * Default constructor
     * 
     * @generated
     */
    public UClassifierImpl() {
        super();
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public UClassifierImpl(final UClassifier _copy) {
        super(_copy);
        mInterfaces = _copy.getInterfaces();
        mOperations = _copy.getOperations();
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public UClassifierImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name,
            List<UClassifier> _interfaces, List<UOperation> _operations) {
        super(_documentation, _annotations, _package, _name);
        mInterfaces = _interfaces;
        mOperations = _operations;
    }

    /**
     * @param manualBuild
     * @param clazz
     * @param name
     */
    protected UClassifierImpl(boolean manualBuild, Class<?> clazz, String name) {
        super(clazz, name);
        UCoreMetaRepository.registerClassifier(clazz.getName(), this); // use the full qualified name
        if (manualBuild == false) {
            UClassBuilder.discoverHierarchie(this);
        }
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.UClassifier;
    }

    /**
     * @generated
     */
    public List<UClassifier> getInterfaces() {
        if (mInterfaces == null) {
            mInterfaces = ListUtils.<UClassifier>asList(this, RuntimePackage.theInstance.getUClassifier_interfaces());
        }
        return mInterfaces;
    }

    /**
     * @generated
     */
    public List<UOperation> getOperations() {
        if (mOperations == null) {
            mOperations = new UContainmentList<UOperation>(this,
                    RuntimePackage.theInstance.getUClassifier_operations());
        }
        return mOperations;
    }

    //////////////////////////////////////////////////////////////////
    // Operations //
    //////////////////////////////////////////////////////////////////
    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void addParent(final UClassifier _cl) {
        if (isFrozen())
            throw new UnsupportedOperationException("Classifier " + getName() + " is frozen");

        if (_cl != null && getInterfaces().contains(_cl) == false)
            getInterfaces().add(_cl);
    }

    private ArrayList<UStructuralFeature> mAllAttributes = null;
    private ArrayList<UStructuralFeature> mAllReferences = null;

    private boolean filter(UStructuralFeature f, final UAssociationType at) {
        if (f.isAttribute())
            return false;
        if (f.getType() instanceof UEnum)
            return false;

        switch (at) {
        case PROPERTY:
            return true;
        case ASSOCIATION:
            if (f.getAggregation() == UAssociationType.PROPERTY)
                return false;
            return true;
        case COMPOSITION:
            if (f.getAggregation() == UAssociationType.COMPOSITION)
                return true;
            return false;
        case AGGREGATION:
            switch (f.getAggregation()) {
            case PROPERTY:
            case ASSOCIATION:
                return false;
            }
            return true;
        }

        return false;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public List<UStructuralFeature> getAllStructuralFeatures(final UAssociationType _type) {
        ArrayList<UStructuralFeature> list = new ArrayList<UStructuralFeature>();
        for (UStructuralFeature f : getAllReferences()) {
            if (filter(f, _type))
                list.add(f);
        }
        return list;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public UOperation getOperation(final String name) {
        List<UOperation> allOp = getAllOperations();
        for (UOperation op : allOp)
            if (op.getName().equals(name))
                return op;
        return null;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public List<UStructuralFeature> getAllAttributes() {
        if (mAllAttributes == null) {
            mAllAttributes = new ArrayList<UStructuralFeature>();
            for (UStructuralFeature f : getAllStructuralFeatures()) {
                if (f.isAttribute())
                    mAllAttributes.add(f);
            }
        }
        return mAllAttributes;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public List<UStructuralFeature> getAllReferences() {
        if (mAllReferences == null) {
            mAllReferences = new ArrayList<UStructuralFeature>();
            for (UStructuralFeature f : getAllStructuralFeatures()) {
                if (f.isReference())
                    mAllReferences.add(f);
            }
        }
        return mAllReferences;
    }

    /**
     * @inheritDoc
     * @generated
     */
    public UOperation createOperation(final String name) {
        // TODO:
        //
        // * adds a new operation to this type
        // * @precondition isFrozen() == false
        //
        throw new UnsupportedOperationException("createOperation not yet implemented");
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UStructuralFeature getFeature(final String _name) {
        for (UStructuralFeature f : getAllStructuralFeatures()) {
            if (f.getName().equals(_name))
                return f;
        }
        return null;
    }

    protected ArrayList<UClassifier> mAllParents = null;

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public List<UClassifier> getAllParents() {
        if (mAllParents == null) {
            mAllParents = new ArrayList<UClassifier>();
            if (this != RuntimePackage.Literals.UObject)
                mAllParents.add(RuntimePackage.Literals.UObject);
            mAllParents.addAll(getInterfaces());
            for (UClassifier in : getInterfaces()) {
                for (UClassifier p : in.getAllParents())
                    if (mAllParents.contains(p) == false)
                        mAllParents.add(p);
            }
        }
        return mAllParents;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public synchronized boolean inherits(final UType _parent) {
        if (_parent == null)
            return false;
        if (_parent == this)
            return true;
        for (UClassifier cl : getAllParents())
            if (cl == _parent)
                return true;
        return false;
    }

    /**
     * @generated
     */
    @Override
    public String toString() {
        return "UClassifierImpl{" + " documentation = " + getDocumentation() + " name = " + getName() + "}";
    }

    @Override
    public void freeze() {
        // delete convinience lists, in case they have been created while the classifier has not been fully defined.
        // they will be rebuild the next time, they are requested.
        mAllParents = null;
        mAllAttributes = null;
        mAllReferences = null;
        super.freeze();
    }

    private ArrayList<UOperation> mAllOperations = null;

    @Override
    public List<UOperation> getAllOperations() {
        if (mAllOperations == null) {
            mAllOperations = new ArrayList<>();
            mAllOperations.addAll(getOperations());
            for (UClassifier p : getAllParents()) {
                mAllOperations.addAll(p.getOperations());
            }

        }
        return mAllOperations;
    }
}
