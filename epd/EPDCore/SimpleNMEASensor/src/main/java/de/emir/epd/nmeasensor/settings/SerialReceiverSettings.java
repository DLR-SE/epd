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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SerialReceiverSettings extends AbstractReceiverSettings {
	private JTextField serialportTextField;
	private JSpinner baudrateSpinner;
	private JSpinner packetsizeSpinner;
	private SpinnerModel model;
	private SpinnerModel model2;
	private Object initialSerialPort;
	private int initialBaudrate;
	private int initialPacketSize;
	private boolean initialServer;
	private JCheckBox chckbxAllowOutput;

	public SerialReceiverSettings(NMEASensorSettingsPage caller) {
		this.caller = caller;
		setPreferredSize(new Dimension(180, 120));
		PropertyContext context = PropertyStore.getContext(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT);

		JLabel lblLocalInterface = new JLabel("Serial Port");
		lblLocalInterface.setHorizontalAlignment(SwingConstants.TRAILING);

		serialportTextField = new JTextField();
		serialportTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				readValues();
			}
		});
		serialportTextField.setColumns(10);

		packetsizeSpinner = new JSpinner();
		packetsizeSpinner.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				readValues();
			}
		});

		JLabel lblPort = new JLabel("Baudrate");
		lblPort.setHorizontalAlignment(SwingConstants.TRAILING);

		baudrateSpinner = new JSpinner();
		baudrateSpinner.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				readValues();
			}
		});

		JLabel lblMaxPacketsize = new JLabel("Max. Packetsize");
		lblMaxPacketsize.setHorizontalAlignment(SwingConstants.TRAILING);
		
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
						.addComponent(chckbxAllowOutput, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMaxPacketsize)
								.addComponent(lblPort)
								.addComponent(lblLocalInterface))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(baudrateSpinner, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
								.addComponent(packetsizeSpinner, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
								.addComponent(serialportTextField, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))))
					.addGap(8))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLocalInterface)
						.addComponent(serialportTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(baudrateSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPort))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaxPacketsize)
						.addComponent(packetsizeSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxAllowOutput)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

	public void readValues() {
		receiverProperty.getAttributes().put(NMEASensorIds.NMEA_SENSOR_PROP_SERIALPORT, serialportTextField.getText());
		receiverProperty.getAttributes().put(NMEASensorIds.NMEA_SENSOR_PROP_BAUDRATE, (int) model.getValue());
		receiverProperty.getAttributes().put(NMEASensorIds.NMEA_SENSOR_PROP_PACKETSIZE, (int) model2.getValue());
		receiverProperty.getAttributes().put(NMEASensorIds.NMEA_SENSOR_PROP_SERVER, chckbxAllowOutput.isSelected());
		dirtyFlag = (!initialSerialPort.equals(serialportTextField.getText())
				|| initialBaudrate != (int) model.getValue() || initialPacketSize != (int) model2.getValue()
				|| initialServer != chckbxAllowOutput.isSelected());
		if (dirtyFlag) {
			caller.readValues();
		}
	}

	public void init(ReceiverProperty receiverProperty) {
		dirtyFlag = false;
		if (receiverProperty == null)
			return;
		this.receiverProperty = receiverProperty;
		serialportTextField.setText(
				(String) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_SERIALPORT, "COM1"));
		initialSerialPort = serialportTextField.getText();
		serialportTextField.getDocument().addDocumentListener(new DocumentListener() {
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
		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(baudrateSpinner, "#");
		editor.getFormat().setGroupingUsed(false);
		baudrateSpinner.setEditor(editor);
		model = new SpinnerNumberModel(
				(int) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_BAUDRATE, 9600), 1,
				256000, 1);
		baudrateSpinner.setModel(model);
		baudrateSpinner.setValue(
				(int) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_BAUDRATE, 9600));
		initialBaudrate = (int) model.getValue();
		model.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				readValues();
			}
		});
		JSpinner.NumberEditor editor2 = new JSpinner.NumberEditor(packetsizeSpinner, "#");
		editor2.getFormat().setGroupingUsed(false);
		packetsizeSpinner.setEditor(editor2);
		model2 = new SpinnerNumberModel(
				(int) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_PACKETSIZE, 65535),
				1, 65535, 1);
		packetsizeSpinner.setModel(model2);
		packetsizeSpinner.setValue(
				(int) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_PACKETSIZE, 65535));
		initialPacketSize = (int) model2.getValue();
		model2.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				readValues();
			}
		});
		chckbxAllowOutput.setSelected((boolean) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_SERVER, false));
		initialServer = chckbxAllowOutput.isSelected();
		chckbxAllowOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				readValues();
			}
		});
	}
}
