package de.emir.rcp.views.workspace;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import de.emir.rcp.jobs.IJob;
import de.emir.rcp.keybindings.ep.CommandActionAdapter;
import de.emir.rcp.manager.CommandManager;
import de.emir.rcp.manager.EventManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.views.workspace.cmd.CopyFilesCommand;
import de.emir.rcp.views.workspace.cmd.CopyFilesCommand.FileTransferable;
import de.emir.rcp.views.workspace.cmd.OpenFilesCommand;
import de.emir.rcp.views.workspace.cmd.PasteFilesCommand;
import de.emir.rcp.views.workspace.events.WorkspaceChangedEvent;
import de.emir.rcp.views.workspace.jobs.MoveFilesJob;
import de.emir.rcp.views.workspace.jobs.PasteFilesJob;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;

public class WorkspaceTreePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2177460721680574076L;

	private FileTree tree;

	/**
	 * Create the panel.
	 */
	public WorkspaceTreePanel() {

		setLayout(new BorderLayout());

		TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent tse) {

				TreeSelectionModel selection = tree.getSelectionModel();

				TreePath[] paths = selection.getSelectionPaths();

				List<Object> selectedData = new ArrayList<>();

				for (TreePath treePath : paths) {

					FileTreeNode node = (FileTreeNode) treePath.getLastPathComponent();

					if (node == null) {
						continue;
					}

					Object uo = node.getUserObject();

					if (uo != null) {
						selectedData.add(uo);
					}

				}

				PlatformUtil.getSelectionManager().setSelection(selectedData);

			}
		};

		tree = new FileTree();

		tree.addTreeSelectionListener(treeSelectionListener);

		tree.expandRow(0);

		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				TreeSelectionModel selection = tree.getSelectionModel();

				if (SwingUtilities.isRightMouseButton(e)) {
					TreePath path = tree.getPathForLocation(e.getX(), e.getY());

					if (path == null) {
						return;
					}

					boolean selectionContainsRightClickedElement = false;

					TreePath[] paths = selection.getSelectionPaths();

					for (TreePath treePath : paths) {
						if (treePath == path) {
							selectionContainsRightClickedElement = true;
						}
					}

					if (selectionContainsRightClickedElement == false) {
						tree.setSelectionPath(path);
					}

				}

				TreePath[] paths = selection.getSelectionPaths();

				List<Object> selectedData = new ArrayList<>();

				boolean containsNonFile = false;

				for (TreePath treePath : paths) {

					FileTreeNode node = (FileTreeNode) treePath.getLastPathComponent();

					if (node == null) {
						continue;
					}

					Object uo = node.getUserObject();

					if (uo != null) {
						selectedData.add(uo);
					}

					if (uo instanceof File == false || ((File) uo).isFile() == false) {
						containsNonFile = true;
					}

				}

				PlatformUtil.getSelectionManager().setSelection(selectedData);

				if (e.getClickCount() == 2 && SwingUtilities.isRightMouseButton(e) == false) {

					if (containsNonFile == true) {
						return;
					}

					// Create and execute an open file command
					// it uses the current selection of the selection manager
					OpenFilesCommand cmd = new OpenFilesCommand();
					CommandManager cm =  ServiceManager.get(CommandManager.class);
					cm.executeCommand(cmd);

				}

			}
		});

		JScrollPane sc = new JScrollPane(tree);
		sc.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		// Override basic actions of tree
		tree.getActionMap().put("copy", new CommandActionAdapter(new CopyFilesCommand()));
		tree.getActionMap().put("paste", new CommandActionAdapter(new PasteFilesCommand()));
		tree.setTransferHandler(new FSTransfer());
		tree.setDragEnabled(true);

		add(sc, BorderLayout.CENTER);

	}

	public void setPopupMenu(JPopupMenu menu) {
		tree.setComponentPopupMenu(menu);
	}

	class FSTransfer extends TransferHandler {

		/**
		* 
		*/
		private static final long serialVersionUID = 4657807365722049329L;

		private boolean isInternalDnD = false;

		public boolean importData(TransferSupport support) {

			Component comp = support.getComponent();
			Transferable t = support.getTransferable();

			if (!(comp instanceof JTree)) {
				return false;
			}
			if (!t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
				return false;
			}

			try {
				@SuppressWarnings("unchecked")
				List<File> data = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);

				Point me = tree.getMousePosition();
				TreePath tp = tree.getPathForLocation(me.x, me.y);

				if (tp == null) {
					return false;
				}

				Object last = tp.getLastPathComponent();

				if (last instanceof FileTreeNode == false) {
					return false;
				}

				Object nodeObject = ((FileTreeNode) last).getUserObject();
				if (nodeObject instanceof File == false) {
					return false;
				}

				File targetFolder = (File) nodeObject;

				if (targetFolder.isDirectory() == false) {

					targetFolder = targetFolder.getParentFile();
					if (targetFolder == null) {
						return false;
					}
				}

				IJob job;

				if (isInternalDnD == true) {
					job = new MoveFilesJob(data, targetFolder);
				} else {
					job = new PasteFilesJob(data, targetFolder);
				}

				PlatformUtil.getJobManager().schedule(job, j -> EventManager.UI.post(new WorkspaceChangedEvent()));

				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
			if (comp instanceof JTree) {
				for (int i = 0; i < transferFlavors.length; i++) {
					if (!transferFlavors[i].equals(DataFlavor.javaFileListFlavor)) {
						return false;
					}
				}
				return true;
			}
			return false;
		}

		@Override
		protected Transferable createTransferable(JComponent c) {

			List<?> selection = PlatformUtil.getSelectionManager().getSelectedObjectAsList();
			List<File> listOfFiles = new ArrayList<>();

			for (Object object : selection) {
				if (object instanceof File) {
					listOfFiles.add((File) object);
				}
			}

            return new FileTransferable(listOfFiles);
		}

		public int getSourceActions(JComponent c) {
			return MOVE;
		}

		@Override
		public void exportAsDrag(JComponent comp, InputEvent e, int action) {
			isInternalDnD = true;
			super.exportAsDrag(comp, e, action);
		}

		@Override
		protected void exportDone(JComponent source, Transferable data, int action) {
			isInternalDnD = false;
			super.exportDone(source, data, action);
		}

	}

	public void setSelection(File file) {
		tree.setSelection(file);
	}

	public void setRootLocation(File location, IWorkspaceRefreshCallback cb) throws IOException {
		tree.setRootLocation(location, cb);

	}

	public void setExpansionState(String expansionState) {
		tree.setExpansionState(expansionState);

	}

	public String getExpansionState() {
		return tree.getExpansionState();
	}

	public void setSelection(List<File> files) {
		tree.setSelection(files);
	}

	public boolean isUpdating() {
		return tree.isUpdating();
	}

}
