package de.emir.rcp.parts.vesseleditor.view.vessel;

import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.VesselDimensionCharacteristic;
import de.emir.model.domain.maritime.vessel.VesselSafetyCharacteristic;
import de.emir.model.domain.maritime.vessel.WatercraftHull;
import de.emir.model.domain.maritime.vessel.impl.VesselDimensionCharacteristicImpl;
import de.emir.model.domain.maritime.vessel.impl.VesselSafetyCharacteristicImpl;
import de.emir.model.domain.maritime.vessel.impl.WatercraftHullImpl;
import de.emir.model.universal.crs.impl.WGS842DImpl;
import de.emir.model.universal.physics.LocatableObject;
import de.emir.model.universal.physics.ObjectSurfaceInformation;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.impl.LengthImpl;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.parts.VesselEditorBasic;
import de.emir.rcp.parts.vesseleditor.provider.equip.EquipmentProviderExtensionPoint;
import de.emir.rcp.parts.vesseleditor.utils.PaintUtil;
import de.emir.rcp.parts.vesseleditor.utils.View;
import de.emir.rcp.parts.vesseleditor.view.geometry.GeometryPanel;
import de.emir.rcp.parts.vesseleditor.view.helper.DragProperties;
import de.emir.rcp.parts.vesseleditor.view.helper.DragWrapper;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VesselEditorPanel extends GeometryPanel {
    private double EQUIPMENT_SIZE = EDIT_POINT_SIZE * 2;
    private ActionListener equipmentListener;

    private Map<String, Coordinate> featureMap;

    public VesselEditorPanel(Geometry geometry, View view) {
        super(geometry, view);
        featureMap = new HashMap<>();
    }

    @Override
    protected void initListeners() {
        super.initListeners();

        EquipmentHandler dragHandler = new EquipmentHandler();
        addMouseListener(dragHandler);
    }

    /**
     * Draw basic grid
     *
     * @param g1
     */
    @Override
    public void paint(Graphics g1) {
        super.paint(g1);

        EQUIPMENT_SIZE = EDIT_POINT_SIZE * 3;

        Graphics2D graphics2D = (Graphics2D) g1;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawEquipment(graphics2D);

        if (getView() != View.TOP) {
            drawWaterLine(graphics2D);

            drawDraft(graphics2D);

            drawSafetyInformation(graphics2D);
        }
        
        drawPersonalSpace(graphics2D);
    }

    private void drawSafetyInformation(Graphics2D graphics2D) {
        Object obj = PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID);

        if (obj instanceof ObjectSurfaceInformation) {
            ObjectSurfaceInformation objectSurfaceInformation = (ObjectSurfaceInformation) obj;
            UObject uContainer = objectSurfaceInformation.getUContainer();
            if (uContainer instanceof Vessel) {
                Vessel vessel = (Vessel) uContainer;
                VesselSafetyCharacteristic vsc = vessel.getFirstCharacteristic(VesselSafetyCharacteristic.class, true);
                if (vsc == null) {
                    vsc = new VesselSafetyCharacteristicImpl();
                    vessel.getCharacteristics().add(vsc);
                }

                Length ukc = (vsc.getUnderKeelClearance() != null) ? vsc.getUnderKeelClearance() : new LengthImpl();
                if (vsc.getUnderKeelClearance() == null) {
                    vsc.setUnderKeelClearance(ukc);
                }

                double underKeelClearance = ukc.getAs(DistanceUnit.METER);

                Coordinate draftLine = featureMap.get("draftLine");
                Coordinate underKeelLine = featureMap.get("underKeelLine");
                if(underKeelLine == null){
                    underKeelLine = new CoordinateImpl(0, draftLine.getY()+underKeelClearance, 0, new WGS842DImpl());

                    VesselSafetyCharacteristic finalVsc = vsc;
                    Coordinate finalUnderKeelLine = underKeelLine;

                    underKeelLine.registerListener(e -> {
                        finalVsc.setUnderKeelClearance(new LengthImpl(Math.abs(draftLine.getY() - finalUnderKeelLine.getY()), DistanceUnit.METER));
                    });

                    featureMap.put("underKeelLine", underKeelLine);
                }else {
                    underKeelLine.setY(draftLine.getY() - underKeelClearance);
                }

                graphics2D.setColor(Color.RED);
                graphics2D.setStroke(new BasicStroke((float) LINE_WIDTH));

                Line2D underKeelGeom = new Line2D.Double();

                Point2D point2D = coordinateToPoint(underKeelLine);
                underKeelGeom.setLine(new Point2D.Double(0, point2D.getY()), new Point2D.Double(getWidth(), point2D.getY()));

                Rectangle2D rectangle2D = new Rectangle2D.Double();
                rectangle2D.setRect(0, point2D.getY(), getWidth(), LINE_WIDTH * 1.5);
                draggableElements.add(new DragWrapper(rectangle2D, underKeelLine, new DragProperties(false, true, false, null)));

                graphics2D.draw(underKeelGeom);
            }
        }
    }

    private void drawPersonalSpace(Graphics2D graphics2D){
        Object obj = PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID);

        if (obj instanceof ObjectSurfaceInformation) {
            ObjectSurfaceInformation objectSurfaceInformation = (ObjectSurfaceInformation) obj;
            UObject uContainer = objectSurfaceInformation.getUContainer();
            if (uContainer instanceof Vessel) {
                Vessel vessel = (Vessel) uContainer;
                VesselSafetyCharacteristic vsc = vessel.getFirstCharacteristic(VesselSafetyCharacteristic.class, true);
                if (vsc == null) {
                    vsc = new VesselSafetyCharacteristicImpl();
                    vessel.getCharacteristics().add(vsc);
                }

                Length ps = (vsc.getPersonalSpace() != null) ? vsc.getPersonalSpace() : new LengthImpl();
                if (vsc.getPersonalSpace() == null) {
                    vsc.setPersonalSpace(ps);
                }

                double personalSpace = ps.getAs(DistanceUnit.METER);

                if(getGeometry() != null){
                    Envelope env = getGeometry().getEnvelope();
                    if(env != null){
                        List<Coordinate> vertices = env.getVertices();

                        Point2D downLeft = coordinateToPoint(vertices.get(0));
                        Point2D downRight = coordinateToPoint(vertices.get(1));
                        Point2D topLeft = coordinateToPoint(vertices.get(3));

                        Rectangle2D.Double personalSpaceGeom = new Rectangle2D.Double();
                        personalSpaceGeom.setRect(downLeft.getX()-personalSpace, downLeft.getY()-personalSpace,
                                downLeft.distance(downRight)+(personalSpace*2),
                                downLeft.distance(topLeft)+(personalSpace*2));

                        graphics2D.setColor(Color.GREEN.darker().darker());

                        graphics2D.draw(personalSpaceGeom);
                    }
                }
            }
        }

    }

    private void drawDraft(Graphics2D graphics2D) {
        Object obj = PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID);

        if (obj instanceof ObjectSurfaceInformation) {
            ObjectSurfaceInformation objectSurfaceInformation = (ObjectSurfaceInformation) obj;
            UObject uContainer = objectSurfaceInformation.getUContainer();
            if (uContainer instanceof Vessel) {
                Vessel vessel = (Vessel) uContainer;
                VesselDimensionCharacteristic vdc = vessel.getFirstCharacteristic(VesselDimensionCharacteristic.class, true);

                if (vdc == null) {
                    vdc = new VesselDimensionCharacteristicImpl();
                    vessel.getCharacteristics().add(vdc);
                }
                WatercraftHull hull = (vdc.getHull() != null) ? vdc.getHull() : new WatercraftHullImpl();

                if (vdc.getHull() == null) {
                    vdc.setHull(hull);
                }

                final Length draftLength = (hull.getDraft() != null) ? hull.getDraft() : new LengthImpl();
                if (hull.getDraft() == null) {
                    hull.setDraft(draftLength);
                }

                double draft = draftLength.getAs(DistanceUnit.METER);
                Coordinate draftLine = featureMap.get("draftLine");
                Coordinate waterLine = featureMap.get("waterLine");

                if (draftLine == null) {
                    draftLine = new CoordinateImpl(0, waterLine.getZ() + draft, 0, new WGS842DImpl());
                    Coordinate finalDraftLine = draftLine;
                    draftLine.registerListener(e -> {
                        hull.setDraft(new LengthImpl(Math.abs(finalDraftLine.getY() - waterLine.getY()), DistanceUnit.METER));
                    });
                    featureMap.put("draftLine", draftLine);
                } else {
                    draftLine.setY(waterLine.getY() - draft);
                }

                graphics2D.setColor(Color.BLACK);
                graphics2D.setStroke(new BasicStroke((float) LINE_WIDTH));

                Line2D draftLineGeom = new Line2D.Double();

                Point2D point2D = coordinateToPoint(draftLine);
                draftLineGeom.setLine(new Point2D.Double(0, point2D.getY()), new Point2D.Double(getWidth(), point2D.getY()));

                Rectangle2D rectangle2D = new Rectangle2D.Double();
                rectangle2D.setRect(0, point2D.getY(), getWidth(), LINE_WIDTH * 1.5);
                draggableElements.add(new DragWrapper(rectangle2D, draftLine, new DragProperties(false, true, false, null)));

                graphics2D.draw(draftLineGeom);

            }
        }
    }

    private void drawEquipment(Graphics2D graphics2D) {
        ObjectSurfaceInformation objectSurfaceInformation = (ObjectSurfaceInformation) PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID);
        if (objectSurfaceInformation == null) {
            return;
        }

        UObject object = objectSurfaceInformation.getUContainer();
        if (object instanceof PhysicalObject) {
            PhysicalObject target = (PhysicalObject) object;

            EquipmentProviderExtensionPoint providerExtensionPoint = ExtensionPointManager.getExtensionPoint(EquipmentProviderExtensionPoint.class);
            ArrayList<EquipmentProviderExtensionPoint.EquipmentData> datas = providerExtensionPoint.getEquipmentOf(target);
            for (EquipmentProviderExtensionPoint.EquipmentData data : datas) {

                URL url = data.provider.getIconURL();

                BufferedImage image = null;
                try {
                    image = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                image = PaintUtil.rotateImag(image, 180);

                Image scaleImage = null;
                if (image != null) {
                    scaleImage = image.getScaledInstance((int) EQUIPMENT_SIZE, (int) EQUIPMENT_SIZE, Image.SCALE_DEFAULT);
                }

                Point2D point2D = getViewDependantCoordinate(data.equipment.getPose().getCoordinate());

                Rectangle rectangle = new Rectangle();
                rectangle.setSize((int) EQUIPMENT_SIZE, (int) EQUIPMENT_SIZE);
                rectangle.setLocation((int) point2D.getX() - ((int) EQUIPMENT_SIZE / 2), (int) point2D.getY() - ((int) EQUIPMENT_SIZE / 2));

                draggableElements.add(new DragWrapper(rectangle, data.equipment.getPose().getCoordinate(), new DragProperties(true, true, true, data.equipment.getUClassifier())));

                graphics2D.drawImage(scaleImage, (int) point2D.getX() - ((int) EQUIPMENT_SIZE / 2), (int) point2D.getY() - ((int) EQUIPMENT_SIZE / 2), null);

                Object obj = PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_EQUIPMENT_SELECTION_ID);
                if (obj instanceof LocatableObject && (data.equipment == obj)) {
                    graphics2D.draw(rectangle);
                }
            }
        }
    }

    private void drawWaterLine(Graphics2D graphics2D) {
        Coordinate waterLine = featureMap.get("waterLine");
        if (waterLine == null) {
            waterLine = new CoordinateImpl(0, 0, 0, new WGS842DImpl());
            waterLine.registerTreeListener(e -> refresh());
            featureMap.put("waterLine", waterLine);
        }

        graphics2D.setColor(Color.BLUE);
        graphics2D.setStroke(new BasicStroke((float) LINE_WIDTH));

        Line2D waterLineGeom = new Line2D.Double();

        Point2D point2D = coordinateToPoint(waterLine);
        waterLineGeom.setLine(new Point2D.Double(0, point2D.getY()), new Point2D.Double(getWidth(), point2D.getY()));

        Rectangle2D rectangle2D = new Rectangle2D.Double();
        rectangle2D.setRect(0, point2D.getY(), getWidth(), LINE_WIDTH * 1.5);
        draggableElements.add(new DragWrapper(rectangle2D, waterLine, new DragProperties(false, true, false, null)));

        graphics2D.draw(waterLineGeom);
    }

    public void setOnEquipmentClicked(ActionListener actionListener) {
        this.equipmentListener = actionListener;
    }

    protected class EquipmentHandler extends MouseAdapter {

        public EquipmentHandler() {

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (equipmentListener == null) return;

            ObjectSurfaceInformation objectSurfaceInformation = (ObjectSurfaceInformation) PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID);
            if (objectSurfaceInformation == null) {
                return;
            }

            UObject object = objectSurfaceInformation.getUContainer();
            if (object instanceof PhysicalObject) {
                PhysicalObject target = (PhysicalObject) object;

                EquipmentProviderExtensionPoint providerExtensionPoint = ExtensionPointManager.getExtensionPoint(EquipmentProviderExtensionPoint.class);
                ArrayList<EquipmentProviderExtensionPoint.EquipmentData> datas = providerExtensionPoint.getEquipmentOf(target);
                for (EquipmentProviderExtensionPoint.EquipmentData data : datas) {
                    Coordinate coordinate = data.equipment.getPose().getCoordinate();

                    if (markedPoints.contains(coordinate)) {
                        equipmentListener.actionPerformed(new ActionEvent(data.equipment, 0, ""));
                        PlatformUtil.getSelectionManager().setSelection(VesselEditorBasic.CTX_EQUIPMENT_SELECTION_ID, data.equipment);
                    }
                }
            }
        }
    }
}
