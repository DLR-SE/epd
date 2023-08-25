package de.emir.epd.alert.view.settings;

import de.emir.epd.alert.manager.Alert;
import de.emir.epd.alert.manager.AlertManager;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlertTableModel extends AbstractTableModel {

    private Map<Alert, Boolean> alertBooleanMap;
    private boolean isDirty = false;

    public AlertTableModel(List<Alert> alerts) {
        alertBooleanMap = new HashMap<>();
        for (Alert alert : alerts) {
            boolean visible = AlertManager.isVisible(alert.getId());
            alertBooleanMap.put(alert, visible);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1;
    }

    private Alert getAlert(int index) {
        return alertBooleanMap.keySet().toArray(new Alert[0])[index];
    }

    @Override
    public int getRowCount() {
        return alertBooleanMap.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Alert";
            case 1:
                return "Visible";
        }

        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Boolean.class;
        }

        return Object.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Alert alert = getAlert(rowIndex);
        switch (columnIndex) {
            case 0:
                return alert.getText();
            case 1:
                return alertBooleanMap.get(alert);
        }

        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                break;
            case 1:
                Alert alert = getAlert(rowIndex);
                alertBooleanMap.put(alert, (Boolean) aValue);
                isDirty = true;
                break;
        }
    }

    public Map<Alert, Boolean> getAlertBooleanMap() {
        return alertBooleanMap;
    }

    public boolean isDirty() {
        return isDirty;
    }
}
