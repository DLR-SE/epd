package de.emir.rcp.manager;

import de.emir.rcp.wizards.ep.NewFileWizardExtensionPoint;

public class NewFileWizardManager {

	private static NewFileWizardExtensionPoint ep = new NewFileWizardExtensionPoint();

	public static NewFileWizardExtensionPoint getNewFileWizardExtensionPoint() {
		return ep;
	}
}
