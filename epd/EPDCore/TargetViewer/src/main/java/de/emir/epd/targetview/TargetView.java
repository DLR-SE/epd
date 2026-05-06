package de.emir.epd.targetview;

import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.targetview.ids.TargetBasics;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.detection.ITarget;
import de.emir.model.universal.detection.ITrackedTarget;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.PhysicalObjectUtils;
import de.emir.model.universal.units.*;
import de.emir.rcp.manager.SelectionManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.views.AbstractScalingListView;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.prop.IProperty;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Target viewer which displays detailed target information for the selected or focused target on the map in a separate UI element.
 */
public class TargetView extends AbstractScalingListView {
    private PhysicalObject currentTarget;
    private PhysicalObject currentSelectedTarget;
    private PhysicalObject currentFocusedTarget;
    private ITreeValueChangeListener valueChangeListener;
    private final IProperty<Boolean> propDisplayProperties;
    private final Map<String, String> template;

    /**
     * Creates a new TargetView.
     *
     * @param id ID of the view.
     */
    public TargetView(String id) {
        super(id);
        PropertyContext ctx = PropertyStore.getContext(TargetBasics.TARGET_VIEWER_PROP_CONTEXT);
        propDisplayProperties = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_DISPLAY_PROPERTIES, true);
        template = createTargetTemplate();
        for (Map.Entry<String, String> entry : template.entrySet()) {
            setEntry(entry.getKey(), entry.getValue());
        }
        // Use prototype labels for all values to prevent scaling
        // when values change.
        setPrototype("ID", "X".repeat(10));
        setPrototype("NAME", "X".repeat(20));
        setPrototype("COG", "XXX.XX°");
        setPrototype("SOG", "XXX.XXkn");
        setPrototype("UTC", "XX:XX:XX");
        setPrototype("POS", "XX° XX' XX'' / XX° XX' XX''");
        setBaseFontSizes(18f, 26f);
        setBaseRowCount(5);
        setHeightCalculation(true);
        addListeners();
    }

    /**
     * Adds model listeners in order to update the view dynamically
     * on model changes.
     */
    private void addListeners() {
        SelectionManager sm = PlatformUtil.getSelectionManager();
        sm.subscribe(MVBasic.MAP_FOCUS_CTX, oo -> {
            if (oo.isPresent() && oo.get() instanceof PhysicalObject && (oo.get() instanceof ITarget || oo.get() instanceof ITrackedTarget)) {
                currentFocusedTarget = (PhysicalObject) oo.get();
                // If the current selected target changed, remove treelisteners for the old
                // target, set the new target and register the listeners.
                if (currentTarget != currentFocusedTarget) {
                    updateTarget(currentFocusedTarget, extractBaseTargetData(currentFocusedTarget), extractDynamicTargetData(currentFocusedTarget));
                }
            } else {
                currentFocusedTarget = null;
                if (currentSelectedTarget == null) {
                    updateTarget(null, template, extractDynamicTargetData(null));
                } else {
                    updateTarget(currentSelectedTarget, extractBaseTargetData(currentSelectedTarget), extractDynamicTargetData(currentSelectedTarget));
                }
            }
        });
        sm.subscribe(MVBasic.MAP_SELECTION_CTX, oo -> {
            if (oo.isPresent() && oo.get() instanceof PhysicalObject && (oo.get() instanceof ITarget || oo.get() instanceof ITrackedTarget)) {
                currentSelectedTarget = (PhysicalObject) oo.get();
                if (currentTarget != currentSelectedTarget) {
                    updateTarget(currentSelectedTarget, extractBaseTargetData(currentSelectedTarget), extractDynamicTargetData(currentSelectedTarget));
                }
            } else {
                currentSelectedTarget = null;
                if (currentFocusedTarget == null) {
                    updateTarget(null, template, extractDynamicTargetData(null));
                } else {
                    updateTarget(currentFocusedTarget, extractBaseTargetData(currentFocusedTarget), extractDynamicTargetData(currentFocusedTarget));
                }
            }
        });
        valueChangeListener = notification -> {
            if (currentTarget != null) {
                Map<String, String> baseData = extractBaseTargetData(currentTarget);
                Map<String, String> dynamicData = extractDynamicTargetData(currentTarget);
                updateTarget(currentTarget, baseData, dynamicData);  // Refresh values
            } else {
                initializePanel(template);
            }
        };
    }

    /**
     * Adds a value to the list or updates it.
     *
     * @param key   Key of the value to add/update.
     * @param value Value to set.
     */
    private void addOrUpdateField(String key, String value) {
        setEntry(key, value != null ? value : "Unknown");
    }

    /**
     * Extracts the base data of a target. This data is always present
     * in this view.
     *
     * @param target Target to extract data from.
     * @return Map with base data of the target.
     */
    private Map<String, String> extractBaseTargetData(PhysicalObject target) {
        Map<String, String> values = new LinkedHashMap<>();
        if (target != null) {
            if (target instanceof ITarget it) {
                values.put("ID", it.getId());
                if (it.getTimestamp() != null) {
                    values.put("UTC", formatTimestamp(it.getTimestamp().getDateTime()));
                } else {
                    values.put("UTC", "Unknown");
                }
            }
            if (target instanceof ITrackedTarget it) {
                values.put("ID", it.getId());
                if (it.getTrack() != null && it.getTrack().getLastUpdate() != null) {
                    values.put("UTC", formatTimestamp(it.getTrack().getLastUpdate().getDateTime()));
                } else {
                    values.put("UTC", "Unknown");
                }
            }
            values.put("NAME", target.getNameAsString());
            Angle cog = PhysicalObjectUtils.getCOG(target);
            if (cog != null) {
                values.put("COG", String.format("%.2f°", cog.getAs(AngleUnit.DEGREE)));
            } else {
                values.put("COG", "Unknown");
            }
            if (target.getPose() != null && target.getPose().getCoordinate() != null) {
                String readablePosition = CRSUtils.toDegreeMinuteSecond(target.getPose().getCoordinate().getLatitude()) + " / " +
                        CRSUtils.toDegreeMinuteSecond(target.getPose().getCoordinate().getLongitude());
                values.put("POS", readablePosition);
            } else {
                values.put("POS", "Unknown");
            }
            Speed speed = PhysicalObjectUtils.getSOG(target);
            if (speed != null) {
                values.put("SOG", String.format("%.2fkn", speed.getAs(SpeedUnit.KNOTS)));
            } else {
                values.put("SOG", "Unknown");
            }
        } else {
            values.putAll(template);
        }
        return values;
    }

    /**
     * Displays the data of a target on the view.
     *
     * @param newTarget            Target to set value for.
     * @param data                 Base data to set for the target.
     * @param additionalProperties Additional properties to set for the target.
     */
    public void updateTarget(PhysicalObject newTarget, Map<String, String> data, Map<String, String> additionalProperties) {
        boolean isNewTarget = !Objects.equals(newTarget, currentTarget);
        if (isNewTarget) {
            if (currentTarget != null) {
                currentTarget.removeTreeListener(valueChangeListener);
            }
            currentTarget = newTarget;
            if (currentTarget != null) {
                currentTarget.registerTreeListener(valueChangeListener);
            }
            initializePanel(template);
            if (newTarget instanceof ITarget) {
                addOrUpdateField("RNG", data.get("RNG"));
                addOrUpdateField("BRG", data.get("BRG"));
            }
            for (Map.Entry<String, String> entry : data.entrySet()) {
                addOrUpdateField(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<String, String> entry : additionalProperties.entrySet()) {
                addOrUpdateField(entry.getKey(), entry.getValue());
            }
            redraw();
        } else {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                addOrUpdateField(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<String, String> entry : additionalProperties.entrySet()) {
                addOrUpdateField(entry.getKey(), entry.getValue());
            }
        }
        rootPanel.revalidate();
        rootPanel.repaint();
    }

    /**
     * Extracts the dynamic data of a target. This is data that is not statically
     * defined within this view but appended to the list depending on the available
     * data.
     *
     * @param target Target to extract dynamic data from.
     * @return Dynamic data of the passed target.
     */
    private Map<String, String> extractDynamicTargetData(PhysicalObject target) {
        Map<String, String> values = new LinkedHashMap<>();
        if (target != null) {
            if (target instanceof ITarget it) {
                if (it.getReferenceBearing() != null) {
                    values.put("BRG", String.format("%.2f°", it.getReferenceBearing().getAs(AngleUnit.DEGREE)));
                } else {
                    values.put("BRG", "Unknown");
                }
                if (it.getReferenceDistance() != null) {
                    values.put("RNG", String.format("%.2fm", it.getReferenceDistance().getAs(DistanceUnit.METER)));
                } else {
                    values.put("RNG", "Unknown");
                }
            }
            if (propDisplayProperties.getValue() && target.getAllProperties() != null) {
                for (IProperty<?> property : target.getAllProperties()) {
                    if (property.getName().equals("tcpa")) {
                        values.put("TCPA", String.format("%.2fmin", Double.valueOf(property.getValue().toString())));
                        continue;
                    }
                    if (property.getName().equals("dcpa")) {
                        values.put("CPA", String.format("%.2fNM", Double.valueOf(property.getValue().toString())));
                        continue;
                    }
                    if (property.getName().equals("status")) {
                        values.put("STAT", property.getValue().toString());
                        continue;
                    }
                    if (property.getName().equals("acquisitionType")) {
                        values.put("ACQ", property.getValue().toString());
                        continue;
                    }
                    if (property.getName().equals("lastReceiveTimestamp")) {
                        continue;
                    }
                    values.put(property.getName(), property.getValue().toString());
                }
            }
        }
        return values;
    }

    /**
     * Creates a new template for the Target View. This template is the default set of attributes which are displayed
     * in the Target View panel.
     */
    private Map<String, String> createTargetTemplate() {
        Map<String, String> template = new LinkedHashMap<>();
        template.put("ID", "Unknown");
        template.put("NAME", "Unknown");
        template.put("COG", "Unknown");
        template.put("SOG", "Unknown");
        template.put("UTC", "Unknown");
        template.put("POS", "Unknown");
        return template;
    }

    /**
     * Formats the UTC timestamp of the targets to a string time.
     *
     * @param instant Instant to format to HH:mm:SS.
     * @return String time of the instant.
     */
    private String formatTimestamp(Instant instant) {
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("HH:mm:ss").withZone(ZoneOffset.UTC);
        return formatter.format(instant);
    }

    /**
     * Initializes the panel. This deletes all dynamic entries, resets
     * all other values to the base values and reassigns the string
     * prototypes to reset the layout.
     *
     * @param baseTemplate
     */
    private void initializePanel(Map<String, String> baseTemplate) {
        for (String key : baseTemplate.keySet()) {
            setEntry(key, baseTemplate.get(key));
        }
        valueLabels.keySet().retainAll(baseTemplate.keySet());
        setPrototype("ID", "X".repeat(10));
        setPrototype("NAME", "X".repeat(20));
        setPrototype("COG", "XXX.XX°");
        setPrototype("SOG", "XXX.XXkn");
        setPrototype("UTC", "XX:XX:XX");
        setPrototype("POS", "XX° XX' XX'' / XX° XX' XX''");
        redraw();
    }

    @Override
    public void onOpen() {

    }

    @Override
    public void onClose() {

    }
}
