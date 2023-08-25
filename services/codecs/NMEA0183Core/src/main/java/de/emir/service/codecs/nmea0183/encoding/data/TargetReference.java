package de.emir.service.codecs.nmea0183.encoding.data;

/**
 * Enumeration type for target reference. Also provides method to parse and representation
 * in nmea.
 * 
 */
public enum TargetReference {
	/** Reference target designated. */
	REFERENCE_TARGET("R"),
	/** Not designated. */
	NOT_DESIGNATED("");

	/** Stores the nmea representation. */
	private final String shortName;

	/**
	 * Constructor.
	 * 
	 * @param shortName
	 *            Nmea representation.
	 */
	TargetReference(String shortName) {
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
	public static TargetReference parse(String s) {
		for (TargetReference r : TargetReference.class.getEnumConstants()) {
			if (r.shortName.equals(s)) {
				return r;
			}
		}
		return NOT_DESIGNATED;
	}
}
