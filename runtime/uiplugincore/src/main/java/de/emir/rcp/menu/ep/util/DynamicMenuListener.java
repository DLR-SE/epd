package de.emir.rcp.menu.ep.util;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import de.emir.rcp.menu.AbstractDynamicMenuProvider;

public class DynamicMenuListener implements MenuListener, PopupMenuListener {

    private AbstractDynamicMenuProvider provider;

    public DynamicMenuListener(AbstractDynamicMenuProvider p) {
        this.provider = p;
    }

    @Override
    public void menuSelected(MenuEvent e) {
        provider.setDirty(true);

    }

    @Override
    public void menuDeselected(MenuEvent e) {
    }

    @Override
    public void menuCanceled(MenuEvent e) {
    }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        provider.setDirty(true);

    }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
    }

    @Override
    public void popupMenuCanceled(PopupMenuEvent e) {
    }

}
