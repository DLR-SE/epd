package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.NavigationStatus;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LongRangeAISBroadcastMessage", propOrder = {
		"positionAccuracy",
		"raimFlag",
		"navigationStatus",
		"longitude",
		"latitude",
		"speedOverGround",
		"courseOverGround",
		"gnssPositionStatus"
})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class LongRangeAISBroadcastMessage extends DecodedAISPayload {

	/**
	 * 
	 */
	@XmlTransient
	private static final long serialVersionUID = 4550514676446539125L;
	
	private Boolean positionAccuracy;
	private Boolean raimFlag;
	private NavigationStatus navigationStatus;
	private Double longitude;
	private Double latitude;
	private Float speedOverGround;
	private Float courseOverGround;
	private Boolean gnssPositionStatus;
	
	public LongRangeAISBroadcastMessage(String originalNMEA, Integer repeatIndicator,
			MMSI mmsi, Boolean positionAccuracy, Boolean raimFlag,
			NavigationStatus navigationStatus, Double longitude, Double latitude, 
			Float speedOverGround, Float courseOverGround, Boolean gnssPositionStatus) {
		super(originalNMEA, AISMessageType.PositionReportForLongRangeApplications, repeatIndicator, mmsi);
		this.positionAccuracy = positionAccuracy;
		this.raimFlag = raimFlag;
		this.navigationStatus = navigationStatus;
		this.longitude = longitude;
		this.latitude = latitude;
		this.speedOverGround = speedOverGround;
		this.courseOverGround = courseOverGround;
		this.gnssPositionStatus = gnssPositionStatus;
	}
	
	public LongRangeAISBroadcastMessage(Integer repeatIndicator,
			MMSI mmsi, Boolean positionAccuracy, Boolean raimFlag,
			NavigationStatus navigationStatus, Double longitude, Double latitude, 
			Float speedOverGround, Float courseOverGround, Boolean gnssPositionStatus) {
		super(AISMessageType.PositionReportForLongRangeApplications, repeatIndicator, mmsi);
		this.positionAccuracy = positionAccuracy;
		this.raimFlag = raimFlag;
		this.navigationStatus = navigationStatus;
		this.longitude = longitude;
		this.latitude = latitude;
		this.speedOverGround = speedOverGround;
		this.courseOverGround = courseOverGround;
		this.gnssPositionStatus = gnssPositionStatus;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LongRangeAISBroadcastMessage [positionAccuracy")
				.append(getPositionAccuracy()).append(", raimFlag=").append(getRaimFlag())
				.append(", navigationStatus=").append(getNavigationStatus())
				.append(", longitude=").append(getLongitude())
				.append(", latitude=").append(getLatitude())
				.append(", speedOverGround=").append(getSpeedOverGround())
				.append(", courseOverGround=").append(getCourseOverGround())
				.append(", gnssPositionStatus=").append(getGnssPositionStatus()).append("]");
		return builder.toString();
	}

	@Override
	public void fillMap(Map<String, Object> res) {	
		if (getPositionAccuracy() != null) res.put("positionAccuracy", getPositionAccuracy());
		if (getRaimFlag() != null) res.put("raimFlag", getRaimFlag());
		if (getNavigationStatus() != null) res.put("navigationStatus", getNavigationStatus());
		if (getLongitude() != null) res.put("longitude", getLongitude());
		if (getLatitude() != null) res.put("latitude", getLatitude());
		if (getSpeedOverGround() != null) res.put("speedOverGround", getSpeedOverGround());
		if (getCourseOverGround() != null) res.put("courseOverGround", getCourseOverGround());
		if (getGnssPositionStatus() != null) res.put("gnssPositionStatus", getGnssPositionStatus());
		if (getSourceMmsi() != null) res.put("sourceMmsi", getSourceMmsi().getMMSI());
	}

	/**
	 * @return the positionAccuracy
	 */
	public final Boolean getPositionAccuracy() {
		return positionAccuracy;
	}

	/**
	 * @param positionAccuracy the positionAccuracy to set
	 */
	public void setPositionAccuracy(Boolean positionAccuracy) {
		this.positionAccuracy = positionAccuracy;
	}

	/**
	 * @return the raimFlag
	 */
	public final Boolean getRaimFlag() {
		return raimFlag;
	}

	/**
	 * @param raimFlag the raimFlag to set
	 */
	public void setRaimFlag(Boolean raimFlag) {
		this.raimFlag = raimFlag;
	}

	/**
	 * @return the navigationStatus
	 */
	public final NavigationStatus getNavigationStatus() {
		return navigationStatus;
	}

	/**
	 * @param navigationStatus the navigationStatus to set
	 */
	public void setNavigationStatus(NavigationStatus navigationStatus) {
		this.navigationStatus = navigationStatus;
	}

	/**
	 * @return the longitude
	 */
	public final Double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public final Double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the speedOverGround
	 */
	public final Float getSpeedOverGround() {
		return speedOverGround;
	}

	/**
	 * @param speedOverGround the speedOverGround to set
	 */
	public void setSpeedOverGround(Float speedOverGround) {
		this.speedOverGround = speedOverGround;
	}

	/**
	 * @return the courseOverGround
	 */
	public final Float getCourseOverGround() {
		return courseOverGround;
	}

	/**
	 * @param courseOverGround the courseOverGround to set
	 */
	public void setCourseOverGround(Float courseOverGround) {
		this.courseOverGround = courseOverGround;
	}

	/**
	 * @return the gnssPositionStatus
	 */
	public final Boolean getGnssPositionStatus() {
		return gnssPositionStatus;
	}

	/**
	 * @param gnssPositionStatus the gnssPositionStatus to set
	 */
	public void setGnssPositionStatus(Boolean gnssPositionStatus) {
		this.gnssPositionStatus = gnssPositionStatus;
	}

}
