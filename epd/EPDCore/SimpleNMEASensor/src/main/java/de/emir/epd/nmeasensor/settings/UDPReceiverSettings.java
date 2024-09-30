package de.emir.epd.nmeasensor.settings;

import de.emir.epd.nmeasensor.ids.NMEASensorIds;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.ui.utils.properties.PropertyCheckboxWidget;
import de.emir.rcp.ui.utils.properties.PropertySpinnerWidget;
import de.emir.rcp.ui.utils.properties.PropertyTextWidget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UDPReceiverSettings extends AbstractReceiverSettings {

    private PropertyTextWidget interfaceTextField;
    private PropertySpinnerWidget portSpinner;
    private PropertySpinnerWidget packetsizeSpinner;
    private SpinnerModel model;
    private SpinnerModel model2;
    private String initialHost = "0.0.0.0";
    private int initialPort = 7003;
    private int initialPacketSize = 65507;
    private boolean initialServer = false;
    private PropertyCheckboxWidget chckbxAllowOutput;

    public UDPReceiverSettings(NMEASensorSettingsPage caller) {
        this.caller = caller;
        setPreferredSize(new Dimension(220, 140));
        PropertyContext context = PropertyStore.getContext(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{87, 116, 0};
        gridBagLayout.rowHeights = new int[]{30, 20, 20, 23, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);
        JLabel lblLocalInterface = new JLabel("Local Interface");
        lblLocalInterface.setHorizontalAlignment(SwingConstants.TRAILING);
        GridBagConstraints gbc_lblLocalInterface = new GridBagConstraints();
        gbc_lblLocalInterface.anchor = GridBagConstraints.EAST;
        gbc_lblLocalInterface.insets = new Insets(4, 4, 4, 4);
        gbc_lblLocalInterface.gridx = 0;
        gbc_lblLocalInterface.gridy = 0;
        add(lblLocalInterface, gbc_lblLocalInterface);

        interfaceTextField = new PropertyTextWidget(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT,
                caller.getNamePath() + "." + NMEASensorIds.NMEA_SENSOR_PROP_INTERFACE,
                "0.0.0.0"
        );

        GridBagConstraints gbc_interfaceTextField = new GridBagConstraints();
        gbc_interfaceTextField.fill = GridBagConstraints.BOTH;
        gbc_interfaceTextField.anchor = GridBagConstraints.WEST;
        gbc_interfaceTextField.insets = new Insets(4, 4, 4, 4);
        gbc_interfaceTextField.gridx = 1;
        gbc_interfaceTextField.gridy = 0;
        add(interfaceTextField, gbc_interfaceTextField);

        JLabel lblPort = new JLabel("Port");
        lblPort.setHorizontalAlignment(SwingConstants.TRAILING);
        GridBagConstraints gbc_lblPort = new GridBagConstraints();
        gbc_lblPort.anchor = GridBagConstraints.EAST;
        gbc_lblPort.insets = new Insets(4, 4, 4, 4);
        gbc_lblPort.gridx = 0;
        gbc_lblPort.gridy = 1;
        add(lblPort, gbc_lblPort);

        portSpinner = new PropertySpinnerWidget(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT,
                caller.getNamePath() + "." + NMEASensorIds.NMEA_SENSOR_PROP_PORT,
                7003
        );

        GridBagConstraints gbc_portSpinner = new GridBagConstraints();
        gbc_portSpinner.anchor = GridBagConstraints.WEST;
        gbc_portSpinner.fill = GridBagConstraints.BOTH;
        gbc_portSpinner.insets = new Insets(4, 4, 4, 4);
        gbc_portSpinner.gridx = 1;
        gbc_portSpinner.gridy = 1;
        add(portSpinner, gbc_portSpinner);

        JLabel lblMaxPacketsize = new JLabel("Max. Packetsize");
        lblMaxPacketsize.setHorizontalAlignment(SwingConstants.TRAILING);
        GridBagConstraints gbc_lblMaxPacketsize = new GridBagConstraints();
        gbc_lblMaxPacketsize.anchor = GridBagConstraints.EAST;
        gbc_lblMaxPacketsize.insets = new Insets(4, 4, 4, 4);
        gbc_lblMaxPacketsize.gridx = 0;
        gbc_lblMaxPacketsize.gridy = 2;
        add(lblMaxPacketsize, gbc_lblMaxPacketsize);

        packetsizeSpinner = new PropertySpinnerWidget(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT,
                caller.getNamePath() + "." + NMEASensorIds.NMEA_SENSOR_PROP_PACKETSIZE,
                65507
        );

        GridBagConstraints gbc_packetsizeSpinner = new GridBagConstraints();
        gbc_packetsizeSpinner.anchor = GridBagConstraints.WEST;
        gbc_packetsizeSpinner.fill = GridBagConstraints.BOTH;
        gbc_packetsizeSpinner.insets = new Insets(4, 4, 4, 4);
        gbc_packetsizeSpinner.gridx = 1;
        gbc_packetsizeSpinner.gridy = 2;
        add(packetsizeSpinner, gbc_packetsizeSpinner);

        chckbxAllowOutput = new PropertyCheckboxWidget("Server", NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT,
                caller.getNamePath() + "." + NMEASensorIds.NMEA_SENSOR_PROP_OUTPUT,
                false
        );

        GridBagConstraints gbc_chckbxAllowOutput = new GridBagConstraints();
        gbc_chckbxAllowOutput.insets = new Insets(4, 4, 4, 4);
        gbc_chckbxAllowOutput.anchor = GridBagConstraints.NORTHWEST;
        gbc_chckbxAllowOutput.fill = GridBagConstraints.BOTH;
        gbc_chckbxAllowOutput.gridx = 1;
        gbc_chckbxAllowOutput.gridy = 3;
        add(chckbxAllowOutput, gbc_chckbxAllowOutput);
    }
    
    @Override
    public boolean isDirty() {
        readValues();
        return dirtyFlag;
    }

    @Override
    public void readValues() {
        dirtyFlag = (!initialHost.equals(interfaceTextField.getValue()) || initialPort != (int) model.getValue()
                || (initialPacketSize != (int) model2.getValue()) || initialServer != chckbxAllowOutput.isSelected());
    }

    @Override
    public void init() {
        dirtyFlag = false;
        initialHost = interfaceTextField.getValue();
        initialPort = (int) portSpinner.getProperty().getValue();
        initialPacketSize = (int) packetsizeSpinner.getProperty().getValue();
        initialServer = chckbxAllowOutput.isSelected();

        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(portSpinner, "#");
        editor.getFormat().setGroupingUsed(false);
        portSpinner.setEditor(editor);
        model = new SpinnerNumberModel(initialPort, 1, 65507, 1);
        portSpinner.setModel(model);

        JSpinner.NumberEditor editor2 = new JSpinner.NumberEditor(packetsizeSpinner, "#");
        editor2.getFormat().setGroupingUsed(false);
        packetsizeSpinner.setEditor(editor2);
        model2 = new SpinnerNumberModel(initialPacketSize, 1, 65507, 1);
        packetsizeSpinner.setModel(model2);

        initialServer = chckbxAllowOutput.isSelected();
    }

    @Override
    public void finish() {
        portSpinner.finish();
        interfaceTextField.finish();
        packetsizeSpinner.finish();
        chckbxAllowOutput.finish();
    }
}
