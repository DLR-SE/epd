package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.DecodedAISPayload;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * VDM/VDO - AIS Message (VDO = Own ship)<br>
 * <br>
 * 
 * <pre>
 * {@code
 * .      1 2 3 4 5    6 7
 *        | | | | |    | |
 * $--VDM,x,x,x,a,c--c,x*hh
 * }
 * </pre>
 * <ol>
 * <li>Count of fragments</li>
 * <li>Fragment number</li>
 * <li>Sequential message id (only in multi fragments)</li>
 * <li>Channel (A = 161.975 Mhz, B = 162.025 Mhz)</li>
 * <li>AIS message</li>
 * <li>Fill bits</li>
 * <li>Checksum</li>
 * </ol>
 *
 */
@XmlRootElement(name = "AISSentence")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AISSentence", propOrder = {
	  /*  "fragmentsCount", */
	  /*  "fragmentId", */
	    "messageId", 
	    "channel",
	    "message"/*,
	    "fillBits",
	    "decodedPayload"*/
})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class AISSentence extends Sentence{
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '!';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "AI";
	/** Sentence id. */
	public static final String SENTENCE_ID = "VDM";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 6;
	
	/** Count of fragments */
	@XmlTransient
	protected Integer fragmentsCount = 1; // defaults to 1, set manually if multi fragment (ais5)
	/** Fragment number */
	@XmlTransient
	protected Integer fragmentId = 1; // defaults to 1
	/** Sequential message id (only in multi fragments) */
	//@XmlTransient
	protected Integer messageId;
	/** Channel (A = 161.975 Mhz, B = 162.025 Mhz) */
	protected String channel = "A";
	/** AIS message */
	protected String message;
	/** Fill bits */
	@XmlTransient
	protected Integer fillBits = 0; // defaults to 0, set manually if length does not divide by 6
	
	/**The decoded message, this will be set by the AISSentenceHandler:
	 * 1- If the sentence is not fragmented, the AISSentenceHandler will set it after handling the sentence directly
	 * 2- If the sentence is fragmented, the AISSentenceHandler will set this member only if this sentence represents the last fragment. */
	@XmlTransient
	@Transient
	private DecodedAISPayload decodedPayload;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public AISSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public AISSentence(String nmea) {
		super(nmea);
	}
	@Override
	protected void decode() {
		int index = 0;
		fragmentsCount = ParseUtils.parseInteger(getValue(index++));
		fragmentId = ParseUtils.parseInteger(getValue(index++));
		messageId = ParseUtils.parseInteger(getValue(index++));
		channel = getValue(index++);
		message = getValue(index++);
		fillBits = ParseUtils.parseInteger(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(fragmentsCount));
		setValue(index++, ParseUtils.toString(fragmentId));
		setValue(index++, ParseUtils.toString(messageId));
		setValue(index++, channel);
		setValue(index++, message);
		setValue(index++, ParseUtils.toString(fillBits));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
//		//We will fill the map with the decoded message parts instead of the original encoded one:
//		if (decodedPayload != null)
//			decodedPayload.fillMap(res);
		if (fragmentsCount != null) res.put("fragmentsCount", fragmentsCount);
		if (fragmentId != null) res.put("fragmentId", fragmentId);
		if (messageId != null) res.put("messageId", messageId);
		if (channel != null) res.put("channel", channel);
		if (message != null) res.put("message", message);
		if (fillBits != null) res.put("fillBits", fillBits);
	}

	public Integer getFragmentsCount() {
		return fragmentsCount;
	}

	public void setFragmentsCount(Integer fragmentsCount) {
		this.fragmentsCount = fragmentsCount;
	}

	public Integer getFragmentId() {
		return fragmentId;
	}

	public void setFragmentId(Integer fragmentId) {
		this.fragmentId = fragmentId;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getFillBits() {
		return fillBits;
	}

	public void setFillBits(Integer fillBits) {
		this.fillBits = fillBits;
	}

	public DecodedAISPayload getDecodedPayload() {
		return decodedPayload;
	}

	public void setDecodedPayload(DecodedAISPayload decodedPayload) {
		this.decodedPayload = decodedPayload;
	}
}
