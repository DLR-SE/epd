package de.emir.rcp.menu.ep;

public class MenuEntryData implements Comparable<MenuEntryData> {

    public String path;
    public MenuEntry menuEntry;
    public int parts;

    public MenuEntryData(String path, MenuEntry menuEntry) {
        this.path = path;
        this.menuEntry = menuEntry;
        this.parts = path.split("\\.").length;
    }

    @Override
    public int compareTo(MenuEntryData o) {

        if (o.parts > this.parts) {
            return -1;
        }

        if (this.parts > o.parts) {
            return 1;
        }

        if (o.menuEntry.getAddedIndex() > this.menuEntry.getAddedIndex()) {
            return -1;
        }

        if (o.menuEntry.getAddedIndex() < this.menuEntry.getAddedIndex()) {
            return 1;
        }

        return 0;
    }

}
