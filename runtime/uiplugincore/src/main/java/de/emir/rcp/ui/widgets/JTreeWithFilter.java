package de.emir.rcp.ui.widgets;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * A widget composition containing a tree of elements and a details area.
 * Selecting an element will update the details area to display informations
 * regarding this element. An optional filter text field can be used. Note: The
 * filter text will only show up, if a FilterMatcher is added.
 *
 * @author Florian
 */
public class JTreeWithFilter extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -7788285063478895705L;
    protected boolean firstFocus;
    protected boolean tempFilterDisabled;
    private JTextField filterText;
    private String filterInfoText = "type filter text";
    private JTree tree;
    private IFilterMatcher filterMatcher;
    private DefaultMutableTreeNode rootNode;
    private DefaultMutableTreeNode filteredRootNode;
    private JScrollPane sc;
    private DefaultTreeModel treeModel;

    public JTreeWithFilter() {
        treeModel = new DefaultTreeModel(filteredRootNode);
        setLayout(new BorderLayout(0, 0));
        tree = new JTree(treeModel);
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);

        sc = new JScrollPane(tree);
        sc.setPreferredSize(new Dimension(50, 322));
        sc.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));

        GridBagConstraints gbc_sc = new GridBagConstraints();
        gbc_sc.fill = GridBagConstraints.BOTH;
        gbc_sc.insets = new Insets(0, 5, 5, 5);
        gbc_sc.gridx = 0;
        gbc_sc.gridy = 0;
        gbc_sc.gridheight = 2;
        add(sc);
    }

    public void addTreeSelectionListener(TreeSelectionListener tsl) {
        tree.addTreeSelectionListener(tsl);
    }

    /**
     * Updates the filtered model tree to show elements in accordance to the
     * currently active filtering
     */
    protected void updateFiltering() {

        filteredRootNode = new DefaultMutableTreeNode();

        for (int i = 0; i < rootNode.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) rootNode.getChildAt(i);
            updateFiltering(filteredRootNode, child);
        }
        treeModel = new DefaultTreeModel(filteredRootNode);
        tree.setModel(treeModel);

        if (filterText != null && filterText.getText().isEmpty() == false
                && filterText.getText().equals(filterInfoText) == false) {

            expandAllNodes(tree, 0, tree.getRowCount());

        }

    }

    private void expandAllNodes(JTree tree, int startingIndex, int rowCount) {
        for (int i = startingIndex; i < rowCount; ++i) {
            tree.expandRow(i);
        }

        if (tree.getRowCount() != rowCount) {
            expandAllNodes(tree, rowCount, tree.getRowCount());
        }
    }

    /**
     * Recursivly walks along the model tree, filtering all non matching elements
     *
     * @param root
     * @param child
     * @return
     */
    private boolean updateFiltering(DefaultMutableTreeNode root, DefaultMutableTreeNode child) {

        DefaultMutableTreeNode childRepresentation = new DefaultMutableTreeNode(child.getUserObject());

        if (child.getChildCount() == 0) {

            // leaf reached
            boolean matches = filterText == null || filterText.getText().isEmpty() == true
                    || filterText.getText().equals(filterInfoText) ? true : matches(child);

            if (matches == true) {

                root.add(childRepresentation);
            }

            return matches;

        }

        boolean matchFound = false;

        for (int i = 0; i < child.getChildCount(); i++) {
            DefaultMutableTreeNode child2 = (DefaultMutableTreeNode) child.getChildAt(i);
            boolean contained = updateFiltering(childRepresentation, child2);

            if (contained == true) {

                matchFound = true;

            }

        }

        if (matchFound == true || matches(child)) {

            root.add(childRepresentation);
            return true;
        }

        return false;

    }

    private boolean matches(DefaultMutableTreeNode node) {

        if (filterText.getText().isEmpty() == true || filterText.getText().equals(filterInfoText)) {
            return true;
        }

        if (filterMatcher == null) {
            return false;
        }

        return filterMatcher.matches(node.getUserObject(), filterText.getText());

    }

    /**
     * Add a cell renderer to define how elements are displayed within the tree
     *
     * @param r
     */
    public void setCellRenderer(TreeCellRenderer r) {
        tree.setCellRenderer(r);
    }

    /**
     * The root node of the tree model
     *
     * @param rootNode
     */
    public void setRootNode(DefaultMutableTreeNode rootNode) {
        this.rootNode = rootNode;
        updateFiltering();
    }

    /**
     * The default text shown within the filter text field
     *
     * @param filterInfoText
     */
    public void setFilterInfoText(String filterInfoText) {

        if (filterText != null && filterText.getText().equals(this.filterInfoText)) {
            filterText.setText(filterInfoText);
        }

        this.filterInfoText = filterInfoText;
    }

    /**
     * (Optional) Set a matcher that checks, if an element applies to the currently
     * active filter. The filter tet field is only visible if a matcher is set
     *
     * @param f
     */
    public void setFilterMatcher(IFilterMatcher f) {

        if (filterMatcher == null) {
            addFilterText();
        }

        this.filterMatcher = f;
    }

    private void addFilterText() {

        filterText = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(5, 5, 5, 5);
        gbc_textField.fill = GridBagConstraints.BOTH;
        gbc_textField.gridx = 0;
        gbc_textField.gridy = 0;
        add(filterText, BorderLayout.NORTH);
        filterText.setColumns(10);

        filterText.setText(filterInfoText);

        remove(sc);

        GridBagConstraints gbc_sc = new GridBagConstraints();
        gbc_sc.fill = GridBagConstraints.BOTH;
        gbc_sc.insets = new Insets(0, 5, 5, 5);
        gbc_sc.gridx = 0;
        gbc_sc.gridy = 1;

        add(sc, BorderLayout.CENTER);

        filterText.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {

                if (tempFilterDisabled == false) {
                    updateFiltering();
                }

            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (tempFilterDisabled == false) {
                    updateFiltering();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        filterText.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                firstFocus = false;

                if (filterText.getText().isEmpty()) {

                    tempFilterDisabled = true;
                    filterText.setText(filterInfoText);
                    tempFilterDisabled = false;
                }

            }

            @Override
            public void focusGained(FocusEvent e) {

                if (firstFocus == false && filterText.getText().equals(filterInfoText)) {
                    tempFilterDisabled = true;
                    filterText.setText("");
                    tempFilterDisabled = false;

                } else {

                    filterText.select(0, filterText.getText().length());

                }
            }
        });

    }

    public JTree getTree() {
        return tree;
    }

    public void clearSelection() {
        tree.clearSelection();
    }

    public void expandPath(TreePath treePath) {
        tree.expandPath(treePath);
    }

    public int getRowCount() {
        return tree.getRowCount();
    }

    public void expandRow(int i) {
        tree.expandRow(i);
    }

    public int getRowForPath(TreePath path) {
        return tree.getRowForPath(path);
    }

    public TreePath getSelectionPath() {
        return tree.getSelectionPath();
    }

    public void reset(){
        filterText.setText("");
    }

}
