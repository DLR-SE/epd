package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types;

public enum ReportingInterval {
	Autonomous (0),
	TenMinutes(1),
	SixMinutes(2),
	ThreeMinutes(3),
	OneMinute(4),
	ThirtySeconds(5),
	FifteenSeconds(6),
	TenSeconds(7),
	FiveSeconds(8),
	NextShortReportingInterval(9),
	NextLongerReportingInterval(10),
	FutureUse1(11),
	FutureUse2(12),
	FutureUse3(13),
	FutureUse4(14),
	FutureUse5(15);
	
	private final Integer interval;

	ReportingInterval(Integer interval) {
		this.interval = interval;
	}
	
	public Integer getInterval() {
		return interval;
	}

	public String getValue() {
	    return toString();
	}

	public static ReportingInterval fromInteger(Integer integer) {
		if (integer != null) {
			for (ReportingInterval b : ReportingInterval.values()) {
				if (integer.equals(b.interval)) {
					return b;
				}
			}
		}
		return null;
	}
}
