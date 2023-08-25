
package de.emir.ui.utils.treetable.celledt;

import java.awt.Component;
import java.awt.Rectangle;
import java.util.EventObject;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import de.emir.ui.utils.tscb.TristateCheckBox;
import de.emir.ui.utils.tscb.TristateState;

public class TriStateTreeTableEditor extends TristateCheckBox implements TableCellRenderer, TableCellEditor {

    /**
     * 
     */
    private static final long serialVersionUID = 4877673854148458324L;
    protected static Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

    protected EventListenerList listenerList = new EventListenerList();

    public TriStateTreeTableEditor() {
        super("");
        setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(noFocusBorder);
        setBorderPainted(true);
        setBorderPaintedFlat(true);
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {

        // if (isSelected) {
        // setForeground(table.getSelectionForeground());
        // super.setBackground(table.getSelectionBackground());
        // } else {
        // setForeground(table.getForeground());
        // setBackground(table.getBackground());
        // }
        if (value instanceof TristateState) {
            this.setState((TristateState) value);
        }
        // setSelected((value != null && ((Boolean) value).booleanValue()));

        // if (hasFocus) {
        // setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
        // } else {
        // setBorder(null);
        // }

        return this;
    }

    public Object getCellEditorValue() {
        return TristateState.INDETERMINATE;
    }

    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    public boolean stopCellEditing() {
        fireEditingStopped();
        return true;
    }

    public void cancelCellEditing() {
        fireEditingCanceled();
    }

    public void addCellEditorListener(CellEditorListener l) {
        listenerList.add(CellEditorListener.class, l);
    }

    public void removeCellEditorListener(CellEditorListener l) {
        listenerList.remove(CellEditorListener.class, l);
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        JComponent editorComponent = (JComponent) getTableCellRendererComponent(table, value, isSelected, true, row,
                column);
        if (editorComponent instanceof JCheckBox) {
            // in order to avoid a "flashing" effect when clicking a checkbox
            // in a table, it is important for the editor to have as a border
            // the same border that the renderer has, and have as the background
            // the same color as the renderer has. This is primarily only
            // needed for JCheckBox since this editor doesn't fill all the
            // visual space of the table cell, unlike a text field.
            TableCellRenderer renderer = table.getCellRenderer(row, column);
            Component c = renderer.getTableCellRendererComponent(table, value, isSelected, true, row, column);
            if (c != null) {
                editorComponent.setOpaque(true);
                editorComponent.setBackground(c.getBackground());
                if (c instanceof JComponent) {
                    editorComponent.setBorder(((JComponent) c).getBorder());
                }
            } else {
                editorComponent.setOpaque(false);
            }
        }
        return editorComponent;
    }

    /**
     * Notifies all listeners that have registered interest for notification on this event type. The event instance is
     * created lazily.
     *
     * @see EventListenerList
     */
    protected void fireEditingStopped() {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == CellEditorListener.class) {
                // Lazily create the event:
                if (changeEvent == null)
                    changeEvent = new ChangeEvent(this);
                ((CellEditorListener) listeners[i + 1]).editingStopped(changeEvent);
            }
        }
    }

    // workaround for a Swing bug (?)
    @Override
    protected void paintComponent(java.awt.Graphics g) {
        Rectangle oldClip = g.getClipBounds();
        g.setClip(oldClip.x, oldClip.y, oldClip.width - 1, oldClip.height - 1);
        super.paintComponent(g);
        g.setClip(oldClip);
    }

    /**
     * Notifies all listeners that have registered interest for notification on this event type. The event instance is
     * created lazily.
     *
     * @see EventListenerList
     */
    protected void fireEditingCanceled() {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == CellEditorListener.class) {
                // Lazily create the event:
                if (changeEvent == null)
                    changeEvent = new ChangeEvent(this);
                ((CellEditorListener) listeners[i + 1]).editingCanceled(changeEvent);
            }
        }
    }
}
