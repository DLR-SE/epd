package de.emir.ui.utils.treetable.umodel;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public interface IColumnProvider {

    String getColumnName();

    Class<?> getColumnClass();

    Object getColumnValue(final Object node);

    boolean isEditable(Object node);

    /**
     * Returns a TableCellEditor for the given node, or null. If null is returned, the default cell editor will be used
     * (see JTable)
     * 
     * @param node
     * @return
     */
    TableCellEditor getCellEditor(Object node);

    TableCellRenderer getCellRenderer(Object node);

    void setValue(Object node, Object aValue);
}
