package de.emir.rcp.pluginmanager.cmds;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.pluginmanager.manager.PmManager;
import de.emir.rcp.pluginmanager.model.transactions.AddPluginsTransaction;
import de.emir.rcp.pluginmanager.views.dialogs.AddPluginDialog;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

public class AddPluginCommand extends AbstractCommand {

    public AddPluginCommand() {
        PmManager pm = ServiceManager.get(PmManager.class);

        if (pm == null || pm.getModelProvider() == null) {
        	setCanExecute(false);
        }else {
	        pm.getModelProvider().subscribeProductFile(opt -> setCanExecute(opt.isPresent()));
	
	        setCanExecute(pm.getModelProvider().getProductFile() != null);
        }
    }

    @Override
    public void execute() {

        PmManager pm = ServiceManager.get(PmManager.class);
        ProductFile pf = pm.getModelProvider().getProductFile();

        if (pf == null) {
            return;
        }

        AddPluginDialog dialog = new AddPluginDialog();
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (dialog.isApplied()) {
                    List<File> selectedFiles = dialog.getChosenFiles();

                    AddPluginsTransaction t = new AddPluginsTransaction(pf, selectedFiles);

                    pm.getModelProvider().getTransactionStack().run(t);
                }
            }
        });
    }

}
