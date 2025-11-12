package de.emir.rcp.views.console.cmd;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.views.console.LogLevelDialog;

public class SetClassLogLevelCommand extends AbstractCommand {
	/**
	 * {@inheritDoc}
	 * Opens a dialog where the user can select a log level for every class.
	 */
    @Override
    public void execute() {
        LogLevelDialog dialog = new LogLevelDialog(PlatformUtil.getWindowManager().getMainWindow());
        dialog.setSize(400, 500);
        dialog.setVisible(true);
    }

}
