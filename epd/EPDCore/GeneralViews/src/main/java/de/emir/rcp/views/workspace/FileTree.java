package de.emir.rcp.views.workspace;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class FileTree extends JTree {

	private static final long serialVersionUID = -5791655600699891405L;

	private boolean updating = false;

	private DefaultTreeModel baseTreeModel;

	private FileTreeNode mRootNode;

	private boolean showOnlyDirectories;

	public FileTree() {
		this(-1);

	}

	/**
	 * Use JFileChooser.DIRECTORIES_ONLY to filter files
	 * 
	 * @param filterOptions
	 */
	public FileTree(int filterOptions) {
		super();

		setRootVisible(false);
		setShowsRootHandles(true);
		setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		setUpdating(true);
		
		if(filterOptions == JFileChooser.DIRECTORIES_ONLY) {
			showOnlyDirectories = true;
		}
		
		// the File tree
		FileTreeNode root = new FileTreeNode();
		baseTreeModel = new DefaultTreeModel(root);

		setModel(baseTreeModel);

		setCellRenderer(new FileTreeCellRenderer());
		
		// as per trashgod tip
		setVisibleRowCount(15);

	}

	public void paintComponent(Graphics g) {

		int offset = 0;

		super.paintComponent(g);

		if (isUpdating() == true) {
			g.setColor(new Color(50, 50, 50, 100));
			g.fillRect(0, 0, getWidth() - 1 - offset, getHeight() - 1);
		}
	}

	public void setUpdating(boolean updating) {

		boolean old = this.updating;

		this.updating = updating;

		if (this.updating != old) {
			repaint();
		}
	}

	public boolean isUpdating() {
		return updating;
	}

	public void setRootLocation(File location, IWorkspaceRefreshCallback cb) throws IOException {
		if (location == null)
			throw new NullPointerException("Need a non null location as root location");
		if (location.isDirectory() == false)
			throw new IOException("Root Location needs to be a directory");

		Thread updater = new Thread(new Runnable() {

			@Override
			public void run() {

				setUpdating(true);

				String expState = null;

				if (mRootNode != null) {

					expState = getExpansionState();

					baseTreeModel.setRoot(null);
					mRootNode = null;
					baseTreeModel.reload();
				}
				{
					mRootNode = new FileTreeNode(location, showOnlyDirectories);
					baseTreeModel.setRoot(mRootNode);
				}
				mRootNode.setUserObject(location);

				baseTreeModel.nodeChanged(mRootNode);
				setRootVisible(true);

				expandPath(new TreePath(mRootNode.getPath()));

				if (expState != null) {
					setExpansionState(expState);
				}

				setUpdating(false);

				if (cb != null) {
					cb.finished();
				}

			}
		});

		updater.start();
	}

	public String getExpansionState() {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < getRowCount(); i++) {

			if (isExpanded(i)) {
				TreePath path = getPathForRow(i);
				sb.append(path.getLastPathComponent()).append("<");

			}

		}

		return sb.toString();

	}

	/**
	 * 
	 * Sets the expansion state based upon a < delimited list of row indexes
	 * that
	 * 
	 * are expanded.
	 * 
	 * @param s
	 * 
	 */

	public void setExpansionState(String s) {

		String[] indexes = s.split("<");

		for (String st : indexes) {

			TreePath path = getPathForString(st);

			if (path == null) {
				continue;
			}

			expandPath(path);
		}

	}

	public TreePath getPathForString(String s) {

		LinkedList<String> path = new LinkedList<>();

		String[] parts = s.split("\\\\");

		for (String part : parts) {
			path.add(part);
		}

		TreeModel model = getModel();
		FileTreeNode root = (FileTreeNode) model.getRoot();

		boolean rootFound = false;
		
		if(root == null) {
			return null;
		}
		
		File rootFile = root.getFile();
		while (rootFound == false) {

			if (path.isEmpty()) {
				break;
			}

			String pathPart = path.removeFirst();

			rootFound = rootFile.getName().equals(pathPart);
		}

		if (rootFound == false) {
			return null;
		}

		FileTreeNode node = root.getNode(path);

		if (node == null) {
			return null;
		}

		return new TreePath(node.getPath());

	}

	public void setSelection(File f) {

		if (f == null) {
			return;
		}

		TreePath treePath = getPathForString(f.getAbsolutePath());
		if (treePath != null) {
			scrollPathToVisible(treePath);
			setSelectionPath(treePath);
		}

	}
	
	public void setSelection(List<File> files) {

		if (files == null) {
			return;
		}

		
		List<TreePath> paths = new ArrayList<>();

		for (File file : files) {
			TreePath treePath = getPathForString(file.getAbsolutePath());

			if(treePath == null) {
				continue;
			}
			
			paths.add(treePath);
		}
		
		if(paths.size() == 0) {
			return;
		}
		
		setSelectionPaths(paths.toArray(new TreePath[0]));
		scrollPathToVisible(paths.get(0));
		
	}

}
