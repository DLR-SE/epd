package de.emir.rcp.settings.basics.keybindings;

import de.emir.model.universal.plugincore.var.AbstractKeyBinding;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.SwingUtilities;

import de.emir.rcp.manager.util.PlatformUtil;

public class KeyBindingTableMouseListener extends MouseAdapter {

    public void mousePressed(MouseEvent mouseEvent) {

        if (mouseEvent.getClickCount() != 2) {
            return;
        }

        JTable table = (JTable) mouseEvent.getSource();
        Point point = mouseEvent.getPoint();

        int col = table.columnAtPoint(point);

        if (col == 0) {

            int row = table.rowAtPoint(point);

            KeyBindingTableModel tm = (KeyBindingTableModel) table.getModel();

            AbstractKeyBinding bindingToEdit = tm.getBindingInRow(row);

            if (bindingToEdit == null) {
                return;
            }

            Window parentWindow = SwingUtilities.windowForComponent(table);
            AbstractKeyBinding kb = KeyBindingsUtil.showContextDialog(parentWindow);

            if (kb == null) {
                return;
            }

            kb.setCommandID(bindingToEdit.getCommandID());
            kb.setKey(bindingToEdit.getKey());

            table.getModel().setValueAt(kb, row, col);

        } else if (col == 1) {
            int row = table.rowAtPoint(point);

            KeyBindingTableModel tm = (KeyBindingTableModel) table.getModel();

            AbstractKeyBinding bindingToEdit = tm.getBindingInRow(row);

            if (bindingToEdit == null) {
                return;
            }

            EnterKeyBindingDialog dialog = new EnterKeyBindingDialog(PlatformUtil.getWindowManager().getMainWindow(),
                    bindingToEdit);

            String newKeys = dialog.getSelectedKeys();

            if (newKeys == null) {
                return;
            }
            table.getModel().setValueAt(newKeys, row, col);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        table.setCursor(Cursor.getDefaultCursor());
    }
}
