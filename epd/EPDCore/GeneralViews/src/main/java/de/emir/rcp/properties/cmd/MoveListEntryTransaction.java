package de.emir.rcp.properties.cmd;

import java.util.List;

import de.emir.rcp.editors.transactions.AbstractEditorTransaction;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.FeaturePointer;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class MoveListEntryTransaction extends AbstractEditorTransaction {
	private Pointer mPointer;
	private int mDelta;
	private int mOldIndex;
	private Object mOldValue;
	private boolean mIsDone;
	private boolean mIsUndone;

	public MoveListEntryTransaction(Pointer ptr, int delta) {
		mPointer = ptr;
		mDelta = delta;
	}
	@Override
	public void run() {
		if (mPointer == null || mPointer.isValid() == false) {
			return;
		}
		
		// keep a reference to the old state
		mOldValue = mPointer.getValue();
		
		// keep a copy of the pointer before removal
		Pointer ptr = mPointer.copy();
		// remember the index
		mOldIndex = ((FeaturePointer)mPointer).getListIndex();
		PointerOperations.unset(mPointer);
		if (ptr instanceof FeaturePointer) {
			((FeaturePointer)ptr).setListIndex(-1); //access the whole list
			List<Object> list = (List<Object>) ptr.getValue();
			int newPos = mOldIndex + mDelta;
			if (newPos < 0) newPos = 0;
			
			if (newPos < list.size()) {
				list.add(newPos, mOldValue);
			} else {
				list.add(mOldValue);
			}
		}
		mIsDone = true;
	}

	@Override
	public void undo() {
		Pointer ptr = mPointer.copy();
		if (ptr instanceof FeaturePointer) {
			((FeaturePointer)ptr).setListIndex(-1);
			if (mOldValue instanceof UObject old) {
				Pointer newPtr = PointerOperations.createPointerToObject(old);
				PointerOperations.unset(newPtr);
			}
			List<Object> list = (List<Object>) ptr.getValue();
			list.add(mOldIndex, mOldValue);
		}
		mIsUndone = true;

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
