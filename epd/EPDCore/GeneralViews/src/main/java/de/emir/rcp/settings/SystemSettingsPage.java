package de.emir.rcp.settings;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import de.emir.rcp.commands.basics.ExitCommand;
import de.emir.rcp.ui.utils.properties.PropertyCheckboxWidget;

public class SystemSettingsPage extends AbstractSettingsPage {

	private PropertyCheckboxWidget showConfirm;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component fillPage() {

		JPanel p = new JPanel();
		GridBagLayout gbl_p = new GridBagLayout();

		gbl_p.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_p.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		p.setLayout(gbl_p);

		showConfirm = new PropertyCheckboxWidget("Show Exit Confirm Dialog",
				ExitCommand.EXIT_COMMAND_ShowDialog_Context, ExitCommand.EXIT_COMMAND_ShowDialog_Property, true);

		GridBagConstraints gbc_showConfirm = new GridBagConstraints();
		gbc_showConfirm.anchor = GridBagConstraints.NORTH;
		gbc_showConfirm.fill = GridBagConstraints.HORIZONTAL;
		gbc_showConfirm.gridx = 0;
		gbc_showConfirm.gridy = 0;
		p.add(showConfirm, gbc_showConfirm);

		return p;
	}

	@Override
	public boolean isDirty() {
		return showConfirm.isDirty();
	}

	@Override
	public void finish() {
		showConfirm.finish();
	}

}
