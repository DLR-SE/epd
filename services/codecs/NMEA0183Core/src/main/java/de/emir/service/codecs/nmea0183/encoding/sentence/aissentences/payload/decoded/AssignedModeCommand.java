package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;

/**
 * 
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class AssignedModeCommand extends DecodedAISPayload {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1994991229318550786L;
	
	private final MMSI destinationMmsiA;
	private final Integer offsetA;
	private final Integer incrementA;
	private final MMSI destinationMmsiB;
	private final Integer offsetB;
	private final Integer incrementB;
	
	public AssignedModeCommand(String originalNmea,
			Integer repeatIndicator, MMSI sourceMmsi, MMSI destinationMmsiA,
			Integer offsetA, Integer incrementA, MMSI destinationMmsiB,
			Integer offsetB, Integer incrementB) {
		super(originalNmea, AISMessageType.AssignedModeCommand, repeatIndicator, sourceMmsi);
		this.destinationMmsiA = destinationMmsiA;
		this.offsetA = offsetA;
		this.incrementA = incrementA;
		this.destinationMmsiB = destinationMmsiB;
		this.offsetB = offsetB;
		this.incrementB = incrementB;
	}

	public final MMSI getDestinationMmsiA() {
		return destinationMmsiA;
	}

	public final Integer getOffsetA() {
		return offsetA;
	}

	public final Integer getIncrementA() {
		return incrementA;
	}

	public final MMSI getDestinationMmsiB() {
		return destinationMmsiB;
	}

	public final Integer getOffsetB() {
		return offsetB;
	}

	public final Integer getIncrementB() {
		return incrementB;
	}

	@Override
	public void fillMap(Map<String, Object> res) {
		if (destinationMmsiA != null) res.put("destinationMmsiA", destinationMmsiA);
		if (offsetA != null) res.put("offsetA", offsetA);
		if (incrementA != null) res.put("incrementA", incrementA);
		if (destinationMmsiB != null) res.put("destinationMmsiB", destinationMmsiB);
		if (offsetB != null) res.put("offsetB", offsetB);
		if (incrementB != null) res.put("incrementB", incrementB);
	}
}
