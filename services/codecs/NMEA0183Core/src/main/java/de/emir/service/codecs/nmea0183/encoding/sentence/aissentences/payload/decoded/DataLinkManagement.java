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
public class DataLinkManagement extends DecodedAISPayload {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1235185044065387210L;

	private final Integer offsetNumber1;
	private final Integer reservedSlots1;
	private final Integer timeout1;
	private final Integer increment1;
	private final Integer offsetNumber2;
	private final Integer reservedSlots2;
	private final Integer timeout2;
	private final Integer increment2;
	private final Integer offsetNumber3;
	private final Integer reservedSlots3;
	private final Integer timeout3;
	private final Integer increment3;
	private final Integer offsetNumber4;
	private final Integer reservedSlots4;
	private final Integer timeout4;
	private final Integer increment4;
	

    public DataLinkManagement(String originalNmea,
			Integer repeatIndicator, MMSI sourceMmsi, Integer offsetNumber1,
			Integer reservedSlots1, Integer timeout1, Integer increment1,
			Integer offsetNumber2, Integer reservedSlots2, Integer timeout2,
			Integer increment2, Integer offsetNumber3, Integer reservedSlots3,
			Integer timeout3, Integer increment3, Integer offsetNumber4,
			Integer reservedSlots4, Integer timeout4, Integer increment4) {
		super(originalNmea, AISMessageType.DataLinkManagement, repeatIndicator, sourceMmsi);
		this.offsetNumber1 = offsetNumber1;
		this.reservedSlots1 = reservedSlots1;
		this.timeout1 = timeout1;
		this.increment1 = increment1;
		this.offsetNumber2 = offsetNumber2;
		this.reservedSlots2 = reservedSlots2;
		this.timeout2 = timeout2;
		this.increment2 = increment2;
		this.offsetNumber3 = offsetNumber3;
		this.reservedSlots3 = reservedSlots3;
		this.timeout3 = timeout3;
		this.increment3 = increment3;
		this.offsetNumber4 = offsetNumber4;
		this.reservedSlots4 = reservedSlots4;
		this.timeout4 = timeout4;
		this.increment4 = increment4;
	}

	public final Integer getOffsetNumber1() {
		return offsetNumber1;
	}

	public final Integer getReservedSlots1() {
		return reservedSlots1;
	}

	public final Integer getTimeout1() {
		return timeout1;
	}

	public final Integer getIncrement1() {
		return increment1;
	}

	public final Integer getOffsetNumber2() {
		return offsetNumber2;
	}

	public final Integer getReservedSlots2() {
		return reservedSlots2;
	}

	public final Integer getTimeout2() {
		return timeout2;
	}

	public final Integer getIncrement2() {
		return increment2;
	}

	public final Integer getOffsetNumber3() {
		return offsetNumber3;
	}

	public final Integer getReservedSlots3() {
		return reservedSlots3;
	}

	public final Integer getTimeout3() {
		return timeout3;
	}

	public final Integer getIncrement3() {
		return increment3;
	}

	public final Integer getOffsetNumber4() {
		return offsetNumber4;
	}

	public final Integer getReservedSlots4() {
		return reservedSlots4;
	}

	public final Integer getTimeout4() {
		return timeout4;
	}

	public final Integer getIncrement4() {
		return increment4;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DataLinkManagement [offsetNumber1=")
				.append(offsetNumber1).append(", reservedSlots1=")
				.append(reservedSlots1).append(", timeout1=").append(timeout1)
				.append(", increment1=").append(increment1)
				.append(", offsetNumber2=").append(offsetNumber2)
				.append(", reservedSlots2=").append(reservedSlots2)
				.append(", timeout2=").append(timeout2).append(", increment2=")
				.append(increment2).append(", offsetNumber3=")
				.append(offsetNumber3).append(", reservedSlots3=")
				.append(reservedSlots3).append(", timeout3=").append(timeout3)
				.append(", increment3=").append(increment3)
				.append(", offsetNumber4=").append(offsetNumber4)
				.append(", reservedSlots4=").append(reservedSlots4)
				.append(", timeout4=").append(timeout4).append(", increment4=")
				.append(increment4).append("]");
		return builder.toString();
	}

	@Override
	public void fillMap(Map<String, Object> res) {
		if (offsetNumber1 != null) res.put("offsetNumber1", offsetNumber1);
		if (reservedSlots1 != null) res.put("reservedSlots1", reservedSlots1);
		if (timeout1 != null) res.put("timeout1", timeout1);
		if (increment1 != null) res.put("increment1", increment1);
		if (offsetNumber2 != null) res.put("offsetNumber2", offsetNumber2);
		if (reservedSlots2 != null) res.put("reservedSlots2", reservedSlots2);
		if (timeout2 != null) res.put("timeout2", timeout2);
		if (increment2 != null) res.put("increment2", increment2);
		if (offsetNumber3 != null) res.put("offsetNumber3", offsetNumber3);
		if (reservedSlots3 != null) res.put("reservedSlots3", reservedSlots3);
		if (timeout3 != null) res.put("timeout3", timeout3);
		if (increment3 != null) res.put("increment3", increment3);
		if (timeout2 != null) res.put("offsetNumber4", offsetNumber4);
		if (increment2 != null) res.put("reservedSlots4", reservedSlots4);
		if (offsetNumber3 != null) res.put("timeout4", timeout4);
		if (reservedSlots3 != null) res.put("increment4", increment4);
	}
}
