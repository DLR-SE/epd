package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.data.Reference;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * HDT - Heading � True<br>
 * <br>
 * 
 * <pre>
 * {@code
 * .      1   2 3
 *        |   | |
 * $--HDT,x.x,T*hh
 * }
 * </pre>
 * <ol>
 * <li>Heading Degrees, true</li>
 * <li>T = True, R = Relative</li>
 * <li>Checksum</li>
 * </ol>
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class HDTSentence extends Sentence{
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "HE";
	/** Sentence id. */
	public static final String SENTENCE_ID = "HDT";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 2;
	
	/** Heading Degrees, true. */
	private Double degrees;
	/** T = True, R = Relative. */
	@Enumerated(EnumType.STRING)
	private Reference reference;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public HDTSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public HDTSentence(String nmea) {
		super(nmea);
	}
	@Override
	protected void decode() {
		int index = 0;
		degrees = ParseUtils.parseDouble(getValue(index++));
		reference = ParseUtils.parseReference(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(degrees, 1));
		setValue(index++, ParseUtils.toString(reference));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (degrees != null) res.put("degrees", degrees);
		if (reference != Reference.NULL) res.put("reference", reference.name());
	}

	public Double getDegrees(){
		return degrees;
	}
	
	public void setDegrees(Double degrees){
		this.degrees = degrees;
	}
	
	public Reference getReference(){
		return reference;
	}
	
	public void setReference(Reference reference){
		this.reference = reference;
	}
}
