package de.emir.rcp.properties.cmd;

import java.util.List;

import de.emir.rcp.editors.transactions.AbstractEditorTransaction;
import de.emir.tuml.ucore.runtime.utils.FeaturePointer;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class AddListEntryTransaction extends AbstractEditorTransaction {

	private Pointer 		mPointer;
	private boolean 		mIsDone;
	private boolean 		mIsUndone;
	private Object 			mNewValue;
	

	public AddListEntryTransaction(Pointer ptr, Object value) {
		mPointer = ptr;
		mNewValue = value;
	}
	@Override
	public void run() {
		if (mPointer == null || mPointer.isValid() == false)
			return ;
		
		mPointer.assign(mNewValue, false);
		mIsDone = true;
	}

	@Override
	public void undo() {
		if (mPointer == null || mPointer.isValid() == false) {
			Pointer ptr = mPointer.copy();
			if (ptr instanceof FeaturePointer) {
				((FeaturePointer)ptr).setListIndex(-1); //access the whole list
				List list = (List) ptr.getValue();
				
				mIsUndone = list.remove(mNewValue);
			}
		}
	}

	@Override
	public void redo() {
		run();
		mIsUndone = false;
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
