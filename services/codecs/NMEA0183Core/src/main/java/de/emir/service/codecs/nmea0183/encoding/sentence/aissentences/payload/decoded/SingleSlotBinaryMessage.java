package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded;

import java.util.Map;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;

public class SingleSlotBinaryMessage extends DecodedAISPayload {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7966018113759367164L;
	
	private final Boolean destinationIndicator;
	private final Boolean binaryDataFlag;
	private final MMSI destinationMMSI;
	private final String binaryData;
	
	public SingleSlotBinaryMessage(String originalNmea,
			Integer repeatIndicator, MMSI sourceMmsi,
			Boolean destinationIndicator, Boolean binaryDataFlag,
			MMSI destinationMMSI, String binaryData) {
		super(originalNmea, AISMessageType.SingleSlotBinaryMessage, repeatIndicator, sourceMmsi);
		this.destinationIndicator = destinationIndicator;
		this.binaryDataFlag = binaryDataFlag;
		this.destinationMMSI = destinationMMSI;
		this.binaryData = binaryData;
	}
	public final Boolean getDestinationIndicator() {
		return destinationIndicator;
	}
	public final Boolean getBinaryDataFlag() {
		return binaryDataFlag;
	}
	public final MMSI getDestinationMMSI() {
		return destinationMMSI;
	}
	public final String getBinaryData() {
		return binaryData;
	}
	@Override
	public void fillMap(Map<String, Object> res) {
		if (destinationIndicator != null) res.put("destinationIndicator", destinationIndicator);
		if (binaryDataFlag != null) res.put("binaryDataFlag", binaryDataFlag);
		if (destinationMMSI != null) res.put("destinationMMSI", destinationMMSI);
		if (binaryData != null) res.put("binaryData", binaryData);
	}
}
