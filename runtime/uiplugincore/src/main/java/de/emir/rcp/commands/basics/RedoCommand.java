package de.emir.rcp.commands.basics;

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.rcp.model.ModelTransactionStack;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * Performs a redo operation on the command stack of the currently active editor
 * 
 * @author fklein
 *
 */
public class RedoCommand extends AbstractCommand implements Observer {

    private static Logger log = ULog.getLogger(RedoCommand.class);

    private ModelTransactionStack currentTransactionStack;

    public RedoCommand() {

        AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();
        mp.subscribeTransactionStack(sub -> setCurrentTransactionStack(sub.isPresent() ? sub.get() : null));

    }

    private void setCurrentTransactionStack(ModelTransactionStack stack) {

        if (currentTransactionStack != null) {
            currentTransactionStack.deleteObserver(this);
        }

        currentTransactionStack = stack;
        setCanExecute(currentTransactionStack != null ? currentTransactionStack.canRedo() : false);

        if (currentTransactionStack != null) {
            currentTransactionStack.addObserver(this);
        }

    }

    @Override
    public void execute() {

        if (currentTransactionStack != null) {

            currentTransactionStack.redo();

        } else {
            log.error("Can't redo transaction: TransactionStack is null");
        }

    }

    @Override
    public void update(Observable o, Object arg) {

        if (currentTransactionStack == null) {

            setCanExecute(false);
            log.error("TransactionStack changed but is null");
            return;
        }
        setCanExecute(currentTransactionStack.canRedo());

    }

}
