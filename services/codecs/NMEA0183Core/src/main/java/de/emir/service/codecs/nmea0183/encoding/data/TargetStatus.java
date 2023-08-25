package de.emir.service.codecs.nmea0183.encoding.data;

/**
 * Enumeration type for target status. Also provides method to parse and representation
 * in nmea.
 * 
 */
public enum TargetStatus {
	/** Target tracking. */
	TRACKING_TARGET("T"),
	/** Lost target. */
	LOST_TARGET("L"),
	/** Target being acquired. */
	ACQUIRED("Q"),
	/** Invalid or not set. */
	NULL("");

	/** Stores the nmea representation. */
	private final String shortName;

	/**
	 * Constructor.
	 * 
	 * @param shortName
	 *            Nmea representation.
	 */
	TargetStatus(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * Gets the nmea representation of this enum.
	 * 
	 * @return Nmea String.
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * Parses the given String and returns the enum.
	 * 
	 * @param s
	 *            Nmea String.
	 * @return Enum for the given String.
	 */
	public static TargetStatus parse(String s) {
		for (TargetStatus r : TargetStatus.class.getEnumConstants()) {
			if (r.shortName.equals(s)) {
				return r;
			}
		}
		return NULL;
	}
}
