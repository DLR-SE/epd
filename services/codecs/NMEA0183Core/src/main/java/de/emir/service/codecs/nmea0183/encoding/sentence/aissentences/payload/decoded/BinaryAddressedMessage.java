package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.encryption.AISPayloadEncryptionUtil;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;

/**
 * 
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class BinaryAddressedMessage extends DecodedAISPayload {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4929275421942010922L;
	
	private final Integer sequenceNumber;
	private final MMSI destinationMmsi;
	private final Boolean retransmit;
	private final Integer designatedAreaCode;
	private final Integer functionalId;
	private final String binaryData;
	
	public BinaryAddressedMessage(String originalNmea,
			Integer repeatIndicator, MMSI sourceMmsi, Integer sequenceNumber,
			MMSI destinationMmsi, Boolean retransmit,
			Integer designatedAreaCode, Integer functionalId, String binaryData) {
		super(originalNmea, AISMessageType.BinaryAddressedMessage, repeatIndicator, sourceMmsi);
		this.sequenceNumber = sequenceNumber;
		this.destinationMmsi = destinationMmsi;
		this.retransmit = retransmit;
		this.designatedAreaCode = designatedAreaCode;
		this.functionalId = functionalId;
		this.binaryData = binaryData;
	}

	// copy constructor
	public BinaryAddressedMessage(BinaryAddressedMessage copy) {
        this.messageType = AISMessageType.BinaryAddressedMessage;
		if (copy == null) {
			this.sourceMmsi = null;
			this.designatedAreaCode = null;
			this.functionalId = null;
			this.binaryData = null;
			this.sequenceNumber = null;
			this.destinationMmsi = null;
			this.retransmit = null;
		} else {
            this.repeatIndicator = copy.repeatIndicator;
			this.sourceMmsi = copy.sourceMmsi;
			this.designatedAreaCode = copy.designatedAreaCode;
			this.functionalId = copy.functionalId;
			this.binaryData = copy.binaryData;
			this.sequenceNumber = copy.sequenceNumber;
			this.destinationMmsi = copy.destinationMmsi;
			this.retransmit = copy.retransmit;
		}
	}

	@Override
	public void encode() {
		message = AISPayloadEncryptionUtil.encodeBinaryAddressedtMessage(this);
		super.encode();
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

	public final Integer getDesignatedAreaCode() {
		return designatedAreaCode;
	}

	public final Integer getFunctionalId() {
		return functionalId;
	}

	public final String getBinaryData() {
		return binaryData;
	}
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BinaryAddressedMessage [binaryData=")
                .append(getBinaryData()).append(", designatedAreaCode=")
                .append(getDesignatedAreaCode()).append(", destinationMmsi=")
                .append(getDestinationMmsi()).append(", functionalId=")
                .append(getFunctionalId()).append(", retransmit=")
                .append(getRetransmit()).append(", sequenceNumber=")
                .append(getSequenceNumber()).append("]");
        return builder.toString();
    }

	@Override
	public void fillMap(Map<String, Object> res) {
		if (sequenceNumber != null) res.put("sequenceNumber", sequenceNumber);
		if (destinationMmsi != null) res.put("destinationMmsi", destinationMmsi);
		if (retransmit != null) res.put("retransmit", retransmit);
		if (designatedAreaCode != null) res.put("designatedAreaCode", designatedAreaCode);
		if (functionalId != null) res.put("functionalId", functionalId);
		if (binaryData != null) res.put("binaryData", binaryData);
		super.fillMap(res);
	}
}
