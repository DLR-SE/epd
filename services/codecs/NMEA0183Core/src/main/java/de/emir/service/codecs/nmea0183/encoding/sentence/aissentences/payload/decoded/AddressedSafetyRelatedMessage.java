package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class AddressedSafetyRelatedMessage extends DecodedAISPayload {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8094515685838005675L;

	private final Integer sequenceNumber;
	private final MMSI destinationMmsi;
	private final Boolean retransmit;
	private final String text;
	
	public AddressedSafetyRelatedMessage(String originalNmea,
			Integer repeatIndicator, MMSI sourceMmsi, Integer sequenceNumber,
			MMSI destinationMmsi, Boolean retransmit, String text) {
		super(originalNmea, AISMessageType.AddressedSafetyRelatedMessage, repeatIndicator, sourceMmsi);
		this.sequenceNumber = sequenceNumber;
		this.destinationMmsi = destinationMmsi;
		this.retransmit = retransmit;
		this.text = text;
	}

	public final Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public final MMSI getDestinationMmsi() {
		return destinationMmsi;
	}

	public final Boolean getRetransmit() {
		return retransmit;
	}

	public final String getText() {
		return text;
	}

	@Override
	public void fillMap(Map<String, Object> res) {
		if (sequenceNumber != null) res.put("sequenceNumber", sequenceNumber);
		if (destinationMmsi != null) res.put("destinationMmsi", destinationMmsi);
		if (retransmit != null) res.put("retransmit", retransmit);
		if (text != null) res.put("text", text);
	}
}
