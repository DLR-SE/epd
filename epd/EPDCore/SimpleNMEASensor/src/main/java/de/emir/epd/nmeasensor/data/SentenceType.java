package de.emir.epd.nmeasensor.data;

import de.emir.service.codecs.nmea0183.encoding.sentence.*;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.*;

/**
 * Supported sentence types of the NMEA0183 sensor to select in UI
 */
public enum SentenceType {
	// Can be extended by introducing new enums with existing sentences from the NMEA0183 codec library
	PositionReportClassA(PositionReportClassA.class),
	PositionReportClassAAssignedSchedule(PositionReportClassAAssignedSchedule.class),
	PositionReportClassAResponseToInterrogation(PositionReportClassAResponseToInterrogation.class),
	/*PositionReportForLongRangeApplications(PositionReportForLongRangeApplications.class),*/
	StaticAndVoyageData(StaticAndVoyageData.class),
	BaseStationReport(BaseStationReport.class),
	BinaryBroadcastMessage(BinaryBroadcastMessage.class),
	StandardSARAircraftPositionReport(StandardSARAircraftPositionReport.class),
	StandardClassBCSPositionReport(StandardClassBCSPositionReport.class),
	StaticDataReport(StaticDataReport.class),
	DPTSentence(DPTSentence.class),
	GGASentence(GGASentence.class),
	GLLSentence(GLLSentence.class),
	GSVSentence(GSVSentence.class),
	HDTSentence(HDTSentence.class),
	HDGSentence(HDGSentence.class),
	RMCSentence(RMCSentence.class),
	ROTSentence(ROTSentence.class),
	VTGSentence(VTGSentence.class),
	TTMSentence(TTMSentence.class),
	TLLSentence(TLLSentence.class),
	ZDASentence(ZDASentence.class);

	private final Class<?> type;

	SentenceType(Class<?> type) {
		this.type = type;
	}

	public Class<?> getType() {
		return this.type;
	}
}
