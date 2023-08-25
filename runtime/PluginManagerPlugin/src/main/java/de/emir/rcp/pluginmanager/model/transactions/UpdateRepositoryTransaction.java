package de.emir.rcp.pluginmanager.model.transactions;

import de.emir.rcp.model.AbstractModelTransaction;
import io.tesla.aether.Repository;

public class UpdateRepositoryTransaction extends AbstractModelTransaction {

    private boolean executed = false;
    private Repository toUpdate;
    private Repository newValues;
    private Repository oldValues;

    public UpdateRepositoryTransaction(Repository toUpdate, Repository newValues) {
        this.toUpdate = toUpdate;
        this.newValues = newValues;
    }

    @Override
    public void run() {

        oldValues = new Repository(toUpdate.getUrl());
        oldValues.setId(toUpdate.getId());
        oldValues.setUsername(toUpdate.getUsername());
        oldValues.setPassword(toUpdate.getPassword());

        toUpdate.setUrl(newValues.getUrl());
        toUpdate.setId(newValues.getId());
        toUpdate.setUsername(newValues.getUsername());
        toUpdate.setPassword(newValues.getPassword());

        executed = true;

    }

    @Override
    public void undo() {

        toUpdate.setUrl(oldValues.getUrl());
        toUpdate.setId(oldValues.getId());
        toUpdate.setUsername(oldValues.getUsername());
        toUpdate.setPassword(oldValues.getPassword());

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
