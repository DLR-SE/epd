package de.emir.rcp.pluginmanager.model.transactions;

import java.util.HashMap;
import java.util.Map;

import de.emir.rcp.model.AbstractModelTransaction;
import de.emir.tuml.runtime.epf.ObservableDependency;
import de.emir.tuml.runtime.epf.ProductFile;

public class UpdateVersionsTransaction extends AbstractModelTransaction {
    private ProductFile pf;
    private Map<ObservableDependency, VersionUpdate> dependencies;

    private boolean executed = false;

    public UpdateVersionsTransaction(ProductFile pf, String group, String newVersion) {
        this.pf = pf;
        this.dependencies = new HashMap<>();
        boolean wildcard = group.endsWith("*");
        for (ObservableDependency dep : pf.getDependencies()) {
        	if (wildcard && dep.getGroupId().startsWith(group.substring(0, group.indexOf("*")))) {
        		this.dependencies.put(dep, new VersionUpdate(dep.getVersion(), newVersion));
        	} else if (dep.getGroupId().equals(group)) {
        		this.dependencies.put(dep, new VersionUpdate(dep.getVersion(), newVersion));
        	}
        }
    }

    @Override
    public void run() {
    	for (Map.Entry<ObservableDependency, VersionUpdate> update : dependencies.entrySet()) {
        	update.getKey().setVersion(update.getValue().newVersion());
        }

        executed = true;
    }

    @Override
    public void undo() {
    	for (Map.Entry<ObservableDependency, VersionUpdate> update : dependencies.entrySet()) {
        	update.getKey().setVersion(update.getValue().oldVersion());
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