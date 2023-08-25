package de.emir.rcp.pluginmanager.cmds;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.SelectionManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.rcp.model.transactions.CompoundTransaction;
import de.emir.rcp.pluginmanager.ids.PMBasics;
import de.emir.rcp.pluginmanager.manager.PmManager;
import de.emir.rcp.pluginmanager.model.transactions.AddPluginsTransaction;
import de.emir.rcp.pluginmanager.model.transactions.RemoveDependenciesTransaction;
import de.emir.rcp.pluginmanager.model.transactions.RemovePluginsTransaction;
import de.emir.rcp.pluginmanager.model.transactions.RemoveRepositoriesTransaction;
import de.emir.rcp.pluginmanager.views.PluginTreeNodeData;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.runtime.epf.ObservableDependency;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.runtime.epf.utils.MavenUtil;
import de.emir.tuml.runtime.epf.utils.WorkspaceResolver;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import io.tesla.aether.Repository;
import org.apache.maven.model.Model;

public class RemoveElementCommand extends AbstractCommand {

    private List<PluginTreeNodeData> pluginsToDelete = new ArrayList<>();
    private List<Repository> repositoriesToDelete = new ArrayList<>();
    private List<ObservableDependency> dependenciesToDelete = new ArrayList<>();

    public RemoveElementCommand() {

        SelectionManager sm = PlatformUtil.getSelectionManager();
        sm.subscribe(PMBasics.PLUGIN_TREE_SELECTION_CTX, opt -> handleSelection(opt));

    }

    private void handleSelection(Optional<Object> opt) {

        pluginsToDelete = new ArrayList<>();
        repositoriesToDelete = new ArrayList<>();
        dependenciesToDelete = new ArrayList<>();

        List<?> selection = PlatformUtil.getSelectionManager()
                .getSelectedObjectAsList(PMBasics.PLUGIN_TREE_SELECTION_CTX);

        if (selection.isEmpty() == true) {
            setCanExecute(false);
            return;
        }

        for (Object o : selection) {

            if (o instanceof PluginTreeNodeData) {
                pluginsToDelete.add((PluginTreeNodeData) o);
            } else if (o instanceof Repository) {
                repositoriesToDelete.add((Repository) o);
            } else if (o instanceof ObservableDependency) {
                dependenciesToDelete.add((ObservableDependency) o);
            }

        }

        setCanExecute(pluginsToDelete.size() > 0 || repositoriesToDelete.size() > 0 || dependenciesToDelete.size() > 0);

    }

    @Override
    public void execute() {
        PmManager pmm = ServiceManager.get(PmManager.class);
        ProductFile pf = pmm.getModelProvider().getProductFile();

        // We check if the user tries to delete implicit entries

        List<PluginTreeNodeData> implicitsToRemove = new ArrayList<>();
        List<PluginTreeNodeData> specialCaseElements = new ArrayList<>();
        boolean warningShown = false;

        for (PluginTreeNodeData data : pluginsToDelete) {

            if (data.parent != null) {
                // The user tries to delete an implicit entry. We check, if the parent of
                // this entry will also be deleted. If so, we can ignore this
                // special case
                if (isParentAlsoDeleted(data) == false) {

                    if (warningShown == false) {
                        warningShown = true;
                        boolean continueDeletion = showIndirectEntryDialog(data);

                        if (continueDeletion == false) {
                            return;
                        }
                    }

                    implicitsToRemove.add(data);
                }else {
                    specialCaseElements.add(data);
                }
            }
        }

        pluginsToDelete.removeAll(specialCaseElements);

        removeSiblings(pluginsToDelete, pf);

        // Group implicits by its parents
        Map<PluginTreeNodeData, List<PluginTreeNodeData>> pgMap = new HashMap<>();

        for (PluginTreeNodeData id : implicitsToRemove) {

            List<PluginTreeNodeData> groupList = pgMap.get(id.parent);
            if (groupList == null) {
                groupList = new ArrayList<>();
                pgMap.put(id.parent, groupList);
            }

            groupList.add(id);

        }

        CompoundTransaction comp = new CompoundTransaction();

        // Remove the parent, add all except the ones to remove

        for (Entry<PluginTreeNodeData, List<PluginTreeNodeData>> entry : pgMap.entrySet()) {

            PluginTreeNodeData parent = entry.getKey();
            List<PluginTreeNodeData> implicits = entry.getValue();

            RemovePluginsTransaction t = new RemovePluginsTransaction(pf, parent);

            comp.add(t);

            for (String mod : parent.model.getModules()) {

                String pomFolder = parent.model.getPomFile().getParent();
                File modFile = new File(pomFolder + File.separator + mod + File.separator + "pom.xml");

                if (isInImplicitFilesList(implicits, modFile) == true) {
                    continue;
                }

                for (File file : pf.getWorkspaces()){
                    if (file.getParent().equals(pomFolder)){
                        AddPluginsTransaction addTrans = new AddPluginsTransaction(pf, modFile);
                        comp.add(addTrans);
                    }
                }
            }
        }

        RemovePluginsTransaction t = new RemovePluginsTransaction(pf, pluginsToDelete);

        comp.add(t);

        RemoveRepositoriesTransaction t2 = new RemoveRepositoriesTransaction(pf, repositoriesToDelete);

        comp.add(t2);

        RemoveDependenciesTransaction t3 = new RemoveDependenciesTransaction(pf, dependenciesToDelete);

        comp.add(t3);

        AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();
        mp.getTransactionStack().run(comp);

        System.out.println(new WorkspaceResolver(new MavenUtil(pf, true)).resolveWorkspaces(pf.getWorkspaces()));
    }

    private void removeSiblings(List<PluginTreeNodeData> pluginsToDelete, ProductFile pf){
        for (PluginTreeNodeData data : new ArrayList<>(pluginsToDelete)){
            if (data.model.getModules() != null && data.model.getModules().size() > 0){
                for (String mod : data.model.getModules()){
                    String pomFolder = data.model.getPomFile().getParent();
                    File modFile = new File(pomFolder + File.separator + mod + File.separator + "pom.xml");
                    Model sibling = new MavenUtil(pf, true).readModel(modFile);
                    PluginTreeNodeData treeNodeData = new PluginTreeNodeData(data, sibling);
                    this.pluginsToDelete.add(treeNodeData);
                }
                this.pluginsToDelete.remove(data);
                removeSiblings(this.pluginsToDelete, pf);
            }
        }
    }

    private boolean isInImplicitFilesList(List<PluginTreeNodeData> implicits, File file) {

        for (PluginTreeNodeData id : implicits) {

            try {
                if (Files.isSameFile(file.toPath(), id.model.getPomFile().toPath())) {
                    return true;
                }
            } catch (IOException e) {

            }

        }

        return false;
    }

    private boolean showIndirectEntryDialog(PluginTreeNodeData data) {

        MainWindow mw = PlatformUtil.getWindowManager().getMainWindow();

        int result = JOptionPane.showConfirmDialog(mw,
                "<html>You're trying to remove an implicit integration:<br><font  color=\"#99b4d1\">"
                        + data.model.getArtifactId()
                        + "</font><br><br>Provided by its parent:<br><font  color=\"#99b4d1\">"
                        + data.parent.model.getArtifactId() + "</font><br><br>"
                        + "The selected module can be removed by replacing the integration<br>"
                        + "of the parent with the integration of each individual module. <br> "
                        + "Would you like to continue? The operation is performed for all <br>other implicit elements.</html>",
                "Removing an implicit integration", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

        return result == JOptionPane.OK_OPTION;
    }

    // Check if there is a parent of the plugin that is also selected for deletion
    private boolean isParentAlsoDeleted(PluginTreeNodeData data) {

        for (PluginTreeNodeData rootData : pluginsToDelete) {

            if (rootData.model == data.parent.model) {
                return true;
            }

        }

        if (data.parent.parent != null) {

            return isParentAlsoDeleted(data.parent);
        }

        return false;

    }
}
