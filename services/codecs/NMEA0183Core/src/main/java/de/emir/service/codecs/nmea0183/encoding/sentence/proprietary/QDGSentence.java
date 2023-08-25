package de.emir.service.codecs.nmea0183.encoding.sentence.proprietary;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.data.Status;
import de.emir.service.codecs.nmea0183.encoding.data.Time;
import de.emir.service.codecs.nmea0183.encoding.sentence.Sentence;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * QDG - Own ship Heading<br>
 * <br>
 * 
 * <pre>
 * {@code
 * .     1         2   3   4   5   6 7
 *       |         |   |   |   |   | |
 * $-QDG,hhmmss.ss,x.x,x.x,x.x,x.x,A*hh
 * }
 * </pre>
 * <ol>
 * <li>Timestamp (UTC)</li>
 * <li>Heading in degrees from geographical north. </li>
 * <li>Error of the heading in degrees. </li>
 * <li>Rotation in degrees per minute. </li>
 * <li>Error of the rotation in degrees per minute. </li>
 * <li>Status, A means data is valid</li>
 * <li>Checksum</li>
 * </ol>
 * 
 * @author 
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class QDGSentence extends Sentence{
	
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "P";
	/** Sentence id. */
	public static final String SENTENCE_ID = "QDG";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 7;
	
	/** The timestamp (UTC) of the heading. */
	private Time time;
	/** The heading in degrees from geographical north. */
	private Double heading;
	/** The error of the heading in degrees. */
	private Double hError;
	/** The rotation in degrees per minute. */
	private Double rot;
	/** The error of the rotation in degrees per minute. */
	private Double rError;
	/** Status, A means data is valid. */
	@Enumerated(EnumType.STRING)
	private Status valid;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public QDGSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}
	
	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public QDGSentence(String nmea) {
		super(nmea);
	}
	
	@Override
	protected void decode() {
		int index = 0;
		time = ParseUtils.parseTime(getValue(index++));
		heading = ParseUtils.parseDouble(getValue(index++));
		hError = ParseUtils.parseDouble(getValue(index++));
		rot = ParseUtils.parseDouble(getValue(index++));
		rError = ParseUtils.parseDouble(getValue(index++));
		valid = ParseUtils.parseStatus(getValue(index++));
	}
	
	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(time));
		setValue(index++, ParseUtils.toString(heading, 1));
		setValue(index++, ParseUtils.toString(hError, 1));
		setValue(index++, ParseUtils.toString(rot, 1));
		setValue(index++, ParseUtils.toString(rError, 1));
		setValue(index++, ParseUtils.toString(valid));
	}
	
	@Override
	protected void fillMap(Map<String, Object> res) {
		if (time != null) res.put("time", time);
		if (heading != null) res.put("heading", heading);
		if (hError != null) res.put("hError", hError);
		if (rot != null) res.put("rot", rot);
		if (rError != null) res.put("rError", rError);
		if (valid != Status.NULL) res.put("valid", valid);
 	}
	
	/**
	 * @return the time
	 */
	public Time getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(Time time) {
		this.time = time;
	}
	/**
	 * @return the heading
	 */
	public Double getHeading() {
		return heading;
	}
	/**
	 * @param heading the heading to set
	 */
	public void setHeading(Double heading) {
		this.heading = heading;
	}
	/**
	 * @return the hError
	 */
	public Double gethError() {
		return hError;
	}
	/**
	 * @param hError the hError to set
	 */
	public void sethError(Double hError) {
		this.hError = hError;
	}
	/**
	 * @return the rot
	 */
	public Double getRot() {
		return rot;
	}
	/**
	 * @param rot the rot to set
	 */
	public void setRot(Double rot) {
		this.rot = rot;
	}
	/**
	 * @return the rError
	 */
	public Double getrError() {
		return rError;
	}
	/**
	 * @param rError the rError to set
	 */
	public void setrError(Double rError) {
		this.rError = rError;
	}
	/**
	 * @return the valid
	 */
	public Status getValid() {
		return valid;
	}
	/**
	 * @param valid the valid to set
	 */
	public void setValid(Status valid) {
		this.valid = valid;
	}

}
