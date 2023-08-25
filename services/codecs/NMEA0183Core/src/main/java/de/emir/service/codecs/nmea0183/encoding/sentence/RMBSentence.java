package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.data.Hemisphere;
import de.emir.service.codecs.nmea0183.encoding.data.ModeIndicator;
import de.emir.service.codecs.nmea0183.encoding.data.Status;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * 
 * Implements NMEA0183 RMB Sentence
 * 
 * @author rdroste
 * 
 *                                                              14
 *       1 2   3 4    5    6       7 8        9 10  11  12  13|  15
 *       | |   | |    |    |       | |        | |   |   |   | |   |
 *$--RMB,A,x.x,a,c--c,c--c,llll.ll,a,yyyyy.yy,a,x.x,x.x,x.x,A,m,*hh<CR><LF>
 *
 * Field Number:
 * 1. Status, A= Active, V = Void
 * 2. Cross Track error - nautical miles
 * 3. Direction to Steer, Left or Right
 * 4. TO Waypoint ID
 * 5. FROM Waypoint ID
 * 6. Destination Waypoint Latitude
 * 7. N or S
 * 8. Destination Waypoint Longitude
 * 9. E or W
 * 10. Range to destination in nautical miles
 * 11. Bearing to destination in degrees True
 * 12. Destination closing velocity in knots
 * 13. Arrival Status, A = Arrival Circle Entered
 * 14. FAA mode indicator (NMEA 2.3 and later)
 * 15. Checksum
 *
 * Example: $GPRMB,A,0.66,L,003,004,4917.24,N,12309.57,W,001.3,052.5,000.5,V*0B
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RMBSentence extends Sentence {

	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "AG";
	/** Sentence id. */
	public static final String SENTENCE_ID = "RMB";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 14;
	
	/**
	 * 1) Status 
     	A = Active
     	V = Void
	 */
	private Status status;
	
	/**
	 * 2) Cross Track error - nautical miles
	 */
	private Double xteNm;
	
	/**
	 * 3) Direction to steer, L or R 
	 */
	private String directionToSteer;
	
	/**
	 *  4) TO Waypoint ID 
	 */
	private String toWaypointID;
	
	/**
	 *  5) FROM Waypoint ID 
	 */
	private String fromWaypointID;
	
	/**
	 *  6) Destination Waypoint Latitude
	 */
	private Double latitude;
	
	/**
	 *  7) N or S (North or South)
	 */
	private Hemisphere latitudeHem;
	
	/**
	 *  8) Destination Waypoint Longitude
	 */
	private Double longitude;
	
	/**
	 *  9) E or W (East or West)
	 */
	private Hemisphere longitudeHem;
	
	/**
	 *  10) Range to destination in nautical miles
	 */
	private Double rangeToDestination;
	
	/**
	 *  11) Bearing to destination in degrees True
	 */
	private Double bearingToDestination;
	/**
	 *  12) Destination closing velocity in knots
	 */
	private Double destClosingVelocity;
	
	/**
	 *  13) Arrival Status, A = Arrival Circle Entered
	 */
	private Status arrivalStatus;
	
	/**
	 *  14) FAA mode indicator (NMEA 2.3 and later)
		A = Autonomous mode 
		D = Differential mode 
		E = Estimated (dead reckoning) mode 
		M = Manual input mode 
		S = Simulator mode 
		N = Data not valid 
		
		The  positioning  system  Mode  Indicator  field  supplements  the  positioning  system  Status  fields,  the 
		Status fields shall be set to V = Invalid for all values of Indicator mode except for A= Autonomous and 
		D = Differential.  The positioning system Mode Indicator and Status fields shall not be null fields. 
     */
    private ModeIndicator modeIndicator;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public RMBSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public RMBSentence(String nmea) {
		super(nmea);
	}

	@Override
	protected void decode() {
		int index = 0;
		status = ParseUtils.parseStatus(getValue(index++));
		xteNm = ParseUtils.parseDouble(getValue(index++));
		directionToSteer = getValue(index++);
		toWaypointID = getValue(index++);
		fromWaypointID = getValue(index++);
		latitude = ParseUtils.parseDouble(getValue(index++));
		latitudeHem = ParseUtils.parseHemisphere(getValue(index++));
		longitude = ParseUtils.parseDouble(getValue(index++));
		longitudeHem = ParseUtils.parseHemisphere(getValue(index++));
		rangeToDestination = ParseUtils.parseDouble(getValue(index++));
		bearingToDestination = ParseUtils.parseDouble(getValue(index++));
		destClosingVelocity = ParseUtils.parseDouble(getValue(index++));
		arrivalStatus = ParseUtils.parseStatus(getValue(index++));
		modeIndicator = ParseUtils.parseModeIndicator(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(status));
		setValue(index++, ParseUtils.toString(xteNm));
		setValue(index++, directionToSteer);
		setValue(index++, toWaypointID);
		setValue(index++, fromWaypointID);
		setValue(index++, ParseUtils.doubleToDecimalDegreeString(latitude, 2));
		setValue(index++, ParseUtils.toString(latitudeHem));
		setValue(index++, ParseUtils.doubleToDecimalDegreeString(longitude, 3));
		setValue(index++, ParseUtils.toString(longitudeHem));
		setValue(index++, ParseUtils.toString(rangeToDestination));
		setValue(index++, ParseUtils.toString(bearingToDestination,1));
		setValue(index++, ParseUtils.toString(destClosingVelocity, 1));
		setValue(index++, ParseUtils.toString(arrivalStatus));
		setValue(index++, ParseUtils.toString(modeIndicator));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (status != null) res.put("status", status.name());
		if (xteNm != null) res.put("xteNm", xteNm);
		if (directionToSteer != null) res.put("directionToSteer", directionToSteer);
		if (toWaypointID != null) res.put("toWaypointID", toWaypointID);
		if (fromWaypointID != null) res.put("fromWaypointID", fromWaypointID);
		if (latitude != null) res.put("latitude", latitude);
		if (latitudeHem != Hemisphere.NULL) res.put("latitudeHem", latitudeHem.name());
		if (longitude != null) res.put("longitude", longitude);
		if (longitudeHem != Hemisphere.NULL) res.put("longitudeHem", longitudeHem.name());
		if (rangeToDestination != null) res.put("rangeToDestination", rangeToDestination);
		if (bearingToDestination != null) res.put("bearingToDestination", bearingToDestination);
		if (destClosingVelocity != null) res.put("destClosingVelocity", destClosingVelocity);
		if (arrivalStatus != null) res.put("arrivalStatus", arrivalStatus.name());
		if (modeIndicator != null) res.put("modeIndicator", modeIndicator.name());
	}

	
	
	/**
	 * GETTER and SETTER
	 */
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDirectionToSteer() {
		return directionToSteer;
	}

	public void setDirectionToSteer(String directionToSteer) {
		this.directionToSteer = directionToSteer;
	}

	public String getToWaypointID() {
		return toWaypointID;
	}

	public void setToWaypointID(String toWaypointID) {
		this.toWaypointID = toWaypointID;
	}

	public String getFromWaypointID() {
		return fromWaypointID;
	}

	public void setFromWaypointID(String fromWaypointID) {
		this.fromWaypointID = fromWaypointID;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Hemisphere getLatitudeHem() {
		return latitudeHem;
	}

	public void setLatitudeHem(Hemisphere latitudeHem) {
		this.latitudeHem = latitudeHem;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Hemisphere getLongitudeHem() {
		return longitudeHem;
	}

	public void setLongitudeHem(Hemisphere longitudeHem) {
		this.longitudeHem = longitudeHem;
	}

	public Double getRangeToDestination() {
		return rangeToDestination;
	}

	public void setRangeToDestination(Double rangeToDestination) {
		this.rangeToDestination = rangeToDestination;
	}

	public Double getBearingToDestination() {
		return bearingToDestination;
	}

	public void setBearingToDestination(Double bearingToDestination) {
		this.bearingToDestination = bearingToDestination;
	}

	public Double getDestClosingVelocity() {
		return destClosingVelocity;
	}

	public void setDestClosingVelocity(Double destClosingVelocity) {
		this.destClosingVelocity = destClosingVelocity;
	}

	public Status getArrivalStatus() {
		return arrivalStatus;
	}

	public void setArrivalStatus(Status arrivalStatus) {
		this.arrivalStatus = arrivalStatus;
	}

	public ModeIndicator getModeIndicator() {
		return modeIndicator;
	}

	public void setModeIndicator(ModeIndicator modeIndicator) {
		this.modeIndicator = modeIndicator;
	}

	


	
}
