package de.emir.rcp.views;

import java.awt.Component;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import de.emir.rcp.views.ep.ViewDescriptor;
import de.emir.rcp.views.ep.ViewGroup;

public class ViewSelectionTreeCellRenderer extends DefaultTreeCellRenderer {

    private static final long serialVersionUID = 5599432058867531308L;

    private Border border = BorderFactory.createEmptyBorder ( 1, 0, 1, 0);

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
            int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        setIcon(null);

        setBorder(border);

        if (node.getUserObject() != null) {
            Object obj = node.getUserObject();

            if (obj instanceof ViewGroup){
                ViewGroup view = (ViewGroup) obj;

                ImageIcon icon = view.getIcon();

                if (icon != null) {
                    setIcon(icon);
                }
            }else {
                ViewDescriptor view = (ViewDescriptor) obj;

                ImageIcon icon = view.getIcon();

                if (icon != null) {
                    setIcon(icon);
                }
            }
        }

        return this;
    }

}
