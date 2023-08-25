package de.emir.rcp.pluginmanager.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.pluginmanager.ids.PMBasics;

public class PluginsTreeSelectionListener implements TreeSelectionListener {

    @Override
    public void valueChanged(TreeSelectionEvent e) {

        JTree tree = (JTree) e.getSource();

        TreeSelectionModel sm = tree.getSelectionModel();

        TreePath[] paths = sm.getSelectionPaths();

        List<Object> selectedData = new ArrayList<>();

        for (TreePath treePath : paths) {

            DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();

            if (node == null) {
                continue;
            }

            Object uo = node.getUserObject();

            if (uo != null) {
                selectedData.add(uo);
            }

        }

        PlatformUtil.getSelectionManager().setSelection(PMBasics.PLUGIN_TREE_SELECTION_CTX, selectedData);

    }

}
