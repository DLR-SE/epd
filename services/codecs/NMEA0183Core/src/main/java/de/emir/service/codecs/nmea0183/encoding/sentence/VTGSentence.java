package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.data.ModeIndicator;
import de.emir.service.codecs.nmea0183.encoding.data.Reference;
import de.emir.service.codecs.nmea0183.encoding.data.Unit;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * VTG - Track Made Good and Ground Speed<br>
 * <br>
 * 
 * <pre>
 * {@code
 * .      1   2 3   4 5   6 7   8 9 10
 *        |   | |   | |   | |   | | |
 * $--VTG,x.x,T,x.x,M,x.x,N,x.x,K,a*hh
 * }
 * </pre>
 * <ol>
 * <li>Course Over Ground Degrees True</li>
 * <li>T = True</li>
 * <li>Course Over Ground Degrees Magnetic</li>
 * <li>M = Magnetic</li>
 * <li>Speed Over Ground Knots</li>
 * <li>N = Knots</li>
 * <li>Speed Over Ground Kilometers Per Hour</li>
 * <li>K = Kilometers Per Hour</li>
 * <li>a = Mode indicator
 * </ol>
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class VTGSentence extends Sentence{
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "GP";
	/** Sentence id. */
	public static final String SENTENCE_ID = "VTG";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 9;
	
	/** Course Over Ground Degrees True */
	private Double courseOverGround;
	/** T = True */
	@Enumerated(EnumType.STRING)
	private Reference trackReference;
	/** Course Over Ground Degrees Magnetic */
	private Double courseOverGroundMagnetic;
	/** M = Magnetic */
	@Enumerated(EnumType.STRING)
	private Reference magneticReference;
	/** Speed Knots */
	private Double speedOverGround;
	/** N = Knots */
	@Enumerated(EnumType.STRING)
	private Unit speedKnotsUnits;
	/** Speed Kilometers Per Hour */
	private Double speedKilometers;
	/** K = Kilometers per Hour */
	@Enumerated(EnumType.STRING)
	private Unit speedKilometersUnits;
	/** a = Mode indicator */
    @Enumerated(EnumType.STRING)
    private ModeIndicator modeIndicator;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public VTGSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public VTGSentence(String nmea) {
		super(nmea);
	}
	
	@Override
	protected void decode() {
		int index = 0;
		courseOverGround = ParseUtils.parseDouble(getValue(index++));
		trackReference = ParseUtils.parseReference(getValue(index++));
		courseOverGroundMagnetic = ParseUtils.parseDouble(getValue(index++));
		magneticReference = ParseUtils.parseReference(getValue(index++));
		speedOverGround = ParseUtils.parseDouble(getValue(index++));
		speedKnotsUnits = ParseUtils.parseUnit(getValue(index++));
		speedKilometers = ParseUtils.parseDouble(getValue(index++));
		speedKilometersUnits = ParseUtils.parseUnit(getValue(index++));
		modeIndicator = ParseUtils.parseModeIndicator(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(courseOverGround, 1));
		setValue(index++, ParseUtils.toString(trackReference));
		setValue(index++, ParseUtils.toString(courseOverGroundMagnetic, 1));
		setValue(index++, ParseUtils.toString(magneticReference));
		setValue(index++, ParseUtils.toString(speedOverGround, 1));
		setValue(index++, ParseUtils.toString(speedKnotsUnits));
		setValue(index++, ParseUtils.toString(speedKilometers, 1));
		setValue(index++, ParseUtils.toString(speedKilometersUnits));
		setValue(index++, ParseUtils.toString(modeIndicator));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (courseOverGround != null) {
			res.put("headingTrack", courseOverGround);
			res.put("courseOverGround", courseOverGround);
		}
		if (trackReference != Reference.NULL) res.put("trackReference", trackReference.name());
		if (courseOverGroundMagnetic != null) {
			res.put("headingMagnetic", courseOverGroundMagnetic);
			res.put("courseOverGroungMagnetic", courseOverGroundMagnetic);
		}
		if (magneticReference != Reference.NULL) res.put("magneticReference", magneticReference.name());
		if (speedOverGround != null) {
			res.put("speedKnots", speedOverGround);
			res.put("speedOverGround", speedOverGround);
		}
		if (speedKnotsUnits != Unit.NULL) res.put("speedKnotsUnits", speedKnotsUnits.name());
		if (speedKilometers != null) res.put("speedKilometers", speedKilometers);
		if (speedKilometersUnits != Unit.NULL) res.put("speedKilometersUnits", speedKilometersUnits.name());
		if (modeIndicator != ModeIndicator.NULL) res.put("modeIndicator", modeIndicator.name());
	}

	public Double getHeadingTrack() {
		return courseOverGround;
	}
	
	public Double getCourseOverGround() {
		return courseOverGround;
	}

	public void setHeadingTrack(Double courseOverGround) {
		this.courseOverGround = courseOverGround;
	}
	
	public void setCourseOverGround(Double courseOverGround) {
		this.courseOverGround = courseOverGround;
	}

	public Reference getTrackReference() {
		return trackReference;
	}

	public void setTrackReference(Reference trackReference) {
		this.trackReference = trackReference;
	}

	public Double getHeadingMagnetic() {
		return courseOverGroundMagnetic;
	}
	
	public Double getCourseOverGroundMagnetic() {
		return courseOverGroundMagnetic;
	}

	public void setCourseOverGroundMagnetic(Double courseOverGroundMagnetic) {
		this.courseOverGroundMagnetic = courseOverGroundMagnetic;
	}
	
	public void setHeadingMagnetic(Double courseOverGroundMagnetic) {
		this.courseOverGroundMagnetic = courseOverGroundMagnetic;
	}

	public Reference getMagneticReference() {
		return magneticReference;
	}

	public void setMagneticReference(Reference magneticReference) {
		this.magneticReference = magneticReference;
	}

	public Double getSpeedKnots() {
		return speedOverGround;
	}

	public void setSpeedKnots(Double speedOverGround) {
		this.speedOverGround = speedOverGround;
	}
	
	public Double getSpeedOverGround() {
		return speedOverGround;
	}

	public void setSpeedOverGround(Double speedOverGround) {
		this.speedOverGround = speedOverGround;
	}

	public Unit getSpeedKnotsUnits() {
		return speedKnotsUnits;
	}

	public void setSpeedKnotsUnits(Unit speedKnotsUnits) {
		this.speedKnotsUnits = speedKnotsUnits;
	}

	public Double getSpeedKilometers() {
		return speedKilometers;
	}

	public void setSpeedKilometers(Double speedKilometers) {
		this.speedKilometers = speedKilometers;
	}

	public Unit getSpeedKilometersUnits() {
		return speedKilometersUnits;
	}

	public void setSpeedKilometersUnits(Unit speedKilometersUnits) {
		this.speedKilometersUnits = speedKilometersUnits;
	}
	
	public ModeIndicator getModeIndicator() {
        return modeIndicator;
    }

    public void setModeIndicator(ModeIndicator modeIndicator) {
        this.modeIndicator = modeIndicator;
    }
}
