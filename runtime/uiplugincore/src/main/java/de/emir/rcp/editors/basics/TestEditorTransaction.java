package de.emir.rcp.editors.basics;

import de.emir.rcp.editors.transactions.AbstractEditorTransaction;

public class TestEditorTransaction extends AbstractEditorTransaction {

    @Override
    public void run() {
        System.out.println("RUN " + this);

    }

    @Override
    public boolean canUndo() {
        return true;
    }

    @Override
    public boolean canRedo() {
        return true;
    }

    @Override
    public void undo() {
        System.out.println("UNDO " + this);
    }

    @Override
    public void redo() {
        System.out.println("REDO " + this);
    }

}
