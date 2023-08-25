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
 * MTA - Air Temperature<br>
 * <br>
 * 
 * <pre>
 * {@code
 * .      1   2  
 *        |   |  
 * $--MTA,x.x,C*hh
 * }
 * </pre>
 * <ol>
 * <li>Temperature</li>
 * <li>TemperatureUnit, C = Celsius</li>
 * <li>Checksum</li>
 * </ol>
 *                           
 * @author msalous
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MTASentence extends Sentence{
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "WI";
	/** Sentence id. */
	public static final String SENTENCE_ID = "MTA";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 2;
	
	/** Temperature */
	private Double temperature;
	/** TemperatureUnit */
	@Enumerated(EnumType.STRING)
	private TemperatureUnit temperatureUnit = TemperatureUnit.CELSIUS;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public MTASentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public MTASentence(String nmea) {
		super(nmea);
	}
	
	@Override
	protected void decode() {
		int index = 0;
		temperature = ParseUtils.parseDouble(getValue(index++));
		temperatureUnit = ParseUtils.parseTemperatureUnit(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(temperature, 1));
		setValue(index++, ParseUtils.toString(temperatureUnit));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (temperature != null) res.put("temperature", temperature);
		if (temperatureUnit != TemperatureUnit.NULL) res.put("temperatureUnit", temperatureUnit.name());
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public TemperatureUnit getTemperatureUnit() {
		return temperatureUnit;
	}

	public void setTemperatureUnit(TemperatureUnit temperatureUnit) {
		this.temperatureUnit = temperatureUnit;
	}
}
