package de.emir.rcp.wizards.ep;

import java.util.HashMap;
import java.util.Map;

import de.emir.rcp.wizards.AbstractNewFileWizardTypeInformation;
import de.emir.tuml.ucore.runtime.extension.IExtensionPoint;

public class NewFileWizardExtensionPoint implements IExtensionPoint {

	private Map<String, NewFileWizardCategory> categories = new HashMap<>();
	private Map<String, NewFileWizardType> types = new HashMap<>();

	public INewFileWizardCategory category(String id) {

		NewFileWizardCategory cat = categories.get(id);

		if (cat == null) {
			cat = new NewFileWizardCategory(id);
			categories.put(id, cat);
		}

		return cat;

	}

	public INewFileWizardType type(String id, Class<? extends AbstractNewFileWizardTypeInformation> typeClass) {

		if (types.containsKey(id)) {
			return types.get(id);
		}
		

		NewFileWizardType type = new NewFileWizardType();

		type.setId(id);
		type.setTypeClass(typeClass);
		types.put(id, type);

		return type;

	}

	public Map<String, NewFileWizardCategory> getCategories() {
		return categories;
	}
	
	public Map<String, NewFileWizardType> getTypes() {
		return types;
	}
	
}
