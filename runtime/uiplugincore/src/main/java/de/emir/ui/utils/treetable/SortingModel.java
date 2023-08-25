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

import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

/**
 * Sortable tree table models should implement this interface
 */
public class SortingModel {
    /**
     * Compares Comparables
     */
    public static class DefaultComparator implements Comparator {
        public int compare(java.lang.Object o1, java.lang.Object o2) {
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null)
                return -1;
            if (o2 == null)
                return 1;
            return ((Comparable) o1).compareTo(o2);
        }
    }

    public static Comparator DEFAULT_COMPARATOR = new DefaultComparator();

    private EventListenerList listenerList = new EventListenerList();
    private ArrayList comparators = new ArrayList();
    private int sortedColumn = -1;
    private boolean descending = true;

    /**
     * Fires a change event that is used to notify listeners about the changes in SortingModel
     */
    protected void fireChange() {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        ChangeEvent e = null;
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ChangeListener.class) {
                // Lazily create the event:
                if (e == null)
                    e = new ChangeEvent(this);
                ((ChangeListener) listeners[i + 1]).stateChanged(e);
            }
        }
    }

    /**
     * Returns a comparator for the specified model column
     *
     * @param modelColumn
     *            column number
     * @return comparator or null if the column is not sortable
     */
    public Comparator getColumnComparator(int modelColumn) {
        if (modelColumn >= comparators.size())
            return null;
        return (Comparator) comparators.get(modelColumn);
    }

    /**
     * Sets a comparator for the specified model column
     *
     * @param modelColumn
     *            column number
     * @param c
     *            comparator or null if the column is not sortable
     */
    public void setColumnComparator(int modelColumn, Comparator c) {
        while (modelColumn >= comparators.size())
            comparators.add(null);
        comparators.set(modelColumn, c);
    }

    /**
     * Returns sorting order
     *
     * @return true = descending, false = ascending
     */
    public boolean isSortOrderDescending() {
        return descending;
    }

    /**
     * Sets sorting order
     *
     * @param d
     *            true = descending, false = ascending
     */
    public void setSortOrderDescending(boolean d) {
        this.descending = d;
        fireChange();
    }

    /**
     * Changes the sorted column
     *
     * @param index
     *            column number in the model or -1 if not sorted
     */
    public void setSortedColumn(int index) {
        this.sortedColumn = index;
        fireChange();
    }

    /**
     * Returns the sorted column
     *
     * @return column number in the model or -1 if not sorted
     */
    public int getSortedColumn() {
        return sortedColumn;
    }

    /**
     * Adds a listener
     *
     * @param l
     *            a listener
     */
    public void addChangeListener(ChangeListener l) {
        this.listenerList.add(ChangeListener.class, l);
    }

    /**
     * Removes a listener
     *
     * @param l
     *            a listener to be removed
     */
    public void removeChangeListener(ChangeListener l) {
        this.listenerList.remove(ChangeListener.class, l);
    }
}
