package de.emir.ui.utils.treetable;
/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2003 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

import java.util.Comparator;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * Default model for TreeTable
 */
public class DefaultTreeTableModel extends DefaultTreeModel implements TreeTableModel {
    private static final long serialVersionUID = 1;

    private String[] columnNames;

    /**
     * Creates a tree in which any node can have children.
     *
     * @param root
     *            a TreeNode object that is the root of the tree
     * @param columnNames
     *            names for columns
     * @see #DefaultTreeModel(TreeNode, boolean)
     */
    public DefaultTreeTableModel(TreeTableNode root, String[] columnNames) {
        super(root);
        this.columnNames = columnNames;
    }

    /**
     * Creates a tree specifying whether any node can have children, or whether only certain nodes can have children.
     *
     * @param root
     *            a TreeNode object that is the root of the tree
     * @param askAllowsChildren
     *            a boolean, false if any node can have children, true if each node is asked to see if it can have
     *            children
     * @param columnNames
     *            names for columns
     * @see #asksAllowsChildren
     */
    public DefaultTreeTableModel(TreeTableNode root, boolean asksAllowsChildren, int columnNumber) {
        super(root, asksAllowsChildren);
        // TODO: columnNumber
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Class getColumnClass(int column) {
        if (column == 0)
            return TreeTableModel.class;
        else
            return Object.class;
    }

    public Object getValueAt(Object node, int column) {
        return ((TreeTableNode) node).getValueAt(column);
    }

    public boolean isCellEditable(Object node, int column) {
        return column == 0;
    }

    public void setValueAt(Object aValue, Object node, int column) {
        ((TreeTableNode) node).setValueAt(aValue, column);
    }

    public static class ToStringComparator implements Comparator {
        public int compare(Object obj1, Object obj2) {
            String s1 = (obj1 == null) ? "" : obj1.toString();
            String s2 = (obj2 == null) ? "" : obj2.toString();
            if (s1 == null)
                s1 = "";
            if (s2 == null)
                s2 = "";
            return s1.compareTo(s2);
        }
    }

    /*
     * Notify all listeners that have registered interest for notification on this event type. The event instance is
     * lazily created using the parameters passed into the fire method.
     * 
     * @see EventListenerList
     */
    public void fireTreeStructureChanged(Object source, Object[] path) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        TreeModelEvent e = null;
        TreePath tp = path == null ? null : new TreePath(path);
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == TreeModelListener.class) {
                // Lazily create the event:
                if (e == null)
                    e = new TreeModelEvent(source, tp);
                ((TreeModelListener) listeners[i + 1]).treeStructureChanged(e);
            }
        }
    }

    @Override
    public void fireTreeNodesRemoved(Object source, Object[] path, int[] childIndices, Object[] children) {
        super.fireTreeNodesRemoved(source, path, childIndices, children);
    }

    @Override
    public void fireTreeNodesInserted(Object source, Object[] path, int[] childIndices, Object[] children) {
        super.fireTreeNodesInserted(source, path, childIndices, children);
    }

    @Override
    public void fireTreeNodesChanged(Object source, Object[] path, int[] childIndices, Object[] children) {
        super.fireTreeNodesChanged(source, path, childIndices, children);
    }

    @Override
    public void fireTreeStructureChanged(Object source, Object[] path, int[] childIndices, Object[] children) {
        super.fireTreeStructureChanged(source, path, childIndices, children);
    }

    protected void setColumnNames(String[] names) {
        this.columnNames = names;
    }

    @Override
    public TableCellEditor getCellEditor(Object node, int column) {
        return null; // if null is returned the table uses the default editor
    }

    @Override
    public TableCellRenderer getCellRenderer(Object node, int column) {
        return null;// if null is returned the table uses the default editor
    }
}
