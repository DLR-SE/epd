/**
 * Based on: http://www.jroller.com/santhosh/date/20050610
 */
package de.emir.ui.utils.cbtree;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import de.emir.ui.utils.TreeUtils;

public class CheckTreeManager extends MouseAdapter implements TreeSelectionListener {
    private CheckTreeSelectionModel selectionModel;
    private JTree tree = new JTree();
    int hotspot = new JCheckBox().getPreferredSize().width;

    public CheckTreeManager(JTree tree) {
        this.tree = tree;
        selectionModel = new CheckTreeSelectionModel(tree.getModel());
        tree.setCellRenderer(new CheckTreeCellRenderer(tree.getCellRenderer(), selectionModel));
        tree.addMouseListener(this);
        selectionModel.addTreeSelectionListener(this);
    }

    public void mouseClicked(MouseEvent me) {
        TreePath path = tree.getPathForLocation(me.getX(), me.getY());
        if (path == null)
            return;
        if (me.getX() > tree.getPathBounds(path).x + hotspot)
            return;
        boolean selected = selectionModel.isPathSelected(path, true);
        selectionModel.removeTreeSelectionListener(this);

        try {
            if (selected)
                selectionModel.removeSelectionPath(path);
            else
                selectionModel.addSelectionPath(path);
        } finally {
            selectionModel.addTreeSelectionListener(this);
            tree.treeDidChange();
        }
    }

    public Collection<TreePath> getAllSelectedItems() {
        ArrayList<TreePath> out = new ArrayList<>();
        Collection<TreePath> all = TreeUtils.getAllPathes((TreeNode) tree.getModel().getRoot());
        for (TreePath tp : all) {
            if (selectionModel.isPathSelected(tp, true))
                out.add(tp);
        }
        return out;
    }

    public CheckTreeSelectionModel getSelectionModel() {
        return selectionModel;
    }
    
    public JTree getTree() {
        return tree;
    }

    public void valueChanged(TreeSelectionEvent e) {
        tree.treeDidChange();
    }
}
