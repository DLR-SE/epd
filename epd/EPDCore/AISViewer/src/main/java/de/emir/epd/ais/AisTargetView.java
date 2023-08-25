package de.emir.epd.ais;

import de.emir.epd.mapview.ids.MVBasic;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.VesselType;
import de.emir.model.domain.maritime.vessel.VesselUtils;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.physics.PhysicalObjectUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.units.*;
import de.emir.rcp.manager.SelectionManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.views.AbstractView;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;

import javax.swing.*;
import java.awt.*;

public class AisTargetView extends AbstractView {
    private JLabel nameLbl;
    private JLabel mmsiLbl;
    private JLabel typeLbl;
    private JLabel destinationLbl;
    private JLabel dimensionsLbl;
    private JLabel draughtLbl;
    private JLabel posLbl;
    private JLabel sogLbl;
    private JLabel cogLbl;
    private JLabel headingLbl;
    private JLabel rotLbl;
    protected Vessel currentSelected;
    protected Vessel currentFocused;

    protected ITreeValueChangeListener targetTreeListener;
    protected Vessel currentTarget;

    private JPanel area;
    private JScrollPane sc;

    public AisTargetView(String id) {
        super(id);

    }

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component createContent() {

        Container parent = new JPanel();
        GridBagLayout gbl_parent = new GridBagLayout();

        gbl_parent.columnWeights = new double[]{1.0};
        gbl_parent.rowWeights = new double[]{1.0};
        parent.setLayout(gbl_parent);

        int row = 0;

        // This scrollpane prevents view resizes by its contents
        sc = new JScrollPane();
        sc.setViewportBorder(null);
        sc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        sc.setBorder(null);
        GridBagConstraints gbc_sc = new GridBagConstraints();
        gbc_sc.fill = GridBagConstraints.BOTH;
        gbc_sc.gridx = 0;
        gbc_sc.gridy = row;
        parent.add(sc, gbc_sc);

        area = new JPanel();
        sc.setViewportView(area);
        GridBagLayout gbl_area = new GridBagLayout();

        gbl_area.columnWeights = new double[]{0.0, 1.0};
        gbl_area.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        area.setLayout(gbl_area);


//        row++;
        JLabel lblName = new JLabel("Name:");
        lblName.setForeground(UIManager.getColor("Label.disabledForeground"));
        GridBagConstraints gbc_lblName = new GridBagConstraints();
        gbc_lblName.anchor = GridBagConstraints.EAST;
        gbc_lblName.insets = new Insets(0, 10, 5, 5);
        gbc_lblName.gridx = 0;
        gbc_lblName.gridy = row;
        area.add(lblName, gbc_lblName);

        nameLbl = new JLabel("Unknown");
        this.nameLbl.setForeground(UIManager.getColor("Label.foreground"));
        nameLbl.setFont(nameLbl.getFont().deriveFont(nameLbl.getFont().getStyle() | Font.BOLD, nameLbl.getFont().getSize() + 3));

        GridBagConstraints gbc_nameLbl = new GridBagConstraints();
        gbc_nameLbl.anchor = GridBagConstraints.WEST;
        gbc_nameLbl.insets = new Insets(0, 5, 5, 0);
        gbc_nameLbl.gridx = 1;
        gbc_nameLbl.gridy = row;
        area.add(nameLbl, gbc_nameLbl);

        row++;
        JLabel lblMmsi = new JLabel("MMSI:");
        lblMmsi.setForeground(UIManager.getColor("Label.disabledForeground"));
        GridBagConstraints gbc_lblMmsi = new GridBagConstraints();
        gbc_lblMmsi.anchor = GridBagConstraints.EAST;
        gbc_lblMmsi.insets = new Insets(0, 10, 5, 5);
        gbc_lblMmsi.gridx = 0;
        gbc_lblMmsi.gridy = row;
        area.add(lblMmsi, gbc_lblMmsi);

        mmsiLbl = new JLabel("Unknown");
        mmsiLbl.setFont(mmsiLbl.getFont().deriveFont(mmsiLbl.getFont().getStyle() | Font.BOLD, mmsiLbl.getFont().getSize() + 3));
        GridBagConstraints gbc_mmsiLbl = new GridBagConstraints();
        gbc_mmsiLbl.insets = new Insets(0, 5, 5, 0);
        gbc_mmsiLbl.anchor = GridBagConstraints.WEST;
        gbc_mmsiLbl.gridx = 1;
        gbc_mmsiLbl.gridy = row;
        area.add(mmsiLbl, gbc_mmsiLbl);

        row++;
        JLabel lbltype = new JLabel("Ship Type:");
        lbltype.setForeground(UIManager.getColor("Label.disabledForeground"));
        GridBagConstraints gbc_lbltype = new GridBagConstraints();
        gbc_lbltype.anchor = GridBagConstraints.EAST;
        gbc_lbltype.insets = new Insets(0, 10, 5, 5);
        gbc_lbltype.gridx = 0;
        gbc_lbltype.gridy = row;
        area.add(lbltype, gbc_lbltype);

        typeLbl = new JLabel("Unknown");
        typeLbl.setFont(typeLbl.getFont().deriveFont(typeLbl.getFont().getStyle() | Font.BOLD, typeLbl.getFont().getSize() + 3));
        GridBagConstraints gbc_typeLbl = new GridBagConstraints();
        gbc_typeLbl.insets = new Insets(0, 5, 5, 0);
        gbc_typeLbl.anchor = GridBagConstraints.WEST;
        gbc_typeLbl.gridx = 1;
        gbc_typeLbl.gridy = row;
        area.add(typeLbl, gbc_typeLbl);

        row++;
//        JLabel lblDimensions = new JLabel("Dimensions (l, w):");
        JLabel lblDimensions = new JLabel("Length/Width:");
        lblDimensions.setForeground(UIManager.getColor("Label.disabledForeground"));
        GridBagConstraints gbc_lblDimensions = new GridBagConstraints();
        gbc_lblDimensions.anchor = GridBagConstraints.EAST;
        gbc_lblDimensions.insets = new Insets(0, 10, 5, 5);
        gbc_lblDimensions.gridx = 0;
        gbc_lblDimensions.gridy = row;
        area.add(lblDimensions, gbc_lblDimensions);

        dimensionsLbl = new JLabel("Unknown");
        dimensionsLbl.setFont(dimensionsLbl.getFont().deriveFont(dimensionsLbl.getFont().getStyle() | Font.BOLD, dimensionsLbl.getFont().getSize() + 3));
        GridBagConstraints gbc_dimensionsLbl = new GridBagConstraints();
        gbc_dimensionsLbl.insets = new Insets(0, 5, 5, 0);
        gbc_dimensionsLbl.anchor = GridBagConstraints.WEST;
        gbc_dimensionsLbl.gridx = 1;
        gbc_dimensionsLbl.gridy = row;
        area.add(dimensionsLbl, gbc_dimensionsLbl);

        row++;
        JSeparator staticSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        GridBagConstraints gbc_staticSeparator = new GridBagConstraints();
        gbc_staticSeparator.anchor = GridBagConstraints.WEST;
        gbc_staticSeparator.insets = new Insets(0, 10, 5, 5);
        gbc_staticSeparator.gridx = 0;
        gbc_staticSeparator.gridy = row;
        gbc_staticSeparator.gridwidth = 2;
        area.add(staticSeparator, gbc_staticSeparator);

        row++;
        JLabel lblDestination = new JLabel("Destination:");
        lblDestination.setForeground(UIManager.getColor("Label.disabledForeground"));
        GridBagConstraints gbc_lblDestination = new GridBagConstraints();
        gbc_lblDestination.anchor = GridBagConstraints.EAST;
        gbc_lblDestination.insets = new Insets(0, 10, 5, 5);
        gbc_lblDestination.gridx = 0;
        gbc_lblDestination.gridy = row;
        area.add(lblDestination, gbc_lblDestination);

        destinationLbl = new JLabel("Unknown");
        destinationLbl.setFont(destinationLbl.getFont().deriveFont(destinationLbl.getFont().getStyle() | Font.BOLD, destinationLbl.getFont().getSize() + 3));
        GridBagConstraints gbc_destinationLbl = new GridBagConstraints();
        gbc_destinationLbl.insets = new Insets(0, 5, 5, 0);
        gbc_destinationLbl.anchor = GridBagConstraints.WEST;
        gbc_destinationLbl.gridx = 1;
        gbc_destinationLbl.gridy = row;
        area.add(destinationLbl, gbc_destinationLbl);

        row++;
        JLabel lblDraught = new JLabel("Draught:");
        lblDraught.setForeground(UIManager.getColor("Label.disabledForeground"));
        GridBagConstraints gbc_lblDraught = new GridBagConstraints();
        gbc_lblDraught.anchor = GridBagConstraints.EAST;
        gbc_lblDraught.insets = new Insets(0, 10, 5, 5);
        gbc_lblDraught.gridx = 0;
        gbc_lblDraught.gridy = row;
        area.add(lblDraught, gbc_lblDraught);

        draughtLbl = new JLabel("Unknown");
        draughtLbl.setFont(draughtLbl.getFont().deriveFont(draughtLbl.getFont().getStyle() | Font.BOLD, draughtLbl.getFont().getSize() + 3));
        GridBagConstraints gbc_draughtLbl = new GridBagConstraints();
        gbc_draughtLbl.insets = new Insets(0, 5, 5, 0);
        gbc_draughtLbl.anchor = GridBagConstraints.WEST;
        gbc_draughtLbl.gridx = 1;
        gbc_draughtLbl.gridy = row;
        area.add(draughtLbl, gbc_draughtLbl);

        row++;
        JSeparator voyageSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        GridBagConstraints gbc_voyageSeparator = new GridBagConstraints();
        gbc_voyageSeparator.anchor = GridBagConstraints.WEST;
        gbc_voyageSeparator.insets = new Insets(0, 10, 5, 5);
        gbc_voyageSeparator.gridx = 0;
        gbc_voyageSeparator.gridy = row;
        gbc_voyageSeparator.gridwidth = 2;
        area.add(voyageSeparator, gbc_voyageSeparator);

        row++;
        JLabel lblPosition = new JLabel("Position:");
        lblPosition.setForeground(UIManager.getColor("Label.disabledForeground"));
        GridBagConstraints gbc_lblPosition = new GridBagConstraints();
        gbc_lblPosition.anchor = GridBagConstraints.EAST;
        gbc_lblPosition.insets = new Insets(0, 10, 5, 5);
        gbc_lblPosition.gridx = 0;
        gbc_lblPosition.gridy = row;
        area.add(lblPosition, gbc_lblPosition);

        posLbl = new JLabel("Unknown");
        posLbl.setFont(posLbl.getFont().deriveFont(posLbl.getFont().getStyle() | Font.BOLD, posLbl.getFont().getSize() + 3));
        GridBagConstraints gbc_posLbl = new GridBagConstraints();
        gbc_posLbl.anchor = GridBagConstraints.WEST;
        gbc_posLbl.insets = new Insets(0, 5, 5, 5);
        gbc_posLbl.gridx = 1;
        gbc_posLbl.gridy = row;
        area.add(posLbl, gbc_posLbl);

        row++;
        JLabel lblSog = new JLabel("SOG:");
        lblSog.setForeground(UIManager.getColor("Label.disabledForeground"));
        GridBagConstraints gbc_lblSog = new GridBagConstraints();
        gbc_lblSog.anchor = GridBagConstraints.EAST;
        gbc_lblSog.insets = new Insets(0, 10, 5, 5);
        gbc_lblSog.gridx = 0;
        gbc_lblSog.gridy = row;
        area.add(lblSog, gbc_lblSog);

        sogLbl = new JLabel("Unknown");
        sogLbl.setFont(sogLbl.getFont().deriveFont(sogLbl.getFont().getStyle() | Font.BOLD, sogLbl.getFont().getSize() + 3));
        GridBagConstraints gbc_sogLbl = new GridBagConstraints();
        gbc_sogLbl.insets = new Insets(0, 5, 5, 0);
        gbc_sogLbl.anchor = GridBagConstraints.WEST;
        gbc_sogLbl.gridx = 1;
        gbc_sogLbl.gridy = row;
        area.add(sogLbl, gbc_sogLbl);

        row++;
        JLabel lblCog = new JLabel("COG:");
        lblCog.setForeground(UIManager.getColor("Label.disabledForeground"));
        GridBagConstraints gbc_lblCog = new GridBagConstraints();
        gbc_lblCog.anchor = GridBagConstraints.EAST;
        gbc_lblCog.insets = new Insets(0, 10, 5, 5);
        gbc_lblCog.gridx = 0;
        gbc_lblCog.gridy = row;
        area.add(lblCog, gbc_lblCog);

        cogLbl = new JLabel("Unknown");
        cogLbl.setFont(cogLbl.getFont().deriveFont(cogLbl.getFont().getStyle() | Font.BOLD, cogLbl.getFont().getSize() + 3));
        GridBagConstraints gbc_cogLbl = new GridBagConstraints();
        gbc_cogLbl.insets = new Insets(0, 5, 5, 0);
        gbc_cogLbl.anchor = GridBagConstraints.WEST;
        gbc_cogLbl.gridx = 1;
        gbc_cogLbl.gridy = row;
        area.add(cogLbl, gbc_cogLbl);

        row++;
        JLabel lblHeading = new JLabel("Heading:");
        lblHeading.setForeground(UIManager.getColor("Label.disabledForeground"));
        GridBagConstraints gbc_lblHeading = new GridBagConstraints();
        gbc_lblHeading.anchor = GridBagConstraints.EAST;
        gbc_lblHeading.insets = new Insets(0, 10, 5, 5);
        gbc_lblHeading.gridx = 0;
        gbc_lblHeading.gridy = row;
        area.add(lblHeading, gbc_lblHeading);

        headingLbl = new JLabel("Unknown");
        headingLbl.setFont(headingLbl.getFont().deriveFont(headingLbl.getFont().getStyle() | Font.BOLD, headingLbl.getFont().getSize() + 3));
        GridBagConstraints gbc_headingLbl = new GridBagConstraints();
        gbc_headingLbl.insets = new Insets(0, 5, 5, 0);
        gbc_headingLbl.anchor = GridBagConstraints.WEST;
        gbc_headingLbl.gridx = 1;
        gbc_headingLbl.gridy = row;
        area.add(headingLbl, gbc_headingLbl);

        row++;
        JLabel lblRot = new JLabel("ROT:");
        lblRot.setForeground(UIManager.getColor("Label.disabledForeground"));
        GridBagConstraints gbc_lblRot = new GridBagConstraints();
        gbc_lblRot.anchor = GridBagConstraints.EAST;
        gbc_lblRot.insets = new Insets(0, 10, 5, 5);
        gbc_lblRot.gridx = 0;
        gbc_lblRot.gridy = row;
        area.add(lblRot, gbc_lblRot);

        rotLbl = new JLabel("Unknown");
        rotLbl.setFont(rotLbl.getFont().deriveFont(rotLbl.getFont().getStyle() | Font.BOLD, rotLbl.getFont().getSize() + 3));
        GridBagConstraints gbc_rotLbl = new GridBagConstraints();
        gbc_rotLbl.insets = new Insets(0, 5, 5, 0);
        gbc_rotLbl.anchor = GridBagConstraints.WEST;
        gbc_rotLbl.gridx = 1;
        gbc_rotLbl.gridy = row;
        area.add(rotLbl, gbc_rotLbl);

        addListeners();

        return parent;

    }

    protected void addListeners() {

        targetTreeListener = notification -> updateInformation();

        SelectionManager sm = PlatformUtil.getSelectionManager();

        sm.subscribe(MVBasic.MAP_FOCUS_CTX, oo -> {

            if (oo.isPresent() == true && oo.get() instanceof Vessel) {
                currentFocused = (Vessel) oo.get();
            } else {
                currentFocused = null;
            }

            updateCurrentTarget();

        });

        sm.subscribe(MVBasic.MAP_SELECTION_CTX, oo -> {

            if (oo.isPresent() == true && oo.get() instanceof Vessel) {
                currentSelected = (Vessel) oo.get();
            } else {
                currentSelected = null;
            }

            updateCurrentTarget();

        });

    }

    protected void updateCurrentTarget() {

        if (currentTarget != null) {
            currentTarget.removeTreeListener(targetTreeListener);
        }

        if (currentFocused != null) {
            currentTarget = currentFocused;
        } else {
            currentTarget = currentSelected;
        }

        if (currentTarget != null) {
            currentTarget.registerTreeListener(targetTreeListener);
        }

        updateInformation();
    }

    protected void updateInformation() {
        if (currentTarget == null) {

            nameLbl.setText("Unknown");
            cogLbl.setText("Unknown");
            sogLbl.setText("Unknown");
            mmsiLbl.setText("Unknown");

            typeLbl.setText("Unknown");
            destinationLbl.setText("Unknown");
            dimensionsLbl.setText("(Unknown, Unknown)");
            draughtLbl.setText("Unknown");
            sogLbl.setText("Unknown");
            cogLbl.setText("Unknown");
            headingLbl.setText("Unknown");
            rotLbl.setText("Unknown");

            return;
        }

        Vessel v = currentTarget;

        String name = v.getNameAsString();

        if (name != null) {
            nameLbl.setText(name);
        } else {
            nameLbl.setText("Unknown");
        }

        VesselType type = v.getType();
        if (type != null) {
            typeLbl.setText(type.getLabel());
        } else {
            typeLbl.setText("Unknown");
        }

        String destination = VesselUtils.getDestination(v);
        if (destination != null) {
            destinationLbl.setText(destination);
        } else {
            destinationLbl.setText("Unknown");
        }

        Length shipLength = PhysicalObjectUtils.getLength(v);
        Length shipWidth = PhysicalObjectUtils.getWidth(v);
        if (shipLength != null && shipWidth != null) {

            float length = (float) shipLength.getAs(DistanceUnit.METER);
            float width = (float) shipWidth.getAs(DistanceUnit.METER);
            dimensionsLbl.setText(String.format("(%.1fm, %.1fm)", length, width));

        } else {
            dimensionsLbl.setText("(Unknown, Unknown)");
        }

        Length draftLength = VesselUtils.getDraft(v);
        if (draftLength != null) {
            float draft = (float) draftLength.getAs(DistanceUnit.METER);
            draughtLbl.setText(String.format("%.1fm", draft));
        }

        Pose pose = v.getPose();
        if (pose != null) {
            Coordinate coord = pose.getCoordinate();
            if (coord != null) {
                posLbl.setText(
                        CRSUtils.toDegreeMinuteSecond(coord.getLatitude()) +
                        " / " +
                        CRSUtils.toDegreeMinuteSecond(coord.getLongitude())
                );
            } else {
                posLbl.setText("Unknown");
            }
        } else {
            posLbl.setText("Unknown");
        }

        Speed sogSpeed = PhysicalObjectUtils.getSOG(v);
        if (sogSpeed != null) {
            float sog = (float) sogSpeed.getAs(SpeedUnit.KNOTS);
            sogLbl.setText(String.format("%.1fkn", sog));
        } else {
            sogLbl.setText("Unknown");
        }

        Angle cogAngle = PhysicalObjectUtils.getCOG(v);
        if (cogAngle != null) {
            float cog = (float) cogAngle.getAs(AngleUnit.DEGREE);
            cogLbl.setText(String.format("%.1f°", cog));
        } else {
            cogLbl.setText("Unknown");
        }

        Angle headingAngle = PhysicalObjectUtils.getHeading(v);
        if (headingAngle != null) {
            float heading = (float) headingAngle.getAs(AngleUnit.DEGREE);
            headingLbl.setText(String.format("%.1f°", heading));
        } else {
            headingLbl.setText("Unknown");
        }

        AngularSpeed rotSpeed = PhysicalObjectUtils.getRateOfTurn(v);
        if (rotSpeed != null) {
            double rot = rotSpeed.getAs(AngularSpeedUnit.DEGREES_PER_MINUTE);
            rotLbl.setText(String.format("%.1f°/min", rot));
        } else {
            rotLbl.setText("Unknown");
        }

        long mmsi = v.getMmsi();

        mmsiLbl.setText(mmsi + "");
    }

    @Override
    public void onOpen() {

    }

    @Override
    public void onClose() {

    }

}
