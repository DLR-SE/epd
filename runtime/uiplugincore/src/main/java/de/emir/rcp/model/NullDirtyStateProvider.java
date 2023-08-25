package de.emir.rcp.model;

public class NullDirtyStateProvider implements IDirtyStateProvider {

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public void setDirty(boolean isDirty) {
    }

    @Override
    public boolean save() {
        return true;
    }

}
