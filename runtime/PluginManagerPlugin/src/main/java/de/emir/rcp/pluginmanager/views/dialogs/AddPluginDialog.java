package de.emir.rcp.pluginmanager.views.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.pluginmanager.ids.PMBasics;
import de.emir.rcp.pluginmanager.views.PluginRenderUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.ui.widgets.IFilterMatcher;
import de.emir.rcp.ui.widgets.JTreeWithFilter;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.ui.utils.cbtree.CheckTreeManager;

public class AddPluginDialog extends JDialog {

	private JLabel infoLabel;
	private JTreeWithFilter treeWithFilter;
	private PropertyContext ctx = PropertyStore.getContext(PMBasics.PM_PROP_CTX);
	private CheckTreeManager treeManager;
	private JTextField directoryField;

	private boolean validated = true;

	private boolean applied = false;

	public AddPluginDialog(File file) {
		this();
		directoryField.setText(file.getAbsolutePath());
		setRootDirectory(file);
	}

	public AddPluginDialog() {
		super(PlatformUtil.getWindowManager().getMainWindow());
		setModal(true);
		setTitle("Import Plugins");
		getContentPane().setLayout(new BorderLayout(0, 0));
		setMinimumSize(new Dimension(600, 400));
        setLocationRelativeTo(PlatformUtil.getWindowManager().getMainWindow());

		JPanel choosePanel = new JPanel();
		choosePanel.setBorder(
				new TitledBorder(null, "Choose Directory", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(choosePanel, BorderLayout.NORTH);
		GridBagLayout gbl_choosePanel = new GridBagLayout();
		gbl_choosePanel.columnWidths = new int[] { 29, 166, 67, 0 };
		gbl_choosePanel.rowHeights = new int[] { 23, 0, 0 };
		gbl_choosePanel.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_choosePanel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		choosePanel.setLayout(gbl_choosePanel);

		JLabel lblSelect = new JLabel("Select:");
		GridBagConstraints gbc_lblSelect = new GridBagConstraints();
		gbc_lblSelect.anchor = GridBagConstraints.EAST;
		gbc_lblSelect.insets = new Insets(0, 10, 5, 8);
		gbc_lblSelect.gridx = 0;
		gbc_lblSelect.gridy = 0;
		choosePanel.add(lblSelect, gbc_lblSelect);

		directoryField = new JTextField();
		directoryField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				updateRootDirectory();
			}
		});
		directoryField.addActionListener(e -> updateRootDirectory());
		directoryField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				validateInputs();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				validateInputs();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				validateInputs();
			}
		});

		lblSelect.setLabelFor(directoryField);
		directoryField.setMinimumSize(new Dimension(20, 20));
		directoryField.setToolTipText("Choose a file or folder to import");
		GridBagConstraints gbc_directoryField = new GridBagConstraints();
		gbc_directoryField.fill = GridBagConstraints.HORIZONTAL;
		gbc_directoryField.insets = new Insets(0, 0, 5, 5);
		gbc_directoryField.gridx = 1;
		gbc_directoryField.gridy = 0;
		choosePanel.add(directoryField, gbc_directoryField);
		directoryField.setColumns(20);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(e -> browseDirectory());
		GridBagConstraints gbc_btnBrowse = new GridBagConstraints();
		gbc_btnBrowse.insets = new Insets(0, 0, 5, 0);
		gbc_btnBrowse.anchor = GridBagConstraints.WEST;
		gbc_btnBrowse.gridx = 2;
		gbc_btnBrowse.gridy = 0;
		choosePanel.add(btnBrowse, gbc_btnBrowse);

		infoLabel = new JLabel("");
		infoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_infoLabel = new GridBagConstraints();
		gbc_infoLabel.anchor = GridBagConstraints.WEST;
		gbc_infoLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_infoLabel.gridwidth = 2;
		gbc_infoLabel.insets = new Insets(0, 5, 0, 5);
		gbc_infoLabel.gridx = 1;
		gbc_infoLabel.gridy = 1;
		choosePanel.add(infoLabel, gbc_infoLabel);

		JPanel toolbar = new JPanel();
		toolbar.setBorder(null);
		toolbar.setPreferredSize(new Dimension(110, 10));
		getContentPane().add(toolbar, BorderLayout.EAST);
		GridBagLayout gbl_toolbar = new GridBagLayout();
		gbl_toolbar.columnWidths = new int[]{110, 0};
		gbl_toolbar.rowHeights = new int[] {32, 32, 32, 0};
		gbl_toolbar.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_toolbar.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		toolbar.setLayout(gbl_toolbar);
		
				JButton btnSelectAll = new JButton("Select All");
				btnSelectAll.addActionListener(e -> selectAll());
				GridBagConstraints gbc_btnSelectAll = new GridBagConstraints();
				gbc_btnSelectAll.fill = GridBagConstraints.BOTH;
				gbc_btnSelectAll.insets = new Insets(4, 4, 4, 4);
				gbc_btnSelectAll.gridx = 0;
				gbc_btnSelectAll.gridy = 0;
				toolbar.add(btnSelectAll, gbc_btnSelectAll);
		
				JButton btnRefresh = new JButton("Refresh");
				btnRefresh.addActionListener(e -> updateRootDirectory());
				
						JButton btnSelectNone = new JButton("Select None");
						btnSelectNone.addActionListener(e -> selectNone());
						GridBagConstraints gbc_btnSelectNone = new GridBagConstraints();
						gbc_btnSelectNone.fill = GridBagConstraints.BOTH;
						gbc_btnSelectNone.insets = new Insets(4, 4, 4, 4);
						gbc_btnSelectNone.gridx = 0;
						gbc_btnSelectNone.gridy = 1;
						toolbar.add(btnSelectNone, gbc_btnSelectNone);
				GridBagConstraints gbc_btnRefresh = new GridBagConstraints();
				gbc_btnRefresh.insets = new Insets(4, 4, 4, 4);
				gbc_btnRefresh.fill = GridBagConstraints.BOTH;
				gbc_btnRefresh.gridx = 0;
				gbc_btnRefresh.gridy = 2;
				toolbar.add(btnRefresh, gbc_btnRefresh);

		treeWithFilter = new JTreeWithFilter();
		treeWithFilter.setFilterMatcher(new FileTreeFilterMatcher(""));
		getContentPane().add(treeWithFilter, BorderLayout.CENTER);
		treeManager = new CheckTreeManager(treeWithFilter.getTree());

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(3, 0, 5, 3));
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(e -> {
			applied = true;
			dispose();
		});
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		btnApply.setMinimumSize(new Dimension(100, 25));
		btnApply.setMaximumSize(new Dimension(100, 25));
		btnApply.setPreferredSize(new Dimension(100, 25));
		panel.add(btnApply);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e -> dispose());
		btnCancel.setMinimumSize(new Dimension(100, 25));
		btnCancel.setMaximumSize(new Dimension(100, 25));
		btnCancel.setPreferredSize(new Dimension(100, 25));
		panel.add(btnCancel);

	}

	private void validateInputs() {
		String text = directoryField.getText();
		if (text == null || text.trim().equals("")) {
			validated = false;
			infoLabel.setText("Please select a maven project");
			infoLabel.setIcon(PluginRenderUtil.INFO_ICON);
			return;
		}

		File file = new File(text);
		if (file.exists() == false) {
			validated = false;
			infoLabel.setText("The file does not exist");
			infoLabel.setIcon(PluginRenderUtil.ERROR_ICON);
			return;
		}

		if (file.isDirectory()) {
			validated = false;
			infoLabel.setText("Please select a maven pom");
			infoLabel.setIcon(PluginRenderUtil.ERROR_ICON);
			return;
		}

		if (file.isFile() && (file.getAbsolutePath().endsWith("pom.xml") == false)) {
			validated = false;
			infoLabel.setText("The selected file needs to be a pom file");
			infoLabel.setIcon(PluginRenderUtil.ERROR_ICON);
			return;
		}

		validated = true;
		infoLabel.setText("");
		infoLabel.setIcon(null);
	}

	private void browseDirectory() {
		File lastFolder = ctx.getValue(PMBasics.PM_PROP_LAST_FILE_FOLDER, new File("."));

		MainWindow mw = PlatformUtil.getWindowManager().getMainWindow();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(lastFolder);
		fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
		fileChooser.setDialogTitle("Select Workspace...");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		fileChooser.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "Maven POM File (pom.xml)";
			}

			@Override
			public boolean accept(File f) {
				return f.getName().toLowerCase().equals("pom.xml") || f.isDirectory();
			}
		});

		int result = fileChooser.showOpenDialog(mw);

		if (result != JFileChooser.APPROVE_OPTION) {
			return;
		}

		File selectedFile = fileChooser.getSelectedFile();
		directoryField.setText(selectedFile.getAbsolutePath());
		setRootDirectory(selectedFile);

		ctx.setValue(PMBasics.PM_PROP_LAST_FILE_FOLDER, selectedFile);
	}

	private void updateRootDirectory() {
		setRootDirectory(directoryField.getText());
	}

	private void setRootDirectory(String file) {
		File f = new File(file);
		setRootDirectory(f);
	}

	private void setRootDirectory(File file) {

		if (validated == false) {
			treeWithFilter.setRootNode(new DefaultMutableTreeNode());
		} else {
			DefaultMutableTreeNode node = addNodes(null, file);

			//this is just used when a project is selected which doesn't have child projects
			if (node != null && node.getUserObject() instanceof ModelWrapper && node.getChildCount() == 0){
				DefaultMutableTreeNode copy = new DefaultMutableTreeNode(node.getUserObject());
				node.add(copy);
			}

			treeWithFilter.setRootNode(node);
			treeWithFilter.setFilterMatcher(new FileTreeFilterMatcher(file.getParent()));
		}
	}

	private void selectAll() {
		if (validated) {
			JTree projectTree = treeWithFilter.getTree();
			// tells the tree to select all items
			projectTree.setSelectionInterval(0, projectTree.getRowCount());
			try {

				TreePath[] pPaths = projectTree.getSelectionPaths();
				if (pPaths != null){
					// updates the tree manager to select all paths
					treeManager.getSelectionModel().setSelectionPaths(projectTree.getSelectionPaths());
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				// can be caused if selection is the same as before
			}
		}
	}

	private void selectNone() {
		if (validated) {
			JTree projectTree = treeWithFilter.getTree();
			TreePath[] paths = projectTree.getSelectionPaths();
			if (paths != null && paths.length != 0) {
				// updates the tree manager to remove all currently selected items
				treeManager.getSelectionModel().removeSelectionPaths(paths);
			}
		}
	}

	private DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File pom) {
		if (pom.isDirectory()) {
			File[] files = pom.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					File current = new File(dir.getAbsolutePath() + File.separator + name);
					return current.isDirectory() || current.getAbsolutePath().endsWith("pom.xml");
				}
			});

			if (files == null || files.length == 0) {
				return null;
			} else {
				pom = files[0];
			}
		}
		DefaultMutableTreeNode currentDir = new DefaultMutableTreeNode("");
		try {
			Model model = new MavenXpp3Reader().read(new FileReader(pom));
			// pom file is sometimes null <.<
			model.setPomFile(pom);

			currentDir = new DefaultMutableTreeNode(new ModelWrapper(model));

			if (curTop != null) {
				curTop.add(currentDir);
			}

			List<String> modules = model.getModules();

			if (modules != null && modules.isEmpty() == false) {
				for (String module : modules) {
					File modulePom = new File(pom.getParent() + File.separator + module + File.separator + "pom.xml");
					addNodes(currentDir, modulePom);
				}
			}

		} catch (IOException | XmlPullParserException e) {

		}

		return currentDir;
	}

	public boolean isApplied() {
		return applied;
	}

	public List<File> getChosenFiles() {
		List<File> fileList = new ArrayList<>();
		Collection<TreePath> collection = treeManager.getAllSelectedItems();

		for (TreePath path : collection) {
			Object obj = path.getLastPathComponent();
			if (obj instanceof DefaultMutableTreeNode) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) obj;
				Object userObj = node.getUserObject();
				if (userObj instanceof ModelWrapper) {
					ModelWrapper wrapper = (ModelWrapper) userObj;
					Model m = wrapper.getModel();
					String packaging = m.getPackaging();
					if (packaging == null || (packaging.trim().toLowerCase().equals("pom") == false)) {
						fileList.add(m.getPomFile());
					}
				}
			}
		}

		return fileList;
	}

	private class FileTreeFilterMatcher implements IFilterMatcher {

		private String root;

		public FileTreeFilterMatcher(String root) {
			this.root = root;
		}

		@Override
		public boolean matches(Object o, String filter) {
			ModelWrapper f = (ModelWrapper) o;

			String filePath = f.getModel().getPomFile().getAbsolutePath();
			filePath = filePath.replace(root, "");
			filePath = filePath.replace("pom.xml", "");

			return filePath.toLowerCase().contains(filter.toLowerCase());
		}
	}

	private class ModelWrapper {
		private Model model;

		public ModelWrapper(Model model) {
			this.model = model;
		}

		public Model getModel() {
			return model;
		}

		@Override
		public String toString() {
			return model.getArtifactId();
		}
	}
}
