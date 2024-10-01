package de.emir.epd.nmeasensor;

import de.emir.epd.alert.manager.AlertManager;
import de.emir.epd.alert.manager.AlertState;
import de.emir.epd.model.EPDModel;
import de.emir.epd.model.EPDModelUtils;
import de.emir.epd.model.ais.AisTarget;
import de.emir.epd.nmeasensor.ids.NMEAFieldIds;
import de.emir.epd.nmeasensor.ids.NMEASensorIds;
import de.emir.epd.nmeasensor.ids.OwnshipIds;
import de.emir.epd.nmeasensor.verify.VesselVerifier;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.VesselDimensionCharacteristic;
import de.emir.model.domain.maritime.vessel.WatercraftHull;
import de.emir.model.domain.maritime.vessel.impl.VesselDimensionCharacteristicImpl;
import de.emir.model.domain.maritime.vessel.impl.WatercraftHullImpl;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.physics.Environment;
import de.emir.model.universal.physics.ObjectSurfaceInformation;
import de.emir.model.universal.physics.PhysicalObjectUtils;
import de.emir.model.universal.physics.impl.ObjectSurfaceInformationImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.CoordinateSequenceImpl;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.spatial.sf.LinearRing;
import de.emir.model.universal.spatial.sf.Polygon;
import de.emir.model.universal.spatial.sf.impl.LinearRingImpl;
import de.emir.model.universal.spatial.sf.impl.PolygonImpl;
import de.emir.model.universal.units.*;
import de.emir.model.universal.units.impl.*;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.service.codecs.nmea0183.encoding.sentence.Sentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.encryption.AISPayloadEncryptionUtil;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.DecodedAISPayload;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.*;
//import de.emir.service.geometry.impl.GeometryTransform;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;

import org.slf4j.Logger;

import java.awt.TrayIcon.MessageType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Component which maps incoming NMEA and AIS messages to vessels of the
 * EPDModel.
 * 
 * @apiNote It is possible to set filters which prevent certain messages from
 *          being mapped to vessels.
 *          These filters currently are the ownship sources set by the ownship
 *          plugin.
 * @implNote When ownshipsource AISTARGET is used, only DecodedAISPayload
 *           messages are mapped to vessels. If ownshipsource INTERNAL is used,
 *           the dynamic values of AIS messages (heading, lat, lon etc.)
 *           matching the ownship MMSI are not being mapped. Only NMEA messages
 *           are used for supplying dynamic values to the ownship vessel object.
 */
public class NMEAVesselUpdater {
    private static final Logger LOG = ULog.getLogger(NMEAVesselUpdater.class);

    private enum OwnshipSource {
        NO_PROCESSING, AISTARGET, INTERNAL
    }

    private static Long lastNMEAUpdate = null;
    private static Long lastAISUpdate = null;

    private static Environment aisTargets;
    private static Vessel ownship;
    private static OwnshipSource ownshipFilter = OwnshipSource.NO_PROCESSING;

    /**
     * Initiates the VesselUpdater.
     */
    public static void init() {
        Object o = PlatformUtil.getModelManager().getModelProvider().getModel();
        if (!(o instanceof EPDModel))
            return;
        EPDModel model = (EPDModel) o;
        aisTargets = model.getAisTargetSet();

        PropertyContext ownshipContext = PropertyStore.getContext(OwnshipIds.OWNSHIP_VIEWER_PROP_CONTEXT);
        IProperty<String> ownshipSourceProp = ownshipContext.getProperty(OwnshipIds.OWNSHIP_VIEWER_PROP_OWNSHIP_SOURCE,
                OwnshipSource.AISTARGET.name());

        ownship = EPDModelUtils.retrieveOwnship();

        // Subcribes to changes of the ownship.
        EPDModelUtils.subscribeModelChange("ownship", evt -> {
            if (evt.getNewValue() instanceof Vessel) {
                ownship = (Vessel) evt.getNewValue();
            }
        });

        // Sets the ownship source prop filter if set.
        if (ownshipSourceProp.getValue() != null) {
            ownshipFilter = OwnshipSource.valueOf((String) ownshipContext
                    .getProperty(OwnshipIds.OWNSHIP_VIEWER_PROP_OWNSHIP_SOURCE, OwnshipSource.AISTARGET.name())
                    .getValue());
        }

        // Subscribes to changes of the ownship source filter.
        ownshipSourceProp.addPropertyChangeListener(evt -> {
            if (evt.getNewValue() != null) {
                ownshipFilter = OwnshipSource.valueOf((String) evt.getNewValue());
            }
        });

        // Starts treads which control the status indicator of Ownship and AIS on the
        // UI.
        initAlertThreads();

        NMEAOutput.subscribeSentences(entry -> {
            Sentence sentence = entry.getValue();
            if (sentence instanceof DecodedAISPayload) {
                lastAISUpdate = System.currentTimeMillis();
                DecodedAISPayload payload = (DecodedAISPayload) sentence;
                if (payload.getSourceMmsi() != null) {
                    try {
                        // If ownship source was set to internal, get the ownship and check if the AIS
                        // message MMSI matches the ownship. IF it does, filter
                        // out all dynamic AIS messages to prevent mixing of ownship NMEA sentences and
                        // ownship AIS messages ownship coordinates.
                        if (ownshipFilter == OwnshipSource.INTERNAL) {
                            if (ownship != null) {
                                if (Long.compare(payload.getSourceMmsi().getMMSI(), ownship.getMmsi()) == 0) {
                                    // Do not process dynamic AIS attributes if ownship source is internal and the
                                    // ais message MMSI matches the ownship.
                                    Map<String, Object> properties = payload.toMap();
                                    properties.remove(NMEAFieldIds.NMEA_RATE_OF_TURN);
                                    properties.remove(NMEAFieldIds.NMEA_TRUE_HEADING);
                                    properties.remove(NMEAFieldIds.NMEA_HEADING);
                                    properties.remove(NMEAFieldIds.NMEA_COURSE_OVER_GROUND);
                                    properties.remove(NMEAFieldIds.NMEA_SPEED_OVER_GROUND);
                                    properties.remove(NMEAFieldIds.NMEA_LATITUDE);
                                    properties.remove(NMEAFieldIds.NMEA_LONGITUDE);

                                    AisTarget.updateVessel(mapToVessel(properties, ownship));
                                    return;
                                }
                            } else {
                                // In case no ownship mmsi is found something went wrong. Try processing as
                                // normal ais messages.
                                ULog.error(
                                        "Could not filter for ownship ais messages because there is no ownship MMSI. Defaulting to not processing ownship source.");
                            }
                        }

                        // Default procedure when ownship source is set to AIS, none or there was
                        // nothing to filter.
                        Vessel target = EPDModelUtils.retrieveById(aisTargets,
                                Long.toString(payload.getSourceMmsi().getMMSI()));

                        if (ownshipFilter == OwnshipSource.AISTARGET && target.getAllias().contains("Ownship")) {
                            // If the ownship source is set to AIS and the retrieved target is marked as
                            // ownship, update the alertstate for the ownship.
                            lastNMEAUpdate = System.currentTimeMillis();
                        }

                        AisTarget.updateVessel(mapToVessel(payload.toMap(), target));
                    } catch (Exception e) {
                        // FIXME: This causes ConcurrentModificationException
                        e.printStackTrace();
                    }

                }
            } else {
                if (ownshipFilter == OwnshipSource.NO_PROCESSING || ownshipFilter == OwnshipSource.INTERNAL) {
                    // Only process NMEA messages if no source filter is active or the source filter
                    // is set to internal.
                    lastNMEAUpdate = System.currentTimeMillis();
                    Vessel ownship = EPDModelUtils.retrieveOwnship();
                    if (ownship != null) {
                        try {
                            mapToVessel(sentence.toMap(), ownship);
                            AisTarget.updateVessel(ownship);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    /**
     * Maps NMEA sentences to vessels.
     * 
     * @param sentence Sentence to map to a vessel.
     * @param vessel   Vessel to which to map the sentence.
     * @return Vessel with new values mapped to it.
     */
    public static Vessel mapToVessel(Sentence sentence, Vessel vessel) {
        if (sentence == null || vessel == null) {
            return null;
        }
        return mapToVessel(sentence.toMap(), vessel);
    }

    /**
     * Removes all null values of a map.
     * 
     * @param map Map to remove nulls from.
     * @return Map with removed nulls.
     */
    private static Map<String, Object> removeAllNulls(Map<String, Object> map) {
        Map<String, Object> newMap = new HashMap<>();
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value != null) {
                newMap.put(key, value);
            }
        }
        return newMap;
    }

    /**
     * Puts all relevant AIS values into the given targets vessel. This is static to
     * ensure the updateTarget method is used.
     * <p>
     * Ignores values if they are already set, ensures that
     * {@link de.emir.tuml.ucore.runtime.ITreeValueChangeListener}
     * isn't fired
     *
     * @param map    The map of AIS information
     * @param vessel The target to fill with data, preferably acquired by calling
     *               retrieveById on the TargetSet
     * @return The filled AisTarget
     */
    public static Vessel mapToVessel(Map<String, Object> map, Vessel vessel) {
        if (map == null || vessel == null) {
            return null;
        }

        map = removeAllNulls(map);

        if (map.containsKey(NMEAFieldIds.NMEA_LATITUDE) && map.containsKey(NMEAFieldIds.NMEA_LONGITUDE)) {
            Coordinate coordinate = new CoordinateImpl(
                    ((Number) map.get(NMEAFieldIds.NMEA_LATITUDE)).doubleValue(),
                    ((Number) map.get(NMEAFieldIds.NMEA_LONGITUDE)).doubleValue(),
                    CRSUtils.WGS84_2D);

            if (!debugFilterPosition(coordinate))
                return null;

            if (!(sameD(coordinate.getLatitude(), 0.0d) && sameD(coordinate.getLongitude(), 0.0d))
                    && !(sameD(coordinate.getLatitude(), 18.366885) && sameD(coordinate.getLongitude(), -25.39764))) {
                AisTarget.setCoordinate(vessel, coordinate);
                // AisTarget.setTimestamp(vessel, System.currentTimeMillis());
            } else {
                LOG.warn("Received weird coordinate, skipping {} {}", coordinate.getLatitude(),
                        coordinate.getLongitude());
            }
        }
        /*
         * if (map.containsKey("sourceMmsi")) { vessel.setMmsi((long)
         * map.get("sourceMmsi")); }
         */
        if (map.containsKey(NMEAFieldIds.NMEA_RATE_OF_TURN)) {
            Double rotv = AISPayloadEncryptionUtil
                    .getROTValue(Integer.parseInt(map.get(NMEAFieldIds.NMEA_RATE_OF_TURN).toString()));
            if (rotv != null) {
                PhysicalObjectUtils.setRateOfTurn(new AngularSpeedImpl(rotv, AngularSpeedUnit.DEGREES_PER_MINUTE),
                        vessel);
            }
        }
        if (map.containsKey(NMEAFieldIds.NMEA_NAVIGATION_STATUS)) {
            vessel.setPropertyValue(NMEAFieldIds.NMEA_NAVIGATION_STATUS,
                    ((NavigationStatus) map.get(NMEAFieldIds.NMEA_NAVIGATION_STATUS)).name());
        }
        if (map.containsKey(NMEAFieldIds.NMEA_MANEUVER_INDICATOR)) {
            vessel.setPropertyValue(NMEAFieldIds.NMEA_MANEUVER_INDICATOR,
                    ((ManeuverIndicator) map.get(NMEAFieldIds.NMEA_MANEUVER_INDICATOR)).name());
        }
        if (map.containsKey(NMEAFieldIds.NMEA_TRUE_HEADING)) {
            vessel.getPose().setOrientation(new EulerImpl(.0d, .0d,
                    ((Number) map.get(NMEAFieldIds.NMEA_TRUE_HEADING)).intValue(), AngleUnit.DEGREE));
        }
        if (map.containsKey(NMEAFieldIds.NMEA_HEADING)) {
            Number head = (Number) map.get(NMEAFieldIds.NMEA_HEADING);
            vessel.getPose().setOrientation(new EulerImpl(.0d, .0d, head.intValue(), AngleUnit.DEGREE));
        }
        if (map.containsKey("degrees")) {
            vessel.getPose().setOrientation(
                    new EulerImpl(.0d, .0d, ((Number) map.get("degrees")).doubleValue(), AngleUnit.DEGREE));
        }
        if (map.containsKey("rot")) {
            PhysicalObjectUtils.setRateOfTurn(
                    new AngularSpeedImpl(((Number) map.get("rot")).doubleValue(), AngularSpeedUnit.DEGREES_PER_MINUTE),
                    vessel);
        }
        if (map.containsKey(NMEAFieldIds.NMEA_COURSE_OVER_GROUND)
                && map.containsKey(NMEAFieldIds.NMEA_SPEED_OVER_GROUND)) {
            PhysicalObjectUtils.setCOGAndSOG(vessel,
                    new AngleImpl(((Number) map.get(NMEAFieldIds.NMEA_COURSE_OVER_GROUND)).floatValue(),
                            AngleUnit.DEGREE),
                    new SpeedImpl(((Number) map.get(NMEAFieldIds.NMEA_SPEED_OVER_GROUND)).floatValue(),
                            SpeedUnit.KNOTS));
        }
        if (map.containsKey("headingMagnetic") && map.containsKey("speedKnots")) {
            PhysicalObjectUtils.setCOGAndSOG(vessel,
                    new AngleImpl(((Number) map.get("headingMagnetic")).doubleValue(), AngleUnit.DEGREE),
                    new SpeedImpl(((Number) map.get("speedKnots")).doubleValue(), SpeedUnit.KNOTS));
        }
        if (map.containsKey(NMEAFieldIds.NMEA_COURSE_OVER_GROUND)
                && !map.containsKey(NMEAFieldIds.NMEA_SPEED_OVER_GROUND)) {
            // TODO: fix setting of COG without SOG
            Number number = (Number) map.get(NMEAFieldIds.NMEA_COURSE_OVER_GROUND);
            PhysicalObjectUtils.setCOG(vessel, new AngleImpl(number.floatValue(), AngleUnit.DEGREE));
        }
        if (map.containsKey(NMEAFieldIds.NMEA_SPEED_OVER_GROUND)
                && !map.containsKey(NMEAFieldIds.NMEA_COURSE_OVER_GROUND)) {
            // TODO: fix setting of SOG without COG
            Number number = (Number) map.get(NMEAFieldIds.NMEA_SPEED_OVER_GROUND);
            PhysicalObjectUtils.setSOG(vessel, (new SpeedImpl(number.floatValue(), SpeedUnit.KNOTS)));
        }
        if (map.containsKey(NMEAFieldIds.NMEA_SECOND)) {
            vessel.setPropertyValue(NMEAFieldIds.NMEA_SECOND, ((Number) map.get(NMEAFieldIds.NMEA_SECOND)).intValue());
        }
        if (map.containsKey(NMEAFieldIds.NMEA_CALLSIGN)) {
            vessel.setCallSign((String) map.get(NMEAFieldIds.NMEA_CALLSIGN));
        }
        if (map.containsKey(NMEAFieldIds.NMEA_IMO)) {
            vessel.setImo(((IMO) map.get(NMEAFieldIds.NMEA_IMO)).getIMO());
        }
        if (map.containsKey(NMEAFieldIds.NMEA_SHIPNAME)) {
            vessel.setName((String) map.get(NMEAFieldIds.NMEA_SHIPNAME)); // TODO: check, if setName is correct
        }

        if (map.containsKey(NMEAFieldIds.NMEA_TO_BOW) &&
                map.containsKey(NMEAFieldIds.NMEA_TO_STERN) &&
                map.containsKey(NMEAFieldIds.NMEA_TO_PORT) &&
                map.containsKey(NMEAFieldIds.NMEA_TO_STARBOARD)) {

            Number bow = (Number) map.get(NMEAFieldIds.NMEA_TO_BOW);
            Number stern = (Number) map.get(NMEAFieldIds.NMEA_TO_STERN);
            Number port = (Number) map.get(NMEAFieldIds.NMEA_TO_PORT);
            Number starboard = (Number) map.get(NMEAFieldIds.NMEA_TO_STARBOARD);

            setDimensions(vessel,
                    port.intValue(),
                    starboard.intValue(),
                    bow.intValue(),
                    stern.intValue());
        }

        if (map.containsKey(NMEAFieldIds.NMEA_POSITION_FIXING_DEVICE)) {
            vessel.setPropertyValue(NMEAFieldIds.NMEA_POSITION_FIXING_DEVICE,
                    ((PositionFixingDevice) map.get(NMEAFieldIds.NMEA_POSITION_FIXING_DEVICE)).name());
        }
        if (map.containsKey(NMEAFieldIds.NMEA_DRAUGHT)) {
            Number draught = (Number) map.get(NMEAFieldIds.NMEA_DRAUGHT);
            setDraught(vessel, draught.floatValue());
        }
        if (map.containsKey(NMEAFieldIds.NMEA_ETA)) {
            vessel.setPropertyValue(NMEAFieldIds.NMEA_ETA, (String) map.get(NMEAFieldIds.NMEA_ETA));
        }
        if (map.containsKey(NMEAFieldIds.NMEA_DESTINATION)) {
            vessel.setPropertyValue(NMEAFieldIds.NMEA_DESTINATION, (String) map.get(NMEAFieldIds.NMEA_DESTINATION));
        }
        if (map.containsKey(NMEAFieldIds.NMEA_DATA_TERMINAL_READY)) {
            vessel.setPropertyValue(NMEAFieldIds.NMEA_DATA_TERMINAL_READY,
                    (boolean) map.get(NMEAFieldIds.NMEA_DATA_TERMINAL_READY));
        }
        if (map.containsKey(NMEAFieldIds.NMEA_SHIPTYPE)) {
            // vessel.setType(VesselType.get(((ShipType) map.get("shipType")).name())); //
            // TODO: translate between NMEA0183 and S100 type names
            vessel.setType(AisS100VesselTypeDictionary.map().get((ShipType) map.get(NMEAFieldIds.NMEA_SHIPTYPE)));
        }

        try {
            VesselVerifier.verify(vessel);
        } catch (Exception ignored) {

        }
        return vessel;
    }

    private static void setDraught(Vessel vessel, float draught) {
        VesselDimensionCharacteristic vdc = vessel.getFirstCharacteristic(VesselDimensionCharacteristic.class, true);

        if (vdc == null) {
            vdc = new VesselDimensionCharacteristicImpl();
            vessel.getCharacteristics().add(vdc);
        }
        WatercraftHull hull = vdc.getHull();

        if (hull == null) {
            hull = new WatercraftHullImpl();
            vdc.setHull(hull);
        }

        hull.setDraft(new LengthImpl(draught, DistanceUnit.METER));
    }

    private static void setDimensions(Vessel vessel, int port, int starboard, int bow, int stern) {
        if ((port != 0 || starboard != 0) && (bow != 0 || stern != 0)) {
            try {
                double width = port + starboard;
                double len = bow + stern;
                ObjectSurfaceInformation info = vessel.getFirstCharacteristic(ObjectSurfaceInformation.class);
                if (info == null) {
                    info = new ObjectSurfaceInformationImpl();
                    vessel.getCharacteristics().add(info);
                }

                Geometry geometry = info.getGeometry();
                if (geometry == null) {
                    geometry = createGeometry(port, starboard, bow, stern);
                    info.setGeometry(geometry);
                }

                GeometryOperations ops = geometry.getDelegate();
                if (ops != null) {
                    ops.invalidate();
                }

                Length ow = PhysicalObjectUtils.getWidth(info);
                Length nw = new LengthImpl(width, DistanceUnit.METER);
                Length ol = PhysicalObjectUtils.getLength(info);
                Length nl = new LengthImpl(len, DistanceUnit.METER);

                double fw = nw.getAs(DistanceUnit.METER) / ow.getAs(DistanceUnit.METER);
                double fl = nl.getAs(DistanceUnit.METER) / ol.getAs(DistanceUnit.METER);

                if (fw != 1.0 && fl != 1.0) {
                    doTheScaling(geometry, fw, fl);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Geometry createGeometry(int port, int starboard, int bow, int stern) {
        Polygon geometry = new PolygonImpl();
        List<Coordinate> list = new ArrayList<>();
        list.add(new CoordinateImpl(-port, 0, CRSUtils.ENGINEERING_2D)); // left
        list.add(new CoordinateImpl(-port, bow, CRSUtils.ENGINEERING_2D)); // top left
        list.add(new CoordinateImpl(starboard, bow, CRSUtils.ENGINEERING_2D)); // top right
        list.add(new CoordinateImpl(starboard, 0, CRSUtils.ENGINEERING_2D)); // right
        list.add(new CoordinateImpl(starboard, -stern, CRSUtils.ENGINEERING_2D)); // bottom right
        list.add(new CoordinateImpl(-port, -stern, CRSUtils.ENGINEERING_2D)); // bottom left

        CoordinateSequence sequence = new CoordinateSequenceImpl(list);
        LinearRing ring = new LinearRingImpl(sequence);
        ring.close();
        geometry.setShell(ring);
        return geometry;
    }

    private static void doTheScaling(Geometry geom, double sx, double sy) {
        // we need to change the CRS of the geometry, since we don't want to scale it in
        // local space. Therefore we remember the current CRS, replace it and put it
        // back when we are finished
        CoordinateReferenceSystem originalCRS = geom.getCRS();
        try {
            if (originalCRS != null && originalCRS instanceof Engineering2D == false)
                throw new UnsupportedOperationException(
                        "Expected some engineering2D CRS for the geometry to be scaled");
            Engineering2D newCRS = new Engineering2DImpl(); // we move the shape to the point 0,0 with not rotation by
                                                            // replacing the CRS
            geom.recursiveSetCRS(newCRS);
            // GeometryTransform gt = new GeometryTransform();
            // gt.scaleGeometryLocal(new Vector2DImpl(sx, sy), geom);

            Vector2D scale = new Vector2DImpl(sx, sy);
            for (int i = 0; i < geom.numCoordinates(); i++) {
                Coordinate c = geom.getCoordinate(i);
                c.set(c.getX() * scale.getX(), c.getY() * scale.getY(), Double.NaN);
            }
        } finally {
            geom.recursiveSetCRS(originalCRS);
        }
    }

    public static final boolean sameD(Double a, Double b) {
        if (a == null || b == null) {
            return false;
        } else if (Math.abs(a - b) <= 0.0001) {
            return true;
        }
        return false;
    }

    /**
     * Starts the alert threads for controlling AIS and Ownship status indicators on
     * the UI.
     * 
     * @implNote When receiving NMEA or AIS messages, the lastAISUpdate (for AIS
     *           targets) and lastNMEAUpdate (for Ownship) timestamps
     *           are updated to the current system time. These threads check if the
     *           difference between the current system time and lastAISUpdate or
     *           lastNMEAUpdate is smaller than 10 seconds. If it is, the indicator
     *           is set to OK for AIS messages and for ownship, it is checked if
     *           the ship contains all necessary dynamic values. If it is, the
     *           indicator is set to OK, if not, set to PARTIAL. If the time
     *           difference is
     *           bigger than 10 seconds, the AIS and Ownship state are set to ERROR.
     */
    private static void initAlertThreads() {
        Thread aisStatusThread = new Thread(() -> {
            while (true) {
                if (lastAISUpdate == null) {
                    AlertManager.setState(NMEASensorIds.NMEA_SENSOR_AIS_ALERT_ID, AlertState.UNKNOWN);
                } else {
                    if (System.currentTimeMillis() - lastAISUpdate > 10000) {
                        AlertManager.setState(NMEASensorIds.NMEA_SENSOR_AIS_ALERT_ID, AlertState.ERROR);
                    } else {
                        AlertManager.setState(NMEASensorIds.NMEA_SENSOR_AIS_ALERT_ID, AlertState.OK);
                    }
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread ownshipStatusThread = new Thread(() -> {
            while (true) {
                if (lastNMEAUpdate == null) {
                    AlertManager.setState(NMEASensorIds.NMEA_SENSOR_OWNSHIP_ALERT_ID, AlertState.UNKNOWN);
                } else {
                    if (System.currentTimeMillis() - lastNMEAUpdate > 10000) {
                        AlertManager.setState(NMEASensorIds.NMEA_SENSOR_AIS_ALERT_ID, AlertState.ERROR);
                    } else {
                        Vessel vessel = EPDModelUtils.retrieveOwnship();
                        if (vessel == null) {
                            AlertManager.setState(NMEASensorIds.NMEA_SENSOR_AIS_ALERT_ID, AlertState.ERROR);
                        } else {
                            VesselVerifier.verify(vessel);
                            if (VesselVerifier.isCorrect(vessel) == false) {
                                AlertManager.setState(NMEASensorIds.NMEA_SENSOR_OWNSHIP_ALERT_ID, AlertState.PARTIAL);
                            } else {
                                AlertManager.setState(NMEASensorIds.NMEA_SENSOR_OWNSHIP_ALERT_ID, AlertState.OK);
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        aisStatusThread.setName("AisStatusThread");
        aisStatusThread.start();

        ownshipStatusThread.setName("OwnshipStatusThread");
        ownshipStatusThread.start();
    }

    private static boolean debugFilterPosition(Coordinate coordinate) {
        return true;
        /*
         * double lat0 = 53.946;
         * double lon0 = 8.604;
         * double lat1 = 53.877;
         * double lon1 = 8.740;
         * if (lat1 < lat0) {
         * double t0 = lat0;
         * lat0 = lat1;
         * lat1 = t0;
         * }
         * if (lon1 < lon0) {
         * double t1 = lon0;
         * lon0 = lon1;
         * lon1 = t1;
         * }
         * if (coordinate.getLatitude() >= lat0 && coordinate.getLongitude() >= lon0 &&
         * coordinate.getLatitude() <= lat1 && coordinate.getLongitude() <= lon1) {
         * return true;
         * }
         * return false;
         */
    }
}
