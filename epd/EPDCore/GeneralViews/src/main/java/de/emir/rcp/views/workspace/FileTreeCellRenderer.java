package de.emir.rcp.views.workspace;

import java.awt.Component;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import de.emir.rcp.editors.ep.Editor;
import de.emir.rcp.manager.util.PlatformUtil;

public class FileTreeCellRenderer extends DefaultTreeCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7642203509130431671L;
	public FileSystemView fileSystemView;

	public FileTreeCellRenderer() {

		fileSystemView = FileSystemView.getFileSystemView();
		setBorder( BorderFactory.createEmptyBorder( 2, 0, 2, 0 ));
		
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

		
		
		File file = (File) node.getUserObject();
		if(file == null) {
			return this;
		}
		
		if(file.isDirectory() == true) {
			
			setIcon(fileSystemView.getSystemIcon(file));
			
		} else {
			Editor editorForFile = PlatformUtil.getEditorManager().getEditor(file);
			if(editorForFile != null) {
				
				ImageIcon icon = editorForFile.getIcon();
				setIcon(icon);
			} else {
				setIcon(fileSystemView.getSystemIcon(file));
			}
		}
		
		
		
		
		
		setText(fileSystemView.getSystemDisplayName(file));
		setToolTipText(file.getPath());
		
		return this;
	}
	
	
	
}