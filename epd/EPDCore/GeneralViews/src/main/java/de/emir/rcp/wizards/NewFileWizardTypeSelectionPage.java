package de.emir.rcp.wizards;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import de.emir.rcp.manager.NewFileWizardManager;
import de.emir.rcp.ui.wizard.AbstractWizardPage;
import de.emir.rcp.wizards.ep.NewFileWizardCategory;
import de.emir.rcp.wizards.ep.NewFileWizardExtensionPoint;
import de.emir.rcp.wizards.ep.NewFileWizardType;
import de.emir.rcp.wizards.types.AbstractNewFileWizardPage;
import de.emir.tuml.ucore.runtime.resources.IconManager;

public class NewFileWizardTypeSelectionPage extends AbstractNewFileWizardPage {

	private static final String INFO_FILTER_TEXT = "type filter text";
	private static final long serialVersionUID = 6487873620361264975L;
	private JTextField filterText;
	private JTree tree;

	private Icon catIcon = IconManager.getIcon(this, "icons/emiricons/32/folder_open.png",
			IconManager.preferedSmallIconSize());

	private Object selection;
	private DefaultMutableTreeNode filteredRootNode;
	private DefaultMutableTreeNode rootNode;
	private NewFileWizardModel model;
	

	public NewFileWizardTypeSelectionPage(NewFileWizard wizard, NewFileWizardModel model) {
		super(wizard, model);
		
		this.model = model;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 1.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0 };
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(10, 5, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblSelectAWizard = new JLabel("Select a Wizard");
		lblSelectAWizard.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblSelectAWizard = new GridBagConstraints();
		gbc_lblSelectAWizard.anchor = GridBagConstraints.WEST;
		gbc_lblSelectAWizard.gridx = 0;
		gbc_lblSelectAWizard.gridy = 0;
		panel.add(lblSelectAWizard, gbc_lblSelectAWizard);

		JLabel lblCreateANew = new JLabel("Create a new file resource");
		GridBagConstraints gbc_lblCreateANew = new GridBagConstraints();
		gbc_lblCreateANew.gridx = 0;
		gbc_lblCreateANew.gridy = 1;
		panel.add(lblCreateANew, gbc_lblCreateANew);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.ipady = 2;
		gbc_separator.anchor = GridBagConstraints.NORTH;
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		add(separator, gbc_separator);

		JLabel lblWizards = new JLabel("Wizards:");
		GridBagConstraints gbc_lblWizards = new GridBagConstraints();
		gbc_lblWizards.anchor = GridBagConstraints.WEST;
		gbc_lblWizards.insets = new Insets(0, 5, 5, 0);
		gbc_lblWizards.gridx = 0;
		gbc_lblWizards.gridy = 2;
		add(lblWizards, gbc_lblWizards);

		filterText = new JTextField();
		filterText.setText(INFO_FILTER_TEXT );
		GridBagConstraints gbc_txtTypeFilterText = new GridBagConstraints();
		gbc_txtTypeFilterText.insets = new Insets(0, 5, 5, 5);
		gbc_txtTypeFilterText.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTypeFilterText.gridx = 0;
		gbc_txtTypeFilterText.gridy = 3;
		add(filterText, gbc_txtTypeFilterText);
		filterText.setColumns(10);

		tree = new JTree();

		JScrollPane scrollPane = new JScrollPane(tree);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 5, 30, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		add(scrollPane, gbc_scrollPane);

		tree.setRootVisible(false);
		tree.setShowsRootHandles(true);
		tree.setCellRenderer(new TypeTreeCellRenderer());
		createTreeModel();

		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {

				if(tree.getSelectionPath() == null) {
					selection = null;
				} else {
					
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();

					if (node == null) {
						selection = null;
					} else {
						selection = node.getUserObject();
					}
					
				}
				
				

				handleSelection();
				
				getWizard().updateButtons();

			}
		});
		
		tree.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() > 1 && SwingUtilities.isLeftMouseButton(e)) {
					
					getWizard().nextPage();
					
				}
			}
		});
		
		filterText.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateFiltering();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateFiltering();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
		
		filterText.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				String text = filterText.getText();
				
				if(INFO_FILTER_TEXT.equals(text)) {
					filterText.setText("");
				}
				
			}
			
		});

	}
	
	private void updateFiltering() {

		filteredRootNode = new DefaultMutableTreeNode();

		for (int i = 0; i < rootNode.getChildCount(); i++) {
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) rootNode.getChildAt(i);
			updateFiltering(filteredRootNode, child);
		}

		DefaultTreeModel model = new DefaultTreeModel(filteredRootNode);
		tree.setModel(model);

		if (filterText.getText().isEmpty() == false) {

			expandAllNodes(tree, 0, tree.getRowCount());

		}

	}
	
	private boolean updateFiltering(DefaultMutableTreeNode root, DefaultMutableTreeNode child) {

		DefaultMutableTreeNode childRepresentation = new DefaultMutableTreeNode(child.getUserObject());

		if (child.getChildCount() == 0) {

			// leaf reached
			boolean matches = matches(child);

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

		if (matchFound == true) {

			root.add(childRepresentation);
			return true;
		}

		return false;

	}

	private boolean matches(DefaultMutableTreeNode node) {

		if (filterText.getText().isEmpty() == true) {
			return true;
		}

		String label = null;
		
		Object uo = node.getUserObject();
		
		if(uo instanceof NewFileWizardCategory) {
			label = ((NewFileWizardCategory) uo).getLabel();
		} else if(uo instanceof NewFileWizardType) {
			label = ((NewFileWizardType) uo).getLabel();
		}

		if (label == null) {
			return false;
		}

		return label.toLowerCase().contains(filterText.getText().toLowerCase());

	}
	
	private void expandAllNodes(JTree tree, int startingIndex, int rowCount) {
		for (int i = startingIndex; i < rowCount; ++i) {
			tree.expandRow(i);
		}

		if (tree.getRowCount() != rowCount) {
			expandAllNodes(tree, rowCount, tree.getRowCount());
		}
	}
	
	protected void handleSelection() {

		if (model.getSelectedTypeInstance() != null) {

			List<AbstractNewFileWizardPage> pages = model.getSelectedTypeInstance().getPages();
			
			if(pages != null) {
				getWizard().removePages(pages.toArray(new AbstractWizardPage[0]));
			}
			
			
			model.setSelectedTypeInstance(null);
			model.setSelectedType(null);
			model.setFileName(null);
			model.setAdditionalInformations(null);
			
			
		}

		if (selection instanceof NewFileWizardType == false) {
			return;
		}

		NewFileWizardType type = (NewFileWizardType) selection;

		Class<? extends AbstractNewFileWizardTypeInformation> typeClass = type.getTypeClass();
		try {
			model.setSelectedTypeInstance(typeClass.getConstructor(NewFileWizard.class).newInstance((NewFileWizard)getWizard()));
			model.setSelectedType(type);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return;
		}

		List<AbstractNewFileWizardPage> pages = model.getSelectedTypeInstance().getPages();

		if(pages != null) {
			getWizard().addPages(pages.toArray(new AbstractWizardPage[0]));
		}
		
	}

	@Override
	public boolean isNextAvailable() {
		return selection instanceof NewFileWizardType;

	}

	@Override
	public boolean isFinishAvailable() {
		return false;
	}

	private void createTreeModel() {

		NewFileWizardExtensionPoint ep = NewFileWizardManager.getNewFileWizardExtensionPoint();
		rootNode = new DefaultMutableTreeNode(ep);
		filteredRootNode = rootNode;
		
		Collection<NewFileWizardType> types = ep.getTypes().values();
		Collection<NewFileWizardCategory> categories = ep.getCategories().values();
		
		for (NewFileWizardType type : types) {
			
			
			if(type.getCategoryId() == null) {
				rootNode.add(new DefaultMutableTreeNode(type));
			}
			
		}
		
		for (NewFileWizardCategory cat : categories) {
			
			DefaultMutableTreeNode catNode = new DefaultMutableTreeNode(cat);
			String id = cat.getId();
			
			for (NewFileWizardType type : types) {
				
				if(id == null) {
					continue;
				}
				
				if(id.equals(type.getCategoryId())) {
					catNode.add(new DefaultMutableTreeNode(type));
				}
				
			}
			
			rootNode.add(catNode);
		}
		
		

		tree.setModel(new DefaultTreeModel(rootNode));

	}

	public class TypeTreeCellRenderer extends DefaultTreeCellRenderer {

		private static final long serialVersionUID = 8225979522320181018L;

		public TypeTreeCellRenderer() {
			setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
		}

		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
				boolean leaf, int row, boolean hasFocus) {

			super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

			Object uo = node.getUserObject();

			if (uo instanceof NewFileWizardCategory) {
				setIcon(catIcon);

				String label = ((NewFileWizardCategory) uo).getLabel();
				if (label == null) {
					label = ((NewFileWizardCategory) uo).getId();
				}

				setText(label);

			} else if (uo instanceof NewFileWizardType) {
				setText(((NewFileWizardType) uo).getLabel());

				ImageIcon icon = ((NewFileWizardType) uo).getIcon();

				if (icon != null) {
					setIcon(icon);
				}
			}

			return this;

		}

	}
}
