package de.emir.rcp.parts.vesseleditor.view.panels;

import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.impl.RSIdentifierImpl;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.Orientation;
import de.emir.model.universal.units.Quaternion;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.parts.vesseleditor.utils.MIDManager;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.rcp.util.WidgetUtils;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class GeneralVesselPanel extends JPanel {

    private PhysicalObject mVessel;

    /**
     * @wbp.parser.constructor
     */
    public GeneralVesselPanel(PhysicalObject vessel) {
        this(vessel, null);
    }

    public GeneralVesselPanel(PhysicalObject vessel, JPanel additions) {
        super();
        mVessel = vessel;
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowWeights = new double[]{1.0};
        gridBagLayout.columnWeights = new double[]{1.0};
        setLayout(gridBagLayout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel generalPanel = getGeneralPanel();
        add(generalPanel, gbc);
        gbc.gridy++;

        JPanel posePanel = getPosePanel();
        add(posePanel, gbc);
        gbc.gridy++;

        if (additions != null) {
            add(additions, gbc);
            gbc.gridy++;
        }
        
        setBackground(Color.RED);
        
//        setMaximumSize(getPreferredSize());

        //place holder to avoid big gabs between general and pose
//        JPanel empty = new JPanel();
//        gbc.weighty = 1;
//        gbc.fill = GridBagConstraints.VERTICAL;
//        add(empty, gbc);
    }

    private JPanel getGeneralPanel() {
        JPanel panel = createBorderPanel("General");

        GridBagLayout gridBagLayout = new GridBagLayout();
        panel.setLayout(gridBagLayout);

        GridBagConstraints gbcL = new GridBagConstraints();
        gbcL.insets = new Insets(0, 0, 0, 5);
        gbcL.anchor = GridBagConstraints.WEST;
        gbcL.gridx = 0;
        gbcL.gridy = 0;

        GridBagConstraints gbcPE = new GridBagConstraints();
        gbcPE.anchor = GridBagConstraints.WEST;
        gbcPE.fill = GridBagConstraints.HORIZONTAL;
        gbcPE.gridx = 1;
        gbcPE.gridy = 0;
        gbcPE.weightx = 1;
        gbcPE.gridwidth = 2;

        PropertyManager pmgr = PropertyManager.getInstance();

        if (mVessel.getName() == null)
            mVessel.setName(new RSIdentifierImpl());
        
        WidgetUtils.addEditor(panel, "Name", gbcL, PointerOperations.create(mVessel, CorePackage.Literals.NamedElement_name, CorePackage.Literals.MDIdentifier_code), gbcPE);

        if (mVessel instanceof Vessel) {
        	WidgetUtils.addEditor(panel, "Call Sign", gbcL, PointerOperations.create(mVessel, VesselPackage.Literals.Vessel_callSign), gbcPE);
            
            gbcPE.gridwidth = 1;
            gbcPE.gridx = 2;
            gbcPE.weightx = 0;
            JLabel flagLabel = new JLabel();
            panel.add(flagLabel, gbcPE);
            gbcPE.gridx = 1;
            gbcPE.weightx = 1;
            
            Pointer pointer = PointerOperations.create(mVessel, VesselPackage.Literals.Vessel_mmsi);
            IPropertyEditor mmsiEd = pmgr.getDefaultEditor(pointer);
            addProperty(panel, "MMSI", mmsiEd, gbcL, gbcPE);
            
            gbcPE.gridwidth = 2;
            mmsiEd.addPropertyChangeListener(pcl -> {
                ImageIcon icon = MIDManager.getInstance().getIcon(pcl.getNewValue().toString(), 16);
                if (icon != null)
                    flagLabel.setIcon(icon);
            });
            ImageIcon icon = MIDManager.getInstance().getIcon("" + ((Vessel) mVessel).getMmsi(), 16);
            if (icon != null)
                flagLabel.setIcon(icon);
            
            WidgetUtils.addEditor(panel, "IMO", gbcL, PointerOperations.create(mVessel, VesselPackage.Literals.Vessel_imo), gbcPE);
            WidgetUtils.addEditor(panel, "Type", gbcL, PointerOperations.create(mVessel, VesselPackage.Literals.Vessel_type), gbcPE);
        }
        return panel;
    }

    private JPanel getPosePanel() {
        JPanel posePanel = createBorderPanel("Pose");
        GridBagLayout gridBagLayout = new GridBagLayout();
        posePanel.setLayout(gridBagLayout);

        GridBagConstraints gbcL = new GridBagConstraints();
        gbcL.insets = new Insets(0, 0, 0, 5);
        gbcL.anchor = GridBagConstraints.WEST;
        gbcL.gridx = 0;
        gbcL.gridy = 0;

        GridBagConstraints gbcPE = new GridBagConstraints();
        gbcPE.anchor = GridBagConstraints.WEST;
        gbcPE.fill = GridBagConstraints.HORIZONTAL;
        gbcPE.gridx = 1;
        gbcPE.gridy = 0;
        gbcPE.weightx = 1;
        gbcPE.gridwidth = 2;

        Pose pose = mVessel.getPose();
        Coordinate coord = pose.getCoordinate();
        if (coord == null)
            pose.setCoordinate(coord = new CoordinateImpl(0.0, 0.0, CRSUtils.WGS84_2D));
        if (coord.getCrs() == null)
            coord.setCrs(CRSUtils.WGS84_2D);

        WidgetUtils.addEditor(posePanel, "Latitude", gbcL, PointerOperations.create(coord, SpatialPackage.Literals.Coordinate_x), gbcPE);
        WidgetUtils.addEditor(posePanel, "Longitude", gbcL, PointerOperations.create(coord, SpatialPackage.Literals.Coordinate_y), gbcPE);

        Orientation ori = pose.getOrientation();
        if (ori == null)
            pose.setOrientation(ori = new EulerImpl(0, 0, 0, AngleUnit.DEGREE));
        else if (ori instanceof Quaternion)
            pose.setOrientation(ori = ori.toEuler());
        
        WidgetUtils.addEditor(posePanel, "Heading", gbcL, PointerOperations.create(ori, UnitsPackage.Literals.Euler_z), gbcPE);
        return posePanel;
    }

    private JPanel createBorderPanel(String string) {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), string));
        return panel;
    }

    private void addProperty(JPanel parent, String label, IPropertyEditor editor, GridBagConstraints gbcL, GridBagConstraints gbcPE) {
        if (editor != null) {
            parent.add(new JLabel(label), gbcL);
            gbcL.gridy++;
            parent.add(editor.getEditor(), gbcPE);
            gbcPE.gridy++;
        }
    }

}
