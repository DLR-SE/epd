package de.emir.tuml.ucore.runtime.access.impl;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;

public class ReflectiveInstanceCreator implements IInstanceCreator {

    private Class<?> mClazz;

    public ReflectiveInstanceCreator(Class<?> clazz) {
        mClazz = clazz;
    }

    @Override
    public UObject createNewInstance() {
        Object obj;
        try {
            obj = mClazz.newInstance();
            return (UObject) obj;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
