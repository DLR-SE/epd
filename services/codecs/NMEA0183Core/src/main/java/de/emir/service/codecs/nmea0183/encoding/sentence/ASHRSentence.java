package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.data.Time;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * ASHR - Aiding Ship Heading Rotation - RT300 proprietary roll and pitch sentence - http://www.catb.org/gpsd/NMEA.html#_pashr_rt300_proprietary_roll_and_pitch_sentence<br>
 * <br>
 * 
 * <pre>
 * {@code
 *        1          2      3 4      5      6      7     8     9    10 11 12 
 *        |          |      | |      |      |      |     |     |     | |  |
 * $--SHR,hhmmss.sss,hhh.hh,T,rrr.rr,ppp.pp,xxx.xx,a.aaa,b.bbb,c.ccc,d,e*hh
 * }
 * </pre>
 * <ol>
 * <li>hhmmss.sss - UTC time</li>
 * <li>hhh.hh - Heading in degrees</li>
 * <li>T - flag to indicate that the Heading is True Heading (i.e. to True North)</li>
 * <li>rrr.rr - Roll Angle in degrees</li>
 * <li>ppp.pp - Pitch Angle in degrees</li>
 * <li>xxx.xx - Heave</li>
 * <li>a.aaa - Roll Angle Accuracy Estimate (Stdev) in degrees</li>
 * <li>b.bbb - Pitch Angle Accuracy Estimate (Stdev) in degrees</li>
 * <li>c.ccc - Heading Angle Accuracy Estimate (Stdev) in degrees</li>
 * <li>d - Aiding Status</li>
 * <li>e - IMU Status</li>
 * <li>Checksum</li>
 * </ol>
 * 
 * @author jbmzh <jan.meyer.zu.holte@uni-oldenburg.de>
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ASHRSentence extends Sentence {
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "P";
	/** Sentence id. */
	public static final String SENTENCE_ID = "ASHR";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 12;
	
	/** hhmmss.sss - UTC time */
	private Time time;
	/** hhh.hh - Heading in degrees */
	private Double heading;
	/** T - flag to indicate that the Heading is True Heading (i.e. to True North) */
	private String trueHeading;
	/** rrr.rr - Roll Angle in degrees */
	private Double rollAngle;
	/** ppp.pp - Pitch Angle in degrees */
	private Double pitchAngle;
	/** xxx.xx - Heave */
	private Double heave;
	/** a.aaa - Roll Angle Accuracy Estimate (Stdev) in degrees */
	private Double rollAccuracy;
	/** b.bbb - Pitch Angle Accuracy Estimate (Stdev) in degrees */
	private Double pitchAccuracy;
	/** c.ccc - Heading Angle Accuracy Estimate (Stdev) in degrees */
	private Double headingAccuracy;
	/** d - Aiding Status */
	private String aidingStatus;
	/** e - IMU Status */
	private String IMUStatus;

	/**
	 * Default constructor for parsing.
	 * @param nmea
	 * Nmea String to be parsed.
	 */
	public ASHRSentence(String nmea) {
		super(nmea);
	}
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public ASHRSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	@Override
	protected void decode() {
		int index = 0;
		setTime(ParseUtils.parseTime(getValue(index++)));
		setHeading(ParseUtils.parseDouble(getValue(index++)));
		setTrueHeading(getValue(index++));
		setRollAngle(ParseUtils.parseDouble(getValue(index++)));
		setPitchAngle(ParseUtils.parseDouble(getValue(index++)));
		setHeave(ParseUtils.parseDouble(getValue(index++)));
		setRollAccuracy(ParseUtils.parseDouble(getValue(index++)));
		setPitchAccuracy(ParseUtils.parseDouble(getValue(index++)));
		setHeadingAccuracy(ParseUtils.parseDouble(getValue(index++)));
		setAidingStatus(getValue(index++));
		setIMUStatus(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(time));
		setValue(index++, ParseUtils.toString(heading));
		setValue(index++, trueHeading);
		setValue(index++, ParseUtils.toString(rollAngle));
		setValue(index++, ParseUtils.toString(pitchAngle));
		setValue(index++, ParseUtils.toString(heave, 2));
		setValue(index++, ParseUtils.toString(rollAccuracy));
		setValue(index++, ParseUtils.toString(pitchAccuracy));
		setValue(index++, ParseUtils.toString(headingAccuracy));
		setValue(index++, aidingStatus);
		setValue(index++, IMUStatus);
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (time != null) res.put("time", time);
		// Identifier is hdg because elsewise it causes conflicts with OSD sentence heading
		if (heading != null) res.put("hdg", heading); 
		if (trueHeading != null) res.put("trueHeading", trueHeading);
		if (rollAngle != null) res.put("rollAngle", rollAngle);
		if (pitchAngle != null) res.put("pitchAngle", pitchAngle);
		if (heave != null) res.put("heave", heave);
		if (rollAccuracy != null) res.put("rollAccuracy", rollAccuracy);
		if (pitchAccuracy != null) res.put("pitchAccuracy", pitchAccuracy);
		if (headingAccuracy != null) res.put("headingAccuracy", headingAccuracy);
		if (aidingStatus != null) res.put("aidingStatus", aidingStatus);
		if (IMUStatus != null) res.put("IMUStatus", IMUStatus);
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Double getHeading() {
		return heading;
	}

	public void setHeading(Double heading) {
		this.heading = heading;
	}

	public String getTrueHeading() {
		return trueHeading;
	}

	public void setTrueHeading(String trueHeading) {
		this.trueHeading = trueHeading;
	}

	public double getRollAngle() {
		return rollAngle;
	}

	public void setRollAngle(double rollAngle) {
		this.rollAngle = rollAngle;
	}

	public Double getPitchAngle() {
		return pitchAngle;
	}

	public void setPitchAngle(Double pitchAngle) {
		this.pitchAngle = pitchAngle;
	}

	public Double getHeave() {
		return heave;
	}

	public void setHeave(Double heave) {
		this.heave = heave;
	}

	public Double getRollAccuracy() {
		return rollAccuracy;
	}

	public void setRollAccuracy(Double rollAccuracy) {
		this.rollAccuracy = rollAccuracy;
	}

	public Double getPitchAccuracy() {
		return pitchAccuracy;
	}

	public void setPitchAccuracy(Double pitchAccuracy) {
		this.pitchAccuracy = pitchAccuracy;
	}

	public Double getHeadingAccuracy() {
		return headingAccuracy;
	}

	public void setHeadingAccuracy(Double headingAccuracy) {
		this.headingAccuracy = headingAccuracy;
	}

	public String getAidingStatus() {
		return aidingStatus;
	}

	public void setAidingStatus(String aidingStatus) {
		this.aidingStatus = aidingStatus;
	}

	public String getIMUStatus() {
		return IMUStatus;
	}

	public void setIMUStatus(String iMUStatus) {
		IMUStatus = iMUStatus;
	}
}
