package de.emir.rcp.views.console.cmd;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.views.console.ConsoleView;

public class ClearConsoleCommand extends AbstractCommand {

	@Override
	public void execute() {
		
		ConsoleView view = PlatformUtil.getViewManager().getView(ConsoleView.class);

		if(view != null) {
			view.clearConsole();
		}
		
	}

}
