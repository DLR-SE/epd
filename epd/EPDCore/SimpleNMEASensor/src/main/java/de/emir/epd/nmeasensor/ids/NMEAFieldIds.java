package de.emir.epd.nmeasensor.ids;

public class NMEAFieldIds {

	// identification
	public static final String NMEA_MMSI = "mmsi";
	public static final String NMEA_IMO = "imo";
	public static final String NMEA_SHIPTYPE = "shipType";
	public static final String NMEA_SHIPNAME = "shipName";
	public static final String NMEA_CALLSIGN = "callsign";

	//dynamic
	public static final String NMEA_RATE_OF_TURN = "rateOfTurn";
	public static final String NMEA_TRUE_HEADING = "trueHeading";
	public static final String NMEA_HEADING = "heading";
	public static final String NMEA_COURSE_OVER_GROUND = "courseOverGround";
	public static final String NMEA_SPEED_OVER_GROUND = "speedOverGround";

	public static final String NMEA_LATITUDE = "latitude";
	public static final String NMEA_LONGITUDE = "longitude";

	// static and voyage related
	public static final String NMEA_TO_STERN = "toStern";
	public static final String NMEA_TO_PORT = "toPort";
	public static final String NMEA_TO_STARBOARD = "toStarboard";
	public static final String NMEA_TO_BOW = "toBow";

	public static final String NMEA_NAVIGATION_STATUS = "navigationStatus";
	public static final String NMEA_DRAUGHT = "draught";

	public static final String NMEA_ETA = "eta";
	public static final String NMEA_DESTINATION = "destination";

	//special
	public static final String NMEA_SECOND = "second";

	public static final String NMEA_POSITION_ACCURACY = "positionAccurate";
	public static final String NMEA_RAIM_FLAG = "raimFlag";
	public static final String NMEA_MANEUVER_INDICATOR = "maneuverIndicator";
	public static final String NMEA_POSITION_FIXING_DEVICE = "positionFixingDevice";
	public static final String NMEA_DATA_TERMINAL_READY = "dataTerminalReady";




	// misc
	public static final String TARGET_TIMESTAMP = "targetTimestamp";
	public static final String TARGET_HISTORY = "targetHistory";
}
