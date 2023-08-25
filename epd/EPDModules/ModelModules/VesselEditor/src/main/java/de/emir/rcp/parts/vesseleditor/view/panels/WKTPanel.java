package de.emir.rcp.parts.vesseleditor.view.panels;

import de.emir.model.universal.physics.MultiViewObjectSurfaceInforamtion;
import de.emir.model.universal.physics.ObjectSurfaceInformation;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.spatial.Geometry;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.transactions.CompoundTransaction;
import de.emir.rcp.model.transactions.SetValueTransaction;
import de.emir.rcp.parts.vesseleditor.utils.PredefinedGeometryItem;
import de.emir.rcp.parts.vesseleditor.view.parts.AbstractPhysicalObjectPart;
import de.emir.service.geometry.impl.WKTUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;


public class WKTPanel extends JPanel {

    public WKTPanel(AbstractPhysicalObjectPart editor) {
        setLayout(new BorderLayout());
        setBorder(new TitledBorder(new LineBorder(Color.GRAY), "WKT"));

        JComboBox<PredefinedGeometryItem> cbPreDefined = new JComboBox<>();
        Map<String, PredefinedGeometryItem> items = PredefinedGeometryItem.getPredefinedGeometryItems();
        if(items.size() > 0){
            for (PredefinedGeometryItem pgi : items.values()) {
                cbPreDefined.addItem(pgi);
            }
            add(cbPreDefined, BorderLayout.NORTH);
            cbPreDefined.setSelectedIndex(0);

            JButton btnApply = new JButton("Apply");
            add(btnApply, BorderLayout.SOUTH);

            btnApply.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object selected = cbPreDefined.getSelectedItem();
                    if(selected instanceof PredefinedGeometryItem){
                        PredefinedGeometryItem geometryItem = (PredefinedGeometryItem) selected;

                        ObjectSurfaceInformation osi = editor.getSurfaceInformation();

                        CompoundTransaction compoundTransaction = new CompoundTransaction();

                        WKTUtil wktUtil = new WKTUtil();
                        Geometry topGeometry = wktUtil.loadWKT(geometryItem.wktTop);
                        topGeometry.recursiveSetCRS(editor.getPhysicalObject().getOwnedCoordinateSystem());
                        compoundTransaction.add(new SetValueTransaction(osi, PhysicsPackage.Literals.ObjectSurfaceInformation_geometry, topGeometry));

                        if (osi instanceof MultiViewObjectSurfaceInforamtion) {
                            Geometry frontGeometry = wktUtil.loadWKT(geometryItem.wktFront);
                            frontGeometry.recursiveSetCRS(editor.getPhysicalObject().getOwnedCoordinateSystem());
                            compoundTransaction.add(new SetValueTransaction(osi, PhysicsPackage.Literals.MultiViewObjectSurfaceInforamtion_frontGeometry, frontGeometry));
                            Geometry sideGeometry = wktUtil.loadWKT(geometryItem.wktSide);
                            sideGeometry.recursiveSetCRS(editor.getPhysicalObject().getOwnedCoordinateSystem());
                            compoundTransaction.add(new SetValueTransaction(osi, PhysicsPackage.Literals.MultiViewObjectSurfaceInforamtion_sideGeometry, sideGeometry));
                        }

                        PlatformUtil.getModelManager().getModelProvider().getTransactionStack().run(compoundTransaction);
                    }
                }
            });
        }

    }
}
