
/**
 * 
 */
package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.ManeuverIndicator;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.NavigationStatus;


/**
 *  
 *
 */
//@XmlRootElement(name = "PositionReport")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PositionReport", propOrder = {
	    "navigationStatus",
	    "rateOfTurn",
	    "speedOverGround",
	    "positionAccurate",
	    "latitude",
	    "longitude",
	    "courseOverGround",
	    "trueHeading",
	    "second",
	    "maneuverIndicator",
	    "raimFlag"
})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PositionReport extends DecodedAISPayload {

	/**
	 * 
	 */
	@XmlTransient
	private static final long serialVersionUID = 5139598546090052205L;
	@Enumerated(EnumType.ORDINAL)
	private NavigationStatus navigationStatus;
	private Integer rateOfTurn;
	private Float speedOverGround;
	private Boolean positionAccurate;
	private Double latitude;
	private Double longitude;
	private Float courseOverGround;
	private Integer trueHeading;
	@Column(name = "time_second")
	private Integer second;
	private ManeuverIndicator maneuverIndicator;
	private Boolean raimFlag;
	
	private PositionReport() {
		super(AISMessageType.PositionReportClassA, null, null);
		this.setNavigationStatus(null);
		this.setRateOfTurn(null);
		this.setSpeedOverGround(null);
		this.setPositionAccurate(null);
		this.setLatitude(null);
		this.setLongitude(null);
		this.setCourseOverGround(null);
		this.setTrueHeading(null);
		this.setSecond(null);
		this.setManeuverIndicator(null);
		this.setRaimFlag(null);
	}
	
	protected PositionReport(String originalNmea, AISMessageType messageType,
			Integer repeatIndicator, MMSI sourceMmsi,
			NavigationStatus navigationStatus, Integer rateOfTurn,
			Float speedOverGround, Boolean positionAccurate, Double latitude,
			Double longitude, Float courseOverGround, Integer trueHeading,
			Integer second, ManeuverIndicator maneuverIndicator,
			Boolean raimFlag) {
		super(originalNmea, messageType, repeatIndicator, sourceMmsi);
		this.setNavigationStatus(navigationStatus);
		this.setRateOfTurn(rateOfTurn);
		this.setSpeedOverGround(speedOverGround);
		this.setPositionAccurate(positionAccurate);
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setCourseOverGround(courseOverGround);
		this.setTrueHeading(trueHeading);
		this.setSecond(second);
		this.setManeuverIndicator(maneuverIndicator);
		this.setRaimFlag(raimFlag);
	}
	
	protected PositionReport(AISMessageType messageType,
			Integer repeatIndicator, MMSI sourceMmsi,
			NavigationStatus navigationStatus, Integer rateOfTurn,
			Float speedOverGround, Boolean positionAccurate, Double latitude,
			Double longitude, Float courseOverGround, Integer trueHeading,
			Integer second, ManeuverIndicator maneuverIndicator,
			Boolean raimFlag) {
		super(messageType, repeatIndicator, sourceMmsi);
		this.setNavigationStatus(navigationStatus);
		this.setRateOfTurn(rateOfTurn);
		this.setSpeedOverGround(speedOverGround);
		this.setPositionAccurate(positionAccurate);
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setCourseOverGround(courseOverGround);
		this.setTrueHeading(trueHeading);
		this.setSecond(second);
		this.setManeuverIndicator(maneuverIndicator);
		this.setRaimFlag(raimFlag);
	}
		
	public final NavigationStatus getNavigationStatus() {
		return navigationStatus;
	}

	public final Integer getRateOfTurn() {
		return rateOfTurn;
	}

	public final Float getSpeedOverGround() {
		return speedOverGround;
	}

	public final Boolean getPositionAccurate() {
		return positionAccurate;
	}

	public final Double getLatitude() {
		return latitude;
	}

	public final Double getLongitude() {
		return longitude;
	}

	public final Float getCourseOverGround() {
		return courseOverGround;
	}

	public final Integer getTrueHeading() {
		return trueHeading;
	}

	public final Integer getSecond() {
		return second;
	}

	public final ManeuverIndicator getManeuverIndicator() {
		return maneuverIndicator;
	}

	public final Boolean getRaimFlag() {
		return raimFlag;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PositionReport [navigationStatus=")
				.append(getNavigationStatus()).append(", rateOfTurn=")
				.append(getRateOfTurn()).append(", speedOverGround=")
				.append(getSpeedOverGround()).append(", positionAccurate=")
				.append(getPositionAccurate()).append(", latitude=")
				.append(getLatitude()).append(", longitude=").append(getLongitude())
				.append(", courseOverGround=").append(getCourseOverGround())
				.append(", trueHeading=").append(getTrueHeading())
				.append(", second=").append(getSecond())
				.append(", maneuverIndicator=").append(getManeuverIndicator())
				.append(", raimFlag=").append(getRaimFlag())
				.append(", getMessageType()=").append(getMessageType())
				.append(", getRepeatIndicator()=").append(getRepeatIndicator())
				.append(", getSourceMmsi()=").append(getSourceMmsi())
				.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((getCourseOverGround() == null) ? 0 : getCourseOverGround().hashCode());
		result = prime * result
				+ ((getLatitude() == null) ? 0 : getLatitude().hashCode());
		result = prime * result
				+ ((getLongitude() == null) ? 0 : getLongitude().hashCode());
		result = prime
				* result
				+ ((getManeuverIndicator() == null) ? 0 : getManeuverIndicator()
						.hashCode());
		result = prime
				* result
				+ ((getNavigationStatus() == null) ? 0 : getNavigationStatus().hashCode());
		result = prime
				* result
				+ ((getPositionAccurate() == null) ? 0 : getPositionAccurate().hashCode());
		result = prime * result
				+ ((getRaimFlag() == null) ? 0 : getRaimFlag().hashCode());
		result = prime * result
				+ ((getRateOfTurn() == null) ? 0 : getRateOfTurn().hashCode());
		result = prime * result + ((getSecond() == null) ? 0 : getSecond().hashCode());
		result = prime * result
				+ ((getSpeedOverGround() == null) ? 0 : getSpeedOverGround().hashCode());
		result = prime * result
				+ ((getTrueHeading() == null) ? 0 : getTrueHeading().hashCode());
		return result;
	}
	
	@Override
	public void fillMap(Map<String, Object> res) {
		if (getNavigationStatus() != null)
			res.put("navigationStatus", getNavigationStatus());
		if (getRateOfTurn() != null)
			res.put("rateOfTurn", getRateOfTurn());
		if (getSpeedOverGround() != null)
			res.put("speedOverGround", getSpeedOverGround());
		if (getPositionAccurate() != null)
			res.put("positionAccurate", getPositionAccurate());
		if (getLatitude() != null)
			res.put("latitude", getLatitude());
		if (getLongitude() != null)
			res.put("longitude", getLongitude());
		if (getCourseOverGround() != null)
			res.put("courseOverGround", getCourseOverGround());
		if (getTrueHeading() != null)
			res.put("trueHeading", getTrueHeading());
		if (getSecond() != null)
			res.put("second", getSecond());
		if (getManeuverIndicator() != null)
			res.put("maneuverIndicator", getManeuverIndicator());
		if (getRaimFlag() != null)
			res.put("raimFlag", getRaimFlag());
		if (getSourceMmsi() != null)
			res.put("sourceMmsi", getSourceMmsi().getMMSI());
		super.fillMap(res);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PositionReport))
			return false;
		PositionReport other = (PositionReport) obj;
		if (getCourseOverGround() == null) {
			if (other.getCourseOverGround() != null)
				return false;
		} else if (!getCourseOverGround().equals(other.getCourseOverGround()))
			return false;
		if (getLatitude() == null) {
			if (other.getLatitude() != null)
				return false;
		} else if (!getLatitude().equals(other.getLatitude()))
			return false;
		if (getLongitude() == null) {
			if (other.getLongitude() != null)
				return false;
		} else if (!getLongitude().equals(other.getLongitude()))
			return false;
		if (getManeuverIndicator() != other.getManeuverIndicator())
			return false;
		
		if (messageType == null) {
			if(other.messageType != null){
				return false;
			}
		} else if (messageType != other.messageType) {
			return false;
		}
		if (getNavigationStatus() != other.getNavigationStatus())
			return false;
		if (getPositionAccurate() == null) {
			if (other.getPositionAccurate() != null)
				return false;
		} else if (!getPositionAccurate().equals(other.getPositionAccurate()))
			return false;
		if (getRaimFlag() == null) {
			if (other.getRaimFlag() != null)
				return false;
		} else if (!getRaimFlag().equals(other.getRaimFlag()))
			return false;
		if (getRateOfTurn() == null) {
			if (other.getRateOfTurn() != null)
				return false;
		} else if (!getRateOfTurn().equals(other.getRateOfTurn()))
			return false;
		if (getSecond() == null) {
			if (other.getSecond() != null)
				return false;
		} else if (!getSecond().equals(other.getSecond()))
			return false;
		if (getSpeedOverGround() == null) {
			if (other.getSpeedOverGround() != null)
				return false;
		} else if (!getSpeedOverGround().equals(other.getSpeedOverGround()))
			return false;
		if (getTrueHeading() == null) {
			if (other.getTrueHeading() != null)
				return false;
		} else if (!getTrueHeading().equals(other.getTrueHeading()))
			return false;
		return true;
	}

	/**
	 * @param navigationStatus the navigationStatus to set
	 */
	public void setNavigationStatus(NavigationStatus navigationStatus) {
		this.navigationStatus = navigationStatus;
	}

	/**
	 * @param rateOfTurn the rateOfTurn to set
	 */
	public void setRateOfTurn(Integer rateOfTurn) {
		this.rateOfTurn = rateOfTurn;
	}

	/**
	 * @param speedOverGround the speedOverGround to set
	 */
	public void setSpeedOverGround(Float speedOverGround) {
		this.speedOverGround = speedOverGround;
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
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @param courseOverGround the courseOverGround to set
	 */
	public void setCourseOverGround(Float courseOverGround) {
		this.courseOverGround = courseOverGround;
	}

	/**
	 * @param trueHeading the trueHeading to set
	 */
	public void setTrueHeading(Integer trueHeading) {
		this.trueHeading = trueHeading;
	}

	/**
	 * @param second the second to set
	 */
	public void setSecond(Integer second) {
		this.second = second;
	}

	/**
	 * @param maneuverIndicator the maneuverIndicator to set
	 */
	public void setManeuverIndicator(ManeuverIndicator maneuverIndicator) {
		this.maneuverIndicator = maneuverIndicator;
	}

	/**
	 * @param raimFlag the raimFlag to set
	 */
	public void setRaimFlag(Boolean raimFlag) {
		this.raimFlag = raimFlag;
	}
}
