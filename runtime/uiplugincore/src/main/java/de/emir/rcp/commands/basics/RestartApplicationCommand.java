package de.emir.rcp.commands.basics;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;

import javax.swing.JOptionPane;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.rcp.model.ModelTransactionStack;
import de.emir.rcp.properties.PropertyStore;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class RestartApplicationCommand extends AbstractCommand {

    @Override
    public void execute() {

        MainWindow mw = PlatformUtil.getWindowManager().getMainWindow();

        int result = JOptionPane.showConfirmDialog(mw,
                "The application has to be restarted to apply changes, do you want to restart now?", "Restart",
                JOptionPane.YES_NO_OPTION);

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        boolean editorsShutdown = PlatformUtil.getEditorManager().shutdown();

        if (editorsShutdown == false) {
            return;
        }

        // We may have a transaction stack not bound to an editor that might be
        // dirty and needs a save.
        AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();
        ModelTransactionStack ts = mp.getTransactionStack();

        if (ts != null) {

            if (ts.isDirty() == true) {

                String modelName = mp.getModelIdentifier();
                int rc = JOptionPane.showConfirmDialog(mw, "'" + modelName + "' has been modified. Save changes?",
                        "Save", JOptionPane.YES_NO_CANCEL_OPTION);

                if (rc == JOptionPane.YES_OPTION) {

                    ts.save();
                }

                if (rc == JOptionPane.CANCEL_OPTION) {
                    return;
                }

            }

        }

        // from this point, we can not stop the shutdown sequence

        // notify all view plugins, that we are going to shut down
        mw.notifyAboutToClose();
        mw.saveLayout();
        PropertyStore.save();

        mw.notifyClosed();

        StringBuilder cmd = new StringBuilder();
        cmd.append(System.getProperty("java.home") + File.separator + "bin" + File.separator + "java ");
        for (String jvmArg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
            cmd.append(jvmArg + " ");
        }
        cmd.append("-cp ").append(ManagementFactory.getRuntimeMXBean().getClassPath()).append(" ");
        String java_command = System.getProperty("sun.java.command");
        cmd.append(java_command).append(" ");
        try {
            ULog.warn("Restart Application up on user request with the following command: " + cmd.toString());
            Runtime.getRuntime().exec(cmd.toString()); // we are no longer interested in the process, we will kill this
                                                       // one in the next statement
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);

    }

}
