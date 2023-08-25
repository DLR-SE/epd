package de.emir.rcp.menu;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import de.emir.rcp.menu.ep.DynamicMenu;
import de.emir.rcp.menu.ep.util.DynamicMenuListener;

public class DynamicJMenu extends JMenu {

    private static final long serialVersionUID = -4832031971668834123L;

    private Object parent;
    private AbstractDynamicMenuProvider provider;

    private List<JMenuItem> currentItems = new ArrayList<>();

    public DynamicJMenu(DynamicMenu menu, Object parent) {

        this.parent = parent;

        provider = menu.getItemProvider();

        // This JMenu acts like a placeholder for the real content. We can determine the
        // position of all dynamic contents relative to this element
        setVisible(false);

    }

    /**
     * This JMenu is added to its parent after instantiation. The repopulate method accesses the parent element, so we
     * have to run it after access to the parent became possible
     */
    public void init() {

        provider.subscribeIsDirty(c -> repopulate());
        repopulate();

        if (parent instanceof JMenu) {
            ((JMenu) parent).addMenuListener(new DynamicMenuListener(provider));
        } else if (parent instanceof JPopupMenu) {
            ((JPopupMenu) parent).addPopupMenuListener(new DynamicMenuListener(provider));
        }

    }

    private void repopulate() {

        for (JMenuItem i : currentItems) {
            removeItemFromParent(i);
        }

        currentItems.clear();

        List<JMenuItem> items = provider.populate();

        if (items == null || items.isEmpty()) {
            return;
        }

        for (JMenuItem i : items) {
            addItemToParent(i);
        }

        currentItems.addAll(items);

    }

    private void removeItemFromParent(JMenuItem i) {

        if (parent instanceof JMenuBar) {
            ((JMenuBar) parent).remove(i);
        } else if (parent instanceof JMenu) {
            ((JMenu) parent).remove(i);
        } else if (parent instanceof JPopupMenu) {
            ((JPopupMenu) parent).remove(i);
        }

    }

    private void addItemToParent(JMenuItem i) {

        if (parent instanceof JMenuBar) {

            int addBeforeIndex = ((JMenuBar) parent).getComponentIndex(this);
            ((JMenuBar) parent).add(i, addBeforeIndex);

        } else if (parent instanceof JMenu) {

            int addBeforeIndex = getComponentIndex(((JMenu) parent), this);
            ((JMenu) parent).add(i, addBeforeIndex);

        } else if (parent instanceof JPopupMenu) {
            int addBeforeIndex = ((JPopupMenu) parent).getComponentIndex(this);
            ((JPopupMenu) parent).add(i, addBeforeIndex);
        }

    }

    private int getComponentIndex(JMenu m, Component c) {
        int ncomponents = m.getComponentCount();
        Component[] component = m.getComponents();
        for (int i = 0; i < ncomponents; i++) {
            Component comp = component[i];
            if (comp == c)
                return i;
        }
        return -1;
    }

}
