package de.emir.epd.nmeasensor;

import de.emir.epd.alert.manager.AlertManager;
import de.emir.epd.alert.manager.AlertState;
import de.emir.epd.model.EPDModel;
import de.emir.epd.model.EPDModelUtils;
import de.emir.epd.model.ais.AisTarget;
import de.emir.epd.nmeasensor.ids.NMEAFieldIds;
import de.emir.epd.nmeasensor.ids.NMEASensorIds;
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
import de.emir.service.codecs.nmea0183.encoding.sentence.Sentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.encryption.AISPayloadEncryptionUtil;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.DecodedAISPayload;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.*;
//import de.emir.service.geometry.impl.GeometryTransform;
import de.emir.tuml.ucore.runtime.logging.ULog;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NMEAVesselUpdater {
    private static final Logger LOG = ULog.getLogger(NMEAVesselUpdater.class);

    private static Vessel ownShip;

    private static Environment aisTargets;

    public static void init() {
        Object o = PlatformUtil.getModelManager().getModelProvider().getModel();
        if(!(o instanceof EPDModel)) return;
        EPDModel model = (EPDModel) o;
        aisTargets = model.getAisTargetSet();
        NMEAOutput.subscribeSentences(entry -> {
            Sentence sentence = entry.getValue();
            if (sentence instanceof DecodedAISPayload) {
                DecodedAISPayload payload = (DecodedAISPayload) sentence;
                if (payload.getSourceMmsi() != null) {
                    try {
                        Vessel target = EPDModelUtils.retrieveById(aisTargets,
                                Long.toString(payload.getSourceMmsi().getMMSI()));
                        AisTarget.updateVessel(mapToVessel(payload.toMap(), target));
                    }catch (Exception e){
                        //FIXME: This causes ConcurrentModificationException
                        e.printStackTrace();
                    }

                }
            } else {
                ownShip = NMEAVesselManager.getOwnShip();
                if (ownShip != null) {
                    try {
                        mapToVessel(sentence.toMap(), ownShip);
                        AisTarget.updateVessel(ownShip);
                    }catch (Exception e ){
                        e.printStackTrace();
                    }
                    try {
                        VesselVerifier.verify(ownShip);
                        if (VesselVerifier.isCorrect(ownShip) == false) {
                            AlertManager.setState(NMEASensorIds.NMEA_SENSOR_OWNSHIP_ALERT_ID, AlertState.PARTIAL);
                        } else {
                            AlertManager.setState(NMEASensorIds.NMEA_SENSOR_OWNSHIP_ALERT_ID, AlertState.OK);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static Vessel mapToVessel(Sentence sentence, Vessel vessel) {
        if (sentence == null || vessel == null) {
            return null;
        }
        return mapToVessel(sentence.toMap(), vessel);
    }

    private static Map<String, Object> removeAllNulls(Map<String, Object> map){
        Map<String, Object> newMap = new HashMap<>();
        for(String key : map.keySet()){
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
     * Ignores values if they are already set, ensures that {@link de.emir.tuml.ucore.runtime.ITreeValueChangeListener}
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

            if (!debugFilterPosition(coordinate)) return null;

            if (!(sameD(coordinate.getLatitude(), 0.0d) && sameD(coordinate.getLongitude(), 0.0d))
                    && !(sameD(coordinate.getLatitude(), 18.366885) && sameD(coordinate.getLongitude(), -25.39764))) {
                AisTarget.setCoordinate(vessel, coordinate);
//				AisTarget.setTimestamp(vessel, System.currentTimeMillis());
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
            Double rotv = AISPayloadEncryptionUtil.getROTValue(Integer.parseInt(map.get(NMEAFieldIds.NMEA_RATE_OF_TURN).toString()));
            if (rotv != null) {
                PhysicalObjectUtils.setRateOfTurn(new AngularSpeedImpl(rotv, AngularSpeedUnit.DEGREES_PER_MINUTE), vessel);
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
            vessel.getPose().setOrientation(new EulerImpl(.0d, .0d, ((Number) map.get(NMEAFieldIds.NMEA_TRUE_HEADING)).intValue(), AngleUnit.DEGREE));
        }
        if (map.containsKey(NMEAFieldIds.NMEA_HEADING)) {
            Number head = (Number) map.get(NMEAFieldIds.NMEA_HEADING);
            vessel.getPose().setOrientation(new EulerImpl(.0d, .0d, head.intValue(), AngleUnit.DEGREE));
        }
        if (map.containsKey("degrees")) {
            vessel.getPose().setOrientation(new EulerImpl(.0d, .0d, ((Number) map.get("degrees")).doubleValue(), AngleUnit.DEGREE));
        }
        if (map.containsKey("rot")) {
            PhysicalObjectUtils.setRateOfTurn(
                    new AngularSpeedImpl(((Number) map.get("rot")).doubleValue(), AngularSpeedUnit.DEGREES_PER_MINUTE), vessel);
        }
        if (map.containsKey(NMEAFieldIds.NMEA_COURSE_OVER_GROUND) && map.containsKey(NMEAFieldIds.NMEA_SPEED_OVER_GROUND)) {
            PhysicalObjectUtils.setCOGAndSOG(vessel,
                    new AngleImpl(((Number) map.get(NMEAFieldIds.NMEA_COURSE_OVER_GROUND)).floatValue(), AngleUnit.DEGREE),
                    new SpeedImpl(((Number) map.get(NMEAFieldIds.NMEA_SPEED_OVER_GROUND)).floatValue(), SpeedUnit.KNOTS));
        }
        if (map.containsKey("headingMagnetic") && map.containsKey("speedKnots")) {
            PhysicalObjectUtils.setCOGAndSOG(vessel,
                    new AngleImpl(((Number) map.get("headingMagnetic")).doubleValue(), AngleUnit.DEGREE),
                    new SpeedImpl(((Number) map.get("speedKnots")).doubleValue(), SpeedUnit.KNOTS));
        }
        if (map.containsKey(NMEAFieldIds.NMEA_COURSE_OVER_GROUND) && !map.containsKey(NMEAFieldIds.NMEA_SPEED_OVER_GROUND)) {
            // TODO: fix setting of COG without SOG
            Number number = (Number) map.get(NMEAFieldIds.NMEA_COURSE_OVER_GROUND);
            PhysicalObjectUtils.setCOG(vessel, new AngleImpl(number.floatValue(), AngleUnit.DEGREE));
        }
        if (map.containsKey(NMEAFieldIds.NMEA_SPEED_OVER_GROUND) && !map.containsKey(NMEAFieldIds.NMEA_COURSE_OVER_GROUND)) {
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
                    stern.intValue()
            );
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
            vessel.setPropertyValue(NMEAFieldIds.NMEA_DATA_TERMINAL_READY, (boolean) map.get(NMEAFieldIds.NMEA_DATA_TERMINAL_READY));
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
        list.add(new CoordinateImpl(-port, bow, CRSUtils.ENGINEERING_2D));  // top left
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

    private static boolean debugFilterPosition(Coordinate coordinate) {
        return true;
		/*
		double lat0 = 53.946;
		double lon0 = 8.604;
		double lat1 = 53.877;
		double lon1 = 8.740;
		if (lat1 < lat0) {
			double t0 = lat0;
			lat0 = lat1;
			lat1 = t0;
		}
		if (lon1 < lon0) {
			double t1 = lon0;
			lon0 = lon1;
			lon1 = t1;
		}
		if (coordinate.getLatitude() >= lat0 && coordinate.getLongitude() >= lon0 && coordinate.getLatitude() <= lat1 && coordinate.getLongitude() <= lon1) {
			return true;
		}
		return false;*/
    }
}
