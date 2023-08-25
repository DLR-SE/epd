package de.emir.rcp.settings.basics.keybindings;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

/*
* TODO implement this key binding editor
 */
public class KeyBindingTableCellEditor implements TableCellEditor {

    private JTextField field;

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        //System.out.println("isCellEditable " + e.getSource());
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject e) {
        //System.out.println("shouldSelectCell " + e.getSource());
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        //System.out.println("stopCellEditing");
        return true;
    }

    @Override
    public void cancelCellEditing() {
        //System.out.println("cancelCellEditing");

    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {

    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {

    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        field = new JTextField(value + "");

        return field;
    }

}
