package de.emir.rcp.model.transactions;

import java.util.ArrayList;
import java.util.List;

import de.emir.rcp.model.AbstractModelTransaction;

public class CompoundTransaction extends AbstractModelTransaction {

    private List<AbstractModelTransaction> transactions = new ArrayList<>();

    private boolean executed = false;

    public CompoundTransaction() {

    }

    public CompoundTransaction(AbstractModelTransaction... transactions) {
        if (transactions != null)
            for (AbstractModelTransaction t : transactions)
                add(t);
    }

    public void add(AbstractModelTransaction t) {
        transactions.add(t);
    }

    @Override
    public void run() {

        for (AbstractModelTransaction t : transactions) {
            t.run();
        }

        executed = true;

    }

    @Override
    public void undo() {
        for (AbstractModelTransaction t : transactions) {
            t.undo();
        }

        executed = false;

    }

    @Override
    public void redo() {
        for (AbstractModelTransaction t : transactions) {
            t.redo();
        }

        executed = true;
    }

    @Override
    public boolean canUndo() {
        return executed;
    }

    @Override
    public boolean canRedo() {
        return executed == false;
    }

    public boolean isEmpty() {
        return transactions.isEmpty();
    }

}
