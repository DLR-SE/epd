package de.emir.service.codecs.nmea0183.encoding.sentence.proprietary;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.data.Status;
import de.emir.service.codecs.nmea0183.encoding.sentence.Sentence;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * RUD - Rudder Command and Sensor<br>
 * <br>
 * Introduced by the MTCAS Project (https://www.offis.de/offis/projekt/mtcas.html) 
 * 
 * <pre>
 * {@code
 * .      1 2  3   4    5
 *        | |  |   |    |
 * $-RUD,x,A,x.x,x.x,*hh
 * }
 * </pre>
 * <ol>
 * <li>rudderID</li>
 * <li>Status, A means data is valid, V means warning, null means invalid</li>
 * <li>commanded rudder angle '-' means turn to portside</li>
 * <li>current rudder angle</li>
 * <li>Checksum</li>
 * </ol>
 * 
 * @author sschweigert
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RUDSentence extends Sentence{
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "P";
	/** Sentence id. */
	public static final String SENTENCE_ID = "RUD";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 4;
	
	/** unique identifier of the rudder, if no unique identifier is available increment from port to starboard. */
	private Integer rudderId;
	/** Status, A means data is valid. V means data has warnings, null means data is invalid */
	@Enumerated(EnumType.STRING)
	private Status sbStatus;
	/** commanded rudder angle in degrees (signed value)
	 * >0 means turn to starboard, <0 means turn to port*/
	private Double commanded;
	/** current rudder angle in degrees (signed value)
	 * >0 means turn to starboard, <0 means turn to port*/
	private Double currentAngle;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public RUDSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public RUDSentence(String nmea) {
		super(nmea);
	}
	
	@Override
	protected void decode() {
		int index = 0;
		rudderId = ParseUtils.parseInteger(getValue(index++));
		sbStatus = ParseUtils.parseStatus(getValue(index++));
		commanded = ParseUtils.parseDouble(getValue(index++));
		currentAngle = ParseUtils.parseDouble(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(rudderId));
		setValue(index++, ParseUtils.toString(sbStatus));
		setValue(index++, ParseUtils.toString(commanded, 1));
		setValue(index++, ParseUtils.toString(currentAngle, 1));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (rudderId != null) res.put("rudderId", rudderId);
		if (sbStatus != Status.NULL) res.put("sbStatus", sbStatus.name());
		if (commanded != null) res.put("commanded", commanded);
		if (currentAngle != null) res.put("currentAngle", currentAngle);
	}

	
	
	public Integer getRudderId() {
		return rudderId;
	}

	public void setRudderId(Integer rudderId) {
		this.rudderId = rudderId;
	}

	public Status getStarboardStatus(){
		return sbStatus;
	}
	
	public void setStarboardStatus(Status sbStatus){
		this.sbStatus = sbStatus;
	}

	public Double getCommanded() {
		return commanded;
	}

	public void setCommanded(Double commanded) {
		this.commanded = commanded;
	}

	public Double getCurrentAngle() {
		return currentAngle;
	}

	public void setCurrentAngle(Double currentAngle) {
		this.currentAngle = currentAngle;
	}
	
	
}
