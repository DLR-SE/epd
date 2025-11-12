package de.emir.rcp.ui.widgets;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

import de.emir.tuml.ucore.runtime.resources.IconManager;

/**
 * A widget composition containing a tree of elements and a details area.
 * Selecting an element will update the details area to display informations
 * regarding this element. An optional filter text field can be used. Note: The
 * filter text will only show up, if a FilterMatcher is added.
 * 
 * @author Florian
 *
 */
public class JTreeWithFilterAndDetailsArea extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7788285063478895705L;
	private JTextField filterText;
	private String filterInfoText = "type filter text";
	private JLabel detailsLabel;
	private JTree tree;
	private JPanel content;

	private IDetailsAreaProvider detailsAreaProvider;
	private IFilterMatcher filterMatcher;

	protected boolean firstFocus;

	private DefaultMutableTreeNode rootNode;
	private DefaultMutableTreeNode filteredRootNode;

	private AbstractDetailsContentPanel<?> currentDetails;

	private Icon defaultIcon = IconManager.getIcon(this, "icons/emiricons/32/settings.png",
			IconManager.preferedSmallIconSize());
	private JScrollPane sc;
	protected boolean tempFilterDisabled;
	private TreeSelectionListener treeSelectionListener;
	private DefaultTreeModel treeModel;
	private JSplitPane splitPane;
	private JPanel rightPanel;
	private JPanel leftPanel;
	private JScrollPane sc_1;

	public JTreeWithFilterAndDetailsArea() {

		rightPanel = new JPanel();
		GridBagLayout gbl_rightPanel = new GridBagLayout();
		gbl_rightPanel.columnWidths = new int[] { 104, 0 };
		gbl_rightPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_rightPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_rightPanel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		rightPanel.setLayout(gbl_rightPanel);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		rightPanel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		detailsLabel = new JLabel("");
		detailsLabel.setFont(detailsLabel.getFont().deriveFont(Font.BOLD, 11));
		detailsLabel.setIcon(defaultIcon);

		GridBagConstraints gbc_lblDetailsLabel = new GridBagConstraints();
		gbc_lblDetailsLabel.anchor = GridBagConstraints.WEST;
		gbc_lblDetailsLabel.insets = new Insets(2, 0, 5, 0);
		gbc_lblDetailsLabel.gridx = 0;
		gbc_lblDetailsLabel.gridy = 0;
		panel_1.add(detailsLabel, gbc_lblDetailsLabel);

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 1;
		panel_1.add(separator_1, gbc_separator_1);

		treeModel = new DefaultTreeModel(filteredRootNode);
		setLayout(new BorderLayout(0, 0));

		splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.20);
		add(splitPane);

		splitPane.setRightComponent(rightPanel);
		
		sc_1 = new JScrollPane();
		GridBagConstraints gbc_sc_1 = new GridBagConstraints();
		gbc_sc_1.fill = GridBagConstraints.BOTH;
		gbc_sc_1.gridx = 0;
		gbc_sc_1.gridy = 1;
		rightPanel.add(sc_1, gbc_sc_1);
		
				content = new JPanel();
				sc_1.setViewportView(content);
				GridBagLayout gbl_content = new GridBagLayout();
				gbl_content.columnWidths = new int[]{0, 0};
				gbl_content.rowHeights = new int[]{0, 0};
				gbl_content.columnWeights = new double[]{1.0, Double.MIN_VALUE};
				gbl_content.rowWeights = new double[]{1.0, Double.MIN_VALUE};
				content.setLayout(gbl_content);

		leftPanel = new JPanel();
		splitPane.setLeftComponent(leftPanel);
				leftPanel.setLayout(new BorderLayout(0, 0));
		
				sc = new JScrollPane();
				leftPanel.add(sc);
				tree = new JTree(treeModel);
				sc.setViewportView(tree);
				tree.setRootVisible(false);
				tree.setShowsRootHandles(true);

		addListeners();

	}

	public void addTreeSelectionListener(TreeSelectionListener tsl) {
		tree.addTreeSelectionListener(tsl);
	}

	public void setTreeWidth(int width) {
		sc.setPreferredSize(new Dimension(width, sc.getPreferredSize().height));
		sc.setMinimumSize(new Dimension(width / 2, sc.getPreferredSize().height));
	}

	public void setMinimumTreeWidth(int width) {
		sc.setMinimumSize(new Dimension(width, sc.getPreferredSize().height));
	}

	private void addListeners() {

		treeSelectionListener = new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {

				DefaultMutableTreeNode selection = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				activateContent(selection == null ? null : selection.getUserObject());

			}
		};

		tree.addTreeSelectionListener(treeSelectionListener);

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
		tree.removeTreeSelectionListener(treeSelectionListener);
		treeModel = new DefaultTreeModel(filteredRootNode);
		tree.setModel(treeModel);
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
	 * Updates the details area
	 * 
	 * @param o
	 */
	protected void activateContent(Object o) {

		if (currentDetails != null) {
			currentDetails.onClose();
		}

		detailsLabel.setText("");
		detailsLabel.setIcon(defaultIcon);

		Component[] components = content.getComponents();

		for (Component comp : components) {
			content.remove(comp);
		}

		if (o == null) {
			content.repaint();
			return;
		}

		if (detailsAreaProvider == null) {
			return;
		}

		GridBagConstraints gbcContent = new GridBagConstraints();
		gbcContent.insets = new Insets(0, 0, 0, 0);
		gbcContent.fill = GridBagConstraints.BOTH;
		gbcContent.gridx = 0;
		gbcContent.gridy = 0;

		currentDetails = detailsAreaProvider.getDetailsPanel(o);

		if (currentDetails == null) {

			content.add(new JPanel(), gbcContent);
			return;
		}

		Component detailsContentPanel = currentDetails.createContents();

		if (detailsContentPanel != null) {

			content.add(detailsContentPanel, gbcContent);

		}

		detailsLabel.setText(currentDetails.getTitle());
		detailsLabel.setIcon(currentDetails.getIcon());

		if (detailsLabel.getIcon() == null) {
			detailsLabel.setIcon(defaultIcon);
		}

		currentDetails.onOpen();

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
	 * Set to define the details area in accordance to the selected element
	 * 
	 * @param p
	 */
	public void setDetailsAreaProvider(IDetailsAreaProvider p) {
		detailsAreaProvider = p;
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
		leftPanel.add(filterText, BorderLayout.NORTH);
		filterText.setColumns(10);
		filterText.setText(filterInfoText);

		leftPanel.remove(sc);
		leftPanel.add(sc, BorderLayout.CENTER);

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

}
