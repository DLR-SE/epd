package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types;

public enum TxRxMode {
	TxABRxAB (0),
	TxARxAB(1),
	TxBRxAB(2),
	FutureUse(3);
	
	private final Integer mode;

	TxRxMode(Integer mode) {
		this.mode = mode;
	}
	
	public Integer getMode() {
		return mode;
	}

	public String getValue() {
	    return toString();
	}

	public static TxRxMode fromInteger(Integer integer) {
		if (integer != null) {
			for (TxRxMode b : TxRxMode.values()) {
				if (integer.equals(b.mode)) {
					return b;
				}
			}
		}
		return null;
	}
}
