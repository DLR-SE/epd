package de.emir.service.codecs.nmea0183.encoding.data;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Time container holding hours, minutes, seconds and milliseconds. The time is
 * stored in GMT (+0:00).
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Time", propOrder = {
    "hours",
    "minutes",
    "seconds",
    "milliSeconds"
})
@Embeddable
public class Time implements Serializable {
	/** UID. */
	@XmlTransient
	private static final long serialVersionUID = 880015264981807839L;
	/** Hours. */
	private int hours;
	/** Minutes. */
	private int minutes;
	/** Seconds. */
	private int seconds;
	/** Milliseconds. */
	private int milliSeconds;

	/** Constructor. */
	public Time() {
	}
	public Time(int _hours, int _minutes, int _seconds, int _milliseconds){
		hours = _hours; minutes = _minutes; seconds = _seconds; milliSeconds = _milliseconds;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getMilliSeconds() {
		return milliSeconds;
	}

	public void setMilliSeconds(int milliSeconds) {
		this.milliSeconds = milliSeconds;
	}

	/**
	 * Used by Odysseus to fill a key value map.
	 * 
	 * @param prefix
	 *            Prefix to be used for the key.
	 * @param map
	 *            map to fill.
	 */
	public void addToMap(String prefix, Map<String, Object> map) {
		map.put(prefix + ".hours", hours);
		map.put(prefix + ".minutes", minutes);
		map.put(prefix + ".seconds", seconds);
		map.put(prefix + ".milliSeconds", milliSeconds);
	}
}
