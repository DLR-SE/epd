package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import de.emir.service.codecs.nmea0183.encoding.sentence.AISSentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;

//@XmlRootElement(name = "DecodedAISPayload")
@XmlType(name = "DecodedAISPayload", propOrder = {
	    "messageType",
	    "repeatIndicator",
	    "sourceMmsi"
})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class DecodedAISPayload extends AISSentence implements Serializable {
	
	/**
	 * This is the base class of all different types of decoded payloadMessages.
	 */
	@XmlTransient
	private static final long serialVersionUID = -7146996338054507695L;
	
	protected AISMessageType messageType;
	protected Integer repeatIndicator;
	protected MMSI sourceMmsi;
	
	protected DecodedAISPayload() {
		this.messageType = null;
		this.repeatIndicator = null;
		this.sourceMmsi = null;
	}
	
	protected DecodedAISPayload(String originalNmea, AISMessageType messageType, Integer repeatIndicator, MMSI sourceMmsi) {
		super(originalNmea);
		this.messageType = messageType;
		this.repeatIndicator = repeatIndicator;
		this.sourceMmsi = sourceMmsi;
	}
	
	protected DecodedAISPayload(AISMessageType messageType, Integer repeatIndicator, MMSI sourceMmsi) {
		super();
		this.messageType = messageType;
		this.repeatIndicator = repeatIndicator;
		this.sourceMmsi = sourceMmsi;
	}

	public final AISMessageType getMessageType() {
		return messageType;
	}

	public final Integer getRepeatIndicator() {
		return repeatIndicator;
	}

	public final MMSI getSourceMmsi() {
		return sourceMmsi;
	}
	
	public final void setSourceMmsi(MMSI value) {
		this.sourceMmsi = value;
	}
	
	@Override
	public void fillMap(Map<String, Object> res) {
		if (getMessageType() != null) res.put("messageType", getMessageType());
		if (getRepeatIndicator() != null) res.put("repeatIndicator", getRepeatIndicator());
		if (getSourceMmsi() != null) res.put("sourceMmsi", getSourceMmsi().getMMSI());
		super.fillMap(res);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DecodedAISMessage [messageType=").append(messageType)
				.append(", repeatIndicator=").append(repeatIndicator)
				.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((messageType == null) ? 0 : messageType.hashCode());
		result = prime * result
				+ ((repeatIndicator == null) ? 0 : repeatIndicator.hashCode());
		result = prime * result
				+ ((sourceMmsi == null) ? 0 : sourceMmsi.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DecodedAISPayload))
			return false;
		DecodedAISPayload other = (DecodedAISPayload) obj;
		if (messageType != other.messageType)
			return false;
		if (repeatIndicator == null) {
			if (other.repeatIndicator != null)
				return false;
		} else if (!repeatIndicator.equals(other.repeatIndicator))
			return false;
		if (sourceMmsi == null) {
			if (other.sourceMmsi != null)
				return false;
		} else if (!sourceMmsi.equals(other.sourceMmsi))
			return false;
		return true;
	}
}
