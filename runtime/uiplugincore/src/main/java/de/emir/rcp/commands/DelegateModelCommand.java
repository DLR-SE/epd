package de.emir.rcp.commands;

import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelTransaction;

/**
 * Delegates an AbstractModelCommand, to be executed (or at least the execution is initialized), using the default
 * command stack
 * 
 * this may be used to run EditorCommands as part of menu entries
 * 
 * @author sschweigert
 *
 */
public class DelegateModelCommand extends AbstractCommand {

    private AbstractModelTransaction mCmd;
    private Class<? extends AbstractModelTransaction> mClazz;

    public DelegateModelCommand(Class<? extends AbstractModelTransaction> clazz) {
        mClazz = clazz;
        setCanExecute(getTransaction() != null);
    }

    public DelegateModelCommand(AbstractModelTransaction cmd) {
        mCmd = cmd;
        setCanExecute(getTransaction() != null);
    }

    AbstractModelTransaction getTransaction() {
        if (mCmd != null)
            return mCmd;
        if (mClazz != null)
            try {
                return mCmd = mClazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        return null;
    }

    @Override
    public void execute() {
        PlatformUtil.getEditorManager().getActiveEditor().getTransactionStack().run(getTransaction());
    }

}
