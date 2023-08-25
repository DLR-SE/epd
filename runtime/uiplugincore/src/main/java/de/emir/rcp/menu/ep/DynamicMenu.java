package de.emir.rcp.menu.ep;

import de.emir.rcp.menu.AbstractDynamicMenuProvider;

public class DynamicMenu extends MenuEntry implements IDynamicMenu {

    private AbstractDynamicMenuProvider itemProvider;

    public DynamicMenu(String id, AbstractDynamicMenuProvider p) {
        itemProvider = p;
    }

    public AbstractDynamicMenuProvider getItemProvider() {
        return itemProvider;
    }

}
