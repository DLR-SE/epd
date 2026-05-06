package de.emir.rcp.parts.vesseleditor.view.panels;

import de.emir.model.universal.physics.MultiViewObjectSurfaceInforamtion;
import de.emir.model.universal.physics.ObjectSurfaceInformation;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.spatial.Geometry;
import de.emir.rcp.manager.ModelManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.rcp.model.ModelTransactionStack;
import de.emir.rcp.model.transactions.CompoundTransaction;
import de.emir.rcp.model.transactions.SetValueTransaction;
import de.emir.rcp.parts.vesseleditor.utils.PredefinedGeometryItem;
import de.emir.rcp.parts.vesseleditor.view.parts.AbstractPhysicalObjectPart;
import de.emir.service.geometry.impl.WKTUtil;
import de.emir.tuml.ucore.runtime.logging.ULog;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Map;

/**
 * Creates a panel that lets users choose from a list of predefined geometries and set them as a geometry for their
 * current object surface information. It supports standard top-down surfaces and MultiViewObjectSurfaceInformation
 * (with side, front views).
 */
public class WKTPanel extends JPanel {

    public WKTPanel(AbstractPhysicalObjectPart editor) {
        setLayout(new BorderLayout());
        setBorder(new TitledBorder(new LineBorder(Color.GRAY), "WKT"));

        JComboBox<PredefinedGeometryItem> cbPreDefined = new JComboBox<>();

        Map<String, PredefinedGeometryItem> items = PredefinedGeometryItem.getPredefinedGeometryItems();
        if(!items.isEmpty()){

            for (PredefinedGeometryItem pgi : items.values()) {
                cbPreDefined.addItem(pgi);
            }

            add(cbPreDefined, BorderLayout.NORTH);
            cbPreDefined.setSelectedIndex(0);

            JButton btnApply = new JButton("Apply");
            add(btnApply, BorderLayout.SOUTH);

            btnApply.addActionListener(e -> {
                ModelManager modelManager = PlatformUtil.getModelManager();
                if (modelManager == null){
                    ULog.error("Cannot set WKT geometry, ModelManager not found!");
                    return;
                }

                AbstractModelProvider modelProvider = modelManager.getModelProvider();
                if (modelProvider == null){
                    ULog.error("Cannot set WKT geometry, ModelProvider not found!");
                    return;
                }

                ModelTransactionStack modelTransactionStack = modelProvider.getTransactionStack();
                if (modelTransactionStack == null){
                    ULog.error("Cannot set WKT geometry, TransactionStack not found!");
                    return;
                }

                Object selected = cbPreDefined.getSelectedItem();
                if(selected instanceof PredefinedGeometryItem){
                    PredefinedGeometryItem geometryItem = (PredefinedGeometryItem) selected;

                    ObjectSurfaceInformation osi = editor.getSurfaceInformation();

                    CompoundTransaction compoundTransaction = new CompoundTransaction();

                    WKTUtil wktUtil = new WKTUtil();

                    // read the top-down geometry
                    Geometry topGeometry = wktUtil.loadWKT(geometryItem.wktTop);
                    topGeometry.recursiveSetCRS(editor.getPhysicalObject().getOwnedCoordinateSystem());
                    compoundTransaction.add(new SetValueTransaction(osi, PhysicsPackage.Literals.ObjectSurfaceInformation_geometry, topGeometry));

                    // check if we also need to load front and side geometries
                    if (osi instanceof MultiViewObjectSurfaceInforamtion) {
                        Geometry frontGeometry = wktUtil.loadWKT(geometryItem.wktFront);
                        frontGeometry.recursiveSetCRS(editor.getPhysicalObject().getOwnedCoordinateSystem());
                        compoundTransaction.add(new SetValueTransaction(osi, PhysicsPackage.Literals.MultiViewObjectSurfaceInforamtion_frontGeometry, frontGeometry));

                        Geometry sideGeometry = wktUtil.loadWKT(geometryItem.wktSide);
                        sideGeometry.recursiveSetCRS(editor.getPhysicalObject().getOwnedCoordinateSystem());
                        compoundTransaction.add(new SetValueTransaction(osi, PhysicsPackage.Literals.MultiViewObjectSurfaceInforamtion_sideGeometry, sideGeometry));
                    }

                    // use the transaction stack to add the geometry
                    modelTransactionStack.run(compoundTransaction);
                }
            });
        }
    }
}
