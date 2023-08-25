package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.TxRxMode;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ChannelManagement extends DecodedAISPayload {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3384349277793304453L;
	
	private final Integer channelA;
	private final Integer channelB;
	@Enumerated(EnumType.STRING)
	private final TxRxMode transmitReceiveMode;
	private final Boolean power;
	private final Float northEastLongitude;
	private final Float northEastLatitude;
	private final Float southWestLongitude;
	private final Float southWestLatitude;
	private final MMSI destinationMmsi1;
	private final MMSI destinationMmsi2;
	private final Boolean addressed;
	private final Boolean bandA;
	private final Boolean bandB;
	private final Integer zoneSize;
	
	public ChannelManagement(String originalNmea,
			Integer repeatIndicator, MMSI sourceMmsi, Integer channelA,
			Integer channelB, TxRxMode transmitReceiveMode, Boolean power,
			Float longitudeNorthEast, Float northEastLatitude,
			Float southWestLongitude, Float southWestLatitude,
			MMSI destinationMmsi1, MMSI destinationMmsi2, Boolean addressed,
			Boolean bandA, Boolean bandB, Integer zoneSize) {
		super(originalNmea, AISMessageType.ChannelManagement, repeatIndicator, sourceMmsi);
		this.channelA = channelA;
		this.channelB = channelB;
		this.transmitReceiveMode = transmitReceiveMode;
		this.power = power;
		this.northEastLongitude = longitudeNorthEast;
		this.northEastLatitude = northEastLatitude;
		this.southWestLongitude = southWestLongitude;
		this.southWestLatitude = southWestLatitude;
		this.destinationMmsi1 = destinationMmsi1;
		this.destinationMmsi2 = destinationMmsi2;
		this.addressed = addressed;
		this.bandA = bandA;
		this.bandB = bandB;
		this.zoneSize = zoneSize;
	}

	public final Integer getChannelA() {
		return channelA;
	}

	public final Integer getChannelB() {
		return channelB;
	}

	public final TxRxMode getTransmitReceiveMode() {
		return transmitReceiveMode;
	}

	public final Boolean getPower() {
		return power;
	}

	public final Float getNorthEastLongitude() {
		return northEastLongitude;
	}

	public final Float getNorthEastLatitude() {
		return northEastLatitude;
	}

	public final Float getSouthWestLongitude() {
		return southWestLongitude;
	}

	public final Float getSouthWestLatitude() {
		return southWestLatitude;
	}

	public final MMSI getDestinationMmsi1() {
		return destinationMmsi1;
	}

	public final MMSI getDestinationMmsi2() {
		return destinationMmsi2;
	}

	public final Boolean getAddressed() {
		return addressed;
	}

	public final Boolean getBandA() {
		return bandA;
	}

	public final Boolean getBandB() {
		return bandB;
	}

	public final Integer getZoneSize() {
		return zoneSize;
	}

	@Override
	public void fillMap(Map<String, Object> res) {		
		if (channelA != null) res.put("channelA", channelA);
		if (channelB != null) res.put("channelB", channelB);
		if (transmitReceiveMode != null) res.put("transmitReceiveMode", transmitReceiveMode);
		if (power != null) res.put("power", power);
		if (northEastLongitude != null) res.put("northEastLongitude", northEastLongitude);
		if (northEastLatitude != null) res.put("northEastLatitude", northEastLatitude);
		if (southWestLongitude != null) res.put("southWestLongitude", southWestLongitude);
		if (southWestLatitude != null) res.put("southWestLatitude", southWestLatitude);
		if (destinationMmsi1 != null) res.put("destinationMmsi1", destinationMmsi1);
		if (destinationMmsi2 != null) res.put("destinationMmsi2", destinationMmsi2);
		if (addressed != null) res.put("addressed", addressed);
		if (bandA != null) res.put("bandA", bandA);
		if (bandB != null) res.put("bandB", bandB);
		if (zoneSize != null) res.put("zoneSize", zoneSize);
	}
}
