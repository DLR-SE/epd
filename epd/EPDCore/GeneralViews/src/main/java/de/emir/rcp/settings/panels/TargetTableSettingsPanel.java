package de.emir.rcp.settings.panels;

import de.emir.rcp.ui.utils.properties.IPropertyWidget;
import de.emir.rcp.ui.utils.properties.PropertyCheckboxWidget;
import de.emir.rcp.ui.utils.properties.PropertySpinnerWidget;
import de.emir.rcp.views.targets.basic.TargetTableBasic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom panel for displaying the TargetTable settings page.
 */
public class TargetTableSettingsPanel extends JPanel {
    private final List<IPropertyWidget<?>> settings = new ArrayList<>();
    protected PropertySpinnerWidget<Double> tcpaAlertThreshold;
    protected PropertySpinnerWidget<Double> cpaAlertThreshold;
    protected PropertySpinnerWidget<Double> distAlertThreshold;
    protected PropertySpinnerWidget<Double> tcpaDisplayThreshold;
    protected PropertySpinnerWidget<Double> cpaDisplayThreshold;
    protected PropertySpinnerWidget<Double> distDisplayThreshold;
    protected PropertyCheckboxWidget enableAlerts;
    protected PropertyCheckboxWidget enableCalculations;

    /**
     * Creates a new TargetTableSettingsPanel and sets up the layout as well as the listeners.
     */
    public TargetTableSettingsPanel() {
        tcpaAlertThreshold = new PropertySpinnerWidget<>(TargetTableBasic.PROP_CTX, TargetTableBasic.TCPA_ALERT_THRESHOLD, 60.0);
        cpaAlertThreshold = new PropertySpinnerWidget<>(TargetTableBasic.PROP_CTX, TargetTableBasic.CPA_ALERT_THRESHOLD, 0.5);;
        distAlertThreshold = new PropertySpinnerWidget<>(TargetTableBasic.PROP_CTX, TargetTableBasic.DISTANCE_ALERT_THRESHOLD, 0.1);;
        tcpaDisplayThreshold = new PropertySpinnerWidget<>(TargetTableBasic.PROP_CTX, TargetTableBasic.TCPA_DISPLAY_THRESHOLD, 500.0);;
        cpaDisplayThreshold = new PropertySpinnerWidget<>(TargetTableBasic.PROP_CTX, TargetTableBasic.CPA_DISPLAY_THRESHOLD, 100.0);;
        distDisplayThreshold = new PropertySpinnerWidget<>(TargetTableBasic.PROP_CTX, TargetTableBasic.DISTANCE_DISPLAY_THRESHOLD, 100.0);;
        enableAlerts = new PropertyCheckboxWidget("", TargetTableBasic.PROP_CTX, TargetTableBasic.ENABLE_ALERTS, true);
        enableCalculations = new PropertyCheckboxWidget("", TargetTableBasic.PROP_CTX, TargetTableBasic.ENABLE_CALCULATIONS, true);

        setLayout(new GridBagLayout());
        initiateLayout();
    }

    /**
     * Initiates the settings page layout.
     */
    private void initiateLayout() {
        int row = 0;

        // Create settings
        addSetting(tcpaAlertThreshold, "TCPA Alert Threshold:", "Threshold in seconds below which the Time To Closest Point of Approach (TCPA) <br> is considered dangerous and needs to be highlighted.",
                tcpaAlertThreshold, row++);

        addSetting(cpaAlertThreshold, "CPA Alert Threshold:", "Threshold in nautical miles below which the Closest Point of Approach (CPA) <br> is considered dangerous and needs to be highlighted.",
                cpaAlertThreshold, row++);

        addSetting(distAlertThreshold, "Range Alert Threshold:", "Threshold in nautical miles below which the distance/range to another <br> target is considered dangerous and needs to be highlighted.",
                distAlertThreshold, row++);

        addSetting(tcpaDisplayThreshold, "TCPA Display Threshold:", "Threshold in seconds below which the Time To Closest Point of Approach (TCPA) <br> should be displayed in the table. Values higher than this are replaced with -.",
                tcpaDisplayThreshold, row++);

        addSetting(cpaDisplayThreshold, "CPA Display Threshold:", "Threshold in nautical miles below which the Closest Point of Approach (CPA) <br> should be displayed in the table. Values higher than this are replaced with -.",
                cpaDisplayThreshold, row++);

        addSetting(distDisplayThreshold, "Distance Display Threshold:", "Threshold in nautical miles below which the distance/range to another <br> target should be displayed in the table. Values higher than this are replaced with -.",
                distDisplayThreshold, row++);

        addSetting(enableAlerts, "Enable Alerts:", "Enables the Alert System for the Target Table. The configured Alert Thresholds <br> are used as a base to determine whether targets should be highlighted <br> in the table if below the thresholds.",
                enableAlerts, row++);

        addSetting(enableCalculations, "Enable Calculations:", "Enables the TCPA, Distance, Bearing and CPA calculations for the targets in <br> the Target Table. Each calculation is made from the Ownship reference.",
                enableCalculations, row++);

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
    private void addSetting(IPropertyWidget<?> property, String label, String desc, JComponent comp, int row) {
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
        settings.add(property);
    }

    /**
     * Checks if the settings were changed compared to the current configuration. If yes, the save settings dialog will appear.
     *
     * @return True if settings were changed compared to the current configuration.
     */
    public boolean isDirty() {
        for (IPropertyWidget<?> property : settings) {
            if (property.isDirty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Saves all settings to the config.
     */
    public void saveSettings() {
        for (IPropertyWidget propertyWidget : settings) {
            if (propertyWidget.isDirty()) {
                propertyWidget.finish();
            }
        }
    }

}
