package de.emir.service.codecs.nmea0183.encoding.data;

/**
 * Enumeration type for mode indicator. Also provides method to parse and representation
 * in nmea.
 * 
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 * 
 */
public enum ModeIndicator {
    /** Autonomous mode. */
    AUTONMOUS("A"), 
    /** Differential mode. */
    DIFFERENTIAL("D"),
    /** Estimated (dead reckoning) mode. */
    ESTIMATED("E"),
    /** Manual input mode. */
    MANUAL("M"),
    /** Simulator mode. */
    SIMULATOR("S"),
    /** Data not valid. */
    NOTVALID("N"),
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
	ModeIndicator(String shortName) {
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
	public static ModeIndicator parse(String s) {
		for (ModeIndicator r : ModeIndicator.class.getEnumConstants()) {
			if (r.shortName.equals(s)) {
				return r;
			}
		}
		return NULL;
	}
}
