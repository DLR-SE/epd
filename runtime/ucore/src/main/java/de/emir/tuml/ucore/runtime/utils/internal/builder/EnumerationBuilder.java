package de.emir.tuml.ucore.runtime.utils.internal.builder;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.google.common.reflect.Invokable;

import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.tuml.ucore.runtime.impl.UEnumeratorImpl;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class EnumerationBuilder {

    public static ArrayList<UEnumerator> getLiterals(Class<?> clazz) {
        if (clazz.isEnum() == false) {
            return null;
        }
        Invokable valueInv = null, nameInv = null;
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] descs = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor d : descs) {
                if (d.getName().equals("name"))
                    nameInv = Invokable.from(d.getReadMethod());
                if (d.getName().equals("value"))
                    valueInv = Invokable.from(d.getReadMethod());
            }

            if (valueInv == null || nameInv == null)
                return null;

            ArrayList<UEnumerator> out = new ArrayList<UEnumerator>();
            Object[] cs = clazz.getEnumConstants();
            for (Object obj : cs) {
                String name = (String) nameInv.invoke(obj);
                int value = (int) valueInv.invoke(obj);
                UEnumeratorImpl num = new UEnumeratorImpl(obj);
                num.setName(name);
                num.setValue(value);
                out.add(num);
            }

            return out;
        } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
            ULog.error(e);
        }
        return null;
    }

}
