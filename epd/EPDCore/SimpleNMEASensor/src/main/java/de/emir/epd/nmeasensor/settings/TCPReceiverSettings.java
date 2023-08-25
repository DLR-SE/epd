package de.emir.epd.nmeasensor.settings;

import de.emir.epd.nmeasensor.data.ReceiverProperty;
import de.emir.epd.nmeasensor.ids.NMEASensorIds;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TCPReceiverSettings extends AbstractReceiverSettings {
	private JSpinner portSpinner;
	private JTextField hostTextField;
	private SpinnerModel model;
	private JCheckBox chckbxAllowOutput;
	private int initialPort;
	private boolean initialServer;
	private String initialHost;
	
	public TCPReceiverSettings(NMEASensorSettingsPage caller) {
		this.caller = caller;
		setPreferredSize(new Dimension(180, 104));
		setBorder(new EmptyBorder(8, 8, 8, 8));
		PropertyContext context = PropertyStore.getContext(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT);
		JLabel hostLabel = new JLabel();
		hostLabel.setHorizontalAlignment(SwingConstants.CENTER);

		hostLabel.setText("Host");
		hostTextField = new JTextField(10);
		hostTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				readValues();
			}
		});

		JLabel lblPort = new JLabel("Port");

		portSpinner = new JSpinner();
		portSpinner.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				readValues();
			}
		});
		
		chckbxAllowOutput = new JCheckBox("Allow Output");
		chckbxAllowOutput.addFocusListener(new FocusAdapter() {
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
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblPort)
								.addComponent(hostLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(portSpinner, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
								.addComponent(hostTextField, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
							.addGap(8))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(chckbxAllowOutput, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
							.addGap(14))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(hostLabel)
						.addComponent(hostTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(portSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPort))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxAllowOutput)
					.addContainerGap(11, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

	public void readValues() {
		receiverProperty.getAttributes().put(NMEASensorIds.NMEA_SENSOR_PROP_HOST, hostTextField.getText());
		receiverProperty.getAttributes().put(NMEASensorIds.NMEA_SENSOR_PROP_PORT, (int) model.getValue());
		receiverProperty.getAttributes().put(NMEASensorIds.NMEA_SENSOR_PROP_SERVER, chckbxAllowOutput.isSelected());
		receiverProperty.setOutput(chckbxAllowOutput.isSelected());
		dirtyFlag = (!initialHost.equals(hostTextField.getText()) || initialPort != (int) model.getValue()
				|| initialServer != chckbxAllowOutput.isSelected());
		if (dirtyFlag) {
        	caller.readValues();
        }
	}
	
	public void init(ReceiverProperty receiverProperty) {
		dirtyFlag = false;
		if (receiverProperty == null) return;
		this.receiverProperty = receiverProperty;
		hostTextField.setText((String) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_HOST, "127.0.0.1"));
		initialHost = hostTextField.getText();
		hostTextField.getDocument().addDocumentListener(new DocumentListener() {
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
		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(portSpinner, "#");
		editor.getFormat().setGroupingUsed(false);
		portSpinner.setEditor(editor);
		model = new SpinnerNumberModel((int) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_PORT, 16100), 1, 65535, 1);
		portSpinner.setModel(model);
		portSpinner.setValue((int) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_PORT, 16100));
		initialPort = (int) model.getValue();
		model.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				readValues();
			}
		});
		chckbxAllowOutput.setSelected((boolean) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_SERVER, false));
		receiverProperty.setOutput(chckbxAllowOutput.isSelected());
		initialServer = chckbxAllowOutput.isSelected();
		chckbxAllowOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				readValues();
			}
		});
	}
}
