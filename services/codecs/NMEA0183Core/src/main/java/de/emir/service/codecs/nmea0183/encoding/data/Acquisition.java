package de.emir.service.codecs.nmea0183.encoding.data;

/**
 * Enumeration type for acquisition. Also provides method to parse and representation
 * in nmea.
 * 
 */
public enum Acquisition {
	/** Automatic acquisition. */
	AUTOMATIC("A"),
	/** Manual acquisition. */
	MANUAL("M"),
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
	Acquisition(String shortName) {
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
	public static Acquisition parse(String s) {
		for (Acquisition r : Acquisition.class.getEnumConstants()) {
			if (r.shortName.equals(s)) {
				return r;
			}
		}
		return NULL;
	}
}
