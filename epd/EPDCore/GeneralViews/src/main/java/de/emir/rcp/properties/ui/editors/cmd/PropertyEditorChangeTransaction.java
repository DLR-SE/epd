package de.emir.rcp.properties.ui.editors.cmd;

import de.emir.rcp.editors.AbstractEditor;
import de.emir.rcp.editors.transactions.AbstractEditorTransaction;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class PropertyEditorChangeTransaction extends AbstractEditorTransaction {

	private Object 				mNewValue;
	private Pointer 			mPointer;
	
	private Object				mOldValue = null; //remember old value, after the command has been executed - for undo
	private boolean				mIsUndone = false;
	private boolean				mIsDone = false; //need to remember this, since the old value may be null

	public PropertyEditorChangeTransaction(Pointer ptr, Object newValue, AbstractEditor editor) {
		mPointer = ptr;
		mNewValue = newValue;
		setEditor(editor);
	}
	
	@Override
	public void run() {
		mOldValue = mPointer.getValue();
		mPointer.setValue(mNewValue); //thats the real content of the command
		mIsDone = true;
	}

	@Override
	public void undo() {
		mPointer.setValue(mOldValue);
		mOldValue = null; 
		mIsUndone = true;
	}

	@Override
	public void redo() {
		run();
	}

	@Override
	public boolean canUndo() {
		return mIsDone;
	}

	@Override
	public boolean canRedo() {
		return mIsUndone;
	}

}
