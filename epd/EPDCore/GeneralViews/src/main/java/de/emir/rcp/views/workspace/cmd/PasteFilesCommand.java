package de.emir.rcp.views.workspace.cmd;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.EventManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.views.workspace.events.WorkspaceChangedEvent;
import de.emir.rcp.views.workspace.jobs.PasteFilesJob;

public class PasteFilesCommand extends AbstractCommand {

	public PasteFilesCommand() {

		PlatformUtil.getSelectionManager().subscribe(t -> setCanExecute(isValidState()));

	}

	@Override
	public void execute() {

		List<?> selection = PlatformUtil.getSelectionManager().getSelectedObjectAsList();

		// Get first folder
		File targetFolder = null;

		for (Object o : selection) {
			if (o instanceof File) {

				if (((File) o).isDirectory()) {
					targetFolder = (File) o;
					break;
				} else if (((File) o).isFile()) {

					String parent = ((File) o).getParent();
					if (parent == null) {
						continue;
					}

					File parentFile = new File(parent);
					if (parentFile.exists() == false) {
						continue;
					}

					targetFolder = parentFile;

				}

			}
		}

		if (targetFolder == null) {
			return;
		}

		// get the system clipboard
		Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

		// get the contents on the clipboard in a
		// transferable object
		Transferable clipboardContents = systemClipboard.getContents(null);

		// check if clipboard is empty
		if (clipboardContents.equals(null)) {

			return;
		}

		if (clipboardContents.isDataFlavorSupported(DataFlavor.javaFileListFlavor) == false) {
			return;
		}

		Object data = null;

		try {
			data = clipboardContents.getTransferData(DataFlavor.javaFileListFlavor);

		} catch (UnsupportedFlavorException | IOException e) {

			e.printStackTrace();
		}

		if (data == null) {
			return;
		}

		@SuppressWarnings("unchecked")
		List<File> fileList = (List<File>) data;

		PlatformUtil.getJobManager().schedule(new PasteFilesJob(fileList, targetFolder),
				job -> EventManager.UI.post(new WorkspaceChangedEvent()));

	}

	private boolean isValidState() {

		List<?> selection = PlatformUtil.getSelectionManager().getSelectedObjectAsList();

		// Get first folder
		File targetFolder = null;

		for (Object o : selection) {
			if (o instanceof File) {

				if (((File) o).isDirectory()) {
					targetFolder = (File) o;
					break;
				} else if (((File) o).isFile()) {

					String parent = ((File) o).getParent();
					if (parent == null) {
						continue;
					}

					File parentFile = new File(parent);
					if (parentFile.exists() == false) {
						continue;
					}

					targetFolder = parentFile;

				}

			}
		}

		if (targetFolder == null) {
			return false;
		}

		// get the system clipboard
		Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

		// get the contents on the clipboard in a
		// transferable object
		Transferable clipboardContents = systemClipboard.getContents(null);

		// check if clipboard is empty
		if (clipboardContents.equals(null)) {

			return false;
		}

		if (clipboardContents.isDataFlavorSupported(DataFlavor.javaFileListFlavor) == false) {
			return false;
		}

		Object data = null;

		try {
			data = clipboardContents.getTransferData(DataFlavor.javaFileListFlavor);

		} catch (UnsupportedFlavorException | IOException e) {

			e.printStackTrace();
		}

		if (data == null) {
			return false;
		}

		return true;

	}

}
