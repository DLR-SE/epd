package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded;

import java.util.Map;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.PositionFixingDevice;

public class UTCAndDateResponse extends DecodedAISPayload {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5864506371366705127L;
	
	private final Integer year;
	private final Integer month;
	private final Integer day;
	private final Integer hour;
	private final Integer minute;
	private final Integer second;
	private final Boolean positionAccurate;
	private final Float latitude;
	private final Float longitude;
	private final PositionFixingDevice positionFixingDevice;
	private final Boolean raimFlag;
	
	public UTCAndDateResponse(String originalNmea,
			Integer repeatIndicator, MMSI sourceMmsi, Integer year,
			Integer month, Integer day, Integer hour, Integer minute,
			Integer second, Boolean positionAccurate, Float latitude,
			Float longitude, PositionFixingDevice positionFixingDevice,
			Boolean raimFlag) {
		super(originalNmea, AISMessageType.UTCAndDateResponse, repeatIndicator, sourceMmsi);
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.positionAccurate = positionAccurate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.positionFixingDevice = positionFixingDevice;
		this.raimFlag = raimFlag;
	}
	public final Integer getYear() {
		return year;
	}
	public final Integer getMonth() {
		return month;
	}
	public final Integer getDay() {
		return day;
	}
	public final Integer getHour() {
		return hour;
	}
	public final Integer getMinute() {
		return minute;
	}
	public final Integer getSecond() {
		return second;
	}
	public final Boolean getPositionAccurate() {
		return positionAccurate;
	}
	public final Float getLatitude() {
		return latitude;
	}
	public final Float getLongitude() {
		return longitude;
	}
	public final PositionFixingDevice getPositionFixingDevice() {
		return positionFixingDevice;
	}
	public final Boolean getRaimFlag() {
		return raimFlag;
	}
	
	@Override
	public void fillMap(Map<String, Object> res) {
		if (year != null) res.put("year", year);
		if (month != null) res.put("month", month);
		if (day != null) res.put("day", day);
		if (hour != null) res.put("hour", hour);
		if (minute != null) res.put("minute", minute);
		if (second != null) res.put("second", second);
		if (positionAccurate != null) res.put("positionAccurate", positionAccurate);
		if (latitude != null) res.put("latitude", latitude);
		if (longitude != null) res.put("longitude", longitude);
		if (positionFixingDevice != null) res.put("positionFixingDevice", positionFixingDevice);
		if (raimFlag != null) res.put("raimFlag", raimFlag);
	}
}
