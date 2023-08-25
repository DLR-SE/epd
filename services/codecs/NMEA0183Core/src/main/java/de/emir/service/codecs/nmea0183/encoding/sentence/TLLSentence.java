package de.emir.service.codecs.nmea0183.encoding.sentence;


import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import de.emir.service.codecs.nmea0183.encoding.data.Hemisphere;
import de.emir.service.codecs.nmea0183.encoding.data.TargetNumber;
import de.emir.service.codecs.nmea0183.encoding.data.TargetReference;
import de.emir.service.codecs.nmea0183.encoding.data.TargetStatus;
import de.emir.service.codecs.nmea0183.encoding.data.Time;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * TLL - Target latitude and longitude<br>
 *<br>
 * 
 *<pre>
 *{@code 
 * .      1  2        3 4         5 6    7         8 9 10
 *        |  |        | |         | |    |         | | |
 * $--TLL,xx,llll.lll,a,yyyyy.yyy,a,c--c,hhmmss.ss,a,a*hh
 * }
 * </pre>
 * <ol>
 *<li>Target number 00 - 99</li>
 *<li>Latitude</li>
 *<li>N/S</li>
 *<li>Longitude</li>
 *<li>E/W</li>
 *<li>Target name</li>
 *<li>UTC of data</li>
 *<li>Target status(L = lost,tracked target has beenlost, Q = query,target in the process of acquisition, T = tracking)</li>
 *<li>Reference target=R,null otherwise</li>
 *<li>Checksum</li>
 * </ol>
 * 
 */
@XmlRootElement(name = "TLL")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TLLSentence", propOrder = {
    "targetNumber",
    "latitude",
    "latitudeHem",
    "longitude",
    "longitudeHem",
    "targetName",
    "time",
    "targetStatus",
    "referenceTarget"
})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class TLLSentence  extends Sentence {
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "RA";
	/** Sentence id. */
	public static final String SENTENCE_ID = "TLL";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 9;
	/** Default precision for encoding coordinates. */
	@Transient
	@XmlTransient
	private int precision = 4;
	
	/** Target Number */
	@Embedded
	private TargetNumber targetNumber;
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
	/** Target name */
	private String targetName;
	/** UTC of data */
	@Column(name = "UTC")
	@Embedded
	private Time time;
	/** Target Status (T = Target, L = Lost target, Q = Target being acquired) */
	@Enumerated(EnumType.STRING)
	private TargetStatus targetStatus;
	/** Reference Target (R = Reference target, blank = designated fixed target) */
	@Enumerated(EnumType.STRING)
	private TargetReference referenceTarget;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public TLLSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}
	
	/**
	 * Default constructor for parsing.
	 * @param nmea
	 * Nmea String to be parsed.
	 */
	public TLLSentence(String nmea) {
		super(nmea);
	}

	@Override
	protected void decode() {
		int index = 0;
		targetNumber = ParseUtils.parseTargetNumber(getValue(index++));
		latitude = ParseUtils.parseCoordinate(getValue(index++));
		latitudeHem = ParseUtils.parseHemisphere(getValue(index++));
		longitude = ParseUtils.parseCoordinate(getValue(index++));
		longitudeHem = ParseUtils.parseHemisphere(getValue(index++));
		targetName = getValue(index++);
		time = ParseUtils.parseTime(getValue(index++));
		targetStatus = ParseUtils.parseTargetStatus(getValue(index++));
		referenceTarget = ParseUtils.parseTargetReference(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(targetNumber));
		setValue(index++, ParseUtils.doubleToDecimalDegreeString(latitude, 2, precision));
		setValue(index++, ParseUtils.toString(latitudeHem));
		setValue(index++, ParseUtils.doubleToDecimalDegreeString(longitude, 3, precision));
		setValue(index++, ParseUtils.toString(longitudeHem));
		setValue(index++, targetName);
		setValue(index++, ParseUtils.toString(time));
		setValue(index++, ParseUtils.toString(targetStatus));
		setValue(index++, ParseUtils.toString(referenceTarget));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (targetNumber != null) res.put("targetNumber", targetNumber.getNumber());
		if (latitude != null) res.put("latitude", latitude);
		if (latitudeHem != Hemisphere.NULL) res.put("latitudeHem", latitudeHem.name());
		if (longitude != null) res.put("longitude", longitude);
		if (longitudeHem != Hemisphere.NULL) res.put("longitudeHem", longitudeHem.name());
		if (targetName != null) res.put("targetName", targetName);
		if (time != null) time.addToMap("time", res);
		if (targetStatus != TargetStatus.NULL) res.put("targetStatus", targetStatus.name());
		if (referenceTarget != null) res.put("referenceTarget", referenceTarget);
	}

	public TargetNumber getTargetNumber() {
		return targetNumber;
	}

	public void setTargetNumber(TargetNumber targetNumber) {
		this.targetNumber = targetNumber;
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

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public TargetStatus getTargetStatus() {
		return targetStatus;
	}

	public void setTargetStatus(TargetStatus targetStatus) {
		this.targetStatus = targetStatus;
	}

	public TargetReference getReferenceTarget() {
		return referenceTarget;
	}

	public void setReferenceTarget(TargetReference referenceTarget) {
		this.referenceTarget = referenceTarget;
	}

	/**
	 * @return the precision
	 */
	@Transient
	@XmlTransient
	public int getPrecision() {
		return precision;
	}

	/**
	 * @param precision the precision to set
	 */
	@Transient
	public void setPrecision(int precision) {
		this.precision = precision;
	}
}