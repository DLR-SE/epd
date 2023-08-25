package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.data.PressureUnit;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * MMB - Barometer<br>
 * <br>
 * 
 * <pre>
 * {@code
 * .      1   2 3   4 5 
 *        |   | |   | | 
 * $--MMB,x.x,I,x.x,B*hh
 * }
 * </pre>
 * <ol>
 * <li>Barometric pressure in inches</li>
 * <li>PressureUnit - I inches of mercury</li>
 * <li>Barometric pressure in bars</li>
 * <li>PressureUnit - B bars</li>
 * <li>Checksum</li>
 * </ol>
 *                           
 * @author msalous
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MMBSentence extends Sentence{
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "WI";
	/** Sentence id. */
	public static final String SENTENCE_ID = "MMB";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 4;
	
	/** Barometric pressure in inches - inches of mercury. */
	private Double pressureInInches;
	/** PressureUnit, I = Inches. */
	@Enumerated(EnumType.STRING)
	private PressureUnit incheUnit = PressureUnit.INCHE;
	/** Barometric pressure in bars */
	private Double pressureInBars;
	/** PressureUnit, B = Bars */
	@Enumerated(EnumType.STRING)
	private PressureUnit barUnit = PressureUnit.BAR;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public MMBSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public MMBSentence(String nmea) {
		super(nmea);
	}
	
	@Override
	protected void decode() {
		int index = 0;
		pressureInInches = ParseUtils.parseDouble(getValue(index++));
		incheUnit = ParseUtils.parsePressureUnit(getValue(index++));
		pressureInBars = ParseUtils.parseDouble(getValue(index++));
		barUnit = ParseUtils.parsePressureUnit(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(pressureInInches, 1));
		setValue(index++, ParseUtils.toString(incheUnit));
		setValue(index++, ParseUtils.toString(pressureInBars, 1));
		setValue(index++, ParseUtils.toString(barUnit));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (pressureInInches != null) res.put("pressureInInches", pressureInInches);
		if (incheUnit != PressureUnit.NULL) res.put("incheUnit", incheUnit.name());
		if (pressureInBars != null) res.put("pressureInBars", pressureInBars);
		if (barUnit != PressureUnit.NULL) res.put("barUnit", barUnit.name());
	}
	
	public Double getPressureInInches() {
		return pressureInInches;
	}

	public void setPressureInInches(Double pressureInInches) {
		this.pressureInInches = pressureInInches;
	}

	public PressureUnit getIncheUnit() {
		return incheUnit;
	}

	public void setIncheUnit(PressureUnit incheUnit) {
		this.incheUnit = incheUnit;
	}

	public Double getPressureInBars() {
		return pressureInBars;
	}

	public void setPressureInBars(Double pressureInBars) {
		this.pressureInBars = pressureInBars;
	}

	public PressureUnit getBarUnit() {
		return barUnit;
	}

	public void setBarUnit(PressureUnit barUnit) {
		this.barUnit = barUnit;
	}
}
