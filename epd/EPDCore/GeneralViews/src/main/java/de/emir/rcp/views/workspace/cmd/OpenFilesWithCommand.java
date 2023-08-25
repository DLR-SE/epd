package de.emir.rcp.views.workspace.cmd;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.editors.dialogs.OpenWithEditorDialog;
import de.emir.rcp.manager.EditorManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.runtime.plugin.windows.MainWindow;

public class OpenFilesWithCommand extends AbstractCommand {

	public OpenFilesWithCommand() {
		
		PlatformUtil.getSelectionManager().subscribe(t -> {
			List<?> selection = PlatformUtil.getSelectionManager().getSelectedObjectAsList();
			setVisible(containsFile(selection) == true);
			setCanExecute(PlatformUtil.getWindowManager().getMainWindow().hasEditorArea());
		});
		
	}
	
	@Override
	public void execute() {
		
		List<?> selection = PlatformUtil.getSelectionManager().getSelectedObjectAsList();

		MainWindow mw = PlatformUtil.getWindowManager().getMainWindow();
		
		if(mw.hasEditorArea() == false) {
			return;
		}
		
		for (Object o : selection) {

			if(o instanceof File == false) {
				continue;
			}
			
			if(((File)o).isFile() == false) {
				continue;
			}

			OpenWithEditorDialog dialog = new OpenWithEditorDialog(mw, null, ((File) o).getName());
			dialog.setVisible(true);
			
			String editorID = dialog.getSelectedEditorID();
			
			if(editorID == null) {
				continue;
			}
			
			boolean alwaysUseSelectedEditor = dialog.isAlwaysUseThisSet();
			
			EditorManager em = PlatformUtil.getEditorManager();
			
			if(alwaysUseSelectedEditor == true) {
				
				String ext = FilenameUtils.getExtension(((File) o).getName());
				
				em.setTypeForFile(editorID, ext);
				em.openFile((File) o);
				
				
			} else {
				
				em.openFile((File) o, editorID);
				
			}
			
		}

	}
	
	private boolean containsFile(List<?> selection) {
		
		for (Object object : selection) {
			
			if(object instanceof File) {
				
				if(((File) object).isFile() == true) {
					return true;
				}
				
			}
			
		}
		
		return false;
		
	}

}
