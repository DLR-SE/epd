package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.data.Hemisphere;
import de.emir.service.codecs.nmea0183.encoding.data.ModeIndicator;
import de.emir.service.codecs.nmea0183.encoding.data.Status;
import de.emir.service.codecs.nmea0183.encoding.data.Time;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * GLL - Geographic Position � Latitude/Longitude<br>
 * <br>
 * 
 * 
 * <pre>
 * {@code
 * .      1       2 3        4 5         6 7 8
 *        |       | |        | |         | | |
 * $--GLL,llll.ll,a,yyyyy.yy,a,hhmmss.ss,A,a*hh
 * }
 * </pre>
 * <ol>
 * <li>Latitude</li>
 * <li>N or S (North or South)</li>
 * <li>Longitude</li>
 * <li>E or W (East or West)</li>
 * <li>Time (UTC)</li>
 * <li>Status A - Data Valid, V - Data Invalid</li>
 * <li>Mode indicator</li>
 * <li>Checksum</li>
 * </ol>
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class GLLSentence extends Sentence {
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "IN";
	/** Sentence id. */
	public static final String SENTENCE_ID = "GLL";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 7;

	/** Latitude */
	private Double latitude;
	/** N or S (North or South) */
	@Enumerated(EnumType.STRING)
	private Hemisphere latitudeHem;
	/** Longitude */
	private Double longitude;
	/** E or W (East or West) */
	@Enumerated(EnumType.STRING)
	private Hemisphere longitudeHem;
	/** Time (UTC) */
	private Time time;
	/** Status A - Data Valid, V - Data Invalid */
	@Enumerated(EnumType.STRING)
	private Status status;
	/** Mode indicator */
    @Enumerated(EnumType.STRING)
    private ModeIndicator modeIndicator;

	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public GLLSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public GLLSentence(String nmea) {
		super(nmea);
	}

	@Override
	protected void decode() {
		int index = 0;
		latitude = ParseUtils.parseCoordinate(getValue(index++));
		latitudeHem = ParseUtils.parseHemisphere(getValue(index++));
		longitude = ParseUtils.parseCoordinate(getValue(index++));
		longitudeHem = ParseUtils.parseHemisphere(getValue(index++));
		time = ParseUtils.parseTime(getValue(index++));
		status = ParseUtils.parseStatus(getValue(index++));
		modeIndicator = ParseUtils.parseModeIndicator(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.doubleToDecimalDegreeString(latitude, 2));
		setValue(index++, ParseUtils.toString(latitudeHem));
		setValue(index++, ParseUtils.doubleToDecimalDegreeString(longitude, 3));
		setValue(index++, ParseUtils.toString(longitudeHem));
		setValue(index++, ParseUtils.toString(time));
		setValue(index++, ParseUtils.toString(status));
		setValue(index++, ParseUtils.toString(modeIndicator));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (latitude != null) res.put("latitude", latitude);
		if (latitudeHem != Hemisphere.NULL) res.put("latitudeHem", latitudeHem.name());
		if (longitude != null) res.put("longitude", longitude);
		if (longitudeHem != Hemisphere.NULL) res.put("longitudeHem", longitudeHem.name());
		if (time != null) time.addToMap("time", res);
		if (status != Status.NULL) res.put("status", status.name());
		if (modeIndicator != null) res.put("modeIndicator", modeIndicator.name());
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

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}
	
	public Status getStatus(){
		return status;
	}
	
	public void setStatus(Status status){
		this.status = status;
	}

    public ModeIndicator getModeIndicator() {
        return modeIndicator;
    }

    public void setModeIndicator(ModeIndicator modeIndicator) {
        this.modeIndicator = modeIndicator;
    }
}
