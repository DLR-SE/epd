package de.emir.epd.nmeasensor;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;

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
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.AngularSpeedUnit;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.impl.AngleImpl;
import de.emir.model.universal.units.impl.AngularSpeedImpl;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.model.universal.units.impl.LengthImpl;
import de.emir.model.universal.units.impl.SpeedImpl;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import net.sf.marineapi.ais.message.AISMessage;
import net.sf.marineapi.ais.message.AISMessage05;
import net.sf.marineapi.ais.message.AISMessage19;
import net.sf.marineapi.ais.message.AISMessage24;
import net.sf.marineapi.ais.message.AISPositionInfo;
import net.sf.marineapi.ais.message.AISPositionReport;
import net.sf.marineapi.ais.message.AISPositionReportB;
import net.sf.marineapi.nmea.parser.DataNotAvailableException;
import net.sf.marineapi.nmea.sentence.HeadingSentence;
import net.sf.marineapi.nmea.sentence.PositionSentence;
import net.sf.marineapi.nmea.sentence.RMCSentence;
import net.sf.marineapi.nmea.sentence.ROTSentence;
import net.sf.marineapi.nmea.sentence.Sentence;
import net.sf.marineapi.nmea.sentence.TimeSentence;
import net.sf.marineapi.nmea.sentence.VTGSentence;

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

    private static Double lastHDG = null;
    private static Double lastCOG = null;
    private static Double lastSOG = null;
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

        // Starts treads which control the status indicator of Ownship and AIS on the UI.
        initAlertThreads();

		NMEAOutput.subscribeAISMessages(entry -> {
			AISMessage sentence = entry.getValue();
			if (sentence instanceof AISMessage) {
				lastAISUpdate = System.currentTimeMillis();
				// If ownship source was set to internal, get the ownship and check if the AIS
				// message MMSI matches the ownship. IF it does, filter
				// out all dynamic AIS messages to prevent mixing of ownship NMEA sentences and
				// ownship AIS messages ownship coordinates.
				if (ownshipFilter == OwnshipSource.INTERNAL) {
					if (ownship != null) {
						if (Long.compare(sentence.getMMSI(), ownship.getMmsi()) == 0) {
							// Do not process dynamic AIS attributes if ownship source is internal and the
							// ais message MMSI matches the ownship.
							AisTarget.updateVessel(msgToVessel(sentence, ownship, true));
							return;
						}
					} else {
						// In case no ownship mmsi is found something went wrong. Try processing as
						// normal ais messages.
						LOG.error("Could not filter for ownship ais messages because there is no ownship MMSI. "
								+ "Defaulting to not processing ownship source.");
					}
				}

				// Default procedure when ownship source is set to AIS, none or there was
				// nothing to filter.
				Vessel target = EPDModelUtils.retrieveById(aisTargets, Long.toString(sentence.getMMSI()));

				if (ownshipFilter == OwnshipSource.AISTARGET && target.getAllias().contains("Ownship")) {
					// If the ownship source is set to AIS and the retrieved target is marked as
					// ownship, update the alertstate for the ownship.
					lastNMEAUpdate = System.currentTimeMillis();
				}

				AisTarget.updateVessel(msgToVessel(sentence, target, false));
			} else {
				if (ownshipFilter == OwnshipSource.NO_PROCESSING || ownshipFilter == OwnshipSource.INTERNAL) {
					// Only process NMEA messages if no source filter is active or the source filter
					// is set to internal.
					lastNMEAUpdate = System.currentTimeMillis();
					Vessel ownship = EPDModelUtils.retrieveOwnship();
					if (ownship != null) {
						try {
//							msgToVessel(sentence, ownship, true);
							AisTarget.updateVessel(ownship);
						} catch (Exception e) {
							LOG.error("Could not update Ownship from AIS data.", e);
						}
					}
				}
			}
		});
		NMEAOutput.subscribeSentences(entry -> {
			if (ownshipFilter == OwnshipSource.AISTARGET) {
				return;
			}
			try {
				Sentence sentence = entry.getValue();
				Vessel ownship = EPDModelUtils.retrieveOwnship();
				lastNMEAUpdate = System.currentTimeMillis();
				if (sentence instanceof PositionSentence pos) {
					try {
						Coordinate coordinate = new CoordinateImpl(pos.getPosition().getLatitude(),
								pos.getPosition().getLongitude(), CRSUtils.WGS84_2D);
						AisTarget.setCoordinate(ownship, coordinate);
					} catch (DataNotAvailableException e2) {
						LOG.trace("No position in PositionSentence. Check your data source.");
					}
				}

				if (sentence instanceof HeadingSentence hs) {
					try {
						ownship.getPose().setOrientation(new EulerImpl(.0d, .0d, hs.getHeading(), AngleUnit.DEGREE));
						lastHDG = hs.getHeading();
					} catch (DataNotAvailableException e2) {
						LOG.trace("No heading in HeadingSentence. Check your data source.");
					}
				}

				// The parser breaks down on every single message, when timing is not set. 
				if (sentence instanceof TimeSentence ts) {
					try {
						if (ts.getTime() != null) {
							AisTarget.setTimestamp(ownship, ts.getTime().getMilliseconds());
						}
					} catch (DataNotAvailableException e2) {
						LOG.trace("No position in PositionSentence. Check your data source.");
					}
				}

				if (sentence instanceof ROTSentence rot) {
					try {
						PhysicalObjectUtils.setRateOfTurn(
							new AngularSpeedImpl(rot.getRateOfTurn(), AngularSpeedUnit.DEGREES_PER_MINUTE), ownship);
					} catch (DataNotAvailableException e2) {
						LOG.trace("No rate of turn in ROTSentence. Check your data source.");
					}
				}

				if (sentence instanceof VTGSentence vtg) {
					try {
						PhysicalObjectUtils.setSOG(ownship, (new SpeedImpl(vtg.getSpeedKnots(), SpeedUnit.KNOTS)));
						lastSOG = vtg.getSpeedKnots();
					} catch (DataNotAvailableException e2) {
						LOG.trace("No speed in VTGSentence. Check your data source.");
					}
					try {
						PhysicalObjectUtils.setCOG(ownship, new AngleImpl(vtg.getMagneticCourse(), AngleUnit.DEGREE));
						lastCOG = vtg.getMagneticCourse();
					} catch (DataNotAvailableException e2) {
						LOG.trace("No magnetic course in VTGSentence. Check your data source.");
						// Course was not available but speed was already set. Use 360 as course (unknwon). This
						// is just an emergency workaround to avoid broken course/speed vectors in vessels.
						if (lastSOG != null && lastCOG == null) {
							PhysicalObjectUtils.setCOG(ownship,	new AngleImpl(360.0f, AngleUnit.DEGREE));
						}
					}
					try {
						PhysicalObjectUtils.setCOG(ownship, new AngleImpl(vtg.getTrueCourse(), AngleUnit.DEGREE));
						lastCOG = vtg.getTrueCourse();
					} catch (DataNotAvailableException e2) {
						LOG.trace("No true course in VTGSentence. Check your data source.");
						// Course was not available but speed was already set. Use 360 as course (unknwon). This
						// is just an emergency workaround to avoid broken course/speed vectors in vessels.
						if (lastSOG != null && lastCOG == null) {
							PhysicalObjectUtils.setCOG(ownship,	new AngleImpl(360.0f, AngleUnit.DEGREE));
						}
					}
				}

				if (sentence instanceof RMCSentence rmc) {
					try {
						PhysicalObjectUtils.setSOG(ownship, (new SpeedImpl(rmc.getSpeed(), SpeedUnit.KNOTS)));
						lastSOG = rmc.getSpeed();
					} catch (DataNotAvailableException e2) {
						LOG.trace("No speed in RMCSentence. Check your data source.");
					}
					try {
						PhysicalObjectUtils.setCOG(ownship, new AngleImpl(rmc.getCourse(), AngleUnit.DEGREE));
						lastCOG = rmc.getCourse();
						if (rmc.getPosition() != null) {
							PhysicalObjectUtils.setCOG(ownship,
									new AngleImpl(rmc.getCorrectedCourse(), AngleUnit.DEGREE));
							lastCOG = rmc.getCorrectedCourse();
						}
					} catch (DataNotAvailableException e2) {
						LOG.debug("No course in RMCSentence. Check your data source.");
						// Course was not available but speed was already set. Use 360 as course (unknwon). This
						// is just an emergency workaround to avoid broken course/speed vectors in vessels.
						if (lastSOG != null && lastHDG != null && lastCOG == null) {
							PhysicalObjectUtils.setCOG(ownship,	new AngleImpl(360.0f, AngleUnit.DEGREE));
						}
					}
				}
				AisTarget.updateVessel(ownship);
			} catch (Exception e) {
				LOG.error("Error while parsing NMEA data.", e);
			}
		});
	}

    public static Vessel msgToVessel(AISMessage msg, Vessel vessel, boolean staticOnly) {
        if (msg == null || vessel == null) {
            return null;
        }
        
		if (!staticOnly) {
			if (msg instanceof AISPositionInfo posInfo) {
				Coordinate coordinate = new CoordinateImpl(posInfo.getLatitudeInDegrees(),
						posInfo.getLongitudeInDegrees(), CRSUtils.WGS84_2D);

				if (!debugFilterPosition(coordinate))
					return null;

				if (!(sameD(coordinate.getLatitude(), 0.0d) && sameD(coordinate.getLongitude(), 0.0d))
						&& !(sameD(coordinate.getLatitude(), 18.366885)
								&& sameD(coordinate.getLongitude(), -25.39764))) {
					AisTarget.setCoordinate(vessel, coordinate);
					// AisTarget.setTimestamp(vessel, System.currentTimeMillis());
				} else {
					LOG.warn("Received weird coordinate, skipping {} {}", coordinate.getLatitude(),
							coordinate.getLongitude());
				}
			}

			if (msg instanceof AISPositionReportB prb) {
				if (prb.hasCourseOverGround()) {
					PhysicalObjectUtils.setCOG(vessel, new AngleImpl(prb.getCourseOverGround(), AngleUnit.DEGREE));
				}
				if (prb.hasTrueHeading()) {
					vessel.getPose().setOrientation(new EulerImpl(.0d, .0d, prb.getTrueHeading(), AngleUnit.DEGREE));
				}
				if (prb.hasSpeedOverGround()) {
					PhysicalObjectUtils.setSOG(vessel, (new SpeedImpl(prb.getSpeedOverGround(), SpeedUnit.KNOTS)));
				}
				if (prb.hasTimeStamp()) {
					vessel.setPropertyValue(NMEAFieldIds.NMEA_SECOND, prb.getTimeStamp());
				}
			}

			if (msg instanceof AISPositionReport pr) {
				vessel.setPropertyValue(NMEAFieldIds.NMEA_NAVIGATION_STATUS, pr.getNavigationalStatus());
				vessel.setPropertyValue(NMEAFieldIds.NMEA_MANEUVER_INDICATOR, pr.getManouverIndicator());
				if (pr.hasCourseOverGround()) {
					PhysicalObjectUtils.setCOG(vessel, new AngleImpl(pr.getCourseOverGround(), AngleUnit.DEGREE));
				}
				if (pr.hasTrueHeading()) {
					vessel.getPose().setOrientation(new EulerImpl(.0d, .0d, pr.getTrueHeading(), AngleUnit.DEGREE));
				}
				if (pr.hasSpeedOverGround()) {
					PhysicalObjectUtils.setSOG(vessel, (new SpeedImpl(pr.getSpeedOverGround(), SpeedUnit.KNOTS)));
				}
				if (pr.hasTimeStamp()) {
					vessel.setPropertyValue(NMEAFieldIds.NMEA_SECOND, pr.getTimeStamp());
				}
				if (pr.hasRateOfTurn()) {
					PhysicalObjectUtils.setRateOfTurn(
							new AngularSpeedImpl(pr.getRateOfTurn(), AngularSpeedUnit.DEGREES_PER_MINUTE), vessel);
				}
			}
		}

        if (msg instanceof AISMessage05 ais5) {
            vessel.setCallSign(ais5.getCallSign());
            vessel.setImo(ais5.getIMONumber());
            vessel.setName(ais5.getName());
//            vessel.setType(VesselType.get(ais5.getTypeOfShipAndCargoType()));
            vessel.setType(AisS100VesselTypeDictionary.map().get(ais5.getTypeOfShipAndCargoType()));
            setDimensions(vessel, ais5.getPort(), ais5.getStarboard(), ais5.getBow(), ais5.getStern());
            vessel.setPropertyValue(NMEAFieldIds.NMEA_POSITION_FIXING_DEVICE, ais5.getTypeOfEPFD());
            vessel.setPropertyValue(NMEAFieldIds.NMEA_DESTINATION, ais5.getDestination());
            vessel.setPropertyValue(NMEAFieldIds.NMEA_ETA, String.format("%2d%2d%2d%2d", ais5.getETAMonth(),
            		ais5.getETADay(), ais5.getETAHour(), ais5.getETAMinute()));
            setDraught(vessel, (float) ais5.getMaximumDraught());
            vessel.setPropertyValue(NMEAFieldIds.NMEA_DATA_TERMINAL_READY, ais5.isDteReady());
        }
        
        if (msg instanceof AISMessage19 ais19) {
            vessel.setName(ais19.getName());
//            vessel.setType(VesselType.get(ais19.getTypeOfShipAndCargoType()));
            vessel.setType(AisS100VesselTypeDictionary.map().get(ais19.getTypeOfShipAndCargoType()));
            setDimensions(vessel, ais19.getPort(), ais19.getStarboard(), ais19.getBow(), ais19.getStern());
            vessel.setPropertyValue(NMEAFieldIds.NMEA_POSITION_FIXING_DEVICE, ais19.getTypeOfEPFD());
        }
        
        if (msg instanceof AISMessage24 ais24) {
        	vessel.setCallSign(ais24.getCallSign());
            vessel.setName(ais24.getName());
//            vessel.setType(VesselType.get(ais24.getTypeOfShipAndCargoType()));
            vessel.setType(AisS100VesselTypeDictionary.map().get(ais24.getTypeOfShipAndCargoType()));
            setDimensions(vessel, ais24.getPort(), ais24.getStarboard(), ais24.getBow(), ais24.getStern());
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
