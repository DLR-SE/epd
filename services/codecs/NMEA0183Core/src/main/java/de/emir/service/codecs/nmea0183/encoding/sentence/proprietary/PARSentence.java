package de.emir.service.codecs.nmea0183.encoding.sentence.proprietary;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.data.Status;
import de.emir.service.codecs.nmea0183.encoding.sentence.Sentence;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * PAR - Pitch and Roll<br>
 * Introduced by the MTCAS Project (https://www.offis.de/offis/projekt/mtcas.html) 
 * <br>
 * 
 * <pre>
 * {@code
 * .      1   2  3 4
 *        |   |  | |
 * $-PAR,x.x,x.x,A*hh
 * }
 * </pre>
 * <ol>
 * <li>Pitch, degrees per minute, "-" means bow turns to port </li>
 * <li>Roll,  degrees per minute, "-" means bow turns to port </li>
 * <li>Status, A means data is valid, V means warning, null means invalid</li>
 * <li>Checksum</li>
 * </ol>
 * 
 * @author sschweigert
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class PARSentence extends Sentence{
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "P";
	/** Sentence id. */
	public static final String SENTENCE_ID = "PAR";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 3;
	
	/** Pitch, degrees per minute, "-" means bow turns to port. */
	private Double pitch;
	/** Roll, degrees per minute, "-" means bow turns to port. */
	private Double roll;
	/** Status A - Data Valid, V - Data Invalid */
	@Enumerated(EnumType.STRING)
	private Status status;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public PARSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public PARSentence(String nmea) {
		super(nmea);
	}
	@Override
	protected void decode() {
		int index = 0;
		pitch = ParseUtils.parseDouble(getValue(index++));
		roll = ParseUtils.parseDouble(getValue(index++));
		status = ParseUtils.parseStatus(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(pitch));
		setValue(index++, ParseUtils.toString(roll));
		setValue(index++, ParseUtils.toString(status));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (pitch != null) res.put("pitch", pitch);
		if (roll != null) res.put("roll", roll);
		if (status != Status.NULL) res.put("status", status.name());
	}

	
	
	public Double getPitch() {
		return pitch;
	}

	public void setPitch(Double pitch) {
		this.pitch = pitch;
	}

	public Double getRoll() {
		return roll;
	}

	public void setRoll(Double roll) {
		this.roll = roll;
	}

	public Status getStatus(){
		return status;
	}
	
	public void setStatus(Status status){
		this.status = status;
	}
}
