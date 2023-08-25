package de.emir.rcp.cmd;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class UnsetValueCommand extends AbstractCommand {

	private Pointer mPointer;

	public UnsetValueCommand(Pointer ptr) {
		mPointer = ptr;
	}

	@Override
	public void execute() {
		try{
			PointerOperations.unset(mPointer);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
