package de.emir.service.codecs.nmea0183.encoding.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import de.emir.service.codecs.nmea0183.encoding.data.Acquisition;
import de.emir.service.codecs.nmea0183.encoding.data.Date;
import de.emir.service.codecs.nmea0183.encoding.data.GpsQuality;
import de.emir.service.codecs.nmea0183.encoding.data.Hemisphere;
import de.emir.service.codecs.nmea0183.encoding.data.ModeIndicator;
import de.emir.service.codecs.nmea0183.encoding.data.PressureUnit;
import de.emir.service.codecs.nmea0183.encoding.data.Reference;
import de.emir.service.codecs.nmea0183.encoding.data.SignalIntegrity;
import de.emir.service.codecs.nmea0183.encoding.data.Source;
import de.emir.service.codecs.nmea0183.encoding.data.SpeedUnit;
import de.emir.service.codecs.nmea0183.encoding.data.Status;
import de.emir.service.codecs.nmea0183.encoding.data.TargetNumber;
import de.emir.service.codecs.nmea0183.encoding.data.TargetReference;
import de.emir.service.codecs.nmea0183.encoding.data.TargetStatus;
import de.emir.service.codecs.nmea0183.encoding.data.TemperatureUnit;
import de.emir.service.codecs.nmea0183.encoding.data.Time;
import de.emir.service.codecs.nmea0183.encoding.data.Unit;

/**
 * The parse utils provides some methods to parse enum values or other type of
 * values from a String.
 * 
 */
public class ParseUtils {
	/**
	 * Parses a given String to a Target Number.
	 * 
	 * @param value
	 *            String.
	 * @return A TargetNumber.
	 */
	public static TargetNumber parseTargetNumber(String value) {
		return TargetNumber.parse(value);
	}
	
	/**
	 * Converts the given TargetNumber to a two digit number.
	 * 
	 * @param number
	 *            The TargetNumber
	 * @return Two digit nmea representation.
	 */
	public static String toString(TargetNumber number) {
		if (number == null) return "";
		
		return number.toString();
	}
	
	/**
	 * Parses a given String to a Date.
	 * 
	 * @param value
	 *            String.
	 * @return The date.
	 */
	public static Date parseDate(String value) {
		Date res = new Date();
		try {
			res.setDay(Integer.parseInt(value.substring(0, 2)));
			res.setMonth(Integer.parseInt(value.substring(2, 4)));
			res.setYear(Integer.parseInt(value.substring(4, 6)));
		} catch (Exception e) {
			return null;
		}
		return res;
	}
	
	private static DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
	/**
	 * Converts the given date to a String.
	 * 
	 * @param value
	 *            Date value.
	 * @return String representation in nmea.
	 */
	public static String toString(Date value) {
		if (value == null) return "";
		java.util.Date d = new java.util.Date(value.getYear()-1900, value.getMonth()-1, value.getDay());
		return dateFormat.format(d);
//		return String.format("%02d%02d%02d", value.getYear(), value.getMonth(), value.getDay());
	}
	
	/**
	 * Parses a given String to a Time.
	 * 
	 * @param value
	 *            String.
	 * @return The time.
	 */
	public static Time parseTime(String value) {
		Time res = new Time();
		try {
			if (value.contains(".")) {
				String[] s = value.split("\\.");
				res.setHours(Integer.parseInt(s[0].substring(0, 2)));
				res.setMinutes(Integer.parseInt(s[0].substring(2, 4)));
				res.setSeconds(Integer.parseInt(s[0].substring(4, 6)));
				res.setMilliSeconds(Integer.parseInt(s[1]) * 10);
			} else {
				res.setHours(Integer.parseInt(value.substring(0, 2)));
				res.setMinutes(Integer.parseInt(value.substring(2, 4)));
				res.setSeconds(Integer.parseInt(value.substring(4, 6)));
			}
		} catch (Exception e) {
			return null;
		}
		return res;
	}
	
	/**
	 * Converts the given time to a String.
	 * 
	 * @param value
	 *            Time value.
	 * @return String representation in nmea.
	 */
	public static String toString(Time value) {
		if (value == null) return "";
		
		if (value.getMilliSeconds() > 0) { 
			return String.format("%02d%02d%02d.%02d", value.getHours(), value.getMinutes(), value.getSeconds(), (value.getMilliSeconds() / 10));
		} else {
			return String.format("%02d%02d%02d", value.getHours(), value.getMinutes(), value.getSeconds());
		}
	}
	
	/**
	 * Parses a given String value and calculates the coordinate in decimal
	 * representation.
	 * 
	 * @param value
	 *            String from nmea.
	 * @return Double value of the coordinate.
	 */
	public static Double parseCoordinate(String value) {
		try {
			int p = value.indexOf('.');
			int d = Integer.parseInt(value.substring(0, p-2));
			String decimalMinuteStr = value.substring(p-2);
			double decimalMinute = Double.parseDouble(decimalMinuteStr);
			return d + decimalMinute / 60.0;
//			String[] gr = value.split("\\.");
////			int d = Integer.parseInt(gr[0].substring(0, gr[0].length() - 2));
//			int d1 = Integer.parseInt(gr[0].substring(gr[0].length() - 2, gr[0].length()));
//			double d2 = Float.parseFloat("0." + gr[1]);
//			Double res = new Double(d + ((d1 + d2) / 60.0));
//			return res;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Converts a given double value to a nmea String representation.
	 * 
	 * @param value
	 *            The decimal value.
	 * @param coordLength
	 *            Use 2 for latitude and 3 for longitude.
	 * @return String representation in nmea. That is degreesminutes.decimal
	 */
	public static String doubleToDecimalDegreeString(Double value, int coordLength) {
		if (value == null) return "";
		
		double decimalDegree = value;
		int degrees = (int) decimalDegree;
		double decimalMinute = (decimalDegree - degrees) * 60;
//		String dm = String.format(Locale.US, "%07.4f", decimalMinute);
        return String.format(Locale.US, "%0"+coordLength+"d%07.4f", degrees, decimalMinute);
//		int i2 = (int) f1;
//		int i3 = (int) Math.round((f1 - i2) * 10000);
//		String pattern = "%0" + coordLength + "d%02d"; 
//		String str = String.format(pattern, i1, i2) + "." + String.format("%02d", i3).substring(0, 2); //String.format sucks
//		return str;
	}
	
	/**
     * Converts a given double value to a nmea String representation with
     * variable precision.
     * 
     * @param value
     *            The decimal value.
     * @param coordLength
     *            Use 2 for latitude and 3 for longitude.
     * @param precision
     *            Number of decimal figures
     * @return String representation in nmea.
     */
    public static String doubleToDecimalDegreeString(Double value, int coordLength, int precision) {
        if (value == null) return "";
        if (precision > 7) {
            precision = 7;
        }
        if (precision < 1) {
            precision = 1;
        }
        double val = value;
        int i1 = (int) val;
        double f1 = (val - i1) * 60;
        int i2 = (int) f1;
        int i3 = (int) Math.round((f1 - i2) * Math.pow(10, precision));
        String pattern = "%0" + coordLength + "d%02d.%0" + precision + "d";
        return String.format(pattern, i1, i2, i3);
    }
	
	public static Double parseDouble(String value) {
		try {
			return Double.parseDouble(value);
		} catch (Exception ex) {
			return null;
		}
	}
	
	public static Integer parseInteger(String value) {
		try {
			return Integer.parseInt(value);
		} catch (Exception ex) {
			return null;
		}
	}
	
	public static Hemisphere parseHemisphere(String value) {
		try {
			return Hemisphere.parse(value);
		} catch (Exception e) {
			return Hemisphere.NULL;
		}
	}
	
	public static Unit parseUnit(String value) {
		try {
			return Unit.parse(value);
		} catch (Exception e) {
			return Unit.NULL;
		}
	}
	
	public static GpsQuality parseGpsQuality(String value) {
		try {
			return GpsQuality.parse(value);
		} catch (Exception e) {
			return GpsQuality.INVALID;
		}
	}
	
	public static SignalIntegrity parseSignalIntegrity(String value) {
		try {
			return SignalIntegrity.parse(value);
		} catch (Exception e) {
			return SignalIntegrity.NULL;
		}
	}
	
	public static Status parseStatus(String value) {
		try {
			return Status.parse(value);
		} catch (Exception e) {
			return Status.NULL;
		}
	}
	
	public static ModeIndicator parseModeIndicator(String value) {
        try {
            return ModeIndicator.parse(value);
        } catch (Exception e) {
            return ModeIndicator.NULL;
        }
    }
	
	public static Reference parseReference(String value) {
		try {
			return Reference.parse(value);
		} catch (Exception e) {
			return Reference.NULL;
		}
	}
	
	public static String toString(Reference value) {
		if (value == null) return "";
		
		return value.getShortName();
	}
	
	public static TemperatureUnit parseTemperatureUnit(String value) {
		try {
			return TemperatureUnit.parse(value);
		} catch (Exception e) {
			return TemperatureUnit.NULL;
		}
	}
	
	public static String toString(TemperatureUnit value) {
		if (value == null) return "";
		
		return value.getShortName();
	}
	
	public static SpeedUnit parseSpeedUnit(String value) {
		try {
			return SpeedUnit.parse(value);
		} catch (Exception e) {
			return SpeedUnit.NULL;
		}
	}
	
	public static String toString(SpeedUnit value) {
		if (value == null) return "";
		
		return value.getShortName();
	}
	
	public static Source parseSource(String value) {
		try {
			return Source.parse(value);
		} catch (Exception e) {
			return Source.NULL;
		}
	}
	
	public static String toString(Source value) {
		if (value == null) return "";
		
		return value.getShortName();
	}
	
	public static Acquisition parseAcquisition(String value) {
		try {
			return Acquisition.parse(value);
		} catch (Exception e) {
			return Acquisition.NULL;
		}
	}
	
	public static String toString(Acquisition value) {
		if (value == null) return "";
		
		return value.getShortName();
	}
	
	public static TargetStatus parseTargetStatus(String value) {
		try {
			return TargetStatus.parse(value);
		} catch (Exception e) {
			return TargetStatus.NULL;
		}
	}
	
	public static String toString(TargetStatus value) {
		if (value == null) return "";
		
		return value.getShortName();
	}
	
	public static TargetReference parseTargetReference(String value) {
		try {
			return TargetReference.parse(value);
		} catch (Exception e) {
			return TargetReference.NOT_DESIGNATED;
		}
	}
	
	public static PressureUnit parsePressureUnit(String value) {
		try {
			return PressureUnit.parse(value);
		} catch (Exception e) {
			return PressureUnit.NULL;
		}
	}
	
	public static String toString(TargetReference value) {
		if (value == null) return "";
		
		return value.getShortName();
	}
	
	public static String toString(Integer value) {
		if (value == null) {
			return "";
		} else {
			return String.valueOf(value);
		}
	}
	
	public static String toString(Double value) {
		if (value == null) {
			return "";
		} else {
			return String.valueOf(value);
		}
	}
	
	public static String toString(Double value, int decimalPlaces) 
	{
		if (value == null) return "";		
		return String.format(Locale.US, "%." + decimalPlaces + "f", value);
	}
	
	public static String toString(Hemisphere value) {
		if (value == null) return "";
		
		return value.getShortName();
	}
	
	public static String toString(Unit value) {
		if (value == null) return "";
		
		return value.getShortName();
	}
	
	public static String toString(SignalIntegrity value) {
		if (value == null) return "";
		
		return value.getShortName();
	}
	
	public static String toString(GpsQuality value) {
		if (value == null) return "";
		
		return String.valueOf(value.getValue());
	}
	
	public static String toString(Status value) {
		if (value == null) return "";
		
		return value.getShortName();
	}
	
	public static String toString(ModeIndicator value) {
        if (value == null) return "";
        
        return value.getShortName();
    }
	
	public static String toString(PressureUnit value) {
		if (value == null) return "";
		return value.getShortName();
	}
}
