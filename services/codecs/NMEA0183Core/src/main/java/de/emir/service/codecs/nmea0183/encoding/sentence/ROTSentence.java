package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.data.Status;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * ROT - Rate Of Turn<br>
 * <br>
 * 
 * <pre>
 * {@code
 * .      1   2 3
 *        |   | |
 * $--ROT,x.x,A*hh
 * }
 * </pre>
 * <ol>
 * <li>Rate Of Turn, degrees per minute, "-" means bow turns to port </li>
 * <li>Status, A means data is valid</li>
 * <li>Checksum</li>
 * </ol>
 * 
 * @author msalous
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ROTSentence extends Sentence{
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "GP";
	/** Sentence id. */
	public static final String SENTENCE_ID = "ROT";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 2;
	
	/** Rate Of Turn, degrees per minute, "-" means bow turns to port. */
	private Double rot;
	/** Status A - Data Valid, V - Data Invalid */
	@Enumerated(EnumType.STRING)
	private Status status;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public ROTSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public ROTSentence(String nmea) {
		super(nmea);
	}
	@Override
	protected void decode() {
		int index = 0;
		rot = ParseUtils.parseDouble(getValue(index++));
		status = ParseUtils.parseStatus(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(rot, 1));
		setValue(index++, ParseUtils.toString(status));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (rot != null) res.put("rot", rot);
		if (status != Status.NULL) res.put("status", status.name());
	}

	public Double getRot(){
		return rot;
	}
	
	public void setRot(Double rot){
		this.rot = rot;
	}
	
	public Status getStatus(){
		return status;
	}
	
	public void setStatus(Status status){
		this.status = status;
	}
}
