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
 * TVP - Target Velocity Plausibility<br>
 * <br>
 * 
 * <pre>
 * {@code
 * .     1         2 3   4   5   6   7 8
 *       |         | |   |   |   |   | |
 * $-TVP,hhmmss.ss,x,x.x,x.x,x.x,x.x,A*hh
 * }
 * </pre>
 * <ol>
 * <li>Timestamp (UTC)</li>
 * <li>Maritime Mobile Service Identity</li>
 * <li>Speed Over Ground in knots</li>
 * <li>Error of the Speed Over Ground in knots</li>
 * <li>Course Over Ground in degrees from geographical north</li>
 * <li>Error of the Course Over Ground in degrees</li>
 * <li>Status, A means data is valid</li>
 * <li>Checksum</li>
 * </ol>
 * 
 * @author 
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class TVPSentence extends Sentence {
	
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "P";
	/** Sentence id. */
	public static final String SENTENCE_ID = "TVP";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 8;
	
	/** The timestamp (UTC) of the velocity. */
	private Time time;
	/** The Maritime Mobile Service Identity this message belongs to. */
	private Integer mmsi;
	/** Speed over Ground in knots */
	private Double sog;
	/** The error of the Speed Over Ground in knots. */
	private Double sError;
	/** The Course Over Ground in degrees from geographical north. */
	private Double cog;
	/** The error of the Course Over Ground in degrees. */
	private Double cError;
	/** Status, A means data is valid. */
	@Enumerated(EnumType.STRING)
	private Status valid;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public TVPSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}
	
	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public TVPSentence(String nmea) {
		super(nmea);
	}
	
	@Override
	protected void decode() {
		int index = 0;
		time = ParseUtils.parseTime(getValue(index++));
		mmsi = ParseUtils.parseInteger(getValue(index++));
		sog = ParseUtils.parseDouble(getValue(index++));
		sError = ParseUtils.parseDouble(getValue(index++));
		cog = ParseUtils.parseDouble(getValue(index++));
		cError = ParseUtils.parseDouble(getValue(index++));
		valid = ParseUtils.parseStatus(getValue(index++));
	}
	
	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(time));
		setValue(index++, ParseUtils.toString(mmsi));
		setValue(index++, ParseUtils.toString(sog, 1));
		setValue(index++, ParseUtils.toString(sError, 1));
		setValue(index++, ParseUtils.toString(cog, 1));
		setValue(index++, ParseUtils.toString(cError, 1));
		setValue(index++, ParseUtils.toString(valid));
	}
	
	@Override
	protected void fillMap(Map<String, Object> res) {
		if (time != null) res.put("time", time);
		if (mmsi != null) res.put("mmsi", mmsi);
		if (sog != null) res.put("sog", sog);
		if (sError != null) res.put("sError", sError);
		if (cog != null) res.put("cog", cog);
		if (cError != null) res.put("cError", cError);
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
	 * @return the mmsi
	 */
	public Integer getMmsi() {
		return mmsi;
	}

	/**
	 * @param mmsi the mmsi to set
	 */
	public void setMmsi(Integer mmsi) {
		this.mmsi = mmsi;
	}

	/**
	 * @return the sog
	 */
	public Double getSog() {
		return sog;
	}

	/**
	 * @param sog the sog to set
	 */
	public void setSog(Double sog) {
		this.sog = sog;
	}

	/**
	 * @return the sError
	 */
	public Double getsError() {
		return sError;
	}

	/**
	 * @param sError the sError to set
	 */
	public void setsError(Double sError) {
		this.sError = sError;
	}

	/**
	 * @return the cog
	 */
	public Double getCog() {
		return cog;
	}

	/**
	 * @param cog the cog to set
	 */
	public void setCog(Double cog) {
		this.cog = cog;
	}

	/**
	 * @return the cError
	 */
	public Double getcError() {
		return cError;
	}

	/**
	 * @param cError the cError to set
	 */
	public void setcError(Double cError) {
		this.cError = cError;
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
