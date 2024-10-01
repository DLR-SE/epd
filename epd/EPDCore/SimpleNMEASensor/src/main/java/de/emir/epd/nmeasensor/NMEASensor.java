/**
 * 
 */
package de.emir.epd.nmeasensor;

import com.google.common.collect.EvictingQueue;
import de.emir.epd.model.EPDModel;
import de.emir.epd.model.EPDModelUtils;
import de.emir.epd.nmeasensor.data.ReceiverType;
import de.emir.epd.nmeasensor.data.SentenceType;
import de.emir.epd.nmeasensor.ids.NMEASensorIds;
import de.emir.epd.nmeasensor.receiver.*;
import de.emir.model.domain.maritime.iec61174.Leg;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.WayPoints;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.domain.maritime.iec61174.impl.LegImpl;
import de.emir.model.domain.maritime.iec61174.impl.RouteImpl;
import de.emir.model.domain.maritime.iec61174.impl.WayPointsImpl;
import de.emir.model.domain.maritime.iec61174.impl.WaypointImpl;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.VoyageCharacteristic;
import de.emir.model.domain.maritime.vessel.impl.VoyageCharacteristicImpl;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.physics.Environment;
import de.emir.model.universal.physics.impl.EnvironmentImpl;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.impl.SpeedImpl;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.service.codecs.nmea0183.encoding.sentence.*;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.AISSentenceHandler;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.*;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.blob.RouteInterrogation;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.blob.RouteMessageBroadcast;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.blob.RouteMessageBroadcast.IntermediateLeg;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.exceptions.InvalidEncodedPayload;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.connection.interfaces.Receiver;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.service.connection.interfaces.ReceiverListener;
import org.slf4j.Logger;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public class NMEASensor implements ReceiverListener {
	/** Logger. */
	private static Logger LOG = ULog.getLogger(NMEASensor.class);
	private volatile IConnection nmeaReceiver;
	private IProperty<?> nmeaSensor;
	/** AIS Sentence Handler. */
	private AISSentenceHandler aisHandler;
	/** Last unhandeled AIS Sentence. */
	private String tempSentence;
	/** Unhandled Sentences. */
	private EvictingQueue<AISSentence> unhandledSentences = EvictingQueue.create(100);
	private Environment aisTargets;
	private PropListener propListener;
	private String tempMsg = "";
	private StringBuffer buffer = new StringBuffer();
    private String[] tempSAV;
    private List<SentenceType> allowList = new ArrayList<>();
    private PropertyContext ctx = PropertyStore.getContext(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT);
	
	/**
	 * This contains the NMEA sensor with receiver and parser.
	 */
	public NMEASensor(IProperty<?> sourceProperty) {
		Object o = PlatformUtil.getModelManager().getModelProvider().getModel();

		if (o instanceof EPDModel){
			EPDModel model = (EPDModel) o;
			this.aisTargets = model.getAisTargetSet();
		}else {
			// TODO if there is no EPDModel, aisTargets cannot be created!
			this.aisTargets = new EnvironmentImpl();
		}

		this.nmeaSensor = sourceProperty;
		this.propListener = new PropListener();
		this.nmeaSensor.addPropertyChangeListener(propListener);

		receive();
	}
    
    public String getNamePath() {
        return NMEASensorIds.NMEA_SENSOR_PROP + "." + nmeaSensor.getName();
    }
    
    public ReceiverType getType() {
        IProperty<String> typeProp = ctx.getProperty(
                getNamePath() + "." + NMEASensorIds.NMEA_SENSOR_PROP_TYPE, ReceiverType.UDP.name());
        return ReceiverType.valueOf(typeProp.getValue());
    }
    
    public boolean isActive() {
        IProperty<Boolean> activeProp = ctx.getProperty(
                getNamePath() + "." + NMEASensorIds.NMEA_SENSOR_PROP_ACTIVE, false);
        return activeProp.getValue();
    }
    
    public Object getAttribute(String attributeName) {
        IProperty prop = ctx.getProperty(getNamePath() + "." + attributeName, null);
        return prop.getValue();
    }
    
    public Object getAttributeOrDefault(String attributeName, Object def) {
        IProperty prop = ctx.getProperty(getNamePath() + "." + attributeName, def);
        return prop.getValue();
    }

	class PropListener implements PropertyChangeListener {
		@Override
		public synchronized void propertyChange(PropertyChangeEvent evt) {
            // Only inform about active receivers and stop them for changes
            if (nmeaReceiver != null && nmeaReceiver.getState() == true) {
                LOG.info("Changes in Receiver {} {}:{}", getNamePath(), evt.getPropertyName(), evt.getNewValue());
                try {
                    LOG.info("Stop receiver");
                    nmeaReceiver.stopReceiving();
                } catch (Exception e) {
                    LOG.error("Could not stop receiver " + getNamePath(), e);
                }
            }
			receive();
		}
	}
	
	public void receive() {
		try {
			EPDModelUtils.clear(this.aisTargets);
			this.aisHandler = new AISSentenceHandler();
            this.tempSAV = new String[2];
			
			switch (getType()) {
			case UDP:
				String localAdr = (String) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_INTERFACE, "0.0.0.0");
				int udpport = (int) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_PORT, 7003);
				int packetsize = (int) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_PACKETSIZE, 65535);
				nmeaReceiver = new UdpReceiverHandle(this, udpport, packetsize, localAdr);
				LOG.info("UDP Receiver {}:{} {}", localAdr, udpport, packetsize);
				break;
			case TCP:
				String hostName = (String) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_HOST, "127.0.0.1");
				int tcpport = (int) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_PORT, 16100);
				nmeaReceiver = new TcpReceiverHandle(this, hostName, tcpport, Constants.PACKETSIZE);
				LOG.info("TCP Receiver {}:{}", hostName, tcpport);
				break;
			case Serial:
				String serialPort = (String) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_SERIALPORT, "COM1");
				int baudrate = (int) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_BAUDRATE, 9600);
				int maxPacketsize = (int) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_PACKETSIZE, 65535);
				nmeaReceiver = new SerialReceiverHandle(this, serialPort, baudrate, maxPacketsize);
				LOG.info("Serial Receiver {} {} {}", serialPort, baudrate, maxPacketsize);
				break;
			case File:
				String filename = (String) getAttributeOrDefault(
                        NMEASensorIds.NMEA_SENSOR_PROP_FILENAME, "nmea.txt");
				int delay = (int) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_DELAY, 1000);
				int repeat = (int) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_REPEAT, -1);
				nmeaReceiver = new FileReceiverHandle(this, filename, delay, repeat);
				LOG.info("File Receiver {} {} {}", filename, delay, repeat);
				break;
			}
            
            String[] sentences = ((String) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_SENTENCES, "")).split(",");
            for (String sentence : sentences) {
                if (sentence.isEmpty() || sentence.isBlank()) continue;
                try {
                    allowList.add(SentenceType.valueOf(sentence));
                } catch (IllegalArgumentException e) {
                    LOG.warn("No SenenceType for name \"" + sentence + "\", ignoring entry.");
                    continue;
                }
            }
            
			if (isActive()) {
                // UDP can only send or recieve
				if (!(getType().equals(ReceiverType.UDP) && (boolean) getAttributeOrDefault(
                        NMEASensorIds.NMEA_SENSOR_PROP_OUTPUT, false))) {
                    LOG.info("Start receiver {}", getNamePath());
					nmeaReceiver.receive();
				}
			}
		} catch (Exception e) {
			LOG.error("Could not start NMEA receiver {}.", getNamePath());
		}
	}
	
	public void remove() {
		if (nmeaReceiver != null) nmeaReceiver.stopReceiving();
		if (nmeaSensor != null) nmeaSensor.removePropertyChangeListener(this.propListener);
	}
	
	private boolean allow(Object element) {
		boolean result = false;
		String clazz = element.getClass().getCanonicalName();
		for (SentenceType t : allowList) {
			result = t.getType().getCanonicalName().equals(clazz);
			if (result) break;
		}
		return result;
	}
	
	/**
	 * Decode the AIS payload and output its contents.
	 *
	 * @param handler
	 *            The handler containing to sentence to decode
	 * @param sentence
	 *
	 */
	private void decodeAis(final AISSentenceHandler handler, String sentence) {
		DecodedAISPayload decoded = null;
		try {
			decoded = handler.getDecodedAISMessage();
		} catch (InvalidEncodedPayload e) {
			LOG.trace("Could not decode AIS payload.", e);
		}

		if (decoded != null && !allow(decoded)) return;

		Map<String, Object> map = new HashMap<>();
		if (decoded == null) {
			return;
		} else if (decoded.getMessageType().equals(AISMessageType.BaseStationReport)) {
			BaseStationReport pr = (BaseStationReport) decoded;
//			if (!allow(pr)) return;
			pr.fillMap(map);
		} else if (decoded.getMessageType().equals(AISMessageType.BinaryBroadcastMessage)) {
			BinaryBroadcastMessage pr = (BinaryBroadcastMessage) decoded;
//			if (!allow(pr)) return;
			pr.fillMap(map);
//			LOG.info("BinaryBroadcast {}", pr.toString());
			try {
				RouteMessageBroadcast payload = new RouteMessageBroadcast(pr.getBinaryData());
				if (payload != null && payload.getLatitude() != null && payload.getLongitude() != null) {
					Vessel target = EPDModelUtils.retrieveById(aisTargets, Long.toString(
                            pr.getSourceMmsi().getMMSI()));
					addIntendedRoute(payload, target);
					notify(payload);
				}
			} catch (Exception e) {
				LOG.debug("Error while decoding RouteMessageBroadcast.", e);
			}
		} else if (decoded.getMessageType().equals(AISMessageType.BinaryAddressedMessage)) {
			BinaryAddressedMessage pr = (BinaryAddressedMessage) decoded;
//			if (!allow(pr))
//				return;
			pr.fillMap(map);
//				LOG.info("BinaryBroadcast {}", pr.toString());
			try {
				RouteInterrogation payload = new RouteInterrogation(pr.getBinaryData());
				if (payload != null) {
					notify(payload);
				}
			} catch (Exception e) {
				LOG.debug("Error while decoding RouteMessageBroadcast.", e);
			}
		} else {
			decoded.fillMap(map);
			if (LOG.isTraceEnabled()) LOG.trace("AIS {}: {}", decoded.getMessageType(), decoded.toMap());
			notify(decoded);
		}
	}

	/**
	 * Puts the intended route into a Vessel object.
	 * 
	 * @param payload
	 *            the intended route from a BinaryBroadcastMessage
	 * @param vessel
	 *            the vessel to update
	 * @return the original vessel object now containing the route
	 */
	public static Vessel addIntendedRoute(RouteMessageBroadcast payload, Vessel vessel) {
		LOG.debug(payload.toString());
		VoyageCharacteristic vc = null;
		vc = vessel.getFirstCharacteristic(VoyageCharacteristic.class, true);
		if (vc == null) {
			vessel.getCharacteristics().add(vc = new VoyageCharacteristicImpl());
		}
		Route route = new RouteImpl();
		Waypoint wp = new WaypointImpl();
		wp.setPosition(new CoordinateImpl(payload.getLatitude(), payload.getLongitude(), CRSUtils.WGS84_2D));
		WayPoints wps = new WayPointsImpl();
		route.setWaypoints(wps);
		route.getWaypoints().getWaypoints().add(wp);
		for (IntermediateLeg leg : payload.getIntermediateLegs()) {
			wp = new WaypointImpl();
			wp.setPosition(new CoordinateImpl(leg.getLatitude(), leg.getLongitude(), CRSUtils.WGS84_2D));
			Leg wpLeg = new LegImpl();
			if (leg.getSpeed() != null && leg.getSpeed() != 1023) {
				wpLeg.setPlanSpeedMin(new SpeedImpl(leg.getSpeed(), SpeedUnit.KNOTS));
				wpLeg.setPlanSpeedMax(new SpeedImpl(leg.getSpeed(), SpeedUnit.KNOTS));
			}
			if (leg.getRadius() != null && leg.getRadius() != 0f) {
				wp.setRadius(leg.getRadius());
			}
			wp.setLeg(wpLeg);
			route.getWaypoints().getWaypoints().add(wp);
		}
		vc.setActiveRoute(route);
		return vessel;
	}

	/**
	 * Decode the parsed NMEA sentence and output its contents.
	 *
	 * @param sentences
	 *            The NMEA sentences to decode
	 *
	 */
	private void decodeNMEA(final String[] sentences) {
		SentenceFactory factory = SentenceFactory.getInstance();
		for (String sentence : sentences) {
			if (LOG.isTraceEnabled()) LOG.trace(sentence);
			try {
				Sentence s = factory.createSentence(sentence.trim());

				if (s instanceof AISSentence) {
					AISSentence parsed = new AISSentence(sentence.trim());
					parsed.parse();
                    //if (parsed.getMessageId() == null) parsed.setMessageId(0);
					if (parsed.getFragmentId() > 1) {
						if (this.aisHandler.getUnhandledSentences().size() > 0
								&& parsed.getMessageId() != null
								&& parsed.getMessageId().equals(this.aisHandler.getUnhandledSentences().get(0).getMessageId())
						) {
							final AISSentence unhandledSentence = this.aisHandler
									.getUnhandledSentences().get(0);
							this.tempSAV = new String[2];
                            this.tempSAV[1] = new String(sentence.trim());
							this.tempSAV[0] = new String(unhandledSentence.getNmeaString());
							this.unhandledSentences.remove(this.aisHandler.getUnhandledSentences()
									.get(0));
						} else {
							Iterator<AISSentence> iterator = this.unhandledSentences.iterator();
							while (iterator.hasNext()) {
								AISSentence unhandledSentence = iterator.next();
								if (unhandledSentence.getMessageId().equals(parsed
										.getMessageId())) {
									this.tempSAV = new String[2];
                                    this.tempSAV[1] = new String(sentence.trim());
									this.tempSAV[0] = new String(unhandledSentence.getNmeaString());
									this.aisHandler.handleAISSentence(unhandledSentence);
									iterator.remove();
								}
							}
						}
					}
					try {
						this.aisHandler.handleAISSentence(parsed);
					} catch (Exception e) {
						return;
					}
					int unhandeledsize = aisHandler.getUnhandledSentences().size();
					if (unhandeledsize == 0) {
						decodeAis(aisHandler, sentence.trim());
						aisHandler = new AISSentenceHandler();
					} else if (unhandeledsize >= 1) {
						tempSentence = aisHandler.getUnhandledSentences()
								.get(aisHandler.getUnhandledSentences().size() - 1).getNmeaString();
					}
				} else if (s != null && allow(s)){
						s.parse();
						notify(s);
				}
			} catch (IllegalArgumentException e) {
				LOG.debug("IllegalArgumentException: " + e.getMessage());
			} catch (IllegalStateException e) {
				LOG.debug("IllegalStateException: ", e);
			} catch (InvalidEncodedPayload e) {
				LOG.trace("InvalidEncodedPayload: " + sentence, e);
			} catch (Exception e) {
				LOG.error("Exception while decoding: " + sentence, e);
			}
		}
	}
	
	private void notify(Sentence sentence) {
		NMEAOutput.notify(this, sentence);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.emir.commons.connection.interfaces.ReceiverListener#onReceived(de.emir.
	 * commons.connection.interfaces.Receiver, byte[])
	 */
	//@Override
    /*
	public void onReceived(IConnection source, byte[] data) {
		if (source.equals(nmeaReceiver)) {
			try {
				if (data != null && data.length > 0) {
                        	decodeNMEA(new String(data).trim().split("\n"));
				}
			} catch (Exception e) {
				LOG.trace("Exception while handling message. ", e);
			}
		}
	}*/
    
    @Override
    public void onReceived(Receiver source, byte[] data) {
        if (source.equals(nmeaReceiver)) {
			try {
				if (data != null && data.length > 0) {
                        	decodeNMEA(new String(data).trim().split("\n"));
				}
			} catch (Exception e) {
				LOG.trace("Exception while handling message. ", e);
			}
		}
    }

	public IConnection getNmeaReceiver() {
		return nmeaReceiver;
	}

//	public void update(ReceiverProperty rp) {
//		LOG.info("Stop receiver");
//		if (this.nmeaReceiver != null) {
//			getNmeaReceiver().stopReceiving();
//		}
//		this.receiverProperty = rp;
//		// TODO: only restart receiver when the connection details have changed
//		LOG.info("Start receiver");
//		receive();
//	}

}
