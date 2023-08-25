package de.emir.rcp.wizards.types.ucore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.emir.rcp.wizards.AbstractNewFileWizardTypeInformation;
import de.emir.rcp.wizards.INewFileWizardFinishHandler;
import de.emir.rcp.wizards.NewFileWizard;
import de.emir.rcp.wizards.NewFileWizardModel;
import de.emir.rcp.wizards.types.AbstractNewFileWizardPage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;
import de.emir.tuml.ucore.runtime.serialization.AbstractSerializer;
import de.emir.tuml.ucore.runtime.serialization.bin.BinarySerializer;
import de.emir.tuml.ucore.runtime.serialization.bin.BinaryShortSerializer;
import de.emir.tuml.ucore.runtime.serialization.xml.XMLSerializer;

public class NewFileWizardUCoreModelInformation extends AbstractNewFileWizardTypeInformation {

	public NewFileWizardUCoreModelInformation(NewFileWizard wizard) {
		super(wizard);

	}

	@Override
	public List<AbstractNewFileWizardPage> getPages() {
		
		List<AbstractNewFileWizardPage> result = new ArrayList<>();
		
		result.add(new ChooseModelPage(wizard, wizard.getModel()));
		
		return result;
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
				
				monitor.setMessage("Creating UCore Model '" + fileName + "'");
				
				Object o = model.getAdditionalInformations();
				
				
				if(o instanceof UClass == false) {
					return null;
				}
				
				UClass uClass = (UClass) o;
				
				UObject oInstance = uClass.createNewInstance();
				
				monitor.setProgress(50);

				AbstractSerializer ser = AbstractSerializer.createSerializerForFile(new File(absName));
				try {

					File targetFile = new File(absName);
					
					ser.writeFile(oInstance, targetFile);

					ArrayList<File> result = new ArrayList<>();
					result.add(targetFile);
					
					monitor.setProgress(100);
					
					return result;
					
					
				} catch (IOException e) {
					e.printStackTrace();
					monitor.setProgress(0);
					return null;
				}

			}
		};
		
	}

}
