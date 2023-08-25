package de.emir.rcp.views.workspace.cmd;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.EventManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.views.workspace.WorkspaceView;
import de.emir.rcp.views.workspace.events.WorkspaceChangedEvent;
import de.emir.rcp.views.workspace.jobs.DeleteFilesJob;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class DeleteFilesCommand extends AbstractCommand {

	private IProperty<String> workspaceProperty;

	public DeleteFilesCommand() {

		PropertyContext propCont = PropertyStore.getContext(WorkspaceView.PROPERTY_CONTEXT);
		workspaceProperty = propCont.getProperty(WorkspaceView.WORKSPACE_LOCATION_PROPERTY,
				WorkspaceView.WORKSPACE_LOCATION_PROPERTY_DEFAULT);

		PlatformUtil.getSelectionManager().subscribe(t -> {
			List<?> selection = PlatformUtil.getSelectionManager().getSelectedObjectAsList();
			// TODO: It is possible to delete the workspace folder
			setCanExecute(containsWorkspaceFolder(selection) == false && containsFile(selection) == true);
		});

	}

	@Override
	public void execute() {
		// TODO: It is possible to delete the workspace folder
		List<?> selection = PlatformUtil.getSelectionManager().getSelectedObjectAsList();

		if (containsWorkspaceFolder(selection) == true) {
			return;
		}

		MainWindow mw = PlatformUtil.getWindowManager().getMainWindow();

		String message = "Are you shure you want to delete these " + selection.size() + " elements?";

		if (selection.size() == 1) {

			if (selection.get(0) instanceof File == false) {
				return;
			}

			message = "Are you shure you want to delete file '" + ((File) selection.get(0)).getName() + "'?";

		}

		int result = JOptionPane.showConfirmDialog(mw, message, "Delete File(s)?", JOptionPane.YES_NO_OPTION);

		if (result != JOptionPane.YES_OPTION) {
			return;

		}

		List<File> listOfFiles = new ArrayList<>();

		for (Object object : selection) {
			if (object instanceof File) {
				listOfFiles.add((File) object);
			}
		}

		PlatformUtil.getJobManager().schedule(new DeleteFilesJob(listOfFiles),
				job -> EventManager.UI.post(new WorkspaceChangedEvent()));

	}

	private boolean containsFile(List<?> selection) {

		for (Object object : selection) {

			if (object instanceof File) {

				return true;

			}

		}

		return false;

	}

	private boolean containsWorkspaceFolder(List<?> selection) {

		File workspaceFolder = new File((String) workspaceProperty.getValue()).getAbsoluteFile();
		
		for (Object object : selection) {
			
			
			
			if (object instanceof File) {
				File f = (File) object;

				f = f.getAbsoluteFile();
				
				if (f.equals(workspaceFolder)) {
					
					return true;
				}
			}
		}

		return false;
	}

}
