package de.emir.rcp.commands.basics;

import java.util.Observable;
import java.util.Observer;

import org.apache.logging.log4j.Logger;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.rcp.model.ModelTransactionStack;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * Performs a undo operation on the command stack of the currently active editor.
 * 
 * @author fklein
 *
 */
public class UndoCommand extends AbstractCommand implements Observer {

    private static Logger log = ULog.getLogger(UndoCommand.class);

    private ModelTransactionStack currentTransactionStack;

    public UndoCommand() {
        AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();
        mp.subscribeTransactionStack(sub -> setCurrentTransactionStack(sub.isPresent() ? sub.get() : null));
    }

    private void setCurrentTransactionStack(ModelTransactionStack stack) {
        if (currentTransactionStack != null) {
            currentTransactionStack.deleteObserver(this);
        }

        currentTransactionStack = stack;
        setCanExecute(currentTransactionStack != null ? currentTransactionStack.canUndo() : false);

        if (currentTransactionStack != null) {
            currentTransactionStack.addObserver(this);
        }
    }

    @Override
    public void execute() {
        if (currentTransactionStack != null) {
            currentTransactionStack.undo();
        } else {
            log.error("Can't undo transaction: TransactionStack is null");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (currentTransactionStack == null) {

            setCanExecute(false);
            log.error("TransactionStack changed but is null");
            return;
        }
        setCanExecute(currentTransactionStack.canUndo());
    }
}
