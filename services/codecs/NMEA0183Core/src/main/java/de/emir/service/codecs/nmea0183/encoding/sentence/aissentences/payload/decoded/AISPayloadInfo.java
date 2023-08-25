package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded;

public class AISPayloadInfo extends DecodedAISPayload {
	public AISPayloadInfo(DecodedAISPayload payload) {
		this.messageType = payload.messageType;
		this.repeatIndicator = payload.repeatIndicator;
		this.sourceMmsi = payload.getSourceMmsi();
	}
}
