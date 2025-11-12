package de.emir.epd.targetview;

import de.emir.epd.mapview.basics.utils.SetLayerDirtyPropertyChangeListener;
import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.mapview.views.map.AbstractMapLayer;
import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;
import de.emir.epd.model.EPDModelUtils;
import de.emir.epd.targetview.ids.TargetBasics;
import de.emir.epd.targetview.lib.TargetShapeRenderer;
import de.emir.model.universal.detection.ITarget;
import de.emir.model.universal.detection.ITrackPoint;
import de.emir.model.universal.detection.ITrackedTarget;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.PhysicalObjectUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.units.*;
import de.emir.model.universal.units.impl.DistanceImpl;
import de.emir.rcp.manager.SelectionManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Map layer for displaying Target or TrackedTarget objects which are inserted into the model. a Target/TrackedTarget
 * generally refers to an object perceived by a tracking or detection system such as RADAR.
 */
public class TargetLayer extends AbstractMapLayer {
    protected PhysicalObject lastFocusedTarget;
    protected PhysicalObject lastSelectedTarget;
    protected Set<ITarget> targets = ConcurrentHashMap.newKeySet();
    protected Set<ITrackedTarget> trackedTargets = ConcurrentHashMap.newKeySet();

    protected BasicStroke cogSogStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1,
            new float[]{5, 3}, 0);
    protected BasicStroke trackStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    protected Color shipColor = new Color(78, 78, 78);
    protected Color trackColor = new Color(26, 26, 26, 96);

    protected IProperty<Integer> propTargetLostTime;
    protected IProperty<Integer> propLookahead;
    protected IProperty<Integer> propTrackTimeout;
    protected IProperty<Boolean> propDisplayTargetLoss;
    protected IProperty<Boolean> propHandleTrackTimeout;
    protected IProperty<Boolean> propUseReceiveTimestamp;
    protected IProperty<Boolean> propShowNames;
    protected IProperty<Boolean> propShowTargets;
    protected IProperty<Boolean> propShowTrackedTargets;
    protected IProperty<Boolean> propLayerFixedUpdate;
    protected IProperty<Integer> propLayerUpdateRate;

    protected String currentDescription = "";
    private int mouseX;
    private int mouseY;
    protected Set<ShapeTarget> targetShapes = ConcurrentHashMap.newKeySet();
    protected RedrawObserver observer = new RedrawObserver();
    protected ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    protected AffineTransform baseTransform;
    protected TargetShapeRenderer iconRenderer = new TargetShapeRenderer();

    public TargetLayer() {
        modelChanged();
        addListeners();
    }

    /**
     * Queries if the visibility of the map layer can be controlled by the user.
     *
     * @return Always true.
     */
    @Override
    public boolean isVisibilityUserControlled() {
        return true;
    }

    /**
     * If the model has been changed, reregister property change listeners on the new model instance.
     */
    @Override
    public void modelChanged() {
        EPDModelUtils.subscribeModelChange("targets", event -> {
            Set<ITarget> oldTargets = new HashSet<>(Arrays.asList((ITarget[]) event.getOldValue()));
            Set<ITarget> newTargets = new HashSet<>(Arrays.asList((ITarget[]) event.getNewValue()));
            if (oldTargets.size() > newTargets.size()) {
                Set<ITarget> removedObjects = difference(oldTargets, newTargets);
                unregisterTreeListeners(removedObjects);
                targets.removeAll(removedObjects);
            } else {
                Set<ITarget> addedObjects = difference(newTargets, oldTargets);
                registerTreeListeners(addedObjects);
                targets.addAll(addedObjects);
            }
            setDirty(true);
        });
        EPDModelUtils.subscribeModelChange("trackedTargets", event -> {
            Set<ITrackedTarget> oldTargets = new HashSet<>(Arrays.asList((ITrackedTarget[]) event.getOldValue()));
            Set<ITrackedTarget> newTargets = new HashSet<>(Arrays.asList((ITrackedTarget[]) event.getNewValue()));
            if (oldTargets.size() > newTargets.size()) {
                Set<ITrackedTarget> removedObjects = difference(oldTargets, newTargets);
                unregisterTreeListeners(removedObjects);
                trackedTargets.removeAll(removedObjects);
            } else {
                Set<ITrackedTarget> addedObjects = difference(newTargets, oldTargets);
                registerTreeListeners(addedObjects);
                trackedTargets.addAll(addedObjects);
            }
            setDirty(true);
        });
    }

    /**
     * Calculates the difference between two sets based on a bigger and a smaller set. This operation equals the relative complement of the set theory.
     *
     * @param biggerSet  Set to compare.
     * @param smallerSet Set to compare.
     * @param <T>        Type of physical object.
     * @return Objects which are included in the bigger set but are not included in the smaller set.
     */
    private <T extends PhysicalObject> Set<T> difference(Set<T> biggerSet, Set<T> smallerSet) {
        biggerSet.removeAll(smallerSet);
        return biggerSet;
    }

    /**
     * Registers a tree listener to all targets of a set of targets.
     *
     * @param targets Targets to register tree listeners for.
     * @param <T>     Type of physical object.
     */
    private <T extends PhysicalObject> void registerTreeListeners(Set<T> targets) {
        for (PhysicalObject target : targets) {
            target.registerTreeListener(observer);
        }
    }

    /**
     * Deregisters a tree listener from all targets of a set of targets.
     *
     * @param targets Targets to unregister tree listener from.
     * @param <T>     Type of physical object.
     */
    private <T extends PhysicalObject> void unregisterTreeListeners(Set<T> targets) {
        for (PhysicalObject target : targets) {
            target.removeTreeListener(observer);
        }
    }

    /**
     * Configures the listeners of the layer.
     */
    private void addListeners() {
        SelectionManager sm = PlatformUtil.getSelectionManager();
        // Subscribe to target selection. If the selected target is TrackedTarget or Target, this layer will draw the selection box.
        sm.subscribe(MVBasic.MAP_SELECTION_CTX, oo -> {
            PhysicalObject oldTarget = lastSelectedTarget;
            if (oo.isPresent()) {
                if (oo.get() instanceof ITrackedTarget) {
                    lastSelectedTarget = (ITrackedTarget) oo.get();
                } else if (oo.get() instanceof ITarget) {
                    lastSelectedTarget = (ITarget) oo.get();
                } else {
                    lastSelectedTarget = null;
                }
            } else {
                lastSelectedTarget = null;
            }
            if (oldTarget != lastSelectedTarget) {
                setDirty(true);
            }
        });
        // Subscribe to target focus. If the selected target is TrackedTarget or Target, this layer will focus the target.
        sm.subscribe(MVBasic.MAP_FOCUS_CTX, oo -> {
            PhysicalObject oldTarget = lastFocusedTarget;
            if (oo.isPresent()) {
                if (oo.get() instanceof ITrackedTarget) {
                    lastFocusedTarget = (ITrackedTarget) oo.get();
                } else if (oo.get() instanceof ITarget) {
                    lastFocusedTarget = (ITarget) oo.get();
                } else {
                    lastFocusedTarget = null;
                }
            } else {
                lastFocusedTarget = null;
            }
            if (oldTarget != lastFocusedTarget) {
                setDirty(true);
            }
        });
        // Configure property listeners for storing settings.
        PropertyContext ctx = PropertyStore.getContext(TargetBasics.TARGET_VIEWER_PROP_CONTEXT);
        propTargetLostTime = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_TARGET_LOSTTIME, 30);
        propLookahead = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_LOOKAHEAD, 6);
        propTrackTimeout = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_TRACK_TIMEOUT, 10);
        propDisplayTargetLoss = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_DISPLAY_TARGET_LOSS, false);
        propHandleTrackTimeout = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_HANDLE_TRACK_TIMEOUT, false);
        propUseReceiveTimestamp = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_USE_RECEIVE_TIMESTAMP, true);
        propShowNames = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_SHOW_NAMES, true);
        propShowTargets = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_SHOW_TARGETS, true);
        propShowTrackedTargets = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_SHOW_TRACKED_TARGETS, true);
        propLayerFixedUpdate = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_LAYER_FIXED_UPDATE, true);
        propLayerUpdateRate = ctx.getProperty(TargetBasics.TARGET_VIEWER_PROP_LAYER_UPDATE_RATE, 10);

        propTargetLostTime.addPropertyChangeListener(new SetLayerDirtyPropertyChangeListener(this));
        propLookahead.addPropertyChangeListener(new SetLayerDirtyPropertyChangeListener(this));
        propTrackTimeout.addPropertyChangeListener(new SetLayerDirtyPropertyChangeListener(this));
        propDisplayTargetLoss.addPropertyChangeListener(new SetLayerDirtyPropertyChangeListener(this));
        propHandleTrackTimeout.addPropertyChangeListener(new SetLayerDirtyPropertyChangeListener(this));
        propUseReceiveTimestamp.addPropertyChangeListener(new SetLayerDirtyPropertyChangeListener(this));
        ;
        propShowNames.addPropertyChangeListener(new SetLayerDirtyPropertyChangeListener(this));
        ;
        propShowTargets.addPropertyChangeListener(new SetLayerDirtyPropertyChangeListener(this));
        ;
        propShowTrackedTargets.addPropertyChangeListener(new SetLayerDirtyPropertyChangeListener(this));
        ;
        propLayerFixedUpdate.addPropertyChangeListener(new SetLayerDirtyPropertyChangeListener(this));

        // Updater which updates the layer at a fixed rate if no other changes in the model were made.
        scheduler.scheduleAtFixedRate(() -> {
            if (propLayerFixedUpdate.getValue()) {
                setDirty(true);
            }
        }, 0, propLayerUpdateRate.getValue(), java.util.concurrent.TimeUnit.SECONDS);
    }

    /**
     * Paints the layer.
     *
     * @param g Graphics buffer to draw symbols to.
     * @param c Current draw context on the map.
     */
    @Override
    public void paint(BufferingGraphics2D g, IDrawContext c) {
        baseTransform = g.getTransform();
        targetShapes.clear();
        // Draw each Target if show targets is configured in the layers.
        if (targets != null && propShowTargets.getValue()) {
            for (ITarget target : targets) {
                if (inViewport(c, target.getPose())) {
                    paintTargetSymbol(g, c, target);
                    if (lastSelectedTarget == target) {
                        paintSelectionBox(g, c, target);
                    }
                }
            }
        }
        // Draw each TrackedTarget if show tracked targets is configured in the layers.
        if (trackedTargets != null && propShowTrackedTargets.getValue()) {
            for (ITrackedTarget target : trackedTargets) {
                boolean lostTarget = getLostStatus(target);
                if (inViewport(c, target.getPose())) {
                    paintTrackedTargetSymbol(lostTarget, g, c, target);
                    paintVector(lostTarget, g, c, target);
                }
                paintTrackPoints(g, c, target);
                if (lastSelectedTarget == target) {
                    paintSelectionBox(g, c, target);
                }
            }
        }
        // If a description is present, draw the popup box to display target information next to the cursor.
        if (!currentDescription.isEmpty()) {
            c.drawInfobox(g, currentDescription, mouseX + 12, mouseY + 50, SwingConstants.CENTER, SwingConstants.CENTER);
        }
    }

    /**
     * Draw the speed and COG vector of a target.
     *
     * @param lostTarget True if the target is lost. This either means that the last sent timestamp is too old or the last update is too old to be considered current.
     * @param graphics2D Graphics buffer to use for drawing.
     * @param context    Draw context to use.
     * @param target     Target to retrieve vector from.
     */
    private void paintVector(boolean lostTarget, BufferingGraphics2D graphics2D, IDrawContext context, PhysicalObject target) {
        if (context.getZoom() < 6) {
            double cogRad = 0;
            double lat = target.getPose().getCoordinate().getLatitude();
            double lon = target.getPose().getCoordinate().getLongitude();
            Point2D px = context.convert(lon, lat);
            Angle course = PhysicalObjectUtils.getCOG(target);
            Speed speed = PhysicalObjectUtils.getSOG(target);
            graphics2D.translate(px.getX(), px.getY());
            // draw lookahead
            if (course != null && speed != null) {
                float cog = (float) course.getAs(AngleUnit.DEGREE);
                float sog = (float) speed.getAs(SpeedUnit.KNOTS);
                // Only draw SOG/COG vector if COG is available (Not 360 according to NMEA0183)
                if (cog != 360) {
                    if (sog < 0.5) {
                        graphics2D.setColor(Color.GRAY);
                    }
                    cogRad = Math.toRadians(cog);
                    graphics2D.rotate(cogRad);
                    if (!lostTarget && context.getZoom() < 8 && Float.compare(sog, 102.3f) != 0) {
                        int vec = propLookahead.getValue();
                        double[] nleft = CRSUtils.getTarget(
                                new double[]{lat, lon},
                                new DistanceImpl(sog, DistanceUnit.NAUTICAL_MILES)
                                        .getAs(DistanceUnit.METER) / 60,
                                cogRad,
                                CRSUtils.WGS84_2D);
                        GeoPosition dest = new GeoPosition(nleft);
                        Point2D lp = context.convert(dest);
                        double x = px.getX() - lp.getX();
                        double y = px.getY() - lp.getY();
                        double distance = Math.sqrt(Math.pow((x), 2) + Math.pow((y), 2));
                        if ((int) distance > 0) {
                            int spdv = (int) distance;
                            int veclen = (spdv * vec) - 12;
                            graphics2D.setStroke(cogSogStroke);
                            graphics2D.drawLine(0, -12, 0, -12 - veclen);
                            graphics2D.drawLine(0, -12 - veclen, 4, -8 - veclen);
                            graphics2D.drawLine(0, -12 - veclen, -4, -8 - veclen);
                            for (int i = 1; i < vec; i++) {
                                int pos = (int) (veclen - (spdv * i));
                                graphics2D.drawLine(-2, -12 - pos, 2, -12 - pos);
                            }
                        }
                    }
                    graphics2D.rotate(-cogRad);
                }
            }
            resetTransform(graphics2D);
        }
    }

    /**
     * Retrieves the track of a target and displays all previous track points on the map.
     *
     * @param graphics2D Graphics buffer to draw past route to.
     * @param context    Context to use.
     * @param target     Target to retrieve track from.
     */
    private void paintTrackPoints(BufferingGraphics2D graphics2D, IDrawContext context, ITrackedTarget target) {
        if (context.getZoom() < 6) {
            if (target.getTrack() != null && !target.getTrack().getTrackPoints().isEmpty()) {
                List<ITrackPoint> copy = new ArrayList<>(target.getTrack().getTrackPoints());
                ITrackPoint prevTrackpoint = null;
                for (ITrackPoint trackPoint : copy) {
                    if (propHandleTrackTimeout.getValue()) {
                        if (propUseReceiveTimestamp.getValue()) {
                            // Timeouts for internal timestamps inside the EPD.
                            if (trackPoint.hasProperty("lastReceiveTimestamp") &&
                                    System.currentTimeMillis() - (long) trackPoint.getProperty("lastReceiveTimestamp").getValue() >=
                                            (long) (propTargetLostTime.getValue() * 1000)) {
                                continue;
                            }
                            if (!trackPoint.hasProperty("lastReceiveTimestamp")) {
                                continue;
                            }
                        } else {
                            // Timeouts for supplied timestamps of the target messages.
                            long tpTime;
                            long diff;
                            if (trackPoint.getTimestamp() != null) {
                                tpTime = (long) trackPoint.getTimestamp().getAs(TimeUnit.MILLISECOND);
                                diff = System.currentTimeMillis() - tpTime;
                                // check if we want to draw this point based on track timeout property
                                if (propHandleTrackTimeout.getValue() && (diff > propTrackTimeout.getValue() * 60 * 1000)) {
                                    continue;
                                }
                            } else if (propHandleTrackTimeout.getValue()) {
                                continue;
                            }
                        }
                    }
                    if (inViewport(context, trackPoint.getPose())) {
                        graphics2D.setColor(trackColor);
                        graphics2D.setStroke(trackStroke);
                        Point2D point = context.convert(
                                trackPoint.getPose().getCoordinate());
                        if (prevTrackpoint != null) {
                            Point2D prevPoint = context.convert(
                                    prevTrackpoint.getPose().getCoordinate());
                            graphics2D.drawLine((int) prevPoint.getX(), (int) prevPoint.getY(), (int) point.getX(), (int) point.getY());
                        }
                        graphics2D.translate(point.getX(), point.getY());
                        graphics2D.fill(iconRenderer.getTrackMarker());
                        resetTransform(graphics2D);
                    }
                    prevTrackpoint = trackPoint;
                }
            }
        }
    }

    /**
     * Paints a TrackedTarget symbol on the map. This represents the Target symbol of SN.1/Circ.243.
     *
     * @param lostTarget True if the target is lost. This either means that the last sent timestamp is too old or the last update is too old to be considered current.
     * @param graphics2D Graphics to draw target symbol to.
     * @param context    Draw context to use.
     * @param target     Target to retrieve data from.
     */
    private void paintTrackedTargetSymbol(boolean lostTarget, BufferingGraphics2D graphics2D, IDrawContext context, ITrackedTarget target) {
        double lat = target.getPose().getCoordinate().getLatitude();
        double lon = target.getPose().getCoordinate().getLongitude();
        Point2D point2D = context.convert(lon, lat);
        graphics2D.translate(point2D.getX(), point2D.getY());
        graphics2D.setColor(shipColor);
        graphics2D.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
        if (context.getZoom() < 6) {
            if (target.getName() != null && propShowNames.getValue()) {
                graphics2D.drawString(target.getNameAsString(), 10, 10);
            }
            if (target.hasProperty("status")) {
                IProperty<?> status = target.getProperty("status");
                if (status.getValue().equals("LOST")) {
                    graphics2D.draw(iconRenderer.getTargetSymbol());
                    graphics2D.draw(iconRenderer.getLostTargetSymbol());
                    targetShapes.add(new ShapeTarget(graphics2D.getTransform().createTransformedShape(iconRenderer.getTargetSymbol()), target));
                } else if (status.getValue().equals("QUERY")) {
                    graphics2D.draw(iconRenderer.getAcquisitionStateSymbol());
                    targetShapes.add(new ShapeTarget(graphics2D.getTransform().createTransformedShape(iconRenderer.getAcquisitionStateSymbol()), target));
                } else {
                    graphics2D.draw(iconRenderer.getTargetSymbol());
                    targetShapes.add(new ShapeTarget(graphics2D.getTransform().createTransformedShape(iconRenderer.getTargetSymbol()), target));
                    if (lostTarget) {
                        graphics2D.draw(iconRenderer.getLostTargetSymbol());
                    }
                }
            } else {
                graphics2D.draw(iconRenderer.getTargetSymbol());
                targetShapes.add(new ShapeTarget(graphics2D.getTransform().createTransformedShape(iconRenderer.getTargetSymbol()), target));
                if (lostTarget) {
                    graphics2D.draw(iconRenderer.getLostTargetSymbol());
                }
            }
        } else {
            graphics2D.draw(iconRenderer.getMinimizedTargetSymbol());
        }
        resetTransform(graphics2D);
    }

    /**
     * Paints a Target symbol on the map. This represents the plot point symbol of SN.1/Circ.243.
     *
     * @param graphics2D Graphics to draw target symbol to.
     * @param context    Draw context to use.
     * @param target     Target to retrieve data from.
     */
    private void paintTargetSymbol(BufferingGraphics2D graphics2D, IDrawContext context, ITarget target) {
        double lat = target.getPose().getCoordinate().getLatitude();
        double lon = target.getPose().getCoordinate().getLongitude();
        Point2D point2D = context.convert(lon, lat);
        graphics2D.translate(point2D.getX(), point2D.getY());
        graphics2D.setColor(shipColor);
        graphics2D.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
        if (context.getZoom() < 6) {
            graphics2D.draw(iconRenderer.getPlottedTargetSymbol());
            // Mode = Fix, EP, DR.
            if (target.hasProperty("mode")) {
                graphics2D.drawString(target.getPropertyValueAsString("mode"), -30, 15);
            } else {
                graphics2D.drawString("DR", -30, 15);
            }
            if (target.getName() != null && propShowNames.getValue()) {
                graphics2D.drawString(target.getNameAsString(), 20, -10);
            }
            if (target.getTimestamp() != null) {
                String time = LocalTime.ofInstant(target.getTimestamp().getDateTime(), ZoneOffset.UTC)
                        .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                graphics2D.drawString(time, -30, -10);
            }
            // Source of the target. GNSS, L (Loran), R (Radar), V (Visual Bearing), VR (Visual bearing and radar range).
            if (target.hasProperty("source")) {
                graphics2D.drawString(target.getPropertyValueAsString("source"), 20, 15);
            }
            targetShapes.add(new ShapeTarget(graphics2D.getTransform().createTransformedShape(iconRenderer.getPlottedTargetSymbol()), target));
        } else {
            graphics2D.draw(iconRenderer.getMinimizedTargetSymbol());
            targetShapes.add(new ShapeTarget(graphics2D.getTransform().createTransformedShape(iconRenderer.getMinimizedTargetSymbol()), target));
        }

        resetTransform(graphics2D);
    }

    /**
     * Paints a SN.1/Circ.243 compliant selection box if the target is selected
     *
     * @param graphics2D Graphics to draw target symbol to.
     * @param context    Draw context to use.
     * @param target     Target to retrieve data from.
     */
    private void paintSelectionBox(BufferingGraphics2D graphics2D, IDrawContext context, PhysicalObject target) {
        double lat = target.getPose().getCoordinate().getLatitude();
        double lon = target.getPose().getCoordinate().getLongitude();
        Point2D point2D = context.convert(lon, lat);
        graphics2D.translate(point2D.getX(), point2D.getY());
        graphics2D.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
        Color prevColor = graphics2D.getColor();
        graphics2D.setColor(Color.BLACK);
        graphics2D.draw(iconRenderer.getSelectedTargetSymbol());
        graphics2D.setColor(prevColor);
        resetTransform(graphics2D);
    }

    /**
     * Creates a description which can be displayed as a popup next to the cursor containing all relevant target data.
     *
     * @param target Target to retrieve description from.
     * @return Description of the target.
     */
    private String createDescription(PhysicalObject target) {
        StringBuilder descriptionBuilder = new StringBuilder();
        if (target instanceof ITrackedTarget it) {
            descriptionBuilder.append("Target ID: ").append(it.getId()).append("\n");
            if (it.getTrack() != null && it.getTrack().getLastUpdate() != null) {
                descriptionBuilder.append("Last Updated: ").append(it.getTrack().getLastUpdate().getDateTime().toString()).append("\n");
            }
        }
        if (target instanceof ITarget it) {
            descriptionBuilder.append("Target ID: ").append(it.getId()).append("\n");
            descriptionBuilder.append("Target Timestamp: ").append(it.getTimestamp().toString()).append("\n");
            if (it.getTimestamp() != null) {
                descriptionBuilder.append("Timestamp: ").append(it.getTimestamp().getDateTime().toString()).append("\n");
            }
        }
        extractPhysicalObjectInformation(descriptionBuilder, target);
        return descriptionBuilder.toString();
    }

    /**
     * Extracts physical object attributes from a PhysicalObject and appends them to a StringBuilder.
     *
     * @param descriptionBuilder Builder to append physical objects attributes to.
     * @param target             PhysicalObject to extract information from.
     */
    private void extractPhysicalObjectInformation(StringBuilder descriptionBuilder, PhysicalObject target) {
        descriptionBuilder.append("Target Name: ").append(target.getNameAsString()).append("\n");
        if (target.getPose() != null && target.getPose().getCoordinate() != null) {
            descriptionBuilder.append("Position: ").append(target.getPose().getCoordinate().getLatitude()).append(", ").append(target.getPose().getCoordinate().getLongitude()).append("\n");
        }
        Angle cog = PhysicalObjectUtils.getCOG(target);
        if (cog != null) {
            descriptionBuilder.append("Course: ").append(cog.getValue()).append(" ").append(cog.getUnit().name()).append("\n");
        }
        Collection<IProperty> copy = target.getAllProperties();
        if (copy != null) {
            for (IProperty property : copy) {
                descriptionBuilder.append(property.getName()).append(": ").append(property.getValue().toString()).append("\n");
            }
        }
    }

    /**
     * Selects the target object if a mouse click is executed on top of a target symbol.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        for (ShapeTarget shapeTarget : targetShapes) {
            if (shapeTarget.shape.intersects(e.getX(), e.getY(), 3, 3)) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (shapeTarget.target != lastSelectedTarget) {
                        PlatformUtil.getSelectionManager().setSelection(MVBasic.MAP_SELECTION_CTX, shapeTarget.target);
                        e.consume();
                        return;
                    }
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    PlatformUtil.getSelectionManager().setSelection(MVBasic.MAP_SELECTION_CTX, null);
                    e.consume();
                    return;
                }
            }
        }
        if (lastSelectedTarget instanceof ITarget || lastSelectedTarget instanceof ITrackedTarget) {
            PlatformUtil.getSelectionManager().setSelection(MVBasic.MAP_SELECTION_CTX, null);
        }
    }

    /**
     * Focuses a target object if a mouse is hovered on top of a target symbol.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        Point currentMousePosition = e.getPoint();
        PhysicalObject matchingTarget = intersectsTargets(currentMousePosition);
        if (matchingTarget != null) {
            currentDescription = createDescription(matchingTarget);
            mouseX = e.getX();
            mouseY = e.getY();
            setDirty(true);
        } else {
            currentDescription = "";
            mouseX = 0;
            mouseY = 0;
            setDirty(true);
        }
        for (ShapeTarget shapeTarget : targetShapes) {
            if (shapeTarget.shape.intersects(e.getX(), e.getY(), 3, 3)) {
                if (shapeTarget.target == lastFocusedTarget) {
                    return;
                }
                PlatformUtil.getSelectionManager().setSelection(MVBasic.MAP_FOCUS_CTX, shapeTarget.target);
                if (shapeTarget.target != null) {
                    cursorAdapter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
                e.consume();
                return;
            }
        }
        if (lastFocusedTarget == null || lastFocusedTarget instanceof ITarget || lastFocusedTarget instanceof ITrackedTarget) {
            PlatformUtil.getSelectionManager().setSelection(MVBasic.MAP_FOCUS_CTX, null);
        }
    }

    /**
     * Gets the physical object of the target layer which intersects with a given point.
     *
     * @param point Point on the display for which to get targets for. This is used for determining if the mouse is currently hovering over a target symbol.
     * @return PhysicalObject which resides at the mouse cursor location or else null.
     */
    private PhysicalObject intersectsTargets(Point point) {
        for (ShapeTarget shapeTarget : targetShapes) {
            if (shapeTarget.shape.intersects(new Rectangle((int) (point.getX() - 5), (int) (point.getY() - 5), 10, 10))) {
                return shapeTarget.target;
            }
        }
        return null;
    }

    /**
     * Resets the graphics transform of the buffer to the default.
     *
     * @param graphics2D Graphics buffer to reset transform for.
     */
    private void resetTransform(BufferingGraphics2D graphics2D) {
        if (baseTransform != null) {
            graphics2D.setTransform(baseTransform);
        }
    }

    /**
     * Checks if any target is currently lost.
     *
     * @return True if any target is lost.
     */
    private boolean targetsLost() {
        for (ITrackedTarget target : trackedTargets) {
            boolean lostStatus = getLostStatus(target);
            if (lostStatus) {
                return true;
            }
        }
        for (ITarget target : targets) {
            boolean lostStatus = getLostStatus(target);
            if (lostStatus) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks the target loss status for a PhysicalObject.
     *
     * @param target Target to check loss status for.
     * @return True if lost, else false.
     */
    private boolean getLostStatus(PhysicalObject target) {
        if (propDisplayTargetLoss.getValue()) {
            if (propUseReceiveTimestamp.getValue()) {
                if (target instanceof ITrackedTarget it) {
                    if (it.hasProperty("lastReceiveTimestamp")) {
                        return System.currentTimeMillis() - (long) it.getProperty("lastReceiveTimestamp").getValue() >= (long) (propTargetLostTime.getValue() * 1000);
                    }
                }
                if (target instanceof ITarget it) {
                    if (it.getTimestamp() == null) {
                        return false;
                    }
                    return System.currentTimeMillis() - it.getTimestamp().getAs(TimeUnit.MILLISECOND) >= (long) (propTargetLostTime.getValue() * 1000);
                }
            } else {
                if (target instanceof ITrackedTarget it) {
                    if (it.getTrack().getLastUpdate() == null) {
                        return false;
                    }
                    return System.currentTimeMillis() - it.getTrack().getLastUpdate().getAs(TimeUnit.MILLISECOND) >= (long) (propTargetLostTime.getValue() * 1000);
                }
                if (target instanceof ITarget it) {
                    if (it.getTimestamp() == null) {
                        return false;
                    }
                    return System.currentTimeMillis() - it.getTimestamp().getAs(TimeUnit.MILLISECOND) >= (long) (propTargetLostTime.getValue() * 1000);
                }
            }

        }
        return false;
    }

    /**
     * Checks if a target is inside the viewport bounds.
     *
     * @param context Draw context to use.
     * @param pose    Pose of a target to check.
     * @return True if inside the viewport, else false.
     */
    private boolean inViewport(IDrawContext context, Pose pose) {
        if (pose != null && pose.getCoordinate() != null) {
            return inViewport(context, pose.getCoordinate());
        }
        return false;
    }

    /**
     * Checks if a target is inside the viewport bounds.
     *
     * @param context    Draw context to use.
     * @param coordinate Coordinate of a target to check.
     * @return True if inside the viewport, else false.
     */
    private boolean inViewport(IDrawContext context, Coordinate coordinate) {
        Point2D point = context.convert(coordinate);
        return inViewport(context, point);
    }

    /**
     * Checks if a target is inside the viewport bounds.
     *
     * @param context Draw context to use.
     * @param point2D Point of a target to check.
     * @return True if inside the viewport, else false.
     */
    private boolean inViewport(IDrawContext context, Point2D point2D) {
        Rectangle viewPort = context.getBounds();
        return viewPort.contains(point2D);
    }

    /**
     * Utility class for storing outline shapes of a target to a target object. This is used for identifying if
     * the current cursor position resides on top of a target symbol.
     */
    public static class ShapeTarget {
        public Shape shape;
        public PhysicalObject target;

        /**
         * Creates a new ShapeTarget-
         *
         * @param shape  Bounding box of the target.
         * @param target Target object.
         */
        public ShapeTarget(Shape shape, PhysicalObject target) {
            this.shape = shape;
            this.target = target;
        }
    }

    /**
     * Tree listener which fires a dirty event (update) on the map layer if a UObject variable is changed.
     */
    protected class RedrawObserver implements ITreeValueChangeListener {

        /**
         * Sets the layer dirty if a value was changed and forces redraw.
         *
         * @param notification Notification to process.
         */
        @Override
        public void onValueChange(Notification<Object> notification) {
            setDirty(true);
        }
    }


}
