package de.emir.rcp.settings.basics.keybindings;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import de.emir.rcp.settings.basics.keybindings.KeyBindingSettingsPage.KeyBindingData;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public class PluginCommandTreeCellRenderer extends DefaultTreeCellRenderer {

    private ResourceManager rmgr = ResourceManager.get(getClass());

    private static final long serialVersionUID = -6392813113803773794L;

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
            boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        Object o = node.getUserObject();
        if (o instanceof AbstractUIPlugin) {

            setText(o.getClass().getSimpleName());

            setIcon(IconManager.getIcon(this, "icons/emiricons/32/custom_extension.png",
                    IconManager.preferedSmallIconSize()));

        } else if (o instanceof KeyBindingData) {

            KeyBindingData kbd = (KeyBindingData) o;

            setText(kbd.getCommand().getLabel());
            setIcon(rmgr.getImageIcon("icons/emiricons/32/task.png"));

        }

        return this;
    }

}
