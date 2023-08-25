package de.emir.rcp.pluginmanager.model.transactions;

import de.emir.rcp.model.AbstractModelTransaction;
import de.emir.tuml.runtime.epf.ProductFile;

public class SetLocalRepositoryTransaction extends AbstractModelTransaction {

    private ProductFile pf;
    private String path;

    private boolean executed = false;
    private String oldValue;

    public SetLocalRepositoryTransaction(ProductFile pf, String path) {
        this.pf = pf;
        this.path = path;
    }

    @Override
    public void run() {

        oldValue = pf.getLocalRepository();

        pf.setLocalRepository(path);

        executed = true;

    }

    @Override
    public void undo() {

        pf.setLocalRepository(oldValue);
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
