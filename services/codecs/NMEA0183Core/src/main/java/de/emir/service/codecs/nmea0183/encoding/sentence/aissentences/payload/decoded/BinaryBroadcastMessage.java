package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.encryption.AISPayloadEncryptionUtil;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.IMO;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;

/**
 * 
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class BinaryBroadcastMessage extends DecodedAISPayload {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7702966671077548107L;
	
	private final Integer designatedAreaCode;
	private final Integer functionalId;
	private final String binaryData;
	
	public BinaryBroadcastMessage(String originalNmea, Integer repeatIndicator, MMSI sourceMmsi,
			Integer designatedAreaCode, Integer functionalId, String binaryData) {
		super(originalNmea, AISMessageType.BinaryBroadcastMessage, repeatIndicator,
				sourceMmsi);
		this.designatedAreaCode = designatedAreaCode;
		this.functionalId = functionalId;
		this.binaryData = binaryData;
	}
	
	public BinaryBroadcastMessage(Integer repeatIndicator, MMSI sourceMmsi,
			Integer designatedAreaCode, Integer functionalId, String binaryData) {
		super(AISMessageType.BinaryBroadcastMessage, repeatIndicator,
				sourceMmsi);
		this.designatedAreaCode = designatedAreaCode;
		this.functionalId = functionalId;
		this.binaryData = binaryData;
	}
	
	// copy constructor
	public BinaryBroadcastMessage(BinaryBroadcastMessage copy) {
        this.messageType = AISMessageType.BinaryBroadcastMessage;
		if (copy == null) {
			this.sourceMmsi = null;
			this.designatedAreaCode = null;
			this.functionalId = null;
			this.binaryData = null;
		} else {
            this.repeatIndicator = copy.repeatIndicator;
			this.sourceMmsi = copy.sourceMmsi;
			this.designatedAreaCode = copy.designatedAreaCode;
			this.functionalId = copy.functionalId;
			this.binaryData = copy.binaryData;
		}
	}

	public Integer getDesignatedAreaCode() {
		return designatedAreaCode;
	}

	public Integer getFunctionalId() {
		return functionalId;
	}

	public final String getBinaryData() {
		return binaryData;
	}
	
	@Override
	public void encode() {
		super.encode();
	}
	
	public void encodeAIS8() {
		message = AISPayloadEncryptionUtil.encodeBinaryBroadcastMessage(this);
		super.encode();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BinaryBroadcastMessage [designatedAreaCode=")
				.append(designatedAreaCode).append(", functionalId=")
				.append(functionalId).append(", binaryData=")
				.append(binaryData).append("]");
		return builder.toString();
	}

	@Override
	public void fillMap(Map<String, Object> res) {
		if (designatedAreaCode != null) res.put("designatedAreaCode", designatedAreaCode);
		if (functionalId != null) res.put("functionalId", functionalId);
		if (binaryData != null) res.put("binaryData", binaryData);
		super.fillMap(res);
	}
}
