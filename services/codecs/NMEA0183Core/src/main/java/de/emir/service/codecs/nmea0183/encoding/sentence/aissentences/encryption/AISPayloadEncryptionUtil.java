package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.encryption;


import java.util.Locale;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.EncodedAISPayload;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.AddressedSafetyRelatedMessage;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.AidToNavigationReport;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.AssignedModeCommand;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.BaseStationReport;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.BinaryAcknowledge;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.BinaryAddressedMessage;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.BinaryBroadcastMessage;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.ChannelManagement;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.DGNSSBinaryBroadcastMessage;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.DataLinkManagement;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.ExtendedClassBEquipmentPositionReport;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.GroupAssignmentCommand;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.Interrogation;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.LongRangeAISBroadcastMessage;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.MultipleSlotBinaryMessageWithCommunicationState;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.PositionReport;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.PositionReportClassA;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.PositionReportClassAAssignedSchedule;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.PositionReportClassAResponseToInterrogation;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.SafetyRelatedBroadcastMessage;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.SingleSlotBinaryMessage;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.StandardClassBCSPositionReport;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.StandardSARAircraftPositionReport;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.StaticAndVoyageData;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.StaticDataReport;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.UTCAndDateInquiry;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.UTCAndDateResponse;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.exceptions.InvalidEncodedPayload;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.exceptions.UnsupportedPayloadType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AISMessageType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.AidType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.IMO;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.MMSI;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.ManeuverIndicator;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.NavigationStatus;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.PositionFixingDevice;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.ReportingInterval;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.ShipType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.StationType;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.TxRxMode;

/**
 * @author msalous and aoppermann
 *
 */
public class AISPayloadEncryptionUtil {
	public static final String MASK_CALLSIGN = "@@@@@@@";
	public static final String MASK_SHIPNAME = "@@@@@@@@@@@@@@@@@@@@";
	public static final String MASK_DESTINATION = "@@@@@@@@@@@@@@@@@@@@";
	public static final String MASK_VENDOR_ID = "@@@@@@@";
	
	/**
	 * BitString to different types conversion
	 * 
	 * */
	
	public static Integer convertToUnsignedInteger(String bitString) {
		return Integer.parseInt(bitString, 2);
	}
	
	public static Integer convertToSignedInteger(String bitString) {
//		System.out.println("bitstr\t\t" + bitString);
		Integer value;
		String signBit = bitString.substring(0, 1);
		String valueBits = bitString.substring(1);
		if ("0".equals(signBit))
			value = convertToUnsignedInteger(valueBits);
		else {
			valueBits = valueBits.replaceAll("0", "x");
			valueBits = valueBits.replaceAll("1", "0");
			valueBits = valueBits.replaceAll("x", "1");
//			System.out.println("valuebits\t " + valueBits);
			value = -1 * (convertToUnsignedInteger(valueBits) + 1);
		}	
		return value;
	}
	
	public static Float convertToUnsignedFloat(String bitString) {
		return Float.valueOf(convertToUnsignedInteger(bitString));
	}
	
	public static Float convertToFloat(String bitString) {
		return Float.valueOf(convertToSignedInteger(bitString));
	}
	
	public static Double convertToDouble(String bitString) {
		return Double.valueOf(convertToSignedInteger(bitString));
	}
	
	public static Long convertToUnsignedLong(String bitString) {
		return Long.parseLong(bitString, 2);
	}
	
	public static Boolean convertToBoolean(String bits) {
		return "1".equals(bits.substring(0, 1));
	}

	public static String convertToTime(String bits) {
		Integer month = convertToUnsignedInteger(bits.substring(0, 4));
		Integer day = convertToUnsignedInteger(bits.substring(4, 9));
		Integer hour = convertToUnsignedInteger(bits.substring(9, 14));
		Integer minute = convertToUnsignedInteger(bits.substring(14,20));

		String monthAsString = month<10 ? "0"+month : ""+month;
		String dayAsString = day<10 ? "0"+day : ""+day;
		String hourAsString = hour<10 ? "0"+hour : ""+hour;
		String minuteAsString = minute<10 ? "0"+minute : ""+minute;

		return dayAsString + "-" + monthAsString + " " + hourAsString + ":" + minuteAsString;
	}

	public static String convertToString(String bits) {
		StringBuffer stringBuffer = new StringBuffer();
		String remainingBits = bits;
		while (remainingBits.length() >= 6) {
			String binaryValue = remainingBits.substring(0, 6);
			remainingBits = remainingBits.substring(6);
			Integer intValue = convertToUnsignedInteger(binaryValue);
			String character = AISPayLoadEncryptionMaps.sixBitAscii.get(intValue);
			stringBuffer.append(character);
		}
		String ret = stringBuffer.toString();
		ret = ret.replaceAll("@", " ").trim();
		return ret;
	}
	
	/**
	 * Help methods
	 * 
	 * */
	
	public static String mask(String input, String mask) {
		char[] chars = mask.toCharArray();
		for (int i = 0; i < mask.length(); i++) {
			if (i > input.length() - 1) {
				break;
			}
			if (input.charAt(i) != ' ') {
				chars[i] = input.charAt(i);
			}
		}
		return new String(chars);
	}
	
	public static String fillBits(String bitString, int length, boolean signed) {
		String binaryString = "";
		if (signed) {
			if (bitString.length() < length) {
				for (int i = length - bitString.length() - 1; i > 0; i--) {
					binaryString += "0";
				}
				binaryString += bitString;
			} else if (bitString.length() > length) {
				binaryString += bitString.substring(0, length);
			} else {
				binaryString += bitString;
			}
			String temp = "1";
			binaryString = revertBits(binaryString);
			binaryString = temp + binaryString;
		} else {
			binaryString = fillBits(bitString, length);
		}
		return binaryString;
	}

	public static String fillBits(String bitString, int length) {
		String binaryString = "";
		if (bitString.length() < length) {
			for (int i = length - bitString.length(); i > 0; i--) {
				binaryString += "0";
			}
			binaryString += bitString;
		} else if (bitString.length() > length) {
			binaryString += bitString.substring(0, length);
		} else {
			binaryString += bitString;
		}
		return binaryString;
	}
	
	public static String fillBitsRight(String bitString, int length) {
		String binaryString = "";
		if (bitString.length() < length) {
			for (int i = length - bitString.length(); i > 0; i--) {
				binaryString += "0";
			}
			binaryString = bitString + binaryString;
		} else if (bitString.length() > length) {
			binaryString += bitString.substring(0, length);
		} else {
			binaryString += bitString;
		}
		return binaryString;
	}
	
	public static String revertBits(String bitString) {
		bitString = bitString.replaceAll("0", "x");
		bitString = bitString.replaceAll("1", "0");
		bitString = bitString.replaceAll("x", "1");
		return bitString;
	}
	
	public static String toSixBit(String bitString) {
		String sixBitString = "";
		for (int i = 0; i < (bitString.length() / 6); i++) {
			String key = bitString.substring(i * 6, i * 6 + 6);
			sixBitString += AISPayLoadEncryptionMaps.sixBitToChar.get(key);
		}
		return sixBitString;
	}
	
	public static String sixBitAsciiToBitString(String sixBitAscii) {
		String bitString = "";
		sixBitAscii = sixBitAscii.toUpperCase().trim().replaceAll(" ", "@");

		for (int i = 0; i < sixBitAscii.length(); i++) {
			String key = sixBitAscii.substring(i, i + 1);
			bitString += fillBits(Integer.toBinaryString(AISPayLoadEncryptionMaps.asciiToDecimal.get(key)), 6);
		}
		return bitString;
	}
	
	/**
	 * Since not all characters are supported in AIS encoding/decoding,
	 * this method prepares a key from a given character by 
	 * replacing the unsupported character (e.g. lowerCase letters, äöü, etc.) with similar supported character
	 * @param key to be revised
	 * @return revised key 
	 * */
	public static String reviseKey(String inputKey){
		String key = inputKey.toUpperCase(Locale.US);
		if (key.compareTo("Ä") == 0){
			key = "AE";
		}
		else if (key.compareTo("Ö") == 0){
			key = "OE";
		}
		else if (key.compareTo("Ü") == 0){
			key = "UE";
		}
		return key;
	}
	
	public static String etaToBitString(String eta) {
		String bitString = "";
		
		bitString += fillBits(Integer.toBinaryString(Integer.valueOf(eta.substring(3, 5))), 4);
		bitString += fillBits(Integer.toBinaryString(Integer.valueOf(eta.substring(0, 2))), 5);
		bitString += fillBits(Integer.toBinaryString(Integer.valueOf(eta.substring(6, 8))), 5);
		bitString += fillBits(Integer.toBinaryString(Integer.valueOf(eta.substring(9, 11))), 6);

		return bitString;
	}
	
	/**
	 * Maps a degree value to a range from 0 to 359.
	 * @param original
	 * @return the normalized value between 0 and 359 or 511 for "not available"
	 */
	public static int normalizeHeading(double original) {
		double result = original;
		if (original == 511) return (int) original; // do not change magic numbers
		while (result < 0) {
			result = 360 - Math.abs(result);
		}
		while (result > 360) {
			result -= 360;
		}
		if (result == 360) {
			result = 0;
		}
		return (int) result;
	}
	
	/**
	 * Maps a degree value to a range from 0.0 to 359.9.
	 * @param original
	 * @return the normalized value between 0.0 and 359.9 or 360.0 for "not available"
	 */
	public static float normalizeCOG(double original) {
		double result = original;
		if (original == 360.0) return (float) original; // do not change magic numbers
		while (result < 0) {
			result = 360.0 - Math.abs(result);
		}
		while (result > 360) {
			result -= 360.0;
		}
		return (float) result;
	}
	
	/**
	 * Maps a speed value in knots to a range from 0.0 to 102.2.
	 * @param original
	 * @return the normalized value between 0.0 and 102.2 or 102.3 for "not available"
	 */
	public static float normalizeSOG(double original) {
		double result = original;
		if (original == 102.3) return (float) original; // do not change magic numbers
		if (original < 0) result = 102.3;
		if (original > 102.2) result = 102.2; // 102.3 would be bigger but gets caught first
		return (float) result;
	}
	
	/**
	 * Maps a degree value to a range from -126 to 126.
	 * @param original
	 * @return the normalized value between -126 and 126 or -128 for "not available" or 127/-127 for more than 5 degrees per 30 seconds with TI unavailable.
	 */
	public static int normalizeROT(Double original) {
		if (original == null || original.isNaN() || original.isInfinite()) return -128; // not set, use default
		if (Math.abs(original) > 708) {
			if (original < 0) {
				return -127;
			} else {
				return 127;
			}
		} else {
			if (original < 0) {
				return (int) (4.733 * Math.sqrt(Math.abs(original)) * -1); 
			} else {
				return (int) (4.733 * Math.sqrt(Math.abs(original))); 
			}
		}
	}
	
	/**
	 * Maps the AIS ROT value back to degrees. Values <= 709 mean ROT was 127, no TI available.
	 * @param rot
	 * @return The rate of turn or null if ROT was -128
	 */
	public static Double getROTValue(int rot) {
		if (rot == -128) { 
			return null;
		} else {
			if (rot < 0) {
				return (Math.pow(Math.abs(rot) / 4.733, 2) * -1); 
			} else {
				return (Math.pow(Math.abs(rot / 4.733), 2)); 
			}
		}
	}
	
	/***************************************************************************
	 ***************E N C O D I N G   F U N C T I O N S*************************
	 ***************************************************************************/
	
	/**
	 *  PositionReportClassA
	 */
	public static String encodePositionReportClassA(PositionReport decoded) {
		if (decoded != null) {
			String bitString = "";

			bitString += fillBits(Integer.toBinaryString(decoded.getMessageType().getMessageType()), 6); // Message_Type
			bitString += fillBits(Integer.toBinaryString(decoded.getRepeatIndicator()), 2); // Repeat_Indicator
			bitString += fillBits(Long.toBinaryString(decoded.getSourceMmsi().getMMSI()), 30); // MMSI
			bitString += fillBits(Integer.toBinaryString(decoded.getNavigationStatus().getStatus()), 4); // Navigation_Status

			int rateOfTurn = decoded.getRateOfTurn();
//			bitString += fillBits(Integer.toBinaryString(rateOfTurn), 7, rateOfTurn < 0); // Rate_of_Turn
			String rotbits = Integer.toBinaryString(rateOfTurn);
//			System.out.println(rotbits);
			if (rateOfTurn < 0) {
//				System.out.println(Integer.toBinaryString(rateOfTurn).substring(rotbits.length() - 8, rotbits.length()));
				bitString += Integer.toBinaryString(rateOfTurn).substring(rotbits.length() - 8, rotbits.length()); // rot
			} else {
//				System.out.println(fillBits(Integer.toBinaryString(rateOfTurn), 8));
				bitString += fillBits(Integer.toBinaryString(rateOfTurn), 8);
			}
			
			bitString += fillBits(Integer.toBinaryString((int) (decoded.getSpeedOverGround().floatValue() * 10f)), 10); // Speed_over_Ground
			bitString += decoded.getPositionAccurate() ? "1" : "0"; // Position_Accuracy

			double longitude = decoded.getLongitude().doubleValue() * 600000f;
			bitString += fillBits(Integer.toBinaryString((int) Math.abs(longitude)), 28, longitude < 0); // Longitude

			double latitude = decoded.getLatitude().doubleValue() * 600000f;
			bitString += fillBits(Integer.toBinaryString((int) Math.abs(latitude)), 27, latitude < 0); // Latitude
			bitString += fillBits(Integer.toBinaryString((int) (decoded.getCourseOverGround() * 10f)), 12); // Course_over_Ground
			bitString += fillBits(Integer.toBinaryString(decoded.getTrueHeading()), 9); // True_Heading
			bitString += fillBits(Integer.toBinaryString(decoded.getSecond()), 6); // Time_Stamp(in_seconds)
			bitString += fillBits(Integer.toBinaryString(decoded.getManeuverIndicator().getIndicator()), 2); // Maneuver_Indicator
//			bitString += "000"; // Spare
			bitString += fillBits(Integer.toBinaryString(0), 3); // Spare
			bitString += decoded.getRaimFlag() ? "1" : "0"; // RAIM_Flag

			int padding = 168 - bitString.length();
			for (int i = 0; i < padding; i++) {
				bitString += "0";
			}
//			System.out.println(bitString);
			return toSixBit(bitString);
		}
		return null;
	}
	
	/**
	 *  StandardClassBCSPositionReport 18
	 */
	public static String encodeStandardClassBCSPositionReport(StandardClassBCSPositionReport decoded) {
		if (decoded != null) {
			String bitString = "";

			bitString += fillBits(Integer.toBinaryString(decoded.getMessageType().getMessageType()), 6); // Message_Type
			bitString += fillBits(Integer.toBinaryString(decoded.getRepeatIndicator()), 2); // Repeat_Indicator
			bitString += fillBits(Long.toBinaryString(decoded.getSourceMmsi().getMMSI()), 30); // MMSI
			bitString += fillBitsRight(sixBitAsciiToBitString(decoded.getRegionalReserved1()), 8); // Regional Reserved 1 +00000000?
			
			bitString += fillBits(Integer.toBinaryString((int) (decoded.getSpeedOverGround().floatValue() * 10f)), 10); // Speed_over_Ground
			bitString += decoded.getPositionAccurate() ? "1" : "0"; // Position_Accuracy
			double longitude = decoded.getLongitude().doubleValue() * 600000f;
			bitString += fillBits(Integer.toBinaryString((int) Math.abs(longitude)), 28, longitude < 0); // Longitude

			double latitude = decoded.getLatitude().doubleValue() * 600000f;
			bitString += fillBits(Integer.toBinaryString((int) Math.abs(latitude)), 27, latitude < 0); // Latitude

			bitString += fillBits(Integer.toBinaryString((int) (decoded.getCourseOverGround() * 10f)), 12); // Course_over_Ground
			bitString += fillBits(Integer.toBinaryString(decoded.getTrueHeading()), 9); // True_Heading
			bitString += fillBits(Integer.toBinaryString(decoded.getSecond()), 6); // Time_Stamp(in_seconds)
			
			bitString += fillBitsRight(sixBitAsciiToBitString(decoded.getRegionalReserved2()), 2); // Regional Reserved 1 +00?
			
			bitString += decoded.getCsUnit() ? "1" : "0"; // CS Unit
			bitString += decoded.getDisplay() ? "1" : "0"; // Display Flag
			bitString += decoded.getDsc() ? "1" : "0"; // DSC Flag
			bitString += decoded.getBand() ? "1" : "0"; // Band Flag
			bitString += decoded.getMessage22() ? "1" : "0"; // Message22 Flag
			bitString += decoded.getAssigned() ? "1" : "0"; // Assigned Flag
			bitString += decoded.getRaimFlag() ? "1" : "0"; // RAIM_Flag
			
			bitString += fillBitsRight(sixBitAsciiToBitString(decoded.getRadioStatus()), 20); // Radio Status

			int padding = 168 - bitString.length();
			for (int i = 0; i < padding; i++) {
				bitString += "0";
			}
//			System.out.println(bitString);
			return toSixBit(bitString);
		}
		return null;
	}
	
	/**
	 * AIS Binary Addressed Message
	 */
	public static String encodeBinaryAddressedtMessage(BinaryAddressedMessage decoded) {
		if (decoded != null) {
			String bitString = "";

			bitString += fillBits(Integer.toBinaryString(decoded.getMessageType().getMessageType()), 6); // Message_Type
			bitString += fillBits(Integer.toBinaryString(decoded.getRepeatIndicator()), 2); // Repeat_Indicator
			bitString += fillBits(Long.toBinaryString(decoded.getSourceMmsi().getMMSI()), 30); // MMSI
			bitString += fillBits(Integer.toBinaryString(decoded.getSequenceNumber()), 2); // Sequence Number
			bitString += fillBits(Long.toBinaryString(decoded.getDestinationMmsi().getMMSI()), 30); // Destination MMSI
			bitString += decoded.getRetransmit() ? "1" : "0"; // Retransmit Flag
			bitString += "0"; // Spare
			bitString += fillBits(Integer.toBinaryString(decoded.getFunctionalId()), 6); // Function Identifier
			bitString += fillBits(Integer.toBinaryString(decoded.getDesignatedAreaCode()), 10); // DAC
			bitString += decoded.getBinaryData();
			int padding = 168 - bitString.length();
			for (int i = 0; i < padding; i++) {
				bitString += "0";
			}
			return toSixBit(bitString);
		}
		return null;
	}
	
	/**
	 * AIS Binary Broadcast Message
	 **/
	public static String encodeBinaryBroadcastMessage(BinaryBroadcastMessage decoded) {
		if (decoded != null) {
			String bitString = "";

			bitString += fillBits(Integer.toBinaryString(decoded.getMessageType().getMessageType()), 6); // Message_Type
			bitString += fillBits(Integer.toBinaryString(decoded.getRepeatIndicator()), 2); // Repeat_Indicator
			bitString += fillBits(Long.toBinaryString(decoded.getSourceMmsi().getMMSI()), 30); // MMSI
			bitString += "00"; // Spare
			bitString += fillBits(Integer.toBinaryString(decoded.getDesignatedAreaCode()), 10); // DAC
			bitString += fillBits(Integer.toBinaryString(decoded.getFunctionalId()), 6); // Function Identifier
			bitString += decoded.getBinaryData();
			int padding = 168 - bitString.length();
			for (int i = 0; i < padding; i++) {
				bitString += "0";
			}
			return toSixBit(bitString);
		}
		return null;
	}
	
	/**
	 * BaseStationReport
	 * */
	
	public static String encodeBaseStationReport(BaseStationReport decoded) {
		if (decoded != null) {
			String bitString = "";

			bitString += fillBits(Integer.toBinaryString(decoded.getMessageType().getMessageType()), 6); // Message_Type
			bitString += fillBits(Integer.toBinaryString(decoded.getRepeatIndicator()), 2); // Repeat_Indicator
			bitString += fillBits(Long.toBinaryString(decoded.getSourceMmsi().getMMSI()), 30); // MMSI
			bitString += fillBits(Integer.toBinaryString(decoded.getYear()), 14); // Year(UTC)
			bitString += fillBits(Integer.toBinaryString(decoded.getMonth()), 4); // Month(UTC)
			bitString += fillBits(Integer.toBinaryString(decoded.getDay()), 5); // Day(UTC)
			bitString += fillBits(Integer.toBinaryString(decoded.getHour()), 5); // Hour(UTC)
			bitString += fillBits(Integer.toBinaryString(decoded.getMinute()), 6); // Minute(UTC)
			bitString += fillBits(Integer.toBinaryString(decoded.getSecond()), 6); // Second(UTC)
			bitString += decoded.getPositionAccurate() ? "1" : "0"; // Position_Accuracy

			Float longitude = decoded.getLongitude().floatValue() * 600000f;
			bitString += fillBits(Integer.toBinaryString((int) Math.abs(longitude)), 28, longitude < 0); // Longitude

			Float latitude = decoded.getLatitude().floatValue() * 600000f;
			bitString += fillBits(Integer.toBinaryString((int) Math.abs(latitude)), 27, latitude < 0); // Latitude

			bitString += fillBits(Integer.toBinaryString(decoded.getPositionFixingDevice().getDevice()), 4); // Type_of_EPFD
			bitString += "0000000000"; // Spare
			bitString += decoded.getRaimFlag() ? "1" : "0"; // RAIM_Flag

			int padding = 168 - bitString.length();
			for (int i = 0; i < padding; i++) {
				bitString += "0";
			}

			return toSixBit(bitString);
		}
		return null;
	}
	
	/**
	 * StaticAndVoyageRelatedData
	 * */
	
	public static String encodeStaticAndVoyageRelatedData(StaticAndVoyageData decoded) {
		if (decoded != null) {
			String bitString = "";

			bitString += fillBits(Integer.toBinaryString(decoded.getMessageType().getMessageType()), 6); // Message_Type
			bitString += fillBits(Integer.toBinaryString(decoded.getRepeatIndicator()), 2); // Repeat_Indicator
			bitString += fillBits(Long.toBinaryString(decoded.getSourceMmsi().getMMSI()), 30); // MMSI
			bitString += "00"; // AIS_Version
			bitString += fillBits(Long.toBinaryString(decoded.getImo().getIMO()), 30); // IMO_Number
			bitString += fillBitsRight(sixBitAsciiToBitString(mask(decoded.getCallsign(), MASK_CALLSIGN)), 42); // Callsign
			bitString += fillBitsRight(sixBitAsciiToBitString(mask(decoded.getShipName(), MASK_SHIPNAME)), 120); // Ship_Name
			bitString += fillBits(Integer.toBinaryString(decoded.getShipType().getType()), 8); // Ship_Type
			bitString += fillBits(Integer.toBinaryString(decoded.getToBow()), 9); // Dimension_to_Bow
			bitString += fillBits(Integer.toBinaryString(decoded.getToStern()), 9); // Dimension_to_Stern
			bitString += fillBits(Integer.toBinaryString(decoded.getToPort()), 6); // Dimension_to_Port
			bitString += fillBits(Integer.toBinaryString(decoded.getToStarboard()), 6); // Dimension_to_Starboard
			bitString += fillBits(Integer.toBinaryString(decoded.getPositionFixingDevice().getDevice()), 4); // PositionFixingDevice
			bitString += fillBits(etaToBitString(decoded.getEta()), 20); // ETA
			bitString += fillBits(Integer.toBinaryString((int) (decoded.getDraught().floatValue() * 10f)), 8);
			bitString += fillBitsRight(sixBitAsciiToBitString(mask(decoded.getDestination(), MASK_DESTINATION)), 120);
			bitString += decoded.getDataTerminalReady() ? "1" : "0";

			int padding = 426 - bitString.length();
			for (int i = 0; i < padding; i++) {
				bitString += "0";
			}

			return toSixBit(bitString);
		}
		return null;
	}
	
	/**
	 * ExtendedClassBEquipmentPositionReport
	 */
	public static String encodeExtendedClassBEquipmentPositionReport(ExtendedClassBEquipmentPositionReport decoded) {
		if (decoded != null) {
			String bitString = "";
			
			bitString += fillBits(Integer.toBinaryString(decoded.getMessageType().getMessageType()), 6); // Message_Type
			bitString += fillBits(Integer.toBinaryString(decoded.getRepeatIndicator()), 2); // Repeat_Indicator
			bitString += fillBits(Long.toBinaryString(decoded.getSourceMmsi().getMMSI()), 30); // MMSI
			bitString += fillBitsRight(sixBitAsciiToBitString(decoded.getRegionalReserved1()), 8); // Regional Reserved 1 +00000000?
			
			bitString += fillBits(Integer.toBinaryString((int) (decoded.getSpeedOverGround().floatValue() * 10f)), 10); // Speed_over_Ground
			bitString += decoded.getPositionAccurate() ? "1" : "0"; // Position_Accuracy
			double longitude = decoded.getLongitude().doubleValue() * 600000f;
			bitString += fillBits(Integer.toBinaryString((int) Math.abs(longitude)), 28, longitude < 0); // Longitude

			double latitude = decoded.getLatitude().doubleValue() * 600000f;
			bitString += fillBits(Integer.toBinaryString((int) Math.abs(latitude)), 27, latitude < 0); // Latitude

			bitString += fillBits(Integer.toBinaryString((int) (decoded.getCourseOverGround() * 10f)), 12); // Course_over_Ground
			bitString += fillBits(Integer.toBinaryString(decoded.getTrueHeading()), 9); // True_Heading
			bitString += fillBits(Integer.toBinaryString(decoded.getSecond()), 6); // Time_Stamp(in_seconds)
			
			bitString += fillBitsRight(sixBitAsciiToBitString(decoded.getRegionalReserved2()), 2); // Regional Reserved 1 +00?
			
			bitString += fillBitsRight(sixBitAsciiToBitString(mask(decoded.getShipName(), MASK_SHIPNAME)), 120); // Ship_Name
			bitString += fillBits(Integer.toBinaryString(decoded.getShipType().getType()), 8); // Ship_Type
			bitString += fillBits(Integer.toBinaryString(decoded.getToBow()), 9); // Dimension_to_Bow
			bitString += fillBits(Integer.toBinaryString(decoded.getToStern()), 9); // Dimension_to_Stern
			bitString += fillBits(Integer.toBinaryString(decoded.getToPort()), 6); // Dimension_to_Port
			bitString += fillBits(Integer.toBinaryString(decoded.getToStarboard()), 6); // Dimension_to_Starboard
			bitString += fillBits(Integer.toBinaryString(decoded.getPositionFixingDevice().getDevice()), 4); // PositionFixingDevice
			
			bitString += decoded.getRaimFlag() ? "1" : "0"; // Raim Flag
			bitString += decoded.getDataTerminalReady() ? "1" : "0"; // DTE
			bitString += decoded.getAssigned() ? "1" : "0"; // Assigned Flag
			bitString += "0000"; // Spare

			int padding = 312 - bitString.length();
			for (int i = 0; i < padding; i++) {
				bitString += "0";
			}

			return toSixBit(bitString);
		}
		return null;
	}
	
	/**
	 * StandardSARAircraftPositionReport
	 */
	public static String encodeStandardSARAircraftPositionReport(StandardSARAircraftPositionReport decoded) {
		if (decoded != null) {
			String bitString = "";

			bitString += fillBits(Integer.toBinaryString(decoded.getMessageType().getMessageType()), 6); // Message_Type
			bitString += fillBits(Integer.toBinaryString(decoded.getRepeatIndicator()), 2); // Repeat_Indicator
			bitString += fillBits(Long.toBinaryString(decoded.getSourceMmsi().getMMSI()), 30); // MMSI
			bitString += fillBits(Integer.toBinaryString(decoded.getAltitude()), 12); // Altitude
			bitString += fillBits(Integer.toBinaryString(decoded.getSpeed()), 10); // Speed_over_Ground
			bitString += decoded.getPositionAccurate() ? "1" : "0"; // Position_Accuracy

			Float longitude = decoded.getLongitude().floatValue() * 600000f;
			bitString += fillBits(Integer.toBinaryString((int) Math.abs(longitude)), 28, longitude < 0); // Longitude

			Float latitude = decoded.getLatitude().floatValue() * 600000f;
			bitString += fillBits(Integer.toBinaryString((int) Math.abs(latitude)), 27, latitude < 0); // Latitude

			bitString += fillBits(Integer.toBinaryString((int) (decoded.getCourseOverGround() * 10f)), 12); // Course_over_Ground
			bitString += fillBits(Integer.toBinaryString(decoded.getSecond()), 6); // Time_Stamp(in_seconds)
			bitString += fillBits(decoded.getRegionalReserved(), 8); // Regional_reserved
			bitString += decoded.getDataTerminalReady() ? "1" : "0";
			bitString += "000";
			bitString += decoded.getAssigned() ? "1" : "0";
			bitString += decoded.getRaimFlag() ? "1" : "0"; // RAIM_Flag
			bitString += fillBits(decoded.getRadioStatus(), 20);

			return toSixBit(bitString);
		}
		return null;
	}
	
	public static String encodeLongRangeAISBroadcastMessage(LongRangeAISBroadcastMessage decoded) {
		if (decoded != null) {
			String bitString = "";
			
			bitString += fillBits(Integer.toBinaryString(decoded.getMessageType().getMessageType()), 6); // Message_Type
			bitString += fillBits(Integer.toBinaryString(decoded.getRepeatIndicator()), 2); // Repeat_Indicator
			bitString += fillBits(Long.toBinaryString(decoded.getSourceMmsi().getMMSI()), 30); // MMSI
			bitString += decoded.getPositionAccuracy() ? "1" : "0"; // Position_Accuracy
			bitString += decoded.getRaimFlag() ? "1" : "0"; // RAIM_Flag
			bitString += fillBits(Integer.toBinaryString(decoded.getNavigationStatus().getStatus()), 4); // Navigation_Status
			
			double longitude = decoded.getLongitude().doubleValue() * 600f;
			bitString += fillBits(Integer.toBinaryString((int) Math.abs(longitude)), 18, longitude < 0); // Longitude

			double latitude = decoded.getLatitude().doubleValue() * 600f;
			bitString += fillBits(Integer.toBinaryString((int) Math.abs(latitude)), 17, latitude < 0); // Latitude
			
			bitString += fillBits(Integer.toBinaryString((int) (decoded.getSpeedOverGround().floatValue())), 6); // Speed_over_Ground
			bitString += fillBits(Integer.toBinaryString((int) (decoded.getCourseOverGround().floatValue())), 9); // Course_over_Ground
			bitString += decoded.getGnssPositionStatus() ? "1" : "0"; // GNSS_Position_Status
			bitString += "0"; // Spare
			
			return toSixBit(bitString);
		}
		return null;
	}
	
	/**
	 * StaticDataReport 24A
	 */
	public static String encodeStaticDataReportA(StaticDataReport decoded) {
		if (decoded != null) {
			String bitString = "";

			bitString += fillBits(Integer.toBinaryString(decoded.getMessageType().getMessageType()), 6); // Message_Type
			bitString += fillBits(Integer.toBinaryString(decoded.getRepeatIndicator()), 2); // Repeat_Indicator
			bitString += fillBits(Long.toBinaryString(decoded.getSourceMmsi().getMMSI()), 30); // MMSI
			bitString += fillBits(Integer.toBinaryString(0), 2); // Part_Number should be 0 for A
			bitString += fillBitsRight(sixBitAsciiToBitString(mask(decoded.getShipName(), MASK_SHIPNAME)), 120); // Ship_Name

			return toSixBit(bitString);
		}
		return null;
	}
	
	/**
	 * StaticDataReport 24B
	 */
	public static String encodeStaticDataReportB(StaticDataReport decoded) {
		if (decoded != null) {
			String bitString = "";

			bitString += fillBits(Integer.toBinaryString(decoded.getMessageType().getMessageType()), 6); // Message_Type
			bitString += fillBits(Integer.toBinaryString(decoded.getRepeatIndicator()), 2); // Repeat_Indicator
			bitString += fillBits(Long.toBinaryString(decoded.getSourceMmsi().getMMSI()), 30); // MMSI
			bitString += fillBits(Integer.toBinaryString(1), 2); // Part_Number should be 1 for B
			bitString += fillBits(Integer.toBinaryString(decoded.getShipType().getType()), 8); // Ship_Type
			bitString += fillBitsRight(sixBitAsciiToBitString(mask(decoded.getVendorId(), MASK_SHIPNAME)), 42); // Ship_Name
			bitString += fillBitsRight(sixBitAsciiToBitString(mask(decoded.getCallsign(), MASK_CALLSIGN)), 42); // Callsign
			bitString += fillBits(Integer.toBinaryString(decoded.getToBow()), 9); // Dimension_to_Bow
			bitString += fillBits(Integer.toBinaryString(decoded.getToStern()), 9); // Dimension_to_Stern
			bitString += fillBits(Integer.toBinaryString(decoded.getToPort()), 6); // Dimension_to_Port
			bitString += fillBits(Integer.toBinaryString(decoded.getToStarboard()), 6); // Dimension_to_Starboard
			bitString += fillBits(Integer.toBinaryString(decoded.getPositionFixingDevice().getDevice()), 4); // PositionFixingDevice
			bitString += fillBits(Integer.toBinaryString(0), 2); // Spare

			return toSixBit(bitString);
		}
		return null;
	}
	
	/***************************************************************************
	 ***************D E C O D I N G   F U N C T I O N S*************************
	 ***************************************************************************/
	
	/**
	 *  StaticAndVoyageData
	 * */
	public static StaticAndVoyageData decodeStaticAndVoyageData(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.StaticAndVoyageRelatedData))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
			
		Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));
		IMO imo = IMO.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(40, 70)));
		String callsign = AISPayloadEncryptionUtil.convertToString(encodedMessage.getBits(70, 112));
		String shipName = AISPayloadEncryptionUtil.convertToString(encodedMessage.getBits(112, 232));
		ShipType shipType = ShipType.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(232, 240)));
		Integer toBow = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(240, 249));
		Integer toStern = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(249, 258));
		Integer toPort = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(258, 264));
		Integer toStarboard = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(264, 270));
		PositionFixingDevice positionFixingDevice = PositionFixingDevice.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(270, 274)));
		String eta = AISPayloadEncryptionUtil.convertToTime(encodedMessage.getBits(274, 294));
		Float draught = AISPayloadEncryptionUtil.convertToUnsignedFloat(encodedMessage.getBits(294, 302)) / 10f;
		String destination = AISPayloadEncryptionUtil.convertToString(encodedMessage.getBits(302, 422));
		Boolean dataTerminalReady = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(422, 423));
		
		return new StaticAndVoyageData(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi, imo, callsign, shipName, shipType, toBow, toStern, toStarboard, toPort, positionFixingDevice, eta, draught, destination, dataTerminalReady);
	}
	
	/**
	 *  PositionReportClassA
	 * */
	public static PositionReportClassA decodePositionReportClassA(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.PositionReportClassA))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
			
		Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));

		NavigationStatus navigationStatus = NavigationStatus.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(38, 42)));
		Integer rateOfTurn = AISPayloadEncryptionUtil.convertToSignedInteger(encodedMessage.getBits(42, 50));
		Float speedOverGround = AISPayloadEncryptionUtil.convertToUnsignedFloat(encodedMessage.getBits(50, 60)) / 10f;
		Boolean positionAccurate = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(60, 61));
		Double longitude = AISPayloadEncryptionUtil.convertToDouble(encodedMessage.getBits(61, 89)) / 600000f;
		Double latitude = AISPayloadEncryptionUtil.convertToDouble(encodedMessage.getBits(89, 116)) / 600000f;
		Float courseOverGround = AISPayloadEncryptionUtil.convertToUnsignedFloat(encodedMessage.getBits(116, 128)) / 10f;
		Integer trueHeading = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(128, 137));
		Integer second = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(137, 143));
		ManeuverIndicator maneuverIndicator = ManeuverIndicator.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(143, 145)));
		Boolean raimFlag = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(148, 149));
		
		return new PositionReportClassA(encodedMessage.getOriginalNmea(), repeatIndicator,
				sourceMmsi, navigationStatus, rateOfTurn, speedOverGround,
				positionAccurate, latitude, longitude, courseOverGround,
				trueHeading, second, maneuverIndicator, raimFlag);
	}
	
	/**
	 *  PositionReportClassAAssignedSchedule
	 * */
	public static PositionReportClassAAssignedSchedule decodePositionReportClassAAssignedSchedule(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.PositionReportClassAAssignedSchedule))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
			
		Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));

		NavigationStatus navigationStatus = NavigationStatus.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(38, 42)));
		Integer rateOfTurn = AISPayloadEncryptionUtil.convertToSignedInteger(encodedMessage.getBits(42, 50));
		Float speedOverGround = AISPayloadEncryptionUtil.convertToUnsignedFloat(encodedMessage.getBits(50, 60)) / 10f;
		Boolean positionAccurate = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(60, 61));
		Double longitude = AISPayloadEncryptionUtil.convertToDouble(encodedMessage.getBits(61, 89)) / 600000f;
		Double latitude = AISPayloadEncryptionUtil.convertToDouble(encodedMessage.getBits(89, 116)) / 600000f;
		Float courseOverGround = AISPayloadEncryptionUtil.convertToUnsignedFloat(encodedMessage.getBits(116, 128)) / 10f;
		Integer trueHeading = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(128, 137));
		Integer second = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(137, 143));
		ManeuverIndicator maneuverIndicator = ManeuverIndicator.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(143, 145)));
		Boolean raimFlag = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(148, 149));
		
		return new PositionReportClassAAssignedSchedule(encodedMessage.getOriginalNmea(), AISMessageType.PositionReportClassAAssignedSchedule, repeatIndicator,
				sourceMmsi, navigationStatus, rateOfTurn, speedOverGround,
				positionAccurate, latitude, longitude, courseOverGround,
				trueHeading, second, maneuverIndicator, raimFlag);
	}
	
	/**
	 *  PositionReportClassAResponseToInterrogation
	 * */
	public static PositionReportClassAResponseToInterrogation decodePositionReportClassAResponseToInterrogation(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.PositionReportClassAResponseToInterrogation))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
			
		Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));

		NavigationStatus navigationStatus = NavigationStatus.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(38, 42)));
		Integer rateOfTurn = AISPayloadEncryptionUtil.convertToSignedInteger(encodedMessage.getBits(42, 50));
		Float speedOverGround = AISPayloadEncryptionUtil.convertToUnsignedFloat(encodedMessage.getBits(50, 60)) / 10f;
		Boolean positionAccurate = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(60, 61));
		Double longitude = AISPayloadEncryptionUtil.convertToDouble(encodedMessage.getBits(61, 89)) / 600000f;
		Double latitude = AISPayloadEncryptionUtil.convertToDouble(encodedMessage.getBits(89, 116)) / 600000f;
		Float courseOverGround = AISPayloadEncryptionUtil.convertToUnsignedFloat(encodedMessage.getBits(116, 128)) / 10f;
		Integer trueHeading = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(128, 137));
		Integer second = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(137, 143));
		ManeuverIndicator maneuverIndicator = ManeuverIndicator.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(143, 145)));
		Boolean raimFlag = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(148, 149));
		
		return new PositionReportClassAResponseToInterrogation(encodedMessage.getOriginalNmea(), AISMessageType.PositionReportClassAResponseToInterrogation, repeatIndicator,
				sourceMmsi, navigationStatus, rateOfTurn, speedOverGround,
				positionAccurate, latitude, longitude, courseOverGround,
				trueHeading, second, maneuverIndicator, raimFlag);
	}
	
	/**
	 *  BaseStationReport
	 * */
	public static BaseStationReport decodeBaseStationReport(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.BaseStationReport))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
			
		try {
		Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));
		Integer year = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(38, 52));
		Integer month = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(52, 56));
		Integer day = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(56, 61));
		Integer hour  = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(61, 66));
		Integer minute = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(66, 72));
		Integer second = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(72, 78));
		Boolean positionAccurate = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(78, 79));
		Float longitude = AISPayloadEncryptionUtil.convertToFloat(encodedMessage.getBits(79, 107)) / 600000f;
		Float latitude = AISPayloadEncryptionUtil.convertToFloat(encodedMessage.getBits(107, 134)) / 600000f;
		PositionFixingDevice positionFixingDevice = PositionFixingDevice.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(134, 138)));
		Boolean raimFlag = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(148, 149));

		return new BaseStationReport(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi, year, month,
				day, hour, minute, second, positionAccurate, latitude,
				longitude, positionFixingDevice, raimFlag);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 *  BinaryAddressedMessage
	 * */
	public static BinaryAddressedMessage decodeBinaryAddressedMessage(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.BinaryAddressedMessage))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
			
		try {
			Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
			MMSI sourceMmsi = MMSI
					.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));

			Integer sequenceNumber = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(38, 40));
			MMSI destinationMmsi = MMSI
					.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(40, 70)));
			Boolean retransmit = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(70, 71));
			Integer designatedAreaCode = AISPayloadEncryptionUtil
					.convertToUnsignedInteger(encodedMessage.getBits(72, 82));
			Integer functionalId = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(82, 88));
			String binaryData = encodedMessage.getBits(88, 1009);

			return new BinaryAddressedMessage(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi,
					sequenceNumber, destinationMmsi, retransmit, designatedAreaCode, functionalId, binaryData);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 *  BinaryAcknowledge (SafetyRelatedAcknowledge)
	 * */
	public static BinaryAcknowledge decodeBinaryAcknowledge(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.BinaryAcknowledge))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());

		try {
			Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
			MMSI sourceMmsi = MMSI
					.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));
			MMSI mmsi1 = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(40, 70)));
			MMSI mmsi2 = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(72, 102)));
			MMSI mmsi3 = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(104, 134)));
			MMSI mmsi4 = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(136, 166)));

			return new BinaryAcknowledge(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi, mmsi1, mmsi2,
					mmsi3, mmsi4);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 *  BinaryBroadcastMessage
	 * */
	public static BinaryBroadcastMessage decodeBinaryBroadcastMessage(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.BinaryBroadcastMessage))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
		
		try {
			Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
			MMSI sourceMmsi = MMSI
					.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));

			Integer designatedAreaCode = AISPayloadEncryptionUtil
					.convertToUnsignedInteger(encodedMessage.getBits(38, 52));
			Integer functionalId = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(52, 56));
			String binaryData = encodedMessage.getBits(56, encodedMessage.getNumberOfBits());

			return new BinaryBroadcastMessage(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi,
					designatedAreaCode, functionalId, binaryData);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 * StandardSARAircraftPositionReport
	 * */
	public static StandardSARAircraftPositionReport decodeStandardSARAircraftPositionReport(
			EncodedAISPayload encodedMessage) {
		if (!encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (!encodedMessage.getMessageType().equals(
				AISMessageType.StandardSARAircraftPositionReport))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType()
					.getMessageType());

		try {
		Integer repeatIndicator = AISPayloadEncryptionUtil
				.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil
				.convertToUnsignedLong(encodedMessage.getBits(8, 38)));

		Integer altitude = AISPayloadEncryptionUtil
				.convertToUnsignedInteger(encodedMessage.getBits(38, 50));
		Integer speed = AISPayloadEncryptionUtil
				.convertToUnsignedInteger(encodedMessage.getBits(50, 60));
		Boolean positionAccurate = AISPayloadEncryptionUtil
				.convertToBoolean(encodedMessage.getBits(60, 61));
		Float longitude = AISPayloadEncryptionUtil
				.convertToFloat(encodedMessage.getBits(61, 89)) / 600000f;
		Float latitude = AISPayloadEncryptionUtil.convertToFloat(encodedMessage
				.getBits(89, 116)) / 600000f;
		Float courseOverGround = AISPayloadEncryptionUtil
				.convertToUnsignedFloat(encodedMessage.getBits(116, 128)) / 10f;
		Integer second = AISPayloadEncryptionUtil
				.convertToUnsignedInteger(encodedMessage.getBits(128, 134));
		String regionalReserved = encodedMessage.getBits(134, 142);
		Boolean dataTerminalReady = AISPayloadEncryptionUtil
				.convertToBoolean(encodedMessage.getBits(142, 143));
		Boolean assigned = AISPayloadEncryptionUtil
				.convertToBoolean(encodedMessage.getBits(146, 147));
		Boolean raimFlag = AISPayloadEncryptionUtil
				.convertToBoolean(encodedMessage.getBits(147, 148));
		String radioStatus = encodedMessage.getBits(148, 168);

		return new StandardSARAircraftPositionReport(
				encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi,
				altitude, speed, positionAccurate, latitude, longitude,
				courseOverGround, second, regionalReserved, dataTerminalReady,
				assigned, raimFlag, radioStatus);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 *  UTCAndDateInquiry
	 * */
	public static UTCAndDateInquiry decodeUTCAndDateInquiry(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.UTCAndDateInquiry))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
			
		try {
		Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));
		MMSI destinationMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(40, 70)));

		return new UTCAndDateInquiry(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi, destinationMmsi);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 *  UTCAndDateResponse
	 * */
	public static UTCAndDateResponse decodeUTCAndDateResponse(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.UTCAndDateResponse))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
			
		try {
		Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));
		Integer year = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(38, 52));
		Integer month = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(52, 56));
		Integer day = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(56, 61));
		Integer hour  = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(61, 66));
		Integer minute = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(66, 72));
		Integer second = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(72, 78));
		Boolean positionAccurate = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(78, 79));
		Float longitude = AISPayloadEncryptionUtil.convertToFloat(encodedMessage.getBits(79, 107)) / 600000f;
		Float latitude = AISPayloadEncryptionUtil.convertToFloat(encodedMessage.getBits(107, 134)) / 600000f;
		PositionFixingDevice positionFixingDevice = PositionFixingDevice.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(134, 138)));
		Boolean raimFlag = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(148, 149));

		return new UTCAndDateResponse(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi, year, month,
				day, hour, minute, second, positionAccurate, latitude,
				longitude, positionFixingDevice, raimFlag);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 *  AddressedSafetyRelatedMessage
	 * */
	public static AddressedSafetyRelatedMessage decodeAddressedSafetyRelatedMessage(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.AddressedSafetyRelatedMessage))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
			
		try {
			Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
			MMSI sourceMmsi = MMSI
					.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));
			Integer sequenceNumber = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(38, 40));
			MMSI destinationMmsi = MMSI
					.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(40, 70)));
			Boolean retransmit = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(70, 71));
			String text = AISPayloadEncryptionUtil.convertToString(encodedMessage.getBits(70, 1009));

			return new AddressedSafetyRelatedMessage(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi,
					sequenceNumber, destinationMmsi, retransmit, text);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 *  SafetyRelatedBroadcastMessage
	 * */
	public static SafetyRelatedBroadcastMessage decodeSafetyRelatedBroadcastMessage(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.SafetyRelatedBroadcastMessage))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());

		try {

			Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
			MMSI sourceMmsi = MMSI
					.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));
			String text = AISPayloadEncryptionUtil.convertToString(encodedMessage.getBits(40, 1049));

			return new SafetyRelatedBroadcastMessage(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi, text);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 *  Interrogation
	 * */
	public static Interrogation decodeInterrogation(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.Interrogation))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
		try {
		int messageLength = encodedMessage.getNumberOfBits();
		Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));

		MMSI mmsi1 = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(40, 70)));
		Integer type1_1 = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(70, 76));
		Integer offset1_1 = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(76, 88));
		Integer type1_2 = messageLength > 88 ? AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(90, 96)) : null;
		Integer offset1_2 = messageLength > 88 ? AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(96, 108)) : null;
		
		MMSI mmsi2 = messageLength > 160 ? MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(110, 140))) : null;
		Integer type2_1 = messageLength > 160 ? AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(140, 146)) : null;
		Integer offset2_1 = messageLength > 160 ? AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(146, 158)) : null;
		
		return new Interrogation(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi, mmsi1, type1_1, offset1_1, type1_2, offset1_2, mmsi2, type2_1, offset2_1);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 *  AssignedModeCommand
	 * */
	public static AssignedModeCommand decodeAssignedModeCommand(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.AssignedModeCommand))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
		
		try {
		int messageLength = encodedMessage.getNumberOfBits();
		Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));

		MMSI destinationMmsiA = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(40, 70)));
		Integer offsetA = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(70, 82));
		Integer incrementA = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(82, 92));
		
		MMSI destinationMmsiB = messageLength >= 144 ? MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(92, 122))) : null;
		Integer offsetB = messageLength >= 144 ? AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(122, 134)) : null;
		Integer incrementB = messageLength >= 144 ? AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(134, 144)) : null;
		
		return new AssignedModeCommand(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi, destinationMmsiA, offsetA, incrementA, destinationMmsiB, offsetB, incrementB);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 *  DGNSSBinaryBroadcastMessage
	 * */
	public static DGNSSBinaryBroadcastMessage decodeDGNSSBinaryBroadcastMessage(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.DGNSSBinaryBroadcastMessage))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
			
		try {
			Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
			MMSI sourceMmsi = MMSI
					.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));

			Float longitude = AISPayloadEncryptionUtil.convertToFloat(encodedMessage.getBits(40, 58)) / 10f;
			Float latitude = AISPayloadEncryptionUtil.convertToFloat(encodedMessage.getBits(58, 75)) / 10f;
			String binaryData = encodedMessage.getBits(80, 816);

			return new DGNSSBinaryBroadcastMessage(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi,
					latitude, longitude, binaryData);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 *  StandardClassBCSPositionReport
	 * */
	public static StandardClassBCSPositionReport decodeStandardClassBCSPositionReport(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.StandardClassBCSPositionReport))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
		
		try {
		Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));

		String regionalReserved1 = encodedMessage.getBits(38, 46);
		Float speedOverGround = AISPayloadEncryptionUtil.convertToUnsignedFloat(encodedMessage.getBits(46, 55)) / 10f;
		Boolean positionAccurate = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(56, 57));
		Double longitude = AISPayloadEncryptionUtil.convertToDouble(encodedMessage.getBits(57, 85)) / 600000f;
		Double latitude = AISPayloadEncryptionUtil.convertToDouble(encodedMessage.getBits(85, 112)) / 600000f;
		Float courseOverGround = AISPayloadEncryptionUtil.convertToUnsignedFloat(encodedMessage.getBits(112, 124)) / 10f;
		Integer trueHeading = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(124, 133));
		Integer second = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(133, 139));
		String regionalReserved2 = encodedMessage.getBits(139, 141);
		Boolean csUnit = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(141, 142));
		Boolean display = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(142, 143));
		Boolean dsc = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(143, 144));
		Boolean band = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(144, 145));
		Boolean message22 = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(145, 146));
		Boolean assigned = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(146, 147));
		Boolean raimFlag = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(147, 148));
		String radioStatus = encodedMessage.getBits(148, 168);

		return new StandardClassBCSPositionReport(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi,
				regionalReserved1, speedOverGround, positionAccurate, latitude,
				longitude, courseOverGround, trueHeading, second,
				regionalReserved2, csUnit, display, dsc, band, message22,
				assigned, raimFlag, radioStatus);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 *  ExtendedClassBEquipmentPositionReport
	 * */
	public static ExtendedClassBEquipmentPositionReport decodeExtendedClassBEquipmentPositionReport(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.ExtendedClassBEquipmentPositionReport))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
			
		try {
		Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));

		String regionalReserved1 = encodedMessage.getBits(38, 46);
		Float speedOverGround = AISPayloadEncryptionUtil.convertToUnsignedFloat(encodedMessage.getBits(46, 55)) / 10f;
		Boolean positionAccurate = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(56, 57));
		Double longitude = AISPayloadEncryptionUtil.convertToDouble(encodedMessage.getBits(57, 85)) / 600000f;
		Double latitude = AISPayloadEncryptionUtil.convertToDouble(encodedMessage.getBits(85, 112)) / 600000f;
		Float courseOverGround = AISPayloadEncryptionUtil.convertToUnsignedFloat(encodedMessage.getBits(112, 124)) / 10f;
		Integer trueHeading = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(124, 133));
		Integer second = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(133, 139));
		String regionalReserved2 = encodedMessage.getBits(139, 143);
		String shipName = AISPayloadEncryptionUtil.convertToString(encodedMessage.getBits(143, 263));
		ShipType shipType = ShipType.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(263, 271)));
		Integer toBow = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(271, 280));
		Integer toStern = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(280, 289));
		Integer toPort = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(289, 295));
		Integer toStarboard = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(295, 301));
		PositionFixingDevice positionFixingDevice = PositionFixingDevice.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(301, 305)));
		Boolean raimFlag = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(305, 306));
		Boolean dataTerminalReady = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(306, 307));
		Boolean assigned = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(307, 308));
		
		return new ExtendedClassBEquipmentPositionReport(encodedMessage.getOriginalNmea(), repeatIndicator,
				sourceMmsi, regionalReserved1, speedOverGround,
				positionAccurate, latitude, longitude, courseOverGround,
				trueHeading, second, regionalReserved2, shipName, shipType,
				toBow, toStern, toStarboard, toPort, positionFixingDevice,
				raimFlag, dataTerminalReady, assigned);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 *  DataLinkManagement
	 * */
	public static DataLinkManagement decodeDataLinkManagement(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.DataLinkManagement))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
			
		try {
		Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));

		final int n = encodedMessage.getNumberOfBits();
		
		Integer offsetNumber1 = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(40, 52));
		Integer reservedSlots1 = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(52, 56));
		Integer timeout1 = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(56, 59));
		Integer increment1 = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(59, 70));
		Integer offsetNumber2 = n >= 100 ? AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(70, 82)) : null;
		Integer reservedSlots2 = n >= 100 ? AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(82, 86)) : null;
		Integer timeout2 = n >= 100 ? AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(86, 89)) : null;
		Integer increment2 = n >= 100 ? AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(89, 100)) : null;
		Integer offsetNumber3 = n >= 130 ? AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(100, 112)) : null;
		Integer reservedSlots3 = n >= 130 ? AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(112,116)) : null;
		Integer timeout3 = n >= 130 ? AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(116, 119)) : null;
		Integer increment3 = n >= 130 ? AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(119, 130)) : null;
		Integer offsetNumber4 = n >= 160 ? AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(130, 142)) : null;
		Integer reservedSlots4 = n >= 160 ? AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(142, 146)) : null;
		Integer timeout4 = n >= 160 ? AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(146, 149)) : null;
		Integer increment4 = n >= 160 ? AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(149, 160)) : null;

		return new DataLinkManagement(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi,
				offsetNumber1, reservedSlots1, timeout1, increment1,
				offsetNumber2, reservedSlots2, timeout2, increment2,
				offsetNumber3, reservedSlots3, timeout3, increment3,
				offsetNumber4, reservedSlots4, timeout4, increment4);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 *  AidToNavigationReport
	 * */
	public static AidToNavigationReport decodeAidToNavigationReport(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.AidToNavigationReport))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
		
		try {
		Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));

		AidType aidType = AidType.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(38, 43)));
		String name = AISPayloadEncryptionUtil.convertToString(encodedMessage.getBits(43, 163));
		Boolean positionAccurate = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(163, 164));
		Float longitude = AISPayloadEncryptionUtil.convertToFloat(encodedMessage.getBits(164, 192)) / 600000f;
		Float latitude = AISPayloadEncryptionUtil.convertToFloat(encodedMessage.getBits(192, 219)) / 600000f;
		Integer toBow = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(219, 228));
		Integer toStern = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(228, 237));
		Integer toPort = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(237, 243));
		Integer toStarboard = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(243, 249));
		PositionFixingDevice positionFixingDevice = PositionFixingDevice.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(249, 253)));
		Integer second = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(253, 259));
		Boolean offPosition = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(259, 260));
		String regionalUse = encodedMessage.getBits(260,268);
		Boolean raimFlag = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(268, 269));
		Boolean virtualAid = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(269, 270));
		Boolean assignedMode = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(270, 271));
		String nameExtension = AISPayloadEncryptionUtil.convertToString(encodedMessage.getBits(272, Math.min(encodedMessage.getNumberOfBits(), 361)));

		return new AidToNavigationReport(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi, aidType,
				name, positionAccurate, latitude, longitude, toBow, toStern,
				toStarboard, toPort, positionFixingDevice, second, offPosition,
				regionalUse, raimFlag, virtualAid, assignedMode, nameExtension);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 *  ChannelManagement
	 * */
	public static ChannelManagement decodeChannelManagement(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.ChannelManagement))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
		
		try {
			
		Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));
		
		Integer channelA = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(40, 52));
		Integer channelB = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(52, 64));
		TxRxMode transmitReceiveMode = TxRxMode.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(64, 68)));
		Boolean power = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(68, 69));
		Boolean addressed = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(139, 140));
		Boolean bandA = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(140, 141));
		Boolean bandB = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(141, 142));
		Integer zoneSize = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(142, 145));
		MMSI destinationMmsi1 = !addressed ? null : MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(69, 99)));
		MMSI destinationMmsi2 = !addressed ? null : MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(104, 134)));
		Float northEastLatitude = addressed ? null : AISPayloadEncryptionUtil.convertToFloat(encodedMessage.getBits(87, 104)) / 10f;
		Float northEastLongitude = addressed ? null : AISPayloadEncryptionUtil.convertToFloat(encodedMessage.getBits(69, 87)) / 10f;
		Float southWestLatitude = addressed ? null : AISPayloadEncryptionUtil.convertToFloat(encodedMessage.getBits(122, 138)) / 10f;
		Float southWestLongitude = addressed ? null : AISPayloadEncryptionUtil.convertToFloat(encodedMessage.getBits(104, 122)) / 10f;

		return new ChannelManagement(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi, channelA,
				channelB, transmitReceiveMode, power, northEastLongitude,
				northEastLatitude, southWestLongitude, southWestLatitude,
				destinationMmsi1, destinationMmsi2, addressed, bandA, bandB,
				zoneSize);
		
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 *  GroupAssignmentCommand
	 * */
	public static GroupAssignmentCommand decodeGroupAssignmentCommand(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.GroupAssignmentCommand))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
			
		try {
		Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));
		
		Float northEastLatitude = AISPayloadEncryptionUtil.convertToFloat(encodedMessage.getBits(61, 89)) / 10f;
		Float northEastLongitude = AISPayloadEncryptionUtil.convertToFloat(encodedMessage.getBits(61, 89)) / 10f;
		Float southWestLatitude = AISPayloadEncryptionUtil.convertToFloat(encodedMessage.getBits(61, 89)) / 10f;
		Float southWestLongitude = AISPayloadEncryptionUtil.convertToFloat(encodedMessage.getBits(61, 89)) / 10f;
		StationType stationType = StationType.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(143, 145)));
		ShipType shipType = ShipType.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(143, 145)));
		TxRxMode transmitReceiveMode = TxRxMode.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(128, 137)));
		ReportingInterval reportingInterval = ReportingInterval.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(143, 145)));
		Integer quietTime = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(128, 137));
		
		return new GroupAssignmentCommand(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi, northEastLatitude, northEastLongitude, southWestLatitude, southWestLongitude, stationType, shipType, transmitReceiveMode, reportingInterval, quietTime);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 *  StaticDataReport
	 * */
	public static StaticDataReport decodeStaticDataReport(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.StaticDataReport))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
		try {	
		Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));

		Integer partNumber = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(38, 40));

		String shipName = partNumber == 1 ? null : AISPayloadEncryptionUtil.convertToString(encodedMessage.getBits(40, 160));
		ShipType shipType = partNumber != 1 ? null : ShipType.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(40, 48)));
		String vendorId = partNumber != 1 ? null : AISPayloadEncryptionUtil.convertToString(encodedMessage.getBits(48, 90));
		String callsign = partNumber != 1 ? null : AISPayloadEncryptionUtil.convertToString(encodedMessage.getBits(90, 132));
		Integer toBow = partNumber != 1 ? null : AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(132, 141));
		Integer toStern = partNumber != 1 ? null : AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(141, 150));
		Integer toPort = partNumber != 1 ? null : AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(150, 156));
		Integer toStarboard = partNumber != 1 ? null : AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(156, 162));
        PositionFixingDevice positionFixingDevice = partNumber != 1 ? null : PositionFixingDevice.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(162, 166)));
//		MMSI mothershipMmsi = partNumber == 0 ? null : MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(132, 162)));
		
		return new StaticDataReport(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi, partNumber, shipName, shipType, vendorId, callsign, toBow, toStern, toStarboard, toPort, positionFixingDevice);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 *  SingleSlotBinaryMessage
	 * */
	public static SingleSlotBinaryMessage decodeSingleSlotBinaryMessage(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.SingleSlotBinaryMessage))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
			
		try {
		Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));

		Boolean destinationIndicator = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(38, 39));
		Boolean binaryDataFlag = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(39, 40));
		MMSI destinationMMSI = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(40, 70)));
		String binaryData = encodedMessage.getBits(40, 168);

		return new SingleSlotBinaryMessage(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi,
				destinationIndicator, binaryDataFlag, destinationMMSI,
				binaryData);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 *  MultipleSlotBinaryMessageWithCommunicationState
	 * */
	public static MultipleSlotBinaryMessageWithCommunicationState decodeMultipleSlotBinaryMessageWithCommunicationState(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.MultipleSlotBinaryMessageWithCommunicationState))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
			
		try {
		Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));

		Boolean addressed = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(38, 39));
		Boolean structured = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(39, 40));
		MMSI destinationMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(40, 70)));
		Integer applicationId = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(70, 86));
		String data = encodedMessage.getBits(86, 86+1004+1);
		String radioStatus = null; // Decoder.convertToBinaryString(encodedMessage.getBits(6, 8));

		return new MultipleSlotBinaryMessageWithCommunicationState(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi,
				addressed, structured, destinationMmsi, applicationId, data, radioStatus);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}
	
	/**
	 * Long Range AIS Broadcast Message
	 **/
	public static LongRangeAISBroadcastMessage decodeLongRangeAISBroadcastMessage(EncodedAISPayload encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedPayload(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.PositionReportForLongRangeApplications))
			throw new UnsupportedPayloadType(encodedMessage.getMessageType().getMessageType());
		try {
		Integer repeatIndicator = AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(AISPayloadEncryptionUtil.convertToUnsignedLong(encodedMessage.getBits(8, 38)));
		
		Boolean positionAccuracy = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(38, 39));
		Boolean raimFlag = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(39, 40));
		NavigationStatus navigationStatus = NavigationStatus.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(encodedMessage.getBits(40, 44)));
		Double longitude = AISPayloadEncryptionUtil.convertToDouble(encodedMessage.getBits(44, 62)) / 600f;
		Double latitude = AISPayloadEncryptionUtil.convertToDouble(encodedMessage.getBits(62, 79)) / 600f;
		Float speedOverGround = AISPayloadEncryptionUtil.convertToUnsignedFloat(encodedMessage.getBits(79, 85));
		Float courseOverGround = AISPayloadEncryptionUtil.convertToUnsignedFloat(encodedMessage.getBits(85, 94));
		Boolean gnssPositionStatus = AISPayloadEncryptionUtil.convertToBoolean(encodedMessage.getBits(94, 95));
		
		return new LongRangeAISBroadcastMessage(encodedMessage.getOriginalNmea(), repeatIndicator, sourceMmsi, positionAccuracy, raimFlag, 
				navigationStatus, longitude, latitude, speedOverGround, courseOverGround, gnssPositionStatus);
		} catch (Exception e) {
			throw new InvalidEncodedPayload(encodedMessage);
		}
	}

}
