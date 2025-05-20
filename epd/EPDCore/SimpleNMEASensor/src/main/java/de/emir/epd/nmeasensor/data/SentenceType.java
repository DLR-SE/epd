package de.emir.epd.nmeasensor.data;

import net.sf.marineapi.nmea.sentence.SentenceId;

/**
 * Supported sentence types of the NMEA0183 sensor to select in UI
 */
public enum SentenceType {
	// Can be extended by introducing new enums with existing sentences from the NMEA0183 codec library
	PositionReportClassA(SentenceId.VDM, 1),
	PositionReportClassAAssignedSchedule(SentenceId.VDM, 2),
	PositionReportClassAResponseToInterrogation(SentenceId.VDM, 3),
	PositionReportForLongRangeApplications(SentenceId.VDM, 27),
	StaticAndVoyageData(SentenceId.VDM, 5),
	BaseStationReport(SentenceId.VDM, 4),
//	BinaryBroadcastMessage(BinaryBroadcastMessage.class),
	StandardSARAircraftPositionReport(SentenceId.VDM, 9),
	StandardClassBCSPositionReport(SentenceId.VDM, 18),
	StaticDataReport(SentenceId.VDO, 24),
	DPTSentence(SentenceId.DPT, -1),
	GGASentence(SentenceId.GGA, -1),
	GLLSentence(SentenceId.GLL, -1),
	GSVSentence(SentenceId.GSV, -1),
	HDTSentence(SentenceId.HDT, -1),
	HDGSentence(SentenceId.HDG, -1),
	RMCSentence(SentenceId.RMC, -1),
	ROTSentence(SentenceId.ROT, -1),
	VTGSentence(SentenceId.VTG, -1),
	TTMSentence(SentenceId.TTM, -1),
	TLLSentence(SentenceId.TLL, -1),
	ZDASentence(SentenceId.ZDA, -1);
//	VDOPositionReportClassA(SentenceId.VDO, 1),
//	VDOPositionReportClassAAssignedSchedule(SentenceId.VDO, 2),
//	VDOPositionReportClassAResponseToInterrogation(SentenceId.VDO, 3),
//	VDOStaticAndVoyageData(SentenceId.VDO, 5),
//	VDOStandardClassBCSPositionReport(SentenceId.VDO, 18),
//	VDOStaticDataReport(SentenceId.VDO, 24);

	private final SentenceId type;
	private final int detail;

	SentenceType(SentenceId type, int detail) {
		this.type = type;
		this.detail = detail;
	}

	public SentenceId getType() {
		return this.type;
	}
	
	public int getDetail() {
		return this.detail;
	}
}
