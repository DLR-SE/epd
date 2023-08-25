package de.emir.rcp.settings.basics.keybindings;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import de.emir.tuml.ucore.runtime.resources.IconManager;

public class KeyTreeCellRenderer extends DefaultTreeCellRenderer {

    private static final long serialVersionUID = 7229528837258319160L;

    private Icon icon = IconManager.getIcon(this, "icons/emiricons/32/keyboard.png", IconManager.preferedSmallIconSize());

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
            boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        Object o = node.getUserObject();
        if (o instanceof String) {

            setText((String) o);

            setIcon(icon);

        }

        return this;
    }

}
