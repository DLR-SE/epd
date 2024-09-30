package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types;

public enum PositionFixingDevice {
	Undefined (0),
	Gps(1),
	Glonass(2),
	CombinedGpsGlonass(3),
	LoranC(4),
	Chayka(5),
	IntegratedNavigationSystem(6),
	Surveyed(7),
	Galileo(8),
    NotInUse(9),
    InternalGNSS(15);
	
	private final Integer device;

	PositionFixingDevice(Integer device) {
		this.device = device;
	}
	
	public Integer getDevice() {
		return device;
	}

	public String getValue() {
	    return toString();
	}

	public static PositionFixingDevice fromInteger(Integer integer) {
		if (integer != null) {
            if (integer >= 9 && integer <= 14) return PositionFixingDevice.NotInUse;
			for (PositionFixingDevice b : PositionFixingDevice.values()) {
				if (integer.equals(b.device)) {
					return b;
				}
			}
		}
		return null;
	}
}
