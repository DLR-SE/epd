package de.emir.rcp.cmd;


import de.emir.rcp.commands.AbstractCommand;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class SetValueCommand extends AbstractCommand {

	private Pointer mPointer;
	private Object mValue;
	private AbstractCommand command;

	public SetValueCommand(Pointer ptr, Object value) {
		mPointer = ptr;
		mValue = value;
	}

	protected void setValue(Object value) {
		mValue = value;
	}
	public Object getValue() { return mValue; }
	
	@Override
	public void execute() {
		//TODO
		if (mPointer != null && mPointer.isValid()){
			Object oldValue = mPointer.getValue();
			setUndoCommand(new SetValueCommand(mPointer, oldValue));
			mPointer.assign(getValue(), false);
		}
	}
	public AbstractCommand getUndoCommand() { return command;}
	public void setUndoCommand(AbstractCommand cmd){ command = cmd;}

}
