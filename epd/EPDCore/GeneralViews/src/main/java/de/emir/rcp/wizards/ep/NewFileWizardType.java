package de.emir.rcp.wizards.ep;

import java.net.URL;

import javax.swing.ImageIcon;

import de.emir.rcp.wizards.AbstractNewFileWizardTypeInformation;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public class NewFileWizardType implements INewFileWizardType {
	private String 													label;
	private String 													mIconPath;
	private int														mIconSize;
	private ResourceManager											mResourceManager;
	
	private Class<? extends AbstractNewFileWizardTypeInformation> 	typeClass;
	private String 													id;
	private String 													categoryId;
	
	public void setTypeClass(Class<? extends AbstractNewFileWizardTypeInformation> typeClass) {
		this.typeClass = typeClass;
	}
	
	public Class<? extends AbstractNewFileWizardTypeInformation> getTypeClass() {
		return typeClass;
	}

	@Override
	public INewFileWizardType icon(String path) {
		return icon(path, getTypeClass() != null ? ResourceManager.get(getTypeClass()) : ResourceManager.get(getClass()));
	}
	@Override
	public INewFileWizardType icon(String path, ResourceManager rmgr) {
		mResourceManager = rmgr;
		this.mIconPath = path;
		return this;
	}

	@Override
	public INewFileWizardType iconSize(int size) {
		mIconSize = size;
		return this;
	}
	
	
	public String getLabel() {
		return label;
	}
	
	public ImageIcon getIcon() {
		if (mIconPath != null && !mIconPath.isEmpty()) {
			URL iconURL = mResourceManager != null ? mResourceManager.getResource(mIconPath) : ResourceManager.get(getClass()).getResource(mIconPath);
			if (iconURL != null) {
				int iconSize = mIconSize < 0 ? IconManager.preferedSmallIconSize() : mIconSize;
				return IconManager.getIcon(iconURL, iconSize);
			}				
		}
		return null;
	}

	public void setId(String id) {
		this.id = id;
		
	}
	
	public String getId() {
		return id;
	}

	@Override
	public INewFileWizardType label(String label) {
		this.label = label;
		return this;
	}

	@Override
	public INewFileWizardType inCategory(INewFileWizardCategory cat) {
		return category(((NewFileWizardCategory)cat).getId());
	}

	@Override
	public INewFileWizardType category(String categoryID) {
		
		this.categoryId = categoryID;
		return this;
	}
	
	public String getCategoryId() {
		return categoryId;
	}	
}
