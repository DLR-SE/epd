package de.emir.rcp.parts.vesseleditor.view.parts;

import de.emir.model.universal.math.Vector;
import de.emir.model.universal.physics.MultiViewObjectSurfaceInforamtion;
import de.emir.model.universal.physics.ObjectSurfaceInformation;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.sf.impl.PolygonImpl;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Length;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.transactions.CompoundTransaction;
import de.emir.rcp.model.transactions.SetValueTransaction;
import de.emir.rcp.parts.VesselEditorBasic;
import de.emir.rcp.parts.vesseleditor.cmds.transactions.CreateChildTransaction;
import de.emir.rcp.parts.vesseleditor.cmds.transactions.ScaleGeometryTransaction;
import de.emir.rcp.parts.vesseleditor.utils.GeometryUtil;
import de.emir.rcp.parts.vesseleditor.utils.PredefinedGeometryItem;
import de.emir.rcp.parts.vesseleditor.utils.View;
import de.emir.rcp.parts.vesseleditor.view.geometry.AbstractGeometryPanel;
import de.emir.service.geometry.impl.WKTUtil;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public abstract class AbstractPhysicalObjectPart extends JPanel {
    private boolean mEdtScaleActivated = false;

    private PhysicalObject physicalObject;
    private JComponent chooseCharacteristicPanel;
    private AbstractGeometryPanel editorPanel;

    public AbstractPhysicalObjectPart(PhysicalObject pobj) {
        physicalObject = pobj;
        setup();
    }

    protected void setup() {
        setLayout(new BorderLayout());

        ObjectSurfaceInformation objectSurface = getPhysicalObject().getFirstCharacteristic(ObjectSurfaceInformation.class, true);

        if (objectSurface == null) {
            chooseCharacteristicPanel = setupChooseCharacteristicPanel();
            add(chooseCharacteristicPanel, BorderLayout.CENTER);
        } else {
            PlatformUtil.getSelectionManager().setSelection(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID, objectSurface);

            if (editorPanel == null) {
                editorPanel = createEditorPanel();
            }

            add(editorPanel, BorderLayout.CENTER);

            initListeners();

            doSetup();
        }
    }

    protected abstract AbstractGeometryPanel createEditorPanel();

    protected abstract void doSetup();

    private void initListeners() {
//        needed because removing and adding listeners to geometries causes concurrent modification exception
        getPhysicalObject().registerTreeListener(notification -> onModelChanged());
        PlatformUtil.getSelectionManager().subscribe(VesselEditorBasic.CTX_VIEW_SELECTION_ID, e -> onModelChanged());
    }

    private JComponent setupChooseCharacteristicPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = gbc.gridy = 0;
        Collection<UClassifier> classes = UCoreMetaRepository.getClassesInheritFrom(PhysicsPackage.Literals.ObjectSurfaceInformation, true);
        for (UClassifier cl : classes) {
            JButton btn = new JButton("create " + cl.getName());
            panel.add(btn, gbc);
            gbc.gridy++;
            btn.addActionListener(acl -> {
                onChooseCharacteristic(cl);
            });
        }
        return new JScrollPane(panel);
    }

    protected void onChooseCharacteristic(UClassifier cl) {

        CreateChildTransaction physicalCharacteristics = new CreateChildTransaction(PointerOperations.create(getPhysicalObject(), PhysicsPackage.Literals.PhysicalObject_characteristics), (UClass) cl);
        PlatformUtil.getModelManager().getModelProvider().getTransactionStack().run(physicalCharacteristics);

        ObjectSurfaceInformation objectSurface = getPhysicalObject().getFirstCharacteristic(ObjectSurfaceInformation.class, true);

        CreateChildTransaction geometryCharacteristics = new CreateChildTransaction(PointerOperations.create(objectSurface, PhysicsPackage.Literals.ObjectSurfaceInformation_geometry), new PolygonImpl().getUClassifier());
        PlatformUtil.getModelManager().getModelProvider().getTransactionStack().run(geometryCharacteristics);

        PlatformUtil.getSelectionManager().setSelection(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID, objectSurface);

        PredefinedGeometryItem[] items = PredefinedGeometryItem.getPredefinedGeometryItems().values().toArray(new PredefinedGeometryItem[0]);

        PredefinedGeometryItem geometryItem = items[0];

        ObjectSurfaceInformation osi = getSurfaceInformation();

        CompoundTransaction compoundTransaction = new CompoundTransaction();

        WKTUtil wktUtil = new WKTUtil();
        Geometry topGeometry = wktUtil.loadWKT(geometryItem.wktTop);
        compoundTransaction.add(new SetValueTransaction(osi, PhysicsPackage.Literals.ObjectSurfaceInformation_geometry, topGeometry));

        if (osi instanceof MultiViewObjectSurfaceInforamtion) {
            Geometry frontGeometry = wktUtil.loadWKT(geometryItem.wktFront);
            compoundTransaction.add(new SetValueTransaction(osi, PhysicsPackage.Literals.MultiViewObjectSurfaceInforamtion_frontGeometry, frontGeometry));
            Geometry sideGeometry = wktUtil.loadWKT(geometryItem.wktSide);
            compoundTransaction.add(new SetValueTransaction(osi, PhysicsPackage.Literals.MultiViewObjectSurfaceInforamtion_sideGeometry, sideGeometry));
        }

        PlatformUtil.getModelManager().getModelProvider().getTransactionStack().run(compoundTransaction);


        //new child is created, setup editorGeometryPanel
        remove(chooseCharacteristicPanel);

        if (editorPanel == null) {
            editorPanel = createEditorPanel();
        }

        add(editorPanel);

        initListeners();

        doSetup();
    }

    public AbstractGeometryPanel getEditorPanel() {
        return editorPanel;
    }

    public <T extends PhysicalObject> T getPhysicalObject() {
        return (T) physicalObject;
    }

    public ObjectSurfaceInformation getSurfaceInformation() {
        return (ObjectSurfaceInformation) PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID);
    }

    public View getView() {
        View view = (View) PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_VIEW_SELECTION_ID);
        if (view == null) {
            view = View.TOP;
            PlatformUtil.getSelectionManager().setSelection(VesselEditorBasic.CTX_VIEW_SELECTION_ID, view);
        }
        return view;
    }

    public void onModelChanged() {
        editorPanel.changeGeometry(GeometryUtil.getGeometry(getView(), getSurfaceInformation()), getView());
    }

    /**
     * Change scale of the current geometry
     */
    public void changeScaleFromGeometryEditor() {
        if ((getSurfaceInformation() instanceof MultiViewObjectSurfaceInforamtion) == false) {
            return;
        }

        mEdtScaleActivated = true;
        try {
            View v = getView(); //detect what has been changed by the editor
            Geometry geom = GeometryUtil.getGeometry(v, getSurfaceInformation()); //the geometry that has already been scaled
            Vector size = null;
            Envelope env = geom.getEnvelope(); //the new values
            CompoundTransaction ct = new CompoundTransaction();
            switch (v) {
                case FRONT: {
                    //front shows width and height
                    size = env.getSize(DistanceUnit.METER);
                    Geometry si = GeometryUtil.getGeometry(View.SIDE, getSurfaceInformation());
                    ct.add(new ScaleGeometryTransaction(si, 1, size.get(1) / si.getEnvelope().getSize(DistanceUnit.METER).get(1)));
                    Geometry to = GeometryUtil.getGeometry(View.TOP, getSurfaceInformation());
                    ct.add(new ScaleGeometryTransaction(to, size.get(1) / to.getEnvelope().getSize(DistanceUnit.METER).get(0), 1));
                    break;
                }
                case SIDE: {
                    //shows length and height
                    size = env.getSize(DistanceUnit.METER);
                    Geometry fr = GeometryUtil.getGeometry(View.FRONT, getSurfaceInformation());
                    ct.add(new ScaleGeometryTransaction(fr, 1, size.get(1) / fr.getEnvelope().getSize(DistanceUnit.METER).get(1)));
                    Geometry to = GeometryUtil.getGeometry(View.TOP, getSurfaceInformation());
                    ct.add(new ScaleGeometryTransaction(to, 1, size.get(1) / to.getEnvelope().getSize(DistanceUnit.METER).get(1)));
                    break;
                }
                case TOP: {
                    //shows width and length
                    size = env.getSize(DistanceUnit.METER);
                    Geometry fr = GeometryUtil.getGeometry(View.FRONT, getSurfaceInformation());
                    ct.add(new ScaleGeometryTransaction(fr, size.get(0) / fr.getEnvelope().getSize(DistanceUnit.METER).get(0), 1));
                    Geometry si = GeometryUtil.getGeometry(View.SIDE, getSurfaceInformation());
                    ct.add(new ScaleGeometryTransaction(fr, size.get(1) / si.getEnvelope().getSize(DistanceUnit.METER).get(0), 1));
                    break;
                }
            }
            PlatformUtil.getModelManager().getModelProvider().getTransactionStack().run(ct);

        } finally {
            mEdtScaleActivated = false;
        }
    }

    /**
     * Change scale of the current geometry
     */
    public void changeScaleFromProperty(Length nl, Length nw, Length nh) {
        if (mEdtScaleActivated)
            return;
        if (nl == null || nw == null || nh == null)
        	return ;
        if (nl.getValue() == 0 || nw.getValue() == 0 || nh.getValue() == 0) {
        	ULog.error("Invalid Scale of 0 detected");
        	return ;
        }
        try {
            mEdtScaleActivated = true;
            ObjectSurfaceInformation osi = getSurfaceInformation();
            Length ol = osi.getLength();
            Length ow = osi.getWidth();
            Length oh = osi.getHeight();

            CompoundTransaction ct = new CompoundTransaction();
            if (nl != null && ol != null && nl.equals(ol) == false) {
                //length is shown by SIDE and TOP
                double f = nl.getAs(DistanceUnit.METER) / ol.getAs(DistanceUnit.METER);
                if (f != 1.0) {
                    if (GeometryUtil.getGeometry(View.SIDE, getSurfaceInformation()) != null)
                        ct.add(new ScaleGeometryTransaction(GeometryUtil.getGeometry(View.SIDE, getSurfaceInformation()), f, 1));
                    if (GeometryUtil.getGeometry(View.TOP, getSurfaceInformation()) != null)
                        ct.add(new ScaleGeometryTransaction(GeometryUtil.getGeometry(View.TOP, getSurfaceInformation()), 1, f));
                }
            }
            if (nw != null && ow != null && nw.equals(ow) == false) {
                //width is shown by TOP (Y-Comp) and FRONT (X-Comp)
                double f = nw.getAs(DistanceUnit.METER) / ow.getAs(DistanceUnit.METER);
                if (f != 1.0) {
                    if (GeometryUtil.getGeometry(View.TOP, getSurfaceInformation()) != null)
                        ct.add(new ScaleGeometryTransaction(GeometryUtil.getGeometry(View.TOP, getSurfaceInformation()), f, 1));
                    if (GeometryUtil.getGeometry(View.FRONT, getSurfaceInformation()) != null)
                        ct.add(new ScaleGeometryTransaction(GeometryUtil.getGeometry(View.FRONT, getSurfaceInformation()), f, 1));
                }
            }
            if (nh != null && oh != null && nh.equals(oh) == false) {
                //height is shown by SIDE(Y-COMP) && FRONT (Y-Comp)
                double f1 = nh.getAs(DistanceUnit.METER) / oh.getAs(DistanceUnit.METER);
                if (f1 != 1) {
                    if (GeometryUtil.getGeometry(View.SIDE, getSurfaceInformation()) != null)
                        ct.add(new ScaleGeometryTransaction(GeometryUtil.getGeometry(View.SIDE, getSurfaceInformation()), 1, f1));
                    if (GeometryUtil.getGeometry(View.FRONT, getSurfaceInformation()) != null)
                        ct.add(new ScaleGeometryTransaction(GeometryUtil.getGeometry(View.FRONT, getSurfaceInformation()), 1, f1));
                }
            }
            if (ct.isEmpty() == false){
                PlatformUtil.getModelManager().getModelProvider().getTransactionStack().run(ct);
                if(ol != null && nl != null) ol.set(nl);
                if(ow != null && nw != null) ow.set(nw);
                if(oh != null && nh != null) oh.set(oh);
            }
        } finally {
            mEdtScaleActivated = false;
        }
    }

}
