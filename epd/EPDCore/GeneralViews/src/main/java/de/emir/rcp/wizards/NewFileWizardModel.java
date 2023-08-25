package de.emir.rcp.wizards;

import de.emir.rcp.wizards.ep.NewFileWizardType;

public class NewFileWizardModel {
	
	private NewFileWizardType selectedType;
	private AbstractNewFileWizardTypeInformation selectedTypeInstance;
	
	private String fileName;
	private String folder;
	
	private Object additionalInformations;
	
	
	public AbstractNewFileWizardTypeInformation getSelectedTypeInstance() {
		return selectedTypeInstance;
	}
	protected void setSelectedTypeInstance(AbstractNewFileWizardTypeInformation selectedType) {
		this.selectedTypeInstance = selectedType;
	}
	
	protected void setSelectedType(NewFileWizardType selectedType) {
		this.selectedType = selectedType;
	}
	
	protected NewFileWizardType getSelectedType() {
		return selectedType;
	}
	
	public String getFileName() {
		return fileName;
	}
	protected void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFolder() {
		return folder;
	}
	protected void setFolder(String folder) {
		this.folder = folder;
	}
	
	public void setAdditionalInformations(Object additionalInformations) {
		this.additionalInformations = additionalInformations;
	}
	
	public Object getAdditionalInformations() {
		return additionalInformations;
	}
}
