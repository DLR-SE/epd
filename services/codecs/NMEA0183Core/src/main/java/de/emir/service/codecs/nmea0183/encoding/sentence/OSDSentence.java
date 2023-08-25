package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.data.Reference;
import de.emir.service.codecs.nmea0183.encoding.data.Status;
import de.emir.service.codecs.nmea0183.encoding.data.Unit;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * OSD - Own Ship Data<br>
 * <br>
 * 
 * <pre>
 * {@code
 *.      1   2 3   4 5   6 7   8   9 10
 *       |   | |   | |   | |   |   | |
 *$--OSD,x.x,A,x.x,a,x.x,a,x.x,x.x,a*hh
 * }
 * </pre>
 * <ol>
 * <li>Heading, degrees true</li>
 * <li>Status, A = Data Valid </li>
 * <li>Vessel Course, degrees True </li>
 * <li>Course Reference </li>
 * <li>Vessel Speed </li>
 * <li>Speed Reference </li>
 * <li>Vessel Set, degrees True  </li>
 * <li>Vessel drift (speed)  </li>
 * <li>Speed Units  </li>
 * <li>Checksum </li>
 * </ol>
 * 
 * @author jbmzh <jan.meyer.zu.holte@uni-oldenburg.de>
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class OSDSentence extends Sentence {
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "GP";
	/** Sentence id. */
	public static final String SENTENCE_ID = "OSD";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 10;
	
	/** Heading  */
	private Double heading;
	/** Status, A = Data Valid */
	@Enumerated(EnumType.STRING)
	private Status status;
	/** Vessel Course */
	private Double course;
	/** Course Reference  */
	@Enumerated(EnumType.STRING)
	private Reference courseReference;
	/** Vessel Speed */
	private Double vesselSpeed;
	/** Speed Reference */
	@Enumerated(EnumType.STRING)
	private Reference vesselSpeedReference;
	/** Vessel Set */
	private Double vesselSet;
	/** Vessel drift (speed)  */
	private Double vesselDrift;
	/** Speed Units  */
	@Enumerated(EnumType.STRING)
	private Unit speedKnotsUnits;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public OSDSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}
	
	/**
	 * Default constructor for parsing.
	 * @param nmea
	 * Nmea String to be parsed.
	 */
	public OSDSentence(String nmea) {
		super(nmea);
	}

	@Override
	protected void decode() {
		int index = 0;
		setHeading(ParseUtils.parseDouble(getValue(index++)));
		setStatus(ParseUtils.parseStatus(getValue(index++)));
		setCourse(ParseUtils.parseDouble(getValue(index++)));
		setCourseReference(ParseUtils.parseReference(getValue(index++)));
		setVesselSpeed(ParseUtils.parseDouble(getValue(index++)));
		setVesselSpeedReference(ParseUtils.parseReference(getValue(index++)));
		setVesselSet(ParseUtils.parseDouble(getValue(index++)));
		setVesselDrift(ParseUtils.parseDouble(getValue(index++)));
		setSpeedKnotsUnits(ParseUtils.parseUnit(getValue(index++)));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(getHeading(), 1));
		setValue(index++, ParseUtils.toString(getStatus()));
		setValue(index++, ParseUtils.toString(getCourse(), 1));
		setValue(index++, ParseUtils.toString(getCourseReference()));
		setValue(index++, ParseUtils.toString(getVesselSpeed(), 1));
		setValue(index++, ParseUtils.toString(getVesselSpeedReference()));
		setValue(index++, ParseUtils.toString(getVesselSet(), 1));
		setValue(index++, ParseUtils.toString(getVesselDrift(), 1));
		setValue(index++, ParseUtils.toString(getSpeedKnotsUnits()));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (getHeading() != null) res.put("heading", getHeading());
		if (getStatus() != null) res.put("status", getStatus());
		if (getCourse() != null) res.put("course", getCourse());
		if (getCourseReference() != null) res.put("courseReference", getCourseReference());
		if (getVesselSpeed() != null) res.put("vesselSpeed", getVesselSpeed());
		if (getVesselSpeedReference() != null) res.put("vesselSpeedReference", getVesselSpeedReference());
		if (getVesselSet() != null) res.put("vesselSet", getVesselSet());
		if (getVesselDrift() != null) res.put("vesselDrift", getVesselDrift());
		if (getSpeedKnotsUnits() != null) res.put("speedKnotsUnits", getSpeedKnotsUnits());
	}

	public Double getHeading() {
		return heading;
	}

	public void setHeading(Double heading) {
		this.heading = heading;
	}

	public Double getCourse() {
		return course;
	}

	public void setCourse(Double course) {
		this.course = course;
	}

	public Reference getCourseReference() {
		return courseReference;
	}

	public void setCourseReference(Reference courseReference) {
		this.courseReference = courseReference;
	}

	public Double getVesselSpeed() {
		return vesselSpeed;
	}

	public void setVesselSpeed(Double vesselSpeed) {
		this.vesselSpeed = vesselSpeed;
	}

	public Double getVesselSet() {
		return vesselSet;
	}

	public void setVesselSet(Double vesselSet) {
		this.vesselSet = vesselSet;
	}

	public Double getVesselDrift() {
		return vesselDrift;
	}

	public void setVesselDrift(Double vesselDrift) {
		this.vesselDrift = vesselDrift;
	}

	public Unit getSpeedKnotsUnits() {
		return speedKnotsUnits;
	}

	public void setSpeedKnotsUnits(Unit speedKnotsUnits) {
		this.speedKnotsUnits = speedKnotsUnits;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Reference getVesselSpeedReference() {
		return vesselSpeedReference;
	}

	public void setVesselSpeedReference(Reference vesselSpeedReference) {
		this.vesselSpeedReference = vesselSpeedReference;
	}

}
