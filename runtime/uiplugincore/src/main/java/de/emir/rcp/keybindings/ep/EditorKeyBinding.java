package de.emir.rcp.keybindings.ep;

public class EditorKeyBinding extends AbstractKeyBinding {

    private String editorID;

    public String getEditorID() {
        return editorID;
    }

    public void setEditorID(String editorID) {
        this.editorID = editorID;
    }

    @Override
    public AbstractKeyBinding copy() {
        EditorKeyBinding result = new EditorKeyBinding();
        result.setCommandID(getCommandID());
        result.setEditorID(getEditorID());
        result.setKey(getKey());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof EditorKeyBinding == false) {
            return false;
        }

        EditorKeyBinding theOther = (EditorKeyBinding) obj;
        String otherKey = theOther.getKey();
        String otherEditorId = theOther.getEditorID();

        if (otherKey.equals(getKey()) == false) {
            return false;
        }

        if (getEditorID().equals(otherEditorId) == false) {
            return false;
        }

        return getCommandID().equals(theOther.getCommandID());

    }
}
