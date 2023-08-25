package de.emir.rcp.pluginmanager.model.transactions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.emir.rcp.model.AbstractModelTransaction;
import de.emir.tuml.runtime.epf.ProductFile;

public class AddPluginsTransaction extends AbstractModelTransaction {

    private ProductFile pf;

    private boolean executed = false;

    private List<File> pomsToAdd;

    public AddPluginsTransaction(ProductFile pf, File pomToAdd) {
        this.pf = pf;
        this.pomsToAdd = new ArrayList<>();
        this.pomsToAdd.add(pomToAdd);
    }

    public AddPluginsTransaction(ProductFile pf, List<File> pomsToAdd) {
        this.pf = pf;
        this.pomsToAdd = pomsToAdd;
    }

    @Override
    public void run() {

        for (File file : pomsToAdd) {
            pf.addWorkspace(file);
        }

        executed = true;

    }

    @Override
    public void undo() {

        File[] toAddArray = pomsToAdd.toArray(new File[0]);

        for (int j = toAddArray.length - 1; j >= 0; j--) {

            pf.removeWorkspace(toAddArray[j]);

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
