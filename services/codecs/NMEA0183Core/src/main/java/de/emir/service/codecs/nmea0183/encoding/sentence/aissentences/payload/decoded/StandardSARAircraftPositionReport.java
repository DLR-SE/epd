package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.encryption.AISPayloadEncryptionUtil;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StandardSARAircraftPositionReport", propOrder = { 
		"altitude", 
		"speed", 
		"positionAccurate", 
		"latitude", 
		"longitude", 
		"courseOverGround",
		"second", 
		"regionalReserved", 
		"dataTerminalReady", 
		"assigned", 
		"raimFlag", 
		"radioStatus" 
})
public class StandardSARAircraftPositionReport extends DecodedAISPayload {

	/**
	 * 
	 */
	@XmlTransient
	private static final long serialVersionUID = -6474280807670439357L;

	private Integer altitude;
	private Integer speed;
	private Boolean positionAccurate;
	private Float latitude;
	private Float longitude;
	private Float courseOverGround;
	private Integer second;
	private String regionalReserved;
	private Boolean dataTerminalReady;
	private Boolean assigned;
	private Boolean raimFlag;
	private String radioStatus;

	private StandardSARAircraftPositionReport() {
		super(AISMessageType.StandardSARAircraftPositionReport, null, null);
		this.setAltitude(null);
		this.setSpeed(null);
		this.setPositionAccurate(null);
		this.setLatitude(null);
		this.setLongitude(null);
		this.setCourseOverGround(null);
		this.setSecond(null);
		this.setRegionalReserved(null);
		this.setDataTerminalReady(null);
		this.setAssigned(null);
		this.setRaimFlag(null);
		this.setRadioStatus(null);
	}

	public StandardSARAircraftPositionReport(String originalNmea, Integer repeatIndicator, MMSI sourceMmsi, Integer altitude, Integer speed,
			Boolean positionAccurate, Float latitude, Float longitude, Float courseOverGround, Integer second, String regionalReserved,
			Boolean dataTerminalReady, Boolean assigned, Boolean raimFlag, String radioStatus) {
		super(originalNmea, AISMessageType.StandardSARAircraftPositionReport, repeatIndicator, sourceMmsi);
		this.setAltitude(altitude);
		this.setSpeed(speed);
		this.setPositionAccurate(positionAccurate);
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setCourseOverGround(courseOverGround);
		this.setSecond(second);
		this.setRegionalReserved(regionalReserved);
		this.setDataTerminalReady(dataTerminalReady);
		this.setAssigned(assigned);
		this.setRaimFlag(raimFlag);
		this.setRadioStatus(radioStatus);
	}
	
	public StandardSARAircraftPositionReport(Integer repeatIndicator, MMSI sourceMmsi, Integer altitude, Integer speed,
			Boolean positionAccurate, Float latitude, Float longitude, Float courseOverGround, Integer second, String regionalReserved,
			Boolean dataTerminalReady, Boolean assigned, Boolean raimFlag, String radioStatus) {
		super(AISMessageType.StandardSARAircraftPositionReport, repeatIndicator, sourceMmsi);
		this.setAltitude(altitude);
		this.setSpeed(speed);
		this.setPositionAccurate(positionAccurate);
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setCourseOverGround(courseOverGround);
		this.setSecond(second);
		this.setRegionalReserved(regionalReserved);
		this.setDataTerminalReady(dataTerminalReady);
		this.setAssigned(assigned);
		this.setRaimFlag(raimFlag);
		this.setRadioStatus(radioStatus);
	}

	public final Integer getAltitude() {
		return altitude;
	}

	public final Integer getSpeed() {
		return speed;
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

	public final Float getCourseOverGround() {
		return courseOverGround;
	}

	public final Integer getSecond() {
		return second;
	}

	public final String getRegionalReserved() {
		return regionalReserved;
	}

	public final Boolean getDataTerminalReady() {
		return dataTerminalReady;
	}

	public final Boolean getAssigned() {
		return assigned;
	}

	public final Boolean getRaimFlag() {
		return raimFlag;
	}

	public final String getRadioStatus() {
		return radioStatus;
	}
	
	@Override
	public void encode() {
		this.message = AISPayloadEncryptionUtil.encodeStandardSARAircraftPositionReport(this);
		super.encode();
	}

	@Override
	public void fillMap(Map<String, Object> res) {
		if (getAltitude() != null) res.put("altitude", getAltitude());
		if (getSpeed() != null) res.put("speed", getSpeed());
		if (getPositionAccurate() != null) res.put("positionAccurate", getPositionAccurate());
		if (getLatitude() != null) res.put("latitude", getLatitude());
		if (getLongitude() != null) res.put("longitude", getLongitude());
		if (getCourseOverGround() != null) res.put("courseOverGround", getCourseOverGround());
		if (getSecond() != null) res.put("second", getSecond());
		if (getRegionalReserved() != null) res.put("regionalReserved", getRegionalReserved());
		if (getDataTerminalReady() != null) res.put("dataTerminalReady", getDataTerminalReady());
		if (getAssigned() != null) res.put("assigned", getAssigned());
		if (getRaimFlag() != null) res.put("raimFlag", getRaimFlag());
		if (getRadioStatus() != null) res.put("radioStatus", getRadioStatus());
		if (getSourceMmsi() != null) res.put("sourceMmsi", getSourceMmsi().getMMSI());
	}

	/**
	 * @param altitude the altitude to set
	 */
	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(Integer speed) {
		this.speed = speed;
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
	 * @param courseOverGround the courseOverGround to set
	 */
	public void setCourseOverGround(Float courseOverGround) {
		this.courseOverGround = courseOverGround;
	}

	/**
	 * @param second the second to set
	 */
	public void setSecond(Integer second) {
		this.second = second;
	}

	/**
	 * @param regionalReserved the regionalReserved to set
	 */
	public void setRegionalReserved(String regionalReserved) {
		this.regionalReserved = regionalReserved;
	}

	/**
	 * @param dataTerminalReady the dataTerminalReady to set
	 */
	public void setDataTerminalReady(Boolean dataTerminalReady) {
		this.dataTerminalReady = dataTerminalReady;
	}

	/**
	 * @param assigned the assigned to set
	 */
	public void setAssigned(Boolean assigned) {
		this.assigned = assigned;
	}

	/**
	 * @param raimFlag the raimFlag to set
	 */
	public void setRaimFlag(Boolean raimFlag) {
		this.raimFlag = raimFlag;
	}

	/**
	 * @param radioStatus the radioStatus to set
	 */
	public void setRadioStatus(String radioStatus) {
		this.radioStatus = radioStatus;
	}
}
