package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.blob;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.encryption.AISPayloadEncryptionUtil;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.BinaryAddressedMessage;

/**
 * 
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RouteInterrogation extends BinaryAddressedMessage {
	private Integer requestedDAC;
	private Integer requestedFI;

	public RouteInterrogation(BinaryAddressedMessage original, String binaryData) {
		super(original);
		if (binaryData == null || binaryData.length() <= 8)
			return;
		requestedDAC = AISPayloadEncryptionUtil.convertToUnsignedInteger(binaryData.substring(0, 10));
		requestedFI = AISPayloadEncryptionUtil.convertToUnsignedInteger(binaryData.substring(10, 16));
	}
	
	public RouteInterrogation(String binaryData) {
		this(null, binaryData);
	}

	/**
	 * @return the requestedFI
	 */
	public Integer getRequestedFI() {
		return requestedFI;
	}

	/**
	 * @param requestedFI the requestedFI to set
	 */
	public void setRequestedFI(Integer requestedFI) {
		this.requestedFI = requestedFI;
	}

	/**
	 * @return the requestedDAC
	 */
	public Integer getRequestedDAC() {
		return requestedDAC;
	}

	/**
	 * @param requestedDAC the requestedDAC to set
	 */
	public void setRequestedDAC(Integer requestedDAC) {
		this.requestedDAC = requestedDAC;
	}

	public String toBinaryData() {
		String result = null;
		String bitString = "";

		if (requestedDAC == null || requestedFI == null) {
			return "00000000";
		}

		bitString += AISPayloadEncryptionUtil.fillBits(Integer.toBinaryString(requestedDAC), 10);
		bitString += AISPayloadEncryptionUtil.fillBits(Integer.toBinaryString(requestedFI), 6);

		result = bitString;
		return result;
	}

	public RouteInterrogation() {
		this (null, null);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RouteInterrogation [toString()=")
				.append(super.toString()).append(", requestedDAC=").append(requestedDAC).append(", requestedFI=")
				.append(requestedFI).append("]");
		return builder.toString();
	}

	public void fillMap(Map<String, Object> res) {
        super.fillMap(res);
		if (requestedDAC != null)
			res.put("requestedDAC", requestedDAC);
		if (requestedFI != null)
			res.put("requestedFI", requestedFI);
	}
}
