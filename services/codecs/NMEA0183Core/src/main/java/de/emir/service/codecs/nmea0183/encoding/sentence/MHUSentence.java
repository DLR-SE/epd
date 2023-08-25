package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.data.TemperatureUnit;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * MHU - Humidity<br>
 * <br>
 * 
 * <pre>
 * {@code
 * .      1   2   3   4  
 *        |   |   |   |  
 * $--MHU,x.x,x.x,x.x,C*hh
 * }
 * </pre>
 * <ol>
 * <li>Relative humidity, percentage%</li>
 * <li>Absolute humidity,  in g/m³</li>
 * <li>Dew point temperature in °C</li>
 * <li>Unit: degrees Celsius C</li>
 * <li>Checksum</li>
 * </ol>
 *                           
 * @author msalous
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MHUSentence extends Sentence{
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "WI";
	/** Sentence id. */
	public static final String SENTENCE_ID = "MHU";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 4;
	
	/** relative Humidity, percentage%*/
	private Double relativeHumidity;
	/** Absolute humidity, in g/m³ */
	private Double absoluteHumidity;
	/** Dew point temperature in C  */
	private Double dewPointTemperature;
	/** Dew point temperature unit: C */
	@Enumerated(EnumType.STRING)
	private TemperatureUnit dewPointTemperatureUnit = TemperatureUnit.CELSIUS;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public MHUSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public MHUSentence(String nmea) {
		super(nmea);
	}
	
	@Override
	protected void decode() {
		int index = 0;
		relativeHumidity = ParseUtils.parseDouble(getValue(index++));
		absoluteHumidity = ParseUtils.parseDouble(getValue(index++));
		dewPointTemperature = ParseUtils.parseDouble(getValue(index++));
		dewPointTemperatureUnit = ParseUtils.parseTemperatureUnit(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(relativeHumidity, 1));
		setValue(index++, ParseUtils.toString(absoluteHumidity, 1));
		setValue(index++, ParseUtils.toString(dewPointTemperature, 1));
		setValue(index++, ParseUtils.toString(dewPointTemperatureUnit));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (relativeHumidity != null) res.put("relativeHumidity", relativeHumidity);
		if (absoluteHumidity != null) res.put("absoluteHumidity", absoluteHumidity);
		if (dewPointTemperature != null) res.put("dewPointTemperature", dewPointTemperature);
		if (dewPointTemperatureUnit != TemperatureUnit.NULL) res.put("dewPointTemperatureUnit", dewPointTemperatureUnit.name());
	}

	public Double getRelativeHumidity() {
		return relativeHumidity;
	}

	public void setRelativeHumidity(Double relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}

	public Double getAbsoluteHumidity() {
		return absoluteHumidity;
	}

	public void setAbsoluteHumidity(Double absoluteHumidity) {
		this.absoluteHumidity = absoluteHumidity;
	}

	public Double getDewPointTemperature() {
		return dewPointTemperature;
	}

	public void setDewPointTemperature(Double dewPointTemperature) {
		this.dewPointTemperature = dewPointTemperature;
	}

	public TemperatureUnit getDewPointTemperatureUnit() {
		return dewPointTemperatureUnit;
	}

	public void setDewPointTemperatureUnit(TemperatureUnit dewPointTemperatureUnit) {
		this.dewPointTemperatureUnit = dewPointTemperatureUnit;
	}
}
