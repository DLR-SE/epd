package de.emir.epd.nmeasensor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import de.emir.epd.nmeasensor.data.ReceiverProperty;
import de.emir.epd.nmeasensor.ids.NMEASensorIds;
import de.emir.epd.nmeasensor.settings.NMEASensorSettingsPage;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.prop.ValueProperty;

public class NMEASensors {
    /** Last settings update. */
    IProperty<Long> lastUpdate;
	/** NMEA Sensors identified by string. */
	Map<String, NMEASensor> nmeaSensors;
	/** Property context. */
	private PropertyContext context;
	/** NMEA sources property container. */
	private IProperty<List<Map<String, Object>>> nmeaSources;
	public Logger LOG = ULog.getLogger(NMEASensors.class);

	public NMEASensors() {
		nmeaSensors = new HashMap<>();
		this.context = PropertyStore.getContext(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT);
		nmeaSources = getContext().getProperty(NMEASensorIds.NMEA_SENSOR_PROP, new ArrayList<Map<String, Object>>());
        lastUpdate = getContext().getProperty(NMEASensorIds.NMEA_SENSOR_PROP_NMEA_SOURCE, 0L);
        
		NMEASensorSettingsPage.printProperty(nmeaSources);
		PropListener propListener = new PropListener();

		if (nmeaSources.getValue() == null) {
			nmeaSources.setValue(null);
		}
		lastUpdate.addPropertyChangeListener(propListener);
		
		if (nmeaSources.getValue() != null) {
			for (Map<String, Object> rpmap : nmeaSources.getValue()) {
				ReceiverProperty rp = new ReceiverProperty(rpmap);
				if (nmeaSensors.containsKey(rp.getLabel())) {
					//prop.notify();
				} else {
					NMEASensor sensor = new NMEASensor(rp, nmeaSources);
					nmeaSensors.put(rp.getLabel(), sensor);
				}
			}
		}
		
		NMEAVesselUpdater.init();
	}
	
	public NMEASensor getSensor(String id) {
		return nmeaSensors.get(id);
	}

	class PropListener implements PropertyChangeListener {
		@Override
		public synchronized void propertyChange(PropertyChangeEvent evt) {
			LOG .info("property changed");
			nmeaSources = getContext().getProperty(NMEASensorIds.NMEA_SENSOR_PROP, new ArrayList<Map<String, Object>>());

			List<Map<String, Object>> rps = nmeaSources.getValue();
			Iterator<Map.Entry<String, NMEASensor>> it = nmeaSensors.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, NMEASensor> sensor = it.next();
				boolean found = false;
				for (Map<String, Object> item : rps) {
					found = sensor.getKey().equals(item.get("label"));
					if (found) break;
				}
				if (!found) {
					sensor.getValue().remove();
					it.remove();
				}
			}
			for (Map<String, Object> rpmap : nmeaSources.getValue()) {
				ReceiverProperty rp = new ReceiverProperty(rpmap);
				if (nmeaSensors.containsKey(rp.getLabel())) {
					NMEASensor sensor = nmeaSensors.get(rp.getLabel());
					synchronized (sensor) {
						sensor.update(rp);
					}
				} else {
					NMEASensor sensor = new NMEASensor(rp, nmeaSources);
					nmeaSensors.put(rp.getLabel(), sensor);
				}
			}
		}
	}

	public void initializeSensors() {
		
	}

	protected PropertyContext getContext() {
		return this.context;
	}

}
