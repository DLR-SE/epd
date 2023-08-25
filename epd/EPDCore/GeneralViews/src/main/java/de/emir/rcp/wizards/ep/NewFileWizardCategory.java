package de.emir.rcp.wizards.ep;

public class NewFileWizardCategory implements INewFileWizardCategory {

	private String label;
	private String id;

	public NewFileWizardCategory(String id) {
		this.id = id;
	}
	
	@Override
	public INewFileWizardCategory label(String label) {
		this.label = label;
		return this;
	}
	
	public String getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}

}
