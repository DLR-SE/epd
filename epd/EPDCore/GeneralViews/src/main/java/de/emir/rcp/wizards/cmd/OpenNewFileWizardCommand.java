package de.emir.rcp.wizards.cmd;

import javax.swing.JFrame;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.ui.wizard.AbstractWizard;
import de.emir.rcp.wizards.NewFileWizard;

public class OpenNewFileWizardCommand extends AbstractCommand {

	private String fileTypeID;

	public OpenNewFileWizardCommand() {
	}
	
	public OpenNewFileWizardCommand(String fileTypeID) { 
		this.fileTypeID = fileTypeID;
	}
	
	@Override
	public void execute() {
		JFrame mw = PlatformUtil.getWindowManager().getMainWindow();

		AbstractWizard sw = new NewFileWizard(mw, fileTypeID);

		sw.initialize();
	}

}
