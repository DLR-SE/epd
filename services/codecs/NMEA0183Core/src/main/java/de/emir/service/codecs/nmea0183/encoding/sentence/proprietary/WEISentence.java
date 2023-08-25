package de.emir.service.codecs.nmea0183.encoding.sentence.proprietary;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.sentence.Sentence;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * WEI - <br>
 * <br>
 * Introduced by the MTCAS Project (https://www.offis.de/offis/projekt/mtcas.html) 
 * 
 * <pre>
 * {@code
 * .      1   2   3   4 
 *        |   |   |   | 
 * $-WEI,x.x,x.x,x.x,*hh
 * }
 * </pre>
 * <ol>
 * <li>dead weight, ton</li>
 * <li>light, ton</li>
 * <li>loaded, ton</li>
 * <li>Checksum</li>
 * </ol>
 * 
 * @author sschweigert
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class WEISentence extends Sentence{
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "P";
	/** Sentence id. */
	public static final String SENTENCE_ID = "WEI";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 4;
	
	
	private Double deadWeight;
	private Double light;
	private Double loaded;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public WEISentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public WEISentence(String nmea) {
		super(nmea);
	}
	
	@Override
	protected void decode() {
		int index = 0;
		deadWeight = ParseUtils.parseDouble(getValue(index++));
		light = ParseUtils.parseDouble(getValue(index++));
		loaded = ParseUtils.parseDouble(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(deadWeight));
		setValue(index++, ParseUtils.toString(light));
		setValue(index++, ParseUtils.toString(loaded));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (deadWeight != null) res.put("deadWeight", deadWeight);
		if (light != null) res.put("light", light);
		if (loaded != null) res.put("loaded", loaded);
	}

	public Double getDeadWeight() {
		return deadWeight;
	}

	public void setDeadWeight(Double deadWeight) {
		this.deadWeight = deadWeight;
	}

	public Double getLight() {
		return light;
	}

	public void setLight(Double light) {
		this.light = light;
	}

	public Double getLoaded() {
		return loaded;
	}

	public void setLoaded(Double loaded) {
		this.loaded = loaded;
	}

	
	
	
}
