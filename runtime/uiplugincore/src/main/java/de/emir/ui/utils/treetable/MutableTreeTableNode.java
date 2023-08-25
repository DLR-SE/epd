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

import javax.swing.tree.MutableTreeNode;

/**
 * A Mutable node for a TreeTable
 */
public interface MutableTreeTableNode extends MutableTreeNode, TreeTableNode {
    /**
     * Sets the value for node node, at column number column.
     */
    public void setValueAt(Object aValue, int column);
}
