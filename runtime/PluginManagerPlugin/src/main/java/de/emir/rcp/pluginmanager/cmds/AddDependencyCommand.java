package de.emir.rcp.pluginmanager.cmds;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.pluginmanager.manager.PmManager;
import de.emir.rcp.pluginmanager.model.transactions.AddDependencyTransaction;
import de.emir.rcp.pluginmanager.views.dialogs.EditDependencyDialog;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.runtime.epf.ObservableDependency;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;

public class AddDependencyCommand extends AbstractCommand {

    public AddDependencyCommand() {
        PmManager pm = ServiceManager.get(PmManager.class);
        if (pm != null && pm.getModelProvider() != null) {
	        pm.getModelProvider().subscribeProductFile(opt -> setCanExecute(opt.isPresent()));
	
	        setCanExecute(pm.getModelProvider().getProductFile() != null);
        }else
        	setCanExecute(false);

    }

    @Override
    public void execute() {

        PmManager pm = ServiceManager.get(PmManager.class);
        ProductFile pf = pm.getModelProvider().getProductFile();

        if (pf == null) {
            return;
        }

        MainWindow mw = PlatformUtil.getWindowManager().getMainWindow();
        EditDependencyDialog dialog = new EditDependencyDialog(mw);

        dialog.setVisible(true);

        ObservableDependency result = dialog.getResult();

        if (result == null) {
            return;
        }

        AddDependencyTransaction t = new AddDependencyTransaction(pf, result);

        pm.getModelProvider().getTransactionStack().run(t);

    }

}
