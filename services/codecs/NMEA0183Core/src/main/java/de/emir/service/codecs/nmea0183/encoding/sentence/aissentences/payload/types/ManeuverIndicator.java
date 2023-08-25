package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types;

public enum ManeuverIndicator {
	NotAvailable(0),
	NoSpecialManeuver(1),
	SpecialManeuver(2);

	private final Integer indicator;

	ManeuverIndicator(Integer indicator) {
		this.indicator = indicator;
	}
	
	public Integer getIndicator() {
		return indicator;
	}

	public String getValue() {
	    return toString();
	}

	public static ManeuverIndicator fromInteger(Integer integer) {
		if (integer != null) {
			for (ManeuverIndicator b : ManeuverIndicator.values()) {
				if (integer.equals(b.indicator)) {
					return b;
				}
			}
		}
		return null;
	}
}
