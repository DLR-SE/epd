package de.emir.tuml.ucore.runtime.extension;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import de.emir.tuml.ucore.runtime.logging.ULog;

public class ServiceManager {
    private static Map<String, IService> extensionPoints = new HashMap<String, IService>();
    private static Map<Class<? extends IService>, IService> extensionPointsByClass = new HashMap<Class<? extends IService>, IService>();
    private static Logger log = ULog.getLogger(ServiceManager.class.getName());

    public static void register(IService ep) {
        register(ep.getClass().getName(), ep);
    }

    public static void register(String id, IService ep) {
        if (extensionPoints.containsKey(id) == true) {
            log.error("Extension Point with id [" + id + "] already exists.");
            return;
        }
        extensionPointsByClass.put(ep.getClass(), ep);
        extensionPoints.put(id, ep);
        log.debug("Extension Point with ID [" + id + "] registered.");
    }

    @SuppressWarnings("unchecked")
    public static <T extends IService> T getByID(String id) {
        return (T) extensionPoints.get(id);

    }

    @SuppressWarnings("unchecked")
    public static <T extends IService> T get(Class<T> extClass) {
        return (T) extensionPointsByClass.get(extClass);

    }

}
