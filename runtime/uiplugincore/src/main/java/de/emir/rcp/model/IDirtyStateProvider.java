package de.emir.rcp.model;

public interface IDirtyStateProvider {

    public boolean isDirty();

    public void setDirty(boolean isDirty);

    public boolean save();

}
