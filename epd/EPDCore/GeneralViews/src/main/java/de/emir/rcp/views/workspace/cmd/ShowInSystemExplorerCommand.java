package de.emir.rcp.views.workspace.cmd;

import java.io.File;
import java.io.IOException;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;

public class ShowInSystemExplorerCommand extends AbstractCommand {

	public ShowInSystemExplorerCommand() {
		
		
		String os = System.getProperty("os.name");
		setCanExecute(os.toLowerCase().contains("win"));
	}
	
	@Override
	public void execute() {
		
		Object selection = PlatformUtil.getSelectionManager().getFirstSelectedObject();
		
		if(selection instanceof File) {
			try {
				Runtime.getRuntime().exec("explorer.exe /select," + ((File)selection).getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
