package de.emir.rcp.pluginmanager.cmds;

import java.awt.Window;
import java.util.List;
import java.util.Optional;

import javax.swing.SwingUtilities;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.SelectionManager;
import de.emir.rcp.manager.WindowManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelTransaction;
import de.emir.rcp.pluginmanager.ids.PMBasics;
import de.emir.rcp.pluginmanager.manager.PmManager;
import de.emir.rcp.pluginmanager.model.transactions.UpdateDependencyTransaction;
import de.emir.rcp.pluginmanager.model.transactions.UpdateRepositoryTransaction;
import de.emir.rcp.pluginmanager.views.dialogs.EditDependencyDialog;
import de.emir.rcp.pluginmanager.views.dialogs.EditRepositoryDialog;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.runtime.epf.ObservableDependency;
import de.emir.tuml.runtime.epf.ObservableRepository;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import io.tesla.aether.Repository;

public class EditElementCommand extends AbstractCommand {

    public EditElementCommand() {

        SelectionManager sm = PlatformUtil.getSelectionManager();
        sm.subscribe(PMBasics.PLUGIN_TREE_SELECTION_CTX, opt -> handleSelection(opt));

    }

    private void handleSelection(Optional<Object> opt) {

        List<?> selection = PlatformUtil.getSelectionManager()
                .getSelectedObjectAsList(PMBasics.PLUGIN_TREE_SELECTION_CTX);

        if (selection.size() != 1) {
            setCanExecute(false);
            return;
        }

        Object o = selection.get(0);

        if (o instanceof ObservableRepository || o instanceof ObservableDependency) {
            setCanExecute(true);
        } else {
            setCanExecute(false);
        }

    }

    @Override
    public void execute() {

        WindowManager wm = PlatformUtil.getWindowManager();
        MainWindow mw = wm.getMainWindow();

        Window parentWindow = SwingUtilities.windowForComponent(mw);

        List<?> selection = PlatformUtil.getSelectionManager()
                .getSelectedObjectAsList(PMBasics.PLUGIN_TREE_SELECTION_CTX);
        Object o = selection.get(0);

        if (o instanceof ObservableRepository) {

            ObservableRepository repo = (ObservableRepository) o;

            EditRepositoryDialog dialog = new EditRepositoryDialog(parentWindow, repo);

            dialog.setModal(true);
            dialog.setVisible(true);

            Repository result = dialog.getResult();

            if (result == null) {
                return;
            }

            AbstractModelTransaction t = new UpdateRepositoryTransaction(repo, result);

            PmManager pm = ServiceManager.get(PmManager.class);
            pm.getModelProvider().getTransactionStack().run(t);
        } else if (o instanceof ObservableDependency) {

            ObservableDependency dependency = (ObservableDependency) o;

            EditDependencyDialog dialog = new EditDependencyDialog(parentWindow, dependency);

            dialog.setModal(true);
            dialog.setVisible(true);

            ObservableDependency result = dialog.getResult();

            if (result == null) {
                return;
            }

            AbstractModelTransaction t = new UpdateDependencyTransaction(dependency, result);

            PmManager pm = ServiceManager.get(PmManager.class);
            pm.getModelProvider().getTransactionStack().run(t);

        }

    }

}
