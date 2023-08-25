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

public class UDPReceiverSettings extends AbstractReceiverSettings {
	private JTextField interfaceTextField;
	private JSpinner portSpinner;
	private JSpinner packetsizeSpinner;
	private SpinnerModel model;
	private SpinnerModel model2;
    private String initialHost;
    private int initialPort;
    private int initialPacketSize;
	private boolean initialServer;
	private JCheckBox chckbxAllowOutput;

	public UDPReceiverSettings(NMEASensorSettingsPage caller) {
		this.caller = caller;
		setPreferredSize(new Dimension(180, 126));
		PropertyContext context = PropertyStore.getContext(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT);
		JLabel lblLocalInterface = new JLabel("Local Interface");
		lblLocalInterface.setHorizontalAlignment(SwingConstants.TRAILING);

		interfaceTextField = new JTextField();
		interfaceTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				readValues();
			}
		});
		interfaceTextField.setColumns(10);

		JLabel lblPort = new JLabel("Port");
		lblPort.setHorizontalAlignment(SwingConstants.TRAILING);

		portSpinner = new JSpinner();
		portSpinner.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				readValues();
			}
		});
		
		JLabel lblMaxPacketsize = new JLabel("Max. Packetsize");
		lblMaxPacketsize.setHorizontalAlignment(SwingConstants.TRAILING);

		packetsizeSpinner = new JSpinner();
		packetsizeSpinner.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				readValues();
			}
		});

		chckbxAllowOutput = new JCheckBox();
		chckbxAllowOutput.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				readValues();
			}
		});
		chckbxAllowOutput.setText("Server");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblMaxPacketsize).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(packetsizeSpinner, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
								.addGroup(
										groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblLocalInterface, GroupLayout.PREFERRED_SIZE, 80,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblPort))
												.addGap(12)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(portSpinner, GroupLayout.DEFAULT_SIZE, 80,
																Short.MAX_VALUE)
														.addComponent(interfaceTextField, GroupLayout.DEFAULT_SIZE, 80,
																Short.MAX_VALUE))
												.addGap(8))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(chckbxAllowOutput, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
										.addGap(14)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(5)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblLocalInterface)
								.addComponent(interfaceTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPort)
								.addComponent(portSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblMaxPacketsize)
								.addComponent(packetsizeSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(chckbxAllowOutput)
						.addContainerGap(11, Short.MAX_VALUE)));
		setLayout(groupLayout);
	}
	
	public void readValues() {
		receiverProperty.getAttributes().put(NMEASensorIds.NMEA_SENSOR_PROP_HOST, interfaceTextField.getText());
		receiverProperty.getAttributes().put(NMEASensorIds.NMEA_SENSOR_PROP_INTERFACE, interfaceTextField.getText());
		receiverProperty.getAttributes().put(NMEASensorIds.NMEA_SENSOR_PROP_PORT, (int) model.getValue());
		receiverProperty.getAttributes().put(NMEASensorIds.NMEA_SENSOR_PROP_PACKETSIZE, (int) model2.getValue());
		receiverProperty.getAttributes().put(NMEASensorIds.NMEA_SENSOR_PROP_SERVER, chckbxAllowOutput.isSelected());
        dirtyFlag = (!initialHost.equals(interfaceTextField.getText()) || initialPort != (int) model.getValue() ||
                (initialPacketSize != (int) model2.getValue()) || initialServer != chckbxAllowOutput.isSelected());
        if (dirtyFlag) {
        	caller.readValues();
        }
	}
	
	public void init(ReceiverProperty receiverProperty) {
		dirtyFlag = false;
		if (receiverProperty == null) return;
		this.receiverProperty = receiverProperty;
		interfaceTextField.setText((String) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_INTERFACE, "0.0.0.0"));
		initialHost = interfaceTextField.getText();
		interfaceTextField.getDocument().addDocumentListener(new DocumentListener() {
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
		model = new SpinnerNumberModel((int) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_PORT, 7003), 1, 65507, 1);
		initialPort = (int) model.getValue();
		portSpinner.setModel(model);
		portSpinner.setValue((int) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_PORT, 7003));
		model.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				readValues();
			}
		});
		JSpinner.NumberEditor editor2 = new JSpinner.NumberEditor(packetsizeSpinner, "#");
		editor2.getFormat().setGroupingUsed(false);
		packetsizeSpinner.setEditor(editor2);
		model2 = new SpinnerNumberModel((int) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_PACKETSIZE, 65507), 1, 65507, 1);
		initialPacketSize = (int) model2.getValue();
		packetsizeSpinner.setModel(model2);
		packetsizeSpinner.setValue((int) receiverProperty.getAttributes().getOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_PACKETSIZE, 65507));
		model2.addChangeListener(new ChangeListener() {
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
