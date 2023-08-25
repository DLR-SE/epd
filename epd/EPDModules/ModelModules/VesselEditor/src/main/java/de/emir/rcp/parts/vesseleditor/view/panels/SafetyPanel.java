package de.emir.rcp.parts.vesseleditor.view.panels;

import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.VesselSafetyCharacteristic;
import de.emir.model.domain.maritime.vessel.impl.VesselSafetyCharacteristicImpl;
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

public class SafetyPanel extends JPanel {


    private GenericProperty<Length> underKeelClearance;
    private GenericProperty<Length> personalSpace;

    private AbstractPhysicalObjectPart editorPart;

    public SafetyPanel(AbstractPhysicalObjectPart editor) {
        editorPart = editor;

        this.underKeelClearance = new GenericProperty<>("Under Keel Clearance", "", true);
        this.personalSpace = new GenericProperty<>("Personal Space", "", true);


        if (PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID) != null) {
            update();
        }

        setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Safety"));
        setLayout(new GridBagLayout());

        GridBagConstraints gbcL = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0);
        GridBagConstraints gbcE = new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);

        WidgetUtils.addEditor(this, "UnderKeelClearance", gbcL, underKeelClearance, gbcE);
        WidgetUtils.addEditor(this, "PersonalSpace", gbcL, personalSpace, gbcE);

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
            VesselSafetyCharacteristic vsc = vessel.getFirstCharacteristic(VesselSafetyCharacteristic.class,true);
            if(vsc == null){
                vsc = new VesselSafetyCharacteristicImpl();
                vessel.getCharacteristics().add(vsc);
            }

            Length ukc = (vsc.getUnderKeelClearance() != null) ? vsc.getUnderKeelClearance() :new LengthImpl();
            if(vsc.getUnderKeelClearance() == null){
                vsc.setUnderKeelClearance(ukc);
            }
            Length ps = (vsc.getPersonalSpace() != null) ? vsc.getPersonalSpace() : new LengthImpl();
            if(vsc.getPersonalSpace() == null){
                vsc.setPersonalSpace(ps);
            }

            underKeelClearance.set(ukc);
            personalSpace.set(ps);
        }
    }
}
