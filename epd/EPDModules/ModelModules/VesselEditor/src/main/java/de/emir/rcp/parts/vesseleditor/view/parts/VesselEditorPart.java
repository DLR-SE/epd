package de.emir.rcp.parts.vesseleditor.view.parts;

import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.parts.VesselEditorBasic;
import de.emir.rcp.parts.vesseleditor.utils.GeometryUtil;
import de.emir.rcp.parts.vesseleditor.view.geometry.AbstractGeometryPanel;
import de.emir.rcp.parts.vesseleditor.view.panels.*;
import de.emir.rcp.parts.vesseleditor.view.panels.transferable.EquipmentPanel;
import de.emir.rcp.parts.vesseleditor.view.panels.transferable.ReferencePointPanel;
import de.emir.rcp.parts.vesseleditor.view.vessel.VesselEditorPanel;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;

/**
 * Editor which consists of {@link EquipmentPanel}, {@link GeneralVesselPanel}, {@link SizePanel} and
 * {@link WKTPanel} which lets a user change vessel dimensions and its equipment
 */
public class VesselEditorPart extends AbstractPhysicalObjectPart {

    public VesselEditorPart(Vessel pobj) {
        super(pobj);
    }

    public void doSetup() {
        //add editor sidePanel
        JComponent sidePanel = createSidePanel();
        sidePanel.setPreferredSize(new Dimension(300,100));
        add(sidePanel, BorderLayout.WEST);

        //fill extensionPoint toolbar
        JToolBar tb = new JToolBar();
        PlatformUtil.getMenuManager().fillToolbar(tb, VesselEditorBasic.TOOLBAR_ID);
        add(tb, BorderLayout.NORTH);

        addAncestorListener(new AncestorListener() {

            @Override
            public void ancestorRemoved(AncestorEvent event) {

            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

            }

            @Override
            public void ancestorAdded(AncestorEvent event) {
                //TODO sad but its actually working
                Container container = getParent().getParent().getParent();
                if (container instanceof JScrollPane) {
                    JScrollPane pane = (JScrollPane) container;
                    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                }
            }
        });
    }

    /**
     * Create side settings panel
     *
     * @return
     */
    private JComponent createSidePanel() {
        JPanel side = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        side.setLayout(gridBagLayout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        side.add(new GeneralVesselPanel(getPhysicalObject()), gbc);
        gbc.gridy++;
        side.add(new SizePanel(this), gbc);
        gbc.gridy++;
        side.add(new HullPanel(this), gbc);
        gbc.gridy++;
        side.add(new SafetyPanel(this), gbc);
        gbc.gridy++;
        side.add(new WKTPanel(this), gbc);
        gbc.gridy++;
        side.add(new EquipmentPanel(this), gbc);
        gbc.gridy++;
        side.add(new ReferencePointPanel(this), gbc);
        gbc.gridy++;

        return new JScrollPane(side);
    }

    @Override
    protected AbstractGeometryPanel createEditorPanel() {
        return new VesselEditorPanel(GeometryUtil.getGeometry(getView(), getSurfaceInformation()), getView());
    }

}
