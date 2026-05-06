package de.emir.epd.routemanager.cmd.popup;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.emir.epd.routemanager.IRouteManager.IRouteAccessModel;
import de.emir.rcp.ui.wizard.AbstractWizard;
import de.emir.rcp.ui.wizard.AbstractWizardPage;

/**
 * ImportRouteClipboardCmdPage main page of ImportRouteClipboardCmdWizard.
 * Import target for route is selected (e.g. import in unassigned routes, or any other route collection (RouteAccessModel) available in the route manager
 */
public class ImportRouteClipboardCmdPage extends AbstractWizardPage {
	private static final long serialVersionUID = 1765464186; // Override
	
	private List<IRouteAccessModel> mRouteAccessModels;
	private IRouteAccessModel mSelectedTarget;

	public ImportRouteClipboardCmdPage(AbstractWizard wizard, List<IRouteAccessModel> routeAccessModels, String clipboardContent) {
		super(wizard, false);
		mRouteAccessModels = routeAccessModels;

		// Layout
		setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 16, 86, 67, 0 };
		gbl_panel.rowHeights = new int[] { 0, 23, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		// Route Label
		JLabel lblTargetFeature = new JLabel("Route Folder");
		GridBagConstraints gbc_lblTargetFeature = new GridBagConstraints();
		gbc_lblTargetFeature.anchor = GridBagConstraints.EAST;
		gbc_lblTargetFeature.insets = new Insets(0, 0, 5, 5);
		gbc_lblTargetFeature.gridx = 0;
		gbc_lblTargetFeature.gridy = 0;
		panel.add(lblTargetFeature, gbc_lblTargetFeature);

		// Dropdown box for route target selections
		JComboBox<IRouteAccessModel> comboBox = new JComboBox();
		IRouteAccessModel[] containers = new IRouteAccessModel[mRouteAccessModels.size()];
		mRouteAccessModels.toArray(containers);
		comboBox.setModel(new DefaultComboBoxModel<>(containers));
		comboBox.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (value instanceof IRouteAccessModel) {
					IRouteAccessModel obj = (IRouteAccessModel) value;
					setText(obj.getName());
					setToolTipText(obj.getDescription());
				}
				return c;
			}
		});
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectTarget(comboBox.getModel().getElementAt(comboBox.getSelectedIndex()));
			}
		});
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel.add(comboBox, gbc_comboBox);
		comboBox.setSelectedIndex(0);

		// Display of clipboard content
		JLabel lblClipboardContent = new JLabel("Clipboard Content:");
		GridBagConstraints gbc_lblClipboardContent = new GridBagConstraints();
		gbc_lblClipboardContent.anchor = GridBagConstraints.EAST;
		gbc_lblClipboardContent.insets = new Insets(0, 0, 5, 5);
		gbc_lblClipboardContent.gridx = 0;
		gbc_lblClipboardContent.gridy = 1;
		panel.add(lblClipboardContent, gbc_lblClipboardContent);
		JTextArea cpTextBox = new JTextArea(15, 1);
		cpTextBox.setEditable(false);
		cpTextBox.setText(clipboardContent);
		JScrollPane scrollClipBoardTextArea = new JScrollPane(cpTextBox, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_textBox = new GridBagConstraints();
		gbc_textBox.gridwidth = 3;
		gbc_textBox.insets = new Insets(0, 0, 5, 5);
		gbc_textBox.gridx = 0;
		gbc_textBox.gridy = 2;
		gbc_textBox.fill = GridBagConstraints.BOTH;
		panel.add(scrollClipBoardTextArea, gbc_textBox);
	}

	@Override
	public boolean isNextAvailable() {
		if (mSelectedTarget == null)
			return false;
		return true;
	}

	@Override
	public boolean isFinishAvailable() {
		return isNextAvailable();
	}

	protected void selectTarget(IRouteAccessModel selectedItem) {
		mSelectedTarget = selectedItem;
		getWizard().updateButtons();
	}

	public IRouteAccessModel getSelectedTarget() {
		return mSelectedTarget;
	}

}
