package de.emir.rcp.pluginmanager.cmds;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.ids.Basic;
import de.emir.rcp.manager.util.PlatformUtil;

public class SaveAndExitCommand extends AbstractCommand {

    @Override
    public void execute() {

        PlatformUtil.getCommandManager().executeCommand(Basic.CMD_SAVE);
        PlatformUtil.getCommandManager().executeCommand(Basic.CMD_EXIT);

    }

}
