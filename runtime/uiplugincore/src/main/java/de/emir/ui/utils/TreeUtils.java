package de.emir.ui.utils;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.*;

public class TreeUtils {

    private static List<List<TreeNode>> getPaths0(TreeNode pos) {
        List<List<TreeNode>> retLists = new ArrayList<>();

        if (pos.getChildCount() == 0) {
            List<TreeNode> leafList = new LinkedList<>();
            leafList.add(pos);
            retLists.add(leafList);
        } else {
            for (int i = 0; i < pos.getChildCount(); i++) {
                TreeNode node = pos.getChildAt(i);
                List<List<TreeNode>> nodeLists = getPaths0(node);
                for (List<TreeNode> nodeList : nodeLists) {
                    nodeList.add(0, pos);
                    retLists.add(nodeList);
                }
            }
        }

        return retLists;
    }

    public static List<List<TreeNode>> getPaths(TreeNode head) {
        if (head == null) {
            return new ArrayList<>();
        } else {
            return getPaths0(head);
        }
    }

    public static Collection<TreeNode> getAllNodes(TreeNode head) {
        HashSet<TreeNode> out = new HashSet<>();
        List<List<TreeNode>> lists = getPaths(head);
        for (List<TreeNode> list : lists) {
            out.addAll(list);
        }
        return out;
    }

    public static TreePath getPath(TreeNode treeNode) {
        List<Object> nodes = new ArrayList<Object>();
        if (treeNode != null) {
            nodes.add(treeNode);
            treeNode = treeNode.getParent();
            while (treeNode != null) {
                nodes.add(0, treeNode);
                treeNode = treeNode.getParent();
            }
        }

        return nodes.isEmpty() ? null : new TreePath(nodes.toArray());
    }

    public static Collection<TreePath> getAllPathes(TreeNode head) {
        ArrayList<TreePath> out = new ArrayList<>();
        Collection<TreeNode> nodes = getAllNodes(head);
        for (TreeNode n : nodes) {
            out.add(getPath(n));
        }
        return out;
    }

    public static DefaultMutableTreeNode sort(DefaultMutableTreeNode node) {
        List<DefaultMutableTreeNode> nodes = new ArrayList<>();
        for (int i = 0; i < node.getChildCount(); i++) {
            nodes.add(i, (DefaultMutableTreeNode) node.getChildAt(i));
        }

        nodes.sort((o1, o2) -> {
            if (o1.getChildCount() > 0 && o2.getChildCount() == 0) {
                return -1;
            } else if (o1.getChildCount() == 0 && o2.getChildCount() > 0) {
                return 1;
            } else {
                return o1.toString().compareTo(o2.toString());
            }

        });

        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode n = nodes.get(i);
            if (n.getChildCount() > 0) {
                n = sort(n);
            }
            node.insert(n, i);
        }


        return node;

    }
}
