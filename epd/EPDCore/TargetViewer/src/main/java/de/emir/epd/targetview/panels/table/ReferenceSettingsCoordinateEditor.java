package de.emir.epd.targetview.panels.table;

import de.emir.tuml.ucore.runtime.logging.ULog;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Coordinate editor component which validates the latitude and longitude inputs in the reference editor table.
 */
public class ReferenceSettingsCoordinateEditor extends DefaultCellEditor {
    private final CoordinateType type;
    private final Border red = new LineBorder(Color.RED);
    private boolean invalidValue;

    /**
     * Creates a new coordinate editor component. Based on the type, an input validation is made on the entered
     * latitude and longitude values. If the values are not in the decimal format or outside of the range (i.e. 180 for longitude
     * and 90 for latitude), the values don't pass validation.
     *
     * @param type Type of coordinate to validate. LATITUDE or LONGITUDE.
     */
    public ReferenceSettingsCoordinateEditor(CoordinateType type) {
        super(new JTextField());
        this.type = type;
    }

    /**
     * Overrides the get table cell editor method. This is to ensure that the border style is reverted to the default
     * table color.
     *
     * @param table      the <code>JTable</code> that is asking the
     *                   editor to edit; can be <code>null</code>
     * @param value      the value of the cell to be edited; it is
     *                   up to the specific editor to interpret
     *                   and draw the value.  For example, if value is
     *                   the string "true", it could be rendered as a
     *                   string or it could be rendered as a check
     *                   box that is checked.  <code>null</code>
     *                   is a valid value
     * @param isSelected true if the cell is to be rendered with
     *                   highlighting
     * @param row        the row of the cell being edited
     * @param column     the column of the cell being edited
     * @return Editor component.
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        editorComponent.setBorder(table.getBorder());
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>. If the latitude or longitude values are not in
     * the right format or exceed the limits, the cell is prohibited from being finished with editing.
     *
     * @see EditorDelegate#stopCellEditing
     */
    @Override
    public boolean stopCellEditing() {
        if (!isValid(((JTextField) editorComponent).getText())) {
            ((JTextField) editorComponent).setBorder(red);
            invalidValue = false;
            return false;
        } else {
            invalidValue = true;
        }
        return super.stopCellEditing();
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>. This performs the conversion of input values to decimal coordinates.
     * If the coordinate is malformed, null is returned.
     *
     * @see EditorDelegate#getCellEditorValue
     */
    @Override
    public Object getCellEditorValue() {
        if (isValid(((JTextField) editorComponent).getText())) {
            invalidValue = false;
            return Double.parseDouble(((JTextField) editorComponent).getText());
        } else {
            invalidValue = true;
        }
        return null;
    }

    /**
     * Checks if the given coordinate is invalid or valid.
     *
     * @return True if invalid, else valid.
     */
    public boolean isInvalid() {
        return invalidValue;
    }

    /**
     * Checks if a given text value is in the decimal format and does not exceed latitude and longitude ranges.
     *
     * @param text Text to check.
     * @return True if in decimal format and inside the latitude/longitude ranges.
     */
    private boolean isValid(String text) {
        try {
            double val = Double.parseDouble(text.trim());
            if (type.equals(CoordinateType.LATITUDE) && (val < -90 || val > 90)) {
                ULog.error("Error during coordinate validation: Latitude must be between -90 and 90.");
                return false;
            } else if (type.equals(CoordinateType.LONGITUDE) && (val < -180 || val > 180)) {
                ULog.error("Error during coordinate validation: Longitude must be between -180 and 180.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            ULog.error("Error during coordinate validation: " + type.name() + " must be a number");
            return false;
        }
    }

    /**
     * Coordinate type helper to specify if the editor should validate against longitude or latitude format.
     */
    public enum CoordinateType {
        LATITUDE,
        LONGITUDE
    }
}