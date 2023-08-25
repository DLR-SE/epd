package de.emir.tuml.ucore.runtime;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;

/**
 * Static class to register extension points this class is just used as central storage point and has almost no logic.
 * Extensionpoints can either be registered as Class<?> or as instance (Object). to query extensionpoints you can ask
 * for instances that implements a specific Class<?>.
 * 
 * @note if registered as Class<?> each query for this class will result in a new instance
 * @author sschweigert
 *
 */
public class UCoreExtensionManager {

    private static HashSet<Class<?>> mExtensionClasses = new HashSet<>();
    private static HashSet<Object> mExtensionInstances = new HashSet<>();
    private static HashMap<String, Object> mNamedExtensions = new HashMap<>();

    public static void registerExtension(Class<?> clazz) {
        if (clazz != null) {
            ULog.debug("Register Extension: " + clazz.getName());
            mExtensionClasses.add(clazz);
        }
    }

    public static void registerExtension(Object obj) {
        if (obj != null) {
            ULog.debug("Register Extension: " + obj.toString());
            mExtensionInstances.add(obj);
        }
    }

    public static void registerExtension(String id, Object obj) {
        if (id != null && id.isEmpty() == false && obj != null) {
            mNamedExtensions.put(id, obj);
        }
    }

    public static <T> Collection<T> getExtensions(Class<T> clazz) {
        HashSet<T> out = new HashSet<>();
        HashSet<Class<?>> outClasses = new HashSet<>();

        for (Object obj : mExtensionInstances) {
            if (TypeUtils.inherits(obj.getClass(), clazz))
                out.add((T) obj);
        }
        for (Class<?> cl : mExtensionClasses) {
            if (TypeUtils.inherits(cl, clazz)) {
                try {
                    Object nObj = cl.newInstance();
                    if (nObj != null)
                        out.add((T) nObj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return out;
    }

    public static <T> T getExtension(String name, Class<T> class1) {
        if (mNamedExtensions.containsKey(name))
            return (T) mNamedExtensions.get(name);
        return null;
    }

}
