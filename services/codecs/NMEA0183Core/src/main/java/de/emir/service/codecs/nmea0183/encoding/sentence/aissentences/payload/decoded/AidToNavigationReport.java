package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AidType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.PositionFixingDevice;

/**
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class AidToNavigationReport extends DecodedAISPayload {

	/**
	 * 
	 */
	private static final long serialVersionUID = 824601868554089420L;
	@Enumerated(EnumType.STRING)
	private final AidType aidType;
	private final String name;
	private final Boolean positionAccurate;
	private final Float latitude;
	private final Float longitude;
	private final Integer toBow;
	private final Integer toStern;
	private final Integer toStarboard;
	private final Integer toPort;
	@Enumerated(EnumType.STRING)
	private final PositionFixingDevice positionFixingDevice;
	private final Integer second;
	private final Boolean offPosition;
	private final String regionalUse;
	private final Boolean raimFlag;
	private final Boolean virtualAid;
	private final Boolean assignedMode;
	private final String nameExtension;
	
	public AidToNavigationReport(String originalNmea,
			Integer repeatIndicator, MMSI sourceMmsi, AidType aidType,
			String name, Boolean positionAccurate, Float latitude,
			Float longitude, Integer toBow, Integer toStern,
			Integer toStarboard, Integer toPort,
			PositionFixingDevice positionFixingDevice, Integer second,
			Boolean offPosition, String regionalUse, Boolean raimFlag,
			Boolean virtualAid, Boolean assignedMode, String nameExtension) {
		super(originalNmea, AISMessageType.AidToNavigationReport, repeatIndicator, sourceMmsi);
		this.aidType = aidType;
		this.name = name;
		this.positionAccurate = positionAccurate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.toBow = toBow;
		this.toStern = toStern;
		this.toStarboard = toStarboard;
		this.toPort = toPort;
		this.positionFixingDevice = positionFixingDevice;
		this.second = second;
		this.offPosition = offPosition;
		this.regionalUse = regionalUse;
		this.raimFlag = raimFlag;
		this.virtualAid = virtualAid;
		this.assignedMode = assignedMode;
		this.nameExtension = nameExtension;
	}

	public final AidType getAidType() {
		return aidType;
	}

	public final String getName() {
		return name;
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

	public final Integer getSecond() {
		return second;
	}

	public final Boolean getOffPosition() {
		return offPosition;
	}

	public final String getRegionalUse() {
		return regionalUse;
	}

	public final Boolean getRaimFlag() {
		return raimFlag;
	}

	public final Boolean getVirtualAid() {
		return virtualAid;
	}

	public final Boolean getAssignedMode() {
		return assignedMode;
	}

	public final String getNameExtension() {
		return nameExtension;
	}

	@Override
	public void fillMap(Map<String, Object> res) {
		if (aidType != null) res.put("aidType", aidType);
		if (name != null) res.put("name", name);
		if (positionAccurate != null) res.put("positionAccurate", positionAccurate);
		if (latitude != null) res.put("latitude", latitude);
		if (longitude != null) res.put("longitude", longitude);
		if (toBow != null) res.put("toBow", toBow);
		if (toStern != null) res.put("toStern", toStern);
		if (toStarboard != null) res.put("toStarboard", toStarboard);
		if (toPort != null) res.put("toPort", toPort);
		if (positionFixingDevice != null) res.put("positionFixingDevice", positionFixingDevice);
		if (second != null) res.put("second", second);
		if (offPosition != null) res.put("offPosition", offPosition);
		if (regionalUse != null) res.put("regionalUse", regionalUse);
		if (raimFlag != null) res.put("raimFlag", raimFlag);
		if (virtualAid != null) res.put("virtualAid", virtualAid);
		if (assignedMode != null) res.put("assignedMode", assignedMode);
		if (nameExtension != null) res.put("nameExtension", nameExtension);
	}
}
