package de.emir.rcp.parts.vesseleditor.view.panels;

import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.VesselSafetyCharacteristic;
import de.emir.model.domain.maritime.vessel.impl.VesselSafetyCharacteristicImpl;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.impl.LengthImpl;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.parts.VesselEditorBasic;
import de.emir.rcp.parts.vesseleditor.view.parts.AbstractPhysicalObjectPart;
import de.emir.rcp.util.WidgetUtils;
import de.emir.tuml.ucore.runtime.IDisposable;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;
import io.reactivex.rxjava3.disposables.Disposable;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates a panel that lets users edit properties of the VesselSafetyCharacteristic. If this characteristic is not
 * present it will automatically be added to the PhysicalObject. This ensures that this panel can be used without
 * prerequisites. Subclasses of VesselSafetyCharacteristic are allowed.
 *
 * @note: No need to register listeners on our properties. Their internal value will be the ones from the model. Thus,
 * it will directly set data in the model.
 */
public class SafetyPanel extends JPanel {
    private static final double DEFAULT_LENGTH_VALUE = 0.0;
    private static final DistanceUnit DEFAULT_LENGTH_UNIT = DistanceUnit.METER;

    private final GenericProperty<Length> underKeelClearance;
    private final GenericProperty<Length> personalSpace;

    private final AbstractPhysicalObjectPart editorPart;

    /**
     * Editor lock is true when changes are made from this editor, which should not update the view,
     * Otherwise UI changes trigger property changes, which trigger UI changes, resulting in an infinite
     * loop.
     */
    private boolean editorLock;

    private final java.util.List<Disposable> registeredConsumer = new ArrayList<>();
    private final List<IDisposable> registeredTreeListeners = new ArrayList<>();

    public SafetyPanel(AbstractPhysicalObjectPart editor) {
        editorPart = editor;

        this.underKeelClearance = new GenericProperty<>("Under Keel Clearance", "", true);
        this.personalSpace = new GenericProperty<>("Personal Space", "", true);

        if (PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID) != null) {
            // read the initial values for our properties
            updateProperties();
        }

        setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Safety"));
        setLayout(new GridBagLayout());

        GridBagConstraints gbcL = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0);
        GridBagConstraints gbcE = new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);

        WidgetUtils.addEditor(this, "UnderKeelClearance", gbcL, underKeelClearance, gbcE);
        WidgetUtils.addEditor(this, "PersonalSpace", gbcL, personalSpace, gbcE);

        setMaximumSize(getPreferredSize());
    }

    /**
     * Informs use that this panel got added to something. Thus, we can register listeners
     * which get removed when this panel is removed (removeNotify).
     */
    @Override
    public void addNotify() {
        // check for changes in the selected geometry or view
        Disposable objectSurfaceSelectionDisposable = PlatformUtil.getSelectionManager().subscribe(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID, o -> updateProperties());
        registeredConsumer.add(objectSurfaceSelectionDisposable);
        Disposable objectSurfaaceViewSelectionDisposable = PlatformUtil.getSelectionManager().subscribe(VesselEditorBasic.CTX_VIEW_SELECTION_ID, o -> updateProperties());
        registeredConsumer.add(objectSurfaaceViewSelectionDisposable);

        IDisposable disposable = editorPart.getPhysicalObject().registerTreeListener(notification -> {
            if (notification.getNewValue() == null) {
                updateProperties();
                return;
            }

            if (!notification.getNewValue().equals(notification.getOldValue())) {
                updateProperties();
            }
        });
        registeredTreeListeners.add(disposable);

        super.addNotify();
    }

    /**
     * Unregister listeners when a panel got removed
     */
    @Override
    public void removeNotify() {
        // unregister consumers
        for (Disposable disposable : registeredConsumer){
            disposable.dispose();
        }

        // unregister tree listeners
        for (IDisposable disposable : registeredTreeListeners){
            disposable.dispose();
        }

        registeredTreeListeners.clear();
        registeredConsumer.clear();

        super.removeNotify();
    }

    /**
     * Update is called when the underlying model is changed, and we need to update our properties and therefore
     * the UI.
     */
    private void updateProperties() {
        // make sure that setting properties does not result in scaling the geometry over and over
        if (!editorLock) {
            editorLock = true;
            try {
                PhysicalObject physicalObject = editorPart.getPhysicalObject();
                if (physicalObject instanceof Vessel) {
                    Vessel vessel = (Vessel) physicalObject;
                    VesselSafetyCharacteristic vsc = vessel.getFirstCharacteristic(
                            VesselSafetyCharacteristic.class,
                            true
                    );
                    if (vsc == null) {
                        vsc = new VesselSafetyCharacteristicImpl();
                        vessel.getCharacteristics().add(vsc);
                    }

                    // make sure that each value is set
                    if (vsc.getUnderKeelClearance() == null) {
                        vsc.setUnderKeelClearance(new LengthImpl(DEFAULT_LENGTH_VALUE, DEFAULT_LENGTH_UNIT));
                    }
                    if (vsc.getPersonalSpace() == null) {
                        vsc.setPersonalSpace(new LengthImpl(DEFAULT_LENGTH_VALUE, DEFAULT_LENGTH_UNIT));
                    }

                    // bind the units to our properties
                    underKeelClearance.set(vsc.getUnderKeelClearance());
                    personalSpace.set(vsc.getPersonalSpace());
                }
            } finally {
                editorLock = false;
            }
        }
    }
}
