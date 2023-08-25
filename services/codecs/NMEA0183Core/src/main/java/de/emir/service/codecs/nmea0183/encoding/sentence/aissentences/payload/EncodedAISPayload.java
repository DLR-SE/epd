package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload;


import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.encryption.AISPayLoadEncryptionMaps;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;

public class EncodedAISPayload {

	private final String originalNmea;
	private final String bitString;
	private final String encodedPaylaod;
	

	/**
	 * 
	 * This class represents an encoded PayloadMessage as characters(encodedPaylaod) and bits(bitString). 
	 * It considers both cases of the payloadMessage: non-fragmented , fragmented.
	 * For fragmented payloadMessage, the encoded PayloadMessage should be concatenated in (encodedPaylaod). 
	 * @param originalNmea
	 * @param encodedPayload
	 * @param paddingBits
	 */
	public EncodedAISPayload(String originalNmea, String encodedPayload, Integer paddingBits) {
		this.originalNmea = originalNmea;
		this.encodedPaylaod = encodedPayload;
		this.bitString = toBitString(encodedPayload, paddingBits);
	}

	public final AISMessageType getMessageType() {
		return AISMessageType.fromInteger(Integer.parseInt(bitString.substring(0,6), 2));
	}

	public final String getEncodedString() {
		return this.encodedPaylaod;
	}
	
	public final String getBits() {
		return bitString;
	}

	public final String getBits(Integer beginIndex, Integer endIndex) {
		return bitString.substring(beginIndex, endIndex);
	}

	public final Integer getNumberOfBits() {
		return bitString.length();
	}
	
	public Boolean isValid() {
		if (bitString.length() < 6) {
			return Boolean.FALSE;
		}
		
		int messageType = Integer.parseInt(bitString.substring(0,6), 2);
		if (messageType < 1 || messageType > 26) {
			return Boolean.FALSE;
		}
		
		int actualMessageLength = bitString.length();
		switch (messageType) {
		case 1: 
		case 2:
		case 3:
		case 4:
			if (actualMessageLength != 168) 
				return Boolean.FALSE;
			break;
		case 5: 
			if (actualMessageLength != 424) 
				return Boolean.FALSE;
			break;
		case 6: 
			break;
		case 7: 
			break;
		case 8: 
			break;
		case 9: 
			break;
		case 10: 
			break;
		case 11: 
			break;
		case 12: 
			break;
		case 13: 
			break;
		case 14: 
			break;
		case 15: 
			if (actualMessageLength != 88 && actualMessageLength != 110 && actualMessageLength != 112 && actualMessageLength != 160) 
				return Boolean.FALSE;
			break;
		case 16: 
			break;
		case 17: 
			break;
		case 18: 
			break;
		case 19: 
			break;
		case 20: 
			break;
		case 21: 
			break;
		case 22: 
			break;
		case 23: 
			break;
		case 24: 
			break;
		case 25: 
			break;
		case 26: 
			break;
		default: 
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
	
	private static String toBitString(String encodedString, Integer paddingBits) {
		StringBuffer bitString = new StringBuffer();
		int n = encodedString.length();
		for (int i=0; i<n; i++) {
			String c = encodedString.substring(i, i+1);
			bitString.append(AISPayLoadEncryptionMaps.charToSixBit.get(c));
		}
		return bitString.substring(0, bitString.length() - paddingBits);
	}

	public String getOriginalNmea() {
		return originalNmea;
	}
}
