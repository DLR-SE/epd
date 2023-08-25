package de.emir.epd.nmeasensor.settings;

import java.awt.Component;

import javax.swing.JPanel;

import de.emir.rcp.settings.AbstractSettingsPage;
import javax.swing.JLabel;

public class SensorsInfoPage extends AbstractSettingsPage {
	private JPanel infoPanel;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component fillPage() {
		infoPanel = new JPanel();
		
		JLabel lblCreateAndModify = new JLabel("Create and modify sensors");
		infoPanel.add(lblCreateAndModify);
		return infoPanel;
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void finish() {

	}

}
