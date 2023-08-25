package de.emir.rcp.keybindings.ep;

public class GlobalKeyBinding extends AbstractKeyBinding {

    @Override
    public AbstractKeyBinding copy() {
        GlobalKeyBinding result = new GlobalKeyBinding();
        result.setCommandID(getCommandID());
        result.setKey(getKey());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof GlobalKeyBinding == false) {
            return false;
        }

        GlobalKeyBinding theOther = (GlobalKeyBinding) obj;
        String otherKey = theOther.getKey();

        if (otherKey.equals(getKey()) == false) {
            return false;
        }

        return getCommandID().equals(theOther.getCommandID());

    }

}
