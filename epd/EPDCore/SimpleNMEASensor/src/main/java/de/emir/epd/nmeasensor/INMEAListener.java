package de.emir.epd.nmeasensor;

import net.sf.marineapi.nmea.sentence.Sentence;

/**
 * This interface allows handling of MarineAPI NMEA sentences from an NMEASensor.
 */
public interface INMEAListener {
	/**
	 * Call this whenever a NMEASensor has received and parsed a Sentence.
	 *  
	 * @param nmeaSensor the sensor which received the sentence
	 * @param sentence the received Sentence object
	 */
	public void update(NMEASensor nmeaSensor, Sentence sentence);	
}
