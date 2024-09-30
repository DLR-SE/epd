package de.emir.rcp.settings.basics.keybindings;

import de.emir.model.universal.plugincore.var.GlobalKeyBinding;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import de.emir.rcp.editors.ep.Editor;
import de.emir.rcp.views.ep.ViewDescriptor;
import de.emir.tuml.ucore.runtime.resources.IconManager;

public class ContextSelectionTreeCellRenderer extends DefaultTreeCellRenderer {

    private static final long serialVersionUID = -6388062123012208281L;

    private Icon globalIcon = IconManager.getIcon(this, "icons/emiricons/32/dashboard.png");

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
            boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        setIcon(null);

        if (node.getUserObject() instanceof ViewDescriptor) {
            ViewDescriptor view = (ViewDescriptor) node.getUserObject();

            ImageIcon icon = view.getIcon();
            if (icon != null) {
                setIcon(icon);
            }
        } else if (node.getUserObject() instanceof Editor) {

            Editor editor = (Editor) node.getUserObject();
            ImageIcon icon = editor.getIcon();
            if (icon != null) {
                setIcon(icon);
            }

            String label = editor.getLabel();
            if (label != null) {
                setText(label);
            }
        } else if (node.getUserObject() instanceof GlobalKeyBinding) {

            setText("Global");
            setIcon(globalIcon);
        }

        return this;

    }

}
