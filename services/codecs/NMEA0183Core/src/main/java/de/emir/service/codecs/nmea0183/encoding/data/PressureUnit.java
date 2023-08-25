package de.emir.service.codecs.nmea0183.encoding.data;

/**
 * Enumeration type for pressure units. It provides a parsing method for NMEA string. 
 * 
 * @author msalous
 * 
 */
public enum PressureUnit {
	/** Inches of mercury. */
	INCHE("I"),
	/** Bar. */
	BAR("B"),
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
	PressureUnit(String shortName) {
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
	public static PressureUnit parse(String s) {
		for (PressureUnit r : PressureUnit.class.getEnumConstants()) {
			if (r.shortName.equals(s)) {
				return r;
			}
		}
		return NULL;
	}
}
