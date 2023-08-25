package de.emir.service.codecs.nmea0183.encoding.sentence.proprietary;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.sentence.Sentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.ShipType;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * SID - Ship Identification Data<br>
 * <br>
 * Introduced by the MTCAS Project (https://www.offis.de/offis/projekt/mtcas.html) 
 * 
 * <pre>
 * {@code
 * .      1     2 3   4    5     6  7
 *        |     | |   |    |     |  |
 * $-SID,c-10-c,x,x,c-6-c,c-10-c,x,*hh
 * }
 * </pre>
 * <ol>
 * <li>name maximum (10 characters and digits)</li>
 * <li>mmsi 9 digits</li>
 * <li>imo 6 digits</li>
 * <li>call sign (6 characters and digits)</li>
 * <li>home Harbour (maximum 10 characters and digits)</li>
 * <li>vessel type as number - using the same numbering as the ShipType of AIS message 5 (StaticAndVoyageData)</li>
 * <li>Checksum</li>
 * </ol>
 * 
 * @author sschweigert
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class SIDSentence extends Sentence{
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "P";
	/** Sentence id. */
	public static final String SENTENCE_ID = "SID";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 6;
	
	private String name;
	private Integer mmsi;
	private Integer imo;
	private String callSign;
	private String homeHarbour;
	private ShipType shipType;
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public SIDSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public SIDSentence(String nmea) {
		super(nmea);
	}
	
	@Override
	protected void decode() {
		int index = 0;
		name = getValue(index++);
		mmsi = ParseUtils.parseInteger(getValue(index++));
		imo = ParseUtils.parseInteger(getValue(index++));
		callSign = getValue(index++);
		homeHarbour = getValue(index++);
		shipType = ShipType.fromInteger(ParseUtils.parseInteger(getValue(index++)));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, name);
		setValue(index++, ParseUtils.toString(mmsi));
		setValue(index++, ParseUtils.toString(imo));
		setValue(index++, callSign);
		setValue(index++, homeHarbour);
		setValue(index++, ParseUtils.toString(shipType.getType()));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (name != null) res.put("name", name);
		if (mmsi != null) res.put("mmsi", mmsi);
		if (imo != null) res.put("imo", imo);
		if (callSign != null) res.put("callSign", callSign);
		if (homeHarbour != null) res.put("homeHarbour", homeHarbour);
		if (shipType != null) res.put("shipType", shipType);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMmsi() {
		return mmsi;
	}

	public void setMmsi(Integer mmsi) {
		this.mmsi = mmsi;
	}

	public Integer getImo() {
		return imo;
	}

	public void setImo(Integer imo) {
		this.imo = imo;
	}

	public String getCallSign() {
		return callSign;
	}

	public void setCallSign(String callSign) {
		this.callSign = callSign;
	}

	public String getHomeHarbour() {
		return homeHarbour;
	}

	public void setHomeHarbour(String homeHarbour) {
		this.homeHarbour = homeHarbour;
	}

	public ShipType getShipType() {
		return shipType;
	}

	public void setShipType(ShipType shipType) {
		this.shipType = shipType;
	}

	
	
	
	
}
