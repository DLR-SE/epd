package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.data.ModeIndicator;
import de.emir.service.codecs.nmea0183.encoding.data.Status;
import de.emir.service.codecs.nmea0183.encoding.data.Unit;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * 
 * Implements NMEA0183 APB Sentence
 * 
 * @author
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class APBSentence extends Sentence {

	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "AG";
	/** Sentence id. */
	public static final String SENTENCE_ID = "APB";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 14;
	
	/**
	 * 1) Status 
     	A = Data valid, V = Loran-C Blink or SNR warning 
      	V = General warning flag for other navigation systems when a reliable fix is not available
         
        The  positioning  system  Mode  Indicator  field  supplements  the  positioning  system  Status  fields,  the 
		Status fields shall be set to V = Invalid for all values of Indicator mode except for A= Autonomous and 
		D = Differential.  The positioning system Mode Indicator and Status fields shall not be null fields. 
	 */
	private Status status1;
	/**
	 *  2) Status 
     V = Loran-C Cycle Lock warning flag 
     A = OK or not used 
	 */
	private Status status2;
	/**
	 * 3) Cross Track Error Magnitude 
	 */
	private Double xteMagnitude;
	/**
	 * 4) Direction to steer, L or R 
	 */
	private String directionToSteer;
	/**
	 * 5) Cross Track Units, N = Nautical Miles 
	 */
	private Unit crossTrackUnits;
	/**
	 * 6) Status 
	 * A = Arrival Circle Entered 
	 */
	private Status status3;
	/**
	 * 7) Status 
     A = Perpendicular passed at waypoint 
	 */
	private Status status4;
	/**
	 * 8) Bearing origin to destination 
	 */
	private Double bearingOriginToDestination;
	/**
	 *  9) M = Magnetic, T = True 
	 */
	private String bearingOriginToDestinationType;
	/**
	 *  10) Destination Waypoint ID 
	 */
	private String waypointID;
	/**
	 * 11) Bearing, present position to Destination 
	 */
	private Double bearingPresentPositionToDestination;
	/**
	 * 12) M = Magnetic, T = True 
	 */
	private String bearingPresentPositionToDestinationType;
	/**
	 * 13) Heading to steer to destination waypoint 
	 */
	private Double headingToSteerToDestinationWaypoint;
	/**
	 * 14) M = Magnetic, T = True 
	 */
	private String headingToSteerToDestinationWaypointType;
	/**
     * 15) Mode indicator 
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
	public APBSentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}

	/**
	 * Default constructor for parsing.
	 * 
	 * @param nmea
	 *            Nmea String to be parsed.
	 */
	public APBSentence(String nmea) {
		super(nmea);
	}

	@Override
	protected void decode() {
		int index = 0;
		status1 = ParseUtils.parseStatus(getValue(index++));
		status2 = ParseUtils.parseStatus(getValue(index++));
		xteMagnitude = ParseUtils.parseDouble(getValue(index++));
		directionToSteer = getValue(index++); // TODO: use enum
		crossTrackUnits = ParseUtils.parseUnit(getValue(index++));
		status3 = ParseUtils.parseStatus(getValue(index++));
        status4 = ParseUtils.parseStatus(getValue(index++));
        bearingOriginToDestination = ParseUtils.parseDouble(getValue(index++));
        bearingOriginToDestinationType = getValue(index++); // TODO: use enum
        waypointID = getValue(index++);
        bearingPresentPositionToDestination = ParseUtils.parseDouble(getValue(index++));
        bearingOriginToDestinationType = getValue(index++); // TODO: use enum
        headingToSteerToDestinationWaypoint = ParseUtils.parseDouble(getValue(index++));
        headingToSteerToDestinationWaypointType = getValue(index++); // TODO: use enum
        modeIndicator = ParseUtils.parseModeIndicator(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(status1));
		setValue(index++, ParseUtils.toString(status2));
		setValue(index++, ParseUtils.toString(xteMagnitude, 1));
		setValue(index++, directionToSteer);
		setValue(index++, ParseUtils.toString(crossTrackUnits));
		setValue(index++, ParseUtils.toString(status3));
		setValue(index++, ParseUtils.toString(status4));
		setValue(index++, ParseUtils.toString(bearingOriginToDestination,1));
		setValue(index++, bearingOriginToDestinationType);
		setValue(index++, waypointID);
		setValue(index++, ParseUtils.toString(bearingPresentPositionToDestination,1));
		setValue(index++, bearingPresentPositionToDestinationType);
		setValue(index++, ParseUtils.toString(headingToSteerToDestinationWaypoint,1));
		setValue(index++, headingToSteerToDestinationWaypointType);
		setValue(index++, ParseUtils.toString(modeIndicator));
	}

	@Override
	protected void fillMap(Map<String, Object> res) {
		if (status1 != null) res.put("status1", status1.name());
		if (status2 != null) res.put("status2", status2.name());
		if (xteMagnitude != null) res.put("xteMagnitude", xteMagnitude);
		if (directionToSteer != null) res.put("directionToSteer", directionToSteer);
		if (crossTrackUnits != null) res.put("crossTrackUnits", crossTrackUnits.name());
		if (status3 != null) res.put("status3", status3.name());
		if (status4 != null) res.put("status4", status4.name());
		if (bearingOriginToDestination != null) res.put("bearingOriginToDestination", bearingOriginToDestination);
		if (bearingOriginToDestinationType != null) res.put("bearingOriginToDestinationType", bearingOriginToDestinationType);
		if (waypointID != null) res.put("waypointID", waypointID);
		if (bearingPresentPositionToDestination != null) res.put("bearingPresentPositionToDestination", bearingPresentPositionToDestination);
		if (bearingPresentPositionToDestinationType != null) res.put("bearingPresentPositionToDestinationType", bearingPresentPositionToDestinationType);
		if (headingToSteerToDestinationWaypoint != null) res.put("headingToSteerToDestinationWaypoint", headingToSteerToDestinationWaypoint);
		if (headingToSteerToDestinationWaypointType != null) res.put("headingToSteerToDestinationWaypointType", headingToSteerToDestinationWaypointType);
		if (modeIndicator != null) res.put("modeIndicator", modeIndicator.name());
	}

	
	
	/**
	 * GETTER and SETTER
	 */
	
	public Status getStatus1() {
		return status1;
	}

	public void setStatus1(Status status1) {
		this.status1 = status1;
	}

	public Status getStatus2() {
		return status2;
	}

	public void setStatus2(Status status2) {
		this.status2 = status2;
	}

	public Double getXteMagnitude() {
		return xteMagnitude;
	}

	public void setXteMagnitude(Double xteMagnitude) {
		this.xteMagnitude = xteMagnitude;
	}

	public String getDirectionToSteer() {
		return directionToSteer;
	}

	public void setDirectionToSteer(String directionToSteer) {
		this.directionToSteer = directionToSteer;
	}

	public Unit getCrossTrackUnits() {
		return crossTrackUnits;
	}

	public void setCrossTrackUnits(Unit crossTrackUnits) {
		this.crossTrackUnits = crossTrackUnits;
	}

	public Status getStatus3() {
		return status3;
	}

	public void setStatus3(Status status3) {
		this.status3 = status3;
	}

	public Status getStatus4() {
		return status4;
	}

	public void setStatus4(Status status4) {
		this.status4 = status4;
	}

	public Double getBearingOriginToDestination() {
		return bearingOriginToDestination;
	}

	public void setBearingOriginToDestination(Double bearingOriginToDestination) {
		this.bearingOriginToDestination = bearingOriginToDestination;
	}

	public String getBearingOriginToDestinationType() {
		return bearingOriginToDestinationType;
	}

	public void setBearingOriginToDestinationType(String bearingOriginToDestinationType) {
		this.bearingOriginToDestinationType = bearingOriginToDestinationType;
	}

	public String getWaypointID() {
		return waypointID;
	}

	public void setWaypointID(String waypointID) {
		this.waypointID = waypointID;
	}

	public Double getBearingPresentPositionToDestination() {
		return bearingPresentPositionToDestination;
	}

	public void setBearingPresentPositionToDestination(Double bearingPresentPositionToDestination) {
		this.bearingPresentPositionToDestination = bearingPresentPositionToDestination;
	}

	public String getBearingPresentPositionToDestinationType() {
		return bearingPresentPositionToDestinationType;
	}

	public void setBearingPresentPositionToDestinationType(String bearingPresentPositionToDestinationType) {
		this.bearingPresentPositionToDestinationType = bearingPresentPositionToDestinationType;
	}

	public Double getHeadingToSteerToDestinationWaypoint() {
		return headingToSteerToDestinationWaypoint;
	}

	public void setHeadingToSteerToDestinationWaypoint(Double headingToSteerToDestinationWaypoint) {
		this.headingToSteerToDestinationWaypoint = headingToSteerToDestinationWaypoint;
	}

	public String getHeadingToSteerToDestinationWaypointType() {
		return headingToSteerToDestinationWaypointType;
	}

	public void setHeadingToSteerToDestinationWaypointType(String headingToSteerToDestinationWaypointType) {
		this.headingToSteerToDestinationWaypointType = headingToSteerToDestinationWaypointType;
	}

    public ModeIndicator getModeIndicator() {
        return modeIndicator;
    }

    public void setModeIndicator(ModeIndicator modeIndicator) {
        this.modeIndicator = modeIndicator;
    }
	
}
