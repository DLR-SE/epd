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
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.IMO;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.PositionFixingDevice;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.ShipType;

/**
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StaticAndVoyageData", propOrder = {
		"imo",
		"callsign",
		"shipName",
		"shipType",
		"toBow",
		"toStern",
		"toStarboard",
		"toPort",
		"positionFixingDevice",
		"eta",
		"draught",
		"destination",
		"dataTerminalReady"
})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class StaticAndVoyageData extends DecodedAISPayload {
	
	/**
	 * 
	 */
	@XmlTransient
	private static final long serialVersionUID = 2223457134318263621L;
	
	private IMO imo;
	private String callsign;
	private String shipName;
	private ShipType shipType;
	private Integer toBow;
	private Integer toStern;
	private Integer toStarboard;
	private Integer toPort;
	private PositionFixingDevice positionFixingDevice;
	private String eta;
	private Float draught;
	private String destination;
	private Boolean dataTerminalReady;
	
	protected StaticAndVoyageData(){
		super(AISMessageType.StaticAndVoyageRelatedData, null,null);
		this.setImo(null);
		this.setCallsign(null);
		this.setShipName(null);
		this.setShipType(null);
		this.setToBow(null);
		this.setToStern(null) ;
		this.setToStarboard(null);
		this.setToPort(null) ;
		this.setPositionFixingDevice(null) ;
		this.setEta(null) ;
		this.setDraught(null);
		this.setDestination(null) ;
		this.setDataTerminalReady(null) ;
	}
	
	public StaticAndVoyageData(String originalNmea, Integer repeatIndicator,
			MMSI mmsi, IMO imo, String callsign, String shipName,
			ShipType shipType, Integer toBow, Integer toStern,
			Integer toStarboard, Integer toPort,
			PositionFixingDevice positionFixingDevice, String eta, Float draught,
			String destination, Boolean dataTerminalReady) {
		super(originalNmea, AISMessageType.StaticAndVoyageRelatedData, repeatIndicator, mmsi);
		this.setImo(imo);
		this.setCallsign(callsign);
		this.setShipName(shipName);
		this.setShipType(shipType);
		this.setToBow(toBow);
		this.setToStern(toStern);
		this.setToStarboard(toStarboard);
		this.setToPort(toPort);
		this.setPositionFixingDevice(positionFixingDevice);
		this.setEta(eta);
		this.setDraught(draught);
		this.setDestination(destination);
		this.setDataTerminalReady(dataTerminalReady);
	}
	
	public StaticAndVoyageData(Integer repeatIndicator,
			MMSI mmsi, IMO imo, String callsign, String shipName,
			ShipType shipType, Integer toBow, Integer toStern,
			Integer toStarboard, Integer toPort,
			PositionFixingDevice positionFixingDevice, String eta, Float draught,
			String destination, Boolean dataTerminalReady) {
		super(AISMessageType.StaticAndVoyageRelatedData, repeatIndicator, mmsi);
		this.setImo(imo);
		this.setCallsign(callsign);
		this.setShipName(shipName);
		this.setShipType(shipType);
		this.setToBow(toBow);
		this.setToStern(toStern);
		this.setToStarboard(toStarboard);
		this.setToPort(toPort);
		this.setPositionFixingDevice(positionFixingDevice);
		this.setEta(eta);
		this.setDraught(draught);
		this.setDestination(destination);
		this.setDataTerminalReady(dataTerminalReady);
	}
	
	//copy constructor
	public StaticAndVoyageData(StaticAndVoyageData copy){
        this.messageType = AISMessageType.StaticAndVoyageRelatedData;
        this.repeatIndicator = copy.repeatIndicator;
        this.sourceMmsi = new MMSI(copy.sourceMmsi);
		this.imo = new IMO(copy.imo);
		this.callsign = copy.callsign;
		this.shipName = copy.shipName;
		this.shipType = copy.shipType;
		this.toBow = copy.toBow;
		this.toStern = copy.toStern;
		this.toStarboard = copy.toStarboard;
		this.toPort = copy.toPort;
		this.positionFixingDevice = copy.positionFixingDevice;
		this.eta = copy.eta;
		this.draught = copy.draught;
		this.destination = copy.destination;
		this.dataTerminalReady = copy.dataTerminalReady;
	}

	public final IMO getImo() {
		return imo;
	}

	public final String getCallsign() {
		return callsign;
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

	public final String getEta() {
		return eta;
	}

	public final Float getDraught() {
		return draught;
	}

	public final String getDestination() {
		return destination;
	}

	public final Boolean getDataTerminalReady() {
		return dataTerminalReady;
	}
	
	/**
	 * Do not encode AIS5 with the default encode method, use encodeAIS5 instead. This must remain
	 * empty to prevent message overwriting.
	 */
	@Override 
	public void encode() {
		super.encode();
	}
	
	/**
	 * This is a quick fix to prevent message overwriting in fragmented AIS5 message.
	 */
	public void encodeAIS5() {
		message = AISPayloadEncryptionUtil.encodeStaticAndVoyageRelatedData(this);
		super.encode();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StaticAndVoyageData [imo=").append(getImo())
				.append(", callsign=").append(getCallsign()).append(", shipName=")
				.append(getShipName()).append(", shipType=").append(getShipType())
				.append(", toBow=").append(getToBow()).append(", toStern=")
				.append(getToStern()).append(", toStarboard=").append(getToStarboard())
				.append(", toPort=").append(getToPort())
				.append(", positionFixingDevice=").append(getPositionFixingDevice())
				.append(", eta=").append(getEta()).append(", draught=")
				.append(getDraught()).append(", destination=").append(getDestination())
				.append(", dataTerminalReady=").append(getDataTerminalReady())
				.append("]");
		return builder.toString();
	}

	@Override
	public void fillMap(Map<String, Object> res) {	
		if (getImo() != null) res.put("imo", getImo());
		if (getCallsign() != null) res.put("callsign", getCallsign());
		if (getShipName() != null) res.put("shipName", getShipName());
		if (getShipType() != null) res.put("shipType", getShipType());
		if (getToBow() != null) res.put("toBow", getToBow());
		if (getToStern() != null) res.put("toStern", getToStern());
		if (getToStarboard() != null) res.put("toStarboard", getToStarboard());
		if (getToPort() != null) res.put("toPort", getToPort());
		if (getPositionFixingDevice() != null) res.put("positionFixingDevice", getPositionFixingDevice());
		if (getEta() != null) res.put("eta", getEta());
		if (getDraught() != null) res.put("draught", getDraught());
		if (getDestination() != null) res.put("destination", getDestination());
		if (getDataTerminalReady() != null) res.put("dataTerminalReady", getDataTerminalReady());
		if (getSourceMmsi() != null) res.put("sourceMmsi", getSourceMmsi().getMMSI());
		super.fillMap(res);
	}

	/**
	 * @param imo the imo to set
	 */
	public void setImo(IMO imo) {
		this.imo = imo;
	}

	/**
	 * @param callsign the callsign to set
	 */
	public void setCallsign(String callsign) {
		this.callsign = callsign;
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
	 * @param eta the eta to set
	 */
	public void setEta(String eta) {
		this.eta = eta;
	}

	/**
	 * @param draught the draught to set
	 */
	public void setDraught(Float draught) {
		this.draught = draught;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * @param dataTerminalReady the dataTerminalReady to set
	 */
	public void setDataTerminalReady(Boolean dataTerminalReady) {
		this.dataTerminalReady = dataTerminalReady;
	}
}
