package de.emir.tuml.ucore.runtime.extension;

import java.util.HashMap;
import java.util.Map;

import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import org.apache.logging.log4j.Logger;

public class ExtensionPointManager {

    private static final Logger LOG = ULog.getLogger(ExtensionPointManager.class.getName());

    private static final Map<String, IExtensionPoint> extensionPoints = new HashMap<String, IExtensionPoint>();
    private static final Map<Class<? extends IExtensionPoint>, IExtensionPoint> extensionPointsByClass = new HashMap<Class<? extends IExtensionPoint>, IExtensionPoint>();

    public static void registerExtensionPoint(String id, IExtensionPoint ep) {
        assert LOG != null;

        if (extensionPoints.containsKey(id)) {
            LOG.error("Extension Point with id [{}] already exists.", id);
            return;
        }

        extensionPointsByClass.put(ep.getClass(), ep);
        extensionPoints.put(id, ep);
        LOG.debug("Extension Point with ID [{}] registered.", id);
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
        // the first positive check will be associated (and remembered)
        for (Class<?> c : extensionPointsByClass.keySet()) {
            if (TypeUtils.inherits(c, extClass)) {
                extensionPointsByClass.put(extClass, extensionPointsByClass.get(c));
                return (T) extensionPointsByClass.get(extClass);
            }
        }
        return null;
    }

}
