package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.logging.ULog;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class DelegateFactory {

    public static class DelegateProvider implements IDelegateProvider {
        private Class<?> mClazz;

        public DelegateProvider(Class<?> cl) {
            mClazz = cl;
        }

        @Override
        public Object provideDelegate(UClassifier cl, UObject obj) {
            try {
                // The call `clazz.newInstance()` is deprecated since Java 1.9 and was replaced by
                // `clazz.getDeclaredConstructor().newInstance()` according to the documentation.
                return mClazz.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                ULog.error(e);
            }
            return null;
        }
    }

    private static final HashMap<UClassifier, IDelegateProvider> mDelegateProviders = new HashMap<>();

    /**
     * Associates the specified value with the specified key in this map. If the map previously contained a mapping for
     * the key, the old value is replaced.
     * 
     * @param cl
     * @param provider
     * @return the previous value associated with key, or null if there was no mapping for key. (A null return can also
     *         indicate that the map previously associated null with key.)
     */
    public static IDelegateProvider registerProvider(UClassifier cl, IDelegateProvider provider) {
        return mDelegateProviders.put(cl, provider);
    }

    @SuppressWarnings("unchecked")
    public static <T> T createDelegate(UObject obj) {
        if (obj == null)
            return null;
        UClass cl = obj.getUClassifier();
        @SuppressWarnings("rawtypes")
        IDelegateProvider provider = mDelegateProviders.get(cl);
        if (provider == null)
            throw new NullPointerException("No IDelegateProvider registered for: " + cl.getName());
        return (T) provider.provideDelegate(cl, obj);
    }

}
