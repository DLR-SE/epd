package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.data.Reference;
import de.emir.service.codecs.nmea0183.encoding.data.SpeedUnit;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * MWD - Wind Direction and Speed<br>
 * <br>
 * 
 * <pre>
 * {@code
 * .      
 *        
 * $WIMWD,<1>,<2>,<3>,<4>,<5>,<6>,<7>,<8>*hh
 * }
 * </pre>
 * <ol>
 * <li>Wind direction, 0.0 to 359.9 degrees True, to the nearest 0.1 degree</li>
 * <li>Reference, T = True</li>
 * <li>Wind direction, 0.0 to 359.9 degrees Magnetic, to the nearest 0.1 degree</li>
 * <li>Reference, M = Magnetic</li>
 * <li>Wind speed, knots, to the nearest 0.1 knot</li>
 * <li>SpeedUnit, N = Knots</li>
 * <li>Wind speed, meters/second, to the nearest 0.1 m/s</li>
 * <li>SpeedUnit, M = Meter/Second</li>
 * <li>Checksum</li>
 * </ol>
 *                           
 * @author msalous
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MWDSentence extends Sentence{
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "WI";
	/** Sentence id. */
	public static final String SENTENCE_ID = "MWD";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 8;
	
	/** Wind direction, 0.0 to 359.9 degrees True, to the nearest 0.1 degree. */
	private Double trueNorthBasedDirection;
	/** Reference, T = True. */
	@Enumerated(EnumType.STRING)
	private Reference trueNorthReference = Reference.TRUE;
	/** Wind direction, 0.0 to 359.9 degrees Magnetic, to the nearest 0.1 degree. */
	private Double magneticBasedDirection;
	/** Reference, M = Magnetic. */
	@Enumerated(EnumType.STRING)
	private Reference magneticReference = Reference.MAGNETIC;
	/** Wind speed, knots, to the nearest 0.1 knot. */
	private Double speedInKnots;
	/** Wind Speed Unit, N = Knots */
	@Enumerated(EnumType.STRING)
	private SpeedUnit knotsUnit = SpeedUnit.KNOTS;
	/** Wind speed, m/s, to the nearest 0.1 m/s. */
	private Double speedInMeterPerSecond;
	/** Wind Speed Unit, M = m/s */
	@Enumerated(EnumType.STRING)
	private SpeedUnit meterPerSecondUnit = SpeedUnit.METERS_PER_SECOND;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public MWDSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public MWDSentence(String nmea) {
		super(nmea);
	}
	
	@Override
	protected void decode() {
		int index = 0;
		trueNorthBasedDirection = ParseUtils.parseDouble(getValue(index++));
		trueNorthReference = ParseUtils.parseReference(getValue(index++));
		magneticBasedDirection = ParseUtils.parseDouble(getValue(index++));
		magneticReference = ParseUtils.parseReference(getValue(index++));
		speedInKnots = ParseUtils.parseDouble(getValue(index++));
		knotsUnit = ParseUtils.parseSpeedUnit(getValue(index++));
		speedInMeterPerSecond = ParseUtils.parseDouble(getValue(index++));
		meterPerSecondUnit = ParseUtils.parseSpeedUnit(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(trueNorthBasedDirection, 1));
		setValue(index++, ParseUtils.toString(trueNorthReference));
		setValue(index++, ParseUtils.toString(magneticBasedDirection, 1));
		setValue(index++, ParseUtils.toString(magneticReference));
		setValue(index++, ParseUtils.toString(speedInKnots, 1));
		setValue(index++, ParseUtils.toString(knotsUnit));
		setValue(index++, ParseUtils.toString(speedInMeterPerSecond, 1));
		setValue(index++, ParseUtils.toString(meterPerSecondUnit));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (trueNorthBasedDirection != null) res.put("trueNorthBasedDirection", trueNorthBasedDirection);
		if (trueNorthReference != Reference.NULL) res.put("trueNorthReference", trueNorthReference.name());
		if (magneticBasedDirection != null) res.put("magneticBasedDirection", magneticBasedDirection);
		if (magneticReference != Reference.NULL) res.put("magneticReference", magneticReference.name());
		if (speedInKnots != null) res.put("speedInKnots", speedInKnots);
		if (knotsUnit != SpeedUnit.NULL) res.put("knotsUnit", knotsUnit.name());
		if (speedInMeterPerSecond != null) res.put("speedInMeterPerSecond", speedInMeterPerSecond);
		if (meterPerSecondUnit != SpeedUnit.NULL) res.put("meterPerSecondUnit", meterPerSecondUnit.name());
	}

	public Double getTrueNorthBasedDirection() {
		return trueNorthBasedDirection;
	}

	public void setTrueNorthBasedDirection(Double trueNorthBasedDirection) {
		this.trueNorthBasedDirection = trueNorthBasedDirection;
	}

	public Reference getTrueNorthReference() {
		return trueNorthReference;
	}

	public void setTrueNorthReference(Reference trueNorthReference) {
		this.trueNorthReference = trueNorthReference;
	}

	public Double getMagneticBasedDirection() {
		return magneticBasedDirection;
	}

	public void setMagneticBasedDirection(Double magneticBasedDirection) {
		this.magneticBasedDirection = magneticBasedDirection;
	}

	public Reference getMagneticReference() {
		return magneticReference;
	}

	public void setMagneticReference(Reference magneticReference) {
		this.magneticReference = magneticReference;
	}

	public Double getSpeedInKnots() {
		return speedInKnots;
	}

	public void setSpeedInKnots(Double speedInKnots) {
		this.speedInKnots = speedInKnots;
	}

	public SpeedUnit getKnotsUnit() {
		return knotsUnit;
	}

	public void setKnotsUnit(SpeedUnit knotsUnit) {
		this.knotsUnit = knotsUnit;
	}

	public Double getSpeedInMeterPerSecond() {
		return speedInMeterPerSecond;
	}

	public void setSpeedInMeterPerSecond(Double speedInMeterPerSecond) {
		this.speedInMeterPerSecond = speedInMeterPerSecond;
	}

	public SpeedUnit getMeterPerSecondUnit() {
		return meterPerSecondUnit;
	}

	public void setMeterPerSecondUnit(SpeedUnit meterPerSecondUnit) {
		this.meterPerSecondUnit = meterPerSecondUnit;
	}
	
}
