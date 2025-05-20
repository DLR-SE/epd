package de.emir.epd.nmeasensor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import de.emir.epd.nmeasensor.ids.NMEASensorIds;
import de.emir.epd.nmeasensor.settings.NMEASensorSettingsPage;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class NMEASensors {
	/** NMEA Sensors identified by string. */
	Map<String, NMEASensor> nmeaSensors;
	/** Property context. */
	private PropertyContext context;
	/** NMEA sources property container. */
	private IProperty<Integer> nmeaSources;
	public Logger LOG = ULog.getLogger(NMEASensors.class);

	public NMEASensors() {
		nmeaSensors = new HashMap<>();
		context = PropertyStore.getContext(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT);
		nmeaSources = getContext().getProperty(NMEASensorIds.NMEA_SENSOR_PROP, 0);
        
		NMEASensorSettingsPage.printProperty(nmeaSources);
		PropListener propListener = new PropListener();

		if (nmeaSources.getValue() == null) {
			nmeaSources.setValue(null);
		}
		
		if (nmeaSources.getValue() != null && nmeaSources.getSubProperties() != null) {
            for (IProperty sensorProp : nmeaSources.getSubProperties()) {
				if (nmeaSensors.containsKey(sensorProp.getName())) {
//					prop.notify();
				} else {
					NMEASensor sensor = new NMEASensor(sensorProp);
					nmeaSensors.put(sensorProp.getName(), sensor);
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
			nmeaSources = getContext().getProperty(NMEASensorIds.NMEA_SENSOR_PROP, 0);
		}
	}

	public void initializeSensors() {
		
	}

	protected PropertyContext getContext() {
		return this.context;
	}

}
