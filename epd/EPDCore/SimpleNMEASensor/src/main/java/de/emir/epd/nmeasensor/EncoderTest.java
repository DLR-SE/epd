package de.emir.epd.nmeasensor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;

import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.impl.VesselImpl;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.PoseImpl;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.service.codecs.nmea0183.encoding.sentence.AISSentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.Sentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.SentenceFactory;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.AISSentenceHandler;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.encryption.AISPayloadEncryptionUtil;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.DecodedAISPayload;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.PositionReportClassA;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.exceptions.InvalidEncodedPayload;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.ManeuverIndicator;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.NavigationStatus;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class EncoderTest {
	private static Logger LOG = ULog.getLogger(EncoderTest.class);
		
	public static void main2(String[] args) {
		double a1 = 90;
		double a2 = -90; //270
		double a3 = 400; //40
		double a4 = -400; // 320
		double a5 = -359; // 320
		System.out.println(a1 + " " + AISPayloadEncryptionUtil.normalizeHeading(a1));
		System.out.println(a2 + " " + AISPayloadEncryptionUtil.normalizeHeading(a2));
		System.out.println(a3 + " " + AISPayloadEncryptionUtil.normalizeHeading(a3));
		System.out.println(a4 + " " + AISPayloadEncryptionUtil.normalizeHeading(a4));
		System.out.println(a5 + " " + AISPayloadEncryptionUtil.normalizeHeading(a5));
	}
	
	public static void main3(String[] args) {
		int a1 = 90;
		int a2 = -90; //270
		int a3 = -126; //40
		int a4 = 126; // 320
		int a5 = -127; // 320
		int a6 = 127;
		int a7 = -128;
		int a8 = 125;
		int a9 = 0;
		System.out.println(a1 + " " + AISPayloadEncryptionUtil.getROTValue(a1));
		System.out.println(a2 + " " + AISPayloadEncryptionUtil.getROTValue(a2));
		System.out.println(a3 + " " + AISPayloadEncryptionUtil.getROTValue(a3));
		System.out.println(a4 + " " + AISPayloadEncryptionUtil.getROTValue(a4));
		System.out.println(a5 + " " + AISPayloadEncryptionUtil.getROTValue(a5));
		System.out.println(a6 + " " + AISPayloadEncryptionUtil.getROTValue(a6));
		System.out.println(a7 + " " + AISPayloadEncryptionUtil.getROTValue(a7));
		System.out.println(a8 + " " + AISPayloadEncryptionUtil.getROTValue(a8));
		System.out.println(a9 + " " + AISPayloadEncryptionUtil.getROTValue(a9));
	}
	
	public static void main(String[] args) {
		SentenceFactory factory = SentenceFactory.getInstance();
		AISSentenceHandler aisHandler = new AISSentenceHandler();
		List<String> mismatchedMsgs = new ArrayList<String>();
		double lat = 53.148930d;
		double lon = 8.200868d;
		double hdg = -90d;
		Vessel v = new VesselImpl();
		Vessel target = new VesselImpl();
		v.setMmsi(313131313);
		Pose p = new PoseImpl();
		lat = Double.parseDouble(String.format(Locale.US, "%.6f", lat));
		lon = Double.parseDouble(String.format(Locale.US, "%.6f", lon));
		hdg = Double.parseDouble(String.format(Locale.US, "%.4f", hdg));
		p.setCoordinate(new CoordinateImpl(lat, lon, CRSUtils.WGS84_2D));
		p.setOrientation(new EulerImpl(.0d, .0d, hdg, AngleUnit.DEGREE));
		v.setPose(p);
		PositionReportClassA pr;
		for (int i = 0; i < 10000; i++) {
			System.out.println(String.format("%f %f %f", lat, lon, hdg));
			pr = new PositionReportClassA(0, new MMSI(v.getMmsi()), NavigationStatus.NotDefined,
                    AISPayloadEncryptionUtil.normalizeROT((1420 * Math.random()) - 710), (float) AISPayloadEncryptionUtil.normalizeSOG(102.3f * Math.random()), true, v.getPose().getCoordinate().getLatitude(), v.getPose().getCoordinate().getLongitude(),
                    (float)  AISPayloadEncryptionUtil.normalizeCOG(v.getPose().getOrientation().toEuler().getZ().getValue()), (int) AISPayloadEncryptionUtil.normalizeHeading(v.getPose().getOrientation().toEuler().getZ().getValue()), Calendar.getInstance().getTime().getSeconds(), ManeuverIndicator.NotAvailable,
                    false);
			pr.encode();
			String nmeastr = pr.toNMEA();
			Map<String, Object> map0 = new HashMap<String, Object>();
			pr.fillMap(map0);
			System.out.println(map0);
			System.out.println(nmeastr.trim());
			
			Sentence s = factory.createSentence(nmeastr.trim());
			AISSentence parsed = new AISSentence(nmeastr.trim());
			parsed.parse();
			aisHandler.handleAISSentence(parsed);
			
			DecodedAISPayload decoded = null;
			try {
				decoded = aisHandler.getDecodedAISMessage();
			} catch (InvalidEncodedPayload e) {
				LOG.trace("Could not decode AIS payload.", e);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			if (decoded == null) {
				return;
			}
			PositionReportClassA pra = null;
			if (decoded.getMessageType().equals(AISMessageType.PositionReportClassA)) {
				pra = (PositionReportClassA) decoded;
				pra.fillMap(map);
				target = NMEAVesselUpdater.mapToVessel(pr.toMap(), target);
			}
			System.out.println(map);
			System.out.println(CRSUtils.toDegreeMinuteSecond(pr.getLatitude()) + " / " + CRSUtils.toDegreeMinuteSecond(pr.getLongitude()));
			if (pra != null) {
				System.out.println(CRSUtils.toDegreeMinuteSecond(pra.getLatitude()) + " / " + CRSUtils.toDegreeMinuteSecond(pra.getLongitude()));
				if (Math.abs(pr.getLatitude() - pra.getLatitude()) > 0.00001 || Math.abs(pr.getLongitude() - pra.getLongitude()) > 0.00001) {
					System.out.println("VALUE MISMATCH");
					mismatchedMsgs.add(nmeastr.trim());
				}
			} else {
				System.out.println("...");
			}
			
			System.out.println("\n");
			
			
			lat += Math.random();
			lat -= Math.random();
			if (lat > 90 || lat < -90) lat = Math.abs(lat) - 2;
			lon += Math.random();
			lon -= Math.random();
			if (lon > 180 || lon < -180) lon = Math.abs(lon) - 2;
			hdg += Math.random() * 10;
			hdg -= Math.random() * 10;
			lat *= -1;
			lon *= -1;
//			lat = Double.parseDouble(String.format(Locale.US, "%.6f", lat));
//			lon = Double.parseDouble(String.format(Locale.US, "%.6f", lon));
//			hdg = Double.parseDouble(String.format(Locale.US, "%.4f", hdg));
			p.getCoordinate().setLatLon(lat, lon);
			p.setOrientation(new EulerImpl(.0d, .0d, hdg, AngleUnit.DEGREE));
			
			aisHandler = new AISSentenceHandler();
		}
		for (String msg : mismatchedMsgs) {
			System.err.println("Mismatched: " + msg);
		}
	}

}
