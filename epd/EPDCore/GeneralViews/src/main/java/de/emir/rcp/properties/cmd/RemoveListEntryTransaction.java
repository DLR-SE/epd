package de.emir.rcp.properties.cmd;

import java.util.List;

import de.emir.rcp.editors.transactions.AbstractEditorTransaction;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.FeaturePointer;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class RemoveListEntryTransaction extends AbstractEditorTransaction {

	

	private Pointer 		mPointer;
	private Object 			mOldValue;
	private boolean 		mIsDone;
	private boolean 		mIsUndone;
	

	public RemoveListEntryTransaction(Pointer ptr) {
		mPointer = ptr;
	}
	@Override
	public void run() {
		if (mPointer == null || mPointer.isValid() == false)
			return ;
		
		mOldValue = mPointer.getValue();
		PointerOperations.unset(mPointer);
		mIsDone = true;
	}

	@Override
	public void undo() {
		if (mPointer == null || mPointer.isValid() == false) {
			Pointer ptr = mPointer.copy();
			if (ptr instanceof FeaturePointer) {
				((FeaturePointer)ptr).setListIndex(-1); //access the whole list
				List<Object> list = (List<Object>) ptr.getValue();
				int idx = ((FeaturePointer)mPointer).getListIndex();
				if (list.size() >= idx ) {
					list.add(idx, mOldValue);
				}else {
					list.add(mOldValue);
				}
				mIsUndone = true;
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
