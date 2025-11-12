package de.emir.epd.targetview.panels.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Rendering component for displaying mode values in the table model.
 */
public class ReferenceSettingsModeRenderer extends DefaultTableCellRenderer {

    /**
     * Sets the <code>String</code> object for the cell being rendered to
     * <code>value</code>.
     *
     * @param value the string value for this cell; if value is
     *              <code>null</code> it sets the text value to an empty string
     * @see JLabel#setText
     */
    @Override
    protected void setValue(Object value) {
        if (value instanceof ReferenceSettingsTableModel.Mode mode) {
            setText(mode.name());
        } else {
            super.setValue(value);
        }
    }
}
