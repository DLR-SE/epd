package de.emir.epd.targetview.panels;

import de.emir.epd.targetview.ids.TargetBasics;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.IProperty;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Custom panel for displaying the TargetViewer settings page.
 */
public class TargetSettingsPanel extends JPanel {
    private final List<SettingsEntry<?>> settings = new ArrayList<>();
    protected IProperty<Integer> propTargetLostTime;
    protected IProperty<Integer> propLookahead;
    protected IProperty<Integer> propTrackTimeout;
    protected IProperty<Boolean> propDisplayTargetLoss;
    protected IProperty<Boolean> propHandleTrackTimeout;
    protected IProperty<Boolean> propUseReceiveTimestamp;
    protected IProperty<Boolean> propShowNames;
    protected IProperty<Boolean> propShowTargets;
    protected IProperty<Boolean> propShowTrackedTargets;
    protected IProperty<Boolean> propDisplayProperties;
    protected IProperty<Boolean> propLayerFixedUpdate;
    protected IProperty<Integer> propLayerUpdateRate;

    /**
     * Creates a new TargetSettingsPanel and sets up the layout as well as the listeners.
     */
    public TargetSettingsPanel() {
        PropertyContext ctx = PropertyStore.getContext(TargetBasics.TARGET_VIEWER_PROP_CONTEXT);
        propTargetLostTime = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_TARGET_LOSTTIME, 30);
        propLookahead = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_LOOKAHEAD, 6);
        propTrackTimeout = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_TRACK_TIMEOUT, 10);
        propDisplayTargetLoss = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_DISPLAY_TARGET_LOSS, false);
        propHandleTrackTimeout = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_HANDLE_TRACK_TIMEOUT, false);
        propUseReceiveTimestamp = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_USE_RECEIVE_TIMESTAMP, true);
        propShowNames = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_SHOW_NAMES, true);
        propShowTargets = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_SHOW_TARGETS, true);
        propShowTrackedTargets = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_SHOW_TRACKED_TARGETS, true);
        propDisplayProperties = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_DISPLAY_PROPERTIES, true);
        propLayerFixedUpdate = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_LAYER_FIXED_UPDATE, true);
        propLayerUpdateRate = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_LAYER_UPDATE_RATE, 10);

        setLayout(new GridBagLayout());
        initiateLayout();
    }

    /**
     * Initiates the settings page layout.
     */
    private void initiateLayout() {
        int row = 0;

        // Create settings
        addSetting(propTargetLostTime, "Target Lost Time:", "Time in seconds after which a target is considered gone and will be marked.",
                new JSpinner(new SpinnerNumberModel(propTargetLostTime.getValue().intValue(), 0, 100, 1)), row++);

        addSetting(propLookahead, "Lookahead:", "Number of steps to predict target course ahead.",
                new JSpinner(new SpinnerNumberModel(propLookahead.getValue().intValue(), 0, 100, 1)), row++);

        addSetting(propTrackTimeout, "Track Timeout:", "Time in seconds before a track point is considered timed out and will not be drawn.",
                new JSpinner(new SpinnerNumberModel(propTrackTimeout.getValue().intValue(), 0, 100, 1)), row++);

        addSetting(propDisplayTargetLoss, "Display Target Loss", "Show target loss symbol when the last update time exceeds the lost time. <br> Targets using the LOST status will still be displayed as lost. ",
                new JCheckBox("", propDisplayTargetLoss.getValue()), row++);

        addSetting(propHandleTrackTimeout, "Handle Track Timeout", "Enable the removal of target points in the layer after the track timeout interval.",
                new JCheckBox("", propHandleTrackTimeout.getValue()), row++);

        addSetting(propUseReceiveTimestamp, "Use Receive Timestamp", "Use the EPD internal receive timestamp instead of the supplied target timestamp <br> as a base for timeouts. This is useful if historical data is injected",
                new JCheckBox("", propUseReceiveTimestamp.getValue()), row++);

        addSetting(propShowNames, "Show Names", "Display target names next to the targets.",
                new JCheckBox("", propShowNames.getValue()), row++);

        addSetting(propShowTargets, "Show Targets", "Show all targets with the type ITarget.",
                new JCheckBox("", propShowTargets.getValue()), row++);

        addSetting(propShowTrackedTargets, "Show Tracked Targets", "Show all targets with the type ITrackedTarget.",
                new JCheckBox("", propShowTrackedTargets.getValue()), row++);

        addSetting(propDisplayProperties, "Show Target Properties", "Show the Target properties in the Target viewer.",
                new JCheckBox("", propDisplayProperties.getValue()), row++);

        addSetting(propLayerFixedUpdate, "Update Layer at Fixed Rate", "Updates the Target layer at a fixed rate.",
                new JCheckBox("", propLayerFixedUpdate.getValue()), row++);

        addSetting(propLayerUpdateRate, "Target Layer Update Rate", "The rate with which the Target layer should be updated in seconds.",
                new JSpinner(new SpinnerNumberModel(propLayerUpdateRate.getValue().intValue(), 0, 3600, 1)), row++);

    }

    /**
     * Utility class for storing properties, components and values for each settings entry.
     *
     * @param <T> Type of property.
     */
    private static class SettingsEntry<T> {
        IProperty<T> property;
        JComponent component;
        Object originalValue;

        /**
         * Creates a new SettingsEntry.
         *
         * @param property  Property to store.
         * @param component Component to associate to property.
         */
        SettingsEntry(IProperty<T> property, JComponent component) {
            this.property = property;
            this.originalValue = property.getValue();
            this.component = component;
        }

        /**
         * Gets the current value of the SettingsEntry from the underlying JComponent.
         *
         * @return Current Value of the JComponent associated to the Property.
         */
        Object getCurrentValue() {
            if (component instanceof JSpinner spinner) {
                return spinner.getValue();
            } else if (component instanceof JCheckBox checkBox) {
                return checkBox.isSelected();
            }
            return null;
        }

        /**
         * Checks if a value was changed, i.e. the set value of the component does not match the current property value.
         *
         * @return True if changed, else false.
         */
        boolean isChanged() {
            return !Objects.equals(getCurrentValue(), originalValue);
        }

        /**
         * Marks the current value as the original value, i.e. resets the isChanged to the current value of the JComponent.
         */
        void markOriginal() {
            originalValue = getCurrentValue();
        }
    }

    /**
     * Adds a setting to the settings panel.
     *
     * @param property Property associated to the setting.
     * @param label    Label of the setting.
     * @param desc     Description of the setting.
     * @param comp     Component associated to modifying the setting, i.e. Spinners, Checkboxes etc.
     * @param row      Row where to insert the setting.
     */
    private void addSetting(IProperty<?> property, String label, String desc, JComponent comp, int row) {
        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        labelPanel.add(new JLabel(label));
        JLabel descLabel = new JLabel("<html><i>" + desc + "</i></html>");
        descLabel.setFont(descLabel.getFont().deriveFont(Font.PLAIN, 12f));
        labelPanel.add(descLabel);

        GridBagConstraints gbc = new GridBagConstraints();

        // Left side: label and description.
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 10, 4, 10);
        add(labelPanel, gbc);

        // Right side: input component.
        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        add(comp, gbc);
        settings.add(new SettingsEntry(property, comp));
    }

    /**
     * Checks if the settings were changed compared to the current configuration. If yes, the save settings dialog will appear.
     *
     * @return True if settings were changed compared to the current configuration.
     */
    public boolean isDirty() {
        for (SettingsEntry<?> settingsEntry : settings) {
            if (settingsEntry.isChanged()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Saves all settings to the config.
     */
    public void saveSettings() {
        for (SettingsEntry settingsEntry : settings) {
            if (settingsEntry.isChanged()) {
                settingsEntry.property.setValue(settingsEntry.getCurrentValue());
                settingsEntry.markOriginal();
            }
        }
    }

}
