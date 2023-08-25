package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded;

import java.util.Map;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;

public class UTCAndDateInquiry extends DecodedAISPayload {

	/**
	 * 
	 */
	private static final long serialVersionUID = 35743810927564987L;
	
	private MMSI destinationMmsi;

	public UTCAndDateInquiry(String originalNmea,
			Integer repeatIndicator, MMSI sourceMmsi, MMSI destinationMmsi) {
		super(originalNmea, AISMessageType.UTCAndDateInquiry, repeatIndicator, sourceMmsi);
		this.destinationMmsi = destinationMmsi;
	}

	public final MMSI getDestinationMmsi() {
		return destinationMmsi;
	}

	@Override
	public void fillMap(Map<String, Object> res) {
		if (destinationMmsi != null) res.put("destinationMmsi", destinationMmsi);
	}
}
