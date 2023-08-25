package de.emir.rcp.views.workspace.cmd;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;

public class CopyFilesCommand extends AbstractCommand {

	public CopyFilesCommand() {

		PlatformUtil.getSelectionManager().subscribe(t -> {
			List<?> selection = PlatformUtil.getSelectionManager().getSelectedObjectAsList();
			setVisible(containsFile(selection) == true);
		});

	}

	@Override
	public void execute() {

		List<?> selection = PlatformUtil.getSelectionManager().getSelectedObjectAsList();
		List<File> listOfFiles = new ArrayList<>();

		for (Object object : selection) {
			if (object instanceof File) {
				listOfFiles.add((File) object);
			}
		}

		if (listOfFiles.size() == 0) {
			return;
		}

		FileTransferable ft = new FileTransferable(listOfFiles);

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ft, null);

	}

	private boolean containsFile(List<?> selection) {

		for (Object object : selection) {

			if (object instanceof File) {
				return true;

			}

		}

		return false;

	}

	public static class FileTransferable implements Transferable {

		private List<?> listOfFiles;

		public FileTransferable(List<?> listOfFiles) {
			this.listOfFiles = listOfFiles;
		}

		@Override
		public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[] { DataFlavor.javaFileListFlavor };
		}

		@Override
		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return DataFlavor.javaFileListFlavor.equals(flavor);
		}

		@Override
		public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
			return listOfFiles;
		}
	}

}
