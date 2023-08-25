package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.data.Date;
import de.emir.service.codecs.nmea0183.encoding.data.Hemisphere;
import de.emir.service.codecs.nmea0183.encoding.data.Status;
import de.emir.service.codecs.nmea0183.encoding.data.Time;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * RMC - Recomended Minimum Navigation Information<br>
 * <br>
 * 
 * <pre>
 * {@code
 * .      1         2 3       4 5        6 7   8   9   10  11 12
 *        |         | |       | |        | |   |   |    |   | |
 * $--RMC,hhmmss.ss,A,llll.ll,a,yyyyy.yy,a,x.x,x.x,xxxx,x.x,a*hh
 * }
 * </pre>
 * <ol>
 * <li>Time (UTC)</li>
 * <li>Status, V = Navigation receiver warning</li>
 * <li>Latitude</li>
 * <li>N or S</li>
 * <li>Longitude</li>
 * <li>E or W</li>
 * <li>Speed over ground, knots</li>
 * <li>Track made good, degrees true</li>
 * <li>Date, ddmmyy</li>
 * <li>Magnetic Variation, degrees</li>
 * <li>E or W</li>
 * <li>Checksum</li>
 * </ol>
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RMCSentence extends Sentence {
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "GP";
	/** Sentence id. */
	public static final String SENTENCE_ID = "RMC";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 11;
	
	/** Time (UTC) */
	private Time time;
	/** Status, V = Navigation receiver warning */
	@Enumerated(EnumType.STRING)
	private Status status;
	/** Latitude */
	private Double latitude;
	/** N or S */
	@Enumerated(EnumType.STRING)
	private Hemisphere latitudeHem;
	/** Longitude */
	private Double longitude;
	/** E or W */
	@Enumerated(EnumType.STRING)
	private Hemisphere longitudeHem;
	/** Speed over ground, knots */
	private Double speedOverGround;
	/** Course over Ground/Track made good, degrees true */
	private Double courseOverGround;
	/** Date, ddmmyy */
	private Date date;
	/** Magnetic Variation, degrees */
	private Double magneticVariation;
	/** E or W */
	@Enumerated(EnumType.STRING)
	private Hemisphere magneticHem;

	/**
	 * Default constructor for parsing.
	 * @param nmea
	 * Nmea String to be parsed.
	 */
	public RMCSentence(String nmea) {
		super(nmea);
	}
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public RMCSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	@Override
	protected void decode() {
		int index = 0;
		time = ParseUtils.parseTime(getValue(index++));
		status = ParseUtils.parseStatus(getValue(index++));
		latitude = ParseUtils.parseCoordinate(getValue(index++));
		latitudeHem = ParseUtils.parseHemisphere(getValue(index++));
		longitude = ParseUtils.parseCoordinate(getValue(index++));
		longitudeHem = ParseUtils.parseHemisphere(getValue(index++));
		speedOverGround = ParseUtils.parseDouble(getValue(index++));
		courseOverGround = ParseUtils.parseDouble(getValue(index++));
		date = ParseUtils.parseDate(getValue(index++));
		magneticVariation = ParseUtils.parseDouble(getValue(index++));
		magneticHem = ParseUtils.parseHemisphere(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(time));
		setValue(index++, ParseUtils.toString(status));
		setValue(index++, ParseUtils.doubleToDecimalDegreeString(latitude, 2));
		setValue(index++, ParseUtils.toString(latitudeHem));
		setValue(index++, ParseUtils.doubleToDecimalDegreeString(longitude, 3));
		setValue(index++, ParseUtils.toString(longitudeHem));
		setValue(index++, ParseUtils.toString(speedOverGround, 1));
		setValue(index++, ParseUtils.toString(courseOverGround, 1));
		setValue(index++, ParseUtils.toString(date));
		setValue(index++, ParseUtils.toString(magneticVariation, 1));
		setValue(index++, ParseUtils.toString(magneticHem));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (time != null) res.put("time", time);
		if (status != Status.NULL) res.put("status", status.name());
		if (latitude != null) res.put("latitude", latitude);
		if (latitudeHem != Hemisphere.NULL) res.put("latitudeHem", latitudeHem.name());
		if (longitude != null) res.put("longitude", longitude);
		if (longitudeHem != Hemisphere.NULL) res.put("longitudeHem", longitudeHem.name());
		if (speedOverGround != null) res.put("speedOverGround", speedOverGround);
		if (courseOverGround != null) {
			res.put("trackMadeGood", courseOverGround);
			res.put("courseOverGround", courseOverGround);
		}
		if (date != null) res.put("date", date);
		if (magneticVariation != null) res.put("magneticVariation", magneticVariation);
		if (magneticHem != Hemisphere.NULL) res.put("magneticHem", magneticHem.name());
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Hemisphere getLatitudeHem() {
		return latitudeHem;
	}

	public void setLatitudeHem(Hemisphere latitudeHem) {
		this.latitudeHem = latitudeHem;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Hemisphere getLongitudeHem() {
		return longitudeHem;
	}

	public void setLongitudeHem(Hemisphere longitudeHem) {
		this.longitudeHem = longitudeHem;
	}

	public Double getSpeedOverGround() {
		return speedOverGround;
	}

	public void setSpeedOverGround(Double speedOverGround) {
		this.speedOverGround = speedOverGround;
	}

	public Double getTrackMadeGood() {
		return courseOverGround;
	}

	public void setTrackMadeGood(Double courseOverGround) {
		this.courseOverGround = courseOverGround;
	}
	
	public Double getCourseOverGround() {
		return courseOverGround;
	}

	public void setCourseOverGround(Double courseOverGround) {
		this.courseOverGround = courseOverGround;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getMagneticVariation() {
		return magneticVariation;
	}

	public void setMagneticVariation(Double magneticVariation) {
		this.magneticVariation = magneticVariation;
	}

	public Hemisphere getMagneticHem() {
		return magneticHem;
	}

	public void setMagneticHem(Hemisphere magneticHem) {
		this.magneticHem = magneticHem;
	}

}
