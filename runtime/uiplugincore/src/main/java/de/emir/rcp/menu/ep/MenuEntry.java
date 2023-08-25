package de.emir.rcp.menu.ep;

import de.emir.runtime.plugin.AbstractUIPlugin;

public abstract class MenuEntry {

    private double addedIndex = 0;

    protected String after;
    protected String before;

    private AbstractUIPlugin plugin;

    public double getAddedIndex() {
        return addedIndex;
    }

    public void setAddedIndex(double addedIndex) {
        this.addedIndex = addedIndex;
    }

    public String getAfter() {
        return after;
    }

    public String getBefore() {
        return before;
    }

    public void setPlugin(AbstractUIPlugin plugin) {
        this.plugin = plugin;
    }

    public AbstractUIPlugin getPlugin() {
        return plugin;
    }

}
