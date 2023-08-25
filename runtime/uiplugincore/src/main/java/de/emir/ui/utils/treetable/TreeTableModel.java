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

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/*
 * this file is derived from the "Creating TreeTable" article at
 * http://java.sun.com/products/jfc/tsc/articles/treetable2/index.html
 */

import javax.swing.tree.TreeModel;

/**
 * TreeTableModel is the model used by a JTreeTable. It extends TreeModel to add methods for getting inforamtion about
 * the set of columns each node in the TreeTableModel may have. Each column, like a column in a TableModel, has a name
 * and a type associated with it. Each node in the TreeTableModel can return a value for each of the columns and set
 * that value if isCellEditable() returns true.
 *
 * @author Philip Milne
 * @author Scott Violet
 */
public interface TreeTableModel extends TreeModel {
    /**
     * Returns the number ofs availible column.
     */
    public int getColumnCount();

    /**
     * Returns the name for column number column.
     */
    public String getColumnName(int column);

    /**
     * Returns the type for column number column.
     */
    public Class getColumnClass(int column);

    /**
     * Returns the value to be displayed for node node, at column number column.
     */
    public Object getValueAt(Object node, int column);

    /**
     * Indicates whether the the value for node node, at column number column is editable.
     */
    public boolean isCellEditable(Object node, int column);

    /**
     * Sets the value for node node, at column number column.
     *
     * @param aValue
     *            new value
     * @param node
     *            a node from this model
     * @param column
     *            column index
     */
    public void setValueAt(Object aValue, Object node, int column);

    public TableCellEditor getCellEditor(Object node, int column); // may return null

    public TableCellRenderer getCellRenderer(Object node, int column); // may return null
}
