package de.emir.tuml.ucore.runtime.access.impl;

import java.lang.reflect.InvocationTargetException;

import com.google.common.reflect.Invokable;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;

public class ReflectiveFeatureGetter implements IFeatureGetter {

    private Invokable mInvokable;

    public ReflectiveFeatureGetter(Invokable inv) {
        mInvokable = inv;
    }

    @Override
    public Object get(UObject instance) {
        if (instance == null)
            throw new NullPointerException("Instance may not be null");
        try {
            return mInvokable.invoke(instance);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
