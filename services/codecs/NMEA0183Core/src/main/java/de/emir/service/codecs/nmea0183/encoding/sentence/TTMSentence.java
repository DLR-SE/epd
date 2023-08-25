package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import de.emir.service.codecs.nmea0183.encoding.data.Acquisition;
import de.emir.service.codecs.nmea0183.encoding.data.Reference;
import de.emir.service.codecs.nmea0183.encoding.data.TargetNumber;
import de.emir.service.codecs.nmea0183.encoding.data.TargetReference;
import de.emir.service.codecs.nmea0183.encoding.data.TargetStatus;
import de.emir.service.codecs.nmea0183.encoding.data.Time;
import de.emir.service.codecs.nmea0183.encoding.data.Unit;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * TTM - Tracked Target Message<br>
 * <br>
 * 
 * <pre>
 * {@code
 * .      1  2   3   4 5   6   7 8   9  10 11  12 1314       15 16
 *        |  |   |   | |   |   | |   |   | |    | | |         | |
 * $--TTM,xx,x.x,x.x,a,x.x,x.x,a,x.x,x.x,a,c--c,a,a,hhmmss.ss,a*hh
 * }
 * </pre>
 * <ol>
 * <li>Target Number</li>
 * <li>Target Distance</li>
 * <li>Bearing from own ship</li>
 * <li>Bearing Units</li>
 * <li>Target speed</li>
 * <li>Target Course</li>
 * <li>Course Units</li>
 * <li>Distance of closest-point-of-approach</li>
 * <li>Time until closest-point-of-approach "-" means increasing</li>
 * <li>Distance units (K = Kilometers, S = Statute miles)</li>
 * <li>Target name</li>
 * <li>Target Status (T = Target, L = Lost target, Q = Target being acquired)</li>
 * <li>Reference Target (R = Reference target, blank = designated fixed target)</li>
 * <li>Time (UTC)</li>
 * <li>Type acquisition (M = manual, A = automatic)</li>
 * <li>Checksum</li>
 * </ol>
 * 
 */
@XmlRootElement(name = "TTM")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TMMSentence", propOrder = {
    "targetNumber",
    "targetDistance",
    "bearing",
    "bearingUnit",
    "targetSpeed",
    "targetCourse",
    "courseUnit",
    "closestPointOfApproach",
    "timeUntilClosestPoint",
    "distanceUnit",
    "targetLabel",
    "targetStatus",
    "referenceTarget",
    "time",
    "typeAcquisition"
})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class TTMSentence extends Sentence {
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "RA";
	/** Sentence id. */
	public static final String SENTENCE_ID = "TTM";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 15;
	
	/** Target Number */
	@Embedded
	private TargetNumber targetNumber;
	/** Target Distance */
	private Double targetDistance;
	/** Bearing from own ship */
	private Double bearing;
	/** Bearing Units */
	@Enumerated(EnumType.STRING)
	private Reference bearingUnit;
	/** Target speed */
	private Double targetSpeed;
	/** Target Course */
	private Double targetCourse;
	/** Course Units */
	@Enumerated(EnumType.STRING)
	private Reference courseUnit;
	/** Distance of closest-point-of-approach */
	private Double closestPointOfApproach;
	/** Time until closest-point-of-approach "-" means increasing */
	private Double timeUntilClosestPoint;
	/** Distance units (K = Kilometers, S = Statute miles) */
	@Enumerated(EnumType.STRING)
	private Unit distanceUnit;
	/** Target name */
	private String targetLabel;
	/** Target Status (T = Target, L = Lost target, Q = Target being acquired) */
	@Enumerated(EnumType.STRING)
	private TargetStatus targetStatus;
	/** Reference Target (R = Reference target, blank = designated fixed target) */
	@Enumerated(EnumType.STRING)
	private TargetReference referenceTarget;
	/** Time (UTC) */
	@Column(name = "UTC")
	@Embedded
	private Time time;
	/** Type acquisition (M = manual, A = automatic) */
	@Enumerated(EnumType.STRING)
	private Acquisition typeAcquisition;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public TTMSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}
	
	/**
	 * Default constructor for parsing.
	 * @param nmea
	 * Nmea String to be parsed.
	 */
	public TTMSentence(String nmea) {
		super(nmea);
	}

	@Override
	protected void decode() {
		int index = 0;
		targetNumber = ParseUtils.parseTargetNumber(getValue(index++));
		targetDistance = ParseUtils.parseDouble(getValue(index++));
		bearing = ParseUtils.parseDouble(getValue(index++));
		bearingUnit = ParseUtils.parseReference(getValue(index++));
		targetSpeed = ParseUtils.parseDouble(getValue(index++));
		targetCourse = ParseUtils.parseDouble(getValue(index++));
		courseUnit = ParseUtils.parseReference(getValue(index++));
		closestPointOfApproach = ParseUtils.parseDouble(getValue(index++));
		timeUntilClosestPoint = ParseUtils.parseDouble(getValue(index++));
		distanceUnit = ParseUtils.parseUnit(getValue(index++));
		targetLabel = getValue(index++);
		targetStatus = ParseUtils.parseTargetStatus(getValue(index++));
		referenceTarget = ParseUtils.parseTargetReference(getValue(index++));
		time = ParseUtils.parseTime(getValue(index++));
		typeAcquisition = ParseUtils.parseAcquisition(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(targetNumber));
		setValue(index++, ParseUtils.toString(targetDistance, 1));
		setValue(index++, ParseUtils.toString(bearing, 1));
		setValue(index++, ParseUtils.toString(bearingUnit));
		setValue(index++, ParseUtils.toString(targetSpeed, 1));
		setValue(index++, ParseUtils.toString(targetCourse, 1));
		setValue(index++, ParseUtils.toString(courseUnit));
		setValue(index++, ParseUtils.toString(closestPointOfApproach, 1));
		setValue(index++, ParseUtils.toString(timeUntilClosestPoint, 1));
		setValue(index++, ParseUtils.toString(distanceUnit));
		setValue(index++, targetLabel);
		setValue(index++, ParseUtils.toString(targetStatus));
		setValue(index++, ParseUtils.toString(referenceTarget));
		setValue(index++, ParseUtils.toString(time));
		setValue(index++, ParseUtils.toString(typeAcquisition));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (targetNumber != null) res.put("targetNumber", targetNumber.getNumber());
		if (targetDistance != null) res.put("targetDistance", targetDistance);
		if (bearing != null) res.put("bearing", bearing);
		if (bearingUnit != Reference.NULL) res.put("bearingUnit", bearingUnit.name());
		if (targetSpeed != null) res.put("targetSpeed", targetSpeed);
		if (targetCourse != null) res.put("targetCourse", targetCourse);
		if (courseUnit != Reference.NULL) res.put("courseUnit", courseUnit.name());
		if (closestPointOfApproach != null) res.put("closestPointOfApproach", closestPointOfApproach);
		if (timeUntilClosestPoint != null) res.put("timeUntilClosestPoint", timeUntilClosestPoint);
		if (distanceUnit != Unit.NULL) res.put("distanceUnit", distanceUnit.name());
		if (targetLabel != null) res.put("targetLabel", targetLabel);
		if (targetStatus != TargetStatus.NULL) res.put("targetStatus", targetStatus.name());
		if (referenceTarget != null) res.put("referenceTarget", referenceTarget);
		if (time != null) time.addToMap("time", res);
		if (typeAcquisition != Acquisition.NULL) res.put("typeAcquisition", typeAcquisition.name());
	}

	public TargetNumber getTargetNumber() {
		return targetNumber;
	}

	public void setTargetNumber(TargetNumber targetNumber) {
		this.targetNumber = targetNumber;
	}

	public Double getTargetDistance() {
		return targetDistance;
	}

	public void setTargetDistance(Double targetDistance) {
		this.targetDistance = targetDistance;
	}

	public Double getBearing() {
		return bearing;
	}

	public void setBearing(Double bearing) {
		this.bearing = bearing;
	}

	public Reference getBearingUnit() {
		return bearingUnit;
	}

	public void setBearingUnit(Reference bearingUnit) {
		this.bearingUnit = bearingUnit;
	}

	public Double getTargetSpeed() {
		return targetSpeed;
	}

	public void setTargetSpeed(Double targetSpeed) {
		this.targetSpeed = targetSpeed;
	}

	public Double getTargetCourse() {
		return targetCourse;
	}

	public void setTargetCourse(Double targetCourse) {
		this.targetCourse = targetCourse;
	}

	public Reference getCourseUnit() {
		return courseUnit;
	}

	public void setCourseUnit(Reference courseUnit) {
		this.courseUnit = courseUnit;
	}

	public Double getClosestPointOfApproach() {
		return closestPointOfApproach;
	}

	public void setClosestPointOfApproach(Double closestPointOfApproach) {
		this.closestPointOfApproach = closestPointOfApproach;
	}

	public Double getTimeUntilClosestPoint() {
		return timeUntilClosestPoint;
	}

	public void setTimeUntilClosestPoint(Double timeUntilClosestPoint) {
		this.timeUntilClosestPoint = timeUntilClosestPoint;
	}

	public Unit getDistanceUnit() {
		return distanceUnit;
	}

	public void setDistanceUnit(Unit distanceUnit) {
		this.distanceUnit = distanceUnit;
	}

	public String getTargetLabel() {
		return targetLabel;
	}

	public void setTargetLabel(String targetLabel) {
		this.targetLabel = targetLabel;
	}

	public TargetStatus getTargetStatus() {
		return targetStatus;
	}

	public void setTargetStatus(TargetStatus targetStatus) {
		this.targetStatus = targetStatus;
	}

	public TargetReference getReferenceTarget() {
		return referenceTarget;
	}

	public void setReferenceTarget(TargetReference referenceTarget) {
		this.referenceTarget = referenceTarget;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Acquisition getTypeAcquisition() {
		return typeAcquisition;
	}

	public void setTypeAcquisition(Acquisition typeAcquisition) {
		this.typeAcquisition = typeAcquisition;
	}
}
