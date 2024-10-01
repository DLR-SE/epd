package de.emir.epd.nmeasensor.settings;

import de.emir.epd.nmeasensor.ids.NMEASensorIds;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.ui.utils.properties.PropertyCheckboxWidget;
import de.emir.rcp.ui.utils.properties.PropertySpinnerWidget;
import de.emir.rcp.ui.utils.properties.PropertyTextWidget;
import java.awt.Dimension;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class SerialReceiverSettings extends AbstractReceiverSettings {
	private PropertyTextWidget serialportTextField;
	private PropertySpinnerWidget baudrateSpinner;
	private PropertySpinnerWidget packetsizeSpinner;
	private SpinnerModel model;
	private SpinnerModel model2;
	private String initialSerialPort = "COM1";
	private int initialBaudrate = 9600;
	private int initialPacketSize = 65535;
	private boolean initialServer = false;
	private PropertyCheckboxWidget chckbxAllowOutput;

	public SerialReceiverSettings(NMEASensorSettingsPage caller) {
		this.caller = caller;
		setPreferredSize(new Dimension(180, 120));
		PropertyContext context = PropertyStore.getContext(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT);

		JLabel lblLocalInterface = new JLabel("Serial Port");
		lblLocalInterface.setHorizontalAlignment(SwingConstants.TRAILING);

		serialportTextField = new PropertyTextWidget(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT,
                caller.getNamePath() + "." + NMEASensorIds.NMEA_SENSOR_PROP_SERIALPORT,
                "COM1"
        );

		packetsizeSpinner = new PropertySpinnerWidget(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT, 
                caller.getNamePath() + "." + NMEASensorIds.NMEA_SENSOR_PROP_PACKETSIZE,
                65535
        );
        initialPacketSize = (int) packetsizeSpinner.getValue();

		JLabel lblPort = new JLabel("Baudrate");
		lblPort.setHorizontalAlignment(SwingConstants.TRAILING);

		baudrateSpinner = new PropertySpinnerWidget(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT, 
                caller.getNamePath() + "." + NMEASensorIds.NMEA_SENSOR_PROP_BAUDRATE,
                9600
        );
        initialBaudrate = (int) baudrateSpinner.getValue();

		JLabel lblMaxPacketsize = new JLabel("Max. Packetsize");
		lblMaxPacketsize.setHorizontalAlignment(SwingConstants.TRAILING);
		
		chckbxAllowOutput = new PropertyCheckboxWidget("Allow Output", NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT, 
                caller.getNamePath() + "." + NMEASensorIds.NMEA_SENSOR_PROP_OUTPUT,
                false
        );
        initialServer = chckbxAllowOutput.isSelected();
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

    @Override
	public void readValues() {
		dirtyFlag = (!initialSerialPort.equals(serialportTextField.getValue())
				|| initialBaudrate != (int) model.getValue() || initialPacketSize != (int) model2.getValue()
				|| initialServer != chckbxAllowOutput.isSelected());
	}
    
    @Override
    public boolean isDirty() {
        readValues();
        return dirtyFlag;
    }

    @Override
	public void init() {
		dirtyFlag = false;
		initialSerialPort = serialportTextField.getValue();
		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(baudrateSpinner, "#");
		editor.getFormat().setGroupingUsed(false);
		baudrateSpinner.setEditor(editor);
		model = new SpinnerNumberModel(
				initialBaudrate, 1,
				256000, 1);
		baudrateSpinner.setModel(model);
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
				initialPacketSize,
				1, 65535, 1);
		packetsizeSpinner.setModel(model2);
		initialPacketSize = (int) model2.getValue();
		model2.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				readValues();
			}
		});
		chckbxAllowOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				readValues();
			}
		});
	}

    @Override
    public void finish() {
        serialportTextField.finish();
        packetsizeSpinner.finish();
        baudrateSpinner.finish();
        chckbxAllowOutput.finish();
    }
}
