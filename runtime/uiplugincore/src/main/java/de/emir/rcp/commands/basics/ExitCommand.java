package de.emir.rcp.commands.basics;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.editors.transactions.EditorTransactionStack;
import de.emir.rcp.manager.EditorManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.rcp.model.ModelTransactionStack;
import de.emir.rcp.properties.PropertyStore;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * This command terminates the application.
 * 
 * @author fklein
 *
 */
public class ExitCommand extends AbstractCommand {

    public static final String EXIT_COMMAND_ShowDialog_Context = "System";
    public static final String EXIT_COMMAND_ShowDialog_Property = "ShowExitDialog";

    /**
     * Initiates the close sequence to close the EPD. If the user does not confirm the shutdown, the application
     * will not be closed.
     * @param exitCode Exit code with which to exit the application.
     */
    private void doExecute(int exitCode){
        MainWindow mw = PlatformUtil.getWindowManager().getMainWindow();

        EditorManager em = PlatformUtil.getEditorManager();

        AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();
        ModelTransactionStack ts = mp.getTransactionStack();

        // If Stack is a EditorTransactionStack this case is handled by editors shutdown method
        boolean stackDirty = ts != null && ts instanceof EditorTransactionStack == false && ts.isDirty() == true;

        if (em.isSomeEditorDirty() == false && stackDirty == false) {

            boolean showDiag = PropertyStore.getContext(EXIT_COMMAND_ShowDialog_Context)
                    .getValue(EXIT_COMMAND_ShowDialog_Property, true);

            if (showDiag) {

                JCheckBox cb = new JCheckBox("Do not show this message again");

                String message = "Exit Application?";
                Object[] params = { message, cb };
                int result = JOptionPane.showConfirmDialog(
                        mw,
                        params,
                        "Exit Application",
                        JOptionPane.YES_NO_OPTION
                );

                if (result != JOptionPane.YES_OPTION) {
                    return;
                }

                PropertyStore.getContext(EXIT_COMMAND_ShowDialog_Context)
                        .setValue(EXIT_COMMAND_ShowDialog_Property, !cb.isSelected());
            }
        }

        boolean editorsShutdown = em.shutdown();

        if (editorsShutdown == false) {
            return;
        }

        // We may have a transaction stack not bound to an editor that might be
        // dirty and needs a save.
        if (stackDirty == true) {

            String modelName = mp.getModelIdentifier();
            int rc = JOptionPane.showConfirmDialog(
                    mw,
                    "'" + modelName + "' has been modified. Save changes?",
                    "Save",
                    JOptionPane.YES_NO_CANCEL_OPTION
            );

            if (rc == JOptionPane.YES_OPTION) {

                ts.save();
            }

            if (rc == JOptionPane.CANCEL_OPTION) {
                return;
            }

        }

        // from this point, we can not stop the shutdown sequence

        // notify all view plugins, that we are going to shut down
        mw.notifyAboutToClose();
        mw.saveLayout();
        PropertyStore.save();

        mw.notifyClosed();
        System.exit(exitCode);
    }

    /**
     * Executes the shutdown sequence. If the user does not confirm the shutdown, the shutdown will be aborted.
     */
    @Override
    public void execute() {
        int exitCode = 0;
        try {
            doExecute(exitCode);
        } catch (Exception e){
            ULog.error(e);
            exitCode = 1;
            System.exit(exitCode);
        }
    }

}
