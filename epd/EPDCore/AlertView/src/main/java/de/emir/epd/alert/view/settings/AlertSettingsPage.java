package de.emir.epd.alert.view.settings;

import de.emir.epd.alert.manager.Alert;
import de.emir.epd.alert.manager.AlertManager;
import de.emir.rcp.settings.AbstractSettingsPage;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class AlertSettingsPage extends AbstractSettingsPage {
    private AlertTableModel model;

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component fillPage() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0));

        JTable table = new JTable();
        model = new AlertTableModel(AlertManager.getAlertList());
        table.setModel(model);
        panel.add(table, BorderLayout.CENTER);
        return panel;
    }

    @Override
    public boolean isDirty() {
        return model.isDirty();
    }

    @Override
    public void finish() {
        Map<Alert, Boolean> booleanMap = model.getAlertBooleanMap();
        for (Alert alert : booleanMap.keySet()) {
            AlertManager.setVisible(alert.getId(), booleanMap.get(alert));
        }
    }

}
