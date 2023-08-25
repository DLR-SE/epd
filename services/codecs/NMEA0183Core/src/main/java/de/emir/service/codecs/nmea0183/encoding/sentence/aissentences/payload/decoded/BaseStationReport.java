package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.encryption.AISPayloadEncryptionUtil;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.PositionFixingDevice;

/**
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseStationReport", propOrder = {
		"year",
		"month",
		"day",
		"hour",
		"minute",
		"second",
		"positionAccurate",
		"latitude",
		"longitude",
		"positionFixingDevice",
		"raimFlag"
})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class BaseStationReport extends DecodedAISPayload {
	
	/**
	 * 
	 */
	@XmlTransient
	private static final long serialVersionUID = -625299261384226707L;
	
	private Integer year;
	private Integer month;
	private Integer day;
	private Integer hour;
	private Integer minute;
	private Integer second;
	private Boolean positionAccurate;
	private Float latitude;
	private Float longitude;
	@Enumerated(EnumType.STRING)
	private PositionFixingDevice positionFixingDevice;
	private Boolean raimFlag;
	
	private BaseStationReport(){
		super(AISMessageType.BaseStationReport,null,null);
		this.setYear(null);
		this.setMonth(null);
		this.setDay(null);
		this.setHour(null);
		this.setMinute(null);
		this.setSecond(null);
		this.setPositionAccurate(null);
		this.setLatitude(null);
		this.setLongitude(null);
		this.setPositionFixingDevice(null);
		this.setRaimFlag(null);
	}
	
	public BaseStationReport(String originalNmea,
			Integer repeatIndicator, MMSI sourceMmsi, Integer year,
			Integer month, Integer day, Integer hour, Integer minute,
			Integer second, Boolean positionAccurate, Float latitude,
			Float longitude, PositionFixingDevice positionFixingDevice,
			Boolean raimFlag) {
		super(originalNmea, AISMessageType.BaseStationReport, repeatIndicator, sourceMmsi);
		this.setYear(year);
		this.setMonth(month);
		this.setDay(day);
		this.setHour(hour);
		this.setMinute(minute);
		this.setSecond(second);
		this.setPositionAccurate(positionAccurate);
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setPositionFixingDevice(positionFixingDevice);
		this.setRaimFlag(raimFlag);
	}
	
	public BaseStationReport(Integer repeatIndicator, MMSI sourceMmsi, Integer year,
			Integer month, Integer day, Integer hour, Integer minute,
			Integer second, Boolean positionAccurate, Float latitude,
			Float longitude, PositionFixingDevice positionFixingDevice,
			Boolean raimFlag) {
		super(AISMessageType.BaseStationReport, repeatIndicator, sourceMmsi);
		this.setYear(year);
		this.setMonth(month);
		this.setDay(day);
		this.setHour(hour);
		this.setMinute(minute);
		this.setSecond(second);
		this.setPositionAccurate(positionAccurate);
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setPositionFixingDevice(positionFixingDevice);
		this.setRaimFlag(raimFlag);
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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseStationReport [year=").append(getYear())
				.append(", month=").append(getMonth()).append(", day=").append(getDay())
				.append(", hour=").append(getHour()).append(", minute=")
				.append(getMinute()).append(", second=").append(getSecond())
				.append(", positionAccurate=").append(getPositionAccurate())
				.append(", latitude=").append(getLatitude()).append(", longitude=")
				.append(getLongitude()).append(", positionFixingDevice=")
				.append(getPositionFixingDevice()).append(", raimFlag=")
				.append(getRaimFlag()).append("]");
		return builder.toString();
	}
	
	@Override
	public void encode() {
		this.message = AISPayloadEncryptionUtil.encodeBaseStationReport(this);
		super.encode();
	}
	@Override
	public void fillMap(Map<String, Object> res) {		
		if (getYear() != null) res.put("year", getYear());
		if (getMonth() != null) res.put("month", getMonth());
		if (getDay() != null) res.put("day", getDay());
		if (getHour() != null) res.put("hour", getHour());
		if (getMinute() != null) res.put("minute", getMinute());
		if (getSecond() != null) res.put("second", getSecond());
		if (getPositionAccurate() != null) res.put("positionAccurate", getPositionAccurate());
		if (getLatitude() != null) res.put("latitude", getLatitude());
		if (getLongitude() != null) res.put("longitude", getLongitude());
		if (getPositionFixingDevice() != null) res.put("positionFixingDevice", getPositionFixingDevice());
		if (getRaimFlag() != null) res.put("raimFlag", getRaimFlag());
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(Integer day) {
		this.day = day;
	}

	/**
	 * @param hour the hour to set
	 */
	public void setHour(Integer hour) {
		this.hour = hour;
	}

	/**
	 * @param minute the minute to set
	 */
	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	/**
	 * @param second the second to set
	 */
	public void setSecond(Integer second) {
		this.second = second;
	}

	/**
	 * @param positionAccurate the positionAccurate to set
	 */
	public void setPositionAccurate(Boolean positionAccurate) {
		this.positionAccurate = positionAccurate;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	/**
	 * @param positionFixingDevice the positionFixingDevice to set
	 */
	public void setPositionFixingDevice(PositionFixingDevice positionFixingDevice) {
		this.positionFixingDevice = positionFixingDevice;
	}

	/**
	 * @param raimFlag the raimFlag to set
	 */
	public void setRaimFlag(Boolean raimFlag) {
		this.raimFlag = raimFlag;
	}
}
