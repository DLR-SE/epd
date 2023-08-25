package de.emir.tuml.ucore.runtime.impl;

import java.beans.PropertyDescriptor;
import java.util.List;
import de.emir.tuml.ucore.runtime.IStructuralElement;
import de.emir.tuml.ucore.runtime.Notification;

import com.google.common.reflect.Invokable;

import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UMultiplicity;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.impl.UTypedElementImpl;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.tuml.ucore.runtime.access.impl.ReflectiveFeatureGetter;
import de.emir.tuml.ucore.runtime.access.impl.ReflectiveFeatureSetter;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;

/**
 * @generated
 */
@UMLImplementation(classifier = UStructuralFeature.class)
public class UStructuralFeatureImpl extends UTypedElementImpl implements UStructuralFeature {
    /**
     * @generated
     */
    private UAssociationType mAggregation = null;
    // private PropertyDescriptor mDesc;

    private IFeatureSetter mSetter;
    private IFeatureGetter mGetter;

    private boolean mReadOnly;

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public UStructuralFeatureImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package,
            String _name, UType _type, UMultiplicity _multiplicity, UAssociationType _aggregation) {
        super(_documentation, _annotations, _package, _name, _type, _multiplicity);
        mAggregation = _aggregation;
    }

    /**
     * Default constructor
     * 
     * @generated
     */
    public UStructuralFeatureImpl() {
        super();
        // set the default values and assign them to this instance
        setAggregation(mAggregation);
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public UStructuralFeatureImpl(final UStructuralFeature _copy) {
        super(_copy);
        mAggregation = _copy.getAggregation();
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public boolean isReadOnly() {
        return mSetter == null && isMany() == false; // can not decide if its many, since there won't be a setter
    }

    public UStructuralFeatureImpl(String name, UType type, UMultiplicity multi, UAssociationType assoType,
            Invokable getter, Invokable setter, PropertyDescriptor desc) {
        this(name, type, multi, assoType, getter != null ? new ReflectiveFeatureGetter(getter) : null,
                setter != null ? new ReflectiveFeatureSetter(setter) : null, desc);
    }

    public UStructuralFeatureImpl(String name, UType type, UMultiplicity multi, UAssociationType assoType,
            IFeatureGetter getter, IFeatureSetter setter, PropertyDescriptor desc) {
        super(name, type, multi);
        mAggregation = assoType;
        mGetter = getter;
        mSetter = setter;
        // mDesc = desc;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public String getName() {
        return super.getName();
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public String getDocumentation() {
        return super.getDocumentation();
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public UType getType() {
        return super.getType();
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public UMultiplicity getMultiplicity() {
        return super.getMultiplicity();
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.UStructuralFeature;
    }

    /**
     * @generated
     */
    public void setAggregation(UAssociationType _aggregation) {
        if (needNotification(RuntimePackage.Literals.UStructuralFeature_aggregation)) {
            UAssociationType _oldValue = mAggregation;
            mAggregation = _aggregation;
            notify(_oldValue, mAggregation, RuntimePackage.Literals.UStructuralFeature_aggregation,
                    NotificationType.SET);
        } else {
            mAggregation = _aggregation;
        }
    }

    /**
     * @generated
     */
    public UAssociationType getAggregation() {
        return mAggregation;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UClass getOwner() {
        if (getUContainer() != null && getUContainer() instanceof UClass)
            return (UClass) getUContainer();
        return null;
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
     * @generated not
     */
    @Override
    public String toString() {
    	String prop = "";
    	if (getAggregation() == UAssociationType.PROPERTY || getAggregation() == UAssociationType.ASSOCIATION)
    		prop = "prop";
    	else if  (getAggregation() == UAssociationType.COMPOSITION)
    		prop = "val";
    	else
    		prop = "ref";
    	String str = "UStructualFeature [ " + prop + ": " + getName() + " : " + getType().getName() + " " + getMultiplicity() + " ";
    	if (isReadOnly()) str += " (readonly)]";
    	else str += "]";
        return str;
    }

    @Override
    public void set(UObject instance, Object _value) {
        if (instance == null)
            throw new NullPointerException("Instance may not be null");
        if (mSetter == null)
            throw new NullPointerException(
                    "Can not set value, no setter available, may the feature " + getName() + " is read only");
        mSetter.set(instance, _value);
    }

    @Override
    public Object get(UObject instance) {
        if (instance == null)
            throw new NullPointerException("Instance may not be null");
        if (mGetter == null)
            throw new NullPointerException("Can not get value, no getter available");
        return mGetter.get(instance);
    }

    @Override
    public void setReadOnly(boolean b) {
        mReadOnly = b;
    }

    public void setAcessors(IFeatureGetter getter, IFeatureSetter setter) {
        mGetter = getter;
        mSetter = setter;
    }

    @Override
    public Object invoke(UObject instance, Object parameter) {
        if (parameter == null)
            return get(instance);
        set(instance, parameter);
        return null;
    }
}
