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
public class BinaryAcknowledge extends DecodedAISPayload {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1206398606128760572L;
	
	private final MMSI mmsi1;
	private final MMSI mmsi2;
	private final MMSI mmsi3;
	private final MMSI mmsi4;
	
	public BinaryAcknowledge(String originalNmea,
			Integer repeatIndicator, MMSI sourceMmsi, MMSI mmsi1, MMSI mmsi2,
			MMSI mmsi3, MMSI mmsi4) {
		super(originalNmea, AISMessageType.BinaryAcknowledge, repeatIndicator, sourceMmsi);
		this.mmsi1 = mmsi1;
		this.mmsi2 = mmsi2;
		this.mmsi3 = mmsi3;
		this.mmsi4 = mmsi4;
	}

	public final MMSI getMmsi1() {
		return mmsi1;
	}

	public final MMSI getMmsi2() {
		return mmsi2;
	}

	public final MMSI getMmsi3() {
		return mmsi3;
	}

	public final MMSI getMmsi4() {
		return mmsi4;
	}

	@Override
	public void fillMap(Map<String, Object> res) {
		if (mmsi1 != null) res.put("mmsi1", mmsi1);
		if (mmsi2 != null) res.put("mmsi2", mmsi2);
		if (mmsi3 != null) res.put("mmsi3", mmsi3);
		if (mmsi4 != null) res.put("mmsi4", mmsi4);
	}
}
