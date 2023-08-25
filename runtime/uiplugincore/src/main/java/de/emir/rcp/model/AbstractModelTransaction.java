package de.emir.rcp.model;

/**
 * An abstract model transaction with undo/redo functionality. Transactions have to be executed by a TransactionStack
 * 
 * @author fklein
 *
 */
public abstract class AbstractModelTransaction {

    public abstract void run();

    public abstract void undo();

    public abstract void redo();

    public abstract boolean canUndo();

    public abstract boolean canRedo();

}
