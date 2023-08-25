package de.emir.rcp.keybindings.ep;

public abstract class AbstractKeyBinding {

    private String commandID;
    private String key;

    public String getCommandID() {
        return commandID;
    }

    public void setCommandID(String commandID) {
        this.commandID = commandID;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public abstract AbstractKeyBinding copy();
}
