package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.encryption;


import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.EncodedAISPayload;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.DecodedAISPayload;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.exceptions.InvalidEncodedPayload;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.exceptions.UnsupportedPayloadType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;

public class AISPayloadEncryption {

	public AISPayloadEncryption() {
	}
	

	public DecodedAISPayload decode(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		
		DecodedAISPayload decodedMessage;
		AISMessageType messageType = encodedMessage.getMessageType();
		
		switch(messageType) {
		case PositionReportClassA:
			decodedMessage = AISPayloadEncryptionUtil.decodePositionReportClassA(encodedMessage);
			break;
		case PositionReportClassAAssignedSchedule:
			decodedMessage = AISPayloadEncryptionUtil.decodePositionReportClassAAssignedSchedule(encodedMessage);
			break;
		case PositionReportClassAResponseToInterrogation:
			decodedMessage = AISPayloadEncryptionUtil.decodePositionReportClassAResponseToInterrogation(encodedMessage);
			break;
		case BaseStationReport:
			decodedMessage = AISPayloadEncryptionUtil.decodeBaseStationReport(encodedMessage);
			break;
		case StaticAndVoyageRelatedData:
			decodedMessage = AISPayloadEncryptionUtil.decodeStaticAndVoyageData(encodedMessage);
			break;
		case BinaryAddressedMessage: 
			decodedMessage = AISPayloadEncryptionUtil.decodeBinaryAddressedMessage(encodedMessage);
			break;
		case BinaryAcknowledge:
		case SafetyRelatedAcknowledge:
			decodedMessage = AISPayloadEncryptionUtil.decodeBinaryAcknowledge(encodedMessage);
			break;
		case BinaryBroadcastMessage:
			decodedMessage = AISPayloadEncryptionUtil.decodeBinaryBroadcastMessage(encodedMessage);
			break;
		case StandardSARAircraftPositionReport:
			decodedMessage = AISPayloadEncryptionUtil.decodeStandardSARAircraftPositionReport(encodedMessage);
			break;
		case UTCAndDateInquiry:
			decodedMessage = AISPayloadEncryptionUtil.decodeUTCAndDateInquiry(encodedMessage);
			break;
		case UTCAndDateResponse:
			decodedMessage = AISPayloadEncryptionUtil.decodeUTCAndDateResponse(encodedMessage);
			break;
		case AddressedSafetyRelatedMessage:
			decodedMessage = AISPayloadEncryptionUtil.decodeAddressedSafetyRelatedMessage(encodedMessage);
			break;
		case SafetyRelatedBroadcastMessage:
			decodedMessage = AISPayloadEncryptionUtil.decodeSafetyRelatedBroadcastMessage(encodedMessage);
			break;
		case Interrogation:
			decodedMessage = AISPayloadEncryptionUtil.decodeInterrogation(encodedMessage);
			break;
		case AssignedModeCommand:
			decodedMessage = AISPayloadEncryptionUtil.decodeAssignedModeCommand(encodedMessage);
			break;
		case DGNSSBinaryBroadcastMessage:
			decodedMessage = AISPayloadEncryptionUtil.decodeDGNSSBinaryBroadcastMessage(encodedMessage);
			break;
		case StandardClassBCSPositionReport:
			decodedMessage = AISPayloadEncryptionUtil.decodeStandardClassBCSPositionReport(encodedMessage);
			break;
		case ExtendedClassBEquipmentPositionReport:
			decodedMessage = AISPayloadEncryptionUtil.decodeExtendedClassBEquipmentPositionReport(encodedMessage);
			break;
		case DataLinkManagement:
			decodedMessage = AISPayloadEncryptionUtil.decodeDataLinkManagement(encodedMessage);
			break;
		case AidToNavigationReport:
			decodedMessage = AISPayloadEncryptionUtil.decodeAidToNavigationReport(encodedMessage);
			break;
		case ChannelManagement:
			decodedMessage = AISPayloadEncryptionUtil.decodeChannelManagement(encodedMessage);
			break;
		case GroupAssignmentCommand:
			decodedMessage = AISPayloadEncryptionUtil.decodeGroupAssignmentCommand(encodedMessage);
			break;
		case StaticDataReport:
			decodedMessage = AISPayloadEncryptionUtil.decodeStaticDataReport(encodedMessage);
			break;
		case SingleSlotBinaryMessage:
			decodedMessage = AISPayloadEncryptionUtil.decodeSingleSlotBinaryMessage(encodedMessage);
			break;
		case MultipleSlotBinaryMessageWithCommunicationState:
			decodedMessage = AISPayloadEncryptionUtil.decodeMultipleSlotBinaryMessageWithCommunicationState(encodedMessage);
			break;
		case PositionReportForLongRangeApplications:
			decodedMessage = AISPayloadEncryptionUtil.decodeLongRangeAISBroadcastMessage(encodedMessage);
			break;
		default:
			throw new UnsupportedPayloadType(messageType.getMessageType());
		}
		
		return decodedMessage;
	}
	
	/***
	 * Encode a decodedPayload. 
	 * @return encodedPayload regarding the AIVDM/AIVDO NMEA Sentence. 
	 */
	public EncodedAISPayload encode(DecodedAISPayload decodedPayload){
		return null;
	}
}



