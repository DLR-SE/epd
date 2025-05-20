package de.emir.epd.nmeasensor;

import org.apache.logging.log4j.Logger;

import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class NMEAVesselManager {
	/** Logger. **/
    private static final Logger LOG = ULog.getLogger(NMEAVesselManager.class);
    
    /** Static reference to the ownship Vessel object. **/
    private static Vessel ownShip;

    /**
     * Registers an ownship which will get updates from NMEASensor
     * 
     * @param vessel the vessel object to register as ownship
     */
    public static void registerOwnShip(Vessel vessel){
        if(ownShip == null){
            ownShip = vessel;
            ownShip.registerTreeListener(notification -> LOG.trace("ownship " + ownShip.getPose()));
        }else {
            LOG.error("Multiple Ownship's? Lets ignore it for now");
        }
    }

    /**
     * Get the ownship object.
     * 
     * @return the ownship
     */
    public static Vessel getOwnShip() {
        return ownShip;
    }
}
