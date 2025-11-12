package de.emir.rcp.views.console.cmd;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.views.console.ConsoleView;

public class CopyConsoleCommand extends AbstractCommand {

	@Override
	public void execute() {
		
		ConsoleView view = PlatformUtil.getViewManager().getView(ConsoleView.class);

		if(view != null) {
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(view.getLog()), null);
		}
		
	}

}
