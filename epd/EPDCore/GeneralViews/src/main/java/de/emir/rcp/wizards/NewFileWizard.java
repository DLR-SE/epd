package de.emir.rcp.wizards;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.slf4j.Logger;

import de.emir.rcp.events.RequestFileSelectionEvent;
import de.emir.rcp.ids.GVBasic;
import de.emir.rcp.manager.CommandManager;
import de.emir.rcp.manager.EventManager;
import de.emir.rcp.manager.NewFileWizardManager;
import de.emir.rcp.ui.utils.JProgressBarProgressMonitor;
import de.emir.rcp.ui.wizard.AbstractWizard;
import de.emir.rcp.ui.wizard.AbstractWizardPage;
import de.emir.rcp.wizards.ep.NewFileWizardType;
import de.emir.rcp.wizards.types.AbstractNewFileWizardPage;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class NewFileWizard extends AbstractWizard {

	private Logger log = ULog.getLogger(NewFileWizard.class);
	
	private NewFileWizardModel model;

	private List<AbstractWizardPage> pages;


	public NewFileWizard(JFrame parent) {
		this(parent, null);
	}

	public NewFileWizard(JFrame parent, String fileTypeID) {
		
		super(parent, "New...");

		model = new NewFileWizardModel();
		
		pages = new ArrayList<>();
		
		if(fileTypeID == null) {
			pages.add(new NewFileWizardTypeSelectionPage(this, model));
			pages.add(new NewFileWizardNamePage(this, model));
		} else {
			
			pages.add(new NewFileWizardNamePage(this, model));
			
			NewFileWizardType type = NewFileWizardManager.getNewFileWizardExtensionPoint().getTypes().get(fileTypeID);
			
			Class<? extends AbstractNewFileWizardTypeInformation> typeClass = type.getTypeClass();
			try {
				
				model.setSelectedTypeInstance(typeClass.getConstructor(NewFileWizard.class).newInstance((NewFileWizard)this));
				model.setSelectedType(type);
				
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) 
			{
				e.printStackTrace();
				return;
			}

			List<AbstractNewFileWizardPage> pages = model.getSelectedTypeInstance().getPages();
			
			if(pages != null) {
				this.pages.addAll(pages);
			}
			
			

		}
		
		
	}
	
	public NewFileWizardModel getModel() {
		return model;
	}

	@Override
	public List<AbstractWizardPage> getInitialPages() {
		return pages;
	}
	
	@Override
	public void onFinish() {
		AbstractNewFileWizardTypeInformation type = model.getSelectedTypeInstance();
		
		INewFileWizardFinishHandler handler = type.getFinishHandler();
		
		if(handler == null) {
			log.error("No finish handler for type " + type);
			return ;
		}
		
		
		
		JProgressBarProgressMonitor pm = getContainer().getProgressMonitor();
		
		pm.setVisible(true);
		pm.setMessage("");
		pm.setProgress(0);
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				List<File> result = handler.runFinish(pm, model);
				
				if(result !=  null && result.size() > 0) {

					CommandManager cm =  ServiceManager.get(CommandManager.class);
					cm.executeCommand(GVBasic.CMD_WORKSPACE_REFRESH);
					EventManager.UI.post(new RequestFileSelectionEvent(result));

					close();
				} else {
					
					pm.setVisible(false);
					
				}
				
			}
		});
		
		t.start();
			
	}

}
