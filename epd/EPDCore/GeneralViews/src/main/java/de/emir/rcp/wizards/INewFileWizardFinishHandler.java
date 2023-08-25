package de.emir.rcp.wizards;

import java.io.File;
import java.util.List;

import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;

public interface INewFileWizardFinishHandler {

	/**
	 * This method is executed if the finish button of a NewFileWizard is
	 * pressed. Implement your file creation logic within. To perform long
	 * lasting operations this method is executed in a non ui thread
	 * 
	 * @param monitor
	 *            The progress monitor to visualize the creation process
	 * @param model
	 *            Holds information about the user choices
	 * @return The created file or null/an empty list if an error occured. If creating
	 *         multiple files/folders return the files u want to be selected
	 *         within the workspace view
	 */
	public List<File> runFinish(IProgressMonitor monitor, NewFileWizardModel model);

}
