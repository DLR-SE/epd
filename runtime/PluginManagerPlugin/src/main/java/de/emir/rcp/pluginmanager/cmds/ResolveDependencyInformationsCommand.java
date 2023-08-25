package de.emir.rcp.pluginmanager.cmds;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.pluginmanager.jobs.ResolveDependencyInformationsJob;
import de.emir.rcp.pluginmanager.manager.PmManager;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;

public class ResolveDependencyInformationsCommand extends AbstractCommand {

    public ResolveDependencyInformationsCommand() {
        PmManager pm = ServiceManager.get(PmManager.class);
        if (pm == null || pm.getModelProvider() == null)
        	setCanExecute(false);
        else {
	        pm.getModelProvider().subscribeProductFile(opt -> setCanExecute(opt.isPresent()));
	
	        setCanExecute(pm.getModelProvider().getProductFile() != null);
        }
    }

    @Override
    public void execute() {

        PlatformUtil.getJobManager().schedule(new ResolveDependencyInformationsJob());

    }

}
