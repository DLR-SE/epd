package de.emir.epd.nmeasensor.settings;

import de.emir.epd.nmeasensor.ids.NMEASensorIds;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.ui.utils.properties.PropertyCheckboxWidget;
import de.emir.rcp.ui.utils.properties.PropertySpinnerWidget;
import de.emir.rcp.ui.utils.properties.PropertyTextWidget;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TCPReceiverSettings extends AbstractReceiverSettings {
	private PropertySpinnerWidget portSpinner;
	private PropertyTextWidget hostTextField;
	private SpinnerModel model;
	private PropertyCheckboxWidget chckbxAllowOutput;
	private int initialPort = 16100;
	private boolean initialServer = false;
	private String initialHost = "127.0.0.1";
	
	public TCPReceiverSettings(NMEASensorSettingsPage caller) {
		this.caller = caller;
		setPreferredSize(new Dimension(180, 104));
		setBorder(new EmptyBorder(8, 8, 8, 8));
		PropertyContext context = PropertyStore.getContext(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT);
		JLabel hostLabel = new JLabel();
		hostLabel.setHorizontalAlignment(SwingConstants.CENTER);

		hostLabel.setText("Host");
		hostTextField = new PropertyTextWidget(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT,
                caller.getNamePath() + "." + NMEASensorIds.NMEA_SENSOR_PROP_HOST,
                "127.0.0.1"
        );

		JLabel lblPort = new JLabel("Port");

		portSpinner = new PropertySpinnerWidget(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT,
                caller.getNamePath() + "." + NMEASensorIds.NMEA_SENSOR_PROP_PORT,
                16100
        );
		
		chckbxAllowOutput = new PropertyCheckboxWidget("Allow Output", NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT,
                caller.getNamePath() + "." + NMEASensorIds.NMEA_SENSOR_PROP_SERVER,
                false
        );
		
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

    @Override
	public void readValues() {
		dirtyFlag = (!initialHost.equals(hostTextField.getValue()) || initialPort != (int) model.getValue()
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
		initialHost = hostTextField.getValue();
        initialPort = (int) portSpinner.getProperty().getValue();
        initialServer = chckbxAllowOutput.isSelected();
		
		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(portSpinner, "#");
		editor.getFormat().setGroupingUsed(false);
		portSpinner.setEditor(editor);
		model = new SpinnerNumberModel(initialPort, 1, 65535, 1);
		portSpinner.setModel(model);
		model.addChangeListener(new ChangeListener() {
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
        hostTextField.finish();
        portSpinner.finish();
        chckbxAllowOutput.finish();
    }
}
