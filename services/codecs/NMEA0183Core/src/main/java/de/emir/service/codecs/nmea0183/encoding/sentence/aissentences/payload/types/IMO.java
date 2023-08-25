package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "imo")
@Embeddable
public class IMO implements Serializable {
	/** UID. */
	private static final long serialVersionUID = 4210763318475157413L;
	
	private Long imo;
		
	private IMO(){
		this.imo = null;
	}

	public IMO(Long mmsi) {
		this.imo = mmsi;
	}
	
	//copy constructor
	public IMO(IMO copy){
		this.imo = copy.imo;
	}
	
	public static IMO valueOf(Long mmsi) {
		return new IMO(mmsi);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imo == null) ? 0 : imo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IMO other = (IMO) obj;
		if (imo == null) {
			if (other.imo != null)
				return false;
		} else if (!imo.equals(other.imo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IMO [imo=" + imo + "]";
	}

	public Long getIMO() {
		return imo;
	}
	
	public void setIMO(Long value) {
		imo = value;
	}
}
