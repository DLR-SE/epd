package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**GSV - Satellites in view
1 2 3 4 5 6 7     n
| | | | | | |     |
$--GSV,x,x,x,x,x,x,x,...*hh<CR><LF>

Field Number: 
1) total number of messages
2) message number
3) satellites in view
4) satellite number
5) elevation in degrees
6) azimuth in degrees to true
7) SNR in dB
more satellite infos like 4)-7)
n) checksum

 @author sschweigert
*/
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class GSVSentence extends Sentence {
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "GP";
	/** Sentence id. */
	public static final String SENTENCE_ID = "GSV";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 19;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public GSVSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public GSVSentence(String nmea) {
		super(nmea);
	}

	@Override
	protected void decode() {
		// TODO
	}

	@Override
	protected void encode() {
		// TODO
	}

	@Override
	protected void fillMap(Map<String, Object> result) {
		// TODO
	}
}
