package de.emir.tuml.ucore.runtime.utils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import de.emir.tuml.ucore.runtime.BinaryBlob;
import de.emir.tuml.ucore.runtime.UPrimitiveType;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.impl.UPrimitiveTypeImpl;

public class TypeUtils {

    private static HashMap<Class, UPrimitiveType> mPrimitiveTypes = new HashMap<Class, UPrimitiveType>();

    static {
        mPrimitiveTypes.put(boolean.class, new UPrimitiveTypeImpl(boolean.class));
        mPrimitiveTypes.put(byte.class, new UPrimitiveTypeImpl(byte.class));
        mPrimitiveTypes.put(short.class, new UPrimitiveTypeImpl(short.class));
        mPrimitiveTypes.put(char.class, new UPrimitiveTypeImpl(char.class));
        mPrimitiveTypes.put(int.class, new UPrimitiveTypeImpl(int.class));
        mPrimitiveTypes.put(long.class, new UPrimitiveTypeImpl(long.class));
        mPrimitiveTypes.put(float.class, new UPrimitiveTypeImpl(float.class));
        mPrimitiveTypes.put(double.class, new UPrimitiveTypeImpl(double.class));
        
        mPrimitiveTypes.put(Boolean.class, new UPrimitiveTypeImpl(Boolean.class));
        mPrimitiveTypes.put(Byte.class, new UPrimitiveTypeImpl(Byte.class));
        mPrimitiveTypes.put(Short.class, new UPrimitiveTypeImpl(Short.class));
        mPrimitiveTypes.put(Character.class, new UPrimitiveTypeImpl(Character.class));
        mPrimitiveTypes.put(Integer.class, new UPrimitiveTypeImpl(Integer.class));
        mPrimitiveTypes.put(Long.class, new UPrimitiveTypeImpl(Long.class));
        mPrimitiveTypes.put(Float.class, new UPrimitiveTypeImpl(Float.class));
        mPrimitiveTypes.put(Double.class, new UPrimitiveTypeImpl(Double.class));

        mPrimitiveTypes.put(String.class, new UPrimitiveTypeImpl(String.class));
        mPrimitiveTypes.put(Object.class, new UPrimitiveTypeImpl(Object.class));
        mPrimitiveTypes.put(Void.class, new UPrimitiveTypeImpl(Void.class));
        mPrimitiveTypes.put(void.class, new UPrimitiveTypeImpl(void.class));
        
        mPrimitiveTypes.put(BinaryBlob.class, new UPrimitiveTypeImpl(BinaryBlob.class));
    }

    public static boolean isPrimitive(Type type) {
        return mPrimitiveTypes.containsKey(type);
    }

    public static UPrimitiveType getPrimitiveType(Type t) {
        return mPrimitiveTypes.get(t);
    }

    private static HashMap<Class<?>, HashMap<Class<?>, Boolean>> mInheritMap = new HashMap<Class<?>, HashMap<Class<?>, Boolean>>(); // [class,
                                                                                                                                    // [parent,
                                                                                                                                    // inherits]]

    public static boolean inherits(Class<?> clazz, Class<?> parent) {
        if (parent == Object.class)
            return true;
        HashMap<Class<?>, Boolean> t1 = mInheritMap.get(clazz);
        Boolean b = null;
        if (t1 != null) {
            b = t1.get(parent);
            if (b != null)
                return b;
            else {
                b = _inherits(clazz, parent);
                t1.put(parent, b);
            }
        } else {
            t1 = new HashMap<Class<?>, Boolean>();
            b = _inherits(clazz, parent);
            t1.put(parent, b);
            mInheritMap.put(clazz, t1);
        }
        return b;
    }

    public static boolean _inherits(Class<?> clazz, Class<?> parent) {
        if (clazz == parent)
            return true;
        if (clazz.getSuperclass() == parent)
            return true;

        for (Class<?> in : clazz.getInterfaces())
            if (inherits(in, parent))
                return true;
        return false;
    }

    public static boolean isList(Class rawType) {
        return inherits(rawType, List.class);
    }

    public static UType getPrimitiveType(String typeName) {
        for (UPrimitiveType pt : mPrimitiveTypes.values()) {
            if (pt.getName().equals(typeName))
                return pt;
        }
        return null;
    }
}
