package de.emir.rcp.settings;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import de.emir.rcp.ui.utils.properties.PropertyFolderChooserWidget;
import de.emir.rcp.views.workspace.WorkspaceView;

public class WorkspaceSettingsPage extends AbstractSettingsPage {

	private PropertyFolderChooserWidget pathWidget;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component fillPage() {

		JPanel p = new JPanel();
		GridBagLayout gbl_p = new GridBagLayout();

		gbl_p.columnWeights = new double[] { 1.0 };
		gbl_p.rowWeights = new double[] { 1.0 };
		p.setLayout(gbl_p);

		pathWidget = new PropertyFolderChooserWidget("Path", WorkspaceView.PROPERTY_CONTEXT,
				WorkspaceView.WORKSPACE_LOCATION_PROPERTY, WorkspaceView.WORKSPACE_LOCATION_PROPERTY_DEFAULT);

		GridBagConstraints gbc_pathWidget = new GridBagConstraints();
		gbc_pathWidget.fill = GridBagConstraints.HORIZONTAL;
		gbc_pathWidget.anchor = GridBagConstraints.NORTH;
		gbc_pathWidget.gridx = 0;
		gbc_pathWidget.gridy = 0;
		p.add(pathWidget, gbc_pathWidget);
		return p;
	}

	@Override
	public boolean isDirty() {
		return pathWidget.isDirty();
	}

	@Override
	public void finish() {
		pathWidget.finish();
	}

}
