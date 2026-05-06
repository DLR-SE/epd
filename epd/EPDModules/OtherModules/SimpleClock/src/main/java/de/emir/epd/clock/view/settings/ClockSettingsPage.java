package de.emir.epd.clock.view.settings;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.ZoneId;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.emir.epd.clock.ids.ClockBasics;
import de.emir.rcp.settings.AbstractSettingsPage;
import de.emir.rcp.ui.utils.properties.PropertyComboboxWidget;

/**
 * Contains the view for the clock settings page.
 */
public class ClockSettingsPage extends AbstractSettingsPage {
    PropertyComboboxWidget<String> tzPropertySelection = new PropertyComboboxWidget<>(ClockBasics.CLOCK_PROP_CONTEXT,
    		ClockBasics.CLOCK_PROP_TZ, "utc");
	
	/**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component fillPage() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(8, 8, 8, 8));
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 87, 363, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 300, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
        tzPropertySelection.setModel(new DefaultComboBoxModel(ZoneId.getAvailableZoneIds().toArray()));
        tzPropertySelection.reset();
        
        JLabel lblSelectTZ = new JLabel("Local Timezone:");
        GridBagConstraints gbc_lblSelectTZ = new GridBagConstraints();
        gbc_lblSelectTZ.anchor = GridBagConstraints.WEST;
        gbc_lblSelectTZ.insets = new Insets(0, 0, 5, 5);
        gbc_lblSelectTZ.gridx = 1;
        gbc_lblSelectTZ.gridy = 1;
        panel.add(lblSelectTZ, gbc_lblSelectTZ);
        GridBagConstraints gbc_tzPropertySelection = new GridBagConstraints();
        gbc_tzPropertySelection.insets = new Insets(0, 0, 5, 5);
        gbc_tzPropertySelection.fill = GridBagConstraints.HORIZONTAL;
        gbc_tzPropertySelection.gridx = 2;
        gbc_tzPropertySelection.gridy = 1;
        panel.add(tzPropertySelection, gbc_tzPropertySelection);
        return panel;
    }

    /**
     * Checks if the clock view settings were modified and need saving.
     * @return True if modified.
     */
    @Override
    public boolean isDirty() {
        return tzPropertySelection.isDirty();
    }

    /**
     * Saves the clock view settings to the PropertyStore.
     */
    @Override
    public void finish() {
    	tzPropertySelection.finish();
    }

}
