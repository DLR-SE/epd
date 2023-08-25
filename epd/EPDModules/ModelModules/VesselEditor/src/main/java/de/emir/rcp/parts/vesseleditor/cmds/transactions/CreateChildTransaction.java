package de.emir.rcp.parts.vesseleditor.cmds.transactions;

import de.emir.rcp.model.AbstractModelTransaction;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class CreateChildTransaction extends AbstractModelTransaction {

	private Pointer mPtr;
	private UClass mClassifier;
	
	private boolean mUndone = false;
	private Pointer mUndoPtr = null; //remembers the location of the created object 

	public CreateChildTransaction(Pointer ptr, UClass vcl) {
		mPtr = ptr;
		mClassifier = vcl;
	}

	@Override
	public void run() {
		UObject uobj = mClassifier.createNewInstance();
		if (uobj != null) {
			mPtr.assign(uobj, false);
			mUndoPtr = PointerOperations.createPointerToObject(uobj);
		}
	}

	@Override
	public void undo() {
		PointerOperations.unset(mUndoPtr);
		mUndone = true;
	}

	@Override
	public void redo() {
		run();
	}

	@Override
	public boolean canUndo() {
		return mUndoPtr != null && mUndoPtr.isValid();
	}

	@Override
	public boolean canRedo() {
		return mUndone;
	}

	public <T> T getCreatedModel() {
		if (mUndoPtr == null)
			return null;
		return mUndoPtr.get();
	}
	
}