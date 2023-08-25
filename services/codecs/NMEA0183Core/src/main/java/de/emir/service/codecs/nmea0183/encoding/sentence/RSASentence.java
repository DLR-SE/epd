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
 * RSA - Rudder Sensor Angle<br>
 * <br>
 * 
 * <pre>
 * {@code
 * .      1   2 3   4 5
 *        |   | |   | |
 * $--RSA,x.x,A,x.x,A*hh
 * }
 * </pre>
 * <ol>
 * <li>Starboard (or single) rudder sensor, "-" means Turn To Port</li>
 * <li>Status, A means data is valid</li>
 * <li>Port rudder sensor</li>
 * <li>Status, A means data is valid</li>
 * <li>Checksum</li>
 * </ol>
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RSASentence extends Sentence{
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "IN";
	/** Sentence id. */
	public static final String SENTENCE_ID = "RSA";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 5;
	
	/** Starboard (or single) rudder sensor, "-" means Turn To Port. */
	private Double starboardside;
	/** Status, A means data is valid. */
	@Enumerated(EnumType.STRING)
	private Status sbStatus;
	/** Port rudder sensor. */
	private Double portside;
	/** Status, A means data is valid. */
	@Enumerated(EnumType.STRING)
	private Status pbStatus;	
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public RSASentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public RSASentence(String nmea) {
		super(nmea);
	}
	
	@Override
	protected void decode() {
		int index = 0;
		starboardside = ParseUtils.parseDouble(getValue(index++));
		sbStatus = ParseUtils.parseStatus(getValue(index++));
		portside = ParseUtils.parseDouble(getValue(index++));
		pbStatus = ParseUtils.parseStatus(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(starboardside, 1));
		setValue(index++, ParseUtils.toString(sbStatus));
		setValue(index++, ParseUtils.toString(portside, 1));
		setValue(index++, ParseUtils.toString(pbStatus));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (starboardside != null) res.put("starboardside", starboardside);
		if (sbStatus != Status.NULL) res.put("sbStatus", sbStatus.name());
		if (portside != null) res.put("portside", portside);
		if (pbStatus != Status.NULL) res.put("pbStatus", pbStatus.name());
	}

	public Double getStarboard(){
		return starboardside;
	}
	
	public void setStarboard(Double starboard){
		this.starboardside = starboard;
	}
	
	public Status getStarboardStatus(){
		return sbStatus;
	}
	
	public void setStarboardStatus(Status sbStatus){
		this.sbStatus = sbStatus;
	}
	
	public Double getPortboard(){
		return portside;
	}
	
	public void setPortboard(Double portboard){
		this.portside = portboard;
	}
	
	public Status getPortboardStatus(){
		return pbStatus;
	}
	
	public void setPortboardStatus(Status pbStatus){
		this.pbStatus = pbStatus;
	}
}
