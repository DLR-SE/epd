package de.emir.rcp.pluginmanager.cmds;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.pluginmanager.manager.PmManager;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;

public class ChangeProductDefinitionCommand extends AbstractCommand {

    public ChangeProductDefinitionCommand() {

        PmManager pmm = ServiceManager.get(PmManager.class);
        if (pmm == null || pmm.getModelProvider() == null)
        	setCanExecute(false);
        else
        	setCanExecute(pmm.getModelProvider().isLocked() == false);

    }

    @Override
    public void execute() {

        PmManager pmm = ServiceManager.get(PmManager.class);
        pmm.showProductFileChangeDialog();

    }

}
