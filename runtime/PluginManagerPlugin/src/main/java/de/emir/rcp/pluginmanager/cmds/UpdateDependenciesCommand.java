package de.emir.rcp.pluginmanager.cmds;

import java.awt.Window;
import java.util.List;
import java.util.Optional;

import javax.swing.SwingUtilities;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.ModelManager;
import de.emir.rcp.manager.SelectionManager;
import de.emir.rcp.manager.WindowManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelTransaction;
import de.emir.rcp.pluginmanager.ids.PMBasics;
import de.emir.rcp.pluginmanager.manager.PmManager;
import de.emir.rcp.pluginmanager.model.transactions.UpdateDependencyTransaction;
import de.emir.rcp.pluginmanager.model.transactions.UpdateRepositoryTransaction;
import de.emir.rcp.pluginmanager.model.transactions.UpdateVersionsTransaction;
import de.emir.rcp.pluginmanager.views.dialogs.EditDependencyDialog;
import de.emir.rcp.pluginmanager.views.dialogs.EditRepositoryDialog;
import de.emir.rcp.pluginmanager.views.dialogs.UpdateVersionsDialog;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.runtime.epf.ObservableDependency;
import de.emir.tuml.runtime.epf.ObservableRepository;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import io.tesla.aether.Repository;

public class UpdateDependenciesCommand extends AbstractCommand {

	public UpdateDependenciesCommand() {
		PmManager pm = ServiceManager.get(PmManager.class);
		if (pm != null && pm.getModelProvider() != null) {
			pm.getModelProvider().subscribeProductFile(opt -> setCanExecute(opt.isPresent()));

			setCanExecute(pm.getModelProvider().getProductFile() != null
					&& pm.getModelProvider().getProductFile().getDependencies() != null
					&& !pm.getModelProvider().getProductFile().getDependencies().isEmpty());
		} else {
			setCanExecute(false);
		}
	}

	@Override
	public void execute() {
		PmManager pm = ServiceManager.get(PmManager.class);
		ProductFile pf = pm.getModelProvider().getProductFile();
		WindowManager wm = PlatformUtil.getWindowManager();
		MainWindow mw = wm.getMainWindow();

		Window parentWindow = SwingUtilities.windowForComponent(mw);

		UpdateVersionsDialog dialog = new UpdateVersionsDialog(parentWindow, pf);

		dialog.setModal(true);
		dialog.setVisible(true);

		String group = dialog.getGroup();
		String version = dialog.getVersion();

		if ((version == null || version.isEmpty()) || (group == null || group.isEmpty())) {
			return;
		}

		AbstractModelTransaction t = new UpdateVersionsTransaction(pf, group, version);

		pm.getModelProvider().getTransactionStack().run(t);
	}

}
