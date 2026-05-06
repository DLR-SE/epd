package de.emir.rcp.parts.vesseleditor.view.panels;

import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.VesselDimensionCharacteristic;
import de.emir.model.domain.maritime.vessel.WatercraftHull;
import de.emir.model.domain.maritime.vessel.impl.VesselDimensionCharacteristicImpl;
import de.emir.model.domain.maritime.vessel.impl.WatercraftHullImpl;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.impl.LengthImpl;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.parts.VesselEditorBasic;
import de.emir.rcp.parts.vesseleditor.view.parts.AbstractPhysicalObjectPart;
import de.emir.rcp.util.WidgetUtils;
import de.emir.tuml.ucore.runtime.*;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;
import de.emir.tuml.ucore.runtime.utils.UCoreUtils;
import io.reactivex.rxjava3.disposables.Disposable;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates a panel that lets users edit properties of the VesselDimensionCharacteristic. If this characteristic is not
 * present it will automatically be added to the PhysicalObject. This ensures that this panel can be used without
 * prerequisites. Subclasses of VesselDimensionCharacteristic are allowed.
 *
 * @note: No need to register listeners on our properties. Their internal value will be the ones from the model. Thus,
 * it will directly set data in the model.
 */
public class HullPanel extends JPanel {
    private static final double DEFAULT_LENGTH_VALUE = 0.0;
    private static final DistanceUnit DEFAULT_LENGTH_UNIT = DistanceUnit.METER;

    private final GenericProperty<Length> draft;
    private final GenericProperty<Length> beam;
    private final GenericProperty<Length> freeBoard;
    private final GenericProperty<Length> lengthAtWaterLine;
    private final GenericProperty<Length> mouldedDepth;
    private final GenericProperty<Length> overAllLength;

    private final AbstractPhysicalObjectPart editorPart;

    /**
     * Editor lock is true when changes are made from this editor, which should not update the view,
     * Otherwise UI changes trigger property changes, which trigger UI changes, resulting in an infinite
     * loop.
     */
    private boolean editorLock;

    private final java.util.List<Disposable> registeredConsumer = new ArrayList<>();
    private final List<IDisposable> registeredTreeListeners = new ArrayList<>();

    public HullPanel(AbstractPhysicalObjectPart editor) {
        editorPart = editor;

        this.draft = new GenericProperty<>("Draft of vessel", "", true, null);
        this.beam = new GenericProperty<>("Beam of vessel", "", true, null);
        this.freeBoard = new GenericProperty<>("Freeboard of vessel", "", true, null);
        this.lengthAtWaterLine = new GenericProperty<>("Length at waterline", "", true, null);
        this.mouldedDepth = new GenericProperty<>("moulded depth", "", true, null);
        this.overAllLength = new GenericProperty<>("overall length", "", true, null);

        if (PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID) != null) {
            // read the initial values for our properties
            updateProperties();
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
    }

    /**
     * Informs use that this panel got added to something. Thus, we can register listeners
     * which get removed when this panel is removed (removeNotify).
     */
    @Override
    public void addNotify() {
        // check for changes in the selected geometry or view
        Disposable objectSurfaceSelectionDisposable = PlatformUtil.getSelectionManager()
                .subscribe(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID, o -> updateProperties());
        registeredConsumer.add(objectSurfaceSelectionDisposable);
        Disposable objectSurfaaceViewSelectionDisposable = PlatformUtil.getSelectionManager()
                .subscribe(VesselEditorBasic.CTX_VIEW_SELECTION_ID, o -> updateProperties());
        registeredConsumer.add(objectSurfaaceViewSelectionDisposable);

        IDisposable disposable = editorPart.getPhysicalObject().registerTreeListener(notification -> {
            if (notification.getNewValue() == null) {
                updateProperties();
                return;
            }

            // check if the VesselDimensionCharacteristic was changed
            if (UCoreUtils.getFirstParent(notification.getInstance(), VesselDimensionCharacteristic.class) != null) {
                if (!notification.getNewValue().equals(notification.getOldValue())) {
                    updateProperties();
                }
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
        for (Disposable disposable : registeredConsumer) {
            disposable.dispose();
        }

        // unregister tree listeners
        for (IDisposable disposable : registeredTreeListeners) {
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
                    VesselDimensionCharacteristic vdc = vessel.getFirstCharacteristic(
                            VesselDimensionCharacteristic.class,
                            true
                    );

                    if (vdc == null) {
                        vdc = new VesselDimensionCharacteristicImpl();
                        vessel.getCharacteristics().add(vdc);
                    }
                    WatercraftHull hull = vdc.getHull();

                    if (hull == null) {
                        hull = new WatercraftHullImpl();
                        vdc.setHull(hull);
                    }

                    // make sure that each value is set
                    if (hull.getDraft() == null) {
                        hull.setDraft(new LengthImpl(DEFAULT_LENGTH_VALUE, DEFAULT_LENGTH_UNIT));
                    }
                    if (hull.getBeam() == null) {
                        hull.setBeam(new LengthImpl(DEFAULT_LENGTH_VALUE, DEFAULT_LENGTH_UNIT));
                    }
                    if (hull.getFreeboard() == null) {
                        hull.setFreeboard(new LengthImpl(DEFAULT_LENGTH_VALUE, DEFAULT_LENGTH_UNIT));
                    }
                    if (hull.getLengthAtWaterline() == null) {
                        hull.setLengthAtWaterline(new LengthImpl(DEFAULT_LENGTH_VALUE, DEFAULT_LENGTH_UNIT));
                    }
                    if (hull.getMouldedDepth() == null) {
                        hull.setMouldedDepth(new LengthImpl(DEFAULT_LENGTH_VALUE, DEFAULT_LENGTH_UNIT));
                    }
                    if (hull.getOverAllLength() == null) {
                        hull.setOverAllLength(new LengthImpl(DEFAULT_LENGTH_VALUE, DEFAULT_LENGTH_UNIT));
                    }

                    // bind the units to our properties
                    draft.set(hull.getDraft());
                    beam.set(hull.getBeam());
                    freeBoard.set(hull.getFreeboard());
                    lengthAtWaterLine.set(hull.getLengthAtWaterline());
                    mouldedDepth.set(hull.getMouldedDepth());
                    overAllLength.set(hull.getOverAllLength());
                }
            } finally {
                editorLock = false;
            }
        }
    }
}
