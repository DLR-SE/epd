package de.emir.rcp.pluginmanager.model.transactions;

import java.util.ArrayList;
import java.util.List;

import de.emir.rcp.model.AbstractModelTransaction;
import de.emir.tuml.runtime.epf.ObservableRepository;
import de.emir.tuml.runtime.epf.ProductFile;
import io.tesla.aether.Repository;

public class RemoveRepositoriesTransaction extends AbstractModelTransaction {

    private ProductFile pf;
    private List<Repository> toRemove;

    private boolean executed = false;
    private int[] indexes;

    public RemoveRepositoriesTransaction(ProductFile pf, List<Repository> repositoriesToDelete) {
        this.pf = pf;
        this.toRemove = repositoriesToDelete;
    }

    public RemoveRepositoriesTransaction(ProductFile pf, Repository toRemove) {
        this.pf = pf;
        this.toRemove = new ArrayList<>();
        this.toRemove.add(toRemove);
    }

    @Override
    public void run() {

        indexes = new int[toRemove.size()];

        int i = 0;
        for (Repository r : toRemove) {

            indexes[i] = pf.removeRepository(r);
            i++;

        }

        executed = true;

    }

    @Override
    public void undo() {

        ObservableRepository[] toRemoveArray = toRemove.toArray(new ObservableRepository[0]);

        for (int j = toRemoveArray.length - 1; j >= 0; j--) {

            if (indexes[j] != -1) {
                pf.addRepository(toRemoveArray[j], indexes[j]);
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
