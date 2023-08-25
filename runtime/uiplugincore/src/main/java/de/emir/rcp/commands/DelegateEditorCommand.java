package de.emir.rcp.commands;

import de.emir.rcp.editors.transactions.AbstractEditorTransaction;
import de.emir.rcp.manager.util.PlatformUtil;

/**
 * Delegates an AbstractEditorCommand, to be executed (or at least the execution is initialized), using the default
 * command stack
 * 
 * this may be used to run EditorCommands as part of menu entries
 * 
 * @author sschweigert
 *
 */
public class DelegateEditorCommand extends AbstractCommand {

    private AbstractEditorTransaction mCmd;
    private Class<? extends AbstractEditorTransaction> mClazz;

    public DelegateEditorCommand(Class<? extends AbstractEditorTransaction> clazz) {
        mClazz = clazz;

        setCanExecute(getTransaction() != null);
    }

    public DelegateEditorCommand(AbstractEditorTransaction cmd) {
        mCmd = cmd;

        setCanExecute(getTransaction() != null);

    }

    AbstractEditorTransaction getTransaction() {
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
