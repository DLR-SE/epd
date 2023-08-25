package de.emir.rcp.parts.vesseleditor.view.panels;

import de.emir.model.universal.physics.ObjectSurfaceInformation;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.impl.DistanceImpl;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.parts.VesselEditorBasic;
import de.emir.rcp.parts.vesseleditor.utils.GeometryUtil;
import de.emir.rcp.parts.vesseleditor.utils.View;
import de.emir.rcp.parts.vesseleditor.view.parts.AbstractPhysicalObjectPart;
import de.emir.rcp.util.WidgetUtils;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.beans.PropertyChangeListener;

public class SizePanel extends JPanel {
    private GenericProperty<Length> mGeometryLength;
    private GenericProperty<Length> mGeometryWidth;
    private GenericProperty<Length> mGeometryHeight;

    private AbstractPhysicalObjectPart editorPart;
    private PropertyChangeListener mChangeListener;

    private boolean editorLock;

    public SizePanel(AbstractPhysicalObjectPart editor) {
        editorPart = editor;
        editorLock = false;

        //for this property a default value must be defined! changing its internal value will break the editor for this property
        //therefore make sure that prop.set() isn't called, instead use prop.get().set(), which doesn't break the reference
        this.mGeometryLength = new GenericProperty<>("Length of the Geometry", "", true, new DistanceImpl(0, DistanceUnit.METER));
        this.mGeometryWidth = new GenericProperty<>("Width of the Geometry", "", true, new DistanceImpl(0, DistanceUnit.METER));
        this.mGeometryHeight = new GenericProperty<>("Height of the Geometry", "", true, new DistanceImpl(0, DistanceUnit.METER));

        if (PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID) != null) {
            update();
        }

        setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Size"));
        setLayout(new GridBagLayout());

        GridBagConstraints gbcL = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0);
        GridBagConstraints gbcE = new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);

        WidgetUtils.addEditor(this, "Length", gbcL, this.mGeometryLength, gbcE);
        WidgetUtils.addEditor(this, "Width", gbcL, this.mGeometryWidth, gbcE);
        WidgetUtils.addEditor(this, "Height", gbcL, this.mGeometryHeight, gbcE);

        setMaximumSize(getPreferredSize());

        initListeners();
    }

    private void initListeners() {
        PlatformUtil.getSelectionManager().subscribe(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID, o -> change());
        PlatformUtil.getSelectionManager().subscribe(VesselEditorBasic.CTX_VIEW_SELECTION_ID, o -> change());

        editorPart.getPhysicalObject().registerTreeListener(notification -> {
            GeometryOperations del = GeometryUtil.getGeometry(editorPart.getView(), editorPart.getSurfaceInformation()).getDelegate();
            if (del != null) {
                del.invalidate();
            }
            update();
        });

        mChangeListener =  pcl -> {
            if(!editorLock){
                editorLock = true;

                editorPart.changeScaleFromProperty(mGeometryLength.get(), mGeometryWidth.get(), mGeometryHeight.get());
                editorLock = false;
            }
        };

        mGeometryLength.addPropertyChangeListener(mChangeListener);
        mGeometryWidth.addPropertyChangeListener(mChangeListener);
        mGeometryHeight.addPropertyChangeListener(mChangeListener);
    }

    private void update() {
        if(!editorLock){
            editorLock = true;
            Object object = PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID);
            if (object instanceof ObjectSurfaceInformation) {
                ObjectSurfaceInformation objectSurface = (ObjectSurfaceInformation) object;

                Geometry geometry = objectSurface.getGeometry();
                if (geometry == null){
                    return;
                }
                Envelope env = geometry.getEnvelope();
                if(env == null){
                    mGeometryLength.get().set(new DistanceImpl(0.1, DistanceUnit.METER));
                    mGeometryWidth.get().set(new DistanceImpl(0.1, DistanceUnit.METER));
                    mGeometryHeight.get().set(new DistanceImpl(0.1, DistanceUnit.METER));
                    return;
                }
                Length l = objectSurface.getLength();                                            //make sure that get().set() is called instead of just set()
                if (l != null && !l.equals(mGeometryLength.get())) {
                    mGeometryLength.removePropertyChangeListener(mChangeListener);
                    mGeometryLength.get().set(l); //set will break the reference to the ui editor part
                    mGeometryLength.addPropertyChangeListener(mChangeListener);
                }

                Length w = objectSurface.getWidth();
                if (w != null && !w.equals(mGeometryWidth.get())) {
                    mGeometryWidth.removePropertyChangeListener(mChangeListener);
                    mGeometryWidth.get().set(w);
                    mGeometryWidth.addPropertyChangeListener(mChangeListener);
                }

                Length h = objectSurface.getHeight();
                if (h != null && !h.equals(mGeometryHeight.get())) {
                    mGeometryHeight.removePropertyChangeListener(mChangeListener);
                    mGeometryHeight.get().set(h);
                    mGeometryHeight.addPropertyChangeListener(mChangeListener);
                }
            }
            editorLock = false;
        }
    }

    private void change() {
        Object objSurface = PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID);
        Object objView = PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_VIEW_SELECTION_ID);

        if (objSurface instanceof ObjectSurfaceInformation && objView instanceof View) {
            Geometry geometry = GeometryUtil.getGeometry((View) objView, (ObjectSurfaceInformation) objSurface);
            GeometryOperations del = geometry.getDelegate();
            if (del != null) {
                del.invalidate();
            }
            update();
        }
    }
}
