package de.emir.tuml.ucore.runtime.impl;

import java.util.ArrayList;
import java.util.List;

import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.impl.UClassifierImpl;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.access.impl.ReflectiveInstanceCreator;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.internal.builder.FeatureBuilder;
import de.emir.tuml.ucore.runtime.utils.internal.lists.LazyReferenceContainmentList;

/**
 * @generated
 */
@UMLImplementation(classifier = UClass.class)
public class UClassImpl extends UClassifierImpl implements UClass {
    /**
     * @generated not
     */
    private boolean mAbstract;
    /**
     * @generated not
     */
    private UClassifier mSuperType; // do not initialize since it overwrites the result of the super class
    /**
     * @generated
     */
    private List<UStructuralFeature> mStructuralFeatures = null;
    private IInstanceCreator mInstanceCreator = null;

    /**
     * Default constructor
     * 
     * @generated
     */
    public UClassImpl() {
        super();
        // set the default values and assign them to this instance
        setSuperType(mSuperType);
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public UClassImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name,
            List<UClassifier> _interfaces, List<UOperation> _operations, boolean _abstract, UClassifier _superType,
            List<UStructuralFeature> _structuralFeatures) {
        super(_documentation, _annotations, _package, _name, _interfaces, _operations);
        mAbstract = _abstract;
        mSuperType = _superType;
        mStructuralFeatures = _structuralFeatures;
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public UClassImpl(final UClass _copy) {
        super(_copy);
        mAbstract = _copy.getAbstract();
        mSuperType = _copy.getSuperType();
        mStructuralFeatures = _copy.getStructuralFeatures();
    }

    public static UClassImpl createClassWithReflection(UPackage p, Class<?> clazz, String name) {
        return new UClassImpl(p, clazz, name);
    }

    private UClassImpl(UPackage p, Class<?> clazz, String name) {
        super(false, clazz, name);
        mStructuralFeatures = FeatureBuilder.collectFeatures(this, mClazz);
        mInstanceCreator = new ReflectiveInstanceCreator(mClazz);
        setPackage(p);
    }

    public UClassImpl(String name, boolean isAbstract, Class<? extends UObject> clazz) {
        super(true, clazz, name);
        // setAbstract(isAbstract);
        mAbstract = isAbstract;
        mInstanceCreator = new ReflectiveInstanceCreator(mClazz); // ensure that the class can always be created
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.UClass;
    }

    /**
     * @generated
     */
    public void setAbstract(boolean _abstract) {
        if (needNotification(RuntimePackage.Literals.UClass_abstract)) {
            boolean _oldValue = mAbstract;
            mAbstract = _abstract;
            notify(_oldValue, mAbstract, RuntimePackage.Literals.UClass_abstract, NotificationType.SET);
        } else {
            mAbstract = _abstract;
        }
    }

    /**
     * @generated
     */
    public boolean getAbstract() {
        return mAbstract;
    }

    /**
     * @generated
     */
    public void setSuperType(UClassifier _superType) {
        Notification<UClassifier> notification = basicSet(mSuperType, _superType,
                RuntimePackage.Literals.UClass_superType);
        mSuperType = _superType;
        if (notification != null) {
            dispatchNotification(notification);
        }
    }

    /**
     * @generated
     */
    public UClassifier getSuperType() {
        return mSuperType;
    }

    /**
     * @generated not
     */
    @Override
    public List<UStructuralFeature> getStructuralFeatures() {
        if (mStructuralFeatures == null) {
            // mStructuralFeatures = new ArrayList<UStructuralFeature>();
            mStructuralFeatures = new LazyReferenceContainmentList<>(this,
                    new LazyReferenceContainmentList.FeatureResolver() {
                        @Override
                        public UStructuralFeature getFeature() {
                            UClass cl = UCoreMetaRepository.findClass(UClass.class);
                            if (cl != null && cl instanceof UClassImpl) {
                                if (((UClassImpl) cl).mStructuralFeatures != null) {
                                    return cl.getFeature("structuralFeatures");
                                }
                            }
                            return null;
                        }
                    });
        }
        return mStructuralFeatures;
    }

    /**
     * Sets a new instance creator
     * 
     * @param ic
     */
    public void setInstanceCreator(IInstanceCreator ic) {
        mInstanceCreator = ic;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UObject createNewInstance() {
        if (mInstanceCreator == null)
            return null;
        return mInstanceCreator.createNewInstance();
    }

    //////////////////////////////////////////////////////////////////
    // Operations //
    //////////////////////////////////////////////////////////////////
    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UStructuralFeature createFeature(final String _name) {
        if (isFrozen()) {
            throw new UnsupportedOperationException("Class " + getName() + " is frozen");
        }
        if (getFeature(_name) != null)
            return getFeature(_name);
        UStructuralFeatureImpl feature = new UStructuralFeatureImpl();
        feature.setName(_name);
        feature.setPackage(getPackage());
        getStructuralFeatures().add(feature);
        return feature;
    }

    private ArrayList<UStructuralFeature> mAllFeatures;

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public synchronized List<UStructuralFeature> getAllStructuralFeatures() {
        if (mAllFeatures == null || isFrozen() == false) {
            mAllFeatures = new ArrayList<UStructuralFeature>();
            mAllFeatures.addAll(getStructuralFeatures());
            if (getSuperType() != null) // interfaces to not have structural features
                mAllFeatures.addAll(getSuperType().getAllStructuralFeatures());
        }
        return mAllFeatures;
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
        return "UClassImpl{" + " documentation = " + getDocumentation() + " name = " + getName() + " abstract = "
                + getAbstract() + "}";
    }

    @Override
    public List<UClassifier> getAllParents() {
        if (mAllParents == null) {
            super.getAllParents(); // build the list
            if (getSuperType() != null && mAllParents.contains(getSuperType()) == false) {
                mAllParents.add(getSuperType());
                for (UClassifier spcl : getSuperType().getAllParents())
                    if (mAllParents.contains(spcl) == false)
                        mAllParents.add(spcl);
            }
        }
        return mAllParents;
    }

    @Override
    public void freeze() {
        mAllFeatures = null;
        if (mStructuralFeatures instanceof UContainmentList) {
            if (((UContainmentList) mStructuralFeatures).getOwningFeature() == null) {
                UContainmentList<UStructuralFeature> f = new UContainmentList<>(this,
                        UCoreMetaRepository.getClassifier(UClass.class).getFeature("structuralFeatures"));
                f.addAll(mStructuralFeatures);
                mStructuralFeatures = f;
            }
        }
        super.freeze();
    }

    @Override
    public boolean isStruct() {
        return getAnnotation("struct") != null;
    }

}
