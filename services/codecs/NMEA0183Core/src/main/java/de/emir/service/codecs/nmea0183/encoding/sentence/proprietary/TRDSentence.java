package de.emir.service.codecs.nmea0183.encoding.sentence.proprietary;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.sentence.Sentence;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * This is a NMEA0183 TRD sentence. Implementation details can be found within
 * nmea.py on github
 * 
 * Thruster response data ("Number of thruster, bow or stern ", "thruster_num"),
 * ("RPM response value", "rpm_resp"), ("RPM mode indicator", "rpm_mode"), (
 * "Pitch response value", "pitch_resp"), ("Pitch mode indicator",
 * "pitch_mode"), ("Azimuth response", "azi_resp"))
 * 
 * 
 * @author cdenker
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class TRDSentence extends Sentence {

	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	// FIXME: check DEFAULT_TALKER
	public static final String DEFAULT_TALKER = "AG";
	/** Sentence id. */
	public static final String SENTENCE_ID = "TRD";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 6;

	private Double numberofThrusterBowToStern;
	private Double rpmResponseValue;
	private String rpmModeIndicator;
	private Double pitchResponseValue;
	private String pitchModeIndicator;
	private Double azimuthResponse;

	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public TRDSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public TRDSentence(String nmea) {
		super(nmea);
	}

	@Override
	protected void decode() {
		int index = 0;

		numberofThrusterBowToStern = ParseUtils.parseDouble(getValue(index++));
		rpmResponseValue = ParseUtils.parseDouble(getValue(index++));
		rpmModeIndicator = getValue(index++);
		pitchResponseValue = ParseUtils.parseDouble(getValue(index++));
		pitchModeIndicator = getValue(index++);
		azimuthResponse = ParseUtils.parseDouble(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;

		setValue(index++, ParseUtils.toString(numberofThrusterBowToStern, 1));
		setValue(index++, ParseUtils.toString(rpmResponseValue, 1));
		setValue(index++, rpmModeIndicator);
		setValue(index++, ParseUtils.toString(pitchResponseValue, 1));
		setValue(index++, pitchModeIndicator);
		setValue(index++, ParseUtils.toString(azimuthResponse, 1));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {

		if (numberofThrusterBowToStern != null)
			res.put("numberofThrusterBowToStern", numberofThrusterBowToStern);
		if (rpmResponseValue != null)
			res.put("rpmResponseValue", rpmResponseValue);
		if (rpmModeIndicator != null)
			res.put("rpmModeIndicator", rpmModeIndicator);
		if (pitchResponseValue != null)
			res.put("pitchResponseValue", pitchResponseValue);
		if (pitchModeIndicator != null)
			res.put("pitchModeIndicator", pitchModeIndicator);
		if (azimuthResponse != null)
			res.put("azimuthResponse", azimuthResponse);
	}

	public Double getNumberofThrusterBowToStern() {
		return numberofThrusterBowToStern;
	}

	public void setNumberofThrusterBowToStern(Double numberofThrusterBowToStern) {
		this.numberofThrusterBowToStern = numberofThrusterBowToStern;
	}

	public Double getRpmResponseValue() {
		return rpmResponseValue;
	}

	public void setRpmResponseValue(Double rpmResponseValue) {
		this.rpmResponseValue = rpmResponseValue;
	}

	public String getRpmModeIndicator() {
		return rpmModeIndicator;
	}

	public void setRpmModeIndicator(String rpmModeIndicator) {
		this.rpmModeIndicator = rpmModeIndicator;
	}

	public Double getPitchResponseValue() {
		return pitchResponseValue;
	}

	public void setPitchResponseValue(Double pitchResponseValue) {
		this.pitchResponseValue = pitchResponseValue;
	}

	public String getPitchModeIndicator() {
		return pitchModeIndicator;
	}

	public void setPitchModeIndicator(String pitchModeIndicator) {
		this.pitchModeIndicator = pitchModeIndicator;
	}

	public Double getAzimuthResponse() {
		return azimuthResponse;
	}

	public void setAzimuthResponse(Double azimuthResponse) {
		this.azimuthResponse = azimuthResponse;
	}

}
