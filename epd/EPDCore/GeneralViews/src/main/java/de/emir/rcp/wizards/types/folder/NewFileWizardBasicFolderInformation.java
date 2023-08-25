package de.emir.rcp.wizards.types.folder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.emir.rcp.wizards.AbstractNewFileWizardTypeInformation;
import de.emir.rcp.wizards.INewFileWizardFinishHandler;
import de.emir.rcp.wizards.NewFileWizard;
import de.emir.rcp.wizards.NewFileWizardModel;
import de.emir.rcp.wizards.types.AbstractNewFileWizardPage;
import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;

public class NewFileWizardBasicFolderInformation extends AbstractNewFileWizardTypeInformation {

	public NewFileWizardBasicFolderInformation(NewFileWizard wizard) {
		super(wizard);

	}

	@Override
	public List<AbstractNewFileWizardPage> getPages() {
		return null;
	}

	@Override
	public String getFileExtension() {
		return null;
	}

	@Override
	public INewFileWizardFinishHandler getFinishHandler() {
		
		return new INewFileWizardFinishHandler() {
			
			@Override
			public List<File> runFinish(IProgressMonitor monitor, NewFileWizardModel model) {

				String folderName = model.getFolder();
				String fileName = model.getFileName();
				
				String absName = folderName + File.separator + fileName;
				
				monitor.setMessage("Creating Folder '" + fileName + "'");
				
				File newFile = new File(absName);
				
				monitor.setProgress(50);
				
				try {
					newFile.mkdirs();
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
				
				
				monitor.setProgress(100);
				ArrayList<File> result = new ArrayList<>();
				result.add(newFile);
				return result;
			}
		};
		
	}

}
