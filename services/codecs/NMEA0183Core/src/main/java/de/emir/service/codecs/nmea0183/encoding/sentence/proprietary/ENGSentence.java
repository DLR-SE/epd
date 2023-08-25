package de.emir.service.codecs.nmea0183.encoding.sentence.proprietary;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.data.Status;
import de.emir.service.codecs.nmea0183.encoding.sentence.Sentence;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * ENG - Engine Commanded and Sensor Data
 * <br>
 * Introduced by the MTCAS Project (https://www.offis.de/offis/projekt/mtcas.html) 
 * 
 * <pre>
 * {@code
 * .      1 2 3   4    5    6   7  8  9  10
 *        | | |   |    |    |   |  |  |  |
 * $-ENG,x,A,x.x,xxxx,xxxx,xxx,xxx,a,x.x*hh
 * }
 * </pre>
 * <ol>
 * <li>engineID</li>
 * <li>Status, A means data is valid, V means warning, null means invalid</li>
 * <li>engine order in % (0-100)</li>
 * <li>commanded RPM</li>
 * <li>current RPM</li>
 * <li>commanded propeller pitch % (0->100)</li>
 * <li>current propeller pitch % (0->100)</li>
 * <li> load Limit Indicator 0 (off) or 1 (on)</li>
 * <li> power limit,  % (0->100) </li>
 * <li>Checksum</li>
 * </ol>
 * 
 * @author sschweigert
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ENGSentence extends Sentence{
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "P";
	/** Sentence id. */
	public static final String SENTENCE_ID = "ENG";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 9;
	
	/** unique identifier of the rudder, if no unique identifier is available increment from port to starboard. */
	private Integer engineId;
	/** Status, A means data is valid. V means data has warnings, null means data is invalid */
	@Enumerated(EnumType.STRING)
	private Status sbStatus;
	/** engine order in % (0-100) */
	private Integer engineOrder; 
	/** commanded RPM */
	private Integer commandedRPM;
	/** current RPM */
	private Integer currentRPM;
	
	/** commanded propeller pitch % (0->100)*/
	private Integer commandedPitch;
	/** current propeller pitch % (0->100) */
	private Integer currentPitch;
	/** load Limit Indicator 0 (off) or 1 (on) */
	private Integer loadIndicator;
	/** power limit,  % (0->100)*/
	private Integer powerLimit;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public ENGSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public ENGSentence(String nmea) {
		super(nmea);
	}
	
	@Override
	protected void decode() {
		int index = 0;
		engineId = ParseUtils.parseInteger(getValue(index++));
		sbStatus = ParseUtils.parseStatus(getValue(index++));
		engineOrder = ParseUtils.parseInteger(getValue(index++));
		commandedRPM = ParseUtils.parseInteger(getValue(index++));
		currentRPM = ParseUtils.parseInteger(getValue(index++));
		commandedPitch = ParseUtils.parseInteger(getValue(index++));
		currentPitch = ParseUtils.parseInteger(getValue(index++));
		loadIndicator = ParseUtils.parseInteger(getValue(index++));
		powerLimit = ParseUtils.parseInteger(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(engineId));
		setValue(index++, ParseUtils.toString(sbStatus));
		setValue(index++, ParseUtils.toString(engineOrder));
		setValue(index++, ParseUtils.toString(commandedRPM));
		setValue(index++, ParseUtils.toString(currentRPM));
		setValue(index++, ParseUtils.toString(commandedPitch));
		setValue(index++, ParseUtils.toString(currentPitch));
		setValue(index++, ParseUtils.toString(loadIndicator));
		setValue(index++, ParseUtils.toString(powerLimit));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (engineId != null) res.put("engineId", engineId);
		if (sbStatus != Status.NULL) res.put("sbStatus", sbStatus.name());
		if (engineOrder != null) res.put("engineOrder", engineOrder);
		if (commandedRPM != null) res.put("commandedRPM", commandedRPM);
		if (currentRPM != null) res.put("currentRPM", currentRPM);
		if (commandedPitch != null) res.put("commandedPitch", commandedPitch);
		if (currentPitch != null) res.put("currentPitch", currentPitch);
		if (loadIndicator != null) res.put("loadIndicator", loadIndicator);
		if (powerLimit != null) res.put("powerLimit", powerLimit);
	}

	
	
	

	public Integer getEngineId() {
		return engineId;
	}

	public void setEngineId(Integer engineId) {
		this.engineId = engineId;
	}

	public Status getStatus(){
		return sbStatus;
	}
	
	public void setStatus(Status sbStatus){
		this.sbStatus = sbStatus;
	}

	public Integer getEngineOrder() {
		return engineOrder;
	}

	public void setEngineOrder(Integer engineOrder) {
		this.engineOrder = engineOrder;
	}

	public Integer getCommandedRPM() {
		return commandedRPM;
	}

	public void setCommandedRPM(Integer commandedRPM) {
		this.commandedRPM = commandedRPM;
	}

	public Integer getCurrentRPM() {
		return currentRPM;
	}

	public void setCurrentRPM(Integer currentRPM) {
		this.currentRPM = currentRPM;
	}

	public Integer getCommandedPitch() {
		return commandedPitch;
	}

	public void setCommandedPitch(Integer commandedPitch) {
		this.commandedPitch = commandedPitch;
	}

	public Integer getCurrentPitch() {
		return currentPitch;
	}

	public void setCurrentPitch(Integer currentPitch) {
		this.currentPitch = currentPitch;
	}

	public Integer getLoadIndicator() {
		return loadIndicator;
	}

	public void setLoadIndicator(Integer loadIndicator) {
		this.loadIndicator = loadIndicator;
	}

	public Integer getPowerLimit() {
		return powerLimit;
	}

	public void setPowerLimit(Integer powerLimit) {
		this.powerLimit = powerLimit;
	}

	
	
	
}
