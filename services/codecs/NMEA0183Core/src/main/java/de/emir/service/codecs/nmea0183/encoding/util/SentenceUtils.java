package de.emir.service.codecs.nmea0183.encoding.util;

import java.util.regex.Pattern;

import de.emir.service.codecs.nmea0183.encoding.sentence.Sentence;

/**
 * This utility class provides some methods to validate sentences and calculate
 * checksums.
 * 
 */
public class SentenceUtils {
	/** Regular expression for a sentence with a checksum. */
	private static final Pattern regExpChecksum = Pattern
			.compile("^[$|!]{1}[A-Z0-9]{3,10}[,][\\x20-\\x7F]*[*][A-Fa-f0-9]{2}$");

	/** Regular expression for a sentence without a checksum. */
	private static final Pattern regExpNoChecksum = Pattern
			.compile("^[$|!]{1}[A-Z0-9]{3,10}[,][\\x20-\\x7F]*$");

	/**
	 * Hide utility class constructor.
	 */
	private SentenceUtils() {
	}

	/**
	 * Checks whether the given String could be a nmea sentence (Matching a
	 * pattern).
	 * 
	 * @param nmea
	 *            A String to check.
	 * @return true, if the String matches one of the nmea patterns.
	 */
	public static boolean isSentence(String nmea) {
		//It's necessary to trim the nmeaString for matching the regExp
		nmea = nmea.trim();
		if (nmea == null || "".equals(nmea)) {
			return false;
		}
		if (nmea.indexOf(Sentence.CHECKSUM_DELIMITER) < 0) {
			return regExpNoChecksum.matcher(nmea).matches();
		}
		return regExpChecksum.matcher(nmea).matches();
	}

	/**
	 * Checks if the given sentence is valid. Also calculates and checks the
	 * checksum.
	 * 
	 * @param nmea
	 *            String to be checked.
	 * @return true, if the given String is a valid nmea String.
	 */
	public static boolean validateSentence(String nmea) {
		if (SentenceUtils.isSentence(nmea)) {
			int i = nmea.indexOf(Sentence.CHECKSUM_DELIMITER);
			if (i > 0) {
				String sum = nmea.substring(++i, nmea.length());
				return sum.equalsIgnoreCase(calculateChecksum(nmea));
			} else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Calculates the checksum for a given nmea String an returns it as a 2 hex
	 * digit formated String.
	 * 
	 * @param nmea
	 *            String to calculate the checksum from.
	 * @return 2 hex digit formated String.
	 */
	public static String calculateChecksum(String nmea) {
		char ch;
		int sum = 0;
		for (int i = 0; i < nmea.length(); i++) {
			ch = nmea.charAt(i);
			if (i == 0
					&& (ch == Sentence.BEGIN_CHAR || ch == Sentence.ALTERNATIVE_BEGIN_CHAR)) {
				continue;
			} else if (ch == Sentence.CHECKSUM_DELIMITER) {
				break;
			} else if (sum == 0) {
				sum = (byte) ch;
			} else {
				sum ^= (byte) ch;
			}
		}
		return String.format("%02X", sum);
	}
	
	/**
	 * Gets the sentenceId of the given nmea String.
	 * @param nmea
	 * Valid nmea String.
	 * @return
	 * The sentence id of the nmea String.
	 */
	public static String getSentenceId(String nmea) {
		if (!isSentence(nmea)) {
			throw new IllegalArgumentException("String is not a sentence");
		}
		// System.out.println(nmea);
		if (nmea.startsWith("$P")) {
			return nmea.substring(2, nmea.indexOf(','));
		} else {
			return nmea.substring(3, nmea.indexOf(','));
		}
	}
	
	/**
	 * Gets the talkerId of the given nmea String.
	 * @param nmea
	 * Valid nmea String.
	 * @return
	 * The talker id of the nmea String.
	 */
	public static String getTalkerId(String nmea) {
		if (!isSentence(nmea)) {
			throw new IllegalArgumentException("String is not a sentence:  " + nmea);
		}

		if (nmea.startsWith("$P")) {
			return "P";
		} else {
			return nmea.substring(1, 3);
		}
	}
}
