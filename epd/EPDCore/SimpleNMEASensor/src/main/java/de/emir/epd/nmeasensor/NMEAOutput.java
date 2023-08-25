package de.emir.epd.nmeasensor;

import de.emir.service.codecs.nmea0183.encoding.sentence.Sentence;
import de.emir.tuml.ucore.runtime.logging.ULog;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import org.slf4j.Logger;

import java.util.AbstractMap;
import java.util.Map;

public class NMEAOutput {
    private static final Logger LOG = ULog.getLogger(NMEAOutput.class);
    private static BehaviorSubject<Map.Entry<NMEASensor, Sentence>> publisher = BehaviorSubject.create();

	public static synchronized Disposable subscribeSentences(Consumer<Map.Entry<NMEASensor, Sentence>> consumer){
    	return publisher.subscribe(consumer);
	}

	public static BehaviorSubject<Map.Entry<NMEASensor, Sentence>> getPublisher() {
		return publisher;
	}

	public static void notify(NMEASensor sensor, Sentence sentence) {
		try {
			publisher.onNext(new AbstractMap.SimpleEntry<>(sensor, sentence));
		}catch (Exception e){
			LOG.debug("Could not notify NMEAOutput. {}", e);
		}

	}
}
