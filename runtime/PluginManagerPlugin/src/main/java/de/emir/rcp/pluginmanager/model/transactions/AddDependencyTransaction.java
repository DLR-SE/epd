package de.emir.rcp.pluginmanager.model.transactions;

import de.emir.rcp.model.AbstractModelTransaction;
import de.emir.tuml.runtime.epf.ObservableDependency;
import de.emir.tuml.runtime.epf.ProductFile;

public class AddDependencyTransaction extends AbstractModelTransaction {

    private ProductFile pf;
    private ObservableDependency dependency;

    private boolean executed = false;

    public AddDependencyTransaction(ProductFile pf, ObservableDependency dependency) {
        this.pf = pf;
        this.dependency = dependency;
    }

    @Override
    public void run() {

        pf.addDependency(dependency);

        executed = true;

    }

    @Override
    public void undo() {

        pf.removeDependency(dependency);
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
