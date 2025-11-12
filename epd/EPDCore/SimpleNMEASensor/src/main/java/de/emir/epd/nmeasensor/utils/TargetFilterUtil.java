package de.emir.epd.nmeasensor.utils;

import de.emir.model.universal.detection.ITrackedTarget;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.runtime.epf.PluginManager;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Contains utilities for loading target reference configurations from properties.
 */
public class TargetFilterUtil {
    private final Map<String, TargetFilter> filters = new ConcurrentHashMap<>();
    private final PropertyContext context = PropertyStore.getContext("de.emir.epd.target.propertiesContext.TargetViewerPropertiesContext");
    private final IProperty<String> parentProperty;

    /**
     * Sets up the target filter utilities and queries the current configuration from the property storage.
     */
    public TargetFilterUtil() {
        parentProperty = context.getProperty("targetPropertyReferenceTargets", "");
        if(parentProperty.getSubProperties() != null) {
            if(!parentProperty.getSubProperties().isEmpty()) {
                filters.clear();
                filters.putAll(loadConfiguration(parentProperty));
            }
        }

        parentProperty.addPropertyChangeListener(event -> {
            filters.clear();
            filters.putAll(loadConfiguration(parentProperty));
        });
    }

    /**
     * Gets a target filter for a given sensor.
     * @param sensorIdentifier Name of sensor to get target filter for.
     * @return Target filter if found, else null.
     */
    public TargetFilter getTargetFilter(String sensorIdentifier) {
        return filters.get(sensorIdentifier);
    }

    /**
     * Sets the reference target for a given target filter.
     * @param sensorIdentifier Name of the sensor to select reference for.
     * @param reference Reference to use for the sensor.
     */
    public void setReference(String sensorIdentifier, ITrackedTarget reference) {
        if(filters.containsKey(sensorIdentifier) && reference.getPose() != null && filters.get(sensorIdentifier).mode.equals(Mode.REFERENCE)) {
            filters.get(sensorIdentifier).reference = reference.getPose().getCoordinate();
        }
    }

    /**
     * Loads a target filter based on a subproperty entry.
     * @param properties Subproperty which contains the reference settings configuration.
     * @return Target filter retrieved from the properties.
     */
    private TargetFilter loadFilter(IProperty<?> properties) {
        String sensorIdentifier = "";
        Mode mode = Mode.OWNSHIP;
        Coordinate coordinate = null;

        List<IProperty<?>> subProperties = properties.getSubProperties();
        Double lat = null, lon = null;
        for(IProperty<?> property : subProperties) {
            switch (property.getName()) {
                case "sensorIdentifier" -> sensorIdentifier = String.valueOf(property.getValue());
                case "latitude" -> {
                    if (property.getValue() != null && !String.valueOf(property.getValue()).isEmpty()) lat = Double.parseDouble(String.valueOf(property.getValue()));
                }
                case "longitude" -> {
                    if (property.getValue() != null && !String.valueOf(property.getValue()).isEmpty()) lon = Double.parseDouble(String.valueOf(property.getValue()));
                }
                case "mode" -> mode = Mode.valueOf(String.valueOf(property.getValue()));
            }
        }
        if(lat != null && lon != null && mode.equals(Mode.FIXED)) {
            coordinate = new CoordinateImpl();
            coordinate.setLatLon(lat, lon);
        }
        return new TargetFilter(sensorIdentifier, mode, coordinate);
    }

    /**
     * Loads all target filters from the configuration.
     * @param properties Parent property which contains the list of reference setting configuration entries.
     * @return Map of target filters containing the sensor name as a key and the target filter configuration for the name as the value.
     */
    private Map<String, TargetFilter> loadConfiguration(IProperty<?> properties) {
        Map<String, TargetFilter> result = new HashMap<>();
        if(properties.getSubProperties() != null) {
            for(IProperty<?> property : properties.getSubProperties()) {
                TargetFilter entry = loadFilter(property);
                result.put(entry.sensorIdentifier, entry);
            }
        }
        return result;
    }

    /**
     * Target filter object encapsulating the extracted information of the reference settings configuration.
     */
    public class TargetFilter {
        Coordinate reference;
        Mode mode;
        String sensorIdentifier;

        /**
         * Creates a new TargetFilter object.
         * @param sensorIdentifier Name of the sensor which should be configured.
         * @param mode Reference mode which the sensor should use.
         * @param reference Reference coordinate which the sensor should use. Null if no reference is used.
         */
        public TargetFilter(String sensorIdentifier, Mode mode, Coordinate reference) {
            this.sensorIdentifier = sensorIdentifier;
            this.mode = mode;
            this.reference = reference;
        }

        /**
         * Gets the name of the sensor.
         * @return Name of the sensor which is configured.
         */
        public String getSensorIdentifier() {
            return sensorIdentifier;
        }

        /**
         * Gets the reference coordinate of the configured sensor.
         * @return Reference coordinate if configured, else null.
         */
        public Coordinate getReference() {
            return reference;
        }

        /**
         * Gets the configured reference mode of the configured sensor.
         * @return Mode used for the sensor.
         */
        public Mode getMode() {
            return mode;
        }
    }

    /**
     * Reference mode representation used for determining which reference position should be used for target
     * georeferencing. FIXED represents a fixed coordinate as the reference
     * target for targets which contain a bearing and distance. REFERENCE refers to the usage of reference targets
     * supplied with the data source for target referencing. OWNSHIP refers to the usage of the models internal ownship
     * object as the data source for target referencing. This mirrors the modes defined in the TargetViewer. In order
     * to decouple the viewer, these are defined here again.
     */
    public enum Mode {
        OWNSHIP,
        FIXED,
        REFERENCE
    }
}
