package de.emir.tuml.ucore.runtime.access.impl;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.logging.ULog;

import java.lang.reflect.InvocationTargetException;

public class ReflectiveInstanceCreator implements IInstanceCreator {

    private final Class<?> mClazz;

    public ReflectiveInstanceCreator(Class<?> clazz) {
        mClazz = clazz;
    }

    @Override
    public UObject createNewInstance() {
        Object obj;
        try {
            // The call `clazz.newInstance()` is deprecated since Java 1.9 and was replaced by
            // `clazz.getDeclaredConstructor().newInstance()` according to the documentation.
            obj = mClazz.getDeclaredConstructor().newInstance();
            return (UObject) obj;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            ULog.error(e);
        }
        return null;
    }
}
