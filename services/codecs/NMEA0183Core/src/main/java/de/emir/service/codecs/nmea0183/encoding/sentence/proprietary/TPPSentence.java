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
 * TPP - Target Position Plausibility<br>
 * <br>
 * 
 * <pre>
 * {@code
 * .     1         2 3   4   5 6 7
 *       |         | |   |   | | |
 * $-TPP,hhmmss.ss,x,x.x,x.x,x,A*hh
 * }
 * </pre>
 * <ol>
 * <li>Timestamp (UTC)</li>
 * <li>Maritime Mobile Service Identity</li>
 * <li>Latitude in degrees (WGS84)</li>
 * <li>Longitude in degrees (WGS84)</li>
 * <li>Horizontal error in meters</li>
 * <li>Status, A means data is valid</li>
 * <li>Checksum</li>
 * </ol>
 * 
 * @author 
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class TPPSentence extends Sentence {

	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "P";
	/** Sentence id. */
	public static final String SENTENCE_ID = "TPP";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 7;
	
	/** The timestamp (UTC) of the position. */
	private Time time;
	/** The Maritime Mobile Service Identity this message belongs to. */
	private Integer mmsi;
	/** The longitude position value in degrees (WGS84). */
	private Double longitude;
	/** The latitude value in degrees (WGS84). */
	private Double latitude;
	/** The horizontal error of the position in meters. */
	private Double hError;
	/** Status, A means data is valid. */
	@Enumerated(EnumType.STRING)
	private Status valid;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public TPPSentence(){
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}
	
	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public TPPSentence(String nmea) {
		super(nmea);
	}
	
	@Override
	protected void decode() {
		int index = 0;
		time = ParseUtils.parseTime(getValue(index++));
		mmsi = ParseUtils.parseInteger(getValue(index++));
		longitude = ParseUtils.parseDouble(getValue(index++));
		latitude = ParseUtils.parseDouble(getValue(index++));
		hError = ParseUtils.parseDouble(getValue(index++));
		valid = ParseUtils.parseStatus(getValue(index++));
	}
	
	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(time));
		setValue(index++, ParseUtils.toString(mmsi));
		setValue(index++, ParseUtils.toString(latitude));
		setValue(index++, ParseUtils.toString(longitude));
		setValue(index++, ParseUtils.toString(hError, 1));
		setValue(index++, ParseUtils.toString(valid));
	}
	
	@Override
	protected void fillMap(Map<String, Object> res) {
		if (time != null) res.put("time", time);
		if (mmsi != null) res.put("mmsi", mmsi);
		if (latitude != null) res.put("latitude", latitude);
		if (longitude != null) res.put("longitude", longitude);
		if (hError != null) res.put("hError", hError);
		if (valid != Status.NULL) res.put("status", valid.name());
	}
	
	public Integer getMmsi() {
		return mmsi;
	}
	public void setMmsi(Integer mmsi) {
		this.mmsi = mmsi;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double gethError() {
		return hError;
	}
	public void sethError(Double hError) {
		this.hError = hError;
	}
	public Status getValid() {
		return valid;
	}
	public void setValid(Status valid) {
		this.valid = valid;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

}
