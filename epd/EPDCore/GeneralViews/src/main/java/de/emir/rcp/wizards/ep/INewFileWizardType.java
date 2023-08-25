package de.emir.rcp.wizards.ep;

import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public interface INewFileWizardType {

	INewFileWizardType icon(String iconPath);
	/**
	 * Specifies an icon for this New File Wizard.
	 * @param path
	 * @param rmgr ResourceManager used to locate the icon
	 * @return
	 */
	INewFileWizardType icon(String path, ResourceManager rmgr);
	
	/**
	 * Defines the size of the icon (if set)
	 * @param size
	 * @return
	 */
	INewFileWizardType iconSize(int size);
	INewFileWizardType label(String label);
	INewFileWizardType inCategory(INewFileWizardCategory cat);
	INewFileWizardType category(String categoryID);
	
}