package de.emir.rcp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

/**
 * A transaction stack handles ModelTransactions. It manages the undo/redo functionality.
 * 
 * @author fklein
 *
 */
public class ModelTransactionStack extends Observable {

    // TODO: Maybe synchronized
    private List<AbstractModelTransaction> transactions = new LinkedList<>();

    private int lastRunTransactionIndex = -1;

    private AbstractModelTransaction savedStateTransaction = null;

    private IDirtyStateProvider dsp;

    private PublishSubject<Boolean> dirtyStateSubject = PublishSubject.create();

    public ModelTransactionStack(IDirtyStateProvider dsp) {
        this.dsp = dsp;
    }

    /**
     * Undo the last ModelTransaction. This method shouldn't be called directly. Use a UndoCommand instead.
     */
    public void undo() {

        AbstractModelTransaction trans = getUndoTransaction();

        if (trans != null) {
            trans.undo();
            setLastRunTransactionIndex(lastRunTransactionIndex - 1);

            setDirty(getUndoTransaction() != savedStateTransaction);
        }
    }

    /**
     * Redo the last ModelTransaction. This method shouldn't be called directly. Use a RedoCommand instead.
     */
    public void redo() {

        AbstractModelTransaction trans = getRedoTransaction();

        if (trans != null) {
            trans.redo();
            setLastRunTransactionIndex(lastRunTransactionIndex + 1);

            setDirty(getUndoTransaction() != savedStateTransaction);

        }

    }

    public Disposable subscribeDirtyState(Consumer<Boolean> c) {
        return dirtyStateSubject.subscribe(c);
    }

    private void setDirty(boolean dirty) {

        if (dirty == dsp.isDirty()) {
            return;
        }

        dsp.setDirty(dirty);
        dirtyStateSubject.onNext(dsp.isDirty());

    }

    private void setLastRunTransactionIndex(int newValue) {
        lastRunTransactionIndex = newValue;
        setChanged();
        notifyObservers();
    }

    public void run(AbstractModelTransaction trans) {

        List<AbstractModelTransaction> toRemove = new ArrayList<>();

        for (int i = lastRunTransactionIndex + 1; i < transactions.size(); i++) {
            toRemove.add(transactions.get(i));
        }

        synchronized (transactions) {
        	transactions.removeAll(toRemove);
            transactions.add(trans);	
		}
        

        trans.run();

        setLastRunTransactionIndex(lastRunTransactionIndex + 1); // change index after the transaction has been executed
        setDirty(true);

    }

    private AbstractModelTransaction getRedoTransaction() {
        int redoIndex = lastRunTransactionIndex + 1;

        if (redoIndex >= 0 && redoIndex < transactions.size()) {
            return transactions.get(redoIndex);
        }

        return null;
    }

    private AbstractModelTransaction getUndoTransaction() {

        if (lastRunTransactionIndex >= 0 && lastRunTransactionIndex < transactions.size()) {
            return transactions.get(lastRunTransactionIndex);
        }

        return null;
    }

    public boolean canUndo() {
        AbstractModelTransaction trans = getUndoTransaction();

        if (trans == null) {
            return false;
        }

        return trans.canUndo();
    }

    public boolean canRedo() {
        AbstractModelTransaction trans = getRedoTransaction();

        if (trans == null) {
            return false;
        }

        return trans.canRedo();

    }

    /**
     * In order to handle the correct dirty state when undoing/redoing
     */
    public void handleStateSaved() {

        if (lastRunTransactionIndex == -1) {
            savedStateTransaction = null;
        } else {
            try {
                savedStateTransaction = transactions.get(lastRunTransactionIndex);
            } catch (IndexOutOfBoundsException iobe) {
                iobe.printStackTrace();
            }
        }

        setDirty(false);

    }

    public boolean save() {

        boolean success = dsp.save();

        if (success == true) {

            handleStateSaved();
        }

        return success;

    }

    /**
     * Returns an unmodifiable list of all transactions
     * 
     * @return
     */
    public List<AbstractModelTransaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public int getSaveStateTransactionIndex() {
        return transactions.indexOf(savedStateTransaction);
    }

    public boolean isDirty() {
        return dsp.isDirty();
    }

}
