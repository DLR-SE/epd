package de.emir.rcp.wizards;

import java.util.List;

import de.emir.rcp.wizards.types.AbstractNewFileWizardPage;

public abstract class AbstractNewFileWizardTypeInformation {
	
	protected NewFileWizard wizard;

	public AbstractNewFileWizardTypeInformation(NewFileWizard wizard) {
		this.wizard = wizard;
	}

	public abstract List<AbstractNewFileWizardPage> getPages();
	
	/**
	 * Has to return the file extension for this type of files
	 * Example:
	 * If your type is a text file, implement: 
	 * 	return "txt";
	 * To allow every kind of file extension:
	 * 	return null;
	 * @return
	 */
	public abstract String getFileExtension();
	
	/**
	 * Has to return a finish handler, called when the user presses the finish button
	 * 
	 * @return
	 */
	public abstract INewFileWizardFinishHandler getFinishHandler();

}
