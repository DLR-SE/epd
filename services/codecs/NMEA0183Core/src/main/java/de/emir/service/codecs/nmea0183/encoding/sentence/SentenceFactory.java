package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import de.emir.service.codecs.nmea0183.encoding.sentence.proprietary.ENGSentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.proprietary.EXTSentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.proprietary.MEASentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.proprietary.PARSentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.proprietary.POSSentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.proprietary.QDGSentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.proprietary.RUDSentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.proprietary.SIDSentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.proprietary.TPPSentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.proprietary.TRCSentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.proprietary.TRDSentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.proprietary.TVPSentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.proprietary.WEISentence;
import de.emir.service.codecs.nmea0183.encoding.util.SentenceUtils;

/**
 * The sentence factory creates sentences from strings in
 * {@link #createSentence(String)}. New sentence types can be registered by
 * calling {@link #registerSentenceType(String, Class)} and passing the
 * sentenceId as String and the class, that extends {@link Sentence} and handles
 * the parsing of the given string.
 * 
 */
public class SentenceFactory {
	/** Holds the registered sentence types. */
	private Map<String, Class<? extends Sentence>> sentenceTypes = new HashMap<String, Class<? extends Sentence>>();
	
	/**
	 * Gets the instance of this singleton class.
	 * @return
	 * SentenceFactory instance.
	 */
	public static SentenceFactory getInstance() {
		if (instance == null) {
			instance = new SentenceFactory();
		}
		return instance;
	}
	
	/** Holds the singleton instance. */
	private static SentenceFactory instance;
	
	/**
	 * Hides the constructor for singleton pattern. Call {@link #getInstance()}
	 * to get an instance of this class.
	 */
	private SentenceFactory() {
		registerSentenceType("VDM", AISSentence.class);
		registerSentenceType("VDO", AISSentence.class);
		registerSentenceType("APB", APBSentence.class);
		registerSentenceType("RMB", RMBSentence.class);
		registerSentenceType("ASHR", ASHRSentence.class);
		registerSentenceType("DPT", DPTSentence.class);
		registerSentenceType("EXT", EXTSentence.class);
		registerSentenceType("GGA", GGASentence.class);
		registerSentenceType("GLL", GLLSentence.class);
		registerSentenceType("GSV", GSVSentence.class);
		registerSentenceType("HDG", HDGSentence.class);
		registerSentenceType("HDT", HDTSentence.class);
		registerSentenceType("HTC", HTCSentence.class);
		registerSentenceType("HTD", HTDSentence.class);
		registerSentenceType("MHU", MHUSentence.class);
		registerSentenceType("MMB", MMBSentence.class);
		registerSentenceType("MTA", MTASentence.class);
		registerSentenceType("MTW", MTWSentence.class);
		registerSentenceType("MWD", MWDSentence.class);
		registerSentenceType("MWV", MWVSentence.class);
		registerSentenceType("OSD", OSDSentence.class);
		registerSentenceType("POS", POSSentence.class);
		registerSentenceType("PRC", PRCSentence.class);
		registerSentenceType("QDG", QDGSentence.class);
		registerSentenceType("RMC", RMCSentence.class);
		registerSentenceType("ROT", ROTSentence.class);
		registerSentenceType("RPM", RPMSentence.class);
		registerSentenceType("RSA", RSASentence.class);
		registerSentenceType("RTE", RTESentence.class);
		registerSentenceType("TLL", TLLSentence.class);
		registerSentenceType("TPP", TPPSentence.class);
		registerSentenceType("TRC", TRCSentence.class);
		registerSentenceType("TRD", TRDSentence.class);
		registerSentenceType("TTM", TTMSentence.class);
		registerSentenceType("TVP", TVPSentence.class);
		registerSentenceType("VBW", VBWSentence.class);
		registerSentenceType("VDR", VDRSentence.class);
		registerSentenceType("VEL", VELSentence.class);
		registerSentenceType("VTG", VTGSentence.class);
		registerSentenceType("ZDA", ZDASentence.class);
		
		
		//register proprietary sentences
		registerSentenceType("ENG", ENGSentence.class);
		registerSentenceType("MEA", MEASentence.class);
		registerSentenceType("PAR", PARSentence.class);
		registerSentenceType("RUD", RUDSentence.class);
		registerSentenceType("SID", SIDSentence.class);
		registerSentenceType("WEI", WEISentence.class);
	}
	
	/**
	 * Parses a valid sentence and returns a concrete instance of
	 * {@link Sentence}. It does not validate the String, pass only valid
	 * Strings here, you can use {@link SentenceUtils#validateSentence(String)},
	 * before calling this.
	 * 
	 * @param nmea
	 *            The String to parse.
	 * @return A concrete instance of a {@link Sentence}
	 * @throws IllegalArgumentException
	 *             <ol>
	 *             <li>The given String could not be parsed, cause there is no parser registered for the sentenceId given by the String.</li>
	 *             <li>A given extended {@ling Sentence} could not be instantiated (error in the constructor).</li>
	 *             </ol>
	 */
	public Sentence createSentence(String nmea) {
		String sid = SentenceUtils.getSentenceId(nmea);
		
		if (!hasSentenceType(sid)) {
			String msg = String.format("Unknown sentence type '%s'", sid);
			throw new IllegalArgumentException(msg);
		}

		Sentence parser = null;
		Class<?> klass = nmea.getClass();

		try {
			Class<? extends Sentence> c = sentenceTypes.get(sid);
			Constructor<? extends Sentence> co = c.getConstructor(klass);
			parser = co.newInstance(nmea);
		} catch (Exception e) {
			throw new IllegalStateException("Unable to instanciate sentence.", e);
		}
		
		return parser;
	}
	
	/**
	 * Checks whether a given sentenceId is already registered.
	 * 
	 * @param type
	 *            A sentenceId.
	 * @return true, if there is already a parser registered for the given
	 *         sentenceId.
	 */
	public boolean hasSentenceType(String type) {
		return sentenceTypes.containsKey(type);
	}
	
	/**
	 * Registers a new sentenceId with a given concrete class of
	 * {@link Sentence} as a parser.
	 * 
	 * @param type
	 *            SentenceId to register.
	 * @param sentenceType
	 *            Class of the extended {@link Sentence} as a parser.
	 * @throws IllegalArgumentException
	 *             <ol>
	 *             <li>The given sentenceId is already registered.</li>
	 *             <li>The given class does not have a constructor with a String.</li>
	 *             </ol>
	 */
	public void registerSentenceType(String type, Class<? extends Sentence> sentenceType) {
		if (hasSentenceType(type)) {
			throw new IllegalArgumentException(String.format("Sentence type '%s' already registered", type));
		}
		try {
			sentenceType.getConstructor(String.class);
			sentenceTypes.put(type, sentenceType);
		} catch (NoSuchMethodException e) {
			String msg = "Required constructors not found; Sentence(String)";
			throw new IllegalArgumentException(msg, e);
		}
	}
}
