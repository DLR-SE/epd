package de.emir.tuml.ucore.runtime.extension;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;

public class ExtensionPointManager {
    private static Map<String, IExtensionPoint> extensionPoints = new HashMap<String, IExtensionPoint>();
    private static Map<Class<? extends IExtensionPoint>, IExtensionPoint> extensionPointsByClass = new HashMap<Class<? extends IExtensionPoint>, IExtensionPoint>();
    private static Logger log = ULog.getLogger(ExtensionPointManager.class.getName());

    public static void registerExtensionPoint(String id, IExtensionPoint ep) {

        if (extensionPoints.containsKey(id) == true) {

            log.error("Extension Point with id [" + id + "] already exists.");
            return;

        }
        extensionPointsByClass.put(ep.getClass(), ep);
        extensionPoints.put(id, ep);
        log.debug("Extension Point with ID [" + id + "] registered.");
    }

    public static IExtensionPoint getExtensionPoint(String id) {
        return extensionPoints.get(id);

    }

    @SuppressWarnings("unchecked")
    public static <T extends IExtensionPoint> T getExtensionPoint(Class<T> extClass) {
        T res = (T) extensionPointsByClass.get(extClass);
        if (res != null)
            return res;
        // if we could not find the instance directly, we check if one of our instances inherits from this type
        // the first positive check will be assiciated (and remembered)
        for (Class<?> c : extensionPointsByClass.keySet()) {
            if (TypeUtils.inherits(c, extClass)) {
                extensionPointsByClass.put(extClass, extensionPointsByClass.get(c));
                return (T) extensionPointsByClass.get(extClass);
            }
        }
        return null;
    }

}
