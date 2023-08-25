package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.exceptions;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.EncodedAISPayload;

public class InvalidEncodedPayload extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -840717313826383829L;
	
	private final EncodedAISPayload encodedMessage;

	public final EncodedAISPayload getEncodedMessage() {
		return encodedMessage;
	}

	public InvalidEncodedPayload(EncodedAISPayload encodedMessage) {
		super(encodedMessage.getEncodedString());
		this.encodedMessage = encodedMessage;
	}
}
