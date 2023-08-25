package de.emir.rcp.wizards.types;

import de.emir.rcp.ui.wizard.AbstractWizardPage;
import de.emir.rcp.wizards.NewFileWizard;
import de.emir.rcp.wizards.NewFileWizardModel;

public class AbstractNewFileWizardPage extends AbstractWizardPage {

	private static final long serialVersionUID = -426744387972976828L;
	private NewFileWizardModel model;

	public AbstractNewFileWizardPage(NewFileWizard wizard, NewFileWizardModel model) {
		super(wizard);
		this.model = model;
	}
	
	public NewFileWizardModel getModel() {
		return model;
	}

}
