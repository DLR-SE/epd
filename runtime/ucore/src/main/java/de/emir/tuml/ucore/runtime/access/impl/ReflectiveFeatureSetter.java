package de.emir.tuml.ucore.runtime.access.impl;

import java.lang.reflect.InvocationTargetException;

import com.google.common.reflect.Invokable;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;

public class ReflectiveFeatureSetter implements IFeatureSetter {

    private Invokable mInvokable;

    public ReflectiveFeatureSetter(Invokable invokable) {
        mInvokable = invokable;
    }

    @Override
    public void set(UObject instance, Object value) {
        try {
            mInvokable.invoke(instance, value);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
