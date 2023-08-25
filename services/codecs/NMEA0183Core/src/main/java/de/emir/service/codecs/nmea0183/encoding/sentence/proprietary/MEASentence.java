package de.emir.service.codecs.nmea0183.encoding.sentence.proprietary;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.sentence.Sentence;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * MEA - Measurements<br> //TODO Leon / Björn / Christian: measurements of what?
 * <br>
 * Introduced by the MTCAS Project (https://www.offis.de/offis/projekt/mtcas.html) 
 * 
 * <pre>
 * {@code
 * .      1   2   3   4   5   6  7
 *        |   |   |   |   |   |  |
 * $-MEA,x.x,x.x,x.x,x.x,x.x,x.x,*hh
 * }
 * </pre>
 * <ol>
 * <li>beam, meter</li>
 * <li>length at waterline, meter</li>
 * <li>overall length, meter</li>
 * <li>draft, meter</li>
 * <li>moulded depth, meter</li>
 * <li>freeboard, meter</li>
 * <li>Checksum</li>
 * </ol>
 * 
 * @author sschweigert
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MEASentence extends Sentence{
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "P";
	/** Sentence id. */
	public static final String SENTENCE_ID = "MEA";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 6;
	
	private Double beam;
	private Double lengthAtWaterline;
	private Double overallLength;
	private Double draft;
	private Double mouldedDepth;
	private Double freeboard;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public MEASentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public MEASentence(String nmea) {
		super(nmea);
	}
	
	@Override
	protected void decode() {
		int index = 0;
		beam = ParseUtils.parseDouble(getValue(index++));
		lengthAtWaterline = ParseUtils.parseDouble(getValue(index++));
		overallLength = ParseUtils.parseDouble(getValue(index++));
		draft = ParseUtils.parseDouble(getValue(index++));
		mouldedDepth = ParseUtils.parseDouble(getValue(index++));
		freeboard = ParseUtils.parseDouble(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(beam));
		setValue(index++, ParseUtils.toString(lengthAtWaterline));
		setValue(index++, ParseUtils.toString(overallLength));
		setValue(index++, ParseUtils.toString(draft));
		setValue(index++, ParseUtils.toString(mouldedDepth));
		setValue(index++, ParseUtils.toString(freeboard));		
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (beam != null) res.put("beam", beam);
		if (lengthAtWaterline != null) res.put("lengthAtWaterline", lengthAtWaterline);
		if (overallLength != null) res.put("overallLength", overallLength);
		if (draft != null) res.put("draft", draft);
		if (mouldedDepth != null) res.put("mouldedDepth", mouldedDepth);
		if (freeboard != null) res.put("freeboard", freeboard);
	}

	public Double getBeam() {
		return beam;
	}

	public void setBeam(Double beam) {
		this.beam = beam;
	}

	public Double getLengthAtWaterline() {
		return lengthAtWaterline;
	}

	public void setLengthAtWaterline(Double lengthAtWaterline) {
		this.lengthAtWaterline = lengthAtWaterline;
	}

	public Double getOverallLength() {
		return overallLength;
	}

	public void setOverallLength(Double overallLength) {
		this.overallLength = overallLength;
	}

	public Double getDraft() {
		return draft;
	}

	public void setDraft(Double draft) {
		this.draft = draft;
	}

	public Double getMouldedDepth() {
		return mouldedDepth;
	}

	public void setMouldedDepth(Double mouldedDepth) {
		this.mouldedDepth = mouldedDepth;
	}

	public Double getFreeboard() {
		return freeboard;
	}

	public void setFreeboard(Double freeboard) {
		this.freeboard = freeboard;
	}

	
	
	
	
	
}
