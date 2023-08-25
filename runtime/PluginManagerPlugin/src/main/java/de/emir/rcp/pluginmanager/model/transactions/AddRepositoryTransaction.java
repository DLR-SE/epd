package de.emir.rcp.pluginmanager.model.transactions;

import de.emir.rcp.model.AbstractModelTransaction;
import de.emir.tuml.runtime.epf.ObservableRepository;
import de.emir.tuml.runtime.epf.ProductFile;

public class AddRepositoryTransaction extends AbstractModelTransaction {

    private ProductFile pf;
    private ObservableRepository repo;

    private boolean executed = false;

    public AddRepositoryTransaction(ProductFile pf, ObservableRepository repo) {
        this.pf = pf;
        this.repo = repo;
    }

    @Override
    public void run() {

        pf.addRepository(repo);

        executed = true;

    }

    @Override
    public void undo() {

        pf.removeRepository(repo);
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
