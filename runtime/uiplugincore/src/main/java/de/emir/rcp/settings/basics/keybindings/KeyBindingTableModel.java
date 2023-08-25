package de.emir.rcp.settings.basics.keybindings;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import de.emir.rcp.editors.ep.Editor;
import de.emir.rcp.keybindings.ep.AbstractKeyBinding;
import de.emir.rcp.keybindings.ep.EditorKeyBinding;
import de.emir.rcp.keybindings.ep.GlobalKeyBinding;
import de.emir.rcp.keybindings.ep.ViewKeyBinding;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.settings.basics.keybindings.KeyBindingSettingsPage.KeyBindingData;
import de.emir.rcp.views.ep.ViewDescriptor;

public class KeyBindingTableModel implements TableModel {

    private List<TableModelListener> listeners = new ArrayList<>();

    private KeyBindingData kbd;

    public KeyBindingTableModel(KeyBindingData kbd) {
        this.kbd = kbd;
    }

    @Override
    public int getRowCount() {
        return kbd.getBindings().size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch (columnIndex) {

        case 0:
            return "Context";
        case 1:
            return "Key(s)";
        }

        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {

        case 0:
            return String.class;
        case 1:
            return String.class;
        }

        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if (rowIndex < 0 || rowIndex >= kbd.getBindings().size()) {
            return null;
        }

        AbstractKeyBinding binding = kbd.getBindings().get(rowIndex);

        if (columnIndex == 0) {

            if (binding instanceof GlobalKeyBinding) {
                return "Global";
            }

            if (binding instanceof ViewKeyBinding) {

                ViewDescriptor view = (ViewDescriptor) PlatformUtil.getViewManager()
                        .getViewDescriptor(((ViewKeyBinding) binding).getViewID());

                return "View: " + view.getLabel();
            }

            if (binding instanceof EditorKeyBinding) {

                Editor editor = PlatformUtil.getEditorManager()
                        .getEditorData(((EditorKeyBinding) binding).getEditorID());

                return "Editor: " + editor.getLabel();
            }

            return null;
        }

        if (columnIndex == 1) {

            return binding.getKey();

        }

        return null;
    }

    public AbstractKeyBinding getBindingInRow(int rowIndex) {

        if (rowIndex < 0 || rowIndex >= kbd.getBindings().size()) {
            return null;
        }

        return kbd.getBindings().get(rowIndex);
    }

    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex) {

        if (rowIndex < 0 || rowIndex >= kbd.getBindings().size()) {
            return;
        }

        if (columnIndex == 1) {
            String newKey = (String) o;

            AbstractKeyBinding b = kbd.getBindings().get(rowIndex);
            kbd.setKey(b, newKey);

            for (TableModelListener l : listeners) {
                l.tableChanged(new TableModelEvent(this, rowIndex, rowIndex, columnIndex));
            }
        } else if (columnIndex == 0) {

            AbstractKeyBinding b = kbd.getBindings().get(rowIndex);
            kbd.replaceBinding(b, (AbstractKeyBinding) o);

            for (TableModelListener l : listeners) {
                l.tableChanged(new TableModelEvent(this, rowIndex, rowIndex, columnIndex));
            }

        }

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

        listeners.add(l);

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

        listeners.remove(l);

    }

}
