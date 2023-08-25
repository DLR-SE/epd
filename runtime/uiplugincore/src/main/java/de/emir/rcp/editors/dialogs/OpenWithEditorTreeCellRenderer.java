package de.emir.rcp.editors.dialogs;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import de.emir.rcp.editors.ep.Editor;

public class OpenWithEditorTreeCellRenderer extends DefaultTreeCellRenderer {

    private static final long serialVersionUID = 5599432058867531308L;

    public OpenWithEditorTreeCellRenderer() {
        setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
            int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        setIcon(null);

        if (node.getUserObject() != null) {
            Editor editor = (Editor) node.getUserObject();
            ImageIcon icon = editor.getIcon();
            if (icon != null) {
                setIcon(icon);
            }

            String label = editor.getLabel();
            if (label != null) {
                setText(label);
            }
        }

        return this;
    }

}
