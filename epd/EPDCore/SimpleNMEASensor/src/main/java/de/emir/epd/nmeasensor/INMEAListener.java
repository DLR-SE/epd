package de.emir.epd.nmeasensor;

import de.emir.service.codecs.nmea0183.encoding.sentence.Sentence;

public interface INMEAListener {
	public void update(NMEASensor nmeaSensor, Sentence sentence);	
}
