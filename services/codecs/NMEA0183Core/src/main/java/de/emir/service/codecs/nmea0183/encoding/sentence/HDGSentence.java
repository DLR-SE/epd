package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.data.Hemisphere;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * HDG - Heading-Deviation & Variation<br>
 * <br>
 * 
 * <pre>
 * {@code
 * .      1   2   3 4   5 6
 *        |   |   | |   | |
 * $--HDG,x.x,x.x,a,x.x,a*hh
 * }
 * </pre>
 * <ol>
 * <li>Magnetic Sensor heading in degrees</li>
 * <li>Magnetic Deviation, degrees</li>
 * <li>Magnetic Deviation direction, E = Easterly, W = Westerly</li>
 * <li>Magnetic Variation degrees</li>
 * <li>Magnetic Variation direction, E = Easterly, W = Westerly</li>
 * <li>Checksum</li>
 * </ol>
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class HDGSentence extends Sentence {
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "HC";
	/** Sentence id. */
	public static final String SENTENCE_ID = "HDG";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 6;
	
	/** Magnetic Sensor heading in degrees */
	private Double heading;
	/** Magnetic Deviation, degrees */
	private Double deviation;
	/** Magnetic Deviation direction, E = Easterly, W = Westerly */
	@Enumerated(EnumType.STRING)
	private Hemisphere deviationDir;
	/** Magnetic Variation degrees */
	private Double variation;
	/** Magnetic Variation direction, E = Easterly, W = Westerly */
	@Enumerated(EnumType.STRING)
	private Hemisphere variationDir;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public HDGSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}
	
	/**
	 * Default constructor for parsing.
	 * @param nmea
	 * Nmea String to be parsed.
	 */
	public HDGSentence(String nmea) {
		super(nmea);
	}

	@Override
	protected void decode() {
		int index = 0;
		heading = ParseUtils.parseDouble(getValue(index++));
		deviation = ParseUtils.parseDouble(getValue(index++));
		deviationDir = ParseUtils.parseHemisphere(getValue(index++));
		variation = ParseUtils.parseDouble(getValue(index++));
		variationDir = ParseUtils.parseHemisphere(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(heading, 1));
		setValue(index++, ParseUtils.toString(deviation, 1));
		setValue(index++, ParseUtils.toString(deviationDir));
		setValue(index++, ParseUtils.toString(variation, 1));
		setValue(index++, ParseUtils.toString(variationDir));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (heading != null) res.put("heading", heading);
		if (deviation != null) res.put("deviation", deviation);
		if (deviationDir != Hemisphere.NULL) res.put("deviationDir", deviationDir.name());
		if (variation != null) res.put("variation", variation);
		if (variationDir != Hemisphere.NULL) res.put("variationDir", variationDir.name());
	}

	public Double getHeading() {
		return heading;
	}

	public void setHeading(Double heading) {
		this.heading = heading;
	}

	public Double getDeviation() {
		return deviation;
	}

	public void setDeviation(Double deviation) {
		this.deviation = deviation;
	}

	public Hemisphere getDeviationDir() {
		return deviationDir;
	}

	public void setDeviationDir(Hemisphere deviationDir) {
		this.deviationDir = deviationDir;
	}

	public Double getVariation() {
		return variation;
	}

	public void setVariation(Double variation) {
		this.variation = variation;
	}

	public Hemisphere getVariationDir() {
		return variationDir;
	}

	public void setVariationDir(Hemisphere variationDir) {
		this.variationDir = variationDir;
	}
}
