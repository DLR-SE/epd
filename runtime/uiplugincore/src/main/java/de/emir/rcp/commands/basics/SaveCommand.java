package de.emir.rcp.commands.basics;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.rcp.model.ModelTransactionStack;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Executes a save operation on the currently active editor
 *
 * @author fklein
 */
public class SaveCommand extends AbstractCommand {

    private Disposable stackSubscription;

    /**
     * Creates a new SaveCommand. This command saves the currently active editor when executed.
     */
    public SaveCommand() {
        AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();
        mp.subscribeTransactionStack(opt -> checkStackSubscription());

        checkState();
    }

    /**
     * Subscribes to the transaction stack of the model and deregisters the old subscriber if it exists.
     */
    private void checkStackSubscription() {

        if (stackSubscription != null) {
            stackSubscription.dispose();
        }

        AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();
        ModelTransactionStack ts = mp.getTransactionStack();

        checkState();

        if (ts == null) {
            return;
        }

        stackSubscription = ts.subscribeDirtyState(c -> checkState());

    }

    /**
     * Executes the command. This saves the current model transaction stack.
     */
    @Override
    public void execute() {
        AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();
        ModelTransactionStack ts = mp.getTransactionStack();

        if (ts == null) {
            return;
        }

        ts.save();
    }

    /**
     * Checks the state of the transaction stack. If it is dirty, i.e. needs saving, the command will be enabled to
     * allow execution.
     */
    private void checkState() {

        AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();

        ModelTransactionStack ts = mp.getTransactionStack();
        // we do not check ts.isDirty() here to let users save with an empty transaction
        // stack. We do this since changes could be made without the ModelTransactionStack
        setCanExecute(ts != null);
    }


}
