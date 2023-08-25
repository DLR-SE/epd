package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import de.emir.service.codecs.nmea0183.encoding.data.Time;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

/**
 * ZDA - Time & Date - UTC, day, month, year and local time zone<br>
 * <br>
 * 
 * <pre>
 * {@code
 * .      1         2  3  4    5  6  7 
 *        |         |  |  |    |  |  | 
 * $--ZDA,hhmmss.ss,xx,xx,xxxx,xx,xx*hh
 * }
 * </pre>
 * <ol>
 * <li>Universal Time Coordinated (UTC)</li> 
 * <li>Day, 01 to 31</li>
 * <li>Month, 01 to 12</li>
 * <li>Year</li>
 * <li>Local zone description, 00 to +- 13 hours</li>
 * <li>Local zone minutes description, same sign as local hours</li>
 * <li>Checksum</li>
 * 
 */
@XmlRootElement(name = "ZDA")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZDASentence", propOrder = {
    "time",
    "day",
    "month",
    "year",
    "lzdHours",
    "lzdMinutes"
})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ZDASentence extends Sentence{
	/** Default begin char for this sentence type. */
	public static final char BEGIN_CHAR = '$';
	/** Default talker for this sentence. */
	public static final String DEFAULT_TALKER = "GP";
	/** Sentence id. */
	public static final String SENTENCE_ID = "ZDA";
	/** Default count of fields. */
	public static final int FIELD_COUNT = 6;
	
	/** Time (UTC) */
	private Time time;
	/** Day, 01 to 31 */
	private Integer day;
	/** Month, 01 to 12 */
	private Integer month;
	/** Year */
	private Integer year;
	/** Local zone description, 00 to +- 13 hours */
	private Integer lzdHours;
	/** Local zone minutes description, same sign as local hours */
	private Integer lzdMinutes;
	
	/**
	 * Default constructor for writing. Empty Sentence to fill attributes and
	 * call {@link #toNMEA()}.
	 */
	public ZDASentence() {
		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);
	}
	
	/**
	 * Default constructor for parsing.
	 * @param nmea
	 * Nmea String to be parsed.
	 */
	public ZDASentence(String nmea) {
		super(nmea);
	}
	

	@Override
	protected void decode() {
		int index = 0;
		time = ParseUtils.parseTime(getValue(index++));
		day = ParseUtils.parseInteger(getValue(index++));
		month = ParseUtils.parseInteger(getValue(index++));
		year = ParseUtils.parseInteger(getValue(index++));
		lzdHours = ParseUtils.parseInteger(getValue(index++));
		lzdMinutes = ParseUtils.parseInteger(getValue(index++));
	}

	@Override
	protected void encode() {
		int index = 0;
		setValue(index++, ParseUtils.toString(time));
		setValue(index++, ParseUtils.toString(day));
		setValue(index++, ParseUtils.toString(month));
		setValue(index++, ParseUtils.toString(year));
		setValue(index++, ParseUtils.toString(lzdHours));
		setValue(index++, ParseUtils.toString(lzdMinutes));
	}

	@Override
	protected void fillMap(Map<String, Object> result) {		
		if (time != null) time.addToMap("time", result);
		if (day != null) result.put("day", day);
		if (month != null) result.put("month", month);
		if (year != null) result.put("year", year);
		if (lzdHours != null) result.put("lzdHours", lzdHours);
		if (lzdMinutes != null) result.put("lzdMinutes", lzdMinutes);
	}
	
	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getLzdHours() {
		return lzdHours;
	}

	public void setLzdHours(Integer lzdHours) {
		this.lzdHours = lzdHours;
	}

	public Integer getLzdMinutes() {
		return lzdMinutes;
	}

	public void setLzdMinutes(Integer lzdMinutes) {
		this.lzdMinutes = lzdMinutes;
	}
}
