package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * 
 * This is a NMEA0183 RTE sentence. 
 * Implementation details can be found within
 * the NMEA0183 specification, e.g. at
 * http://caxapa.ru/thumbs/214299/NMEA0183_.pdf
 * 
 * @author cdenker
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RTESentence extends Sentence {

	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	// FIXME: check DEFAULT_TALKER
	public static final String DEFAULT_TALKER = "EC";
	/** Sentence id. */
	public static final String SENTENCE_ID = "RTE";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 7;

	private Double totalNumerOfSentencesBeingTransmitted;
	private Double sentenceNumber;
	private String sentenceMode;
	private String routeIdentifier;
	private String waypointIdenitfier;
	private String additionalWaypointIdentifiers;
	private String waypointNidentier;

	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public RTESentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public RTESentence(String nmea) {
		super(nmea);
	}

	@Override
	protected void decode() {
		int index = 0;

		totalNumerOfSentencesBeingTransmitted = ParseUtils.parseDouble(getValue(index++));
		sentenceNumber = ParseUtils.parseDouble(getValue(index++));
		sentenceMode = getValue(index++);
		routeIdentifier = getValue(index++);
		waypointIdenitfier = getValue(index++);
		additionalWaypointIdentifiers = getValue(index++);
		waypointNidentier = getValue(index++);
	}

	@Override
	protected void encode() {
		int index = 0;

		setValue(index++, ParseUtils.toString(totalNumerOfSentencesBeingTransmitted, 1));
		setValue(index++, ParseUtils.toString(sentenceNumber, 1));
		setValue(index++, sentenceMode);
		setValue(index++, routeIdentifier);
		setValue(index++, waypointIdenitfier);
		setValue(index++, additionalWaypointIdentifiers);
		setValue(index++, waypointNidentier);
	}

	@Override
	protected void fillMap(Map<String, Object> res) {

		if (totalNumerOfSentencesBeingTransmitted != null)
			res.put("totalNumerOfSentencesBeingTransmitted", totalNumerOfSentencesBeingTransmitted);
		if (sentenceNumber != null)
			res.put("sentenceNumber", sentenceNumber);
		if (sentenceMode != null)
			res.put("sentenceMode", sentenceMode);
		if (routeIdentifier != null)
			res.put("routeIdentifier", routeIdentifier);
		if (waypointIdenitfier != null)
			res.put("waypointIdenitfier", waypointIdenitfier);
		if (additionalWaypointIdentifiers != null)
			res.put("additionalWaypointIdentifiers", additionalWaypointIdentifiers);
		if (waypointNidentier != null)
			res.put("waypointNidentier", waypointNidentier);
	}

	public Double getTotalNumerOfSentencesBeingTransmitted() {
		return totalNumerOfSentencesBeingTransmitted;
	}

	public void setTotalNumerOfSentencesBeingTransmitted(Double totalNumerOfSentencesBeingTransmitted) {
		this.totalNumerOfSentencesBeingTransmitted = totalNumerOfSentencesBeingTransmitted;
	}

	public Double getSentenceNumber() {
		return sentenceNumber;
	}

	public void setSentenceNumber(Double sentenceNumber) {
		this.sentenceNumber = sentenceNumber;
	}

	public String getSentenceMode() {
		return sentenceMode;
	}

	public void setSentenceMode(String sentenceMode) {
		this.sentenceMode = sentenceMode;
	}

	public String getRouteIdentifier() {
		return routeIdentifier;
	}

	public void setRouteIdentifier(String routeIdentifier) {
		this.routeIdentifier = routeIdentifier;
	}

	public String getWaypointIdenitfier() {
		return waypointIdenitfier;
	}

	public void setWaypointIdenitfier(String waypointIdenitfier) {
		this.waypointIdenitfier = waypointIdenitfier;
	}

	public String getAdditionalWaypointIdentifiers() {
		return additionalWaypointIdentifiers;
	}

	public void setAdditionalWaypointIdentifiers(String additionalWaypointIdentifiers) {
		this.additionalWaypointIdentifiers = additionalWaypointIdentifiers;
	}

	public String getWaypointNidentier() {
		return waypointNidentier;
	}

	public void setWaypointNidentier(String waypointNidentier) {
		this.waypointNidentier = waypointNidentier;
	}
	
	

}
