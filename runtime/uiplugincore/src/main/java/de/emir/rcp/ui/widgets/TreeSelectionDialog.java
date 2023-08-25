package de.emir.rcp.ui.widgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;

public class TreeSelectionDialog extends JDialog {

    private static final long serialVersionUID = -8749158226171794846L;

    private JTextField filterText;
    private String filterInfoText = "type filter text";

    private JTree tree;

    private IFilterMatcher filterMatcher;

    protected boolean firstFocus;

    private DefaultMutableTreeNode rootNode;
    private DefaultMutableTreeNode filteredRootNode;

    private JScrollPane sc;
    protected boolean tempFilterDisabled;
    private TreeSelectionListener treeSelectionListener;

    private Object currentSelection;
    private JLabel infoLabel;
    private JPanel panel;
    private JButton btnCancel;
    private JButton btnOk;

    private ITreeSelectionValidator validator;

    public TreeSelectionDialog(Window parent) {
        super(parent);

        this.setMinimumSize(new Dimension(340, 400));

        GridBagLayout gridBagLayout = new GridBagLayout();

        gridBagLayout.columnWeights = new double[] { 1.0 };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0 };
        getContentPane().setLayout(gridBagLayout);

        DefaultTreeModel model = new DefaultTreeModel(filteredRootNode);

        infoLabel = new JLabel("");
        GridBagConstraints gbc_infoText = new GridBagConstraints();
        gbc_infoText.fill = GridBagConstraints.HORIZONTAL;
        gbc_infoText.insets = new Insets(5, 5, 5, 0);
        gbc_infoText.gridx = 0;
        gbc_infoText.gridy = 0;
        getContentPane().add(infoLabel, gbc_infoText);
        tree = new JTree(model);

        sc = new JScrollPane(tree);

        sc.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        GridBagConstraints gbc_sc = new GridBagConstraints();
        gbc_sc.fill = GridBagConstraints.BOTH;
        gbc_sc.insets = new Insets(0, 5, 5, 5);
        gbc_sc.gridx = 0;
        gbc_sc.gridy = 1;
        gbc_sc.gridheight = 2;
        getContentPane().add(sc, gbc_sc);

        panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.anchor = GridBagConstraints.EAST;
        gbc_panel.fill = GridBagConstraints.VERTICAL;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 3;
        getContentPane().add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 0, 0, 0 };
        gbl_panel.rowHeights = new int[] { 0, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        btnCancel = new JButton("Cancel");
        GridBagConstraints gbc_btnCancel = new GridBagConstraints();
        gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
        gbc_btnCancel.gridx = 0;
        gbc_btnCancel.gridy = 0;
        panel.add(btnCancel, gbc_btnCancel);

        btnOk = new JButton("OK");
        GridBagConstraints gbc_btnOk = new GridBagConstraints();
        gbc_btnOk.gridx = 1;
        gbc_btnOk.gridy = 0;
        panel.add(btnOk, gbc_btnOk);

        btnOk.setEnabled(false);

        setLocationRelativeTo(null);

        addListeners();

    }

    public void setValidator(ITreeSelectionValidator v) {
        this.validator = v;
    }

    private void addListeners() {

        treeSelectionListener = new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {

                DefaultMutableTreeNode selection = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                setSelection(selection == null ? null : selection.getUserObject());

            }
        };

        tree.addTreeSelectionListener(treeSelectionListener);

        btnCancel.addActionListener(e -> {
            currentSelection = null;
            setVisible(false);
        });
        btnOk.addActionListener(e -> setVisible(false));

        tree.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (btnOk.isEnabled() && e.getClickCount() == 2) {
                    setVisible(false);
                }

            }

        });

        tree.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "clickOK");
        tree.getActionMap().put("clickOK", new AbstractAction() {

            private static final long serialVersionUID = 6569116490088170302L;

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

    }

    protected void setSelection(Object o) {
        currentSelection = o;

        if (validator == null) {
            btnOk.setEnabled(false);
            return;
        }

        btnOk.setEnabled(validator.isValid(currentSelection));

    }

    public void setInfoText(String text) {
        this.infoLabel.setText(text);
    }

    /**
     * Updates the filtered model tree to show elements in accordance to the currently active filtering
     */
    protected void updateFiltering() {

        filteredRootNode = new DefaultMutableTreeNode();

        for (int i = 0; i < rootNode.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) rootNode.getChildAt(i);
            updateFiltering(filteredRootNode, child);
        }
        tree.removeTreeSelectionListener(treeSelectionListener);
        DefaultTreeModel model = new DefaultTreeModel(filteredRootNode);
        tree.setModel(model);
        tree.addTreeSelectionListener(treeSelectionListener);

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
     * (Optional) Set a matcher that checks, if an element applies to the currently active filter. The filter tet field
     * is only visible if a matcher is set
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
        gbc_textField.gridy = 1;
        getContentPane().add(filterText, gbc_textField);
        filterText.setColumns(10);

        filterText.setText(filterInfoText);

        remove(sc);

        GridBagConstraints gbc_sc = new GridBagConstraints();
        gbc_sc.fill = GridBagConstraints.BOTH;
        gbc_sc.insets = new Insets(0, 5, 5, 5);
        gbc_sc.gridx = 0;
        gbc_sc.gridy = 2;

        getContentPane().add(sc, gbc_sc);

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

    public Object getSelection() {
        return currentSelection;
    }

}
