package de.emir.rcp.views.workspace;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public class FileTreeNode extends DefaultMutableTreeNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4796770807334764294L;

	
	private Map<String, FileTreeNode> children = new HashMap<String, FileTreeNode>();

	private boolean childrenLoaded = false;

	private boolean onlyDirectories;
	
	public FileTreeNode(File file, boolean onlyDirectories) {
		super(file);
		
		this.onlyDirectories = onlyDirectories;
	}

	public FileTreeNode() {

	}

	public void add(FileTreeNode newChild) {
		
		File file = newChild.getFile();
		
		children.put(file.getName(), newChild);
		
		super.add(newChild);
	}
	
	public File getFile() {
		return (File) getUserObject();
	}
	
	@Override
	public void removeAllChildren() {
		children.clear();
		super.removeAllChildren();
	}

	public FileTreeNode getNode(LinkedList<String> path) {

		if(path.size() == 0) {
			return this;
		}
		
		checkLoadChildren();
		
		String removed = path.removeFirst();
		
		FileTreeNode child = children.get(removed);
		
		if(child == null) {
			
			
			child = children.get(removed);
			
			if(child == null) {
				return null;
			}
		}

		return child.getNode(path);
	}
	
	private void checkLoadChildren() {
		
		if(childrenLoaded == false) {
			loadChildren();
		}
		
	}

	@Override
	public TreeNode getChildAfter(TreeNode aChild) {
		checkLoadChildren();
		return super.getChildAfter(aChild);
	}
	
	@Override
	public int getChildCount() {
		checkLoadChildren();
		return super.getChildCount();
	}
	
	@Override
	public TreeNode getChildBefore(TreeNode aChild) {
		checkLoadChildren();
		return super.getChildBefore(aChild);
	}
	
	@Override
	public TreeNode getChildAt(int index) {
		checkLoadChildren();
		return super.getChildAt(index);
	}
	
	@Override
	public TreeNode getFirstChild() {
		checkLoadChildren();
		return super.getFirstChild();
	}
	
	@Override
	public TreeNode getLastChild() {
		checkLoadChildren();
		return super.getLastChild();
	}
	
	@Override
	public boolean isLeaf() {
		checkLoadChildren();
		return super.isLeaf();
	}
	
	private void loadChildren() {

		File file = getFile();
		
		if(file == null) {
			return;
		}
		childrenLoaded = true;
		if (file.isDirectory() == false) {
		
			return;
		
		}
		
		List<File> files = Arrays.asList(FileSystemView.getFileSystemView().getFiles(file, true));
		
		removeAllChildren();
		
		Collections.sort(files, new FileComparator());
		for (File child : files) {
			if (child.isDirectory() || (child.canRead() && onlyDirectories == false)) {
				add(new FileTreeNode(child, onlyDirectories));
			}
		}
	}
	
	class FileComparator implements Comparator<File> {
		@Override
		public int compare(File f1, File f2) {
			if (f1.isDirectory() && !f2.isDirectory()) return -1;
			if (!f1.isDirectory() && f2.isDirectory()) return 1;
			int dot = f1.getName().lastIndexOf('.');
			String ext1 = (dot > 0) ? f1.getName().substring(dot + 1) : "";
			dot = f2.getName().lastIndexOf('.');
			String ext2 = (dot > 0) ? f2.getName().substring(dot + 1) : "";
			int extOrder = ext1.compareToIgnoreCase(ext2);
			if (extOrder != 0) return extOrder;
			return f1.getName().compareTo(f2.getName());
		}
	}
}
