package de.emir.rcp.wizards;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.google.common.io.Files;

import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.views.workspace.FileTree;
import de.emir.rcp.views.workspace.IWorkspaceRefreshCallback;
import de.emir.rcp.views.workspace.WorkspaceView;
import de.emir.rcp.wizards.types.AbstractNewFileWizardPage;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.utils.FileOperations;

public class NewFileWizardNamePage extends AbstractNewFileWizardPage {

	private static final long serialVersionUID = 2874394484063757797L;
	private JTextField textField;
	private FileTree tree;
	protected Object selection;
	private NewFileWizardModel model;
	private boolean inputValid;
	private JLabel subtitleLabel;
	private String basicSubtitle;
	private Icon normalIcon = IconManager.getIcon(this, "icons/emiricons/32/file.png", IconManager.preferedSmallIconSize());
	private Icon errorIcon = IconManager.getIcon(this, "icons/emiricons/32/dangerous.png", IconManager.preferedSmallIconSize());
	private JLabel lblFile;
	
	private String lblFileText = "Create ";

	public NewFileWizardNamePage(NewFileWizard wizard, NewFileWizardModel model) {
		super(wizard, model);
		
		this.model = model;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.insets = new Insets(10, 5, 5, 0);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblFile = new JLabel();
		lblFile.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblFile = new GridBagConstraints();
		gbc_lblFile.anchor = GridBagConstraints.WEST;
		gbc_lblFile.gridx = 0;
		gbc_lblFile.gridy = 0;
		panel.add(lblFile, gbc_lblFile);
		
		basicSubtitle = "Create a new file resource";
		subtitleLabel = new JLabel(basicSubtitle);
		subtitleLabel.setIcon(normalIcon);
		
		GridBagConstraints gbc_lblCreateANew = new GridBagConstraints();
		gbc_lblCreateANew.anchor = GridBagConstraints.WEST;
		gbc_lblCreateANew.insets = new Insets(0, 0, 5, 0);
		gbc_lblCreateANew.gridx = 0;
		gbc_lblCreateANew.gridy = 1;
		panel.add(subtitleLabel, gbc_lblCreateANew);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.ipady = 2;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		add(separator, gbc_separator);
		
		tree = new FileTree(JFileChooser.DIRECTORIES_ONLY);
		
		
		JScrollPane scrollPane = new JScrollPane(tree);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 5, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 5, 30, 5);
		gbc_panel_1.anchor = GridBagConstraints.SOUTH;
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel = new JLabel("Name:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);

		PropertyContext propCont = PropertyStore.getContext(WorkspaceView.PROPERTY_CONTEXT);
		IProperty<?> workspaceProperty = propCont.getProperty(WorkspaceView.WORKSPACE_LOCATION_PROPERTY, WorkspaceView.WORKSPACE_LOCATION_PROPERTY_DEFAULT);
		File workspaceFolder = new File((String) workspaceProperty.getValue());
		
		try {
			tree.setRootLocation(workspaceFolder, new IWorkspaceRefreshCallback() {
				
				@Override
				public void finished() {
					
					// Tree loaded, select folder
					
					Object currentSelection = PlatformUtil.getSelectionManager().getFirstSelectedObject();
					
					if(currentSelection instanceof File == false) {
						return;
					}
					
					File selectedFile = (File) currentSelection;
					
					if(selectedFile.isFile()) {
						selectedFile = selectedFile.getParentFile();
					}
					
					if(selectedFile.isDirectory() == false) {
						return;
					}
					
					
					model.setFolder(selectedFile.getAbsolutePath());
					selection = selectedFile;
					tree.setSelection(selectedFile);
					
					textField.requestFocusInWindow();
					
				}
			});

		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
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
				
				validateInputs();
				getWizard().updateButtons();
				
			}
		});
		
		textField.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				validateInputs();
				getWizard().updateButtons();
			}
			
		});

		
	}
	
	protected void validateInputs() {

		inputValid = false;
		
		if(selection instanceof File == false) {
			
			setErrorMessage("Select a target directory");
			return;
		}
		
		if(((File)selection).isDirectory() == false || ((File)selection).exists() == false) {
			
			setErrorMessage("Select a target directory");
			return;
		}
		
		String fileName = textField.getText();
		
		if(fileName.trim().isEmpty()) {
			setErrorMessage("Enter a valid file name");
			return;
		}
		
		File f = null;

		try {
			f = new File(((File)selection).getAbsolutePath() + File.separator + textField.getText());
		} catch (Exception e) {
			setErrorMessage("Invalid file state");
			return;
		}
		
		if(FileOperations.isValidWindowsFileName(f) == false) {
			setErrorMessage("Invalid characters in file name");
			return;
		}
		
		if(f.exists() == true) {
			setErrorMessage("File already exists");
			return;
		}
		
		String extension = Files.getFileExtension(f.getAbsolutePath());
		
		AbstractNewFileWizardTypeInformation typeInstance = model.getSelectedTypeInstance();
		String ext = typeInstance.getFileExtension();
		
		
		
		if(ext != null && ext.equals(extension) == false) {
			
			setErrorMessage("Filename has to end with ." + ext);
			return;
		}

		inputValid = true;
		setErrorMessage(null);

		model.setFileName(fileName);
		model.setFolder(((File)selection).getAbsolutePath());		
	}
	
	
	private void setErrorMessage(String message) {
		
		if(message == null) {
			subtitleLabel.setText(basicSubtitle);
			subtitleLabel.setIcon(normalIcon);
		} else {
			subtitleLabel.setText(message);
			subtitleLabel.setIcon(errorIcon);
		}
		subtitleLabel.getParent().repaint();
	}

	@Override
	public void onEnter() {
		textField.setText(model.getFileName());

		validateInputs();
		getWizard().updateButtons();
		
		lblFile.setText(lblFileText + model.getSelectedType().getLabel());
		

	}
	
	@Override
	public boolean isNextAvailable() {
		return inputValid;
	}
	
	@Override
	public boolean isFinishAvailable() {
		return inputValid && (model.getSelectedTypeInstance().getPages() == null || model.getSelectedTypeInstance().getPages().isEmpty()) ;
	}

}
