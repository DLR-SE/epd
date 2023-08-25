package de.emir.rcp.pluginmanager.cmds;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.pluginmanager.manager.PmManager;
import de.emir.rcp.pluginmanager.model.transactions.AddRepositoryTransaction;
import de.emir.rcp.pluginmanager.views.dialogs.EditRepositoryDialog;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.runtime.epf.ObservableRepository;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;

public class AddRepositoryCommand extends AbstractCommand {

    public AddRepositoryCommand() {
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
        MainWindow mw = PlatformUtil.getWindowManager().getMainWindow();
        EditRepositoryDialog dialog = new EditRepositoryDialog(mw);

        dialog.setVisible(true);

        ObservableRepository result = dialog.getResult();

        if (result == null) {
            return;
        }

        AddRepositoryTransaction t = new AddRepositoryTransaction(pf, result);

        pm.getModelProvider().getTransactionStack().run(t);

    }

}
