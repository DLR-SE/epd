package de.emir.rcp.commands.basics;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.ViewManager;
import de.emir.rcp.manager.util.PlatformUtil;

public class ShowViewCommand extends AbstractCommand {

    private String viewID;

    public ShowViewCommand(String viewID) {
        this.viewID = viewID;
    }

    @Override
    public void execute() {
        ViewManager vm = PlatformUtil.getViewManager();

        vm.openView(viewID);
    }
}
