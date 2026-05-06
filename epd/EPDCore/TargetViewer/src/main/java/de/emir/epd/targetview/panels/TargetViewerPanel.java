package de.emir.epd.targetview.panels;


import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.targetview.ids.TargetBasics;
import de.emir.model.universal.detection.ITarget;
import de.emir.model.universal.detection.ITrackedTarget;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.PhysicalObjectUtils;
import de.emir.model.universal.units.*;
import de.emir.model.universal.units.impl.TimeImpl;
import de.emir.rcp.manager.SelectionManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.prop.IProperty;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;

/**
 * Panel component which contains the presentation logic for the TargetViewer.
 */
public class TargetViewerPanel extends JPanel {
    private final Map<String, JLabel> fieldLabels = new LinkedHashMap<>();
    private final JPanel contentPanel;
    private final Map<String, String> template;
    private PhysicalObject currentTarget;
    private PhysicalObject currentSelectedTarget;
    private PhysicalObject currentFocusedTarget;
    private ITreeValueChangeListener valueChangeListener;
    private final IProperty<Boolean> propDisplayProperties;


    /**
     * Creates a new TargetViewerPanel. This panel contains a template which is the baseline of representation. It contains
     * the at least necessary fields which should be displayed in the viewer. Depending on the type of object which should
     * be displayed, additional attributes can be added but the viewer will fallback to this template.
     *
     * @param template Template to use.
     */
    public TargetViewerPanel(Map<String, String> template) {
        setLayout(new BorderLayout());
        this.template = new LinkedHashMap<>(template);
        PropertyContext ctx = PropertyStore.getContext(TargetBasics.TARGET_VIEWER_PROP_CONTEXT);
        propDisplayProperties = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_DISPLAY_PROPERTIES, true);
        setLayout(new BorderLayout());

        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(new EmptyBorder(10, 0, 10, 10));

        JPanel wrapper = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(20, -20, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;


        wrapper.add(contentPanel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        wrapper.add(Box.createVerticalGlue(), gbc);

        JScrollPane scrollPane = new JScrollPane(wrapper);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        add(scrollPane, BorderLayout.CENTER);
        initializePanel(template);
        setupListener();
    }

    /**
     * Sets up the listeners for the TargetViewerPanel.
     */
    private void setupListener() {
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
            }
        };
    }

    /**
     * Initializes the TargetViewerPanel.
     *
     * @param baseTemplate Template to use for creating the baseline.
     */
    private void initializePanel(Map<String, String> baseTemplate) {
        contentPanel.removeAll();
        fieldLabels.clear();

        for (String key : baseTemplate.keySet()) {
            addField(key, baseTemplate.get(key));
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Adds a field to the TargetViewerPanel. If the value is null, it will be replaced by the placeholder "unknown".
     *
     * @param key   Label of the value to append.
     * @param value Value to display.
     */
    private void addField(String key, String value) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 0, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = contentPanel.getComponentCount() / 2;
        JLabel keyLabel = new JLabel(key + ": ");
        keyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        keyLabel.setForeground(UIManager.getColor("Label.disabledForeground"));
        keyLabel.setFont(keyLabel.getFont().deriveFont(17f));
        gbc.gridx = 0;
        gbc.weightx = 0.2;
        contentPanel.add(keyLabel, gbc);
        gbc.insets = new Insets(4, 4, 4, 4);
        JLabel valueLabel = new JLabel(value != null ? value : "Unknown");
        valueLabel.setForeground(UIManager.getColor("Label.foreground"));
        valueLabel.setFont(valueLabel.getFont().deriveFont(Font.BOLD, 19f));
        valueLabel.setHorizontalAlignment(SwingConstants.LEFT);
        gbc.gridx = 1;
        gbc.weightx = 0.8;
        contentPanel.add(valueLabel, gbc);
        fieldLabels.put(key, valueLabel);
    }

    /**
     * Updates the target, i.e. inserts the current value of a target to the TargetViewerPanel.
     *
     * @param newTarget            Target to insert values for in the UI.
     * @param data                 Base data to display. This is the data also used in the template.
     * @param additionalProperties Additional properties to display below the base data.
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
            // Full reset to base template
            initializePanel(template);
            if (newTarget instanceof ITarget) {
                addOrUpdateField("Distance", data.get("Distance"));
                addOrUpdateField("Bearing", data.get("Bearing"));
            }
            for (Map.Entry<String, String> entry : data.entrySet()) {
                addOrUpdateField(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<String, String> entry : additionalProperties.entrySet()) {
                if(!entry.getKey().equals("lastReceiveTimestamp")) addOrUpdateField(entry.getKey(), entry.getValue());
            }
        } else {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                addOrUpdateField(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<String, String> entry : additionalProperties.entrySet()) {
                if(!entry.getKey().equals("lastReceiveTimestamp")) addOrUpdateField(entry.getKey(), entry.getValue());
            }
        }
        revalidate();
        repaint();
    }

    /**
     * Adds or updates a field. If the field does not exist, it will be appended, else it will be modified.
     *
     * @param key   Name of the field.
     * @param value Value of the field.
     */
    private void addOrUpdateField(String key, String value) {
        JLabel label = fieldLabels.get(key);
        if (label == null) {
            addField(key, value);
        } else {
            label.setText(value != null ? value : "Unknown");
        }
    }

    /**
     * Extracts the base data of a target, i.e. the data matching the passed template. If the target is null, the
     * template will be used as a base for the base data.
     *
     * @param target Target to extract data from.
     * @return Extracted target base data.
     */
    private Map<String, String> extractBaseTargetData(PhysicalObject target) {
        Map<String, String> values = new LinkedHashMap<>();
        if (target != null) {
            if (target instanceof ITarget it) {
                values.put("ID", it.getId());
                if (it.getTimestamp() != null) {
                    values.put("Timestamp", it.getTimestamp().getDateTime().toString());
                } else if(it.hasProperty("lastReceiveTimestamp")) {
                    values.put("Timestamp", new TimeImpl(Double.parseDouble(it.getProperty("lastReceiveTimestamp").getValue().toString()), TimeUnit.MILLISECOND).getDateTime().toString());
                } else {
                    values.put("Timestamp", null);
                }
            }
            if (target instanceof ITrackedTarget it) {
                values.put("ID", it.getId());
                if (it.getTrack() != null && it.getTrack().getLastUpdate() != null) {
                    values.put("Timestamp", it.getTrack().getLastUpdate().getDateTime().toString());
                } else if(it.hasProperty("lastReceiveTimestamp")) {
                    values.put("Timestamp", new TimeImpl(Double.parseDouble(it.getProperty("lastReceiveTimestamp").getValue().toString()), TimeUnit.MILLISECOND).getDateTime().toString());
                }else {
                    values.put("Timestamp", null);
                }
            }
            values.put("Name", target.getNameAsString());
            Angle cog = PhysicalObjectUtils.getCOG(target);
            if (cog != null) {
                values.put("COG", String.format("%.2f", cog.getAs(AngleUnit.DEGREE)) + " deg");
            } else {
                values.put("COG", null);
            }
            if (target.getPose() != null && target.getPose().getCoordinate() != null) {
                String readablePosition = CRSUtils.toDegreeMinuteSecond(target.getPose().getCoordinate().getLatitude()) + " / " +
                        CRSUtils.toDegreeMinuteSecond(target.getPose().getCoordinate().getLongitude());
                values.put("Position", readablePosition);
            } else {
                values.put("Position", null);
            }
            Speed speed = PhysicalObjectUtils.getSOG(target);
            if (speed != null) {
                values.put("SOG", String.format("%.2f", speed.getAs(SpeedUnit.KNOTS)) + " kn");
            } else {
                values.put("SOG", null);
            }
        } else {
            values.putAll(template);
        }
        return values;
    }

    /**
     * Extracts the dynamic data of a target, i.e. the data additional to the base data appended below.
     *
     * @param target Target to extract data from.
     * @return Extracted target dynamic data.
     */
    private Map<String, String> extractDynamicTargetData(PhysicalObject target) {
        Map<String, String> values = new LinkedHashMap<>();
        if (target != null) {
            if (target instanceof ITarget it) {
                if (it.getReferenceBearing() != null) {
                    values.put("Bearing", String.format("%.2f", it.getReferenceBearing().getAs(AngleUnit.DEGREE)) + " deg");
                } else {
                    values.put("Bearing", null);
                }
                if (it.getReferenceDistance() != null) {
                    values.put("Distance", String.format("%.2f", it.getReferenceDistance().getAs(DistanceUnit.METER)) + " m");
                } else {
                    values.put("Distance", null);
                }
            }
            if (propDisplayProperties.getValue() && target.getAllProperties() != null) {
                for (IProperty<?> property : target.getAllProperties()) {
                    values.put(property.getName(), property.getValue().toString());
                }
            }
        }
        return values;
    }

}
