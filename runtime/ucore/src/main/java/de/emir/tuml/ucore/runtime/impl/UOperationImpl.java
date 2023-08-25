package de.emir.tuml.ucore.runtime.impl;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.List;
import de.emir.tuml.ucore.runtime.IStructuralElement;
import de.emir.tuml.ucore.runtime.Notification;

import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UMultiplicity;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.impl.UNamedElementImpl;
import de.emir.tuml.ucore.runtime.UParameter;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;

/**
 * @generated
 */
@UMLImplementation(classifier = UOperation.class)
public class UOperationImpl extends UNamedElementImpl implements UOperation {
    /**
     * @generated
     */
    private boolean mAbstract = false;
    /**
     * @generated
     */
    private boolean mConst = false;
    /**
     * @generated
     */
    private List<UParameter> mParameters = null;
    private IOperationInvoker mInvoker;

    /**
     * Default constructor
     * 
     * @generated
     */
    public UOperationImpl() {
        super();
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public UOperationImpl(final UOperation _copy) {
        super(_copy);
        mAbstract = _copy.getAbstract();
        mConst = _copy.getConst();
        mParameters = _copy.getParameters();
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public UOperationImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name,
            boolean _abstract, boolean _const, List<UParameter> _parameters) {
        super(_documentation, _annotations, _package, _name);
        mAbstract = _abstract;
        mConst = _const;
        mParameters = _parameters;
    }

    public UOperationImpl(String name, boolean isAbstract, IOperationInvoker invoker) {
        setName(name);
        setAbstract(isAbstract);
        mInvoker = invoker;
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.UOperation;
    }

    /**
     * @generated
     */
    public void setAbstract(boolean _abstract) {
        if (needNotification(RuntimePackage.Literals.UOperation_abstract)) {
            boolean _oldValue = mAbstract;
            mAbstract = _abstract;
            notify(_oldValue, mAbstract, RuntimePackage.Literals.UOperation_abstract, NotificationType.SET);
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
    public void setConst(boolean _const) {
        if (needNotification(RuntimePackage.Literals.UOperation_const)) {
            boolean _oldValue = mConst;
            mConst = _const;
            notify(_oldValue, mConst, RuntimePackage.Literals.UOperation_const, NotificationType.SET);
        } else {
            mConst = _const;
        }
    }

    /**
     * @generated
     */
    public boolean getConst() {
        return mConst;
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
        UParameter p = getReturParameter();
        if (p == null) {
            return TypeUtils.getPrimitiveType(void.class);
        }
        return p.getType();
    }

    private UParameter getReturParameter() {
        for (UParameter p : getParameters()) {
            if (p.getDirection() == UDirectionType.RETURN)
                return p;
        }
        return null;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public UMultiplicity getMultiplicity() {
        UParameter p = getReturParameter();
        if (p == null) {
            return new UMultiplicityImpl(0, 0);
        }
        return p.getMultiplicity();
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public boolean isMany() {
        return getMultiplicity().getUpper() != 1;
    }

    /**
     * @generated
     */
    public List<UParameter> getParameters() {
        if (mParameters == null) {
            mParameters = new UContainmentList<UParameter>(this, RuntimePackage.theInstance.getUOperation_parameters());
        }
        return mParameters;
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
        return "UOperationImpl{" + " documentation = " + getDocumentation() + " name = " + getName() + " abstract = "
                + getAbstract() + " const = " + getConst() + "}";
    }

    @Override
    public Object invoke(UObject instance, Object parameter) {
        if (instance == null)
            throw new NullPointerException("Instance may not be null");
        if (mInvoker == null)
            throw new NullPointerException(
                    "Can not set value, no setter available, may the feature " + getName() + " is read only");
        if (parameter instanceof Object[] == false)
            return mInvoker.invoke(instance, new Object[] { parameter });

        return mInvoker.invoke(instance, (Object[]) parameter);
    }

    @Override
    public UParameter getReturnParameter() {
        for (UParameter p : getParameters())
            if (p.getDirection() == UDirectionType.RETURN)
                return p;
        return null;
    }
}
