package de.emir.rcp.pluginmanager.model.transactions;

import java.util.ArrayList;
import java.util.List;

import de.emir.rcp.model.AbstractModelTransaction;
import de.emir.rcp.pluginmanager.views.PluginTreeNodeData;
import de.emir.tuml.runtime.epf.ProductFile;

public class RemovePluginsTransaction extends AbstractModelTransaction {

    private ProductFile pf;
    private List<PluginTreeNodeData> toRemove;

    private boolean executed = false;
    private int[] indexes;

    public RemovePluginsTransaction(ProductFile pf, List<PluginTreeNodeData> toRemove) {
        this.pf = pf;
        this.toRemove = toRemove;
    }

    public RemovePluginsTransaction(ProductFile pf, PluginTreeNodeData toRemove) {
        this.pf = pf;
        this.toRemove = new ArrayList<>();
        this.toRemove.add(toRemove);
    }

    @Override
    public void run() {

        indexes = new int[toRemove.size()];

        int i = 0;
        for (PluginTreeNodeData data : toRemove) {

            indexes[i] = pf.removeWorkspace(data.model.getPomFile());
            i++;

        }

        executed = true;

    }

    @Override
    public void undo() {

        PluginTreeNodeData[] toRemoveArray = toRemove.toArray(new PluginTreeNodeData[0]);

        for (int j = toRemoveArray.length - 1; j >= 0; j--) {

            if (indexes[j] != -1) {
                pf.addWorkspace(toRemoveArray[j].model.getPomFile(), indexes[j]);
            }

        }
        executed = false;

    }

    @Override
    public void redo() {
        run();

    }

    @Override
    public boolean canUndo() {
        return executed == true;
    }

    @Override
    public boolean canRedo() {
        return executed == false;
    }

}
