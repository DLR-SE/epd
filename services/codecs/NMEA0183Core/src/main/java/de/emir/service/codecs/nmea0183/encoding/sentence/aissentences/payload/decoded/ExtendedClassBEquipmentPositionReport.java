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
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.ShipType;

@XmlRootElement(name = "ExtendedClassBEquipmentPositionReport")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtendedClassBEquipmentPositionReport")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ExtendedClassBEquipmentPositionReport extends DecodedAISPayload {

	/**
	 * 
	 */
	@XmlTransient
	private static final long serialVersionUID = -7308814923522151815L;
	
	private String regionalReserved1;
	private Float speedOverGround;
	private Boolean positionAccurate;
	private Double latitude;
	private Double longitude;
	private Float courseOverGround;
	private Integer trueHeading;
	private Integer second;
	private String regionalReserved2;
	private String shipName;
	 @Enumerated(EnumType.ORDINAL)
	private ShipType shipType;
	private Integer toBow;
	private Integer toStern;
	private Integer toStarboard;
	private Integer toPort;
	 @Enumerated(EnumType.ORDINAL)
	private PositionFixingDevice positionFixingDevice;
	private Boolean raimFlag;
	private Boolean dataTerminalReady;
	private Boolean assigned;
	
	public ExtendedClassBEquipmentPositionReport() {
		super(AISMessageType.ExtendedClassBEquipmentPositionReport, null, null);
		this.regionalReserved1 = null;
		this.speedOverGround = null;
		this.positionAccurate = null;
		this.latitude = null;
		this.longitude = null;
		this.courseOverGround = null;
		this.trueHeading = null;
		this.second = null;
		this.regionalReserved2 = null;
		this.shipName = null;
		this.shipType = null;
		this.toBow = null;
		this.toStern = null;
		this.toStarboard = null;
		this.toPort = null;
		this.positionFixingDevice = null;
		this.raimFlag = null;
		this.dataTerminalReady = null;
		this.assigned = null;
	}
	
	public ExtendedClassBEquipmentPositionReport(String originalNmea,
			Integer repeatIndicator, MMSI sourceMmsi, String regionalReserved1,
			Float speedOverGround, Boolean positionAccurate, Double latitude,
			Double longitude, Float courseOverGround, Integer trueHeading,
			Integer second, String regionalReserved2, String shipName,
			ShipType shipType, Integer toBow, Integer toStern,
			Integer toStarboard, Integer toPort,
			PositionFixingDevice positionFixingDevice, Boolean raimFlag,
			Boolean dataTerminalReady, Boolean assigned) {
		super(originalNmea, AISMessageType.ExtendedClassBEquipmentPositionReport, repeatIndicator, sourceMmsi);
		this.regionalReserved1 = regionalReserved1;
		this.speedOverGround = speedOverGround;
		this.positionAccurate = positionAccurate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.courseOverGround = courseOverGround;
		this.trueHeading = trueHeading;
		this.second = second;
		this.regionalReserved2 = regionalReserved2;
		this.shipName = shipName;
		this.shipType = shipType;
		this.toBow = toBow;
		this.toStern = toStern;
		this.toStarboard = toStarboard;
		this.toPort = toPort;
		this.positionFixingDevice = positionFixingDevice;
		this.raimFlag = raimFlag;
		this.dataTerminalReady = dataTerminalReady;
		this.assigned = assigned;
	}
	
	public ExtendedClassBEquipmentPositionReport(
			Integer repeatIndicator, MMSI sourceMmsi, String regionalReserved1,
			Float speedOverGround, Boolean positionAccurate, Double latitude,
			Double longitude, Float courseOverGround, Integer trueHeading,
			Integer second, String regionalReserved2, String shipName,
			ShipType shipType, Integer toBow, Integer toStern,
			Integer toStarboard, Integer toPort,
			PositionFixingDevice positionFixingDevice, Boolean raimFlag,
			Boolean dataTerminalReady, Boolean assigned) {
		super(AISMessageType.ExtendedClassBEquipmentPositionReport, repeatIndicator, sourceMmsi);
		this.regionalReserved1 = regionalReserved1;
		this.speedOverGround = speedOverGround;
		this.positionAccurate = positionAccurate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.courseOverGround = courseOverGround;
		this.trueHeading = trueHeading;
		this.second = second;
		this.regionalReserved2 = regionalReserved2;
		this.shipName = shipName;
		this.shipType = shipType;
		this.toBow = toBow;
		this.toStern = toStern;
		this.toStarboard = toStarboard;
		this.toPort = toPort;
		this.positionFixingDevice = positionFixingDevice;
		this.raimFlag = raimFlag;
		this.dataTerminalReady = dataTerminalReady;
		this.assigned = assigned;
	}
	
	public ExtendedClassBEquipmentPositionReport(ExtendedClassBEquipmentPositionReport copy) {
        this.messageType = AISMessageType.ExtendedClassBEquipmentPositionReport;
        this.repeatIndicator = copy.repeatIndicator;
        this.sourceMmsi = new MMSI(copy.sourceMmsi);
		this.regionalReserved1 = copy.regionalReserved1;
		this.speedOverGround = copy.speedOverGround;
		this.positionAccurate = copy.positionAccurate;
		this.latitude = copy.latitude;
		this.longitude = copy.longitude;
		this.courseOverGround = copy.courseOverGround;
		this.trueHeading = copy.trueHeading;
		this.second = copy.second;
		this.regionalReserved2 = copy.regionalReserved2;
		this.shipName = copy.shipName;
		this.shipType = copy.shipType;
		this.toBow = copy.toBow;
		this.toStern = copy.toStern;
		this.toStarboard = copy.toStarboard;
		this.toPort = copy.toPort;
		this.positionFixingDevice = copy.positionFixingDevice;
		this.raimFlag = copy.raimFlag;
		this.dataTerminalReady = copy.dataTerminalReady;
		this.assigned = copy.assigned;
	}
	
	/**
	 * Do not encode AIS19 with the default encode method, use encodeAIS19 instead. This must remain
	 * empty to prevent message overwriting.
	 */
	@Override 
	public void encode() {
		super.encode();
	}
	
	/**
	 * This is a quick fix to prevent message overwriting in fragmented AIS19 message.
	 */
	public void encodeAIS19() {
		message = AISPayloadEncryptionUtil.encodeExtendedClassBEquipmentPositionReport(this);
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

	public final String getShipName() {
		return shipName;
	}

	public final ShipType getShipType() {
		return shipType;
	}

	public final Integer getToBow() {
		return toBow;
	}

	public final Integer getToStern() {
		return toStern;
	}

	public final Integer getToStarboard() {
		return toStarboard;
	}

	public final Integer getToPort() {
		return toPort;
	}

	public final PositionFixingDevice getPositionFixingDevice() {
		return positionFixingDevice;
	}

	public final Boolean getRaimFlag() {
		return raimFlag;
	}

	public final Boolean getDataTerminalReady() {
		return dataTerminalReady;
	}

	public final Boolean getAssigned() {
		return assigned;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(
				"ExtendedClassBEquipmentPositionReport [regionalReserved1=")
				.append(regionalReserved1).append(", speedOverGround=")
				.append(speedOverGround).append(", positionAccurate=")
				.append(positionAccurate).append(", latitude=")
				.append(latitude).append(", longitude=").append(longitude)
				.append(", courseOverGround=").append(courseOverGround)
				.append(", trueHeading=").append(trueHeading)
				.append(", second=").append(second)
				.append(", regionalReserved2=").append(regionalReserved2)
				.append(", shipName=").append(shipName).append(", shipType=")
				.append(shipType).append(", toBow=").append(toBow)
				.append(", toStern=").append(toStern).append(", toStarboard=")
				.append(toStarboard).append(", toPort=").append(toPort)
				.append(", positionFixingDevice=").append(positionFixingDevice)
				.append(", raimFlag=").append(raimFlag)
				.append(", dataTerminalReady=").append(dataTerminalReady)
				.append(", assigned=").append(assigned).append("]");
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
		if (shipName != null) res.put("shipName", shipName);
		if (shipType != null) res.put("shipType", shipType);
		if (toBow != null) res.put("toBow", toBow);
		if (toStern != null) res.put("toStern", toStern);
		if (toStarboard != null) res.put("toStarboard", toStarboard);
		if (toPort != null) res.put("toPort", toPort);
		if (positionFixingDevice != null) res.put("positionFixingDevice", positionFixingDevice);
		if (raimFlag != null) res.put("raimFlag", raimFlag);
		if (dataTerminalReady != null) res.put("dataTerminalReady", dataTerminalReady);
		if (assigned != null) res.put("assigned", assigned);
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
		result = prime * result + ((courseOverGround == null) ? 0 : courseOverGround.hashCode());
		result = prime * result + ((dataTerminalReady == null) ? 0 : dataTerminalReady.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((positionAccurate == null) ? 0 : positionAccurate.hashCode());
		result = prime * result + ((positionFixingDevice == null) ? 0 : positionFixingDevice.hashCode());
		result = prime * result + ((raimFlag == null) ? 0 : raimFlag.hashCode());
		result = prime * result + ((regionalReserved1 == null) ? 0 : regionalReserved1.hashCode());
		result = prime * result + ((regionalReserved2 == null) ? 0 : regionalReserved2.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		result = prime * result + ((shipName == null) ? 0 : shipName.hashCode());
		result = prime * result + ((shipType == null) ? 0 : shipType.hashCode());
		result = prime * result + ((speedOverGround == null) ? 0 : speedOverGround.hashCode());
		result = prime * result + ((toBow == null) ? 0 : toBow.hashCode());
		result = prime * result + ((toPort == null) ? 0 : toPort.hashCode());
		result = prime * result + ((toStarboard == null) ? 0 : toStarboard.hashCode());
		result = prime * result + ((toStern == null) ? 0 : toStern.hashCode());
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
		ExtendedClassBEquipmentPositionReport other = (ExtendedClassBEquipmentPositionReport) obj;
		if (assigned == null) {
			if (other.assigned != null)
				return false;
		} else if (!assigned.equals(other.assigned))
			return false;
		if (courseOverGround == null) {
			if (other.courseOverGround != null)
				return false;
		} else if (!courseOverGround.equals(other.courseOverGround))
			return false;
		if (dataTerminalReady == null) {
			if (other.dataTerminalReady != null)
				return false;
		} else if (!dataTerminalReady.equals(other.dataTerminalReady))
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
		if (positionAccurate == null) {
			if (other.positionAccurate != null)
				return false;
		} else if (!positionAccurate.equals(other.positionAccurate))
			return false;
		if (positionFixingDevice != other.positionFixingDevice)
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
		if (shipName == null) {
			if (other.shipName != null)
				return false;
		} else if (!shipName.equals(other.shipName))
			return false;
		if (shipType != other.shipType)
			return false;
		if (speedOverGround == null) {
			if (other.speedOverGround != null)
				return false;
		} else if (!speedOverGround.equals(other.speedOverGround))
			return false;
		if (toBow == null) {
			if (other.toBow != null)
				return false;
		} else if (!toBow.equals(other.toBow))
			return false;
		if (toPort == null) {
			if (other.toPort != null)
				return false;
		} else if (!toPort.equals(other.toPort))
			return false;
		if (toStarboard == null) {
			if (other.toStarboard != null)
				return false;
		} else if (!toStarboard.equals(other.toStarboard))
			return false;
		if (toStern == null) {
			if (other.toStern != null)
				return false;
		} else if (!toStern.equals(other.toStern))
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
	 * @param shipName the shipName to set
	 */
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	/**
	 * @param shipType the shipType to set
	 */
	public void setShipType(ShipType shipType) {
		this.shipType = shipType;
	}

	/**
	 * @param toBow the toBow to set
	 */
	public void setToBow(Integer toBow) {
		this.toBow = toBow;
	}

	/**
	 * @param toStern the toStern to set
	 */
	public void setToStern(Integer toStern) {
		this.toStern = toStern;
	}

	/**
	 * @param toStarboard the toStarboard to set
	 */
	public void setToStarboard(Integer toStarboard) {
		this.toStarboard = toStarboard;
	}

	/**
	 * @param toPort the toPort to set
	 */
	public void setToPort(Integer toPort) {
		this.toPort = toPort;
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
}
