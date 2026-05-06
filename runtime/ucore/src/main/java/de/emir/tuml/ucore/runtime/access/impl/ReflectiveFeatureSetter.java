package de.emir.tuml.ucore.runtime.access.impl;

import java.lang.reflect.InvocationTargetException;

import com.google.common.reflect.Invokable;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class ReflectiveFeatureSetter implements IFeatureSetter {

    private final Invokable<UObject, ?> mInvokable;

    public ReflectiveFeatureSetter(Invokable<UObject, ?> invokable) {
        mInvokable = invokable;
    }

    @Override
    public void set(UObject instance, Object value) {
        try {
            mInvokable.invoke(instance, value);
        } catch (InvocationTargetException | IllegalAccessException e) {
            ULog.error(e);
        }
    }

}
