package de.emir.rcp.editor.text.cmd;

import javax.swing.undo.CompoundEdit;
import javax.swing.undo.UndoableEdit;

import de.emir.rcp.editors.transactions.AbstractEditorTransaction;

public class TextEditTransaction extends AbstractEditorTransaction {

	private CompoundEdit ce = new CompoundEdit();
	
	private int edits = 0;

	private boolean isUndone = false;
	private boolean manuallyUndoneRedone = false;

	@Override
	public void run() {

	}

	public void addEdit(UndoableEdit edit) {
		ce.addEdit(edit);
		edits++;
	}
	
	@Override
	public void undo() {
		manuallyUndoneRedone = true;
		ce.end();
		ce.undo();
		isUndone  = true;

	}
	
	@Override
	public void redo() {
		manuallyUndoneRedone = true;
		ce.redo();
		isUndone = false;

	}
	
	@Override
	public boolean canUndo() {
		return edits > 0;
	}
	
	@Override
	public boolean canRedo() {
		return edits > 0 && isUndone == true;
	}

	/**
	 * Wether the user has undone / redone this transaction at least once
	 * @return
	 */
	public boolean hasBeenUndoRedo() {
		return manuallyUndoneRedone;
	}

}
