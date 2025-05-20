package de.emir.rcp.editor.model.transactions;

import de.emir.rcp.model.AbstractModelTransaction;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;

/**
 * Create a new child to the data model at the given Pointer. This is especially useful to populate the model tree with
 * actual instances of required child UObjects.
 */
public class CreateChildTransaction extends AbstractModelTransaction {
	/**	The Pointer to this child. **/
	private Pointer mPtr;
	/** The UClass (type) of the child UObject. **/
	private UClass mClassifier;
	/** Has been undone? **/
	private boolean mUndone = false;
	/** Remembers the location of the created UObject. **/
	private Pointer mUndoPtr = null; 

	/**
	 * Constructor for this transaction.
	 * 
	 * @param ptr the Pointer to this child
	 * @param vcl the classifier (type)
	 */
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

	/**
	 * The newly created child UObject.
	 * 
	 * @param <T> the type of this child to return
	 * @return the newly created model element
	 */
	public <T> T getCreatedModel() {
		if (mUndoPtr == null)
			return null;
		return mUndoPtr.get();
	}
	
}