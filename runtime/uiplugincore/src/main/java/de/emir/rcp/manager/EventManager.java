package de.emir.rcp.manager;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import com.google.common.eventbus.EventBus;

import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * @author fklein
 *
 */
public class EventManager {

    private static Logger log = ULog.getLogger(EventManager.class);

    private static Map<String, EventBus> eBus = new HashMap<>();

    public static EventBus UI = createEventBus("UI");

    public static EventBus getEventBus(String id) {

        return eBus.get(id);
    }

    public static EventBus createEventBus(String id) {

        if (eBus.get(id) != null) {
            log.warn("EventBus with id [" + id + "] already exists");
            return eBus.get(id);
        }

        eBus.put(id, new EventBus(id));

        return eBus.get(id);
    }

}
