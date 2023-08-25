package de.emir.rcp.properties.ui.editors;

import java.awt.Component;

import javax.swing.JPanel;

import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.rcp.manager.PropertyManager;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PoseEditor extends AbstractPropertyEditor<Pose> {
	private JPanel mPanel;

	@Override
	public void dispose() {

	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	protected Component createComponent() {
		if (mPanel == null) {
			mPanel = new JPanel();
			
			PropertyManager pmgr = PropertyManager.getInstance();
			Pose p = getValue();
			if (p.getCoordinate() == null) p.setCoordinate(new CoordinateImpl());
			if (p.getOrientation() == null) p.setOrientation(new EulerImpl(0, 0, 0, AngleUnit.DEGREE)); //Prefer euler as this is readable by a human
			
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			mPanel.setLayout(gbl_panel);
			
			JLabel lblCoordinate = new JLabel("Coordinate");
			GridBagConstraints gbc_lblCoordinate = new GridBagConstraints();
			gbc_lblCoordinate.anchor = GridBagConstraints.EAST;
			gbc_lblCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoordinate.gridx = 0;
			gbc_lblCoordinate.gridy = 0;
			mPanel.add(lblCoordinate, gbc_lblCoordinate);
			
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.gridwidth = 2;
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 0;
			mPanel.add(pmgr.getEditorComponent(p, SpatialPackage.Literals.Pose_coordinate), gbc_textField);
			
			JLabel lblOrientation = new JLabel("Orientation");
			GridBagConstraints gbc_lblOrientation = new GridBagConstraints();
			gbc_lblOrientation.anchor = GridBagConstraints.EAST;
			gbc_lblOrientation.insets = new Insets(0, 0, 0, 5);
			gbc_lblOrientation.gridx = 0;
			gbc_lblOrientation.gridy = 1;
			mPanel.add(lblOrientation, gbc_lblOrientation);
			
	
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.gridwidth = 2;
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 1;
			mPanel.add(pmgr.getEditorComponent(p, SpatialPackage.Literals.Pose_orientation), gbc_textField_1);
		}
		return mPanel;
	}

}
