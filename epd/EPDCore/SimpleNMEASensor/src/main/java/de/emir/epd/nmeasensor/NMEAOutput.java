package de.emir.epd.nmeasensor;

import java.util.AbstractMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import de.emir.tuml.ucore.runtime.logging.ULog;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import net.sf.marineapi.ais.message.AISMessage;
import net.sf.marineapi.nmea.sentence.Sentence;

/**
 * Connect the NMEASensors to interested consumers. 
 */
public class NMEAOutput {
	/** Logger. **/
    private static final Logger LOG = ULog.getLogger(NMEAOutput.class);
    
    /** Handle to the publisher for NMEA Sentences for a given sensor. **/
    private static BehaviorSubject<Map.Entry<NMEASensor, Sentence>> publisher = BehaviorSubject.create();

	/** Handle to the publisher for AIS messages for a given sensor. **/
	private static BehaviorSubject<Map.Entry<NMEASensor, AISMessage>> aisPublisher = BehaviorSubject.create();
    
    /**
     * This methods allows interested consumers to register a subscription with a publisher.
     * 
     * @param consumer the interested consumer to register
     * @return the new {@link Disposable} instance that can be used to dispose the subscription at any time
     */
	public static synchronized Disposable subscribeSentences(Consumer<Map.Entry<NMEASensor, Sentence>> consumer) {
    	return publisher.subscribe(consumer);
	}

	/**
	 * Get the handle to the publisher for NMEA sentences.
	 * @return the publisher
	 */
	public static BehaviorSubject<Map.Entry<NMEASensor, Sentence>> getPublisher() {
		return publisher;
	}

	/**
	 * Notify registered consumers about the new NMEA sentence.
	 * 
	 * @param sensor the sensor in which the sentence was received
	 * @param sentence the newly received sentence to inform the consumers about
	 */
	public static void notify(NMEASensor sensor, Sentence sentence) {
		try {
			publisher.onNext(new AbstractMap.SimpleEntry<>(sensor, sentence));
		} catch (Exception e) {
			LOG.debug("Could not notify NMEAOutput. {}", e);
		}
	}
	
	/**
     * This methods allows interested consumers to register a subscription to AIS messages with a publisher.
     * 
     * @param consumer the interested consumer to register
     * @return the new {@link Disposable} instance that can be used to dispose the subscription at any time
     */
	public static synchronized Disposable subscribeAISMessages(Consumer<Map.Entry<NMEASensor, AISMessage>> consumer) {
    	return aisPublisher.subscribe(consumer);
	}

	/**
	 * Get the handle to the publisher for AIS messages.
	 * @return the publisher
	 */
	public static BehaviorSubject<Map.Entry<NMEASensor, AISMessage>> getAISPublisher() {
		return aisPublisher;
	}

	/**
	 * Notify registered consumers about the new AIS message.
	 * 
	 * @param sensor the sensor in which the message was received
	 * @param message the newly received AIS message to inform the consumers about
	 */
	public static void notifyAis(NMEASensor sensor, AISMessage message) {
		try {
			aisPublisher.onNext(new AbstractMap.SimpleEntry<>(sensor, message));
		} catch (Exception e) {
			LOG.debug("Could not notify NMEAOutput. {}", e);
		}
	}
}
