package de.emir.service.codecs.nmea0183.encoding.sentence.proprietary;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.sentence.Sentence;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * 
 * This is a NMEA0183 TRC sentence. Implementation details can be found within
 * nmea.py on github 
 * 
 * Thruster control data
 * ("Number of thruster, bow or stern ", "thruster_num"),
            ("RPM demand value", "rpm_demand"),
            ("RPM mode indicator", "rpm_mode"),
            ("Pitch demand value", "pitch_demand"),
            ("Pitch mode indicator", "pitch_mode"),
            ("Azimuth demand", "azi_demand"),
            ("Operating location indicator", "op_loc"),
            ("Sentence status flag", "sen_status")) 
 * 
 * @author cdenker
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class TRCSentence extends Sentence {

	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	// FIXME: check DEFAULT_TALKER
	public static final String DEFAULT_TALKER = "AG";
	/** Sentence id. */
	public static final String SENTENCE_ID = "TRC";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 8;

	private Double numberofThrusterBowToStern;
	private Double rpmDemandValue;
	private String rpmModeIndicator;
	private Double pitchDemandValue;
	private String pitchModeIndicator;
	private Double azimuthDemand;
	private String operatingLocationIndicator;
	private String sentenceStatusFlag;

	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public TRCSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public TRCSentence(String nmea) {
		super(nmea);
	}

	@Override
	protected void decode() {
		int index = 0;

		numberofThrusterBowToStern = ParseUtils.parseDouble(getValue(index++));
		rpmDemandValue = ParseUtils.parseDouble(getValue(index++));
		rpmModeIndicator = getValue(index++);
		pitchDemandValue = ParseUtils.parseDouble(getValue(index++));
		pitchModeIndicator = getValue(index++);
		azimuthDemand = ParseUtils.parseDouble(getValue(index++));
		operatingLocationIndicator = getValue(index++);
		sentenceStatusFlag = getValue(index++);
	}

	@Override
	protected void encode() {
		int index = 0;

		setValue(index++, ParseUtils.toString(numberofThrusterBowToStern, 1));
		setValue(index++, ParseUtils.toString(rpmDemandValue, 1));
		setValue(index++, rpmModeIndicator);
		setValue(index++, ParseUtils.toString(pitchDemandValue, 1));
		setValue(index++, pitchModeIndicator);
		setValue(index++, ParseUtils.toString(azimuthDemand, 1));
		setValue(index++, operatingLocationIndicator);
		setValue(index++, sentenceStatusFlag);
	}

	@Override
	protected void fillMap(Map<String, Object> res) {

		if (numberofThrusterBowToStern != null)
			res.put("numberofThrusterBowToStern", numberofThrusterBowToStern);
		if (rpmDemandValue != null)
			res.put("rpmDemandValue", rpmDemandValue);
		if (rpmModeIndicator != null)
			res.put("rpmModeIndicator", rpmModeIndicator);
		if (pitchDemandValue != null)
			res.put("pitchDemandValue", pitchDemandValue);
		if (pitchModeIndicator != null)
			res.put("pitchModeIndicator", pitchModeIndicator);
		if (azimuthDemand != null)
			res.put("azimuthDemand", azimuthDemand);
		if (operatingLocationIndicator != null)
			res.put("operatingLocationIndicator", operatingLocationIndicator);
		if (sentenceStatusFlag != null)
			res.put("sentenceStatusFlag", sentenceStatusFlag);
	}

	public Double getNumberofThrusterBowToStern() {
		return numberofThrusterBowToStern;
	}

	public void setNumberofThrusterBowToStern(Double numberofThrusterBowToStern) {
		this.numberofThrusterBowToStern = numberofThrusterBowToStern;
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

	public Double getAzimuthDemand() {
		return azimuthDemand;
	}

	public void setAzimuthDemand(Double azimuthDemand) {
		this.azimuthDemand = azimuthDemand;
	}

	public String getOperatingLocationIndicator() {
		return operatingLocationIndicator;
	}

	public void setOperatingLocationIndicator(String operatingLocationIndicator) {
		this.operatingLocationIndicator = operatingLocationIndicator;
	}

	public String getSentenceStatusFlag() {
		return sentenceStatusFlag;
	}

	public void setSentenceStatusFlag(String sentenceStatusFlag) {
		this.sentenceStatusFlag = sentenceStatusFlag;
	}

}
