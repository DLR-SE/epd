package de.emir.rcp.parts.vesseleditor.view.panels;

import de.emir.model.universal.physics.ObjectSurfaceInformation;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.impl.DistanceImpl;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.parts.VesselEditorBasic;
import de.emir.rcp.parts.vesseleditor.view.parts.AbstractPhysicalObjectPart;
import de.emir.rcp.util.WidgetUtils;
import de.emir.tuml.ucore.runtime.IDisposable;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;
import de.emir.tuml.ucore.runtime.utils.UCoreUtils;
import io.reactivex.rxjava3.disposables.Disposable;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates a panel that allows users to modify the size of a Geometry (length, width, height). Modifications
 * will scale the geometry accordingly. A scale of 0.0 is prohibited, as this would result in a zero size
 * geometry that cannot be further scaled.
 */
public class SizePanel extends JPanel {
    private static final double DEFAULT_DISTANCE_VALUE = 0.01;
    private static final DistanceUnit DEFAULT_DISTANCE_UNIT = DistanceUnit.METER;

    private final GenericProperty<Length> mGeometryLength;
    private final GenericProperty<Length> mGeometryWidth;
    private final GenericProperty<Length> mGeometryHeight;

    private final AbstractPhysicalObjectPart editorPart;

    private final ITreeValueChangeListener objectTreeListener = notification -> updateProperties();
    private PropertyChangeListener mChangeListener;
    private final List<Disposable> registeredConsumer = new ArrayList<>();
    private final List<IDisposable> registeredTreeListeners = new ArrayList<>();

    /**
     * Editor lock is true when changes are made from this editor, which should not update the view,
     * Otherwise UI changes trigger property changes, which trigger UI changes, resulting in an infinite
     * loop.
     */
    private boolean editorLock;

    public SizePanel(AbstractPhysicalObjectPart editor) {
        editorPart = editor;
        editorLock = false;

        // For these properties a default value must be defined! Note that settings the properties value with prop.set()
        // will break the editor for this property. Therefore, make sure that prop.set() isn't called, instead use
        // prop.get().set(), which doesn't break the reference
        this.mGeometryLength = new GenericProperty<>(
                "Geometry Length",
                "Length of the Geometry",
                true,
                new DistanceImpl(0, DistanceUnit.METER)
        );
        this.mGeometryWidth = new GenericProperty<>(
                "Geometry Width",
                "Width of the Geometry",
                true,
                new DistanceImpl(0, DistanceUnit.METER)
        );
        this.mGeometryHeight = new GenericProperty<>(
                "Geometry Height",
                "Height of the Geometry",
                true,
                new DistanceImpl(0, DistanceUnit.METER)
        );

        if (PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID) != null) {
            // read the initial values for our properties
            updateProperties();
        }

        setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Size"));
        setLayout(new GridBagLayout());

        GridBagConstraints gbcL = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0);
        GridBagConstraints gbcE = new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);

        WidgetUtils.addEditor(this, "Length", gbcL, this.mGeometryLength, gbcE);
        WidgetUtils.addEditor(this, "Width", gbcL, this.mGeometryWidth, gbcE);
        WidgetUtils.addEditor(this, "Height", gbcL, this.mGeometryHeight, gbcE);

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

        // check for changes in the underlying physical object
        PhysicalObject object = editorPart.getPhysicalObject();
        List<ObjectSurfaceInformation> objectSurfaceInformationList = UCoreUtils.collectTypedChildrenList(object, ObjectSurfaceInformation.class);
        if (objectSurfaceInformationList != null) {
            for (ObjectSurfaceInformation information : objectSurfaceInformationList) {
                IDisposable disposable = information.registerTreeListener(objectTreeListener);
                registeredTreeListeners.add(disposable);
            }
        }

        // register listener for property changes, this happens when users edit the UI
        mChangeListener = pcl -> {
            if (!editorLock) {
                editorLock = true;

                // dimension cannot be zero, otherwise we cannot scale the model!
                if (mGeometryLength.get().getValue() == 0.0) {
                    mGeometryLength.get().set(DEFAULT_DISTANCE_VALUE, DEFAULT_DISTANCE_UNIT);
                }

                // dimension cannot be zero, otherwise we cannot scale the model!
                if (mGeometryWidth.get().getValue() == 0.0) {
                    mGeometryWidth.get().set(DEFAULT_DISTANCE_VALUE, DEFAULT_DISTANCE_UNIT);
                }

                // dimension cannot be zero, otherwise we cannot scale the model!
                if (mGeometryHeight.get().getValue() == 0.0) {
                    mGeometryHeight.get().set(DEFAULT_DISTANCE_VALUE, DEFAULT_DISTANCE_UNIT);
                }

                editorPart.changeScaleFromProperty(mGeometryLength.get(), mGeometryWidth.get(), mGeometryHeight.get());

                editorLock = false;
            }
        };

        mGeometryLength.addPropertyChangeListener(mChangeListener);
        mGeometryWidth.addPropertyChangeListener(mChangeListener);
        mGeometryHeight.addPropertyChangeListener(mChangeListener);

        updateProperties();

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

        mGeometryLength.removePropertyChangeListener(mChangeListener);
        mGeometryWidth.removePropertyChangeListener(mChangeListener);
        mGeometryHeight.removePropertyChangeListener(mChangeListener);

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
            Object object = PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID);
            if (object instanceof ObjectSurfaceInformation) {
                ObjectSurfaceInformation objectSurface = (ObjectSurfaceInformation) object;

                Geometry geometry = objectSurface.getGeometry();
                if (geometry == null) {
                    return;
                }

                Length length = objectSurface.getLength();
                if (length == null){
                    mGeometryLength.get().set(DEFAULT_DISTANCE_VALUE, DEFAULT_DISTANCE_UNIT);
                } else if (!length.equals(mGeometryLength.get())) {
                    // make sure that get().set() is called instead of just set()
                    // set alone will break the reference to the ui editor part
                    mGeometryLength.get().set(length);
                }

                Length width = objectSurface.getWidth();
                if (width == null){
                    mGeometryWidth.get().set(DEFAULT_DISTANCE_VALUE, DEFAULT_DISTANCE_UNIT);
                } else if (!width.equals(mGeometryWidth.get())) {
                    // make sure that get().set() is called instead of just set()
                    // set alone will break the reference to the ui editor part
                    mGeometryWidth.get().set(width);
                }

                Length height = objectSurface.getHeight();
                if (height == null){
                    mGeometryHeight.get().set(DEFAULT_DISTANCE_VALUE, DEFAULT_DISTANCE_UNIT);
                } else if (!height.equals(mGeometryHeight.get())) {
                    // make sure that get().set() is called instead of just set()
                    // set alone will break the reference to the ui editor part
                    mGeometryHeight.get().set(height);
                }
            }
            editorLock = false;
        }
    }
}
