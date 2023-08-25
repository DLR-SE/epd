package de.emir.service.codecs.nmea0183.encoding.data;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Date container holding year, month and day. The date is stored in GMT
 * (+0:00).
 * 
 */
@Embeddable
public class Date {
	/** Year */
	@Column(name = "NMEA_Year")
	private int year;
	/** Month */
	@Column(name = "NMEA_Month")
	private int month;
	/** Day */
	@Column(name = "NMEA_Day")
	private int day;
	
	public Date() {
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
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
		map.put(prefix + ".year", year);
		map.put(prefix + ".month", month);
		map.put(prefix + ".day", day);
	}
}
