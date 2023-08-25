package de.emir.rcp.other.navtool.ui.maneuver;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class BearingPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Create the panel.
	 */
	public BearingPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTime = new JLabel("Time");
		GridBagConstraints gbc_lblTime = new GridBagConstraints();
		gbc_lblTime.anchor = GridBagConstraints.EAST;
		gbc_lblTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime.gridx = 0;
		gbc_lblTime.gridy = 0;
		add(lblTime, gbc_lblTime);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.gridwidth = 3;
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 0;
		add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel lblTargetCourse = new JLabel("Target Course");
		GridBagConstraints gbc_lblTargetCourse = new GridBagConstraints();
		gbc_lblTargetCourse.insets = new Insets(0, 0, 5, 5);
		gbc_lblTargetCourse.anchor = GridBagConstraints.EAST;
		gbc_lblTargetCourse.gridx = 4;
		gbc_lblTargetCourse.gridy = 0;
		add(lblTargetCourse, gbc_lblTargetCourse);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 5;
		gbc_textField_5.gridy = 0;
		add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		JLabel lblOwnCourse = new JLabel("Own Course");
		GridBagConstraints gbc_lblOwnCourse = new GridBagConstraints();
		gbc_lblOwnCourse.anchor = GridBagConstraints.EAST;
		gbc_lblOwnCourse.insets = new Insets(0, 0, 5, 5);
		gbc_lblOwnCourse.gridx = 0;
		gbc_lblOwnCourse.gridy = 1;
		add(lblOwnCourse, gbc_lblOwnCourse);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTargetBearing = new JLabel("Target Bearing");
		GridBagConstraints gbc_lblTargetBearing = new GridBagConstraints();
		gbc_lblTargetBearing.insets = new Insets(0, 0, 5, 5);
		gbc_lblTargetBearing.anchor = GridBagConstraints.EAST;
		gbc_lblTargetBearing.gridx = 2;
		gbc_lblTargetBearing.gridy = 1;
		add(lblTargetBearing, gbc_lblTargetBearing);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblTargetSpeed = new JLabel("Target Speed");
		GridBagConstraints gbc_lblTargetSpeed = new GridBagConstraints();
		gbc_lblTargetSpeed.anchor = GridBagConstraints.EAST;
		gbc_lblTargetSpeed.insets = new Insets(0, 0, 5, 5);
		gbc_lblTargetSpeed.gridx = 4;
		gbc_lblTargetSpeed.gridy = 1;
		add(lblTargetSpeed, gbc_lblTargetSpeed);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 5;
		gbc_textField_6.gridy = 1;
		add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		JLabel lblOwnSpeed = new JLabel("Own Speed");
		GridBagConstraints gbc_lblOwnSpeed = new GridBagConstraints();
		gbc_lblOwnSpeed.anchor = GridBagConstraints.EAST;
		gbc_lblOwnSpeed.insets = new Insets(0, 0, 0, 5);
		gbc_lblOwnSpeed.gridx = 0;
		gbc_lblOwnSpeed.gridy = 2;
		add(lblOwnSpeed, gbc_lblOwnSpeed);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 0, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblTargetDistance = new JLabel("Target Distance");
		GridBagConstraints gbc_lblTargetDistance = new GridBagConstraints();
		gbc_lblTargetDistance.anchor = GridBagConstraints.EAST;
		gbc_lblTargetDistance.insets = new Insets(0, 0, 0, 5);
		gbc_lblTargetDistance.gridx = 2;
		gbc_lblTargetDistance.gridy = 2;
		add(lblTargetDistance, gbc_lblTargetDistance);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 0, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 2;
		add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblCpa = new JLabel("CPA");
		GridBagConstraints gbc_lblCpa = new GridBagConstraints();
		gbc_lblCpa.anchor = GridBagConstraints.EAST;
		gbc_lblCpa.insets = new Insets(0, 0, 0, 5);
		gbc_lblCpa.gridx = 4;
		gbc_lblCpa.gridy = 2;
		add(lblCpa, gbc_lblCpa);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 5;
		gbc_textField_7.gridy = 2;
		add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);

	}

}
