package de.emir.rcp.parts.vesseleditor.view.panels;

import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.VesselDimensionCharacteristic;
import de.emir.model.domain.maritime.vessel.WatercraftHull;
import de.emir.model.domain.maritime.vessel.impl.VesselDimensionCharacteristicImpl;
import de.emir.model.domain.maritime.vessel.impl.WatercraftHullImpl;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.impl.LengthImpl;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.parts.VesselEditorBasic;
import de.emir.rcp.parts.vesseleditor.view.parts.AbstractPhysicalObjectPart;
import de.emir.rcp.util.WidgetUtils;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class HullPanel extends JPanel {

    private final GenericProperty<Length> draft;
    private final GenericProperty<Length> beam;
    private final GenericProperty<Length> freeBoard;
    private final GenericProperty<Length> lengthAtWaterLine;
    private final GenericProperty<Length> mouldedDepth;
    private final GenericProperty<Length> overAllLength;

    private AbstractPhysicalObjectPart editorPart;

    public HullPanel(AbstractPhysicalObjectPart editor) {
        editorPart = editor;

        this.draft = new GenericProperty<>("Draft of vessel", "", true, null);
        this.beam = new GenericProperty<>("Beam of vessel", "", true, null);
        this.freeBoard = new GenericProperty<>("Freeboard of vessel", "", true, null);
        this.lengthAtWaterLine = new GenericProperty<>("Length at waterline", "", true, null);
        this.mouldedDepth = new GenericProperty<>("moulded depth", "", true, null);
        this.overAllLength = new GenericProperty<>("overall length", "", true, null);

        if (PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID) != null) {
            update();
        }

        setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Hull"));
        setLayout(new GridBagLayout());

        GridBagConstraints gbcL = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0);
        GridBagConstraints gbcE = new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);

        WidgetUtils.addEditor(this, "Draft", gbcL, draft, gbcE);
        WidgetUtils.addEditor(this, "Beam", gbcL, beam, gbcE);
        WidgetUtils.addEditor(this, "FreeBoard", gbcL, freeBoard, gbcE);
        WidgetUtils.addEditor(this, "Length at Waterline", gbcL, lengthAtWaterLine, gbcE);
        WidgetUtils.addEditor(this, "Moulded Depth", gbcL, mouldedDepth, gbcE);
        WidgetUtils.addEditor(this, "Overall Length", gbcL, overAllLength, gbcE);

        setMaximumSize(getPreferredSize());

        initListeners();
    }

    private void initListeners() {
        PlatformUtil.getSelectionManager().subscribe(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID, o -> update());
        PlatformUtil.getSelectionManager().subscribe(VesselEditorBasic.CTX_VIEW_SELECTION_ID, o -> update());

        editorPart.getPhysicalObject().registerTreeListener(new ITreeValueChangeListener() {
            @Override
            public void onValueChange(Notification<Object> notification) {

                if(notification.getNewValue() == null){
                    update();
                    return;
                }

                if (!notification.getNewValue().equals(notification.getOldValue())) {
                    update();
                }
            }
        });
    }

    private void update() {
        PhysicalObject physicalObject = editorPart.getPhysicalObject();
        if (physicalObject instanceof Vessel) {
            Vessel vessel = (Vessel) physicalObject;
            VesselDimensionCharacteristic vdc = vessel.getFirstCharacteristic(VesselDimensionCharacteristic.class, true);

            if (vdc == null) {
                vdc = new VesselDimensionCharacteristicImpl();
                vessel.getCharacteristics().add(vdc);
            }
            WatercraftHull hull = vdc.getHull();

            if (hull == null) {
                hull = new WatercraftHullImpl();
                vdc.setHull(hull);
            }

            if (hull.getDraft() == null) hull.setDraft(new LengthImpl());
            if (hull.getBeam() == null) hull.setBeam(new LengthImpl());
            if (hull.getFreeboard() == null) hull.setFreeboard(new LengthImpl());
            if (hull.getLengthAtWaterline() == null) hull.setLengthAtWaterline(new LengthImpl());
            if (hull.getMouldedDepth() == null) hull.setMouldedDepth(new LengthImpl());
            if (hull.getOverAllLength() == null) hull.setOverAllLength(new LengthImpl());

            draft.set(hull.getDraft());
            beam.set(hull.getBeam());
            freeBoard.set(hull.getFreeboard());
            lengthAtWaterLine.set(hull.getLengthAtWaterline());
            mouldedDepth.set(hull.getMouldedDepth());
            overAllLength.set(hull.getOverAllLength());
        }
    }
}
