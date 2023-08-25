package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences;

import java.util.ArrayList;
import java.util.Iterator;

import de.emir.service.codecs.nmea0183.encoding.sentence.AISSentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.encryption.AISPayloadEncryption;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.EncodedAISPayload;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.AISPayloadInfo;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.DecodedAISPayload;

public class AISSentenceHandler {

	/**
	 * payloadDecoder is used to decode a payloadMessage.
	 * */
	private final AISPayloadEncryption payloadDecoder;
	private DecodedAISPayload decodedAISMessage = null;

	/**
	 * This attribute will gather the fragments of the fragmented payloadMessage
	 * in order to handle it as one complete message.
	 * */
	private final ArrayList<AISSentence> payloadMessageFragments = new ArrayList<AISSentence>();

	public AISSentenceHandler() {
		this.payloadDecoder = new AISPayloadEncryption();
		this.resetDecodedAISMessage();
	}

	/**
	 * Handle a single AISSentence in order to extract the decoded
	 * payloadMessage
	 * 
	 * @param aisSentence
	 */
	public void handleAISSentence(AISSentence aisSentence) {
		// Avoid handling invalid messages:
		if (aisSentence.getFragmentsCount() == null
				|| aisSentence.getFragmentId() == null
				|| aisSentence.getMessage() == null
				|| aisSentence.getFillBits() == null)
			return;
		int numberOfFragments = aisSentence.getFragmentsCount();
		if (numberOfFragments <= 0) {
//			System.err.println("Invalid AIS PayloadMessage: negative numberOfFragments!");
			payloadMessageFragments.clear();
		} else if (numberOfFragments == 1) {
			String payload = aisSentence.getMessage();
			int fillBits = aisSentence.getFillBits();
			EncodedAISPayload encodedAISMessage = new EncodedAISPayload(aisSentence.
					getNmeaString(), payload, fillBits);
			// this.decodedAISMessage = this.payloadDecoder.decode(encodedAISMessage);
			// aisSentence.setDecodedPayload(this.decodedAISMessage);
			aisSentence = this.payloadDecoder.decode(encodedAISMessage);
			this.decodedAISMessage = this.payloadDecoder.decode(encodedAISMessage);
			aisSentence.setDecodedPayload(new AISPayloadInfo(this.decodedAISMessage));
			payloadMessageFragments.clear();
		} else {
			int fragmentNumber = aisSentence.getFragmentId();
			if (fragmentNumber < 0) {
//				System.err.println("Invalid AIS PayloadMessage: negative fragmentNumber!");
				payloadMessageFragments.clear();
			} else if (fragmentNumber > numberOfFragments) {
//				System.err.println(
//						"Invalid AIS PayloadMessage: fragmentNumber > numberOfFragments !!");
				payloadMessageFragments.clear();
			}
			// Handle fragmented payloadMessage
			else {
				if (fragmentNumber == 1) {
					payloadMessageFragments.clear();
					payloadMessageFragments.add(aisSentence);
				} else {
					int nextFragmentNumber = payloadMessageFragments.size() + 1;
					if (nextFragmentNumber != fragmentNumber) {
//						System.err
//								.println("Invalid AIS PayloadMessage: unexpected fragmentNumber!");
//						System.err.println("fragmentNumber: " + fragmentNumber);
//						System.err.println("nextFragmentNumber: " + nextFragmentNumber);
						payloadMessageFragments.clear();
					} else {// System.out.println("Fragmented: " + aisSentence.getNmeaString());
							// gather the payloadMessage fragments
						payloadMessageFragments.add(aisSentence);
						// handle only when all the fragments are already gathered
						if (aisSentence.getFragmentsCount() == payloadMessageFragments
								.size() && payloadMessageFragments.get(0).getMessageId().intValue() 
								== aisSentence.getMessageId().intValue()) {
							int fillBits = -1;
							StringBuffer payload = new StringBuffer();
							Iterator<AISSentence> iterator = payloadMessageFragments.iterator();
							while (iterator.hasNext()) {
								AISSentence m = iterator.next();
								payload.append(m.getMessage());
								if (!iterator.hasNext()) {
									fillBits = m.getFillBits();
								}
							}
							EncodedAISPayload encodedAISMessage = new EncodedAISPayload(
									aisSentence.getNmeaString(),payload.toString(), fillBits);
							// this.decodedAISMessage = payloadDecoder.decode(encodedAISMessage);
							// aisSentence.setDecodedPayload(this.decodedAISMessage);
							aisSentence = this.payloadDecoder.decode(encodedAISMessage);
							this.decodedAISMessage = this.payloadDecoder.decode(encodedAISMessage);
							aisSentence.setDecodedPayload(new AISPayloadInfo(
									this.decodedAISMessage));
							this.decodedAISMessage.setFragmentsCount(payloadMessageFragments.size());
							payloadMessageFragments.clear();
						}
					}
				}
			}
		}
	}

	/**
	 * Get the UnhandledSentences/fragments.
	 */
	public ArrayList<AISSentence> getUnhandledSentences() {
		@SuppressWarnings("unchecked")
		ArrayList<AISSentence> unhandledSentences = (ArrayList<AISSentence>) payloadMessageFragments
				.clone();
		// payloadMessageFragments.clear();
		return unhandledSentences;
	}

	public DecodedAISPayload getDecodedAISMessage() {
		return decodedAISMessage;
	}

	public void resetDecodedAISMessage() {
		this.decodedAISMessage = null;
	}
}
