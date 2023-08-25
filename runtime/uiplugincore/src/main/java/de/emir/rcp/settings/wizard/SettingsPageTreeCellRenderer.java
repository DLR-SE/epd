package de.emir.rcp.settings.wizard;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import de.emir.rcp.settings.ep.SettingsPageData;

public class SettingsPageTreeCellRenderer extends DefaultTreeCellRenderer {

    private static final long serialVersionUID = 5599432058867531308L;

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
            int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        setIcon(null);

        if (node.getUserObject() != null) {
            SettingsPageData spd = (SettingsPageData) node.getUserObject();

            ImageIcon icon = spd.icon;

            if (icon != null) {
                setIcon(icon);
            }
        }

        return this;
    }

}
