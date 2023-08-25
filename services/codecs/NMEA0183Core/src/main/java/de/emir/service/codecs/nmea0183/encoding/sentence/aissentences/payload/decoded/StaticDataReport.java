package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded;

import java.util.Map;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.encryption.AISPayloadEncryptionUtil;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.PositionFixingDevice;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.ShipType;

public class StaticDataReport extends DecodedAISPayload {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1833249589260757573L;
	
	private final Integer partNumber;
	private final String shipName;
	private final ShipType shipType;
	private final String vendorId;
	private final String callsign;
	private final Integer toBow;
	private final Integer toStern;
	private final Integer toStarboard;
	private final Integer toPort;
    private final PositionFixingDevice positionFixingDevice;
	
	public StaticDataReport(String originalNmea,
			Integer repeatIndicator, MMSI sourceMmsi, Integer partNumber,
			String shipName, ShipType shipType, String vendorId,
			String callsign, Integer toBow, Integer toStern,
			Integer toStarboard, Integer toPort, PositionFixingDevice positionFixingDevice) {
		super(originalNmea, AISMessageType.StaticDataReport, repeatIndicator, sourceMmsi);
		this.partNumber = partNumber;
		this.shipName = shipName;
		this.shipType = shipType;
		this.vendorId = vendorId;
		this.callsign = callsign;
		this.toBow = toBow;
		this.toStern = toStern;
		this.toStarboard = toStarboard;
		this.toPort = toPort;
        this.positionFixingDevice = positionFixingDevice;
	}
	
	public StaticDataReport() {
		super(AISMessageType.StaticDataReport, null, null);
		this.partNumber = null;
		this.shipName = "";
		this.shipType = null;
		this.vendorId = "";
		this.callsign = "";
		this.toBow = null;
		this.toStern = null;
		this.toStarboard = null;
		this.toPort = null;
		this.positionFixingDevice = null;
	}

	public StaticDataReport(
			Integer repeatIndicator, MMSI sourceMmsi, Integer partNumber,
			String shipName, ShipType shipType, String vendorId,
			String callsign, Integer toBow, Integer toStern,
			Integer toStarboard, Integer toPort, PositionFixingDevice positionFixingDevice) {
		super(AISMessageType.StaticDataReport, repeatIndicator, sourceMmsi);
		this.partNumber = partNumber;
		this.shipName = shipName;
		this.shipType = shipType;
		this.vendorId = vendorId;
		this.callsign = callsign;
		this.toBow = toBow;
		this.toStern = toStern;
		this.toStarboard = toStarboard;
		this.toPort = toPort;
        this.positionFixingDevice = positionFixingDevice;
	}
	
	public StaticDataReport(StaticDataReport copy) {
		super(AISMessageType.StaticDataReport, copy.repeatIndicator, copy.sourceMmsi);
		this.partNumber = copy.partNumber;
		this.shipName = copy.shipName;
		this.shipType = copy.shipType;
		this.vendorId = copy.vendorId;
		this.callsign = copy.callsign;
		this.toBow = copy.toBow;
		this.toStern = copy.toStern;
		this.toStarboard = copy.toStarboard;
		this.toPort = copy.toPort;
		this.positionFixingDevice = copy.positionFixingDevice;
	}

	public final Integer getPartNumber() {
		return partNumber;
	}

	public final String getShipName() {
		return shipName;
	}

	public final ShipType getShipType() {
		return shipType;
	}

	public final String getVendorId() {
		return vendorId;
	}

	public final String getCallsign() {
		return callsign;
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
	
	/**
	 * Do not encode AIS24 with the default encode method, use encodeAIS24A or encodeAIS24B instead. This must remain
	 * empty to prevent message overwriting.
	 */
	@Override 
	public void encode() {
		super.encode();
	}
	
	public void encodeAIS24A() {
		message = AISPayloadEncryptionUtil.encodeStaticDataReportA(this);
		super.encode();
	}
	
	public void encodeAIS24B() {
		message = AISPayloadEncryptionUtil.encodeStaticDataReportB(this);
		super.encode();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StaticDataReport [partNumber=")
				.append(partNumber).append(", shipName=").append(shipName)
				.append(", shipType=").append(shipType).append(", vendorId=")
				.append(vendorId).append(", callsign=").append(callsign)
				.append(", toBow=").append(toBow).append(", toStern=")
				.append(toStern).append(", toStarboard=").append(toStarboard)
				.append(", toPort=").append(toPort).append(", positionFixingDevice=")
				.append( positionFixingDevice).append("]");
		return builder.toString();
	}

	@Override
	public void fillMap(Map<String, Object> res) {
		if (partNumber != null) res.put("partNumber", partNumber);
		if (shipName != null) res.put("shipName", shipName);
		if (shipType != null) res.put("shipType", shipType);
		if (vendorId != null) res.put("vendorId", vendorId);
		if (callsign != null) res.put("callsign", callsign);
		if (toBow != null) res.put("toBow", toBow);
		if (toStern != null) res.put("toStern", toStern);
		if (toStarboard != null) res.put("toStarboard", toStarboard);
		if (toPort != null) res.put("toPort", toPort);
		if (positionFixingDevice != null) res.put("positionFixingDevice", positionFixingDevice);
        if (getSourceMmsi() != null) res.put("sourceMmsi", getSourceMmsi().getMMSI());
	}
}
