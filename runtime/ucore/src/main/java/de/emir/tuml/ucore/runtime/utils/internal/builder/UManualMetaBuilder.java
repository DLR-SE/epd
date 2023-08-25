package de.emir.tuml.ucore.runtime.utils.internal.builder;

import com.google.common.reflect.Invokable;

import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UParameter;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.tuml.ucore.runtime.impl.UClassImpl;
import de.emir.tuml.ucore.runtime.impl.UEnumImpl;
import de.emir.tuml.ucore.runtime.impl.UEnumeratorImpl;
import de.emir.tuml.ucore.runtime.impl.UInterfaceImpl;
import de.emir.tuml.ucore.runtime.impl.UMultiplicityImpl;
import de.emir.tuml.ucore.runtime.impl.UOperationImpl;
import de.emir.tuml.ucore.runtime.impl.UParameterImpl;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;

public class UManualMetaBuilder extends UMetaBuilder {

    public UClass createClass(String name, boolean isAbstract, Class<?> interface_clazz, Class<? extends UObject> impl_clazz) {
        UClass cl = new UClassImpl(name, isAbstract, impl_clazz);
        UCoreMetaRepository.registerClassifier(interface_clazz.getName(), cl); // need to additional register the
                                                                               // interface with the same classifier
        UCoreMetaRepository.registerClassifier(name, cl);
        return cl;
    }

    public UInterface createInterface(String name, Class<?> clazz) {
        return new UInterfaceImpl(name, clazz);
    }

    public UEnum createEnumeration(String name, Class<?> clazz) {
        return new UEnumImpl(name, clazz);
    }

    public UEnumerator addLiteral(UEnum owner, String name, int value, Object instanceValue) {
        UEnumerator lit = new UEnumeratorImpl(instanceValue);
        lit.setName(name);
        lit.setValue(value);
        owner.getLiterals().add(lit);
        return lit;
    }

    public UStructuralFeature createFeature(String name, UType type, UAssociationType associationType, int lowerBound,
            int upperBound) {
        Invokable getter = null;
        Invokable setter = null;
        return new UStructuralFeatureImpl(name, type,
                new UMultiplicityImpl(lowerBound, upperBound), associationType, getter, setter, null);
    }

    public void setInstanceCreator(UClass cl, IInstanceCreator ic) {
        if (cl instanceof UClassImpl)
            ((UClassImpl) cl).setInstanceCreator(ic);
    }

    public void setFeatureAccessor(UStructuralFeature feature, IFeatureGetter getter, IFeatureSetter setter) {
        if (feature instanceof UStructuralFeatureImpl) {
            ((UStructuralFeatureImpl) feature).setAcessors(getter, setter);
        }

    }

    public UOperation createOperation(String name, boolean isAbstract, UType returnType, int lowerBound,
            int upperBound) {
        return createOperation(name, isAbstract, returnType, lowerBound, upperBound, null);
    }

    public UOperation createOperation(String name, boolean isAbstract, UType returnType, int lowerBound, int upperBound,
            IOperationInvoker invoker) {
        UOperationImpl op = new UOperationImpl(name, isAbstract, invoker);
        if (returnType != null) {
            addParameter(op, "return", returnType, lowerBound, upperBound, UDirectionType.RETURN);
        }
        return op;
    }

    public UParameter addParameter(UOperation operation, String name, UType type, int lower, int upper,
            UDirectionType direction) {
        UParameterImpl param = new UParameterImpl();
        param.setType(type);
        param.setName(name);
        param.setMultiplicity(new UMultiplicityImpl(lower, upper));
        param.setDirection(direction);
        operation.getParameters().add(param);
        return param;
    }

}
