package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * DPT - Depth of Water<br>
 * <br>
 * 
 * <pre>
 * {@code
 * .      1   2   3   4
 *        |   |   |   |
 * $--DPT,x.x,x.x,x.x*hh
 * }
 * </pre>
 * <ol>
 * <li>Depth, meters</li>
 * <li>Offset from transducer
 * 	   positive means distance from transducer to water line,
 * 	   negative means distance from transducer to keel</li>
 * <li>Maximum range scale in use</li>
 * <li>Checksum</li>
 * </ol>
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class DPTSentence extends Sentence{
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "IN";
	/** Sentence id. */
	public static final String SENTENCE_ID = "DPT";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 3;
	
	/** Depth, meters. */
	private Double depth;
	/** Offset from transducer;
	 *  positive means distance from transducer to water line,
	 *  negative means distance from transducer to keel. */
	private Double offset;
	/** Maximum range scale in use. */
	private Double range;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public DPTSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public DPTSentence(String nmea) {
		super(nmea);
	}
	
	@Override
	protected void decode() {
		int index = 0;
		depth = ParseUtils.parseDouble(getValue(index++));
		offset = ParseUtils.parseDouble(getValue(index++));
		range = ParseUtils.parseDouble(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(depth, 1));
		setValue(index++, ParseUtils.toString(offset, 1));
		setValue(index++, ParseUtils.toString(range, 1));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (depth != null) res.put("depth", depth);
		if (offset != null) res.put("offset", offset);
		if (range != null) res.put("range", range);
	}
	
	public Double getDepth(){
		return depth;
	}
	
	public void setDepth(Double depth){
		this.depth = depth;
	}
	
	public Double getOffset(){
		return offset;
	}
	
	public void setOffset(Double offset){
		this.offset = offset;
	}
	
	public Double getRange(){
        return range;
    }
    
    public void setRange(Double range){
        this.range = range;
    }
}