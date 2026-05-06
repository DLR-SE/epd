package de.emir.tuml.ucore.runtime.extension;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceManager {
    private static final Logger LOG = LogManager.getLogger(ServiceManager.class);

    private static final Map<String, IService> extensionPoints = new HashMap<String, IService>();
    private static final Map<Class<? extends IService>, IService> extensionPointsByClass = new HashMap<Class<? extends IService>, IService>();

    public static void register(IService ep) {
        register(ep.getClass().getName(), ep);
    }

    public static void register(String id, IService ep) {
        assert LOG != null;

        if (extensionPoints.containsKey(id)) {
            LOG.error("Extension Point with id [{}] already exists.", id);
            return;
        }

        extensionPointsByClass.put(ep.getClass(), ep);
        extensionPoints.put(id, ep);
        LOG.debug("Extension Point with ID [{}] registered.", id);
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
