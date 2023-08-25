package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;

/**
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Interrogation extends DecodedAISPayload {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3149732070245554945L;
	
	private final MMSI interrogatedMmsi1;
	private final Integer type1_1;
	private final Integer offset1_1;
	private final Integer type1_2;
	private final Integer offset1_2;
	private final MMSI interrogatedMmsi2;
	private final Integer type2_1;
	private final Integer offset2_1;
	
	public Interrogation(String originalNmea, Integer repeatIndicator,
			MMSI sourceMmsi, MMSI mmsi1, Integer type1_1, Integer offset1_1,
			Integer type1_2, Integer offset1_2, MMSI mmsi2, Integer type2_1,
			Integer offset2_1) {
		super(originalNmea, AISMessageType.Interrogation, repeatIndicator, sourceMmsi);
		this.interrogatedMmsi1 = mmsi1;
		this.type1_1 = type1_1;
		this.offset1_1 = offset1_1;
		this.type1_2 = type1_2;
		this.offset1_2 = offset1_2;
		this.interrogatedMmsi2 = mmsi2;
		this.type2_1 = type2_1;
		this.offset2_1 = offset2_1;
	}

	public final MMSI getInterrogatedMmsi1() {
		return interrogatedMmsi1;
	}

	public final Integer getType1_1() {
		return type1_1;
	}

	public final Integer getOffset1_1() {
		return offset1_1;
	}

	public final Integer getType1_2() {
		return type1_2;
	}

	public final Integer getOffset1_2() {
		return offset1_2;
	}

	public final MMSI getInterrogatedMmsi2() {
		return interrogatedMmsi2;
	}

	public final Integer getType2_1() {
		return type2_1;
	}

	public final Integer getOffset2_1() {
		return offset2_1;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Interrogation [interrogatedMmsi1=")
				.append(interrogatedMmsi1).append(", type1_1=").append(type1_1)
				.append(", offset1_1=").append(offset1_1).append(", type1_2=")
				.append(type1_2).append(", offset1_2=").append(offset1_2)
				.append(", interrogatedMmsi2=").append(interrogatedMmsi2)
				.append(", type2_1=").append(type2_1).append(", offset2_1=")
				.append(offset2_1).append("]");
		return builder.toString();
	}

	@Override
	public void fillMap(Map<String, Object> res) {
		if (interrogatedMmsi1 != null) res.put("interrogatedMmsi1", interrogatedMmsi1);
		if (type1_1 != null) res.put("type1_1", type1_1);
		if (offset1_1 != null) res.put("offset1_1", offset1_1);
		if (type1_2 != null) res.put("type1_2", type1_2);
		if (offset1_2 != null) res.put("offset1_2", offset1_2);
		if (interrogatedMmsi2 != null) res.put("interrogatedMmsi2", interrogatedMmsi2);
		if (type2_1 != null) res.put("type2_1", type2_1);
		if (offset2_1 != null) res.put("offset2_1", offset2_1);
	}
}
