package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * This is a NMEA0183 VDR sentence. Implementation details can be found within
 * the NMEA0183 specification, e.g. at
 * http://caxapa.ru/thumbs/214299/NMEA0183_.pdf
 * 
 * VDR - Set and Drift 
 * The direction towards which a current flows (Set) and speed (Drift) of a current.
 * $--VDR,x.x,T,x.x,M,x.x,N*hh<CR><LF>
 *  Current speed, knots
 *   Direction, degrees Magnetic
 *    Direction, degrees True 
 * 
 * @author cdenker
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class VDRSentence extends Sentence {

	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	// FIXME: check DEFAULT_TALKER
	public static final String DEFAULT_TALKER = "AG";
	/** Sentence id. */
	public static final String SENTENCE_ID = "VDR";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 6;

	private Double directionDegTrue;
	private String directionTrue;
	private Double directionDegMag;
	private String directionMag;
	private Double speedKn;
	private String speedUnit;

	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public VDRSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public VDRSentence(String nmea) {
		super(nmea);
	}

	@Override
	protected void decode() {
		int index = 0;

		directionDegTrue = ParseUtils.parseDouble(getValue(index++));
		directionTrue = getValue(index++);
		directionDegMag = ParseUtils.parseDouble(getValue(index++));
		directionMag = getValue(index++);
		speedKn = ParseUtils.parseDouble(getValue(index++));
		speedUnit = getValue(index++);
	}

	@Override
	protected void encode() {
		int index = 0;

		setValue(index++, ParseUtils.toString(directionDegTrue, 1));
		setValue(index++, directionTrue);
		setValue(index++, ParseUtils.toString(directionDegMag, 1));
		setValue(index++, directionMag);
		setValue(index++, ParseUtils.toString(speedKn, 1));
		setValue(index++, speedUnit);
	}

	@Override
	protected void fillMap(Map<String, Object> res) {

		if (directionDegTrue != null)
			res.put("directionDegTrue", directionDegTrue);
		if (directionTrue != null)
			res.put("directionTrue", directionTrue);
		if (directionDegMag != null)
			res.put("directionDegMag", directionDegMag);
		if (directionMag != null)
			res.put("directionMag", directionMag);
		if (speedKn != null)
			res.put("speedKn", speedKn);
		if (speedUnit != null)
			res.put("speedUnit", speedUnit);
	}

	public Double getDirectionDegTrue() {
		return directionDegTrue;
	}

	public void setDirectionDegTrue(Double directionDegTrue) {
		this.directionDegTrue = directionDegTrue;
	}

	public String getDirectionTrue() {
		return directionTrue;
	}

	public void setDirectionTrue(String directionTrue) {
		this.directionTrue = directionTrue;
	}

	public Double getDirectionDegMag() {
		return directionDegMag;
	}

	public void setDirectionDegMag(Double directionDegMag) {
		this.directionDegMag = directionDegMag;
	}

	public String getDirectionMag() {
		return directionMag;
	}

	public void setDirectionMag(String directionMag) {
		this.directionMag = directionMag;
	}

	public Double getSpeedKn() {
		return speedKn;
	}

	public void setSpeedKn(Double speedKn) {
		this.speedKn = speedKn;
	}

	public String getSpeedUnit() {
		return speedUnit;
	}

	public void setSpeedUnit(String speedUnit) {
		this.speedUnit = speedUnit;
	}

	
}
