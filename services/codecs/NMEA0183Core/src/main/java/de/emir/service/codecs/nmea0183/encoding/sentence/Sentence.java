package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import de.emir.service.codecs.nmea0183.encoding.util.SentenceUtils;

/**
 * This is the super class for all Sentences. It holds the main information
 * generic for all sentences and does the first job to separate the values in
 * the string.
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sentence", propOrder = { "talkerId", "sentenceId" })
@MappedSuperclass
public abstract class Sentence {
	/** Alternative begin char for a nmea sentence. */
	public static final char ALTERNATIVE_BEGIN_CHAR = '!';
	/** Default begin char for a nmea sentence. */
	public static final char BEGIN_CHAR = '$';
	/** Delimiter char for the checksum. */
	public static final char CHECKSUM_DELIMITER = '*';
	/** Delimiter char for the values. */
	public static final char FIELD_DELIMITER = ',';
	/** Maximal length of the string. */
	public static final int MAX_LENGTH = 82;
	/** Termination String of the sentence. */
	public static final String TERMINATOR = "\r\n";

	/** Holds the begin char for this instance. */
	@XmlTransient
	protected char beginChar;
	/** Holds the talker id. */
	protected String talkerId;
	/** Holds the sentence id. */
	protected String sentenceId;
	/** Holds all comma separated values, but checksum and types. */
	@XmlTransient
	@Transient
	protected List<String> fields;
	/** The first given nmea string. */
	@XmlTransient
	@Transient
	protected String nmea;
	/** The PK. */
	@Id
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	@XmlTransient
	protected String id = UUID.randomUUID().toString();

	private Sentence() {

	}

	/**
	 * Constructs a sentence with the given String. This method does not parse
	 * the sentence.
	 * 
	 * @param nmea
	 *            Nmea string.
	 */
	public Sentence(String nmea) {
		int p = nmea.indexOf("\r\n");
		if (p < 0) p = nmea.length();
		this.nmea = nmea.substring(0, p);
		beginChar = this.nmea.charAt(0);
		talkerId = SentenceUtils.getTalkerId(this.nmea);
		sentenceId = SentenceUtils.getSentenceId(this.nmea);
	}

	/**
	 * Constructs a sentence for the output.
	 * 
	 * @param beginChar
	 *            The begin char to use.
	 * @param talkerId
	 *            The talker id.
	 * @param sentenceId
	 *            The sentence id.
	 * @param fieldCount
	 *            Default count of fields to create.
	 */
	public Sentence(char beginChar, String talkerId, String sentenceId, int fieldCount) {
		this.beginChar = beginChar;
		this.talkerId = talkerId;
		this.sentenceId = sentenceId;
		this.fields = new ArrayList<String>(fieldCount);
		for (int i = 0; i < fieldCount; i++) {
			this.fields.add("");
		}
	}

	/**
	 * Parses the nmea String. It is important, that the Sentence was create
	 * with the {@link #Sentence(String)} constructor, so the nmea String was
	 * set.
	 */
	public void parse() {
		int begin = nmea.indexOf(Sentence.FIELD_DELIMITER);
		String temp = nmea.substring(begin + 1);

		// remove checksum
		if (temp.contains(String.valueOf(CHECKSUM_DELIMITER))) {
			int end = temp.indexOf(CHECKSUM_DELIMITER);
			temp = temp.substring(0, end);
		}

		String[] temp2 = temp.split(String.valueOf(FIELD_DELIMITER), -1);
		fields = new ArrayList<String>(temp2.length);
		for (String s : temp2) {
			fields.add(s);
		}
		decode();
	}

	/**
	 * Count of fields in this sentence.
	 * 
	 * @return Count of fields.
	 */
	public final int getFieldCount() {
		if (fields == null) {
			return 0;
		}
		return fields.size();
	}

	/**
	 * Returns a String from the comma separated values on the given index.
	 * 
	 * @param index
	 *            Index of the String to return.
	 * @return The String, or null if the String is empty or out of bounds.
	 */
	protected String getValue(int index) {
		if (fields.size() <= index) {
			return null;
		}
		try {
			String value = fields.get(index);
			if (value == null || "".equals(value)) {
				return null;
			}
			return value;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Sets the String on the given index. Extends the field, if the index is
	 * out of bounds.
	 * 
	 * @param index
	 *            Index where to set the given String.
	 * @param value
	 *            String to set.
	 */
	protected void setValue(int index, String value) {
		while (fields.size() <= index) {
			fields.add("");
		}
		fields.set(index, value);
	}

	@Override
	public String toString() {
		return toNMEA();
	}

	/**
	 * This will call the {@link #encode()} method to set the field values
	 * first. Then all Strings will be concatenated to a nmea String.
	 * 
	 * @return NMEA String for the saved attributes.
	 */
	public final String toNMEA() {
		encode();
		StringBuilder sb = new StringBuilder(MAX_LENGTH);
		sb.append(beginChar);
		sb.append(talkerId.toString());
		sb.append(sentenceId);

		for (int i = 0; i < fields.size();i++) {//String field : fields
//			if(i<fields.size()-1)
				sb.append(FIELD_DELIMITER);
			sb.append(fields.get(i) == null ? "" : fields.get(i));
		}

		String res;
		if (sb.length() > MAX_LENGTH - 2) {
			res = sb.substring(0, MAX_LENGTH - 2);
		} else {
			res = sb.toString();
		}
		String sum = SentenceUtils.calculateChecksum(res);
		return res + CHECKSUM_DELIMITER + sum + TERMINATOR;
	}

	/**
	 * Gets the talker id.
	 * 
	 * @return Talker id.
	 */
	public String getTalkerId() {
		return talkerId;
	}

	public void setTalkerId(String id){
		talkerId = id;
	}
	/**
	 * Gets the sentence id.
	 * 
	 * @return Sentence id.
	 */
	public String getSentenceId() {
		return sentenceId;
	}
	
	/**
	 * Sets the sentence id.
	 * 
	 * @param newSentenceId - the new id of the sentence.
	 */
	public void setSentenceId(String newSentenceId){
		this.sentenceId = newSentenceId;
	}

	/**
	 * Will be called to transform this class to a key value pairs. Is used by
	 * Odysseus.
	 * 
	 * @return A HashMap with attribute name as key String mapping to attribute
	 *         values as objects.
	 */
	public Map<String, Object> toMap() {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("sentenceId", sentenceId);
		res.put("talkerId", talkerId);
		res.put("beginChar", beginChar);
		fillMap(res);
		return res;
	}

	/**
	 * Compare internal Map with given Map of Sentence object.
	 * 
	 * @param obj
	 * 			A Sentence to compare with
	 * @return true if equal and false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Sentence) {
			Map<String, Object> map1 = toMap();
			Map<String, Object> map2 = ((Sentence) obj).toMap();
			return map1.equals(map2);
		}
		return false;
	}

	/**
	 * Will be called in {@link #parse()} method, after all values where
	 * separated to a String array in attribute {@link #fields}. Use this method
	 * to parse each field and save the local attributes.
	 */
	protected abstract void decode();

	/**
	 * Will be called in {@link #toNMEA()} method first. Use this method to set
	 * all String values in {@link #fields} by using
	 * {@link #setValue(int, String)}.
	 */
	protected abstract void encode();

	/**
	 * Will be called in {@link #toMap()} method. Use this method to fill
	 * attributes of the extended class, to the passed result map.
	 * 
	 * @param result
	 *            A HashMap with attribute name as key String mapping to
	 *            attribute values as objects.
	 */
	protected abstract void fillMap(Map<String, Object> result);

	/**
	 * Get the NMEA String
	 * */
	public String getNmeaString() {
		return this.nmea;
	}
	
	/**
	 * Get the primary key.
	 * 
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Set the primary key.
	 * 
	 * @param id the id
	 */
	public void setId(String id) {
		this.id = id;
	}
}
