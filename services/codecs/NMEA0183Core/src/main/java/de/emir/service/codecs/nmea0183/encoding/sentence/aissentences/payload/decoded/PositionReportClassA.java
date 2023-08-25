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

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.encryption.AISPayloadEncryptionUtil;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.ManeuverIndicator;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.NavigationStatus;

/**
 * 
 *
 */
@XmlRootElement(name = "PositionReportClassA")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PositionReportClassA")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class PositionReportClassA extends PositionReport {

	/**
	 * 
	 */
	@XmlTransient
	private static final long serialVersionUID = 4538264355358394386L;

	protected PositionReportClassA() {
		super(AISMessageType.PositionReportClassA, null, null, null, null,
				null, null, null, null, null, null, null, null, null);
	}

	public PositionReportClassA(String originalNmea, Integer repeatIndicator,
			MMSI sourceMmsi, NavigationStatus navigationStatus,
			Integer rateOfTurn, Float speedOverGround,
			Boolean positionAccurate, Double latitude, Double longitude,
			Float courseOverGround, Integer trueHeading, Integer second,
			ManeuverIndicator maneuverIndicator, Boolean raimFlag) {
		super(originalNmea, AISMessageType.PositionReportClassA,
				repeatIndicator, sourceMmsi, navigationStatus, rateOfTurn,
				speedOverGround, positionAccurate, latitude, longitude,
				courseOverGround, trueHeading, second, maneuverIndicator,
				raimFlag);
	}

	public PositionReportClassA(Integer repeatIndicator, MMSI sourceMmsi,
			NavigationStatus navigationStatus, Integer rateOfTurn,
			Float speedOverGround, Boolean positionAccurate, Double latitude,
			Double longitude, Float courseOverGround, Integer trueHeading,
			Integer second, ManeuverIndicator maneuverIndicator,
			Boolean raimFlag) {
		super(AISMessageType.PositionReportClassA, repeatIndicator, sourceMmsi,
				navigationStatus, rateOfTurn, speedOverGround,
				positionAccurate, latitude, longitude, courseOverGround,
				trueHeading, second, maneuverIndicator, raimFlag);
	}
	
	@Override
	public void encode() {
		message = AISPayloadEncryptionUtil.encodePositionReportClassA(this);
		super.encode();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PositionReportClassA [toString()=")
				.append(super.toString()).append("]");
		return builder.toString();
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

}
