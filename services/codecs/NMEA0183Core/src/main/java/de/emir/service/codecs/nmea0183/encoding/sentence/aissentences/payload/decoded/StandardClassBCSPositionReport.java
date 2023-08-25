package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 * 
 * 
 */
@XmlRootElement(name = "StandardClassBCSPositionReport")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StandardClassBCSPositionReport")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class StandardClassBCSPositionReport extends DecodedAISPayload {

	/**
	 * 
	 */
	@XmlTransient
	private static final long serialVersionUID = -5449522125711146975L;
	
	private String regionalReserved1;
	private Float speedOverGround;
	private Boolean positionAccurate;
	private Double latitude;
	private Double longitude;
	private Float courseOverGround;
	private Integer trueHeading;
	@Column(name = "time_second")
	private Integer second;
	private String regionalReserved2;
	private Boolean csUnit;
	private Boolean display;
	private Boolean dsc;
	private Boolean band;
	private Boolean message22;
	private Boolean assigned;
	private Boolean raimFlag;
	private String radioStatus;
	
	protected StandardClassBCSPositionReport() {
		super(AISMessageType.StandardClassBCSPositionReport, null, null);
		this.regionalReserved1 = null;
		this.speedOverGround = null;
		this.positionAccurate = null;
		this.latitude = null;
		this.longitude = null;
		this.courseOverGround = null;
		this.trueHeading = null;
		this.second = null;
		this.regionalReserved2 = null;
		this.csUnit = null;
		this.display = null;
		this.dsc = null;
		this.band = null;
		this.message22 = null;
		this.assigned = null;
		this.raimFlag = null;
		this.radioStatus = null;
	}
	
	public StandardClassBCSPositionReport(String originalNmea,
			Integer repeatIndicator, MMSI sourceMmsi, String regionalReserved1,
			Float speedOverGround, Boolean positionAccurate, Double latitude,
			Double longitude, Float courseOverGround, Integer trueHeading,
			Integer second, String regionalReserved2, Boolean csUnit,
			Boolean display, Boolean dsc, Boolean band, Boolean message22,
			Boolean assigned, Boolean raimFlag, String radioStatus) {
		super(originalNmea, AISMessageType.StandardClassBCSPositionReport, repeatIndicator, sourceMmsi);
		this.regionalReserved1 = regionalReserved1;
		this.speedOverGround = speedOverGround;
		this.positionAccurate = positionAccurate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.courseOverGround = courseOverGround;
		this.trueHeading = trueHeading;
		this.second = second;
		this.regionalReserved2 = regionalReserved2;
		this.csUnit = csUnit;
		this.display = display;
		this.dsc = dsc;
		this.band = band;
		this.message22 = message22;
		this.assigned = assigned;
		this.raimFlag = raimFlag;
		this.radioStatus = radioStatus;
	}
	
	public StandardClassBCSPositionReport(
			Integer repeatIndicator, MMSI sourceMmsi, String regionalReserved1,
			Float speedOverGround, Boolean positionAccurate, Double latitude,
			Double longitude, Float courseOverGround, Integer trueHeading,
			Integer second, String regionalReserved2, Boolean csUnit,
			Boolean display, Boolean dsc, Boolean band, Boolean message22,
			Boolean assigned, Boolean raimFlag, String radioStatus) {
		super(AISMessageType.StandardClassBCSPositionReport, repeatIndicator, sourceMmsi);
		this.regionalReserved1 = regionalReserved1;
		this.speedOverGround = speedOverGround;
		this.positionAccurate = positionAccurate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.courseOverGround = courseOverGround;
		this.trueHeading = trueHeading;
		this.second = second;
		this.regionalReserved2 = regionalReserved2;
		this.csUnit = csUnit;
		this.display = display;
		this.dsc = dsc;
		this.band = band;
		this.message22 = message22;
		this.assigned = assigned;
		this.raimFlag = raimFlag;
		this.radioStatus = radioStatus;
	}
	
	@Override
	public void encode() {
		message = AISPayloadEncryptionUtil.encodeStandardClassBCSPositionReport(this);
		super.encode();
	}

	public final String getRegionalReserved1() {
		return regionalReserved1;
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

	public final String getRegionalReserved2() {
		return regionalReserved2;
	}

	public final Boolean getCsUnit() {
		return csUnit;
	}

	public final Boolean getDisplay() {
		return display;
	}

	public final Boolean getDsc() {
		return dsc;
	}

	public final Boolean getBand() {
		return band;
	}

	public final Boolean getMessage22() {
		return message22;
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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StandardClassBCSPositionReport [regionalReserved1=")
				.append(regionalReserved1).append(", speedOverGround=")
				.append(speedOverGround).append(", positionAccurate=")
				.append(positionAccurate).append(", latitude=")
				.append(latitude).append(", longitude=").append(longitude)
				.append(", courseOverGround=").append(courseOverGround)
				.append(", trueHeading=").append(trueHeading)
				.append(", second=").append(second)
				.append(", regionalReserved2=").append(regionalReserved2)
				.append(", csUnit=").append(csUnit).append(", display=")
				.append(display).append(", dsc=").append(dsc).append(", band=")
				.append(band).append(", message22=").append(message22)
				.append(", assigned=").append(assigned).append(", raimFlag=")
				.append(raimFlag).append(", radioStatus=").append(radioStatus)
				.append("]");
		return builder.toString();
	}

	@Override
	public void fillMap(Map<String, Object> res) {
		if (regionalReserved1 != null) res.put("regionalReserved1", regionalReserved1);
		if (speedOverGround != null) res.put("speedOverGround", speedOverGround);
		if (positionAccurate != null) res.put("positionAccurate", positionAccurate);
		if (latitude != null) res.put("latitude", latitude);
		if (longitude != null) res.put("longitude", longitude);
		if (courseOverGround != null) res.put("courseOverGround", courseOverGround);
		if (trueHeading != null) res.put("trueHeading", trueHeading);
		if (second != null) res.put("second", second);
		if (regionalReserved2 != null) res.put("regionalReserved2", regionalReserved2);
		if (csUnit != null) res.put("csUnit", csUnit);
		if (display != null) res.put("display", display);
		if (dsc != null) res.put("dsc", dsc);
		if (band != null) res.put("band", band);
		if (message22 != null) res.put("message22", message22);
		if (assigned != null) res.put("assigned", assigned);
		if (raimFlag != null) res.put("raimFlag", raimFlag);
		if (radioStatus != null) res.put("radioStatus", radioStatus);
		super.fillMap(res);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((assigned == null) ? 0 : assigned.hashCode());
		result = prime * result + ((band == null) ? 0 : band.hashCode());
		result = prime * result + ((courseOverGround == null) ? 0 : courseOverGround.hashCode());
		result = prime * result + ((csUnit == null) ? 0 : csUnit.hashCode());
		result = prime * result + ((display == null) ? 0 : display.hashCode());
		result = prime * result + ((dsc == null) ? 0 : dsc.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((message22 == null) ? 0 : message22.hashCode());
		result = prime * result + ((positionAccurate == null) ? 0 : positionAccurate.hashCode());
		result = prime * result + ((radioStatus == null) ? 0 : radioStatus.hashCode());
		result = prime * result + ((raimFlag == null) ? 0 : raimFlag.hashCode());
		result = prime * result + ((regionalReserved1 == null) ? 0 : regionalReserved1.hashCode());
		result = prime * result + ((regionalReserved2 == null) ? 0 : regionalReserved2.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		result = prime * result + ((speedOverGround == null) ? 0 : speedOverGround.hashCode());
		result = prime * result + ((trueHeading == null) ? 0 : trueHeading.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		StandardClassBCSPositionReport other = (StandardClassBCSPositionReport) obj;
		if (assigned == null) {
			if (other.assigned != null)
				return false;
		} else if (!assigned.equals(other.assigned))
			return false;
		if (band == null) {
			if (other.band != null)
				return false;
		} else if (!band.equals(other.band))
			return false;
		if (courseOverGround == null) {
			if (other.courseOverGround != null)
				return false;
		} else if (!courseOverGround.equals(other.courseOverGround))
			return false;
		if (csUnit == null) {
			if (other.csUnit != null)
				return false;
		} else if (!csUnit.equals(other.csUnit))
			return false;
		if (display == null) {
			if (other.display != null)
				return false;
		} else if (!display.equals(other.display))
			return false;
		if (dsc == null) {
			if (other.dsc != null)
				return false;
		} else if (!dsc.equals(other.dsc))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (message22 == null) {
			if (other.message22 != null)
				return false;
		} else if (!message22.equals(other.message22))
			return false;
		if (positionAccurate == null) {
			if (other.positionAccurate != null)
				return false;
		} else if (!positionAccurate.equals(other.positionAccurate))
			return false;
		if (radioStatus == null) {
			if (other.radioStatus != null)
				return false;
		} else if (!radioStatus.equals(other.radioStatus))
			return false;
		if (raimFlag == null) {
			if (other.raimFlag != null)
				return false;
		} else if (!raimFlag.equals(other.raimFlag))
			return false;
		if (regionalReserved1 == null) {
			if (other.regionalReserved1 != null)
				return false;
		} else if (!regionalReserved1.equals(other.regionalReserved1))
			return false;
		if (regionalReserved2 == null) {
			if (other.regionalReserved2 != null)
				return false;
		} else if (!regionalReserved2.equals(other.regionalReserved2))
			return false;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		if (speedOverGround == null) {
			if (other.speedOverGround != null)
				return false;
		} else if (!speedOverGround.equals(other.speedOverGround))
			return false;
		if (trueHeading == null) {
			if (other.trueHeading != null)
				return false;
		} else if (!trueHeading.equals(other.trueHeading))
			return false;
		return true;
	}

	/**
	 * @param regionalReserved1 the regionalReserved1 to set
	 */
	public void setRegionalReserved1(String regionalReserved1) {
		this.regionalReserved1 = regionalReserved1;
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
	 * @param regionalReserved2 the regionalReserved2 to set
	 */
	public void setRegionalReserved2(String regionalReserved2) {
		this.regionalReserved2 = regionalReserved2;
	}

	/**
	 * @param csUnit the csUnit to set
	 */
	public void setCsUnit(Boolean csUnit) {
		this.csUnit = csUnit;
	}

	/**
	 * @param display the display to set
	 */
	public void setDisplay(Boolean display) {
		this.display = display;
	}

	/**
	 * @param dsc the dsc to set
	 */
	public void setDsc(Boolean dsc) {
		this.dsc = dsc;
	}

	/**
	 * @param band the band to set
	 */
	public void setBand(Boolean band) {
		this.band = band;
	}

	/**
	 * @param message22 the message22 to set
	 */
	public void setMessage22(Boolean message22) {
		this.message22 = message22;
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
