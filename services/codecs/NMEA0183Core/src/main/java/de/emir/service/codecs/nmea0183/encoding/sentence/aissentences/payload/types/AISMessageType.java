package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types;

public enum AISMessageType {

	PositionReportClassA(1),
	PositionReportClassAAssignedSchedule(2),
	PositionReportClassAResponseToInterrogation(3), 
	BaseStationReport(4), 
	StaticAndVoyageRelatedData(5), 
	BinaryAddressedMessage(6), 
	BinaryAcknowledge(7), 
	BinaryBroadcastMessage(8), 
	StandardSARAircraftPositionReport(9),
	UTCAndDateInquiry(10),
	UTCAndDateResponse(11),
	AddressedSafetyRelatedMessage(12),
	SafetyRelatedAcknowledge(13),
	SafetyRelatedBroadcastMessage(14),
	Interrogation(15), 
	AssignedModeCommand(16), 
	DGNSSBinaryBroadcastMessage(17), 
	StandardClassBCSPositionReport(18), 
	ExtendedClassBEquipmentPositionReport(19), 
	DataLinkManagement(20), 
	AidToNavigationReport(21), 
	ChannelManagement(22), 
	GroupAssignmentCommand(23), 
	StaticDataReport(24), 
	SingleSlotBinaryMessage(25), 
	MultipleSlotBinaryMessageWithCommunicationState(26),
	PositionReportForLongRangeApplications(27);

	private final Integer messageType;
	
	AISMessageType(Integer type) {
		this.messageType = type;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public String getValue() {
	    return toString();
	}

	public static AISMessageType fromInteger(Integer integer) {
		if (integer != null) {
			for (AISMessageType type : AISMessageType.values()) {
				if (integer.equals(type.messageType)) {
					return type;
				}
			}
		}
		return null;
	}
}
