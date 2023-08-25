package de.emir.service.codecs.nmea0183.encoding.sentence;

/**
 * This class generates a NMEA0183 Sentence Class
 * 
 * Howto use: 	1. Change DEFAULT_TALKER, sentenceName, names and types
 * 				2. run
 * 
 * @author cdenker
 *
 */
public class SentenceMachine {

	private static final String DEFAULT_TALKER = "AG";

	static String sentenceName = "PRC";
	
	static String[] names = { "leverDemandPosition", "leverDemandStatus",
			"rpmDemandValue", "rpmModeIndicator",
			"pitchDemandValue", "pitchModeIndicator",
			"operatingLocationIndicator", "numberOfEngineOrPropellerShaft"

			};
	
	//supported types: String, Double, lat, lon, time
	static String[] types = { "Double", "String",
			"Double", "String",
			"Double", "String",
			"String", "Double"
			};
	
//	static String sentenceName = "HTD";
//	
//	static String[] names = {"override", "commandedRudderAngle", 
//			"commandedRudderDirection", "selectedSteeringMode", 
//			"turnMode", "commandedRudderLimitDeg",
//			"commandedOffHeadingLimitDeg", "commandedRadiusOfTurnForHeadingChangesMi",
//			"commandedRotForHeadingChnagesDegMin", "commandedHeadingToSteerDeg",
//			"commandedOffTrackLimitMi", "commandedTrackDeg",
//			"headingReferenceInUseTM", "rudderStatus",
//			"offHeadingStatus", "offTrackStatus",
//			"vesselHeadingDeg"
//			
//			};
//	
//	static String[] types = {"String", "Double", 
//			"String", "String",
//			"String", "Double",
//			"Double", "Double",
//			"Double", "Double", 
//			"Double", "Double",
//			"String", "String",
//			"String", "String",
//			"Double"
//			};
	
	
	public static void main(String[] args) {
		System.out.println("package de.emir.service.codecs.nmea0183.encoding.sentence; \n\nimport java.util.Map; import javax.persistence.Entity; \n"
				+ "import javax.persistence.EnumType; \nimport javax.persistence.Enumerated; \nimport javax.persistence.Inheritance;\n"
				+ "import javax.persistence.InheritanceType; \nimport de.emir.service.codecs.nmea0183.encoding.data.Reference;\n"
				+ "import de.emir.service.codecs.nmea0183.encoding.data.SpeedUnit; \nimport de.emir.service.codecs.nmea0183.encoding.util.ParseUtils; \n\n");
		
		System.out.println("/**\n"
				+ " * This is a NMEA0183 "+ sentenceName +" sentence.\n"
						+ " * Implementation details can be found within the NMEA0183 specification, e.g. at http://caxapa.ru/thumbs/214299/NMEA0183_.pdf\n"
						+ " * \n"
						+ " * @author cdenker\n"
						+ " *\n"
						+ " */\n\n");
		
		
		System.out.println("@Entity\n"
				+ "@Inheritance(strategy = InheritanceType.JOINED)\n"
				+ "public class " + sentenceName + "Sentence"
				+ " extends Sentence{\n");
		
		
			System.out.println("/** Default begin char for this sentence type. */ \n"
					+ "public static final char BEGIN_CHAR = '$';\n"
					+ "	/** Default talker for this sentence. */\n"
					+ "//FIXME: check DEFAULT_TALKER\n"
					+ "	public static final String DEFAULT_TALKER = \""+DEFAULT_TALKER+"\";\n"
					+ "	/** Sentence id. */\n"
					+ "	public static final String SENTENCE_ID = \""+sentenceName+"\";\n"
					+ "	/** Default count of fields. */\n"
					+ "	public static final int FIELD_COUNT = "+names.length+";\n\n");
			
			
			/**
			 * create fields
			 */
			for(int i = 0; i< names.length; i++){
				if(types[i].equals("lat")||types[i].equals("lon")){
					System.out.println("private Double " + names[i] +";");
				} else if (types[i].equals("time")) {
					System.out.println("private Time " + names[i] +";");
				} else {
					System.out.println("private " + types[i] + " " + names[i] +";");
				}
			}
			
			/**
			 * create constructors
			 */
			System.out.println("/**\n	 * Default constructor for writing. Empty Sentence to fill attributes and\n"
					+ "	 * call {@link #toNMEA()}.\n"
					+ "	 */ \n"
					+ "	public "+sentenceName+"Sentence() {\n"
					+ "		super(BEGIN_CHAR, DEFAULT_TALKER, SENTENCE_ID, FIELD_COUNT);\n"
					+ "	}\n\n");
			System.out.println("/**\n"
					+ "	 * Default constructor for parsing.\n"
					+ "	 * \n"
					+ "					* @param nmea\n"
					+ "	 *            Nmea String to be parsed. \n"
					+ "	 */ \n"
					+ "	public "+sentenceName+"Sentence(String nmea) {\n"
					+ "		super(nmea);\n"
					+ "	}\n\n");
			
			/**
			 * create decode
			 */
			System.out.println("@Override\n"
					+ "	protected void decode() {\n"
					+ "		int index = 0;\n" );
				for(int i = 0; i< names.length; i++){
					switch(types[i]){
					case "Double":
						System.out.println(names[i] + " = ParseUtils.parseDouble(getValue(index++));" );
						break;
					case "String":
						System.out.println(names[i] + " = getValue(index++);" );
						break;
					case "lat":
						System.out.println(names[i] + " = ParseUtils.parseCoordinate(getValue(index++));" );
						break;
					case "lon":
						System.out.println(names[i] + " = ParseUtils.parseCoordinate(getValue(index++));" );
						break;
					case "time":
						System.out.println(names[i] + " = ParseUtils.parseTime(getValue(index++));" );
						break;
					default:
						System.out.println("ERROR - Unsupported Type");
					}
				}			
			
			System.out.println("}\n\n");
			
			
			/**
			 * create encode
			 */
			System.out.println("@Override\n"
					+ "	protected void encode() {\n"
					+ "		int index = 0;\n" );
				for(int i = 0; i< names.length; i++){
					switch(types[i]){
					case "Double":
						System.out.println("setValue(index++, ParseUtils.toString("+names[i]+",1));" );
						break;
					case "String":
						System.out.println("setValue(index++, "+names[i]+");" );
						break;
					case "lat":
						System.out.println("setValue(index++, ParseUtils.doubleToDecimalDegreeString("+names[i]+", 2));");
						break;
					case "lon":
						System.out.println("setValue(index++, ParseUtils.doubleToDecimalDegreeString("+names[i]+", 3));");
						break;
					case "time":
						System.out.println("setValue(index++, ParseUtils.toString("+names[i]+"));" );
						break;
					default:
						System.out.println("ERROR - Unsupported Type");
					}
				}			
			
			System.out.println("}\n\n");
			
			
			/**
			 * create fillMap
			 */
			
			System.out.println("@Override\n"
					+ "	protected void fillMap(Map<String, Object> res) {\n");
			
				for(int i = 0; i< names.length; i++){
					System.out.println("if ("+names[i]+" != null) res.put(\""+names[i]+"\", "+names[i]+");");
				}
			
			
			System.out.println("}\n\n");			
		
		System.out.println("}");
		
		
	}

}
