package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * 
 * This is a NMEA0183 HTD sentence. 
 * Implementation details can be found within
 * the NMEA0183 specification, e.g. at
 * http://caxapa.ru/thumbs/214299/NMEA0183_.pdf
 * 
 * @author cdenker
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class HTDSentence extends Sentence {

	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	// FIXME: check DEFAULT_TALKER
	public static final String DEFAULT_TALKER = "AG";
	/** Sentence id. */
	public static final String SENTENCE_ID = "HTD";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 17;

	private String override;
	private Double commandedRudderAngle;
	private String commandedRudderDirection;
	private String selectedSteeringMode;
	private String turnMode;
	private Double commandedRudderLimitDeg;
	private Double commandedOffHeadingLimitDeg;
	private Double commandedRadiusOfTurnForHeadingChangesMi;
	private Double commandedRotForHeadingChnagesDegMin;
	private Double commandedHeadingToSteerDeg;
	private Double commandedOffTrackLimitMi;
	private Double commandedTrackDeg;
	private String headingReferenceInUseTM;
	private String rudderStatus;
	private String offHeadingStatus;
	private String offTrackStatus;
	private Double vesselHeadingDeg;

	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public HTDSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public HTDSentence(String nmea) {
		super(nmea);
	}

	@Override
	protected void decode() {
		int index = 0;

		override = getValue(index++);
		commandedRudderAngle = ParseUtils.parseDouble(getValue(index++));
		commandedRudderDirection = getValue(index++);
		selectedSteeringMode = getValue(index++);
		turnMode = getValue(index++);
		commandedRudderLimitDeg = ParseUtils.parseDouble(getValue(index++));
		commandedOffHeadingLimitDeg = ParseUtils.parseDouble(getValue(index++));
		commandedRadiusOfTurnForHeadingChangesMi = ParseUtils.parseDouble(getValue(index++));
		commandedRotForHeadingChnagesDegMin = ParseUtils.parseDouble(getValue(index++));
		commandedHeadingToSteerDeg = ParseUtils.parseDouble(getValue(index++));
		commandedOffTrackLimitMi = ParseUtils.parseDouble(getValue(index++));
		commandedTrackDeg = ParseUtils.parseDouble(getValue(index++));
		headingReferenceInUseTM = getValue(index++);
		rudderStatus = getValue(index++);
		offHeadingStatus = getValue(index++);
		offTrackStatus = getValue(index++);
		vesselHeadingDeg = ParseUtils.parseDouble(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;

		setValue(index++, override);
		setValue(index++, ParseUtils.toString(commandedRudderAngle, 1));
		setValue(index++, commandedRudderDirection);
		setValue(index++, selectedSteeringMode);
		setValue(index++, turnMode);
		setValue(index++, ParseUtils.toString(commandedRudderLimitDeg, 1));
		setValue(index++, ParseUtils.toString(commandedOffHeadingLimitDeg, 1));
		setValue(index++, ParseUtils.toString(commandedRadiusOfTurnForHeadingChangesMi, 1));
		setValue(index++, ParseUtils.toString(commandedRotForHeadingChnagesDegMin, 1));
		setValue(index++, ParseUtils.toString(commandedHeadingToSteerDeg, 1));
		setValue(index++, ParseUtils.toString(commandedOffTrackLimitMi, 1));
		setValue(index++, ParseUtils.toString(commandedTrackDeg, 1));
		setValue(index++, headingReferenceInUseTM);
		setValue(index++, rudderStatus);
		setValue(index++, offHeadingStatus);
		setValue(index++, offTrackStatus);
		setValue(index++, ParseUtils.toString(vesselHeadingDeg, 1));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {

		if (override != null)
			res.put("override", override);
		if (commandedRudderAngle != null)
			res.put("commandedRudderAngle", commandedRudderAngle);
		if (commandedRudderDirection != null)
			res.put("commandedRudderDirection", commandedRudderDirection);
		if (selectedSteeringMode != null)
			res.put("selectedSteeringMode", selectedSteeringMode);
		if (turnMode != null)
			res.put("turnMode", turnMode);
		if (commandedRudderLimitDeg != null)
			res.put("commandedRudderLimitDeg", commandedRudderLimitDeg);
		if (commandedOffHeadingLimitDeg != null)
			res.put("commandedOffHeadingLimitDeg", commandedOffHeadingLimitDeg);
		if (commandedRadiusOfTurnForHeadingChangesMi != null)
			res.put("commandedRadiusOfTurnForHeadingChangesMi", commandedRadiusOfTurnForHeadingChangesMi);
		if (commandedRotForHeadingChnagesDegMin != null)
			res.put("commandedRotForHeadingChnagesDegMin", commandedRotForHeadingChnagesDegMin);
		if (commandedHeadingToSteerDeg != null)
			res.put("commandedHeadingToSteerDeg", commandedHeadingToSteerDeg);
		if (commandedOffTrackLimitMi != null)
			res.put("commandedOffTrackLimitMi", commandedOffTrackLimitMi);
		if (commandedTrackDeg != null)
			res.put("commandedTrackDeg", commandedTrackDeg);
		if (headingReferenceInUseTM != null)
			res.put("headingReferenceInUseTM", headingReferenceInUseTM);
		if (rudderStatus != null)
			res.put("rudderStatus", rudderStatus);
		if (offHeadingStatus != null)
			res.put("offHeadingStatus", offHeadingStatus);
		if (offTrackStatus != null)
			res.put("offTrackStatus", offTrackStatus);
		if (vesselHeadingDeg != null)
			res.put("vesselHeadingDeg", vesselHeadingDeg);
	}

	public String getOverride() {
		return override;
	}

	public void setOverride(String override) {
		this.override = override;
	}

	public Double getCommandedRudderAngle() {
		return commandedRudderAngle;
	}

	public void setCommandedRudderAngle(Double commandedRudderAngle) {
		this.commandedRudderAngle = commandedRudderAngle;
	}

	public String getCommandedRudderDirection() {
		return commandedRudderDirection;
	}

	public void setCommandedRudderDirection(String commandedRudderDirection) {
		this.commandedRudderDirection = commandedRudderDirection;
	}

	public String getSelectedSteeringMode() {
		return selectedSteeringMode;
	}

	public void setSelectedSteeringMode(String selectedSteeringMode) {
		this.selectedSteeringMode = selectedSteeringMode;
	}

	public String getTurnMode() {
		return turnMode;
	}

	public void setTurnMode(String turnMode) {
		this.turnMode = turnMode;
	}

	public Double getCommandedRudderLimitDeg() {
		return commandedRudderLimitDeg;
	}

	public void setCommandedRudderLimitDeg(Double commandedRudderLimitDeg) {
		this.commandedRudderLimitDeg = commandedRudderLimitDeg;
	}

	public Double getCommandedOffHeadingLimitDeg() {
		return commandedOffHeadingLimitDeg;
	}

	public void setCommandedOffHeadingLimitDeg(Double commandedOffHeadingLimitDeg) {
		this.commandedOffHeadingLimitDeg = commandedOffHeadingLimitDeg;
	}

	public Double getCommandedRadiusOfTurnForHeadingChangesMi() {
		return commandedRadiusOfTurnForHeadingChangesMi;
	}

	public void setCommandedRadiusOfTurnForHeadingChangesMi(Double commandedRadiusOfTurnForHeadingChangesMi) {
		this.commandedRadiusOfTurnForHeadingChangesMi = commandedRadiusOfTurnForHeadingChangesMi;
	}

	public Double getCommandedRotForHeadingChnagesDegMin() {
		return commandedRotForHeadingChnagesDegMin;
	}

	public void setCommandedRotForHeadingChnagesDegMin(Double commandedRotForHeadingChnagesDegMin) {
		this.commandedRotForHeadingChnagesDegMin = commandedRotForHeadingChnagesDegMin;
	}

	public Double getCommandedHeadingToSteerDeg() {
		return commandedHeadingToSteerDeg;
	}

	public void setCommandedHeadingToSteerDeg(Double commandedHeadingToSteerDeg) {
		this.commandedHeadingToSteerDeg = commandedHeadingToSteerDeg;
	}

	public Double getCommandedOffTrackLimitMi() {
		return commandedOffTrackLimitMi;
	}

	public void setCommandedOffTrackLimitMi(Double commandedOffTrackLimitMi) {
		this.commandedOffTrackLimitMi = commandedOffTrackLimitMi;
	}

	public Double getCommandedTrackDeg() {
		return commandedTrackDeg;
	}

	public void setCommandedTrackDeg(Double commandedTrackDeg) {
		this.commandedTrackDeg = commandedTrackDeg;
	}

	public String getHeadingReferenceInUseTM() {
		return headingReferenceInUseTM;
	}

	public void setHeadingReferenceInUseTM(String headingReferenceInUseTM) {
		this.headingReferenceInUseTM = headingReferenceInUseTM;
	}

	public String getRudderStatus() {
		return rudderStatus;
	}

	public void setRudderStatus(String rudderStatus) {
		this.rudderStatus = rudderStatus;
	}

	public String getOffHeadingStatus() {
		return offHeadingStatus;
	}

	public void setOffHeadingStatus(String offHeadingStatus) {
		this.offHeadingStatus = offHeadingStatus;
	}

	public String getOffTrackStatus() {
		return offTrackStatus;
	}

	public void setOffTrackStatus(String offTrackStatus) {
		this.offTrackStatus = offTrackStatus;
	}

	public Double getVesselHeadingDeg() {
		return vesselHeadingDeg;
	}

	public void setVesselHeadingDeg(Double vesselHeadingDeg) {
		this.vesselHeadingDeg = vesselHeadingDeg;
	}

	
}
