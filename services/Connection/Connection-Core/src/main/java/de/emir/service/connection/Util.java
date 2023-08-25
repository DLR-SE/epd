package de.emir.service.connection;

/**
 * Utility class for connection related methods and String manipulation.
 * 
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 * 
 */
public final class Util {

	public static String normalize(String prefix, String in, String suffix) {
		return prefix + in.trim() + suffix;
	}
}
