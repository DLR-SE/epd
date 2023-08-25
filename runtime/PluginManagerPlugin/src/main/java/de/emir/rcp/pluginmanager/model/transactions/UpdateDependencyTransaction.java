package de.emir.rcp.pluginmanager.model.transactions;

import de.emir.rcp.model.AbstractModelTransaction;
import de.emir.tuml.runtime.epf.ObservableDependency;

public class UpdateDependencyTransaction extends AbstractModelTransaction {

    private boolean executed = false;
    private ObservableDependency toUpdate;
    private ObservableDependency newValues;
    private ObservableDependency oldValues;

    public UpdateDependencyTransaction(ObservableDependency toUpdate, ObservableDependency newValues) {
        this.toUpdate = toUpdate;
        this.newValues = newValues;
    }

    @Override
    public void run() {

        oldValues = toUpdate.copy();

        toUpdate.setGroupId(newValues.getGroupId());
        toUpdate.setArtifactId(newValues.getArtifactId());
        toUpdate.setVersion(newValues.getVersion());

        executed = true;

    }

    @Override
    public void undo() {

        toUpdate.setGroupId(oldValues.getGroupId());
        toUpdate.setArtifactId(oldValues.getArtifactId());
        toUpdate.setVersion(oldValues.getVersion());

        executed = false;

    }

    @Override
    public void redo() {
        run();

    }

    @Override
    public boolean canUndo() {
        return executed == true;
    }

    @Override
    public boolean canRedo() {
        return executed == false;
    }

}
