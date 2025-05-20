package de.emir.rcp.pluginmanager.views.dialogs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.emir.rcp.pluginmanager.views.PluginRenderUtil;
import de.emir.tuml.runtime.epf.ObservableDependency;
import de.emir.tuml.runtime.epf.ProductFile;

public class UpdateVersionsDialog extends JDialog {

	private static final long serialVersionUID = -2315770391175489012L;

	private ProductFile pf;
	private JButton btnOk;
	private JComboBox<String> groupText;
	private JTextField versionText;
	private String group;
	private String version;

	public UpdateVersionsDialog(Window parent) {
		this(parent, null);

	}

	/**
	 * @wbp.parser.constructor
	 */
	public UpdateVersionsDialog(Window parent, ProductFile pf) {
		super(parent);
		this.pf = pf;
		setModal(true);

		DocumentListener inputListener = new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				validateInputs();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				validateInputs();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				validateInputs();
			}
		};

		setIconImage(PluginRenderUtil.EDIT_ICON_IMAGE);
		setMinimumSize(new Dimension(400, 180));

		GridBagLayout gridBagLayout = new GridBagLayout();

		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblHint = new JLabel("Update all dependencies with the same groupId to the given version:");
		GridBagConstraints gbc_lblHint = new GridBagConstraints();
		gbc_lblHint.fill = GridBagConstraints.VERTICAL;
		gbc_lblHint.gridwidth = 3;
		gbc_lblHint.insets = new Insets(0, 0, 5, 5);
		gbc_lblHint.gridx = 0;
		gbc_lblHint.gridy = 0;
		getContentPane().add(lblHint, gbc_lblHint);

		JLabel lblId = new JLabel("<groupId>");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.EAST;
		gbc_lblId.insets = new Insets(10, 10, 5, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 1;
		getContentPane().add(lblId, gbc_lblId);

		DefaultComboBoxModel model = new DefaultComboBoxModel(new String[] {});
		groupText = new JComboBox<String>();
		groupText.setEditable(true);
		GridBagConstraints gbc_groupText = new GridBagConstraints();
		gbc_groupText.insets = new Insets(10, 0, 5, 5);
		gbc_groupText.fill = GridBagConstraints.HORIZONTAL;
		gbc_groupText.gridx = 1;
		gbc_groupText.gridy = 1;
		getContentPane().add(groupText, gbc_groupText);
		groupText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (versionText.getText().isEmpty() || versionText.getText().isBlank()) {
					for (ObservableDependency dep : pf.getDependencies()) {
						if (dep.getGroupId().equals(model.getSelectedItem())) {
							versionText.setText(dep.getVersion());
						}
					}
				}
				validateInputs();
			}
		});

		JLabel label = new JLabel("</groupId>");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(10, 0, 5, 10);
		gbc_label.gridx = 2;
		gbc_label.gridy = 1;
		getContentPane().add(label, gbc_label);

		JLabel lblNewLabel = new JLabel("<version>");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		versionText = new JTextField();
		GridBagConstraints gbc_versionText = new GridBagConstraints();
		gbc_versionText.insets = new Insets(0, 0, 5, 5);
		gbc_versionText.fill = GridBagConstraints.HORIZONTAL;
		gbc_versionText.gridx = 1;
		gbc_versionText.gridy = 2;
		getContentPane().add(versionText, gbc_versionText);
		versionText.setColumns(10);
		versionText.getDocument().addDocumentListener(inputListener);
		JLabel label_2 = new JLabel("</version>");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 10);
		gbc_label_2.gridx = 2;
		gbc_label_2.gridy = 2;
		getContentPane().add(label_2, gbc_label_2);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		getContentPane().add(panel_1, gbc_panel_1);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(5, 5, 10, 10);
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.gridwidth = 3;
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JButton btnExit = new JButton("Cancel");
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.insets = new Insets(0, 0, 0, 5);
		gbc_btnExit.gridx = 0;
		gbc_btnExit.gridy = 0;
		panel.add(btnExit, gbc_btnExit);

		btnOk = new JButton("   OK   ");
		btnOk.setEnabled(false);
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 0;
		panel.add(btnOk, gbc_btnOk);

		setLocationRelativeTo(parent);

		btnOk.addActionListener(e -> handleOk());
		btnExit.addActionListener(e -> handleExit());

		setTitle("Update Dependency Versions...");

		Set<String> groups = new HashSet<>();
		for (ObservableDependency dep : pf.getDependencies()) {
			groups.add(dep.getGroupId());
			versionText.setText(dep.getVersion());
		}
		for (String item : groups) {
			groupText.addItem(item);
		}
	}

	protected void validateInputs() {
		boolean valid = true;

		if (groupText.getSelectedItem() == null) {
			valid = false;
		}
		if (versionText.getText().isEmpty() || versionText.getText().isBlank()) {
			valid = false;
		}

		btnOk.setEnabled(valid);
	}

	private void handleOk() {
		group = (String) groupText.getSelectedItem();
		version = versionText.getText();

		setVisible(false);
	}

	public String getGroup() {
		return group;
	}

	public String getVersion() {
		return version;
	}

	private void handleExit() {
		setVisible(false);
	}

}
