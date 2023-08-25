/**
 * Based on: http://www.jroller.com/santhosh/date/20050610
 */
package de.emir.ui.utils.cbtree;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

import de.emir.ui.utils.tscb.TristateCheckBox;
import de.emir.ui.utils.tscb.TristateState;

public class CheckTreeCellRenderer extends JPanel implements TreeCellRenderer {
    private CheckTreeSelectionModel selectionModel;
    private TreeCellRenderer delegate;
    private TristateCheckBox checkBox = new TristateCheckBox("");

    public CheckTreeCellRenderer(TreeCellRenderer delegate, CheckTreeSelectionModel selectionModel) {
        this.delegate = delegate;
        this.selectionModel = selectionModel;
        setLayout(new BorderLayout());
        setOpaque(false);
        checkBox.setOpaque(false);
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
            boolean leaf, int row, boolean hasFocus) {
        Component renderer = delegate.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row,
                hasFocus);

        TreePath path = tree.getPathForRow(row);
        if (path != null) {
            if (selectionModel.isPathSelected(path, true))
                checkBox.setState(TristateState.SELECTED);
            else
                checkBox.setState(selectionModel.isPartiallySelected(path) ? TristateState.INDETERMINATE
                        : TristateState.DESELECTED);
        }
        removeAll();
        add(checkBox, BorderLayout.WEST);
        add(renderer, BorderLayout.CENTER);
        return this;
    }
}
