package de.emir.service.codecs.nmea0183.encoding.data;

/**
 * Enumeration type for Hemisphere. Also provides method to parse and
 * representation in nmea.
 * 
 */
public enum Hemisphere {
	NORTH("N", 1), EAST("E", 1), SOUTH("S", -1), WEST("W", -1), NULL("", 0);

	/** Stores the nmea representation. */
	private final String shortName;
	/** Stores the sign of decimal value. */
	private final int sign;

	/**
	 * Constructor.
	 * 
	 * @param shortName
	 *            Nmea representation.
	 * @param sign
	 *            Sign of decimal value.
	 */
	Hemisphere(String shortName, int sign) {
		this.shortName = shortName;
		this.sign = sign;
	}
	
	/**
	 * Gets the nmea representation of this enum.
	 * @return
	 * Nmea String.
	 */
	public String getShortName() {
		return this.shortName;
	}

	/**
	 * Gets the sign of decimal value.
	 * @return
	 * Sign of decimal value.
	 */
	public int getSign() {
		return sign;
	}

	/**
	 * Parses the given String and returns the enum.
	 * @param s
	 * Nmea String.
	 * @return
	 * Enum for the given String.
	 */
	public static Hemisphere parse(String s) {
		for (Hemisphere r : Hemisphere.class.getEnumConstants()) {
			if (r.shortName.equals(s)) {
				return r;
			}
		}
		return NULL;
	}
}
