package de.emir.epd.targetview.panels.table;

import javax.swing.*;
import java.awt.*;

/**
 * Custom mode editor class. This enables the usage of a combobox for selecting
 * the reference mode FIXED, REFERENCE or OWNSHIP. FIXED represents a fixed coordinate as the reference
 * target for targets which contain a bearing and distance. REFERENCE refers to the usage of reference targets
 * supplied with the data source for target referencing. OWNSHIP refers to the usage of the models internal ownship
 * object as the data source for target referencing.
 */
public class ReferenceSettingsModeEditor extends DefaultCellEditor {
    private final JComboBox<ReferenceSettingsTableModel.Mode> comboBox;

    /**
     * Creates and sets up the reference mode editor.
     */
    public ReferenceSettingsModeEditor() {
        super(new JComboBox<>());
        comboBox = new JComboBox<>(ReferenceSettingsTableModel.Mode.values());
    }

    /**
     * Implements the <code>TableCellEditor</code> interface. This selects the current combobox value based
     * on the supplied mode and returns the combobox as the editor for this column.
     *
     * @param table      Table to get cell editor for.
     * @param value      Value of the table column.
     * @param isSelected True if the column is selected.
     * @param row        Row which is selected.
     * @param column     Column which is selected.
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof ReferenceSettingsTableModel.Mode) {
            comboBox.setSelectedItem(value);
        }
        return comboBox;
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>. Extracts the currently selected value from
     * the internal combobox model.
     *
     * @see EditorDelegate#getCellEditorValue
     */
    @Override
    public Object getCellEditorValue() {
        return comboBox.getSelectedItem();
    }
}
