package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * This is a NMEA0183 PRC sentence. Implementation details can be found within
 * nmea.py on github
 * 
 * Propulsion remote control status
 * 
 * ("Lever demand position", "lever_pos"),
 * ("Lever demand status", "lever_status"),
 * ("RPM demand value", "rpm"),
 * ("RPM mode indicator", "rpm_mode"),
 * ("Pitch demand value", "pitch"),
 * ("Pitch mode indicator", "pitch_mode"),
 * ("Operating location indicator", "op_loc"),
 * ("Number of engine or propeller shaft", "eng_num")                     
 * 
 * @author cdenker
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class PRCSentence extends Sentence {

	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	// FIXME: check DEFAULT_TALKER
	public static final String DEFAULT_TALKER = "AG";
	/** Sentence id. */
	public static final String SENTENCE_ID = "PRC";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 8;

	private Double leverDemandPosition;
	private String leverDemandStatus;
	private Double rpmDemandValue;
	private String rpmModeIndicator;
	private Double pitchDemandValue;
	private String pitchModeIndicator;
	private String operatingLocationIndicator;
	private Double numberOfEngineOrPropellerShaft;

	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public PRCSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public PRCSentence(String nmea) {
		super(nmea);
	}

	@Override
	protected void decode() {
		int index = 0;

		leverDemandPosition = ParseUtils.parseDouble(getValue(index++));
		leverDemandStatus = getValue(index++);
		rpmDemandValue = ParseUtils.parseDouble(getValue(index++));
		rpmModeIndicator = getValue(index++);
		pitchDemandValue = ParseUtils.parseDouble(getValue(index++));
		pitchModeIndicator = getValue(index++);
		operatingLocationIndicator = getValue(index++);
		numberOfEngineOrPropellerShaft = ParseUtils.parseDouble(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;

		setValue(index++, ParseUtils.toString(leverDemandPosition, 1));
		setValue(index++, leverDemandStatus);
		setValue(index++, ParseUtils.toString(rpmDemandValue, 1));
		setValue(index++, rpmModeIndicator);
		setValue(index++, ParseUtils.toString(pitchDemandValue, 1));
		setValue(index++, pitchModeIndicator);
		setValue(index++, operatingLocationIndicator);
		setValue(index++, ParseUtils.toString(numberOfEngineOrPropellerShaft, 1));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {

		if (leverDemandPosition != null)
			res.put("leverDemandPosition", leverDemandPosition);
		if (leverDemandStatus != null)
			res.put("leverDemandStatus", leverDemandStatus);
		if (rpmDemandValue != null)
			res.put("rpmDemandValue", rpmDemandValue);
		if (rpmModeIndicator != null)
			res.put("rpmModeIndicator", rpmModeIndicator);
		if (pitchDemandValue != null)
			res.put("pitchDemandValue", pitchDemandValue);
		if (pitchModeIndicator != null)
			res.put("pitchModeIndicator", pitchModeIndicator);
		if (operatingLocationIndicator != null)
			res.put("operatingLocationIndicator", operatingLocationIndicator);
		if (numberOfEngineOrPropellerShaft != null)
			res.put("numberOfEngineOrPropellerShaft", numberOfEngineOrPropellerShaft);
	}

	public Double getLeverDemandPosition() {
		return leverDemandPosition;
	}

	public void setLeverDemandPosition(Double leverDemandPosition) {
		this.leverDemandPosition = leverDemandPosition;
	}

	public String getLeverDemandStatus() {
		return leverDemandStatus;
	}

	public void setLeverDemandStatus(String leverDemandStatus) {
		this.leverDemandStatus = leverDemandStatus;
	}

	public Double getRpmDemandValue() {
		return rpmDemandValue;
	}

	public void setRpmDemandValue(Double rpmDemandValue) {
		this.rpmDemandValue = rpmDemandValue;
	}

	public String getRpmModeIndicator() {
		return rpmModeIndicator;
	}

	public void setRpmModeIndicator(String rpmModeIndicator) {
		this.rpmModeIndicator = rpmModeIndicator;
	}

	public Double getPitchDemandValue() {
		return pitchDemandValue;
	}

	public void setPitchDemandValue(Double pitchDemandValue) {
		this.pitchDemandValue = pitchDemandValue;
	}

	public String getPitchModeIndicator() {
		return pitchModeIndicator;
	}

	public void setPitchModeIndicator(String pitchModeIndicator) {
		this.pitchModeIndicator = pitchModeIndicator;
	}

	public String getOperatingLocationIndicator() {
		return operatingLocationIndicator;
	}

	public void setOperatingLocationIndicator(String operatingLocationIndicator) {
		this.operatingLocationIndicator = operatingLocationIndicator;
	}

	public Double getNumberOfEngineOrPropellerShaft() {
		return numberOfEngineOrPropellerShaft;
	}

	public void setNumberOfEngineOrPropellerShaft(Double numberOfEngineOrPropellerShaft) {
		this.numberOfEngineOrPropellerShaft = numberOfEngineOrPropellerShaft;
	}

	
}
