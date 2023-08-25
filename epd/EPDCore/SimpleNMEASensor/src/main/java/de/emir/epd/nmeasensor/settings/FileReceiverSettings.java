package de.emir.epd.nmeasensor.settings;

import de.emir.epd.nmeasensor.data.ReceiverProperty;
import de.emir.epd.nmeasensor.ids.NMEASensorIds;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FileReceiverSettings extends AbstractReceiverSettings {
	private JTextField filenameTextField;
	private JSpinner delaySpinner;
	private JSpinner repeatSpinner;
	private SpinnerModel model;
	private SpinnerModel model2;
	private JFileChooser fileChooser;
	private JButton browseButton;

	public FileReceiverSettings(NMEASensorSettingsPage caller) {
		this.caller = caller;
		setPreferredSize(new Dimension(180, 120));
		PropertyContext context = PropertyStore.getContext(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT);
		
		fileChooser = new JFileChooser();
		
		JLabel lblFilename = new JLabel("Filename");
		lblFilename.setHorizontalAlignment(SwingConstants.TRAILING);

		filenameTextField = new JTextField();
		filenameTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				readValues();
			}
		});
		filenameTextField.setColumns(10);
		
		browseButton = new JButton("...");

		JLabel lblDelay = new JLabel("Delay");
		lblDelay.setHorizontalAlignment(SwingConstants.TRAILING);

		delaySpinner = new JSpinner();
		delaySpinner.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				readValues();
			}
		});

		JLabel lblRepeat = new JLabel("Repeat");
		lblRepeat.setHorizontalAlignment(SwingConstants.TRAILING);

		repeatSpinner = new JSpinner();
		repeatSpinner.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				readValues();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDelay)
						.addComponent(lblRepeat)
						.addComponent(lblFilename))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(filenameTextField)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(browseButton))
						.addComponent(delaySpinner, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
						.addComponent(repeatSpinner, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
					.addGap(8))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(filenameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(browseButton)
						.addComponent(lblFilename))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(delaySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDelay))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRepeat)
						.addComponent(repeatSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(192))
		);
		setLayout(groupLayout);
	}
	
	public void readValues() {
		receiverProperty.getAttributes().put(NMEASensorIds.NMEA_SENSOR_PROP_FILENAME, filenameTextField.getText());
		receiverProperty.getAttributes().put(NMEASensorIds.NMEA_SENSOR_PROP_DELAY, (int) model.getValue());
		receiverProperty.getAttributes().put(NMEASensorIds.NMEA_SENSOR_PROP_REPEAT, (int) model2.getValue());
		dirtyFlag = true;
		caller.readValues();
	}
	
	public void init(ReceiverProperty receiverProperty) {
		dirtyFlag = false;
		if (receiverProperty == null) return;
		this.receiverProperty = receiverProperty;
		filenameTextField.setText((String) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_FILENAME, "nmea.txt"));
		filenameTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {

			}

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				readValues();
			}
		});
		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(delaySpinner, "#");
		editor.getFormat().setGroupingUsed(false);
		delaySpinner.setEditor(editor);
		model = new SpinnerNumberModel((int) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_DELAY, 1000), 1, 256000, 1);
		delaySpinner.setModel(model);
		delaySpinner.setValue((int) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_DELAY, 1000));
		model.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				readValues();
			}
		});
		JSpinner.NumberEditor editor2 = new JSpinner.NumberEditor(repeatSpinner, "#");
		editor2.getFormat().setGroupingUsed(false);
		repeatSpinner.setEditor(editor2);
		model2 = new SpinnerNumberModel((int) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_REPEAT, -1), -1, 65535, 1);
		repeatSpinner.setModel(model2);
		repeatSpinner.setValue((int) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_REPEAT, -1));
		model2.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				readValues();
			}
		});
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileChooser.showOpenDialog(FileReceiverSettings.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					filenameTextField.setText(file.getAbsolutePath());
					readValues();
				} else {
					// do nothing
				}
			}
		});
	}
}
