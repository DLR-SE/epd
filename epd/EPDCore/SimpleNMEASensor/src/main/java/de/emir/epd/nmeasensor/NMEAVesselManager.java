package de.emir.epd.nmeasensor;

import de.emir.epd.alert.manager.AlertManager;
import de.emir.epd.alert.manager.AlertState;
import de.emir.epd.model.ais.AisTarget;
import de.emir.epd.nmeasensor.ids.NMEASensorIds;
import de.emir.epd.nmeasensor.verify.VesselVerifier;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.service.codecs.nmea0183.encoding.sentence.Sentence;
import de.emir.tuml.ucore.runtime.logging.ULog;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import org.slf4j.Logger;

import java.util.Optional;

public class NMEAVesselManager {
    private static final Logger LOG = ULog.getLogger(NMEAVesselManager.class);

    private static BehaviorSubject<Optional<Sentence>> ownShipSubject = BehaviorSubject
            .createDefault(Optional.empty());
    private static Vessel ownShip;

    static {
        ownShipSubject.subscribe(new Consumer<Optional<Sentence>>() {
            @Override
            public void accept(Optional<Sentence> sentence) throws Exception {
                if(sentence.isPresent() == false){
                    return;
                }

                if(ownShip != null){
                    Sentence current = sentence.get();
                    NMEAVesselUpdater.mapToVessel(current.toMap(), ownShip);
                    AisTarget.updateVessel(ownShip);

                    try {
                        VesselVerifier.verify(ownShip);
                        if (VesselVerifier.isCorrect(ownShip) == false){
                            AlertManager.setState(NMEASensorIds.NMEA_SENSOR_OWNSHIP_ALERT_ID, AlertState.PARTIAL);
                        }else {
                            AlertManager.setState(NMEASensorIds.NMEA_SENSOR_OWNSHIP_ALERT_ID, AlertState.OK);
                        }
                    }catch (Exception e){
                    	e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * Registers an ownship which will get updates from NMEASensor
     * @param vessel
     */
    public static void registerOwnShip(Vessel vessel){
        if(ownShip == null){
            ownShip = vessel;
            ownShip.registerTreeListener(notification -> LOG.trace("ownship " + ownShip.getPose()));
        }else {
            LOG.error("Multiple Ownship's? Lets ignore it for now");
        }
    }

    public static void updateOwnShip(Sentence sentence){
        if(sentence == null) return;
        ownShipSubject.onNext(Optional.of(sentence));
    }

    public static Vessel getOwnShip() {
        return ownShip;
    }
}
