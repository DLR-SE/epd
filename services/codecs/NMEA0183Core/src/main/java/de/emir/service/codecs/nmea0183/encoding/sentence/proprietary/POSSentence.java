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
 * POS - Own ship POSition<br>
 * <br>
 * 
 * <pre>
 * {@code
 * .     1         2   3   4   5   6 7
 *       |         |   |   |   |   | |
 * $-POS,hhmmss.ss,x.x,x.x,x.x,x.x,A*hh
 * }
 * </pre>
 * <ol>
 * <li>Timestamp (UTC)</li>
 * <li>Latitude in degrees (WGS84)</li>
 * <li>Longitude in degrees (WGS84)</li>
 * <li>Horizontal protection level in meters</li>
 * <li>Mode indicator, defines which sensor provided the data</li>
 * <li>Status, A means data is valid</li>
 * <li>Checksum</li>
 * </ol>
 * 
 * @author 
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class POSSentence extends Sentence {
	
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "P";
	/** Sentence id. */
	public static final String SENTENCE_ID = "POS";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 7;
	
	/** The timestamp (UTC) of the position. */
	private Time time;
	/** The latitude position value in degrees (WGS84). */
	private Double latitude;
	/** The longitude position value in degrees (WGS84). */
	private Double longitude;
	/** Horizontal Protection Level in meters*/
	private Double hpl;
	/** Indicates the sensor which is providing the data. */
	private Double modeIndicator;
	/** Status, A means data is valid. */
	@Enumerated(EnumType.STRING)
	private Status valid;
	
	public POSSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}
	
	public POSSentence(String nmea) {
		super(nmea);
	}
	
	@Override
	protected void decode() {
		int index = 0;
		time = ParseUtils.parseTime(getValue(index++));
		latitude = ParseUtils.parseDouble(getValue(index++));
		longitude = ParseUtils.parseDouble(getValue(index++));
		hpl = ParseUtils.parseDouble(getValue(index++));
		modeIndicator = ParseUtils.parseDouble(getValue(index++));
		valid = ParseUtils.parseStatus(getValue(index++));
	}
	
	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(time));
		setValue(index++, ParseUtils.toString(latitude));
		setValue(index++, ParseUtils.toString(longitude));
		setValue(index++, ParseUtils.toString(hpl, 1));
		setValue(index++, ParseUtils.toString(modeIndicator));
		setValue(index++, ParseUtils.toString(valid));
	}
	
	@Override
	protected void fillMap(Map<String, Object> res) {
		if (time != null) res.put("time", time);
		if (latitude != null) res.put("latitude", latitude);
		if (longitude != null) res.put("longitude", longitude);
		if (hpl != null) res.put("hpl", hpl);
		if (modeIndicator != null) res.put("modeIndicator", modeIndicator);
		if (valid != Status.NULL) res.put("status", valid.name());
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
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the hpl
	 */
	public Double getHpl() {
		return hpl;
	}
	/**
	 * @param hpl the hpl to set
	 */
	public void setHpl(Double hpl) {
		this.hpl = hpl;
	}
	/**
	 * @return the modeIndicator
	 */
	public Double getModeIndicator() {
		return modeIndicator;
	}
	/**
	 * @param modeIndicator the modeIndicator to set
	 */
	public void setModeIndicator(Double modeIndicator) {
		this.modeIndicator = modeIndicator;
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
