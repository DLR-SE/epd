package de.emir.rcp.pluginmanager.model.transactions;

import java.util.ArrayList;
import java.util.List;

import de.emir.rcp.model.AbstractModelTransaction;
import de.emir.tuml.runtime.epf.ObservableDependency;
import de.emir.tuml.runtime.epf.ProductFile;

public class RemoveDependenciesTransaction extends AbstractModelTransaction {

    private ProductFile pf;
    private List<ObservableDependency> toRemove;

    private boolean executed = false;
    private int[] indexes;

    public RemoveDependenciesTransaction(ProductFile pf, List<ObservableDependency> dependenciesToDelete) {
        this.pf = pf;
        this.toRemove = dependenciesToDelete;
    }

    public RemoveDependenciesTransaction(ProductFile pf, ObservableDependency toRemove) {
        this.pf = pf;
        this.toRemove = new ArrayList<>();
        this.toRemove.add(toRemove);
    }

    @Override
    public void run() {

        indexes = new int[toRemove.size()];

        int i = 0;
        for (ObservableDependency d : toRemove) {

            indexes[i] = pf.removeDependency(d);
            i++;

        }

        executed = true;

    }

    @Override
    public void undo() {

        ObservableDependency[] toRemoveArray = toRemove.toArray(new ObservableDependency[0]);

        for (int j = toRemoveArray.length - 1; j >= 0; j--) {

            if (indexes[j] != -1) {
                pf.addDependency(toRemoveArray[j], indexes[j]);
            }

        }
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
