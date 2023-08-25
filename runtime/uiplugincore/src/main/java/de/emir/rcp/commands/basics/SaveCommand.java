package de.emir.rcp.commands.basics;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.rcp.model.ModelTransactionStack;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Executes a save operation on the currently active editor
 * @author fklein
 *
 */
public class SaveCommand extends AbstractCommand {

	private Disposable stackSubscription;
	
	public SaveCommand() {
		
		AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();
		mp.subscribeTransactionStack(opt -> checkStackSubscription());
		
		checkState();
	}
	
	private void checkStackSubscription() {
		
		if(stackSubscription != null) {
			stackSubscription.dispose();
		}
		
		AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();
		ModelTransactionStack ts = mp.getTransactionStack();
		
		checkState();
		
		if(ts == null) {
			return;
		}

		stackSubscription = ts.subscribeDirtyState(c -> checkState());
		
		
		
	}

	@Override
	public void execute() {
		
		AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();
		ModelTransactionStack ts = mp.getTransactionStack();
		
		if(ts == null) {
			return;
		}
		
		ts.save();
		
	}
	
	private void checkState() {
		
		
		
		AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();
		
		ModelTransactionStack ts = mp.getTransactionStack();

		setCanExecute(ts != null && ts.isDirty());
	}
	
	

}
