package de.emir.rcp.menu.ep.util;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class PopupMenuSeparatorVisibilityHandler implements PopupMenuListener {

    private JPopupMenu popup;

    public PopupMenuSeparatorVisibilityHandler(JPopupMenu popup) {
        this.popup = popup;
    }

    private void checkStates(JComponent parent) {

        if (parent == null) {
            return;
        }

        Component[] components = parent.getComponents();

        ArrayList<Component> visibleItems = new ArrayList<Component>();

        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JSeparator || components[i].isVisible()) {
                visibleItems.add(components[i]);
            }
        }

        int i = 0;
        for (Component component : visibleItems) {
            if (component instanceof JSeparator) {

                // Remove heading or trailing separator
                component.setVisible(i > 0 && i < visibleItems.size() - 1);

                // Remove separators followed by other
                if (i > 0) {
                    Component lastItem = visibleItems.get(i - 1);
                    if (lastItem instanceof JSeparator) {

                        lastItem.setVisible(false);
                    }
                }
            }

            if (component instanceof JMenu) {
                checkStates(((JMenu) component).getPopupMenu());
            }

            i++;
        }

    }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {

        checkStates(popup);
    }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
    }

    @Override
    public void popupMenuCanceled(PopupMenuEvent e) {
    }

}
