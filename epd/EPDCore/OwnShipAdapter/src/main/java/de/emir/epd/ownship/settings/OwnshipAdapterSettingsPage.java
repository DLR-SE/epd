package de.emir.epd.ownship.settings;

import de.emir.epd.model.EPDModelUtils;
import de.emir.model.domain.maritime.vessel.*;
import de.emir.model.domain.maritime.vessel.impl.NavigationInformationImpl;
import de.emir.model.domain.maritime.vessel.impl.VesselDimensionCharacteristicImpl;
import de.emir.model.domain.maritime.vessel.impl.WatercraftHullImpl;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.impl.LengthImpl;
import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.rcp.settings.AbstractSettingsPage;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class OwnshipAdapterSettingsPage extends AbstractSettingsPage {
    private JTextField imoTextField;
    private JTextField mmsiTextField;
    private JTextField callsignTextField;
    private JTextField shipnameTextField;
    private JComboBox<VesselType> shiptypeComboBox;
    private JComboBox<NavigationStatus> navstatusComboBox;
    private GenericProperty<Length> draughtProperty;

    private long imo = -1;
    private long mmsi = -1;
    private String callsign = null;
    private String shipName = null;
    private Length draught = null;
    private VesselType vesselType = null;
    private NavigationStatus navigationStatus = null;

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component fillPage() {
        initValue();

        JPanel panel = new JPanel();
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 68, 85, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "Static Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.insets = new Insets(0, 0, 5, 5);
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 1;
        gbc_panel_1.gridy = 1;
        panel.add(panel_1, gbc_panel_1);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[]{66, 0, 0};
        gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel_1.setLayout(gbl_panel_1);

        JLabel lblImo = new JLabel("IMO");
        lblImo.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblImo = new GridBagConstraints();
        gbc_lblImo.anchor = GridBagConstraints.EAST;
        gbc_lblImo.insets = new Insets(0, 0, 5, 5);
        gbc_lblImo.gridx = 0;
        gbc_lblImo.gridy = 0;
        panel_1.add(lblImo, gbc_lblImo);

        imoTextField = new JTextField();
        imoTextField.setText(String.valueOf(imo));
        GridBagConstraints gbc_imoTextField = new GridBagConstraints();
        gbc_imoTextField.insets = new Insets(0, 0, 5, 0);
        gbc_imoTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_imoTextField.gridx = 1;
        gbc_imoTextField.gridy = 0;
        panel_1.add(imoTextField, gbc_imoTextField);
        imoTextField.setColumns(10);

        JLabel lblMmsi = new JLabel("MMSI");
        lblMmsi.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblMmsi = new GridBagConstraints();
        gbc_lblMmsi.anchor = GridBagConstraints.EAST;
        gbc_lblMmsi.insets = new Insets(0, 0, 5, 5);
        gbc_lblMmsi.gridx = 0;
        gbc_lblMmsi.gridy = 1;
        panel_1.add(lblMmsi, gbc_lblMmsi);

        mmsiTextField = new JTextField();
        mmsiTextField.setText(String.valueOf(mmsi));
        GridBagConstraints gbc_mmsiTextField = new GridBagConstraints();
        gbc_mmsiTextField.insets = new Insets(0, 0, 5, 0);
        gbc_mmsiTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_mmsiTextField.gridx = 1;
        gbc_mmsiTextField.gridy = 1;
        panel_1.add(mmsiTextField, gbc_mmsiTextField);
        mmsiTextField.setColumns(10);
        // Do not set the ownship MMSI. This is done by the Ownship viewer.
        mmsiTextField.setEnabled(false);

        JLabel lblCallsign = new JLabel("Callsign");
        lblCallsign.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblCallsign = new GridBagConstraints();
        gbc_lblCallsign.anchor = GridBagConstraints.EAST;
        gbc_lblCallsign.insets = new Insets(0, 0, 5, 5);
        gbc_lblCallsign.gridx = 0;
        gbc_lblCallsign.gridy = 2;
        panel_1.add(lblCallsign, gbc_lblCallsign);

        callsignTextField = new JTextField();
        callsignTextField.setText(callsign);
        GridBagConstraints gbc_callsignTextField = new GridBagConstraints();
        gbc_callsignTextField.insets = new Insets(0, 0, 5, 0);
        gbc_callsignTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_callsignTextField.gridx = 1;
        gbc_callsignTextField.gridy = 2;
        panel_1.add(callsignTextField, gbc_callsignTextField);
        callsignTextField.setColumns(10);

        JLabel lblShipname = new JLabel("Shipname");
        lblShipname.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblShipname = new GridBagConstraints();
        gbc_lblShipname.anchor = GridBagConstraints.EAST;
        gbc_lblShipname.insets = new Insets(0, 0, 5, 5);
        gbc_lblShipname.gridx = 0;
        gbc_lblShipname.gridy = 3;
        panel_1.add(lblShipname, gbc_lblShipname);

        shipnameTextField = new JTextField();
        shipnameTextField.setText(shipName);
        GridBagConstraints gbc_shipnameTextField = new GridBagConstraints();
        gbc_shipnameTextField.insets = new Insets(0, 0, 5, 0);
        gbc_shipnameTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_shipnameTextField.gridx = 1;
        gbc_shipnameTextField.gridy = 3;
        panel_1.add(shipnameTextField, gbc_shipnameTextField);
        shipnameTextField.setColumns(10);

        JLabel lblShiptype = new JLabel("Shiptype");
        lblShiptype.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblShiptype = new GridBagConstraints();
        gbc_lblShiptype.anchor = GridBagConstraints.EAST;
        gbc_lblShiptype.insets = new Insets(0, 0, 0, 5);
        gbc_lblShiptype.gridx = 0;
        gbc_lblShiptype.gridy = 4;
        panel_1.add(lblShiptype, gbc_lblShiptype);

        shiptypeComboBox = new JComboBox<>();
        for (VesselType item : VesselType.values()) {
            shiptypeComboBox.addItem(item);
        }

        shiptypeComboBox.setSelectedItem(vesselType);

        GridBagConstraints gbc_shiptypeComboBox = new GridBagConstraints();
        gbc_shiptypeComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_shiptypeComboBox.gridx = 1;
        gbc_shiptypeComboBox.gridy = 4;
        panel_1.add(shiptypeComboBox, gbc_shiptypeComboBox);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new TitledBorder(null, "Voyage Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.insets = new Insets(0, 0, 5, 5);
        gbc_panel_2.fill = GridBagConstraints.BOTH;
        gbc_panel_2.gridx = 1;
        gbc_panel_2.gridy = 2;
        panel.add(panel_2, gbc_panel_2);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[]{0, 0, 0};
        gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_panel_2.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel_2.setLayout(gbl_panel_2);

        JLabel lblNavstatus = new JLabel("Navstatus");
        lblNavstatus.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblNavstatus = new GridBagConstraints();
        gbc_lblNavstatus.anchor = GridBagConstraints.EAST;
        gbc_lblNavstatus.insets = new Insets(0, 0, 5, 5);
        gbc_lblNavstatus.gridx = 0;
        gbc_lblNavstatus.gridy = 0;
        panel_2.add(lblNavstatus, gbc_lblNavstatus);

        navstatusComboBox = new JComboBox<>();
        for (NavigationStatus item : NavigationStatus.values()) {
            navstatusComboBox.addItem(item);
        }

        navstatusComboBox.setSelectedItem(navigationStatus);

        GridBagConstraints gbc_navstatusComboBox = new GridBagConstraints();
        gbc_navstatusComboBox.insets = new Insets(0, 0, 5, 0);
        gbc_navstatusComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_navstatusComboBox.gridx = 1;
        gbc_navstatusComboBox.gridy = 0;
        panel_2.add(navstatusComboBox, gbc_navstatusComboBox);

        JLabel lblDraught = new JLabel("Draught");
        lblDraught.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblDraught = new GridBagConstraints();
        gbc_lblDraught.anchor = GridBagConstraints.EAST;
        gbc_lblDraught.insets = new Insets(0, 0, 0, 5);
        gbc_lblDraught.gridx = 0;
        gbc_lblDraught.gridy = 1;
        panel_2.add(lblDraught, gbc_lblDraught);

        draughtProperty = new GenericProperty<>("draughtProperty", "Ship Draught", true, draught);
        IPropertyEditor draughtEditor = PropertyManager.getInstance().getFirstEditor(draughtProperty);
        GridBagConstraints gbc_draughtTextField = new GridBagConstraints();
        gbc_draughtTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_draughtTextField.gridx = 1;
        gbc_draughtTextField.gridy = 1;
        panel_2.add(draughtEditor.getEditor(), gbc_draughtTextField);

        return panel;
    }

    private void initValue() {
        Vessel ownship = EPDModelUtils.retrieveOwnship();

        if (ownship != null) {
            //static data
            imo = ownship.getImo();
            mmsi = ownship.getMmsi();
            callsign = ownship.getCallSign();
            if (callsign == null){
                callsign = "";
            }
            //TODO check if this is really the ship name
            shipName = ownship.getNameAsString();
            if (shipName == null){
                shipName = "";
            }

            vesselType = ownship.getType();
            if (vesselType == null) {
                vesselType = VesselType.Other;
                ownship.setType(vesselType);
            }

            NavigationInformation navigationInformation = ownship.getFirstCharacteristic(NavigationInformation.class);
            if (navigationInformation == null) {
                navigationInformation = new NavigationInformationImpl();
                ownship.getCharacteristics().add(navigationInformation);
            }

            navigationStatus = navigationInformation.getStatus();
            if (navigationStatus == null) {
                navigationStatus = NavigationStatus.NotDefined;
            }

            VesselDimensionCharacteristic dimensionCharacteristic = ownship.getFirstCharacteristic(VesselDimensionCharacteristic.class);
            if (dimensionCharacteristic == null) {
                dimensionCharacteristic = new VesselDimensionCharacteristicImpl();
                ownship.getCharacteristics().add(dimensionCharacteristic);
            }

            WatercraftHull hull = dimensionCharacteristic.getHull();
            if (hull == null) {
                hull = new WatercraftHullImpl();
                dimensionCharacteristic.setHull(hull);
            }

            draught = hull.getDraft();
            if (draught == null) {
                draught = new LengthImpl();
                hull.setDraft(draught);
            }
        }
    }

    @Override
    public boolean isDirty() {
        long otherImo = Long.parseLong(imoTextField.getText());
        long otherMMSI = Long.parseLong(mmsiTextField.getText());
        String otherCallsign = callsignTextField.getText();
        String otherShipname = shipnameTextField.getText();
        Length otherDraught = draughtProperty.getValue();
        VesselType otherVesseltype = (VesselType) shiptypeComboBox.getSelectedItem();
        NavigationStatus otherStatus = (NavigationStatus) navstatusComboBox.getSelectedItem();

        return !(otherImo == imo && otherMMSI == mmsi && otherCallsign.equals(callsign) && otherShipname.equals(shipName) &&
                otherDraught.getValue() == draught.getValue() && otherVesseltype.equals(vesselType) &&
                otherStatus.equals(navigationStatus));
    }

    @Override
    public void finish() {
        Vessel ownship = EPDModelUtils.retrieveOwnship();
        if (ownship != null) {
            if(isDirty()) {
                ownship.setCallSign(callsignTextField.getText());
                ownship.setImo(Long.parseLong(imoTextField.getText()));
                ownship.setName(shipnameTextField.getText());
                ownship.setType((VesselType)shiptypeComboBox.getSelectedItem());
                NavigationInformation navigationInformation = ownship.getFirstCharacteristic(NavigationInformation.class);
                if (navigationInformation == null) {
                    navigationInformation = new NavigationInformationImpl();
                    ownship.getCharacteristics().add(navigationInformation);
                }
                navigationInformation.setStatus((NavigationStatus)navstatusComboBox.getSelectedItem());

                VesselDimensionCharacteristic dimensionCharacteristic = ownship.getFirstCharacteristic(VesselDimensionCharacteristic.class);
                if (dimensionCharacteristic == null) {
                    dimensionCharacteristic = new VesselDimensionCharacteristicImpl();
                    ownship.getCharacteristics().add(dimensionCharacteristic);
                }

                WatercraftHull hull = dimensionCharacteristic.getHull();
                if (hull == null) {
                    hull = new WatercraftHullImpl();
                    dimensionCharacteristic.setHull(hull);
                }

                hull.setDraft(draughtProperty.getValue());

                initValue();
            } else {
                ULog.info("Not updating ownship AIS information because no values were changed");
            }
        } else {
            ULog.error("Could not set AIS information because there is no ownship");
        }
    }
}
