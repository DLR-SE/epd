package de.emir.rcp.keybindings.ep;

public class ViewKeyBinding extends AbstractKeyBinding {

    private String viewID;

    public String getViewID() {
        return viewID;
    }

    public void setViewID(String viewID) {
        this.viewID = viewID;
    }

    @Override
    public AbstractKeyBinding copy() {
        ViewKeyBinding result = new ViewKeyBinding();
        result.setCommandID(getCommandID());
        result.setViewID(getViewID());
        result.setKey(getKey());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof ViewKeyBinding == false) {
            return false;
        }

        ViewKeyBinding theOther = (ViewKeyBinding) obj;
        String otherKey = theOther.getKey();
        String otherViewId = theOther.getViewID();

        if (otherKey.equals(getKey()) == false) {
            return false;
        }

        if (getViewID().equals(otherViewId) == false) {
            return false;
        }

        return getCommandID().equals(theOther.getCommandID());

    }

}
