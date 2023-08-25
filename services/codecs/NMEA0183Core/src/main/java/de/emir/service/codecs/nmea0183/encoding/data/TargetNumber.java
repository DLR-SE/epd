package de.emir.service.codecs.nmea0183.encoding.data;

import javax.persistence.Embeddable;

/**
 * Symbolizes a two digit target number for TTA or TLL sentences. Internal
 * representation is handled as decimal number. Provides methods for parsing or
 * converting to nmea.
 * 
 */
@Embeddable
public class TargetNumber {
	/** The target number. */
	private int number;

	/**
	 * Empty default constructor needed for persistence.
	 * 
	 */
	public TargetNumber() {
	}
	
	/**
	 * Constructs a target number with the given number.
	 * 
	 * @param number
	 *            The target number.
	 */
	public TargetNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return String.format("%02d", number);
	}

	/**
	 * Gets the number.
	 * 
	 * @return Number as byte
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * Sets the number.
	 * 
	 * @param number
	 *            Number as byte
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * Parses a String and returns a TargetNumber object.
	 * 
	 * @param value
	 *            String value from nmea
	 * @return {@link TargetNumber}.
	 */
	public static TargetNumber parse(String value) {
		try {
			int res = Integer.parseInt(value);
			return new TargetNumber(res);
		} catch (Exception e) {
			return null;
		}
	}
}
