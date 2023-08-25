package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.data.GpsQuality;
import de.emir.service.codecs.nmea0183.encoding.data.Hemisphere;
import de.emir.service.codecs.nmea0183.encoding.data.Time;
import de.emir.service.codecs.nmea0183.encoding.data.Unit;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * GGA - Global Positioning System Fix Data. Time, Position and fix related data
 * for a GPS receiver<br>
 * <br>
 * 
 * <pre>
 * {@code
 * .      1         2       3 4        5 6 7  8   9  10 11 12 13 14   15
 *        |         |       | |        | | |  |   |   | |   | |   |    |
 * $--GGA,hhmmss.ss,llll.ll,a,yyyyy.yy,a,x,xx,x.x,x.x,M,x.x,M,x.x,xxxx*hh
 * }
 * </pre>
 * <ol>
 * <li>Time (UTC)</li>
 * <li>Latitude</li>
 * <li>N or S (North or South)</li>
 * <li>Longitude</li>
 * <li>E or W (East or West)</li>
 * <li>GPS Quality Indicator
 * <ul>
 * <li>0 - fix not available</li>
 * <li>1 - GPS fix</li>
 * <li>2 - Differential GPS fix</li>
 * </ul>
 * </li>
 * <li>Number of satellites in view, 00 - 12</li>
 * <li>Horizontal Dilution of precision</li>
 * <li>Antenna Altitude above/below mean-sea-level (geoid)</li>
 * <li>Units of antenna altitude, meters</li>
 * <li>Geoidal separation, the difference between the WGS-84 earth ellipsoid and
 * mean-sea-level (geoid), "-" means mean-sea-level below ellipsoid</li>
 * <li>Units of geoidal separation, meters</li>
 * <li>Age of differential GPS data, time in seconds since last SC104 type 1 or
 * 9 update, null field when DGPS is not used</li>
 * <li>Differential reference station ID, 0000-1023</li>
 * <li>Checksum</li>
 * </ol>
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class GGASentence extends Sentence {
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "GP";
	/** Sentence id. */
	public static final String SENTENCE_ID = "GGA";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 14;
	
	/** Time (UTC) */
	private Time time;
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
	/** GPS Quality Indicator */
	@Enumerated(EnumType.STRING)
	private GpsQuality gpsQuality;
	/** Number of satellites in view, 00 - 12 */
	private Integer numberOfSattelites;
	/** Horizontal Dilution of precision */
	private Double horizontalDilution;
	/** Antenna Altitude above/below mean-sea-level (geoid) */
	private Double antennaAltitude;
	/** Units of antenna altitude, meters */
	@Enumerated(EnumType.STRING)
	private Unit antennaAltUnits;
	/** Geoidal separation, the difference between the WGS-84 earth ellipsoid and mean-sea-level (geoid), "-" means mean-sea-level below ellipsoid */
	private Double geoidalSeparation;
	/** Units of geoidal separation, meters */
	@Enumerated(EnumType.STRING)
	private Unit geoidalSepUnits;
	/** Age of differential GPS data, time in seconds since last SC104 type 1 or 9 update, null field when DGPS is not used */
	private Double ageOfDgps;
	/** Differential reference station ID, 0000-1023 */
	private Integer differentialRefId;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public GGASentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}
	
	/**
	 * Default constructor for parsing.
	 * @param nmea
	 * Nmea String to be parsed.
	 */
	public GGASentence(String nmea) {
		super(nmea);
	}

	@Override
	protected void decode() {
		int index = 0;
		time = ParseUtils.parseTime(getValue(index++));
		latitude = ParseUtils.parseCoordinate(getValue(index++));
		latitudeHem = ParseUtils.parseHemisphere(getValue(index++));
		longitude = ParseUtils.parseCoordinate(getValue(index++));
		longitudeHem = ParseUtils.parseHemisphere(getValue(index++));
		gpsQuality = ParseUtils.parseGpsQuality(getValue(index++));
		numberOfSattelites = ParseUtils.parseInteger(getValue(index++));
		horizontalDilution = ParseUtils.parseDouble(getValue(index++));
		antennaAltitude = ParseUtils.parseDouble(getValue(index++));
		antennaAltUnits = ParseUtils.parseUnit(getValue(index++));
		geoidalSeparation = ParseUtils.parseDouble(getValue(index++));
		geoidalSepUnits = ParseUtils.parseUnit(getValue(index++));
		ageOfDgps = ParseUtils.parseDouble(getValue(index++));
		differentialRefId = ParseUtils.parseInteger(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(time));
		setValue(index++, ParseUtils.doubleToDecimalDegreeString(latitude, 2));
		setValue(index++, ParseUtils.toString(latitudeHem));
		setValue(index++, ParseUtils.doubleToDecimalDegreeString(longitude, 3));
		setValue(index++, ParseUtils.toString(longitudeHem));
		setValue(index++, ParseUtils.toString(gpsQuality));
		setValue(index++, ParseUtils.toString(numberOfSattelites));
		setValue(index++, ParseUtils.toString(horizontalDilution, 1));
		setValue(index++, ParseUtils.toString(antennaAltitude, 1));
		setValue(index++, ParseUtils.toString(antennaAltUnits));
		setValue(index++, ParseUtils.toString(geoidalSeparation, 1));
		setValue(index++, ParseUtils.toString(geoidalSepUnits));
		setValue(index++, ParseUtils.toString(ageOfDgps, 1));
		setValue(index++, ParseUtils.toString(differentialRefId));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (time != null) time.addToMap("time", res);
		if (latitude != null) res.put("latitude", latitude);
		if (latitudeHem != Hemisphere.NULL) res.put("latitudeHem", latitudeHem.name());
		if (longitude != null) res.put("longitude", longitude);
		if (longitudeHem != Hemisphere.NULL) res.put("longitudeHem", longitudeHem.name());
		res.put("gpsQuality", gpsQuality.name());
		if (numberOfSattelites != null) res.put("numberOfSattelites", numberOfSattelites);
		if (horizontalDilution != null) res.put("horizontalDilution", horizontalDilution);
		if (antennaAltitude != null) res.put("antennaAltitude", antennaAltitude);
		if (antennaAltUnits != Unit.NULL) res.put("antennaAltUnits", antennaAltUnits.name());
		if (geoidalSeparation != null) res.put("geoidalSeparation", geoidalSeparation);
		if (geoidalSepUnits != Unit.NULL) res.put("geoidalSepUnits", geoidalSepUnits.name());
		if (ageOfDgps != null) res.put("ageOfDgps", ageOfDgps);
		if (differentialRefId != null) res.put("differentialRefId", differentialRefId);
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
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

	public GpsQuality getGpsQuality() {
		return gpsQuality;
	}

	public void setGpsQuality(GpsQuality gpsQuality) {
		this.gpsQuality = gpsQuality;
	}

	public Integer getNumberOfSattelites() {
		return numberOfSattelites;
	}

	public void setNumberOfSattelites(Integer numberOfSattelites) {
		this.numberOfSattelites = numberOfSattelites;
	}

	public Double getHorizontalDilution() {
		return horizontalDilution;
	}

	public void setHorizontalDilution(Double horizontalDilution) {
		this.horizontalDilution = horizontalDilution;
	}

	public Double getAntennaAltitude() {
		return antennaAltitude;
	}

	public void setAntennaAltitude(Double antennaAltitude) {
		this.antennaAltitude = antennaAltitude;
	}

	public Unit getAntennaAltUnits() {
		return antennaAltUnits;
	}

	public void setAntennaAltUnits(Unit antennaAltUnits) {
		this.antennaAltUnits = antennaAltUnits;
	}

	public Double getGeoidalSeparation() {
		return geoidalSeparation;
	}

	public void setGeoidalSeparation(Double geoidalSeparation) {
		this.geoidalSeparation = geoidalSeparation;
	}

	public Unit getGeoidalSepUnits() {
		return geoidalSepUnits;
	}

	public void setGeoidalSepUnits(Unit geoidalSepUnits) {
		this.geoidalSepUnits = geoidalSepUnits;
	}

	public Double getAgeOfDgps() {
		return ageOfDgps;
	}

	public void setAgeOfDgps(Double ageOfDgps) {
		this.ageOfDgps = ageOfDgps;
	}

	public Integer getDifferentialRefId() {
		return differentialRefId;
	}

	public void setDifferentialRefId(Integer differentialRefId) {
		this.differentialRefId = differentialRefId;
	}
}
