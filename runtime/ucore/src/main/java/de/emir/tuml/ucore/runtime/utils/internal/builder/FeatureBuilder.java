package de.emir.tuml.ucore.runtime.utils.internal.builder;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.common.reflect.Invokable;

import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UMultiplicity;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import de.emir.tuml.ucore.runtime.impl.UClassImpl;
import de.emir.tuml.ucore.runtime.impl.UMultiplicityImpl;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;

public class FeatureBuilder {

    public static List<UStructuralFeature> collectFeatures(UClassImpl cl, Class<?> clazz) {
        ArrayList<UStructuralFeature> out = new ArrayList<UStructuralFeature>();

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] descs = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor desc : descs) {
                UStructuralFeature feature = createFeature(cl, clazz, desc);
                if (feature != null)
                    out.add(feature);
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return out;
    }

    public static UMLProperty getPropertyAnnotation(PropertyDescriptor pd) {
        if (pd.getReadMethod() != null && pd.getReadMethod().isAnnotationPresent(UMLProperty.class))
            return pd.getReadMethod().getAnnotation(UMLProperty.class);
        if (pd.getWriteMethod() != null && pd.getWriteMethod().isAnnotationPresent(UMLProperty.class))
            return pd.getWriteMethod().getAnnotation(UMLProperty.class);
        return null;
    }

    public static UAssociationType getAggregation(AssociationType type) {
        switch (type) {
        case SHARED:
            return UAssociationType.AGGREGATION;
        case COMPOSITE:
            return UAssociationType.COMPOSITION;
        case NONE:
            return UAssociationType.ASSOCIATION;
        case PROPERTY:
            return UAssociationType.PROPERTY;
        }
        return null;
    }

    public static UType getPropertyType(Type typeClass) {
        Type type = typeClass;
        if (typeClass instanceof ParameterizedType) {
            Type grt = type;// pd.getReadMethod().getGenericReturnType();
            if (grt instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) grt;
                type = pt.getActualTypeArguments()[0]; // assumes that we use lists only, no maps
            }
        }
        if (TypeUtils.isPrimitive(type))
            return TypeUtils.getPrimitiveType(type);
        return UCoreMetaRepository.getClassifier((Class<?>) type);
    }

    private static UStructuralFeature createFeature(UClassImpl cl, Class<?> clazz, PropertyDescriptor desc) {
        if (desc.getReadMethod() == null)
            return null; // we can not use it if we can not read the value
        UMLProperty anno = getPropertyAnnotation(desc);
        if (anno == null)
            return null;
        String name = (anno != null && anno.name() != null && anno.name().isEmpty() == false) ? anno.name()
                : desc.getName();

        Invokable getter = Invokable.from(desc.getReadMethod());
        Invokable setter = desc.getWriteMethod() != null ? Invokable.from(desc.getWriteMethod()) : null;

        Type rawType = desc.getReadMethod().getGenericReturnType();
        UType type = getPropertyType(rawType);

        int lower = anno != null ? anno.lowerBound() : 0;
        int upper = anno != null ? anno.upperBound() : 1;
        if (upper == 1 && TypeUtils.isList(getter.getReturnType().getRawType()))
            upper = -1;
        UMultiplicity multi = new UMultiplicityImpl(lower, upper);

        UAssociationType assoType = getAggregation(anno != null ? anno.associationType() : AssociationType.PROPERTY);

        return new UStructuralFeatureImpl(name, type, multi, assoType, getter, setter, desc);
    }

}
